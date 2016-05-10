package com.goldcow.emanage.system.web;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.system.service.ISysUserService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.bean.LoginUser;
import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.emanage.util.gen.entity.SysUserHabit;
import com.goldcow.emanage.util.gen.entity.valueObject.SysUser.SysUserVO;
import com.goldcow.emanage.util.system.SysUserHabits.SysUserDefaultHabits;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 用户管理
 * 
 * @author wubin
 * @version v1.0
 * @since 2015-06-01
 */
@Controller
@RequestMapping(value = "/system/user")
public class SysUserController {
	/** 用户管理服务 */
	@Autowired
	protected ISysUserService sysUserService;

	/**
	 * 用户管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 用户管理页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "sysUser", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "system/sysUser/sysUserList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询所有用户
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 查询用户列表
	 */
	@RequestMapping(value = "users", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, SysUser bean) {
		if (SysUtil.hasRight(request, "sysUser", GlobalVal.MENU_FUNCTION.VIEW)) {
			return sysUserService.list(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	/**
	 * 查询所有用户
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 查询用户列表
	 */
	@RequestMapping(value = "usersForCombo", method = RequestMethod.GET)
	@ResponseBody
	public List<SysUser> lists(HttpServletRequest request, SysUser bean) {
		if (SysUtil.hasRight(request, "sysUser", GlobalVal.MENU_FUNCTION.VIEW)) {
			System.out.println("---------------------不带分页的那个--------------------------");
			return sysUserService.lists(bean);
		} else {
			return null;
		}
	}

	/**
	 * 新增用户页面
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @param group_id 用户所属组ID
	 * @return 新增页面路径
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newUser(HttpServletRequest request, Model model , String group_id) {
		if (SysUtil.hasRight(request, "sysUser", GlobalVal.MENU_FUNCTION.VIEW)) {
			SysUserVO bean = new SysUserVO();
			bean.setGroup_id(new Integer(group_id));
			model.addAttribute("sysUserVO",bean);
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/system/user/new");
			SysUtil.getToken(request);
			return "system/sysUser/sysUserEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查看用户信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 用户ID
	 * @param model Model
	 * @return 用户详细信息页面
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "sysUser", GlobalVal.MENU_FUNCTION.VIEW)) {
			SysUserVO bean = sysUserService.getById(id);
			model.addAttribute("sysUserVO", bean);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/system/user/" + id);
			SysUtil.getToken(request);
			return "system/sysUser/sysUserEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 新增用户
	 * 
	 * @param request HttpServletRequest
	 * @param bean 用户信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, SysUserVO bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysUser", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 新增用户
				sysUserService.add(bean, request);
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
	 * 修改用户
	 * 
	 * @param request HttpServletRequest
	 * @param id 用户ID
	 * @param bean 用户信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, SysUserVO bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "sysUser", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 修改用户
				System.out.println("--------"+bean.getUser_id());
				sysUserService.update(bean, request);
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
	 * 启用帐号
	 * 
	 * @param request HttpServletRequest
	 * @param id 用户ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}/enable", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> enable(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysUser", GlobalVal.MENU_FUNCTION.UPDATE)) {
			// 删除用户
			sysUserService.enable(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}

	/**
	 * 停用帐号
	 * 
	 * @param request HttpServletRequest
	 * @param id 用户ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}/disable", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> disable(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysUser", GlobalVal.MENU_FUNCTION.UPDATE)) {
			// 删除用户
			sysUserService.disable(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}

	/**
	 * 删除用户
	 * 
	 * @param request HttpServletRequest
	 * @param id 用户ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysUser", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除用户
			sysUserService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}

	
	/**
	 * 获取用户习惯
	 * 
	 * @param request HttpServletRequest
	 * @param page_id 页面ID
	 * @param ctrl_id datagrid的ID
	 * @return 用户习惯
	 */
	@RequestMapping(value = "getHabits", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getHabits(HttpServletRequest request,String page_id,String ctrl_id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysUser", GlobalVal.MENU_FUNCTION.VIEW)) {
			
			System.out.println("+++++++++++++++++获取用户习惯："+page_id+"+"+ctrl_id);
			
			// 获取用户习惯
			List<SysUserHabit> suhs = sysUserService.getHabits(SysUtil.getLoginUserId(request),page_id,ctrl_id);
			result.put("sysUserHabits", suhs);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}
	
	
	/**
	 * 保存用户习惯
	 * 
	 * @param request HttpServletRequest
	 * @param fields 字段
	 * @param fieldNames  字段名称 
	 * @param widths  字段宽度
	 * @param page_id 页面ID
	 * @param ctrl_id datagrid的ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "saveHabits", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> saveHabits(HttpServletRequest request,String fields,String fieldNames,String widths,String page_id,String ctrl_id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysUser", GlobalVal.MENU_FUNCTION.VIEW)) {
			// 保存用户习惯
			int num = sysUserService.saveHabits(fields,fieldNames,widths,page_id,ctrl_id,request);
			if(num>0){
				result.put("result", "success");
				result.put("message", "操作成功！");
			}else{
				result.put("result", "failure");
				result.put("message", "操作失败，首次保存需要配置显示的列！");
			}
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}
	

	/**
	 * 新增配置用户习惯—页面属性显示 页面
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 新增页面路径
	 */
	@RequestMapping(value = "config", method = RequestMethod.GET)
	public String configHabit(HttpServletRequest request, Model model,String page_id,String ctrl_id) {
		if (SysUtil.hasRight(request, "sysUser", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("page_id", page_id);
			model.addAttribute("ctrl_id", ctrl_id);
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/system/user/config");
			SysUtil.getToken(request);
			return "system/sysUser/configHabit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 保存用户习惯
	 * 
	 * @param request HttpServletRequest
	 * @param page_id 页面ID
	 * @param ctrl_id datagrid的ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "getConfigs", method = RequestMethod.GET)
	@ResponseBody
	public List<SysUserHabit> getConfigs(HttpServletRequest request,String page_id,String ctrl_id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysUser", GlobalVal.MENU_FUNCTION.VIEW)) {
			
			List<SysUserHabit> suhs = new ArrayList<SysUserHabit>();
			SysUserDefaultHabits sax = new SysUserDefaultHabits();  
	        InputStream input = this.getClass().getClassLoader().getResourceAsStream("userDefaultHabits.xml");  
	        try {
				List<SysUserHabit> sysUserHabits = sax.getHabits(input);
				//过滤掉XML中非此datagrid的用户习惯
				for(int i=0;i<sysUserHabits.size();i++){
					if(sysUserHabits.get(i).getCtrl_id().equals(ctrl_id)&&sysUserHabits.get(i).getPage_id().equals(page_id)){
						suhs.add(sysUserHabits.get(i));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();;
			}
	        
	        for(int i=0;i<suhs.size();i++){
	        	suhs.get(i).setHabit_order(i);
	        }
	        
	        return suhs;
		}else{
			return Lists.newArrayList();
		}
	}
	
	
	/**
	 * 新增配置用户习惯—页面属性显示
	 * 
	 * @param request HttpServletRequest
	 * @param habit_field 字段名称
	 * @param habit_field_name 字段中文名称
	 * @param page_id 页面ID 
	 * @param ctrl_id datagrid的ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "config", method = RequestMethod.POST)
	@ResponseBody

	public Map<String, Object> addHabit(HttpServletRequest request ,String habit_field ,String habit_field_name ,String page_id ,String ctrl_id) {
		 Map<String, Object> result = Maps.newHashMap();
		 if (SysUtil.hasRight(request, "sysUser", GlobalVal.MENU_FUNCTION.VIEW)) {	
				// 新增配置用户习惯—页面属性显示
				HttpSession session = request.getSession();
				LoginUser user = (LoginUser) session.getAttribute(GlobalVal.USER_SESSION); 				
				sysUserService.addUserHabits(user.getSysUser().getUser_id(),habit_field, habit_field_name, page_id, ctrl_id, request);
				
				result.put("result", "success");
				result.put("message", "操作成功！");
			} else {
				result.put("result", "failure");
				result.put("message", "非法的请求！");
			}
		return result;
	}

	/**
	 * 根据ID获取用户信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 用户id
	 * @return 用户信息
	 */
	
	@RequestMapping(value = "getUserById", method = RequestMethod.GET)
	@ResponseBody
	public SysUserVO getUserById(HttpServletRequest request ,Integer id) {
		 Map<String, Object> result = Maps.newHashMap();
		 if (SysUtil.hasRight(request, "sysUser", GlobalVal.MENU_FUNCTION.VIEW)) {	
				// 返回用户信息
			 	return sysUserService.getById(id);
			} else {
				return new SysUserVO();
			}
	}
}