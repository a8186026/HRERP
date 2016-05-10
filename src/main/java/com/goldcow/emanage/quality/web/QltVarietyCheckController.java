package com.goldcow.emanage.quality.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.quality.service.IQltVarietyCheckService;
import com.goldcow.emanage.util.gen.entity.QltVarietyCheck;
import com.goldcow.emanage.util.gen.entity.valueObject.quality.QltVarietyCheckVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 重点养护品种信息实体类
 * 
 * @author gaoxiang
 * @since 2015-11-13
 */

@Controller
@RequestMapping(value = "/quality/varietyCheck")
public class QltVarietyCheckController {
	/** 重点养护品种信息管理服务 */
	@Autowired
	protected IQltVarietyCheckService qltVarietyCheckService;
	
	/**
	 * 重点养护品种信息管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 重点养护品种信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "qltVarietyCheckVO", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "quality/varietyCheck/qltVarietyCheckList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询重点养护品种信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, QltVarietyCheckVO bean) {
		if (SysUtil.hasRight(request, "qltVarietyCheckVO", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return qltVarietyCheckService.list(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 新增重点养护品种信息
	 *  
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 新增重点养护品种信息页面路径
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newMenu(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "qltVarietyCheck", GlobalVal.MENU_FUNCTION.VIEW)) {			
			QltVarietyCheck qltVarietyCheck = new QltVarietyCheck(); 
			 
			model.addAttribute("qltVarietyCheck", qltVarietyCheck);
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/quality/varietyCheck/new");
			SysUtil.getToken(request);
			return "quality/varietyCheck/qltVarietyCheckEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 新增重点养护品种信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 重点养护品种信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, QltVarietyCheck bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "qltVarietyCheck", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {          
				// 新增重点养护品种信息
				qltVarietyCheckService.add(bean, request);
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
	 * 查看重点养护品种信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 重点养护品种信息ID
	 * @param model Model
	 * @return 查看重点养护品种信息页面路径
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "qltVarietyCheckVO", GlobalVal.MENU_FUNCTION.VIEW)) {
			QltVarietyCheckVO qltVarietyCheckVO = qltVarietyCheckService.getById(id);
			model.addAttribute("qltVarietyCheckVO", qltVarietyCheckVO);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/quality/varietyCheck/" + id);
			SysUtil.getToken(request);
			return "quality/varietyCheck/qltVarietyCheckEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}



	/**
	 * 修改重点养护品种信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 重点养护品种信息ID
	 * @param bean 重点养护品种信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, QltVarietyCheckVO bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "qltVarietyCheckVO", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 修改重点养护品种信息
				qltVarietyCheckService.update(bean, request);

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
	 * 删除重点养护品种信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的重点养护品种信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "qltVarietyCheck", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除重点养护品种信息
			qltVarietyCheckService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
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
		if (SysUtil.hasRight(request, "qltVarietyCheckVO", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("callback", callback);
			model.addAttribute("proContent", proContent);
			return "quality/varietyCheck/qltVarietyCheckProList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
}