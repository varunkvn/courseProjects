
package jfetch.filters;

import javax.mail.*;
import javax.mail.event.*;

import org.apache.log.Logger;
import org.apache.log.LogKit;
import org.apache.avalon.Initializable;
import org.apache.avalon.configuration.Configuration;
import org.apache.avalon.configuration.Configurable;
import org.apache.avalon.configuration.ConfigurationException;

import jfetch.util.ListRepository;
import jfetch.util.MessageUtil;
import jfetch.event.DeliveryEvent;
import jfetch.event.DeliveryAdapter;

/**
 * Restrict the download of mail through a message-id filter.
 * Message-ids are cached in a repository
 *
 * Configuration is of the form <br>
 * &lt; filter class="..." max-size="25000" delete="true" 
 * repository="../spool/msgid.cache" &gt; <br>
 * &lt; /filter &gt;
 *
 * 
 */
public class MessageIDMailFilter extends DeliveryAdapter 
    implements MailFilter, Configurable, Initializable {

    Configuration conf;
    int size;
    Logger logger;
    boolean delete;
    ListRepository repository;
    
    public MessageIDMailFilter() {
    }

    public void configure( Configuration conf ) 
        throws ConfigurationException {
        this.conf = conf;
        size = conf.getAttributeAsInt( "max-size", 0 );
        delete = conf.getAttributeAsBoolean( "delete", false );
    }

    public void init() throws ConfigurationException {
        logger = LogKit.getLoggerFor("msgidfilter");
        repository = new ListRepository( );

        if( repository instanceof Configurable ) 
            repository.configure(conf.getChild("storage"));

        if( repository instanceof Initializable ) 
            repository.init();
    }

    /**
     * Check for the message-id of the message in the cache
     *
     * @param message a <code>Message</code> value
     * @return a <code>boolean</code> value
     */
    public boolean process( Message message ) {
        String messageID = getMessageID(message);
        if( messageID != null && repository.contains(messageID) ) {
            try {
                if( delete ) {
                    logger.info("Same msg-id, deleting message " +
							MessageUtil.toString(message));
                    message.setFlag(Flags.Flag.DELETED, true);
                } else {
                    logger.info("Same msg-id, skipping message " +
							MessageUtil.toString(message));
                }
            } catch(MessagingException me) {
                logger.error("Could not set delete flag on message", me);
            }
            return false;
        } else {
            return true;
        }
    }

    private String getMessageID(Message message) {
        String []messageID = new String[0];
        try {
            messageID = message.getHeader("message-id");
        } catch( MessagingException me ) {
            logger.error("Error while retrieving message id from message", me);
        }
        if(messageID != null && messageID.length > 0)
            return messageID[0];
        else
            return null;
    }

    public void messageDelivered(DeliveryEvent de) {
        String messageID = getMessageID((Message) de.getSource());
        if( messageID != null) {
            repository.add(messageID);
        }
    }

} // MessageIDMailFilter
