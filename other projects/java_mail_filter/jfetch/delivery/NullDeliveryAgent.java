

package jfetch.delivery;

import javax.mail.Message;
import javax.mail.MessagingException;

/**
 * Do absolutely nothing with a message.
 * This is to allow a maildrop to efficiently process
 * messages that make it through all filters that we don't
 * want to touch, if that would be the desired default.
 *
 * 
 */
public class NullDeliveryAgent extends DeliveryAgent {

    /**
     * We need this for dynamic instantiation.
     */
    public NullDeliveryAgent() {
    }

    /**
     * Do nothing.
     *
     * @exception Exception if an error occurs
     */
    public void open() throws Exception {
    }

    /**
     * Do nothing.
     *
     * @param message a <code>Message</code> value
     * @exception MessagingException if an error occurs
     */
    public void deliver(Message message) throws MessagingException {
    }

    /**
     * Do nothing.
     *
     * @exception Exception if an error occurs
     */
    public void close() throws Exception {
    }

} // NullDeliveryAgent
