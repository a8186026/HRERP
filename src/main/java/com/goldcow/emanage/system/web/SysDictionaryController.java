package com.goldcow.emanage.system.web;

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

import com.goldcow.emanage.system.service.ISysDictionaryService;
import com.goldcow.emanage.util.gen.entity.SysDictionary;
import com.goldcow.emanage.util.gen.entity.SysDictionarySub;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/system/dictionary")
public class SysDictionaryController {
	/** 数据字典服务 */
	@Autowired
	protected ISysDictionaryService service;

	/**
	 * 数据字典页面
	 * 
	 * @param request HttpServletRequest
	 * @return 数据字典页面遣返
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "sysDictionary", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "system/sysDictionary/sysDictionaryList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询数据字典
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 查询结果
	 */
	@RequestMapping(value = "dictionarys", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> list(HttpServletRequest request, SysDictionary bean) {
		if (SysUtil.hasRight(request, "sysDictionary", GlobalVal.MENU_FUNCTION.VIEW)) {
			return service.list(bean);
		} else {
			return Lists.newArrayList();
		}
	}

	/**
	 * 新增数据字典页面
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 新增数据字典页面路径
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newDictionary(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "sysDictionary", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/system/dictionary/new");
			SysUtil.getToken(request);
			return "system/sysDictionary/sysDictionaryEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 新增数据字典子项页面
	 * 
	 * @param request HttpServletRequest
	 * @param dict_parent 数据字典ID
	 * @param model Model
	 * @return 新增数据字典子项页面路径
	 */
	@RequestMapping(value = "newSub", method = RequestMethod.GET)
	public String newDictionarySub(HttpServletRequest request, Integer dict_parent, Model model) {
		if (SysUtil.hasRight(request, "sysDictionary", GlobalVal.MENU_FUNCTION.VIEW)) {
			SysDictionary sysDictionary = service.getById(dict_parent);
			model.addAttribute("sysDictionary", sysDictionary);
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/system/dictionary/newSub");
			SysUtil.getToken(request);
			return "system/sysDictionary/sysDictionarySubEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查看数据字典信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 数据字典ID
	 * @param model Model
	 * @return 查看数据字典信息页面路径
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "sysDictionary", GlobalVal.MENU_FUNCTION.VIEW)) {
			SysDictionary bean = service.getById(id);
			model.addAttribute("sysDictionary", bean);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/system/dictionary/" + id);
			SysUtil.getToken(request);
			return "system/sysDictionary/sysDictionaryEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查看数据字典子项信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 数据字典子项ID
	 * @param model Model
	 * @return 查看数据字典子项信息页面路径
	 */
	@RequestMapping(value = "sub/{id}", method = RequestMethod.GET)
	public String viewSubDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "sysDictionary", GlobalVal.MENU_FUNCTION.VIEW)) {
			SysDictionarySub bean = service.getSubById(id);
			SysDictionary sysDictionary = service.getById(bean.getDict_parent());
			model.addAttribute("sysDictionary", sysDictionary);
			model.addAttribute("sysDictionarySub", bean);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/system/dictionary/sub/" + id);
			SysUtil.getToken(request);
			return "system/sysDictionary/sysDictionarySubEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 新增数据字典
	 * 
	 * @param request HttpServletRequest
	 * @param bean 数据字典信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, SysDictionary bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysDictionary", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 新增数据字典
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
	 * 新增数据字典子项
	 * 
	 * @param request HttpServletRequest
	 * @param bean 数据字典子项信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "newSub", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addSub(HttpServletRequest request, SysDictionarySub bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysDictionary", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 新增数据字典
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
	 * 修改数据字典
	 * 
	 * @param request HttpServletRequest
	 * @param id 数据字典ID
	 * @param bean 数据字典信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, SysDictionary bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "sysDictionary", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 修改数据字典
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
	 * 修改数据字典子项
	 * 
	 * @param request HttpServletRequest
	 * @param id 数据字典子项ID
	 * @param bean 数据字典子项信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "sub/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> updateSub(HttpServletRequest request, @PathVariable("id") Integer id, SysDictionarySub bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "sysDictionary", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 修改数据字典
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
	 * 删除数据字典
	 * 
	 * @param request HttpServletRequest
	 * @param id 数据字典ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysDictionary", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除数据字典
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
	 * 删除数据字典子项
	 * 
	 * @param request HttpServletRequest
	 * @param id 数据字典子项ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "sub/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> deleteSub(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysDictionary", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除数据字典
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
	 * 根据编码获取数据字典子项
	 * 
	 * @param request HttpServletRequest
	 * @param code 编码
	 * @return 操作结果
	 */
	@RequestMapping(value = "code/{code}", method = RequestMethod.GET)
	@ResponseBody
	public List<SysDictionarySub> getSub(HttpServletRequest request, @PathVariable("code") String code) {
		if (SysUtil.hasRight(request, "sysDictionary", GlobalVal.MENU_FUNCTION.VIEW)) {
			return service.getByCode(code);
		} else {
			return Lists.newArrayList();
		}
	}
	
	/**
	 * 根据编码和值获取数据字典子项
	 * 
	 * @param request HttpServletRequest
	 * @param code 编码
	 * @return 操作结果
	 */
	@RequestMapping(value = "code/{code}/value/{value}", method = RequestMethod.GET)
	@ResponseBody
	public SysDictionarySub getSub(HttpServletRequest request, @PathVariable("code") String code, @PathVariable("value") String value) {
		if (SysUtil.hasRight(request, "sysDictionary", GlobalVal.MENU_FUNCTION.VIEW)) {
			return service.getByCodeAndValue(code, value);
		} else {
			return null;
		}
	}
}