
package jfetch.filters;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Flags;
import javax.mail.search.HeaderTerm;

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
 * Restrict the download of mail through a header based filter.
 * Header names and values are specified in the configuration as attributes
 *
 * Configuration is of the form <br>
 * &lt; filter class="..." delete="true" 
 * name="X-Spam-Rating" value="SPAM" &gt; <br>
 * &lt; /filter &gt;
 *
 * 
 */
public class HeaderMailFilter implements MailFilter, Configurable, Initializable {

    private Configuration conf;
    private Logger logger;
    private boolean delete;
    private HeaderTerm headerTerm;
    private String mdaid = null;
    private DeliveryAgent deliveryAgent = null;
    
    /* Allow dynamic creation */
    public HeaderMailFilter() {
    }

    public void configure( Configuration conf ) 
        throws ConfigurationException {
        this.conf = conf;
        delete = conf.getAttributeAsBoolean( "delete", false );
        String headerName = conf.getAttribute( "name" );
        String headerValue = conf.getAttribute( "value" );
        headerTerm = new HeaderTerm( headerName, headerValue );

        try {
            mdaid = conf.getAttribute( "mda" );
        } catch(ConfigurationException ce) {
        }
    }

    public void init() {
        logger = LogKit.getLoggerFor("hdrfilter");
        if(mdaid != null)
            deliveryAgent = DeliveryManager.getInstance().getAgent( mdaid );
    }

    /**
     * Check for the header of the message meeting specified criteria.
     *
     * @param message a <code>Message</code> value
     * @return a <code>boolean</code> value
     */
    public boolean process( Message message ) {

        if( headerTerm.match( message ) ) {
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
                logger.info("Message header found; deleting, " + MessageUtil.toString(message));
                try {
                    message.setFlag(Flags.Flag.DELETED, true);
                } catch(MessagingException me) {
                    logger.error("Could not mark message for deletion", me);
                }
            } else {
                logger.info("Message header found; skipping, " + MessageUtil.toString(message));
            }
            return false;
        } else {
            return true;
        }
    }

} // HeaderMailFilter
