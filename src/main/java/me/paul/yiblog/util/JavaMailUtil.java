package me.paul.yiblog.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailUtil {

	public static void sendEmail(String email, String content) throws MessagingException, UnsupportedEncodingException {
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.163.com");
		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("m15521294948@163.com",
						"229267643aa");
			}
		};
		Session session = Session.getDefaultInstance(prop, auth);

		Message message = new MimeMessage(session);
		message.setHeader("Content-Type", "text/html;charset=utf-8");
		message.setSubject("来自yiblog的验证码");
		message.setFrom(new InternetAddress("m15521294948@163.com", "Paul"));
		message.setRecipient(RecipientType.TO, new InternetAddress(email));
		message.setText(content);
		Transport.send(message);

	}

}
