package com.goldcow.emanage.util.gen;


import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gx 描述：是系统管理模块的工具类
 * */
public class SysTools {
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(SysTools.class);

	public static String getIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (!checkIP(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (!checkIP(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (!checkIP(ip)) {
			ip = request.getRemoteAddr();
		}
		if("0:0:0:0:0:0:0:1".equals(ip)){
			ip = "127.0.0.1";
		}
		return ip;
	}

	private static boolean checkIP(String ip) {
		if (ip == null || ip.length() == 0 || "unkown".equalsIgnoreCase(ip)
				|| ip.split("\\.").length != 4) {
			return false;
		}
		return true;
	}

	/**
	 * 模糊匹配时将传入的参数的特殊字符(%\_)进行转义，并将前后增加通配符%
	 * 
	 * @param sqlParam
	 * @return
	 */
	public static String getSqlLikeParam(Object sqlParam) {
		if (sqlParam == null || StringUtils.isEmpty(sqlParam.toString())) {
			return null;
		}
		String param = sqlParam.toString();
		param = param.replace("\\", "\\\\").replace("%", "\\%").replace("_", "\\_");
		return "%" + param + "%";
	}
	
	/**
	 * 将UTF-8编码的字符串转为ISO-8859-1格式
	 * 
	 * @param src 源字符串
	 * @return 转码后字符串
	 */
	public static String encode(String src) {
		return convert(src, "UTF-8", "ISO-8859-1");
	}

	/**
	 * 将ISO-8859-1编码的字符串转为UTF-8格式
	 * 
	 * @param src 源字符串
	 * @return 转码后字符串
	 */
	public static String decode(String src) {
		return convert(src, "ISO-8859-1", "UTF-8");
	}
	public static String decodeGBK(String src) {
		return convert(src, "GBK", "UTF-8");
	}
	public static String decodeGB2312(String src) {
		return convert(src, "GB2312", "UTF-8");
	}

	/**
	 * 自定义转码格式
	 * 
	 * @param src 源字符串
	 * @param fromCharsetName 源编码格式
	 * @param toCharsetName 转换后编码格式
	 * @return 源字符串
	 */
	public static String convert(String src, String fromCharsetName, String toCharsetName) {
		if (src == null) {
			return null;
		}
		try {
			return new String(src.getBytes(fromCharsetName), toCharsetName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return src;
	}
}
