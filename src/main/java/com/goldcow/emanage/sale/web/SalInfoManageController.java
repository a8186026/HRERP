package com.goldcow.emanage.sale.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.product.service.IProInfoManageService;
import com.goldcow.emanage.sale.service.ISalInfoManageService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.ProInfoManage;
import com.goldcow.emanage.util.gen.entity.SalInfoManage;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 产品信息
 * 
 * @author chenyuxuan
 * @version v1.0
 * @since 2015-7-13
 */
@Controller
@RequestMapping(value = "/sale/salInfoManage")
public class SalInfoManageController {
	/** 销方信息管理服务 */
	@Autowired
	protected ISalInfoManageService salInfoManageService;
	
	/**
	 * 销方信息管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 销方信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "salInfoManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "sale/salInfoManage/salInfoManageList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	
	/**
	 * 药监局选择界面
	 * 
	 * @param request HttpServletRequest
	 * @param callback 回调函数
	 * @return 药监局选择界面
	 */
	@RequestMapping(value = "comNameChoose", method = RequestMethod.GET)
	public String comNameChoose(HttpServletRequest request, String callback, Model model) {
		if (SysUtil.hasRight(request, "salInfoManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("callback", callback);
			return "sale/salInfoManage/comNameChoose.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 品名库选择界面
	 * 
	 * @param request HttpServletRequest
	 * @param callback 回调函数
	 * @return 药监局选择界面
	 */
	@RequestMapping(value = "medNameChoose", method = RequestMethod.GET)
	public String medNameChoose(HttpServletRequest request, String callback,String content, Model model) {
		if (SysUtil.hasRight(request, "salInfoManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("callback", callback);
			model.addAttribute("content", content);
			return "sale/salInfoManage/medNameChoose.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	
	
	/**
	 * 查询销方信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, SalInfoManage bean) {
		if (SysUtil.hasRight(request, "salInfoManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			System.out.println("######"+bean.getRows());
			return salInfoManageService.list(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 新增销方信息
	 *  
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 新增销方信息页面路径
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newMenu(HttpServletRequest request, Model model,String id) {
		if (SysUtil.hasRight(request, "salInfoManage", GlobalVal.MENU_FUNCTION.VIEW)) {			
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/sale/salInfoManage/new");
			SysUtil.getToken(request);
			return "sale/salInfoManage/salInfoManageEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 新增销方信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 销方信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, SalInfoManage bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "salInfoManage", GlobalVal.MENU_FUNCTION.UPDATE)) {
				// 新增销方信息
				salInfoManageService.add(bean, request);
				result.put("result", "success");
				result.put("message", "操作成功！");

		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		
		return result;
	}
		
		 
	
	/**
	 * 查看销方信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 销方信息ID
	 * @param model Model
	 * @return 查看销方信息页面路径
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "salInfoManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			SalInfoManage salInfoManage = salInfoManageService.getById(id);
			model.addAttribute("salInfoManage", salInfoManage);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/sale/salInfoManage/" + id);
			SysUtil.getToken(request);
			return "sale/salInfoManage/salInfoManageEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}



	/**
	 * 修改销方信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 销方信息ID
	 * @param bean 销方信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, SalInfoManage bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "salInfoManage", GlobalVal.MENU_FUNCTION.UPDATE)) {
			// 修改销方信息
			salInfoManageService.update(bean, request);

			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}

	/**
	 * 删除销方信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的销方信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "salInfoManage", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除销方信息
			salInfoManageService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	

	
	/**
	 * 新增产品经营分类信息
	 *  
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 新增产品经营分类信息页面路径
	 */
	@RequestMapping(value = "category", method = RequestMethod.GET)
	public String newScope(HttpServletRequest request, String callback,Model model,String value) {
		if (SysUtil.hasRight(request, "salInfoManage", GlobalVal.MENU_FUNCTION.VIEW)) {			
			//System.out.println(SysTools.decode(value)); 
			model.addAttribute("callback", callback);
			model.addAttribute("value", SysTools.decode(value));
			return "product/proInfoManage/manageCategoryEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**查找当前数据库表中最大的销方编码值(前面代表产品类别，后3位代表流水号)
	 * @param ticketNumber  代表前面销方类别
	 * @return 返回最大流水号
	 * */
	@RequestMapping(value = "getMaxProductCode", method = RequestMethod.GET)
	@ResponseBody
	public String getMaxProductCode(HttpServletRequest request,String ticketNumber) {
		// 返回下一个ID
		return salInfoManageService.getMaxProductCode(ticketNumber);
	}
}