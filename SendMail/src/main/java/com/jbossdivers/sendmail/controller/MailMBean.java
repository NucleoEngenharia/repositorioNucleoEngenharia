package com.jbossdivers.sendmail.controller;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@ManagedBean
@ViewScoped
public class MailMBean {

    private String senderEmail;
    private String receiverEmail;
    private String subjectEmail;
    private String contentEmail;

    @Resource(mappedName="java:jboss/mail/Gmail")
    private Session mailSession;

    public void sendMail(){
        {

            try
            {
                MimeMessage message = new MimeMessage(mailSession);
                //Address from = new InternetAddress(senderEmail);
                Address[] to = new InternetAddress[] {new InternetAddress(receiverEmail) };

                //message.setFrom(from);
                message.setRecipients(Message.RecipientType.TO, to);
                message.setSubject(subjectEmail);
                message.setSentDate(new java.util.Date());
                message.setContent(contentEmail,"text/plain");
                Transport.send(message);
                // log.debug("Email Enviado!");

                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Status", "Email Enviado com Sucesso!!"));
            }
            catch (javax.mail.MessagingException e)
            {
                e.printStackTrace();
                //log.debug("Erro no envio do Email: " + e);
            }
        }
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getSubjectEmail() {
        return subjectEmail;
    }

    public void setSubjectEmail(String subjectEmail) {
        this.subjectEmail = subjectEmail;
    }

    public String getContentEmail() {
        return contentEmail;
    }

    public void setContentEmail(String contentEmail) {
        this.contentEmail = contentEmail;
    }
}
