package com.ungs.revivir.negocios;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {/*

	public static boolean sendEmail(String email, String mensaje) {
		
		try {
			Properties props = new Properties();

			// Nombre del host de correo, es smtp.gmail.com
			props.setProperty("mail.smtp.host", "smtp.gmail.com");

			// TLS si est� disponible
			props.setProperty("mail.smtp.starttls.enable", "true");

			// Puerto de gmail para envio de correos
			props.setProperty("mail.smtp.port","587");

			// Nombre del usuario
			//props.setProperty("mail.smtp.user", "benescence@gmail.com");

			// Si requiere o no usuario y password para conectarse.
			props.setProperty("mail.smtp.auth", "true");
		
			// Get session
			
			Session session = Session.getDefaultInstance(props, null);
			session.setDebug(true);

			session.getProperties().put("mail.smtp.ssl.trust", "smtp.gmail.com");
			session.getProperties().put("mail.smtp.starttls.enable", "true");
			// Define message
			MimeMessage message = new MimeMessage(session);
			//message.setFrom(new InternetAddress("gfgrillo3@gmail.com"));
			message.setSubject("Nueva contrase�a");
			//EMAIL Destinatario
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setText(mensaje);
			// Envia el mensaje
			Transport t = session.getTransport("smtp");
			
			//Email de revivir, Contrasena de revivir
			String emailRevivir = Configuracion.leerDireccionEmail();
			String passwordRevivir = Configuracion.leerPasswordEmail();
			System.out.println(emailRevivir + "pass :  "+ passwordRevivir);
			t.connect(emailRevivir , passwordRevivir);
			
			t.sendMessage(message,message.getAllRecipients());
			t.close();
			
		} catch (Throwable e) {
			System.out.println("Fallo sendEmail al enviar Correo: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	*/
}