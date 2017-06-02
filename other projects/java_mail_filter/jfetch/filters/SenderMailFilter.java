
package jfetch.filters;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Enumeration;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Flags;
import javax.mail.search.FromStringTerm;

import org.apache.log.Logger;
import org.apache.log.LogKit;
import org.apache.avalon.Initializable;
import org.apache.avalon.configuration.Configuration;
import org.apache.avalon.configuration.Configurable;
import org.apache.avalon.configuration.ConfigurationException;

import jfetch.delivery.DeliveryManager;
import jfetch.delivery.DeliveryAgent;
import jfetch.util.MessageUtil;

/**
 * Restrict the download of mail through a sender based filter.
 * Sender ids are stored in a properties file.
 *
 * Configuration is of the form <br>
 * &lt; filter class="..." delete="true" 
 * blocklist="../spool/spammer.list" &gt; <br>
 * &lt; /filter &gt;
 *
 * 
 */
public class SenderMailFilter implements MailFilter, Configurable, Initializable {

    private Configuration conf;
    private Logger logger;
    private boolean delete;
    private String propsFile = null;
    private Properties props;
    private String mdaid = null;
    private DeliveryAgent deliveryAgent = null;
    
    public SenderMailFilter() {
        props = new Properties();
    }

    public void configure( Configuration conf ) 
        throws ConfigurationException {
        this.conf = conf;
        delete = conf.getAttributeAsBoolean( "delete", false );
        propsFile = conf.getAttribute( "blocklist");
        try {
            mdaid = conf.getAttribute( "mda" );
        } catch(ConfigurationException ce) {
        }
    }

    public void init() {
        logger = LogKit.getLoggerFor("sndrfilter");
        loadProps();
        if(mdaid != null)
            deliveryAgent = DeliveryManager.getInstance().getAgent( mdaid );
    }

    /**
     * Check for the sender of the message in the cache
     *
     * @param message a <code>Message</code> value
     * @return a <code>boolean</code> value
     */
    public boolean process( Message message ) {
		FromStringTerm term;
		boolean found = false;
		Enumeration propsEnum = props.propertyNames();

        while( propsEnum.hasMoreElements() ) {
            term = new FromStringTerm( (String)propsEnum.nextElement() );
            if( term.match( message ) ) {
                found = true;
                break;
            }
        }

        if( found ) {
            if(deliveryAgent != null) {
                try {
                    deliveryAgent.open();
                    deliveryAgent.deliver(message);
                    deliveryAgent.close();
                } catch(Exception e) {
                    logger.error("Could not deliver message ", e);
                    return true;
                }
            }

            if(delete) {
                logger.info("Message sender found in list; deleting, " + MessageUtil.toString(message));
                try {
                    message.setFlag(Flags.Flag.DELETED, true);
                } catch(MessagingException me) {
                    logger.error("Could not mark message for deletion", me);
                }
            } else {
                logger.info("Message sender found in list, skipping, " + MessageUtil.toString(message));
            }
            return false;
        } else {
            return true;
        }
    }

    private void loadProps() {
		FileInputStream fin = null;
        try {
			fin = new FileInputStream( propsFile );
            props.load( fin );
        } catch(FileNotFoundException ignore) {
            logger.warn("Blocklist not found!");
        } catch(IOException ioe) {
            logger.error("Error loading blocklist", ioe);
        } finally {
			if( fin != null ) {
				try {
					fin.close();
				} catch( IOException exIgnored ) { }
			}
		}
        // cleanup the props - since we only handle lower case
        Properties sanitisedProps = new Properties();
        for(Iterator i=props.keySet().iterator(); i.hasNext(); ) {
            String key = (String) i.next();
            sanitisedProps.put(key.toLowerCase(), "");
        }
        props = sanitisedProps;
        logger.info("Loaded blocklist, " + props.size() + " entries");
    }
    
} // SenderMailFilter
