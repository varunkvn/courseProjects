

package jfetch.event;

import javax.mail.Message;
import javax.mail.event.MailEvent;

/**
 * The Event triggered when the message is delivered - successfully or not.
 *
 * 
 */
public class DeliveryEvent extends MailEvent {
    /**
     * The message was delivered successfully
     *
     */
    public static final int DELIVERED = 1;
    /**
     * The message was not delivered successfully
     *
     */
    public static final int NOT_DELIVERED = 2;

    protected int type;

    /**
     * Create a new <code>DeliveryEvent</code> instance following message
     *
     * @param id an <code>int</code> value
     * @param source a <code>Message</code> value
     */
    public DeliveryEvent(int id, Message source) {
        super(source);
        this.type = id;
    }

    /**
     * Get the id of the message
     *
     * @return an <code>int</code> value
     */
    public int getType() {
        return type;
    }

    /**
     * Describe <code>dispatch</code> method here.
     *
     * @param dl a <code>DeliveryListener</code> value
     */
    public void dispatch(Object listener) {
        DeliveryListener dl = (DeliveryListener) listener;
        if(getType() == DELIVERED) {
            dl.messageDelivered(this);
        } else if(getType() == NOT_DELIVERED) {
            dl.messageNotDelivered(this);
        }
    }
} // DeliveryEvent
