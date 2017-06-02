
package jfetch.filters;

/**
 * A <code>MailFilter</code> contains some processing logic as to whether to
 * download a message or not.
 * A simple example of a MailFilter could be a size based mail filter. Another 
 * could be an Anti-virus filter scanning for known viruses as attachments. 
 *
 * 
 */
public interface MailFilter {

    /**
     * The process methods decides whether to get the message or not.
     *
     * @param message a <code>javax.mail.Message</code> the message to process.
     * @return a <code>boolean</code> value
     */
    boolean process(javax.mail.Message message);

} // MailFilter
