package com.gameshop.spring.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {

	//Emails are sent using GMail SMTP, a Gmail account is needed with the option to allow access from less secure apps
	
	//Email username, the part before the @, set here
	private static String senderName = ;
	//Your password to the above email account, set here
	private static String senderPass = ;
	private static String host = "smtp.gmail.com";

	public static void accountDeleteNotification(String recipient, String first) {

		Properties props = System.getProperties();

		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		props.put("mail.smtp.user", EmailUtil.senderName);
		props.put("mail.smtp.password", EmailUtil.senderPass);
		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(EmailUtil.senderName));
			InternetAddress toAddress = new InternetAddress(recipient);

			message.setSubject("Goodbye " + first + "!");
			message.setText("Thank you for being one of our valued customers. "
					+ "We're sad to see you go, but glad we could serve. "
					+ "Feel free to browse our collection of games (you don't need an account for that), "
					+ "and if you decide to come back, you can register again.\n\nRegards,\nThe GameShop Team");

			Transport transport = session.getTransport("smtp");
			transport.connect(EmailUtil.host, EmailUtil.senderName, EmailUtil.senderPass);
			transport.sendMessage(message, message.getAllRecipients());
		
			message.addRecipient(Message.RecipientType.TO, toAddress);
			
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void orderConfirmation(String recipient) {

		Properties props = System.getProperties();

		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		props.put("mail.smtp.user", EmailUtil.senderName);
		props.put("mail.smtp.password", EmailUtil.senderPass);
		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(EmailUtil.senderName));
			InternetAddress toAddress = new InternetAddress(recipient);

			message.setSubject("Your order has been received!");
			message.setText(
					"Your order has been received! Your games will be delivered soon! Enjoy!\n\nSincerely,\nThe GameShop Team");

			Transport transport = session.getTransport("smtp");
			transport.connect(EmailUtil.host, EmailUtil.senderName, EmailUtil.senderPass);
			transport.sendMessage(message, message.getAllRecipients());
			
			message.addRecipient(Message.RecipientType.TO, toAddress);
			
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void registrationNotification(String recipient, String first) {

		Properties props = System.getProperties();

		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		props.put("mail.smtp.user", EmailUtil.senderName);
		props.put("mail.smtp.password", EmailUtil.senderPass);
		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(EmailUtil.senderName));
			InternetAddress toAddress = new InternetAddress(recipient);


			message.setSubject("Welcome");
			message.setText("Welcome\n\nRegards,\nThe GameShop Team");

			Transport transport = session.getTransport("smtp");
			transport.connect(EmailUtil.host, EmailUtil.senderName, EmailUtil.senderPass);
			transport.sendMessage(message, message.getAllRecipients());
			
			message.addRecipient(Message.RecipientType.TO, toAddress);
			transport.sendMessage(message, message.getAllRecipients());
			
			transport.close();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void recoverPassword(String recipient, String password) {

		Properties props = System.getProperties();

		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		props.put("mail.smtp.user", EmailUtil.senderName);
		props.put("mail.smtp.password", EmailUtil.senderPass);
		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(EmailUtil.senderName));
			InternetAddress[] toAddress = new InternetAddress(recipient);

			message.setSubject("Password");
			message.setText("Hello,\nYour password is " + password + ".\nRegards,\nThe GameShop Team");

			Transport transport = session.getTransport("smtp");
			transport.connect(EmailUtil.host, EmailUtil.senderName, EmailUtil.senderPass);
			transport.sendMessage(message, message.getAllRecipients());
			
			message.addRecipient(Message.RecipientType.TO, toAddress);
			transport.sendMessage(message, message.getAllRecipients());
			
			transport.close();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
