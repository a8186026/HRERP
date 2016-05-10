package com.goldcow.emanage.util.mail;

import javax.mail.MessagingException;

import org.apache.commons.lang3.StringUtils;

import com.goldcow.emanage.util.spring.SpringUtil;

public class MailUtils {
	
	private SpringMail sender;
	
	public MailUtils(){
		sender = (SpringMail) SpringUtil.getObject("springMail");
	}
	
	public void send(String mailTo,String mailFrom, String subject, String text,
			String... file1) {
		if(StringUtils.isBlank(mailFrom)){
			mailFrom = sender.getFrom();
		}

		if(file1==null||file1.length==0){
			sender.sendTextMail(mailTo,mailFrom, subject, text);
		}else{
			sender.sendMimeMail(mailTo, mailFrom, subject, text, file1);
		}
		
	}
	public static void sendMail(String mailTo,String mailFrom, String subject, String text,
			String... file1) {
		MailUtils mu = new MailUtils();
		mu.send(mailTo, mailFrom, subject, text, file1);
	}
	
	public static void sendHtmlMail(String mailTo,String mailFrom, String subject, String text,
			String... file1) throws MessagingException{
		MailUtils mu = new MailUtils();
		mu.sendHtml(mailTo, mailFrom, subject, text, file1);
	}
	public void sendHtml(String mailTo,String mailFrom, String subject, String text,
			String... file) throws MessagingException{
		if(StringUtils.isBlank(mailFrom)){
			mailFrom = sender.getFrom();
		}
		sender.sendHtmlMail(mailTo, mailFrom, subject, text, file);
	}
	
}
