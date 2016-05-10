package com.goldcow.emanage.basInfo.web;

import java.util.ArrayList;
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
import com.goldcow.emanage.util.gen.entity.BasDepartmentInfo;
import com.goldcow.emanage.util.gen.entity.BasFactoryInfo;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

/**
 * 部门档案信息
 * 
 * @author wangqi
 * @version v1.0
 * @since 2015-6-3
 */
@Controller
@RequestMapping(value = "/basInfo/basDepartmentInfo")
public class BasDepartmentInfoController {
	/** 部门档案信息信息管理服务 */
	@Autowired
	protected IBasDepartmentInfoService departmentInfoService;
	
	/**
	 * 部门档案信息管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 部门档案信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "basDepartmentInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "basInfo/basDepartmentInfo/basDepartmentInfoList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询部门档案信息信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, BasDepartmentInfo bean) {
		if (SysUtil.hasRight(request, "basDepartmentInfo", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return departmentInfoService.list(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	
	/**
	 * 返回数组的查询方法
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 验证结果
	 * 
	 * @author RiverYao
	 * @since 2015-06-10
	 */
	@RequestMapping(value = "getList", method = RequestMethod.GET)
	@ResponseBody
	public List<BasDepartmentInfo> getList(HttpServletRequest request, BasDepartmentInfo bean) {
		if (SysUtil.hasRight(request, "basDepartmentInfo", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return departmentInfoService.getList(bean);
		} else {
			return Lists.newArrayList();
		}
	}
	/**
	 * 返回所有未参加某活动的部门
	 * 
	 * @param request HttpServletRequest
	 * @param depts 已参加的部门
	 * @return 结果
	 * 
	 * @author wubin
	 * @since 2015-10-27
	 */
	@RequestMapping(value = "getListByDepts", method = RequestMethod.GET)
	@ResponseBody
	public List<BasDepartmentInfo> getListByDepts(HttpServletRequest request, String depts) {
		if (SysUtil.hasRight(request, "basDepartmentInfo", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return departmentInfoService.getListByDepts(depts);
		} else {
			return Lists.newArrayList();
		}
	}
	/**
	 * 返回所有参加某活动的部门
	 * 
	 * @param mem_day_id 会员日ID
	 * @return 结果
	 * 
	 * @author wubin
	 * @since 2015-10-28
	 */
	@RequestMapping(value = "getListByPromotion", method = RequestMethod.GET)
	@ResponseBody
	public List<BasDepartmentInfo> getListByPromotion(HttpServletRequest request, Integer mem_day_id) {
		if (SysUtil.hasRight(request, "basDepartmentInfo", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return departmentInfoService.getListByPromotion(mem_day_id);
		} else {
			return Lists.newArrayList();
		}
	}
	
	/**
	 * 新增部门档案信息
	 *  
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 新增部门档案信息页面路径
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newMenu(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "basDepartmentInfo", GlobalVal.MENU_FUNCTION.VIEW)) {			
			BasDepartmentInfo basDepartmentInfo = new BasDepartmentInfo(); 
			model.addAttribute("basDepartmentInfo", basDepartmentInfo);
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/basInfo/basDepartmentInfo/new");
			SysUtil.getToken(request);
			return "basInfo/basDepartmentInfo/basDepartmentInfoEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 新增部门档案信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 部门档案信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, BasDepartmentInfo bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "basDepartmentInfo", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 新增部门档案信息
				departmentInfoService.add(bean, request);
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
	 * 查看部门档案信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 部门档案信息ID
	 * @param model Model
	 * @return 查看部门档案信息页面路径
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "basDepartmentInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			BasDepartmentInfo basDepartmentInfo = departmentInfoService.getById(id);
			model.addAttribute("basDepartmentInfo", basDepartmentInfo);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/basInfo/basDepartmentInfo/" + id);
			SysUtil.getToken(request);
			return "basInfo/basDepartmentInfo/basDepartmentInfoEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}



	/**
	 * 修改部门档案信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 部门档案信息ID
	 * @param bean 部门档案信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, BasDepartmentInfo bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "basDepartmentInfo", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 修改厂家档案信息
				departmentInfoService.update(bean, request);

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
	 * 删除部门档案信息信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的部门档案信息信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "basDepartmentInfo", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除厂家档案信息信息
			departmentInfoService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
	
	/**
	 * 验证部门编号
	 * 
	 * @param request HttpServletRequest
	 * @param department_number 被删除的部门档案信息信息ID
	 * @return 验证结果
	 * 
	 * @author RiverYao
	 * @since 2015-06-10
	 */
	@RequestMapping(value = "checkDepartmentNumber", method = RequestMethod.GET)
	@ResponseBody
	public Boolean checkDepartmentNumber(HttpServletRequest request, String department_number) {
		return departmentInfoService.checkDepartmentNumber(department_number);
	}
}