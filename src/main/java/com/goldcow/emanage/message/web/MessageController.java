package com.goldcow.emanage.message.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.goldcow.emanage.basInfo.service.IBasDepartmentInfoService;
import com.goldcow.emanage.dept.persist.DeptPlanManageDao;
import com.goldcow.emanage.dept.service.IDeptPlanManageService;
import com.goldcow.emanage.member.service.IMemCardManageService;
import com.goldcow.emanage.product.persist.ProInfoManageDao;
import com.goldcow.emanage.product.service.IProInfoManageService;
import com.goldcow.emanage.system.service.ISysUserService;
import com.goldcow.emanage.util.gen.bean.SystemWebSocketHandler;
import com.goldcow.emanage.util.gen.entity.BasDepartmentInfo;
import com.goldcow.emanage.util.gen.entity.DeptPlanManage;
import com.goldcow.emanage.util.gen.entity.MemCardManage;
import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.emanage.util.gen.entity.valueObject.dept.ProStockInfoVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 消息处理
 * 
 * @author wubin
 * @version v1.0
 * @since 2016-1-18
 */
@Controller
@RequestMapping(value = "/message/request")
public class MessageController {
	/** 会员卡资料查询 */
	@Autowired
	protected IMemCardManageService memCardManageService;
	
	/**
	 * 登陆后信息查询
	 * 
	 * @param request HttpServletRequest
	 * @return 会员卡报表页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "memCardReport", GlobalVal.MENU_FUNCTION.VIEW)) {
			SystemWebSocketHandler swsh = new SystemWebSocketHandler();
			
			SysUser sysUser = SysUtil.getLoginUser(request);
			
			swsh.sendMessageToUser(sysUser.getUser_id().toString(), new TextMessage("3"));
			
			
			return "success";
			
		} else {
			return "failure";
		}
	}


	
	
	
}