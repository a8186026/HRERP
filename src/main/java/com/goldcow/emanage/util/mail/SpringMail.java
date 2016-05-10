package com.goldcow.emanage.util.mail;

import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

public class SpringMail{
	
	private JavaMailSender sender; 
	
	private String from;
	
	public void sendTextMail(String mailTo,String mailFrom,String subject,String text){
		SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(mailTo);
        mail.setFrom(mailFrom);
        mail.setSubject(subject);
        mail.setText(text);
        sender.send(mail);
	}
	
	public void sendMimeMail(final String mailTo,final String mailFrom,
			final String subject,final String text,final String... file){
	        MimeMessagePreparator mimeMail = new MimeMessagePreparator() {
	            public void prepare(MimeMessage mimeMessage) throws MessagingException {
	                mimeMessage.setRecipient(Message.RecipientType.TO, 
	                        new InternetAddress(mailTo));
	                mimeMessage.setFrom(new InternetAddress(mailFrom));
	                mimeMessage.setSubject(subject, "UTF-8"); 
	                
	                Multipart mp = new MimeMultipart();
	                
	                //向Multipart添加正文
	                MimeBodyPart content = new MimeBodyPart();
	                content.setText(text);
	                
	                //向MimeMessage添加（Multipart代表正文）
	                mp.addBodyPart(content);
	                
	                //向Multipart添加附件
	                for(String f :file){
	                	
	                    MimeBodyPart attachFile = new MimeBodyPart();
	                    FileDataSource fds = new FileDataSource(f);
	                    attachFile.setDataHandler(new DataHandler(fds));
	                    String fileName = fds.getName();
	                    try {
							fileName = MimeUtility.encodeText(fileName);
						} catch (Exception e) {
							e.printStackTrace();
						}
	                    attachFile.setFileName(fileName);
	                    mp.addBodyPart(attachFile);
	                }
	                
	                //向Multipart添加MimeMessage
	                mimeMessage.setContent(mp);
	                mimeMessage.setSentDate(new Date());
	            }
	        };

	        //发送带附件的邮件
	        sender.send(mimeMail);
	}

	public void sendHtmlMail(String mailTo,String mailFrom,
			String subject,String text,String... file) throws MessagingException{
	        
	  
	        // 建立邮件消息,发送简单邮件和html邮件的区别  
	        MimeMessage mailMessage = sender.createMimeMessage();  
	        // 为防止乱码，添加编码集设置  
	        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,  
	                "UTF-8");  
	  
	        try {  
	            // 接收方邮箱  
	            messageHelper.setTo(mailTo);  
	        } catch (MessagingException e) {  
	            throw new RuntimeException("收件人邮箱地址出错！");  
	        }  
	        try {  
	            // 发送方邮箱  
	            messageHelper.setFrom(mailFrom);  
	        } catch (MessagingException e) {  
	            throw new RuntimeException("发件人邮箱地址出错！");  
	        }  
	        try {  
	            messageHelper.setSubject(subject);  
	        } catch (MessagingException e) {  
	            throw new RuntimeException("邮件主题出错！");  
	        }  
	        try {  
	            // true 表示启动HTML格式的邮件  
	            messageHelper.setText(text, true);  
	        } catch (MessagingException e) {  
	            throw new RuntimeException("邮件内容出错！");  
	        }  
	        InputStreamSource inputStreamSource = null;
            for(String f :file){
            	inputStreamSource = new ClassPathResource(f);
            	messageHelper.addAttachment(f,inputStreamSource);
            }
            
	        // 发送邮件  
	        sender.send(mailMessage);  
	  
	    }  

	public JavaMailSender getSender() {
		return sender;
	}

	public void setSender(JavaMailSender sender) {
		this.sender = sender;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}
	
	
	
}
