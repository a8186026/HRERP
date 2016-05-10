package com.goldcow.emanage.quality.web;

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

import com.goldcow.emanage.quality.service.IQltBatchCheckService;
import com.goldcow.emanage.util.gen.entity.QltBatchCheck;
import com.goldcow.emanage.util.gen.entity.valueObject.quality.QltBatchCheckVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 重点养护批次信息实体类
 * 
 * @author gaoxiang
 * @since 2015-11-17
 */

@Controller
@RequestMapping(value = "/quality/batchCheck")
public class QltBatchCheckController {
	/** 重点养护批次信息管理服务 */
	@Autowired
	protected IQltBatchCheckService qltBatchCheckService;
	
	/**
	 * 重点养护批次信息管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 重点养护批次信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "qltBatchCheckVO", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "quality/batchCheck/qltBatchCheckList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询重点养护批次信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, QltBatchCheckVO bean) {
		if (SysUtil.hasRight(request, "qltBatchCheckVO", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return qltBatchCheckService.list(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 新增重点养护批次信息
	 *  
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 新增重点养护批次信息页面路径
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newMenu(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "qltBatchCheck", GlobalVal.MENU_FUNCTION.VIEW)) {			
			QltBatchCheck qltBatchCheck = new QltBatchCheck(); 
			 
			model.addAttribute("qltBatchCheck", qltBatchCheck);
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/quality/batchCheck/new");
			SysUtil.getToken(request);
			return "quality/batchCheck/qltBatchCheckEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 新增重点养护批次信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 重点养护批次信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, QltBatchCheck bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "qltBatchCheck", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {          
				// 新增重点养护批次信息
				qltBatchCheckService.add(bean, request);
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
	 * 查看重点养护批次信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 重点养护批次信息ID
	 * @param model Model
	 * @return 查看重点养护批次信息页面路径
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "qltBatchCheckVO", GlobalVal.MENU_FUNCTION.VIEW)) {
			QltBatchCheckVO qltBatchCheckVO = qltBatchCheckService.getById(id);
			model.addAttribute("qltBatchCheckVO", qltBatchCheckVO);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/quality/batchCheck/" + id);
			SysUtil.getToken(request);
			return "quality/batchCheck/qltBatchCheckEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}



	/**
	 * 修改重点养护批次信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 重点养护批次信息ID
	 * @param bean 重点养护批次信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, QltBatchCheckVO bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "qltBatchCheckVO", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 修改重点养护批次信息
				qltBatchCheckService.update(bean, request);

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
	 * 删除重点养护批次信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的重点养护批次信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "qltBatchCheck", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除重点养护批次信息
			qltBatchCheckService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
	
	/**
	 * 查询所有库存产品信息
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @param callback 回调方法
	 * @param proContent 查询参数
	 * @return 供方信息页面路径
	 */
	@RequestMapping(value = "viewStockList", method = RequestMethod.GET)
	public String viewStockList(HttpServletRequest request, Model model, String callback, String proContent,Integer product_id) {
		if (SysUtil.hasRight(request, "qltBatchCheckVO", GlobalVal.MENU_FUNCTION.VIEW)) {
			
			QltBatchCheckVO qltBatchCheckVO = new QltBatchCheckVO(); 
			model.addAttribute("qltBatchCheckVO", qltBatchCheckVO);
			
			model.addAttribute("callback", callback);
			model.addAttribute("proContent", proContent);
			model.addAttribute("product_id", product_id);
			return "quality/batchCheck/qltBatchCheckStockList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 查询所有库存产品信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "listStock", method = RequestMethod.GET)
	@ResponseBody
	public List<QltBatchCheckVO> listStock(HttpServletRequest request, QltBatchCheckVO bean, Integer product_id) {
		if (SysUtil.hasRight(request, "qltBatchCheckVO", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return qltBatchCheckService.listStock(bean, product_id);
		} else {
			return Lists.newArrayList();
		}
	}
}