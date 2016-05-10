package com.goldcow.emanage.product.web;

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

import com.goldcow.emanage.product.service.IProInfoManageService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.ProInfoManage;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 产品信息
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-6-5
 */
@Controller
@RequestMapping(value = "/product/proInfoManage")
public class ProInfoManageController {
	/** 产品信息管理服务 */
	@Autowired
	protected IProInfoManageService proInfoManageService;
	
	/**
	 * 产品信息管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 产品信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "proInfoManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "product/proInfoManage/proInfoManageList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 药监局选择界面
	 * 
	 * @param request HttpServletRequest
	 * @param callback 回调函数
	 * @return 药监局选择界面
	 */
	@RequestMapping(value = "comNameChoose", method = RequestMethod.GET)
	public String comNameChoose(HttpServletRequest request, String callback, Model model) {
		if (SysUtil.hasRight(request, "proInfoManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("callback", callback);
			return "product/proInfoManage/comNameChoose.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 品名库选择界面
	 * 
	 * @param request HttpServletRequest
	 * @param callback 回调函数
	 * @return 药监局选择界面
	 */
	@RequestMapping(value = "medNameChoose", method = RequestMethod.GET)
	public String medNameChoose(HttpServletRequest request, String callback,String content, Model model) {
		if (SysUtil.hasRight(request, "proInfoManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("callback", callback);
			model.addAttribute("content", content);
			return "product/proInfoManage/medNameChoose.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	
	
	/**
	 * 查询产品信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, ProInfoManage bean,String code) {
		if (SysUtil.hasRight(request, "proInfoManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			if(bean.getProduct_chnpy()=="")
				bean.setProduct_chnpy(null);
			return proInfoManageService.list(bean,code);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 查询产品信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "listSimilar", method = RequestMethod.GET)
	@ResponseBody
	public List<ProInfoManage> listSimilar(HttpServletRequest request, ProInfoManage bean) {
		if (SysUtil.hasRight(request, "proInfoManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			if(bean.getProduct_chnpy()=="")
				bean.setProduct_chnpy(null);
			return proInfoManageService.listSimilar(bean);
		} else {
			return Lists.newArrayList();
		}
	}
	
	/**
	 * 新增产品信息
	 *  
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 新增产品信息页面路径
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newMenu(HttpServletRequest request, Model model,String id) {
		if (SysUtil.hasRight(request, "proInfoManage", GlobalVal.MENU_FUNCTION.VIEW)) {			
			ProInfoManage proInfoManage = new ProInfoManage(); 
			model.addAttribute("proInfoManage", proInfoManage);
			model.addAttribute("id", id);
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/product/proInfoManage/new");
			SysUtil.getToken(request);
			return "product/proInfoManage/proInfoManageEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 新增产品信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 产品信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, ProInfoManage bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "proInfoManage", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 新增产品信息
				proInfoManageService.add(bean, request);
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
	 * 查看产品信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 产品信息ID
	 * @param model Model
	 * @return 查看产品信息页面路径
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "proInfoManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			System.out.println("iiiiiiiiiiiiiiiiiiiiiii:::::"+id);
			ProInfoManage proInfoManage = proInfoManageService.getById(id);
			model.addAttribute("proInfoManage", proInfoManage);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/product/proInfoManage/" + id);
			SysUtil.getToken(request);
			return "product/proInfoManage/proInfoManageEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}



	/**
	 * 修改产品信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 产品信息ID
	 * @param bean 产品信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, ProInfoManage bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "proInfoManage", GlobalVal.MENU_FUNCTION.UPDATE)) {
				// 修改产品信息
				proInfoManageService.update(bean, request);
				result.put("result", "success");
				result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}

	/**
	 * 删除产品信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的产品信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "proInfoManage", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除产品信息
			proInfoManageService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
	/**查找当前数据库表中最大的产品编码值(前面代表产品类别，后3位代表流水号)
	 * @param ticketNumber  代表前面产品类别
	 * @return 返回最大流水号
	 * */
	@RequestMapping(value = "getMaxProductCode", method = RequestMethod.GET)
	@ResponseBody
	public String getMaxProductCode(HttpServletRequest request,String ticketNumber) {
		// 返回下一个ID
		return proInfoManageService.getMaxProductCode(ticketNumber);
	}
	
	/**
	 * 新增产品经营分类信息
	 *  
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 新增产品经营分类信息页面路径
	 */
	@RequestMapping(value = "category", method = RequestMethod.GET)
	public String newScope(HttpServletRequest request, String callback,Model model,String value) {
		if (SysUtil.hasRight(request, "proInfoManage", GlobalVal.MENU_FUNCTION.VIEW)) {			
			//System.out.println(SysTools.decode(value)); 
			model.addAttribute("callback", callback);
			model.addAttribute("value", SysTools.decode(value));
			return "product/proInfoManage/manageCategoryEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 查询产品折扣规则信息(去重)
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "listRuleProduct", method = RequestMethod.GET)
	@ResponseBody
	public List<ProInfoManage> listRuleProduct(HttpServletRequest request, ProInfoManage bean , Integer rule_id) {
		if (SysUtil.hasRight(request, "proInfoManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			if(bean.getProduct_chnpy()=="")
				bean.setProduct_chnpy(null);
			return proInfoManageService.listRuleProduct(bean, rule_id);
		} else {
			return Lists.newArrayList();
		}
	}
	
	/**
	 * 查询产品折扣规则信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "listRuleProductInfo", method = RequestMethod.GET)
	@ResponseBody
	public List<ProInfoManage> listRuleProductInfo(HttpServletRequest request, ProInfoManage bean , Integer rule_id) {
		if (SysUtil.hasRight(request, "proInfoManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			if(bean.getProduct_chnpy()=="")
				bean.setProduct_chnpy(null);
			return proInfoManageService.listRuleProductInfo(bean, rule_id);
		} else {
			return Lists.newArrayList();
		}
	}
}