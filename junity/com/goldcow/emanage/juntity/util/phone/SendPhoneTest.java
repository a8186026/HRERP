package com.goldcow.emanage.juntity.util.phone;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.goldcow.emanage.juntity.com.JUnitTestBase;
import com.goldcow.emanage.util.mail.MailUtils;
import com.goldcow.emanage.util.phone.SendPhone;


public class SendPhoneTest extends JUnitTestBase<MailUtils> {

	@Test
	public void testAccountList() {
		String s = SendPhone.checkBalancesString();
		System.out.println(s);
//		String[] ss = {"15998369206","13840040617"};
//		try {
//			s = SendPhone.sendPhone(ss, "您的业务办理成功。");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		//SendPhone.sendPhone("13840040617", "您的收益已到账。");
		//s = SendPhone.checkBalancesString();
//		System.out.println(s);
	}


}
