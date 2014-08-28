package com.nucleo.contratos.email;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
@ManagedBean
public class HorariosEmail  {
	@PostConstruct
	public void init(){
		mailSession = Session.getInstance(getProps());
	}
	private Session mailSession;
	private Properties props;
	
	public Properties getProps() {
		props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "smtp.nucleoengenharia.com.br");
		props.put("mail.smtp.port", "587"); 
		props.put("mail.smtp.auth", "false");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.starttls.enable","false"); 
		return props;
	}
	
	public void setProps(Properties props) {
		this.props = props;
	}
	
	public void enviarHorariosEmail(){
		mailSession.setDebug(true);
		System.out.println("Enviando email...");
		
		try {
		Calendar hoje = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		Message message = new MimeMessage(mailSession);
		Address[]to =  new InternetAddress[] {new InternetAddress("raphael.bernoldi@nucleoengenharia.com.br")
		};
		Transport transport = mailSession.getTransport();
		message.setRecipients(Message.RecipientType.TO,to);
		message.setFrom(new InternetAddress("controle.horarios@nucleoengenharia.com.br"));
		message.setSubject("Relatórios de horários "+format.format(hoje.getTime()));
		message.setText("Texto");
		message.saveChanges();
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
		System.out.println("Enviado com sucesso!");
		}catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
}
