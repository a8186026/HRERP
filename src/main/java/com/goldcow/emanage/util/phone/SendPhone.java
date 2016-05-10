package com.goldcow.emanage.util.phone;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SendPhone {
	
    //查询余额
	//不开放
    //strRes = HttpSend.postSend(strBalanceUrl, strBalanceParam);
	public static String checkBalancesString (){
		 String strBalanceParam = "reg=" + SendPhoneCommon.strReg + "&pwd=" + SendPhoneCommon.strPwd;
		return  HttpSend.postSend(SendPhoneCommon.strBalanceUrl, strBalanceParam);
	}
    
    
    //状态报告
	//不开放
    //strRes = HttpSend.postSend(strStatusUrl, strStatusParam);
    
    //修改密码 
	//不开放修改密码接口，一旦密码修改，需要同时修改本地密码
    //strRes = HttpSend.postSend(strUpPwdUrl, strUpPwdParam);
//	private static String editPassword(String strNewPwd){
//		String strUpPwdParam = "reg=" + SendPhoneCommon.strReg 
//				+ "&pwd=" + SendPhoneCommon.strPwd 
//				+ "&newpwd=" + strNewPwd;
//		return HttpSend.postSend(SendPhoneCommon.strUpPwdUrl, strUpPwdParam);
//	}

    //定时短信
	/**
	 * 发送定时短信
	 * @param strPhone 电话号码集合
	 * @param strContent 发送内容
	 * @param date 发送时间
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String timingSendPhone(String strPhone, String strContent,Date date) throws UnsupportedEncodingException{
		String sendDate = dateToString(date);
		String strSchSmsParam = "reg=" + SendPhoneCommon.strReg 
				+ "&pwd=" + SendPhoneCommon.strPwd 
        		+ "&sourceadd=" + SendPhoneCommon.strSourceAdd 
        		+ "&tim=" + HttpSend.paraTo16(sendDate) 
        		+ "&phone=" + strPhone 
        		+ "&content=" + HttpSend.paraTo16(strContent+SendPhoneCommon.qm);
		return HttpSend.postSend(SendPhoneCommon.strSchSmsUrl, strSchSmsParam);
	}
	
	public static String dateToString(Date date) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format1.format(date);
	}

	/**
	 * 发送定时短信
	 * @param strPhone 电话号码集合
	 * @param strContent 发送内容
	 * @param date 发送时间
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String timingSendPhone(String[] strPhone, String strContent,Date date) throws UnsupportedEncodingException{
		return timingSendPhone(separatePhone(strPhone),strContent,date);
	}
	
    //发送短信
    //strRes = HttpSend.postSend(strSmsUrl, strSmsParam);
	/**
	 * 发送短信
	 * @param strPhone 电话号码集合
	 * @param strContent 发送内容
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String sendPhone(String strPhone, String strContent) throws UnsupportedEncodingException{
		String strSmsParam = "reg=" + SendPhoneCommon.strReg 
				+ "&pwd=" + SendPhoneCommon.strPwd 
				+ "&sourceadd=" + SendPhoneCommon.strSourceAdd 
				+ "&phone=" + strPhone 
				+ "&content=" + HttpSend.paraTo16(strContent+SendPhoneCommon.qm);
		return HttpSend.postSend(SendPhoneCommon.strSmsUrl, strSmsParam);
	}
	/**
	 * 发送短信
	 * @param strPhone 电话号码集合
	 * @param strContent 发送内容
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String sendPhone(String[] strPhone, String strContent) throws UnsupportedEncodingException{
		return sendPhone(separatePhone(strPhone),strContent);
	}
	
	public static String separatePhone(String[] strPhone) {
		StringBuffer sb = new StringBuffer();
		for(String s:strPhone){
			sb.append(",");
			sb.append(s);
		}
		return sb.substring(1, sb.length());
	}

	//注册
	public static String register() throws UnsupportedEncodingException{
	       String strRegParam = "reg=" + SendPhoneCommon.strReg 
	    		   + "&pwd=" + SendPhoneCommon.strPwd 
	    		   + "&uname=" + HttpSend.paraTo16(SendPhoneCommon.strUname) 
	    		   + "&mobile=" + SendPhoneCommon.strMobile 
	    		   + "&phone=" + SendPhoneCommon.strRegPhone 
	    		   + "&fax=" + SendPhoneCommon.strFax 
	    		   + "&email=" + SendPhoneCommon.strEmail 
                   + "&postcode=" + SendPhoneCommon.strPostcode 
                   + "&company=" + HttpSend.paraTo16(SendPhoneCommon.strCompany) 
                   + "&address=" + HttpSend.paraTo16(SendPhoneCommon.strAddress);

		return HttpSend.postSend(SendPhoneCommon.strRegUrl, strRegParam);
	}
    
    
}
