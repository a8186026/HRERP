package com.goldcow.emanage.util.network;

import org.apache.commons.lang3.StringUtils;

import com.goldcow.platform.syscontext.util.MD5Util;

public class LinkCodeManagerUtils {

	public static String createCode(String loginName, String loginPass,
			String requestIP, String time) {
		loginName = StringUtils.isBlank(loginName)?"":loginName;
		loginName = StringUtils.isBlank(loginPass)?"":loginPass;
		loginName = StringUtils.isBlank(requestIP)?"":requestIP;
		loginName = StringUtils.isBlank(time)?"":time;
		return MD5Util.get32BitMd5EncString(loginName+loginPass+requestIP+time);
	}

}
