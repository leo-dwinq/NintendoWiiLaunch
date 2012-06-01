package com.dwinq.nintendoRegistration.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;



public class MailUtil {
	private static Logger logger = Logger.getLogger(MailUtil.class.getName());
	public static void mail (String from, String to, String subject, String body) throws Exception {
		Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress(to));
            msg.setSubject(subject);
            msg.setContent(body, "text/html");
            Transport.send(msg);
        } catch (Exception e) {
        	e.printStackTrace();
        	throw e;
        }
	}
	
	public static String defaultSender () {
		String applicationId = (String) System.getProperties().get("com.google.appengine.application.id");
		return "dwinq-notifier@" + applicationId + ".appspotmail.com";
	}
}	
