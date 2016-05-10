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

import com.goldcow.emanage.system.service.ISysComponentService;
import com.goldcow.emanage.system.service.ISysPageService;
import com.goldcow.emanage.util.gen.entity.SysComponentCtrl;
import com.goldcow.emanage.util.gen.entity.SysPageCtrl;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 页面与控件注册
 * 
 * @author Yuanxin
 * @version v1.1
 * @since 2015-05-26
 */
@Controller
@RequestMapping(value = "/system/pageComponent")
public class SysPageComponentController {
	/** 页面注册服务 */
	@Autowired
	protected ISysPageService sysPageService;
	/** 控件注册服务 */
	@Autowired
	protected ISysComponentService sysComponentService;

	/**
	 * 页面与控件注册页面
	 * 
	 * @param request HttpServletRequest
	 * @return 页面与控件注册页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "sysPagecom", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "system/sysPageComponent/sysPageComponentList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询页面注册
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 查询结果
	 */
	@RequestMapping(value = "pages", method = RequestMethod.GET)
	@ResponseBody
	public List<SysPageCtrl> list(HttpServletRequest request, SysPageCtrl bean) {
		if (SysUtil.hasRight(request, "sysPagecom", GlobalVal.MENU_FUNCTION.VIEW)) {
			return sysPageService.list(bean);
		} else {
			return Lists.newArrayList();
		}
	}
	/**
	 * 查询控件注册
	 * 
	 * @param request HttpServletRequest
	 * @param Id Integer
	 * @param bean 查询条件
	 * @return 查询结果
	 */
	@RequestMapping(value = "components/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<SysComponentCtrl> listcompoent(HttpServletRequest request, @PathVariable("id") Integer id, SysComponentCtrl bean) {
		if (SysUtil.hasRight(request, "sysPagecom", GlobalVal.MENU_FUNCTION.VIEW)) {
			if(id==0){
				return null;
			}else{
				bean.setPage_id(id);
				
				if(bean.getCtrl_name()!=null&&(bean.getCtrl_name()==""||bean.getCtrl_name().equals(""))){
					bean.setCtrl_name(null);
				}
				if(bean.getCtrl_type()!=null&&(bean.getCtrl_type()==""||bean.getCtrl_type().equals(""))){
					bean.setCtrl_type(null);
				}
			return sysComponentService.list(bean);
			}
		} else {
			return Lists.newArrayList();
		}
	}
	/**
	 * 新增页面注册
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 页面路径
	 */
	@RequestMapping(value = "newPage", method = RequestMethod.GET)
	public String newPermission(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "sysPagecom", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("method_Page", "POST");
			model.addAttribute("formUrl_Page", "/system/pageComponent/newPage");
			SysUtil.getToken(request);
			return "system/sysPageComponent/sysPageEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	/**
	 * 新增页面注册
	 * 
	 * @param request HttpServletRequest
	 * @param bean 新增对象
	 * @return 操作结果
	 */
	@RequestMapping(value = "newPage", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, SysPageCtrl bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysPagecom", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				
				sysPageService.add(bean, request);

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
	 * 新增控件注册
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @param Id Integer
	 * @return 页面路径
	 */
	@RequestMapping(value = "newCom/{id}", method = RequestMethod.GET)
	public String newCom(HttpServletRequest request, Model model, @PathVariable("id") Integer id) {
		if (SysUtil.hasRight(request, "sysPagecom", GlobalVal.MENU_FUNCTION.VIEW)) {
			SysComponentCtrl bean = new SysComponentCtrl();
			bean.setPage_id(id);
			model.addAttribute("method_Com", "POST");
			model.addAttribute("formUrl_Com", "/system/pageComponent/newCom");
			model.addAttribute("sysComponentCtrl", bean);
			
			System.out.println("page_id:"+bean.getPage_id());
			SysUtil.getToken(request);
			return "system/sysPageComponent/sysComponentEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	/**
	 * 新增控件注册
	 * 
	 * @param request HttpServletRequest
	 * @param bean 新增对象
	 * @return 操作结果
	 */
	@RequestMapping(value = "newCom", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addCom(HttpServletRequest request, SysComponentCtrl bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysPagecom", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				
				sysComponentService.add(bean, request);

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
	 * 修改页面注册
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @param Id Integer
	 * @return 页面路径
	 */
	@RequestMapping(value = "updatePage/{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "sysPagecom", GlobalVal.MENU_FUNCTION.VIEW)) {
			 SysPageCtrl bean = sysPageService.getById(id);
			 
			model.addAttribute("sysPageCtrl", bean);
			model.addAttribute("method_Page", "PUT");
			model.addAttribute("formUrl_Page", "/system/pageComponent/updatePage/" + id);
			SysUtil.getToken(request);
			return "system/sysPageComponent/sysPageEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	/**
	 * 修改页面注册
	 * 
	 * @param request HttpServletRequest
	 * @param Id Integer
	 * @param bean 修改对象
	 * @return 操作结果
	 */
	@RequestMapping(value = "updatePage/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, SysPageCtrl bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "sysPagecom", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
			
				sysPageService.update(bean, request);

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
	 * 修改控件注册
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @param Id Integer
	 * @return 页面路径
	 */
	@RequestMapping(value = "updateCom/{id}", method = RequestMethod.GET)
	public String viewDetailCom(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "sysPagecom", GlobalVal.MENU_FUNCTION.VIEW)) {
			 SysComponentCtrl bean = sysComponentService.getById(id);
			 
			model.addAttribute("sysComponentCtrl", bean);
			model.addAttribute("method_Com", "PUT");
			System.out.println("---------------com");
			model.addAttribute("formUrl_Com", "/system/pageComponent/updateCom/" + id);
			SysUtil.getToken(request);
			return "system/sysPageComponent/sysComponentEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	/**
	 * 修改控件注册
	 * 
	 * @param request HttpServletRequest
	 * @param Id Integer
	 * @param bean 修改对象
	 * @return 操作结果
	 */
	@RequestMapping(value = "updateCom/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> updateCom(HttpServletRequest request, @PathVariable("id") Integer id, SysComponentCtrl bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "sysPagecom", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
			
				sysComponentService.update(bean, request);

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
	 * 删除页面注册
	 * 
	 * @param request HttpServletRequest
	 * @param Id Integer
	 * @return 操作结果
	 */
	@RequestMapping(value = "delPage/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysPagecom", GlobalVal.MENU_FUNCTION.DELETE)) {
	
			sysPageService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}
	/**
	 * 删除页面注册
	 * 
	 * @param request HttpServletRequest
	 * @param Id Integer
	 * @return 操作结果
	 */
	@RequestMapping(value = "delCom/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> deleteCom(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysPagecom", GlobalVal.MENU_FUNCTION.DELETE)) {
		    
			sysComponentService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}
	
}