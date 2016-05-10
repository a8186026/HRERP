package com.goldcow.sframe.util.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.emanage.util.spring.SpringUtil;
import com.goldcow.sframe.util.GlobalVal;



public class LoginFilter implements Filter {
	private static Logger log = LoggerFactory.getLogger(LoginFilter.class);
	
	

	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		// 判断是否登录，访问系统.do必须登录后才能访问
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		/*
		 * YaoTaihang 取消对返回文件上传后ID的过滤
		 * */
		String url = request.getRequestURI();
		
		
		HttpSession session = request.getSession();
		//开放文件上传的接口，不拦截文件上传
		/**
		 * 直接取得内存中的bean
		 * 将实体bean赋值到接口中
		 */
	   /* service=(IPurSpecialVarietyCheckService) SpringUtil.getObject("PurSpecialVarietyCheckServiceImpl");*/
		if(url.equals("/HRERP/fileServer/fileUpload"))
		{
			chain.doFilter(req, resp);
		}else if (session != null && session.getAttribute(GlobalVal.USER_SESSION) != null) { // 有登录信息，不拦截
			chain.doFilter(req, resp);
		} else if (url.startsWith("/HRERP/resources/")) { // 图片脚本等资源，不拦截
			chain.doFilter(req, resp);
		} else if (url.startsWith("/HRERP/webSocketServer")) { // webSocket测试，暂时不拦截
			chain.doFilter(req, resp);
		} else if (url.equals("/HRERP/login.html") || url.equals("/HRERP/login") || url.equals("/HRERP") || url.equals("/HRERP/") ) { // 静态资源，不拦截
			chain.doFilter(req, resp);
		} else {
			log.debug("未登录，自动跳转到登录页，原访问路径：" + url);
			// 转到登录页
			PrintWriter writer = response.getWriter();
			writer.write("<script type=\"text/javascript\">top.location.href=\"/HRERP/login.html\"</script>");
			writer.flush();
		}
		
//		chain.doFilter(req, resp);
	}
	
	@Override
	public void destroy() {
	}
}
