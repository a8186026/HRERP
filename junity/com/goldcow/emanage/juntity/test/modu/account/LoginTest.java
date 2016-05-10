/**
 * 测试各种费率的查询方法
 */
package com.goldcow.emanage.juntity.test.modu.account;

import static org.junit.Assert.*;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.goldcow.emanage.juntity.com.JUnitTestBase;
import com.goldcow.emanage.system.service.ISysUserService;
import com.goldcow.emanage.util.gen.bean.LoginUser;
import com.goldcow.emanage.util.gen.entity.SysUser;

public class LoginTest extends JUnitTestBase{
	
	@Resource
	public ISysUserService userService;
	
	@Test
	public void testLogin(){
		SysUser sysUser = new SysUser();
		sysUser.setUser_name("admin");
		sysUser.setPassword("admin");
		LoginUser loginUser = new LoginUser();
		loginUser.setSysUser(sysUser);
		Map<String, Object> result = userService.login(loginUser);
		assertEquals((String) result.get("result"), "success");
	}
	
	
}
