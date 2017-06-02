
package jfetch.util;

import javax.mail.*;
import javax.mail.internet.*;

public class MessageUtil  {
    
    public static String getSender(Message message) {
        try {
            Address[] senders = message.getFrom();
            if(senders == null || senders.length == 0)
                return null;
            return ((InternetAddress) senders[0]).getAddress();
        } catch(MessagingException me) {
        }
        return null;
    }

    public static int getSize(Message message) {
        int size = -1;
        try {
            size = message.getSize();
        } catch(MessagingException me) {
        }
        return size;
    }

    public static String getSubject(Message message) {
        String subject = null;
        try {
            subject = message.getSubject();
        } catch(MessagingException me) {
        }
        return subject;
    }


    public static String toString(Message message) {
        return "From: " + getSender(message) + ", Sub: " + getSubject(message) + 
            ", (" + getSize(message) + ")";
    }

} // MessageUtil
