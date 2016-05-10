package com.goldcow.emanage.system.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.system.service.ISysParameterService;
import com.goldcow.emanage.util.gen.entity.SysParameter;
import com.goldcow.emanage.util.gen.entity.SysParameterSub;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonParseException;

@Controller
@RequestMapping(value = "/system/parameter")
public class SysParameterController {
	/** 系统参数服务 */
	@Autowired
	protected ISysParameterService service;

	/**
	 * 系统参数页面
	 * 
	 * @param request HttpServletRequest
	 * @return 系统参数页面
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "sysParameter", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "system/sysParameter/sysParameterList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询系统参数
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 查询结果
	 */
	@RequestMapping(value = "parameters", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> list(HttpServletRequest request, SysParameter bean) {
		if (SysUtil.hasRight(request, "sysParameter", GlobalVal.MENU_FUNCTION.VIEW)) {
			return service.list(bean);
		} else {
			return Lists.newArrayList();
		}
	}

	/**
	 * 新增系统参数
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 系统参数新增页面路径
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newParameter(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "sysParameter", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/system/parameter/new");
			SysUtil.getToken(request);
			return "system/sysParameter/sysParameterEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 新增系统参数子项
	 * 
	 * @param request HttpServletRequest
	 * @param param_parent 父结点系统参数ID
	 * @param model Model
	 * @return 系统参数子项新增页面路径
	 */
	@RequestMapping(value = "newSub", method = RequestMethod.GET)
	public String newParameterSub(HttpServletRequest request, Integer param_parent, Model model) {
		if (SysUtil.hasRight(request, "sysParameter", GlobalVal.MENU_FUNCTION.VIEW)) {
			SysParameter sysParameter = service.getById(param_parent);
			model.addAttribute("sysParameter", sysParameter);
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/system/parameter/newSub");
			SysUtil.getToken(request);
			return "system/sysParameter/sysParameterSubEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查看系统参数信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 系统参数ID
	 * @param model Model
	 * @return 查看系统参数信息页面路径
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "sysParameter", GlobalVal.MENU_FUNCTION.VIEW)) {
			SysParameter bean = service.getById(id);
			model.addAttribute("sysParameter", bean);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/system/parameter/" + id);
			SysUtil.getToken(request);
			return "system/sysParameter/sysParameterEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查看系统参数子项信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 系统参数子项ID
	 * @param model Model
	 * @return 查看系统参数子项信息页面路径
	 */
	@RequestMapping(value = "sub/{id}", method = RequestMethod.GET)
	public String viewSubDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "sysParameter", GlobalVal.MENU_FUNCTION.VIEW)) {
			SysParameterSub bean = service.getSubById(id);
			SysParameter sysParameter = service.getById(bean.getParam_parent());
			model.addAttribute("sysParameter", sysParameter);
			model.addAttribute("sysParameterSub", bean);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/system/parameter/sub/" + id);
			SysUtil.getToken(request);
			return "system/sysParameter/sysParameterSubEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 新增系统参数
	 * 
	 * @param request HttpServletRequest
	 * @param bean 系统参数信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, SysParameter bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysParameter", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 新增系统参数
				service.add(bean, request);

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
	 * 新增系统参数子项
	 * 
	 * @param request HttpServletRequest
	 * @param bean 系统参数子项信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "newSub", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addSub(HttpServletRequest request, SysParameterSub bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysParameter", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 新增系统参数
				service.addSub(bean, request);

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
	 * 修改系统参数
	 * 
	 * @param request HttpServletRequest
	 * @param id 系统参数ID
	 * @param bean 系统参数信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, SysParameter bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "sysParameter", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 修改系统参数
				service.update(bean, request);

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
	 * 修改系统参数子项
	 * 
	 * @param request HttpServletRequest
	 * @param id 系统参数子项ID
	 * @param bean 系统参数信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "sub/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> updateSub(HttpServletRequest request, @PathVariable("id") Integer id, SysParameterSub bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "sysParameter", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 修改系统参数
				service.updateSub(bean, request);

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
	 * 删除系统参数
	 * 
	 * @param request HttpServletRequest
	 * @param id 系统参数ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysParameter", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除系统参数
			service.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}

	/**
	 * 删除系统参数子项
	 * 
	 * @param request HttpServletRequest
	 * @param id 系统参数子项ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "sub/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> deleteSub(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysParameter", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除系统参数
			service.deleteSub(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}
	
	/**
	 * 查询系统参数表中的信息
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/getParamCode")
	@ResponseBody
	public Map<String, Object> getParamCode(String param)throws JsonProcessingException, JsonParseException, IOException{
		return this.service.getParamCode(param);
	}
}