package com.meitaomart.common.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailUtils {
	
	private final static String EMAIL_ADMIN_USERNAME = "donotreply@meitaomart.com";
	private final static String EMAIL_ADMIN_PASSWORD = "Nearr123!";
	private final static String[] DEFAULT_EMAIL_LIST = {"t.jacob1996@live.com", "jasonc@nearr.us","anluo1120@gmail.com"};
	private final static String JAVA_EXCEPTION_SUBJECT = "Java代码抛异常";
	
	public static void sendEmail(String toEmail, String subject, String body) {
		// property
		Properties props = System.getProperties();
		String host = "smtp.1and1.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.user", EMAIL_ADMIN_USERNAME);
		props.put("mail.smtp.password", EMAIL_ADMIN_PASSWORD);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.1and1.com");

		// authentication
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EMAIL_ADMIN_USERNAME, EMAIL_ADMIN_PASSWORD);
			}
		});
		try {
			// send message
			// text part
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(EMAIL_ADMIN_USERNAME));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			message.setSubject(subject);
			message.setText(body);
			// set pictures

			Transport transport = session.getTransport("smtp");
			transport.connect(host, EMAIL_ADMIN_USERNAME, EMAIL_ADMIN_PASSWORD);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

		} catch (AddressException ae) {
			EmailUtils.groupSendEmailForJavaException(ae.getStackTrace().toString());
		} catch (MessagingException me) {
			EmailUtils.groupSendEmailForJavaException(me.getStackTrace().toString());
		}
	}
	
	public static void groupSendEmailForJavaException(String body) {
		groupSendEmail(DEFAULT_EMAIL_LIST, JAVA_EXCEPTION_SUBJECT, body);
	}
	
	public static void groupSendEmail(String subject, String body) {
		groupSendEmail(DEFAULT_EMAIL_LIST, subject, body);
	}
	
	public static void groupSendEmail(String[] toEmailList, String subject, String body) {
		// property
		Properties props = System.getProperties();
		String host = "smtp.1and1.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.user", EMAIL_ADMIN_USERNAME);
		props.put("mail.smtp.password", EMAIL_ADMIN_PASSWORD);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.1and1.com");

		// authentication
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EMAIL_ADMIN_USERNAME, EMAIL_ADMIN_PASSWORD);
			}
		});
		try {
			// send message
			// text part
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(EMAIL_ADMIN_USERNAME));
			InternetAddress[] toAddress = new InternetAddress[toEmailList.length];

	        // To get the array of addresses
	        for( int i = 0; i < toEmailList.length; i++ ) {
	            toAddress[i] = new InternetAddress(toEmailList[i]);
	        }
			
	        for( int i = 0; i < toAddress.length; i++) {
	            message.addRecipient(Message.RecipientType.TO, toAddress[i]);
	        }
			message.setSubject(subject);
			message.setText(body);
			// set pictures

			Transport transport = session.getTransport("smtp");
			transport.connect(host, EMAIL_ADMIN_USERNAME, EMAIL_ADMIN_PASSWORD);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

		} catch (AddressException ae) {
			System.out.println(ae.getMessage());
		} catch (MessagingException me) {
			System.out.println(me.getMessage());
		}
	}
}
