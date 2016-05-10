package com.goldcow.emanage.inventory.web;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.inventory.service.InventoryApplyService;
import com.goldcow.emanage.util.gen.entity.Inventory;
import com.goldcow.emanage.util.gen.entity.ProInfoManage;
import com.goldcow.emanage.util.gen.entity.valueObject.inventory.InventoryVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;


@Controller
@RequestMapping(value = "/store/inventroy")
public class InventoryApplyController {
	
	@Autowired
	protected InventoryApplyService inventoryApplyService;
	
	
	/**
	 * 库存盘点损益管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 批次停售信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request,Model model) {
		if (SysUtil.hasRight(request, "InventoryApply", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/store/inventroy/profitLossApply");
			model.addAttribute("user",SysUtil.getLoginUser(request));
			return "inventory/inventoryApply/inventoryApply.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}


	
	/**
	 * 盘点库存信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object>list(HttpServletRequest request, InventoryVO bean) {
		System.out.println("执行list");
		if (SysUtil.hasRight(request, "", GlobalVal.MENU_FUNCTION.VIEW)) {
			return inventoryApplyService.list(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 查看盘点信息
	 *  
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 查看盘点信息页面路径
	 */
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String newMenu(HttpServletRequest request, @PathVariable("id") Integer id,Model model) {
		if (SysUtil.hasRight(request, "InventoryVO", GlobalVal.MENU_FUNCTION.VIEW)) {			
			InventoryVO inventoryVO = inventoryApplyService.getInverntoryVOByID(id); 
			model.addAttribute("InventoryVO", inventoryVO);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/store/inventroy/"+id);
			SysUtil.getToken(request);
			return "inventory/inventoryApply/inventoryApplyEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	
	/**
	 * 修改盘点信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 盘点信息ID
	 * @param bean 盘点信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = {RequestMethod.GET,RequestMethod.PUT})
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request,Integer stock_info_id,Double inventory_countedNumber,
			String inventory_reason,Double inventory_profitLossNumber,Double inventory_profitLossMoney) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "Inventory", GlobalVal.MENU_FUNCTION.UPDATE)) {
				// 修改产品信息
			Inventory bean=new Inventory();
			bean.setStock_info_id(stock_info_id);
			bean.setInventory_countedNumber(inventory_countedNumber);
			bean.setInventory_reason(inventory_reason);
			bean.setInventory_profitLossNumber(inventory_profitLossNumber);
			bean.setInventory_profitLossMoney(inventory_profitLossMoney);
			inventoryApplyService.update(bean, request);
			result.put("result", "success");
			result.put("message", "操作成功！");	
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}
	
	/**
	 * 删除盘点信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的盘点信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		System.out.println("执行删除");
		if (SysUtil.hasRight(request, "Inventory", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除产品信息
			inventoryApplyService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
	/**
	 * 添加盘点库存核对操作
	 * 
	 * @param request HttpServletRequest	
	 * @param bean 查询条件
	 * @return 操作结果
	 */

	@RequestMapping(value="profitLossApply",  method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> profitLossApply(HttpServletRequest request,Inventory bean){
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "Inventory", GlobalVal.MENU_FUNCTION.UPDATE)) {	
			//原库房减掉返货的库存量
			System.out.println("11111111111111111111111");
			System.out.println(bean.toString());
			System.out.println("ddd:"+bean.getInventory_check_status());
			
			bean.setInventory_operator(SysUtil.getLoginUser(request).getUser_id().toString());
			
			inventoryApplyService.add(bean, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
	
	/**
	 * 查询所有产品信息
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @param callback 回调方法
	 * @param proContent 查询参数
	 * @return 供方信息页面路径
	 */
	@RequestMapping(value = "viewProList", method = RequestMethod.GET)
	public String viewProList(HttpServletRequest request, Model model, String callback, String proContent) {
		if (SysUtil.hasRight(request, "viewProList", GlobalVal.MENU_FUNCTION.VIEW)) {
			System.out.println("查询所有产品信息");
			model.addAttribute("callback", callback);
			model.addAttribute("proContent", proContent);
			return "inventory/inventoryApply/inventoryViewProList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	/**
	 * 查询所有产品信息
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @param callback 回调方法
	 * @param proContent 查询参数
	 * @return 供方信息页面路径
	 */
	@RequestMapping(value = "viewStockList", method = RequestMethod.GET)
	public String viewStockList(HttpServletRequest request, Model model, String callback, String product_id) {
		if (SysUtil.hasRight(request, "viewStockList", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("callback", callback);
			model.addAttribute("product_id", product_id);
			return "inventory/inventoryApply/inventoryViewStockList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
}
