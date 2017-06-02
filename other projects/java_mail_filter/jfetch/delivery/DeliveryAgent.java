package jfetch.delivery;

import javax.mail.Message;
import javax.mail.MessagingException;

import org.apache.avalon.Component;
import org.apache.avalon.Initializable;
import org.apache.avalon.configuration.Configurable;
import org.apache.avalon.configuration.Configuration;
import org.apache.avalon.util.ObjectUtil;

public abstract class DeliveryAgent implements Component {

    protected String id;

    /**
     * Setup the agent as specified in the configuration and then return it.
     * The returned Delivery Agent (subclass) is now ready for use.
     *
     * @param conf The configuration as specified by the user.
     * @return The subclass implementation - as specified in the conf
     * @exception Exception if an error occurs
     */
    public static final DeliveryAgent setupAgent(Configuration conf) throws Exception {
        String className = conf.getAttribute( "class" );
        DeliveryAgent da = (DeliveryAgent) ObjectUtil.createObject( className );

        // must have attribute
        da.id = conf.getAttribute( "id" );
        
        // A DeliveryAgent should ideally implement Configurable
        if(da instanceof Configurable) {
            ((Configurable)da).configure( conf );
        }
        // just in case they implement Initialize
        if(da instanceof Initializable) {
            ((Initializable)da).init();
        }
        return da;
    }

    /**
     * Open and initialise any resources if required.
     *
     * @exception Exception if an error occurs
     */
    public abstract void open() throws Exception;

    /**
     * Deliver the message.
     *
     * @exception MessagingException if an error occurs
     */
    public abstract void deliver(Message message) throws MessagingException;

    /**
     * Cleanup any resources if required.
     *
     */
    public abstract void close() throws Exception;

    public String getId() {
        return id;
    }

} // DeliveryAgent
