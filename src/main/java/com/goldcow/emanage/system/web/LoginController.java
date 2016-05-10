package com.goldcow.emanage.system.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.system.service.ISysGroupService;
import com.goldcow.emanage.system.service.ISysUserService;
import com.goldcow.emanage.util.gen.bean.LoginUser;
import com.goldcow.emanage.util.gen.entity.SysGroup;
import com.goldcow.emanage.util.gen.entity.SysMenu;
import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;

@Controller
public class LoginController {
	@Autowired
	protected ISysUserService userService;
	@Autowired
	protected ISysGroupService sysGroupService;

	/**
	 * 正常登录
	 * 
	 * @param user_name 登录名
	 * @param password 密码
	 * @param request HttpServletRequest
	 * @return 操作结果
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(String user_name, String password, HttpServletRequest request) {
		SysUser sysUser = new SysUser();
		sysUser.setUser_name(user_name);
		sysUser.setPassword(password);
		LoginUser loginUser = new LoginUser();
		loginUser.setSysUser(sysUser);
		Map<String, Object> result = userService.login(loginUser);
		if ("success".equals((String) result.get("result"))) {
			// 将登录用户相关信息的VO保存到session中
			HttpSession session = request.getSession();
			session.setAttribute(GlobalVal.USER_SESSION, loginUser);
		}
		return result;
	}
	
	/**
	 * 跳转到首面
	 * 
	 * @param request HttpServletRequest
	 * @return 登录用户信息
	 */
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		return "index.html";
	}
	
	/**
	 * 跳转到首面
	 * 
	 * @param request HttpServletRequest
	 * @return 登录用户信息
	 */
	@RequestMapping(value = "/homePage")
	public String homePage(HttpServletRequest request) {
		return "home/homePage.html";
	}
	
	/**
	 * 获取用户菜单
	 * 
	 * @param request HttpServletRequest
	 * @return 用户菜单
	 */
	@RequestMapping(value = "/userMenus", method = RequestMethod.GET)
	@ResponseBody
	public List<SysMenu> getUserMenus(HttpServletRequest request) {
		LoginUser loginUser = SysUtil.getLoginUserInfos(request);
		if (loginUser == null) {
			return null;
		}
		return loginUser.getMenuList();
	}

	/**
	 * 注销
	 * 
	 * @param request HttpServletRequest
	 * @return 登录页面
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute(GlobalVal.USER_SESSION);
		return "redirect:/login.html";
	}

	/**
	 * 获取登录用户信息
	 * 
	 * @param request HttpServletRequest
	 * @return 登录用户信息
	 */
	@RequestMapping(value = "/userinfo")
	@ResponseBody
	public SysUser getUser(HttpServletRequest request) {
		return SysUtil.getLoginUser(request);
	}
	
	/**
	 * 获取登录用户所在用户组
	 * 
	 * @param request HttpServletRequest
	 * @return 登录用户组ID
	 * @author RiverYao
	 * @since 2015-06-08
	 */
	@RequestMapping(value = "/usergroup")
	@ResponseBody
	public SysGroup getUserGroup(HttpServletRequest request) {
		SysUser loginUser = SysUtil.getLoginUser(request);
		return sysGroupService.getUserGroup(loginUser.getUser_id());
	}
	
}