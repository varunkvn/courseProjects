
package jfetch.delivery;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import org.apache.avalon.configuration.Configurable;
import org.apache.avalon.Initializable;
import org.apache.avalon.configuration.Configuration;
import org.apache.avalon.configuration.ConfigurationException;
import org.apache.log.LogKit;
import org.apache.log.Logger;

public class SMTPDeliveryAgent extends DeliveryAgent implements Configurable, Initializable {
    Logger logger;
	Configuration conf;
	String host;
	int port;
	String localUser;
	String user;
	String password;
	String domain;
	Session session;
	Transport transport;

	/**
     * Read up all the parameters from the configuration
     *
     * @param conf a <code>Configuration</code> value
     * @exception ConfigurationException if an error occurs
     */
    public void configure( Configuration conf ) throws ConfigurationException {
		this.conf = conf;
		host = conf.getChild( "host" ).getValue();
		port = conf.getChild( "port" ).getValueAsInt(25);
		localUser = conf.getChild( "localuser" ).getValue();
		user = conf.getChild( "user" ).getValue( "" );
		password = conf.getChild( "password" ).getValue( "" );
		domain = conf.getChild( "domain" ).getValue("localhost");
	}

	/**
     * Initialise the SMTP Transport
     *
     * @exception Exception if an error occurs
     */
    public void init() throws Exception {
		Properties props = System.getProperties();
		session = Session.getDefaultInstance(props, null);
		transport = session.getTransport("smtp");
        //        session.setDebug(true);
        logger = LogKit.getLoggerFor("smtpdelivery");
	}

	/**
     * Connect through the SMTP Transport
     *
     * @exception MessagingException if an error occurs
     */
    public void open() throws MessagingException {
        try {
            transport.connect( host, port, user, password );
        } catch( MessagingException me) {
            logger.error("Could not connect to SMTP host " + me.getMessage());
            throw me;
        }
	}

	/**
     * Deliver the message through SMTP
     *
     * @param message a <code>Message</code> value
     * @exception MessagingException if an error occurs
     */
    public void deliver( Message message ) throws MessagingException {
        //        message = getWriteableMessage( message );
        try {
            transport.sendMessage( message, new Address[] { new InternetAddress( localUser + "@" + domain ) } );
        } catch( MessagingException me) {
            logger.error("Could not deliver message through SMTP " + me.getMessage());
            throw me;
        }
   }

	/**
     * Close the transport
     *
     * @exception Exception if an error occurs
     */
    public void close() throws Exception {
		transport.close();
	}

    /**
     * Get a writeable version of the message - since transport would be updating
     * headers.
     */
    private Message getWriteableMessage( Message in ) {
        Folder f = in.getFolder();
        if( f == null ||  f.getMode() == Folder.READ_WRITE ) {
            return in;
        }
        try {
            // No alternative - need a writeable mime message.
            // ouch - the cast hurts :(
            Message out = new MimeMessage( (MimeMessage) in );
            return out;
        } catch ( Exception e ) {
            return in;
        }
    }

}
