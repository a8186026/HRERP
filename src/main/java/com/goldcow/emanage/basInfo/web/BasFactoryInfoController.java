package com.goldcow.emanage.basInfo.web;

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
 

import com.goldcow.emanage.basInfo.service.IBasFactoryInfoService;
import com.goldcow.emanage.util.gen.entity.BasFactoryInfo;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil; 
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 厂家档案信息
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-6-2
 */
@Controller
@RequestMapping(value = "/basInfo/basFactoryInfo")
public class BasFactoryInfoController {
	/** 厂家档案信息管理服务 */
	@Autowired
	protected IBasFactoryInfoService factoryInfoService;
	
	/**
	 * 厂家档案信息管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 厂家档案信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "basFactoryInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "basInfo/basFactoryInfo/basFactoryInfoList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询厂家档案信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, BasFactoryInfo bean) {
		if (SysUtil.hasRight(request, "basFactoryInfo", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return factoryInfoService.list(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 新增厂家档案信息
	 *  
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 新增厂家档案信息页面路径
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newMenu(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "basFactoryInfo", GlobalVal.MENU_FUNCTION.VIEW)) {			
			BasFactoryInfo basFactoryInfo = new BasFactoryInfo(); 
			/*编码改为手添，取代自动生成 RiverYao 2015-06-10
			String factory_code = factoryInfoService.getMaxMedicineCode();
			basFactoryInfo.setFactory_code(factory_code);*/
			model.addAttribute("basFactoryInfo", basFactoryInfo);
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/basInfo/basFactoryInfo/new");
			SysUtil.getToken(request);
			return "basInfo/basFactoryInfo/basFactoryInfoEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 新增厂家档案信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 厂家档案信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, BasFactoryInfo bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "basFactoryInfo", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {          
				// 新增厂家档案信息
				factoryInfoService.add(bean, request);
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
	 * 查看厂家档案信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 厂家档案信息ID
	 * @param model Model
	 * @return 查看厂家档案信息页面路径
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "basFactoryInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			BasFactoryInfo basFactoryInfo = factoryInfoService.getById(id);
			model.addAttribute("basFactoryInfo", basFactoryInfo);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/basInfo/basFactoryInfo/" + id);
			SysUtil.getToken(request);
			return "basInfo/basFactoryInfo/basFactoryInfoEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}



	/**
	 * 修改厂家档案信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 厂家档案信息ID
	 * @param bean 厂家档案信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, BasFactoryInfo bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "basFactoryInfo", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 修改厂家档案信息
				factoryInfoService.update(bean, request);

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
	 * 删除厂家档案信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的厂家档案信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "basFactoryInfo", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除厂家档案信息
			factoryInfoService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
	/**
	 * 检验添加过程中的厂家编号是否是重复的
	 * 
	 * @param request HttpServletRequest
	 * @param factory_code 厂家编码
	 * 
	 * @author RiverYao
	 * @since 2015-06-10
	 * */
	@RequestMapping(value = "checkFactoryCode", method = RequestMethod.GET)
	@ResponseBody
	public Boolean checkFactoryCode(HttpServletRequest request, String factory_code) {
		return factoryInfoService.checkFactoryCode(factory_code);
	}
	
}