package com.goldcow.emanage.util.spring;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @ClassName: SpringContextLoaderListener
 * @deprecated: 自定义spring加载器，把ApplicationContext装载到SpringUtil
 * @author 
 * @company 
 * @date 2014-5-15
 * @version V1.0
 */

public class SpringContextLoaderListener extends ContextLoaderListener {

	public void contextInitialized(ServletContextEvent event) {

		// 初始化父类 ContextLoaderListener
		super.contextInitialized(event);

		ServletContext context = event.getServletContext();

		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(context);

		// 装载 SpringUtil 中的 ApplicationContext
		new SpringUtil().setApplicationContext(ctx);
	}
}