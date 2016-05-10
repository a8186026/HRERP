package com.goldcow.emanage.search.web;

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

import com.goldcow.emanage.basInfo.service.IBasDepartmentInfoService;
import com.goldcow.emanage.dept.persist.DeptPlanManageDao;
import com.goldcow.emanage.dept.service.IDeptPlanManageService;
import com.goldcow.emanage.member.service.IMemCardManageService;
import com.goldcow.emanage.product.persist.ProInfoManageDao;
import com.goldcow.emanage.product.service.IProInfoManageService;
import com.goldcow.emanage.system.service.ISysUserService;
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
 * 会员卡查询
 * 
 * @author wubin
 * @version v1.0
 * @since 2016-1-8
 */
@Controller
@RequestMapping(value = "/search/memCard")
public class MemCardSearchController {
	/** 会员卡资料查询 */
	@Autowired
	protected IMemCardManageService memCardManageService;
	
	/**
	 * 会员卡报表页面
	 * 
	 * @param request HttpServletRequest
	 * @return 会员卡报表页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "memCardReport", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "/search/memCard/memCardList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询会员卡报表
	 * 
	 * @param request HttpServletRequest
	 * @param number 会员卡卡号
	 * @return 操作结果
	 */
	@RequestMapping(value = "getMemCardById", method = RequestMethod.GET)
	@ResponseBody
	public MemCardManage getMemCardById(HttpServletRequest request, String number) {
		if (SysUtil.hasRight(request, "memCardReport", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return memCardManageService.getMemCardByNumber(number);
		} else {
			return null;
		}
	}
	/**
	 * 查询会员卡购买记录
	 * 
	 * @param request HttpServletRequest
	 * @param number 会员卡号
	 * @return 操作结果
	 */
	@RequestMapping(value = "getPurchaseByNum", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getPurchaseByNum(HttpServletRequest request,String memCard_id,Date start_time,Date end_time,MemCardManage bean,Integer type) {
		if (SysUtil.hasRight(request, "memCardReport", GlobalVal.MENU_FUNCTION.VIEW)) {
			return memCardManageService.getPurchasesByNum(memCard_id, start_time, end_time, bean,type);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 查询所有产品信息
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @param callback 回调方法
	 * @return 供方信息页面路径
	 */
	@RequestMapping(value = "viewMemCardList", method = RequestMethod.GET)
	public String viewProList(HttpServletRequest request, Model model, String callback) {
		if (SysUtil.hasRight(request, "memCardReport", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("callback", callback);
			return "/search/memCard/viewMemCardList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	
	
}