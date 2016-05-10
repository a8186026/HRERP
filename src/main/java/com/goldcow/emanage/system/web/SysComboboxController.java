package com.goldcow.emanage.system.web;

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

import com.goldcow.emanage.system.service.ISysComboboxService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.bean.LoginUser;
import com.goldcow.emanage.util.gen.entity.SysCombobox;
import com.goldcow.emanage.util.gen.entity.SysUserHabit;
import com.goldcow.emanage.util.gen.entity.valueObject.SysUser.SysUserVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 下拉框管理
 * 
 * @author wubin
 * @version v1.0
 * @since 2015-06-01
 */
@Controller
@RequestMapping(value = "/system/combobox")
public class SysComboboxController {
	/** 用户管理服务 */
	@Autowired
	protected ISysComboboxService sysComboboxService;

	/**
	 * 下拉框管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 下拉框管理页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request,String type,Model model) {
		if (SysUtil.hasRight(request, "sysCombobox", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("type", SysTools.decode(type));
			return "system/sysComboBox/sysComboBoxList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 树形下拉框管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 树形下拉框管理页面路径
	 */
	@RequestMapping(value = "tree", method = RequestMethod.GET)
	public String combotree(HttpServletRequest request,String type,Model model) {
		if (SysUtil.hasRight(request, "sysCombobox", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("type", SysTools.decode(type));
			return "system/sysComboBox/sysComboTreeList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	
	/**
	 * 查询所有下拉框
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @param pid 父级ID
	 * @return 查询下拉框列表
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public List<SysCombobox> list(HttpServletRequest request, SysCombobox bean,String pid,String type) {
		if (SysUtil.hasRight(request, "sysCombobox", GlobalVal.MENU_FUNCTION.VIEW)) {
			//request.getAttribute("pid");
			if(pid!=null&&pid!="")
				bean.setCbs_pid(Integer.parseInt(pid));
			bean.setCbs_type(type);
			return sysComboboxService.list(bean);
		} else {
			return Lists.newArrayList();
		}
	}
	
	/**
	 * 查询所有下拉框
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @param pid 父级ID
	 * @return 查询下拉框列表
	 */
	@RequestMapping(value = "listTree", method = RequestMethod.GET)
	@ResponseBody
	public List<SysCombobox> listTree(HttpServletRequest request, SysCombobox bean,String type) {
		if (SysUtil.hasRight(request, "sysCombobox", GlobalVal.MENU_FUNCTION.VIEW)) {
			bean.setCbs_type(type);
			return sysComboboxService.listTree(bean);
		} else {
			return Lists.newArrayList();
		}
	}

	/**
	 * 新增下拉框页面
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @param cbs_pid 父级类别ID
	 * @param cbs_pchn 父级类别名称
	 * @return 新增页面路径
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newCombobox(HttpServletRequest request, Model model, String cbs_pid ,String cbs_pchn,String type) {
		if (SysUtil.hasRight(request, "sysCombobox", GlobalVal.MENU_FUNCTION.VIEW)) {
			//构建虚拟的bean
			SysCombobox sysCombobox = new SysCombobox();
			if(cbs_pid!=null&&!cbs_pid.equals("")){
				sysCombobox.setCbs_pid(new Integer(cbs_pid));
			}
			sysCombobox.setCbs_type(SysTools.decode(type));
			model.addAttribute("sysCombobox", sysCombobox);//往前台传参
			model.addAttribute("cbs_pchn",cbs_pchn);
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/system/combobox/new");
			SysUtil.getToken(request);
			return "system/sysComboBox/sysComboBoxEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查看下拉框信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 下拉框ID
	 * @param model Model
	 * @param cbs_pchn 父级类别名称
	 * @return 下拉框详细信息页面
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model,String cbs_pchn,String type) {
		if (SysUtil.hasRight(request, "sysCombobox", GlobalVal.MENU_FUNCTION.VIEW)) {
			SysCombobox sysCombobox = sysComboboxService.getById(id);
			sysCombobox.setCbs_type(type);
			model.addAttribute("cbs_pchn",SysTools.decode(cbs_pchn));
			model.addAttribute("sysCombobox", sysCombobox);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/system/combobox/" + id);
			SysUtil.getToken(request);
			return "system/sysComboBox/sysComboBoxEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 新增下拉框
	 * 
	 * @param request HttpServletRequest
	 * @param bean 下拉框信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, SysCombobox bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysCombobox", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				//如果父级目录插入0
				if(bean.getCbs_pid()==null){
					bean.setCbs_pid(0);
				}
				// 新增下拉框
				sysComboboxService.add(bean, request);
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
	 * 修改下拉框
	 * 
	 * @param request HttpServletRequest
	 * @param id 下拉框ID
	 * @param bean 下拉框信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, SysCombobox bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "sysCombobox", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 修改下拉框
				sysComboboxService.update(bean, request);
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
	 * 删除下拉框
	 * 
	 * @param request HttpServletRequest
	 * @param cbs_pid 父级类别ID
	 * @param id 下拉框ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id,String cbs_pid) {

		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysCombobox", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除下拉框,如果是一级
			sysComboboxService.delete(id,Integer.parseInt(cbs_pid), request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}
	
	/**
	 * 验证树形下拉框编码是否存在
	 * 
	 * @param request HttpServletRequest
	 * @param cbs_pid 父级类别ID
	 * @param cbs_code 下拉框编码
	 * @param cbs_type 下拉框类型
	 * @return 操作结果
	 */
	@RequestMapping(value = "getComboTreeByCode", method = RequestMethod.GET)
	@ResponseBody
	public boolean getComboTreeByCode(HttpServletRequest request, Integer cbs_pid,String cbs_code,String cbs_type) {
		// 返回树形下拉框编码是否存在
		return sysComboboxService.getComboTreeByCode(cbs_pid, cbs_code,cbs_type);
	}
}