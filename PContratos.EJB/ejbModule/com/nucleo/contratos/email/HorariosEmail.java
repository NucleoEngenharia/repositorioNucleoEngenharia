package com.nucleo.contratos.email;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class HorariosEmail  {
	public HorariosEmail() {
		mailSession = Session.getInstance(getProps());
	}
	private Session mailSession;
	private Properties props;
	
	public Properties getProps() {
		props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "smtp.nucleoengenharia.com.br");
		props.put("mail.smtp.port", "587"); 
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.starttls.enable","false"); 
		return props;
	}
	
	public void setProps(Properties props) {
		this.props = props;
	}
	public void enviarHorariosEmail(String relatorio){
		mailSession.setDebug(true);
		try {
		Calendar hoje = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		Message message = new MimeMessage(mailSession);
		Address[]to =  new InternetAddress[] {
		new InternetAddress("raphael.bernoldi@nucleoengenharia.com.br"),
		new InternetAddress("raphaelbernoldi@gmail.com"),
		new InternetAddress("ovidio.lins@nucleoengenharia.com.br")
		};
		Transport transport = mailSession.getTransport("smtp");
		transport.connect("rdpetrobras@intranet.nucleoengenharia.com.br", "123@mudar");
		message.setRecipients(Message.RecipientType.TO,to);
		message.setFrom(new InternetAddress("rdpetrobras@nucleoengenharia.com.br"));
		message.setSubject("Relatorios de horarios "+format.format(hoje.getTime()));
		message.setContent(relatorio, "text/html; charset=utf-8");
		message.saveChanges();
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
		}catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
