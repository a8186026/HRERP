package com.goldcow.sframe.util;

import java.beans.PropertyDescriptor;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.bean.LoginUser;
import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.platform.syscontext.util.StringUtil;
import com.goldcow.sframe.util.mybatis.BaseEntity;
import com.goldcow.sframe.util.vo.UserBean;
import com.google.gson.Gson;
import com.sun.star.uno.RuntimeException;

public class SysUtil {
	/**
	 * 获取List中指定属性的值为propertyVal的列表
	 * 
	 * @param List
	 * @param propertyName
	 * @param propertyVal
	 */
	public static List<Map<String, Object>> getSamePropertyList(
			List<Map<String, Object>> list, String propertyName,
			String propertyVal) {
		if (list == null || list.size() == 0) {
			return null;
		}
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			String val = map.get(propertyName).toString();
			if (val == null) {
				continue;
			}
			if (val.equals(propertyVal)) {
				resultList.add(map);
			}
		}
		if (resultList.size() == 0) {
			return null;
		}
		return resultList;
	}

	/**
	 * 根据传入的属性名称、遍历list返回属性值与propertyVal相等的记录
	 * 
	 * @param list
	 * @param propertyName
	 * @param propertyVal
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static <T> List<T> getSamePropertyListBean(Collection<T> list,
			String propertyName, Object propertyVal) {
		if (list == null || list.size() == 0) {
			return null;
		}
		List<T> resultList = new ArrayList<T>();
		for (T bean : list) {
			Object val = null;
			try {
				if (bean instanceof java.util.Map) {
					val = ((java.util.Map) bean).get(propertyName);
				} else {
					PropertyDescriptor p = new PropertyDescriptor(propertyName,
							bean.getClass());
					val = p.getReadMethod().invoke(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (val == null) {
				continue;
			}
			if (val.equals(propertyVal)) {
				resultList.add(bean);
			}
		}
		if (resultList.size() == 0) {
			return null;
		}
		return resultList;
	}

	/**
	 * 返回与传入属性propertyName值相同的一个对象
	 * 
	 * @param list
	 * @param propertyName
	 * @param propertyVal
	 * @return
	 */
	public static <T> T getSamePropertyBean(Collection<T> list,
			String propertyName, Object propertyVal) {
		if (list == null || list.size() == 0) {
			return null;
		}
		for (T bean : list) {
			Object val = null;
			try {
				if (bean instanceof java.util.Map) {
					val = ((java.util.Map) bean).get(propertyName);
				} else {
					PropertyDescriptor p = new PropertyDescriptor(propertyName,
							bean.getClass());
					val = p.getReadMethod().invoke(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (val == null) {
				continue;
			}
			if (val.equals(propertyVal)) {
				return bean;
			}
		}
		return null;
	}

	/**
	 * 获取浏览器的ip地址
	 * @param request
	 * @return
	 */
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
	
	/**
	 * 获取用户对象
	 * @param request
	 * @return
	 */
	public static UserBean getUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return user;
	}
	
	/**
	 * 获取浏览器类型
	 * @param request
	 * @return
	 */
	public static String getUserAgent(HttpServletRequest request) {
		String agent = request.getHeader("User-Agent");
		return agent;
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
	public static String getSqlLikeParam(String sqlParam) {
		if (StringUtils.isBlank(sqlParam)) {
			return null;
		}
		sqlParam = sqlParam.trim();
		sqlParam = sqlParam.replace("\\", "\\\\");
		sqlParam = sqlParam.replace("%", "\\%");
		sqlParam = sqlParam.replace("_", "\\_");
		return "%" + sqlParam + "%";
	}
	
	/**
	 * 模糊匹配时将传入的参数的特殊字符(%\_)进行转义，并将前后增加通配符%
	 * 
	 * @param sqlParam
	 * @return
	 */
	public static String getSqlTrimParam(String sqlParam) {
		if (sqlParam == null) {
			return null;
		}
		return sqlParam.trim();
	}
	
	public static String ajaxFileTextEncoding(String srcStr, HttpServletRequest request){
		String res;
		String agent = request.getHeader("User-Agent").toLowerCase();
		if(agent.indexOf("firefox") > 0 || agent.indexOf("msie 10") > 0 || agent.indexOf("msie 9") > 0 || agent.indexOf("chrome") > 0){
			try {
				res = new String(srcStr.getBytes("UTF-8"), "ISO8859-1");
				return res;
			} catch (UnsupportedEncodingException e) {
				//log.error(e.getMessage());
			} 
		}else if(agent.indexOf("msie 8") > 0){
			return srcStr;
		}
		
		return srcStr;
	}
	
	/**
	 * 比较传入的两个javaBean内容是否一致，不比较last_update_user_id,last_update_timestamp
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public static <T> String checkBean(T obj1,T obj2){
		Class clazz = obj1.getClass();
		Field[] fields = clazz.getDeclaredFields();
		Map<String,String> map = new HashMap<String, String>();
		for(Field field:fields){
			try {
				PropertyDescriptor pd = new PropertyDescriptor(field.getName(),  
				        clazz);
				 Method getMethod = pd.getReadMethod();//获得get方法  
				 Object o1 = getMethod.invoke(obj1);
				 Object o2 = getMethod.invoke(obj2);
				 if((o1 == null && o2 == null)){
					 continue;
				 }
				 if((o1 == null || o2 == null)){
					 map.put(field.getName(), ":"+o1+"--》"+o2);
					 continue;
				 }
				 if(o1 instanceof String){
					 if(!o1.equals(o2)){
						 map.put(field.getName(), ":"+o1+"--》"+o2);
					 }
				 }else if(o1 instanceof BigDecimal){
					 if(((BigDecimal) o1).compareTo((BigDecimal) o2) != 0){
						 map.put(field.getName(), ":"+o1+"--》"+o2);
					 }
				 }else if(o1 instanceof Timestamp){
					if(((Timestamp) o1).getTime() != ((Timestamp) o2).getTime()){
						 map.put(field.getName(), ":"+o1+"--》"+o2);
					 }
				 }else if(o1 instanceof Date){
					 if(((Date) o1).getTime() != ((Date) o2).getTime()){
						 map.put(field.getName(), ":"+o1+"--》"+o2);
					 }
				 }else if(o1 instanceof Integer){
					 if(((Integer) o1).intValue() == ((Integer) o2).intValue() ){
						 map.put(field.getName(), ":"+o1+"--》"+o2);
					 }
				 }else{
					 if(!o1.toString().equals(o2.toString()) ){
						 map.put(field.getName(), ":"+o1+"--》"+o2);
					 }
				 }
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		}
		if(map.size() > 0){
			return new Gson().toJson(map);
		}else{
			return "";
		}
	}
	
	/**
	 * 当obj值存在时向map中插入<key, obj>的键值对，主要用于向查询功能添加参数
	 * 
	 * @param map map对象
	 * @param key key值
	 * @param obj value值
	 * @param fixed 自动加前缀后缀，用于模糊查询
	 */
	public static void putInMapWhenExists(Map<String, Object> map, String key, Object obj, boolean fixed) {
		// obj为空或空字符串时，不向map中添加值
		if (obj == null || obj instanceof String && StringUtil.isEmpty((String) obj)) {
			return;
		}
		// 模糊查询时自动添加【%】
		if (fixed) {
			obj = SysTools.getSqlLikeParam(String.valueOf(obj)); //"%" + String.valueOf(obj) + "%";
		}
		map.put(key, obj);
	}

	/**
	 * 取得登录用户信息
	 * 
	 * @param request HttpServletRequest
	 * @return SysUserBean
	 */
	public static LoginUser getLoginUserInfos(HttpServletRequest request) {
		if (request != null && request.getSession() != null) {
			return (LoginUser) request.getSession().getAttribute(GlobalVal.USER_SESSION);
		} else {
			return null;
		}
	}

	/**
	 * 取得登录用户Bean
	 * 
	 * @param request HttpServletRequest
	 * @return SysUser
	 */
	public static SysUser getLoginUser(HttpServletRequest request) {
		LoginUser loginUser = getLoginUserInfos(request);
		if (loginUser != null) {
			return loginUser.getSysUser();
		}
		return null;
	}

	/**
	 * 取得登录用户ID
	 * 
	 * @param request HttpServletRequest
	 * @return Integer
	 */
	public static Integer getLoginUserId(HttpServletRequest request) {
		SysUser bean = getLoginUser(request);
		if (bean != null) {
			return bean.getUser_id();
		}
		return null;
	}

	/**
	 * 取得登录用户角色
	 * 
	 * @param request HttpServletRequest
	 * @return list
	 */
//	public static List<SysRoleVO> getLoginUserRoles(HttpServletRequest request) {
//		LoginUser loginUser = getLoginUserInfos(request);
//		if (loginUser != null) {
//			return loginUser.getRoleList();
//		}
//		return null;
//	}

	/**
	 * 是否拥有某种角色
	 * 
	 * @param request HttpServletRequest
	 * @param roleId 角色ID
	 * @return boolean
	 */
//	public static boolean hasRole(HttpServletRequest request, Integer roleId) {
//		LoginUser loginUser = getLoginUserInfos(request);
//		if (loginUser != null) {
//			for (SysRoleVO role : loginUser.getRoleList()) {
//				if (role.getRole_id().equals(roleId)) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}

	/**
	 * 取得登录用户角色
	 * 
	 * @param request HttpServletRequest
	 * @param roleId 角色名称
	 * @return SysUserBean
	 */
//	public static boolean hasRole(HttpServletRequest request, String roleName) {
//		LoginUser loginUser = getLoginUserInfos(request);
//		if (loginUser != null) {
//			for (SysRoleVO role : loginUser.getRoleList()) {
//				if (role.getRole_name().equals(roleName)) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}

	/**
	 * 取得登录用户角色
	 * 
	 * @param request HttpServletRequest
	 * @return list
	 */
//	public static List<SysPermissions> getLoginUserPermissions(HttpServletRequest request) {
//		LoginUser loginUser = getLoginUserInfos(request);
//		if (loginUser != null) {
//			return loginUser.getPermissionList();
//		}
//		return null;
//	}

	/**
	 * 判断用户是否拥有某种功能权限
	 * 
	 * @param request HttpServletRequest
	 * @param menu_code 菜单编码
	 * @param menuFunc 功能
	 * @return
	 */
	public static boolean hasRight(HttpServletRequest request, String menuCode, int menuFunc) {
//		LoginUser loginUser = getLoginUserInfos(request);
//		if (loginUser != null) {
//			List<SysPermissions> list = loginUser.getPermissionList();
//			for (SysPermissions menu : list) {
//				if (menu.getMenu_code().equals(menuCode)) {
//					int menu_func = menu.getMenu_func();
//					return (menu_func & menuFunc) != 0;
//				}
//			}
//		}
		return true;
	}

	/**
	 * 检查用户输入内容，将html标签等字符替换掉。
	 * @param bean
	 */
	public static void checkInput(BaseEntity bean) {
		try {
			Field[] fields = bean.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (field.getType() == String.class) {
					field.setAccessible(true);
					String value = (String) field.get(bean);
					if (value != null && (value.indexOf('<') != -1 || value.indexOf('>') != -1)) {
						field.set(bean, escapeString(value));
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("检查用户输入内容时发生错误！");
		}
	}

	/**
	 * 将html标签等字符替换
	 * @param src
	 * @return
	 */
	public static String escapeString(String src) {
		return src.replace("<", "&lt").replace(">", "&gt");
	}

	/**
	 * 取得TOKEN
	 * @param request
	 * @return
	 */
	public static void getToken(HttpServletRequest request) {
		request.getSession().setAttribute(GlobalVal.TOKEN, generateToken());
	}

	/**
	 * 验证TOKEN
	 * @param request
	 * @return
	 */
	public static boolean checkToken(HttpServletRequest request) {
		String token = request.getParameter(GlobalVal.TOKEN);
		String session_token = (String) request.getSession().getAttribute(GlobalVal.TOKEN);
		request.getSession().removeAttribute(GlobalVal.TOKEN);
		if (StringUtils.equals(token, session_token) && token != null) {
			// 备份TOKEN
			request.getSession().setAttribute(GlobalVal.TOKEN_BAK, session_token);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 恢复TOKEN
	 * @param request
	 * @return
	 */
	public static void recoverToken(HttpServletRequest request) {
		String token_bak = (String) request.getSession().getAttribute(GlobalVal.TOKEN_BAK);
		request.getSession().removeAttribute(GlobalVal.TOKEN_BAK);
		request.getSession().setAttribute(GlobalVal.TOKEN, token_bak);
	}

	/**
	 * 生成随机TOKEN
	 * 
	 * @return
	 */
	private static String generateToken() {
		String s = String.valueOf(System.nanoTime());
		return DecriptUtil.MD5(s);
	}
}