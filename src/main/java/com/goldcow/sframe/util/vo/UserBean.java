package com.goldcow.sframe.util.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录用户信息表
 * 
 * @author gx
 * 
 */
public class UserBean implements Serializable {

	private static final long serialVersionUID = -6289280101558471306L;

	private Integer userId;
	private String userCode;
	private String userPasswd;
	private String realname;
	private Integer deptId;
	private String deptSimpleName;
	private Map<String, String> funcMap = new HashMap<String, String>();

	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserPasswd() {
		return userPasswd;
	}

	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}

	public void putFuncName(String funcName) {
		funcMap.put(funcName, "1");
	}

	public boolean hasFuncName(String funcName) {
		if (funcMap.get(funcName) == null) {
			return false;
		}
		return true;
	}

	public void cleanFuncNameMap() {
		funcMap = new HashMap<String, String>();
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getDeptSimpleName() {
		return deptSimpleName;
	}

	public void setDeptSimpleName(String deptSimpleName) {
		this.deptSimpleName = deptSimpleName;
	}

}
