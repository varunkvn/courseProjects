
package jfetch.filters;

import javax.mail.*;

import org.apache.log.Logger;
import org.apache.log.LogKit;
import org.apache.avalon.Initializable;
import org.apache.avalon.configuration.Configuration;
import org.apache.avalon.configuration.Configurable;
import org.apache.avalon.configuration.ConfigurationException;

import jfetch.util.MessageUtil;

/**
 * Restrict the download of mail through a size filter.
 *
 * Configuration is of the form <br>
 * &lt; filter class="..." max-size="25000" delete="true" &gt; <br>
 * &lt; /filter &gt;
 *
 * 
 */
public class SizeMailFilter implements MailFilter, Configurable, 
                                       Initializable {
    Configuration conf;
    int size;
    Logger logger;
    boolean delete;
    
    public SizeMailFilter() {
    }

    public void configure( Configuration conf ) {
        this.conf = conf;
        size = conf.getAttributeAsInt( "max-size", 0 );
        delete = conf.getAttributeAsBoolean( "delete", false );
    }

    public void init() {
        logger = LogKit.getLoggerFor("sizefilter");
    }

    /**
     * Check for the max size of a message
     *
     * @param message a <code>Message</code> value
     * @return a <code>boolean</code> value
     */
    public boolean process( Message message ) {
        if( size > 0 ) {
           int msgSize = MessageUtil.getSize( message );
           if( msgSize < 0 ) {
                logger.warn("Could not determine the size of the message");
                return true;
            }
            // set the message to deleted in case the delete flag is on
            if( msgSize > size ) {
                logger.debug("Filtering message of size " + msgSize);
                if( delete ) {
                    try {
                        message.setFlag(Flags.Flag.DELETED, true);
                    } catch (MessagingException me) {
                        logger.error("Could not set deleted flag on message", me);
                    }
                }
                return false;
            } 
        }
        return true;
    }
    
} // SizeMailFilter
