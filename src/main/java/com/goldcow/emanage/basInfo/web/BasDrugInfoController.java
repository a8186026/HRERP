package com.goldcow.emanage.basInfo.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.basInfo.service.IBasDrugInfoService;
import com.goldcow.emanage.util.gen.entity.BasAccount;
import com.goldcow.emanage.util.gen.entity.BasDrugInfo;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.goldcow.sframe.util.tools.CreateMapper;
import com.google.common.collect.Maps;

/**
 * 药监局药品信息
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-7-7
 */
@Controller
@RequestMapping(value = "/basInfo/basDrugInfo")
public class BasDrugInfoController {
	/** 药监局药品信息管理服务 */
	@Autowired
	protected IBasDrugInfoService drugInfoService;
	
	/**
	 * 药监局药品信息管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 药监局药品信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "basDrugInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			
			return "basInfo/basDrugInfo/basDrugInfoList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询药监局药品信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, BasDrugInfo bean) {
		if (SysUtil.hasRight(request, "basDrugInfo", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return drugInfoService.list(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 新增药监局药品信息
	 *  
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 新增药监局药品信息页面路径
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newMenu(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "basDrugInfo", GlobalVal.MENU_FUNCTION.VIEW)) {			
			BasDrugInfo basDrugInfo = new BasDrugInfo(); 
			model.addAttribute("basDrugInfo", basDrugInfo);
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/basInfo/basDrugInfo/new");
			return "basInfo/basDrugInfo/basDrugInfoEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 新增药监局药品信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 药监局药品信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, BasDrugInfo bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "basDrugInfo", GlobalVal.MENU_FUNCTION.UPDATE)) {
				drugInfoService.add(bean, request);
				result.put("result", "success");
				result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		
		return result;
	}
		
		 
	
	/**
	 * 查看药监局药品信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 药监局药品信息ID
	 * @param model Model
	 * @return 查看药监局药品信息页面路径
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "basDrugInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			BasDrugInfo basDrugInfo = drugInfoService.getById(id);
			model.addAttribute("basDrugInfo", basDrugInfo);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/basInfo/basDrugInfo/" + id);
			return "basInfo/basDrugInfo/basDrugInfoEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}



	/**
	 * 修改药监局药品信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 药监局药品信息ID
	 * @param bean 药监局药品信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, BasDrugInfo bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "basDrugInfo", GlobalVal.MENU_FUNCTION.UPDATE)) {
				// 修改药监局药品信息
				drugInfoService.update(bean, request);
				result.put("result", "success");
				result.put("message", "操作成功！");
			} 
		else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}

	/**
	 * 删除药监局药品信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的药监局药品信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "basDrugInfo", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除药监局药品信息
			drugInfoService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
}