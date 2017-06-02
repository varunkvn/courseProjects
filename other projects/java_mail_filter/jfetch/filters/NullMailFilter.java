
package jfetch.filters;

import javax.mail.*;

import org.apache.avalon.Initializable;
import org.apache.log.Logger;
import org.apache.log.LogKit;

import jfetch.util.MessageUtil;

/**
 * Consume all messages - also mark them for deletion.
 *
 * 
 */
public class NullMailFilter implements MailFilter, Initializable  {
    private Logger logger;
    public NullMailFilter() {        
    }

    public void init() {
        logger = LogKit.getLoggerFor("nullfilter");
    }

    /**
     * Always consume a message
     *
     * @param message a <code>Message</code> value
     * @return a <code>boolean</code> value
     */
    public boolean process( Message message ) {
        try {
            message.setFlag(Flags.Flag.DELETED, true);
        } catch (MessagingException me) {
            logger.error("Could not set deleted flag on message", me);
        }
        logger.info("Consumed message: " + MessageUtil.toString(message));
		return false;
    }
    
} // NullMailFilter
