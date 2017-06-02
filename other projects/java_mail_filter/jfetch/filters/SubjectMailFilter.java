package jfetch.filters;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Iterator;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Flags;
import javax.mail.search.SubjectTerm;

import java.util.Properties;
import java.util.Enumeration;

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
 * Restrict the download of mail through a subject based filter.
 * Subjects are stored in a properties file; match is by substring,
 * and is case-insensitive.
 *
 * Configuration is of the form:
 * <filter class="..." delete=true
 * blocklist="../spool/virus-spam_subjects.list">
 * </filter>
 *
 * 
 */
public class SubjectMailFilter implements MailFilter, Configurable, 
                                          Initializable {
    private Configuration conf;
    private Logger logger;
    private boolean delete;
    private String propsFile = null;
    private Properties props;
    private String mdaid = null;
    private DeliveryAgent deliveryAgent = null;

    public SubjectMailFilter() {
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
        logger = LogKit.getLoggerFor("subjfilter");
        loadProps();
        if(mdaid != null)
            deliveryAgent = DeliveryManager.getInstance().getAgent( mdaid );
    }

    /**
     * Check for a match with the subject of the message in the cache
     *
     * @param message a <code>Message</code> value
     * @return a <code>boolean</code> value
     */
    public boolean process( Message message ) {
        boolean found = false;
        SubjectTerm searchTerm;
        Enumeration propsEnum = props.propertyNames();

        while( propsEnum.hasMoreElements() ) {
            searchTerm = new SubjectTerm( (String)propsEnum.nextElement() );
            if ( searchTerm.match(message) ) {
                found = true;
                break;
            }
        }

        if( found ) {
            if( deliveryAgent != null ) {
                try {
                    deliveryAgent.open();
                    deliveryAgent.deliver( message );
                    deliveryAgent.close();
                } catch(Exception e) {
                    logger.error( "Could not deliver message ", e );
                    return true;
                }
            }

            if(delete) {
                logger.info("Message subject found in list; deleting, " + MessageUtil.toString(message));
                try {
                    message.setFlag(Flags.Flag.DELETED, true);
                } catch(MessagingException me) {
                    logger.error("Could not mark message for deletion", me);
                }
            } else {
                logger.info("Message subject found in list, skipping, " + MessageUtil.toString(message));
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
            try {
                if( fin != null )
                    fin.close();
            } catch( IOException ignoreEx ) { }
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

} // SubjectMailFilter
