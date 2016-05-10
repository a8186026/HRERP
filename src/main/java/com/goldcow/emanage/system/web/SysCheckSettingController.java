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

import sun.java2d.loops.MaskBlit;

import com.goldcow.emanage.system.service.ISysCheckSettingService;
import com.goldcow.emanage.util.gen.entity.SysCheckSetting;
import com.goldcow.emanage.util.gen.entity.valueObject.SysCheck.SysCheckVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 审核次数管理
 * 
 * @author wangqi
 * @version v1.0
 * @since 2015-6-2
 */
@Controller
@RequestMapping(value = "/system/setting")
public class SysCheckSettingController {
	/** 用户组管理服务 */
	@Autowired
	protected ISysCheckSettingService sysCheckSettingService;

	/**
	 * 用户组管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 用户组管理页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "sysCheckSetting", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "system/sysCheckSetting/sysCheckSettingList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询用户组
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 查询结果
	 */
	@RequestMapping(value = "settings", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, SysCheckSetting bean) {
		if (SysUtil.hasRight(request, "sysCheckSetting", GlobalVal.MENU_FUNCTION.VIEW)) {
			return sysCheckSettingService.list(bean);
		} else {
			return Maps.newHashMap();
		}
	}

	/**
	 * 新增用户组页面
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 新增用户组页面路径
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newGroup(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "sysCheckSetting", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/system/setting/new");
			SysUtil.getToken(request);
			return "system/sysCheckSetting/sysCheckSettingEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查看用户组信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 用户组ID
	 * @param model Model
	 * @return 查看用户组信息页面
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "sysCheckSetting", GlobalVal.MENU_FUNCTION.VIEW)) {
			SysCheckVO sysCheckVO = sysCheckSettingService.getCheckVO(id);
			model.addAttribute("sysCheckVO", sysCheckVO);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/system/setting/" + id);
			SysUtil.getToken(request);
			return "system/sysCheckSetting/sysCheckSettingEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 新增用户组
	 * 
	 * @param request HttpServletRequest
	 * @param bean 用户组信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, SysCheckVO bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysCheckSetting", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 新增用户组
				sysCheckSettingService.add(bean, request);

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
	 * 修改用户组
	 * 
	 * @param request HttpServletRequest
	 * @param id 用户组ID
	 * @param bean 用户组信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, SysCheckVO bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();	
		if (SysUtil.hasRight(request, "sysCheckSetting", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 修改用户组
				sysCheckSettingService.update(bean, request);

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
	 * 删除用户组
	 * 
	 * @param request HttpServletRequest
	 * @param id 用户组ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysCheckSetting", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除用户组
			sysCheckSettingService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	/**
	 * 得到审核信息
	 *  @param id 审核id
	 * @return bean
	 * 
	 * */
	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	@ResponseBody
	public SysCheckVO get(HttpServletRequest request, @PathVariable("id") Integer id) {
		
		if (SysUtil.hasRight(request, "sysCheckSetting", GlobalVal.MENU_FUNCTION.DELETE)) {
		    System.out.println(sysCheckSettingService.getCheckVO(id));
			return sysCheckSettingService.getCheckVO(id);
		} else {
			return null;
		}

		
	}
	
	/**
	 * 根据编号获取正在审核的数量
	 * 
	 * @param code 审核标号
	 * @return 如果存在正在审核的，则返回false，如果没有真正审核的，则返回true
	 */
	@RequestMapping(value = "getCheckNumber", method = RequestMethod.GET)
	@ResponseBody
	public boolean getCheckNumber(HttpServletRequest request,String code) {
		if (SysUtil.hasRight(request, "sysCheckSetting", GlobalVal.MENU_FUNCTION.DELETE)) {
			return sysCheckSettingService.getCheckNumber(code);
		} else {
			return true;
		}

		
	}
	
	
	
}