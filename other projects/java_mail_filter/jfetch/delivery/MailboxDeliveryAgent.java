package jfetch.delivery;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.mail.Header;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Folder;
import javax.mail.MessagingException;

import org.apache.avalon.Initializable;
import org.apache.avalon.configuration.Configurable;
import org.apache.avalon.configuration.Configuration;
import org.apache.avalon.configuration.ConfigurationException;
import org.apache.log.LogKit;
import org.apache.log.Logger;

/**
 * Deliver mail to a mailbox as expected.

 */
public class MailboxDeliveryAgent extends DeliveryAgent 
    implements Configurable, Initializable {
    
    private Configuration conf;
    private String destination;
    private boolean open = false;
    private Logger logger;
	private Store store;
	private Folder folder;

    /**
     * We need this for dynamic instantiation.
     */
    public MailboxDeliveryAgent() {
    }

    /**
     * Configure oneself. Configuration is of the form:
     * <mda .....>
     *   <destination>../var/spool/mail/vimala</destination>
     * </mda>
     *
     * @param conf a <code>Configuration</code> value
     * @exception ConfigurationException if an error occurs
     */
    public void configure(Configuration conf) throws ConfigurationException {
        this.conf = conf;
        destination = conf.getChild("destination").getValue();
    }

    /**
     * Get the logger.
     *
     */
    public void init() {
        logger = LogKit.getLoggerFor("mboxdelivery");
    }

    /**
     * Open the mailbox for delivery
     *
     * @exception Exception if an error occurs
     */
    public void open() throws Exception {
        if(open)
            return;
		Properties props = System.getProperties();
		Session session = Session.getDefaultInstance( props );
		
		store = session.getStore("mbox");
		store.connect();
		folder = store.getFolder( destination );

        // Create the folder if it doesn't exist
        if( !folder.exists() ) {
            folder.create( Folder.HOLDS_MESSAGES );
        }

		/*	
         * The mbox provider requires the folder to be opened
		 * for appending msgs; in violation of the javamail spec.
         */
		folder.open( Folder.READ_WRITE );

        logger.debug("Mailbox " + destination + " opened");
        open = true;
    }

    /**
     * Deliver (append) the message to the Mailbox.
     * 
     * @param message a <code>Message</code> value
     * @exception MessagingException if an error occurs
     */
    public void deliver(Message message) throws MessagingException {
        if(!open) {
            throw new IllegalStateException("DeliveryAgent: cannot deliver before opened.");
        }
        try {
            String sender = "unknown-could-not-get-sender-jfetch@host.domain";
            Address senders[] = message.getFrom();
            if(senders.length > 0)
                sender = ((InternetAddress) senders[0]).getAddress();

            String recdDate = getReceivedDate();

            Message msgs[] = new Message[1];
            msgs[0] = message;
            folder.appendMessages( msgs );

            // log the incoming message
            logger.debug("Appended message: " + sender + ", " + recdDate + ", " + 
                         ", " + "Sub: " + message.getSubject() + ", " + 
                         message.getSize());

        } catch(Exception e) {
            logger.error("Caught Exception", e);
            throw new MessagingException("Caught exception while delivering: " + e.getMessage(), e);
        }
    }

    /**
     * Close the mailbox
     *
     * @exception Exception if an error occurs
     */
    public void close() throws Exception {
        folder.close( true );
        store.close();
        open = false;
        logger.debug("Mailbox " + destination + " closed.");
    }

    /**
     * Generate a formatted string of the current date.
     *
     * @return Formatted string of the form "Sun Jun 25 01:57:24 2000"
     */
    private String getReceivedDate() {
        String pattern = "EEE MMM dd hh:mm:ss yyyy";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern); 
        Date d = new Date();
        String dateString = formatter.format(d);
        return dateString;
    }

} // MailboxDeliveryAgent
