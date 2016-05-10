package com.goldcow.emanage.modi.service;

import java.util.Map;

/*
 * @version 1.0
 */

public interface IModiService {

	/**
	 * 登录
	 * 
	 * @param user_name 查询条数
	 * @param password 密码
	 * @return 登录结果
	 */
	public Map<String, String> login(String user_name, String password);

}