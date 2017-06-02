
package jfetch.event;

import java.util.EventListener;

/**
 * The <code>DeliveryListener</code> is implemented by entities who want to be
 * notified when a message is successfully delivered or when delivery fails.
 *
 * 
 */
public interface DeliveryListener extends EventListener {

    /**
     * Message Delivery was successful.
     *
     * @param de a <code>DeliveryEvent</code> value
     */
    void messageDelivered(DeliveryEvent de);

    /**
     * Message delivery was not successful.
     *
     * @param de a <code>DeliveryEvent</code> value
     */
    void messageNotDelivered(DeliveryEvent de);
} // DeliveryListener
