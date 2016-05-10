package com.goldcow.emanage.promotion.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.promotion.service.IPmnRuleInfoService;
import com.goldcow.emanage.util.gen.entity.PmnRuleInfo;
import com.goldcow.emanage.util.gen.entity.PmnRuleProduct;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 折扣规则设定
 * 
 * @author gaoxiang
 * @since 2015-10-27
 */
@Controller
@RequestMapping(value = "/promotion/pmnRuleInfo")
public class PmnRuleInfoController {
	/** 折扣规则信息管理服务 */
	@Autowired
	protected IPmnRuleInfoService pmnRuleInfoService;
	
	/**
	 * 折扣规则信息管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 折扣规则信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "pmnRuleInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "promotion/pmnRuleInfo/pmnRuleInfoList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询折扣规则信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, PmnRuleInfo bean) {
		if (SysUtil.hasRight(request, "pmnRuleInfo", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return pmnRuleInfoService.list(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 新增折扣规则信息
	 *  
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 新增折扣规则信息页面路径
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newMenu(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "pmnRuleInfo", GlobalVal.MENU_FUNCTION.VIEW)) {			
			PmnRuleInfo pmnRuleInfo = new PmnRuleInfo(); 
			model.addAttribute("pmnRuleInfo", pmnRuleInfo);
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/promotion/pmnRuleInfo/new");
			SysUtil.getToken(request);
			return "promotion/pmnRuleInfo/pmnRuleInfoEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 新增折扣规则信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 折扣规则信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, PmnRuleInfo bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "PmnRuleInfo", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {          
				// 新增折扣规则信息
				pmnRuleInfoService.add(bean, request);
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
	 * 查看折扣规则信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 折扣规则信息ID
	 * @param model Model
	 * @return 查看折扣规则信息页面路径
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "pmnRuleInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			PmnRuleInfo pmnRuleInfo = pmnRuleInfoService.getById(id);
			model.addAttribute("pmnRuleInfo", pmnRuleInfo);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/promotion/pmnRuleInfo/" + id);
			SysUtil.getToken(request);
			return "promotion/pmnRuleInfo/pmnRuleInfoEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}



	/**
	 * 修改折扣规则信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 折扣规则信息ID
	 * @param bean 折扣规则信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, PmnRuleInfo bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "PmnRuleInfo", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 修改折扣规则信息
				pmnRuleInfoService.update(bean, request);

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
	 * 删除折扣规则信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的折扣规则信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "pmnRuleInfo", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除折扣规则信息
			pmnRuleInfoService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
	/**
	 * 查看规则产品选择页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 折扣规则信息ID
	 * @param model Model
	 * @return 查看折扣规则信息页面路径
	 */
	@RequestMapping(value = "ruleProduct/{id}", method = RequestMethod.GET)
	public String ruleProductSelect(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "pmnRuleInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("rule_id", id);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/promotion/pmnRuleInfo/ruleProduct/" + id);
			SysUtil.getToken(request);
			return "promotion/pmnRuleInfo/ruleProductChoose.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	
	/**
	 * 添加规则产品选择页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 折扣规则信息ID
	 * @param bean 折扣规则信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "rulePro/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> addRuleProduct(HttpServletRequest request, @PathVariable("id") Integer id,Integer rule_id) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "pmnRuleProduct", GlobalVal.MENU_FUNCTION.UPDATE)) {
				PmnRuleProduct pmnRuleProduct = new PmnRuleProduct(); 
				pmnRuleProduct.setRule_id(rule_id);
				pmnRuleProduct.setProduct_id(id);
				// 添加产品折扣规则信息
				pmnRuleInfoService.addRuleProduct(pmnRuleProduct, request);

				result.put("result", "success");
				result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}
	
	/**
	 * 批量添加规则产品选择页面
	 * 
	 * @param request HttpServletRequest
	 * @param String data,Integer rule_id
	 * @param bean 折扣规则信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "chooseProducts", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> chooseProducts(HttpServletRequest request, String data,Integer rule_id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "pmnRuleProduct", GlobalVal.MENU_FUNCTION.UPDATE)) {
			
			pmnRuleInfoService.chooseProducts(data, rule_id, request);
			
			result.put("result", "success");
			result.put("message", "操作成功！");
		}else{
			result.put("result", "failure");
			result.put("message", "没有失败!");
		}
		return result;
	}
	
	/**
	 * 删除产品折扣规则信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的折扣规则信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "deleteRuleProduct", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> deleteRuleProduct(HttpServletRequest request, Integer rule_id,Integer product_id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "pmnRuleInfo", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除折扣规则信息
			System.out.println("rule_id"+rule_id+"+"+"product_id"+product_id);
			pmnRuleInfoService.deleteRuleProduct(rule_id, product_id, request);
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
			
			pmnRuleInfoService.savePriority(data, request);
			
			result.put("result", "success");
			result.put("message", "操作成功！");
		}else{
			result.put("result", "failure");
			result.put("message", "没有失败!");
		}
		return result;
	}
}