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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.system.service.ISysGroupService;
import com.goldcow.emanage.system.service.ISysUserService;
import com.goldcow.emanage.util.gen.entity.SysGroup;
import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.emanage.util.gen.entity.valueObject.SysUser.SysUserVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 用户组管理
 * 
 * @author chengrongchang
 * @version v1.0
 * @since 2014-10-15
 */
@Controller
@RequestMapping(value = "/system/group")
public class SysGroupController {
	/** 用户组管理服务 */
	@Autowired
	protected ISysGroupService sysGroupService;
	@Autowired 
	/** 用户管理服务 */
	protected ISysUserService sysUserService; 

	/**
	 * 用户及组管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 用户组管理页面路径
	 */
	@RequestMapping(value = "groupuser", method = RequestMethod.GET)
	public String viewGroupUser(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "sysGroup", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "system/sysGroup/sysGroupUserList.jsp";
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
	@RequestMapping(value = "groups", method = RequestMethod.GET)
	@ResponseBody
	public List<SysGroup> list(HttpServletRequest request, SysGroup bean) {
		if (SysUtil.hasRight(request, "sysGroup", GlobalVal.MENU_FUNCTION.VIEW)) {
			return sysGroupService.list(bean);
		} else {
			return Lists.newArrayList();
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
		if (SysUtil.hasRight(request, "sysGroup", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/system/group/new");
			SysUtil.getToken(request);
			return "system/sysGroup/sysGroupEdit.jsp";
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
		if (SysUtil.hasRight(request, "sysGroup", GlobalVal.MENU_FUNCTION.VIEW)) {
			SysGroup sysGroup = sysGroupService.getById(id);
			model.addAttribute("sysGroup", sysGroup);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/system/group/" + id);
			SysUtil.getToken(request);
			return "system/sysGroup/sysGroupEdit.jsp";
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
	public Map<String, Object> add(HttpServletRequest request, SysGroup bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysGroup", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 新增用户组
				sysGroupService.add(bean, request);

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
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, SysGroup bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "sysGroup", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 修改用户组
				sysGroupService.update(bean, request);

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
		if (SysUtil.hasRight(request, "sysGroup", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除用户组
			sysGroupService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}

	/**
	 * 查询用户组内人员
	 * 
	 * @param request HttpServletRequest
	 * @param id 用户组ID
	 * @param model Model
	 * @return 查询用户组内用户列表
	 */
	@RequestMapping(value = "{id}/users/all", method = RequestMethod.GET)
	@ResponseBody
	public List<SysUserVO> usersAll(HttpServletRequest request, @PathVariable("id") Integer id, Model model, SysUserVO bean) {
		if (SysUtil.hasRight(request, "sysGroup", GlobalVal.MENU_FUNCTION.MANAGE)) {
			// 取得用户组内的用户列表,如果有条件及条件搜索，如果无则返回全部
			bean.setGroup_id(id);
			return sysUserService.getSysUserContainGroupID(bean);

		} else {
			return Lists.newArrayList();
		}
	}
	
	/**
	 * 查询可添加到组内的用户
	 * 
	 * @param request HttpServletRequest
	 * @param id 用户组ID
	 * @param model Model
	 * @return 可添加到组内的用户列表
	 */
	@RequestMapping(value = "{id}/users/others", method = RequestMethod.GET)
	@ResponseBody
	public List<SysUser> otherUsers(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "sysGroup", GlobalVal.MENU_FUNCTION.MANAGE)) {
			// 查询可添加到用户组中的用户列表
			return sysGroupService.getUsersForAdd(id);
		} else {
			return Lists.newArrayList();
		}
	}

	/**
	 * 向用户组添加用户
	 * 
	 * @param request HttpServletRequest
	 * @param id 用户组ID
	 * @param user_id 用户ID
	 * @param model Model
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}/users", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addUser(HttpServletRequest request, @PathVariable("id") Integer id,
			@RequestParam("user_id") Integer user_id, Model model) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysGroup", GlobalVal.MENU_FUNCTION.MANAGE)) {
			// 将用户添加到用户组
			sysGroupService.addUser(id, user_id);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}

	/**
	 * 从用户组删除用户
	 * 
	 * @param request HttpServletRequest
	 * @param id 用户组ID
	 * @param user_id 用户ID
	 * @param model Model
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}/users/{user_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> removeUser(HttpServletRequest request, @PathVariable("id") Integer id,
			@PathVariable("user_id") Integer user_id, Model model) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysGroup", GlobalVal.MENU_FUNCTION.MANAGE)) {
			// 删除用户组内用户
			sysGroupService.deleteUser(id, user_id);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
}