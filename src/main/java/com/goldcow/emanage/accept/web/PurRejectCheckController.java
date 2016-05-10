package com.goldcow.emanage.accept.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.accept.service.IPurAcceptCheckService;
import com.goldcow.emanage.util.gen.entity.PurAcceptCheck;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 收货拒收处理
 * 
 * @author zhanxiaotong
 * @since15-11-27
 * @version v1.0
 */
@Controller
@RequestMapping(value = "/accept/reject")
public class PurRejectCheckController {
	/** 订单收货确认信息管理服务 */
	@Autowired
	protected IPurAcceptCheckService purRejectCheckService;
	
	/**
	 * 订单收货确认信息管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 订单收货确认信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "purAcceptCheck", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "accept/rejectCheck/purRejectCheckList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	
	/**
	 * 拒收审核通过
	 * 
	 * @param request HttpServletRequest
	 * @param data 拒收审核信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "checkResult", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> passReject(HttpServletRequest request, String data,String flag) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "purAcceptCheck", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if(flag.equals("pass"))
				purRejectCheckService.passReject(data,request);
			else
				purRejectCheckService.failReject(data,request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}
}