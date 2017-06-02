package jfetch.core;

import java.util.Properties;

import javax.mail.*;
import javax.mail.search.*;

import org.apache.avalon.configuration.Configurable;
import org.apache.avalon.Initializable;
import org.apache.avalon.Composer;
import org.apache.avalon.ComponentManager;
import org.apache.avalon.ComponentManagerException;
import org.apache.avalon.configuration.Configuration;
import org.apache.avalon.configuration.ConfigurationException;
import org.apache.log.LogKit;
import org.apache.log.Logger;

import jfetch.filters.FilterProcessor;
import jfetch.delivery.DeliveryManager;
import jfetch.delivery.DeliveryAgent;
import jfetch.event.DeliveryEvent;

import jfetch.util.MessageUtil;

public abstract class Maildrop implements Configurable, Initializable, Composer {
    protected Configuration conf;
    protected ComponentManager compMgr;
    protected String protocol;
    protected String host;
    protected String user;
    protected String password;
    protected int port;
    protected boolean delete;
    protected FilterProcessor filterProc;
    protected Logger logger = LogKit.getLoggerFor("maildrop");
    protected Session session;
    protected String mdaID;
    protected Store store;
    //    protected Folder folder;
    //    String folderName = "INBOX";

    protected Maildrop() {
    }
    
    /**
     * Get an instance of a Maildrop based on some properties of the Configuration
     *
     * @param conf a <code>Configuration</code> value
     * @return a <code>Maildrop</code> value
     * @exception ConfigurationException if an error occurs
     */
    public static final Maildrop getMaildrop( Configuration conf ) 
        throws ConfigurationException {

        String proto = conf.getAttribute( "protocol" );
        if( proto.equalsIgnoreCase("pop3") ) {
            return new POP3Maildrop();
        } else if( proto.equalsIgnoreCase( "imap" ) ) {
            return new IMAPMaildrop();
        } else if( proto.equalsIgnoreCase( "nntp" ) ) {
            return new NNTPMaildrop();
        }
        throw new ConfigurationException( "Invalid protocol in configuration of maildrop " + proto );
    }
    
    /**
     * Configure this maildrop
     *
     * @param conf a <code>Configuration</code> value
     * @exception ConfigurationException if an error occurs
     */
    public void configure(Configuration conf) throws ConfigurationException {
        this.conf = conf;
        protocol = conf.getAttribute( "protocol" );
        mdaID = conf.getAttribute( "mda" );
        host = conf.getChild( "host" ).getValue();
        port = conf.getChild( "port" ).getValueAsInt( getDefaultPort() );
        user = conf.getChild( "user" ).getValue("");
        password = conf.getChild( "password" ).getValue("");
        delete = conf.getChild( "delete" ).getValueAsBoolean( false );
        if(user.trim().equals(""))
            user = null;
        if(password.trim().equals(""))
            password = null;
    }

    /**
     * Compose this maildrop
     *
     * @param compManager a <code>ComponentManager</code> value
     */
    public void compose(ComponentManager compManager) {
        this.compMgr = compManager;
    }

    /**
     * Initialize this maildrop
     *
     * @exception Exception if an error occurs
     */
    public void init() throws Exception {
        FilterProcessor globalProc = null;
        try {
            globalProc = (FilterProcessor) compMgr.lookup("jfetch.filters.GlobalFilterProcessor");
        } catch(ComponentManagerException ce) {
            logger.debug("Could not locate global filtering component");
            globalProc = null;
        }

        // set up the filter processor local to this maildrop
        if(globalProc == null)
            filterProc = new FilterProcessor();
        else
            filterProc = new FilterProcessor(globalProc);

        if(filterProc instanceof Configurable ) {
            try {
                ((Configurable) filterProc).configure(conf.getChild("filters"));
            } catch(ConfigurationException ignore) {
            }
        }

        if(filterProc instanceof Initializable ) {
            try {
                ((Initializable) filterProc).init();
            } catch(Exception e) {
                logger.error("Could not initialize FilterProcessor.", e);
                throw e;
            }
        }

        // setup session etc.
        Properties props = System.getProperties();
        session = Session.getDefaultInstance(props);
    }

    /**
     * Connect to the Maildrop using the requested protocol. Download all the 
     * messages which are allowed to be downloaded by the filters and deliver
     * them using the Delivery Agent as specified in the configuration.
     * Note that on close, messages are deleted based on user configuration.
     * The process method gets all the Folders for the Maildrop and repeats 
     * the above mentioned process for each of them.
     * 
     * @see jfetch.delivery.DeliveryAgent
     * @see #getFolders()
     */
    public void process() {        
        try {
            connectStore();
        } catch(MessagingException me) {
            logger.error("Could not get/connect Store", me);
            return;
        }

        Folder[] folders = new Folder[0];
        try {
            folders = getFolders();
        } catch(MessagingException me) {
            logger.error("Could not get folders", me);
        }

        for( int i=0; i < folders.length; i++ ) {
	        try {
	            openFolder( folders[i] );
	        } catch(MessagingException me) {
	            logger.error("Could not open Folder", me);
	            continue;
	        }

            processFolder( folders[i] );

	        try {
    	        closeFolder( folders[i] );
	        } catch ( MessagingException me ) {
  	          logger.error("Could not close folder successfully", me);
   		    }

        }
        try {
            closeStore();
        } catch(MessagingException me) {
            logger.error("Could not close Store", me);
            return;
        }

    }


    /**
     * Process all the messages in a particular folder. This method is called
     * by the process() method. Caller has the responsibility of opening and closing
	 * the folder.
     *
     * @param folder a <code>Folder</code> value
     * @see #process()
     */
    protected void processFolder(Folder folder) {
        Message[] messages = new Message[0];
        try {
            messages = getMessages(folder);
        } catch ( MessagingException me ) {
            logger.error("Could not get messages from folder " + folder.getFullName(), me);
            return;
        }
        logger.info("Got " + messages.length + " messages in maildrop..");

        // have to init to null - grrrr...
        DeliveryAgent deliveryAgent = null;
        try {
            if(messages.length > 0) {
                deliveryAgent = openDeliveryAgent();
            }
        } catch (Exception e) {
            logger.error("Could not open DeliveryAgent", e);
            return;
        }

        boolean result;
        for( int i=0; i<messages.length; i++ ) {
            try {
                logger.debug("Starting download of message #" + (i+1) + " of " + messages.length + " (" + messages[i].getSize() + ")" );
            } catch(MessagingException me) {
                logger.error("Aborting, Error getting message #" + (i+1), me);
                closeDeliveryAgent(deliveryAgent);
                return;
            }

            result = true;
            // run the message through the filter processor
            try {
                result = filterProc.process(messages[i]);
            } catch ( Exception e ) {
                logger.error("Aborting, Error processing filter message #" + (i+1), e);
                closeDeliveryAgent(deliveryAgent);
                return;
            }             

            // now deliver it if we need to
            if(result) {
                try {
                    deliveryAgent.deliver( messages[i] );
                    if( delete )
                        deleteMessage( messages[i] );
                    
                    fireDeliveryEvent(messages[i]);
                    logger.debug( "Delivered, " +
                                  MessageUtil.toString(messages[i]));
                } catch ( Exception e ) {
                    logger.error("Aborting, Error delivering message #" + (i+1), e);
                    closeDeliveryAgent(deliveryAgent);
                    return;
                }
			}
        }
        
        if(messages.length > 0)
            closeDeliveryAgent(deliveryAgent);
    }

    /**
     * Close the delivery agent.
     *
     * @param da a <code>DeliveryAgent</code> value
     */
    private void closeDeliveryAgent(DeliveryAgent da) {
        try {
            da.close();
        } catch(Exception e) {
            logger.error("Could not close DeliveryAgent", e);
        }  
    }

    /**
     * Fire a delivery event(DELIVERED) to all the filters. 
     *
     * @param message a <code>Message</code> value
     */
    private void fireDeliveryEvent(Message message) {
        DeliveryEvent de = new DeliveryEvent(DeliveryEvent.DELIVERED, message);
        filterProc.fireEvent(de);
    }

    /**
     * Connect to a store.
     *
     * @exception MessagingException if an error occurs
     */
    protected void connectStore() throws MessagingException {
        store = session.getStore( protocol );
        logger.info("Connecting to " + user + "@" + host + " ...");
        store.connect( host, port, user, password );        
    }

    /**
     * Close the store.
     *
     * @exception MessagingException if an error occurs
     */
    protected void closeStore() throws MessagingException {
        logger.info("Closing connection to " + user + "@" + host + " ...");
        store.close();
    }

    /**
     * Get an array of all the folders that have to be processed for
     * messages in the store.
     *
     * @return a <code>Folder[]</code> value
     * @exception MessagingException if an error occurs
     */
    protected Folder[] getFolders() throws MessagingException {
        //XXX: Maybe wanna specify the Folder name later....
        Folder folder = store.getFolder( "INBOX" );

        if( folder == null || !folder.exists() ) {
            throw new MessagingException("Default folder does not exist");
        }

        Folder[] folders = { folder };
        return folders;
    }


    /**
     * Open the specified folder.
     *
     * @param folder a <code>Folder</code> value
     * @exception MessagingException if an error occurs
     */
    protected void openFolder(Folder folder) throws MessagingException {
        folder.open( Folder.READ_WRITE );
    }

    /**
     * Get all the messages for this store.
     *
     * @param folder a <code>Folder</code> value
     * @return a <code>Message[]</code> value
     * @exception MessagingException if an error occurs
     */
    protected Message[] getMessages(Folder folder) throws MessagingException {
        FlagTerm flagTerm = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
        Message messages[] = folder.search(flagTerm);
        /*
        Message messages[] = new Message[0];
        logger.info("Stating store for messages ...");
        messages = folder.getMessages();
        */
        return messages;
    }

    /**
     * Open the delivery agent,
     *
     * @return a <code>DeliveryAgent</code> value
     * @exception Exception if an error occurs
     */
    protected DeliveryAgent openDeliveryAgent() throws Exception  {
        DeliveryManager dm = DeliveryManager.getInstance();
        DeliveryAgent deliveryAgent = dm.getAgent(mdaID);
        deliveryAgent.open();
        return deliveryAgent;
    }

    /**
     * Delete a particular message
     *
     * @param message a <code>Message</code> value
     * @exception MessagingException if an error occurs
     */
    protected void deleteMessage(Message message) throws MessagingException {
        message.setFlag(Flags.Flag.DELETED, true);
    }

    /**
     * Close a particular folder. Expunge if required.
     *
     * @param folder a <code>Folder</code> value
     * @exception MessagingException if an error occurs
     */
    protected void closeFolder( Folder folder ) throws MessagingException {
        // delete the messages if atleast one message was deleted
        FlagTerm flagTerm = new FlagTerm(new Flags(Flags.Flag.DELETED), true);
        Message deletedMessages[] = folder.search(flagTerm);
        if(deletedMessages.length > 0) {
            folder.close(true);
        } else {
            // do whatever was chosen in the conf
            folder.close( delete );
        }
    }

    protected abstract int getDefaultPort();

} // Maildrop
