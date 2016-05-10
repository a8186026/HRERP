package com.goldcow.emanage.giftCard.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.basInfo.service.IBasMedicineInfoService;
import com.goldcow.emanage.giftCard.service.IGiftCardService;
import com.goldcow.emanage.util.gen.entity.BasMedicineInfo;
import com.goldcow.emanage.util.gen.entity.GiftCardManage;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 代金卡
 * 
 * @author chenyuxuan
 * @version v1.0
 * @since 2015-12-11
 */
@Controller
@RequestMapping(value = "/giftCard/giftCardManage")
public class GiftCardController {
	/** 代金卡管理服务 */

	@Autowired
	protected IGiftCardService giftCardService;

	/**
	 * 代金卡信息管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 药品管理页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "giftCard", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "/giftCard/giftCardManage/giftCardManageList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询代金卡信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> list(HttpServletRequest request, GiftCardManage bean) {
		if (SysUtil.hasRight(request, "giftCard", GlobalVal.MENU_FUNCTION.VIEW)) {
			return giftCardService.list(bean);
		} else {
			return Maps.newHashMap(); 
		}
	}

	/**
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 新增代金卡页面路径
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newMenu(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "giftCard", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/giftCard/giftCardManage/new");
			SysUtil.getToken(request);
			return "/giftCard/giftCardManage/giftCardManageEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查看代金卡信息页面
	 *  
	 * @param request HttpServletRequest
	 * @return 查看代金卡信息页面路径
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "giftCard", GlobalVal.MENU_FUNCTION.VIEW)) {
			GiftCardManage giftCardManage = giftCardService.getById(id);
			model.addAttribute("giftCardManage", giftCardManage);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/giftCard/giftCardManage/" + id);
			SysUtil.getToken(request);
			return "/giftCard/giftCardManage/giftCardManageEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 新增代金卡
	 * 
	 * @param request HttpServletRequest
	 * @param bean 代金卡信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, GiftCardManage bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "giftCard", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				
				// 新增代金卡
				giftCardService.add(bean, request);
				result.put("result", "success");
				result.put("message", "操作成功！");
			} else {
				result.put("result", "failure");
				result.put("message", "非法的请求！");
			}
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}

	/**
	 * 修改代金卡信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 药品信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, GiftCardManage bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "giftCard", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 修改代金卡信息
				giftCardService.update(bean, request);

				result.put("result", "success");
				result.put("message", "操作成功！");
			} else {
				result.put("result", "failure");
				result.put("message", "非法的请求！");
			}
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}

	/**
	 * 删除代金卡信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的代金卡信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "giftCard", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除代金卡信息
			giftCardService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
	/**
	 * 查询当前消费金额能满足的代金卡规则
	 * 
	 * @param department_id 部门Id
	 * @param Amount 消费金额
	 * @return 
	 */
	@RequestMapping(value = "getOffsetAmount", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getOffsetAmount(HttpServletRequest request,Integer department_id, Double Amount) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "giftCard", GlobalVal.MENU_FUNCTION.VIEW)) {
			Double gift_amount= giftCardService.getOffsetAmount(department_id, Amount);
			result.put("result", "success");
			result.put("message", "操作成功！");
			result.put("gift_amount", gift_amount);
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}

}