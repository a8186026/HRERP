package com.goldcow.emanage.inventory.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.inventory.service.InventoryCheckService;
import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.emanage.util.gen.entity.valueObject.inventory.InventoryVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;
/**
 * 盘点损益申请
 * 
 * @author zhanxiaotong
 * @version v1.0
 * @since 2015-12-3
 */
@Controller
@RequestMapping(value = "/store/inventroyCheck")
public class InventoryCheckController {
	
	@Autowired
	protected InventoryCheckService inventoryCheckService;
	/**
	 * 库存盘点损益管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 批次停售信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request,Model model) {
		if (SysUtil.hasRight(request, "inventoryVO", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/store/inventroy");
			model.addAttribute("user",SysUtil.getLoginUser(request));
			return "inventory/inventoryCheck/inventoryCheckList.jsp";
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
		if (SysUtil.hasRight(request, "inventoryVO", GlobalVal.MENU_FUNCTION.VIEW)) {
			return inventoryCheckService.Checklist(bean);
		} else {
			return Maps.newHashMap();
		}
	}
 
	/**
	 * 获取登陆用户名字
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的订单收货确认信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "getUserName", method = RequestMethod.GET)
	@ResponseBody
	public String getUserName(HttpServletRequest request) {
		String user_name = null;
		SysUser user = SysUtil.getLoginUser(request);
		user_name = user.getDisplay_name();
		return user_name;
	}
	
	/**
	 * 添加拒收信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 入库信息ID
	 * @param bean 入库信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "checked", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> checked(HttpServletRequest request,Integer inventory_id) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "inventoryVO", GlobalVal.MENU_FUNCTION.UPDATE)) {
			
			inventoryCheckService.checked(inventory_id, request);

			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
}
