package com.goldcow.emanage.financial.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.financial.service.IWarehousingInvoiceService;
import com.goldcow.emanage.util.gen.entity.DeptPlanManage;
import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.emanage.util.gen.entity.valueObject.financial.WarehousingInvoiceVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 入库购进发票
 * 
 * @author zhanxiaotong
 * @version v1.0
 * @since 2015-12-15
 */
@Controller
@RequestMapping(value = "/financial/warehousingInvoice")
public class WarehousingInvoiceController {
	/** 调货计划管理 */
	@Autowired
	protected IWarehousingInvoiceService warehousingInvoiceService;
	
	
	
	/**
	 * 调货计划管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 调货计划页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "deptPlanManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "/financial/warehousingInvoice/warehousingInvoiceList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询门店入库验收初始信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "stockCheckedlists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, WarehousingInvoiceVO bean) {
		if (SysUtil.hasRight(request, "warehousingInvoiceVO", GlobalVal.MENU_FUNCTION.VIEW)) {	
			
			return warehousingInvoiceService.stockCheckedlists(request,bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 查询门店返货信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "RefundInfoList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> RefundInfoList(HttpServletRequest request, WarehousingInvoiceVO bean) {
		if (SysUtil.hasRight(request, "warehousingInvoiceVO", GlobalVal.MENU_FUNCTION.VIEW)) {	
			
			return warehousingInvoiceService.RefundInfoList(request,bean);
		} else {
			return Maps.newHashMap();
		}
	}

	
	/**
	 * 获取最大票号
	 * 
	 * @param request HttpServletRequest
	 * @return 操作结果
	 */
	@RequestMapping(value = "getMaxInvoiceTicket", method = RequestMethod.GET)
	@ResponseBody
	public String getMaxInvoiceTicket(HttpServletRequest request) {
		String maxTicket = warehousingInvoiceService.getMaxInvoiceTicket();
		return maxTicket;
	}
	
	/**
	 * 审核调货计划页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 调货计划ID
	 * @param model Model
	 * @return 查看调货计划页面路径
	 */
	@RequestMapping(value = "confirm", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> confirm(HttpServletRequest request, String data) {
		if(data == null || data ==""){
			return Maps.newHashMap();
		}
		if (SysUtil.hasRight(request, "warehousingInvoiceVO", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return warehousingInvoiceService.confirmList(request,data);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 审核调货计划页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 调货计划ID
	 * @param model Model
	 * @return 查看调货计划页面路径
	 */
	@RequestMapping(value = "saveInvoice", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> saveInvoice(HttpServletRequest request, String invalid, 
									String inrefund, String instock, String info) {
		Map<String, Object> result = Maps.newHashMap();
		if(info == null || info ==""){
			result.put("result", "failure");
			result.put("message", "没有权限!");
			return result;
		}
		if (SysUtil.hasRight(request, "warehousingInvoiceVO", GlobalVal.MENU_FUNCTION.VIEW)) {	
			warehousingInvoiceService.saveInvoice(request,invalid,inrefund,instock, info);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} 
		else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
}