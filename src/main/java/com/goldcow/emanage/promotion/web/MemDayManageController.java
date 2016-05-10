package com.goldcow.emanage.promotion.web;

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









import com.goldcow.emanage.promotion.service.IMemDayManageService;
import com.goldcow.emanage.promotion.service.IMemDayManageService;
import com.goldcow.emanage.util.gen.entity.MemDayManage;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 会员日控制页面
 * 
 * @author wubin
 * @version v1.0
 * @since 2015-10-20
 */
@Controller
@RequestMapping(value = "/promotion/memday")
public class MemDayManageController {
	/** 会员日服务 */
	@Autowired
	protected IMemDayManageService memDayManageService;

	/**
	 * 主页面
	 * 
	 * @param request HttpServletRequest
	 * @return 主页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "MemDayManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "promotion/memDay/memDayList.jsp";
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
	public Map<String, Object> list(HttpServletRequest request, MemDayManage bean) {
		if (SysUtil.hasRight(request, "MemDayManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			return memDayManageService.list(bean);
		} else {
			return Maps.newHashMap();
		}
	}

	
	/**
	 * 查询今天是否是会员日
	 * 
	 * @param request HttpServletRequest
	 * @return 会员日信息
	 */
	@RequestMapping(value = "isMemDay", method = RequestMethod.GET)
	@ResponseBody
	public MemDayManage isMemDay(HttpServletRequest request,Integer dept_id) {
		if (SysUtil.hasRight(request, "memCardManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			return memDayManageService.isMemDay(dept_id);
		} else {
			return null;
		}
	}
	

	/**
	 * 新增页面注册
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 页面路径
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newPermission(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "MemDayManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			/*MemDayManage memDayManage = new MemDayManage();
			model.addAttribute("memDayManage",memDayManage);*/
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/promotion/memday/new");
			SysUtil.getToken(request);
			return "promotion/memDay/memDayEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	/**
	 * 新增页面
	 * 
	 * @param request HttpServletRequest
	 * @param bean 新增对象
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, MemDayManage bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "MemDayManage", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				MemDayManage mdm = memDayManageService.add(bean, request);
				result.put("mem_day_id", mdm.getMem_day_id());
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
	 * 修改页面
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @param Id Integer
	 * @return 页面路径
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "MemDayManage", GlobalVal.MENU_FUNCTION.VIEW)) {
		    MemDayManage memDayManage = memDayManageService.getById(id);
			model.addAttribute("memDayManage", memDayManage);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/promotion/memday/" + id);
			SysUtil.getToken(request);
			return "promotion/memDay/memDayEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	/**
	 * 修改页面
	 * 
	 * @param request HttpServletRequest
	 * @param Id Integer
	 * @param bean 修改对象
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, MemDayManage bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "MemDayManage", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
			
				memDayManageService.update(bean, request);
				result.put("mem_day_id", bean.getMem_day_id());
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
	 * 删除页面
	 * 
	 * @param request HttpServletRequest
	 * @param Id Integer
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "MemDayManage", GlobalVal.MENU_FUNCTION.DELETE)) {
			memDayManageService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}
	/**
	 * 保存优先级设置
	 * 
	 * @param request HttpServletRequest
	 * @param data String 
	 * @return 操作结果
	 */
	@RequestMapping(value = "savePriority", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> savePriority(HttpServletRequest request, String data) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "pmnRuleProduct", GlobalVal.MENU_FUNCTION.UPDATE)) {
			
			memDayManageService.savePriority(data, request);
			
			result.put("result", "success");
			result.put("message", "操作成功！");
		}else{
			result.put("result", "failure");
			result.put("message", "没有失败!");
		}
		return result;
	}
	
	
	
}