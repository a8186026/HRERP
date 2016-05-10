package com.goldcow.emanage.promotion.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.promotion.service.IPmnFulfillGiftService;
import com.goldcow.emanage.util.gen.entity.PmnFulfillGift;
import com.goldcow.emanage.util.gen.entity.PmnGiftSale;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 满额赠信息控制页面
 * 
 * @author cyx
 * @version v1.0
 * @since 2015-10-27
 */
@Controller
@RequestMapping(value = "/promotion/fulfillgift")
public class PmnFulfillGiftController {
	/** 满额赠信息服务 */
	@Autowired
	protected IPmnFulfillGiftService pmnFulfillGiftService;

	/**
	 * 满额赠信息页面
	 * 
	 * @param request HttpServletRequest
	 * @return 满额赠信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "PmnFulfillGift", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "promotion/pmnFulfillGift/pmnFulfillGiftList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询页面
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 查询结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, PmnFulfillGift bean) {
		if (SysUtil.hasRight(request, "PmnFulfillGift", GlobalVal.MENU_FUNCTION.VIEW)) {
			return pmnFulfillGiftService.list(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 新增满额赠信息
	 *  
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 新增满额赠信息页面路径
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newMenu(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "PmnFulfillGift", GlobalVal.MENU_FUNCTION.VIEW)) {	
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/promotion/fulfillgift/new");
			SysUtil.getToken(request);
			return "promotion/pmnFulfillGift/pmnFulfillGiftEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 新增满额赠产品信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 满额赠产品信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, PmnFulfillGift bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "PmnFulfillGift", GlobalVal.MENU_FUNCTION.UPDATE)) {
				// 新增满额赠产品信息
				pmnFulfillGiftService.add(bean, request);
				result.put("result", "success");
				result.put("message", "操作成功！");

		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		
		return result;
	}	
	
	/**
	 * 查看满额赠信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 满额赠信息ID
	 * @param model Model
	 * @return 查看买赠信息页面路径
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "PmnFulfillGift", GlobalVal.MENU_FUNCTION.VIEW)) {
			PmnFulfillGift pmnFulfillGift = pmnFulfillGiftService.getById(id);
			model.addAttribute("pmnFulfillGift", pmnFulfillGift);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/promotion/fulfillgift/" + id);
			SysUtil.getToken(request);
			return "promotion/pmnFulfillGift/pmnFulfillGiftEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 修改满额赠信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 满额赠信息ID
	 * @param bean 满额赠信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, PmnFulfillGift bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "PmnFulfillGift", GlobalVal.MENU_FUNCTION.UPDATE)) {
			// 修改买赠信息
			pmnFulfillGiftService.update(bean, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}
	
	/**
	 * 删除满额赠产品信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的满额赠产品信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "PmnFulfillGift", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除满额赠信息
			pmnFulfillGiftService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
	
	/**
	 * 新增满额赠信息
	 *  
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 新增满额赠信息页面路径
	 */
	@RequestMapping(value = "listPro", method = RequestMethod.GET)
	public String listPro(HttpServletRequest request, Model model,String full_gift_joinProduct) {
		if (SysUtil.hasRight(request, "PmnFulfillGift", GlobalVal.MENU_FUNCTION.VIEW)) {	
			//model.addAttribute("method", "POST");
			model.addAttribute("product_ids", full_gift_joinProduct);
			SysUtil.getToken(request);
			return "promotion/pmnFulfillGift/pmnFulfillProductChoose.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
}