package com.goldcow.emanage.wholeSale.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.accept.service.IPurAcceptCheckService;
import com.goldcow.emanage.accept.vo.PurSupAndProVO;
import com.goldcow.emanage.transCargo.service.ITransCargoService;
import com.goldcow.emanage.util.gen.entity.PurAcceptCheck;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.JsonUtil;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 
 * @author dht
 *
 */
@Controller
@RequestMapping(value = "wholeSale/order")
public class WholeSaleOrderController{
	
	@Autowired
	private ITransCargoService iTransCargoService;
	@Autowired
	private IPurAcceptCheckService acceptService;
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "store/transcargo", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "wholeSale/order/wholeSaleOrder.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	@RequestMapping(value = "/lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> lists(HttpServletRequest request,
			PurSupAndProVO bean,
			Model model) {
		if (SysUtil.hasRight(request, "purSpecialVarietyCheck", GlobalVal.MENU_FUNCTION.VIEW)) {
		    System.out.println("获取列表");
			return acceptService.specialVariteyList(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	
	/**
	 * 查询所有供方信息
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @param callback 回调方法
	 * @param supContent 查询参数
	 * @return 供方信息页面路径
	 */
	@RequestMapping(value = "viewSupList", method = RequestMethod.GET)
	public String viewSupList(HttpServletRequest request, Model model, String callback, String supContent) {
		if (SysUtil.hasRight(request, "purSpecialVarietyCheck", GlobalVal.MENU_FUNCTION.VIEW)) {
			System.out.println("查询所有供方信息");
			model.addAttribute("callback", callback);
			model.addAttribute("supContent", supContent);
			return "accept/specialVarietyCheck/purOrderViewSupList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	/**
	 * 查询所有产品信息
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @param callback 回调方法
	 * @param proContent 查询参数
	 * @return 供方信息页面路径
	 */
	@RequestMapping(value = "viewProList", method = RequestMethod.GET)
	public String viewProList(HttpServletRequest request, Model model, String callback, String proContent) {
		if (SysUtil.hasRight(request, "purSpecialVarietyCheck", GlobalVal.MENU_FUNCTION.VIEW)) {
			System.out.println("查询所有产品信息");
			model.addAttribute("callback", callback);
			model.addAttribute("proContent", proContent);
			return "accept/specialVarietyCheck/purOrderViewProList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	/**
	 *提交特殊药品的审核信息
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 */
	@RequestMapping(value = "checkspecialvariety", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public int checkSpecialVariety(HttpServletRequest request, Model model,@RequestParam("dataArr") String dataArr){
		System.out.println(dataArr);
		List<PurAcceptCheck> list=JsonUtil.jsonArrayToList(dataArr, PurAcceptCheck.class);
		for (PurAcceptCheck purAcceptCheck : list) {
			System.out.println(purAcceptCheck.toString());
		}
		System.out.println("特殊药品审核");
		if (SysUtil.hasRight(request, "purSpecialVarietyCheck", GlobalVal.MENU_FUNCTION.VIEW)) {
			return acceptService.specialVarietyCheck(list);
		}else{
		    return 0;
		}
	}
}