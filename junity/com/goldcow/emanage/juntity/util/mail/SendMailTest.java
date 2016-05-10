package com.goldcow.emanage.juntity.util.mail;

import org.junit.Test;

import com.goldcow.emanage.juntity.com.JUnitTestBase;
import com.goldcow.emanage.util.mail.MailUtils;


public class SendMailTest extends JUnitTestBase<MailUtils> {

	@Test
	public void testAccountList() {
		String mailTo = "jiajiadaipingtai@163.com";
		String mailFrom = null;
		String subject = "佳家贷平台自动发送邮件";
		String text = "<html>"
				+ "<body>"
				+ "<h3>您好 $!{userName}, 欢迎您加入编程爱好者俱乐部!</h3>"
				+ "<div>"
				+ "您的email地址是<a href='mailto:${emailAddress}'>$!{emailAddress}</a>."
				+ "本条信息是系统自动发送，请勿回复！" + "</div>" + "</body>"
				+ "</html>";
		String file = "E:\\房贷信息保密等级说明书.doc";
		//String file2 = "D:\\svn\\jjd\\16其他\\短信接口\\Download_JAVA\\java例子(HTTP接口)\\给客户webHttpDemo UTF-8.htm";
		//MailUtils.sendMail(mailTo, mailFrom, subject, text,file,file2);
//		MailUtils.sendMail(mailTo, mailFrom, subject, text);
		try {
			MailUtils.sendHtmlMail(mailTo, mailFrom, subject, text, file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
