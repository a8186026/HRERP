package com.goldcow.emanage.quality.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.stock.service.IStockInfoService;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 批次停售解锁
 * 
 * @author gaoxiang
 * @since 2016-1-7
 */

@Controller
@RequestMapping(value = "/quality/bacthClear")
public class QltBatchSaleStopClearController {
	/** 不合格品种信息管理服务 */
	@Autowired
	protected IStockInfoService stockInfoService;
	
	/**
	 * 批次停售解锁信息管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 不合格品种信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "stockInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "/quality/batchSuspensionClear/qltBatchSuspensionClearList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	
	/**
	 * 批次停售解锁操作
	 * 
	 * @param request HttpServletRequest
	 * @param id 审核通过品种的id
	 * @return 操作结果
	 */
	@RequestMapping(value = "saleStopClear", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> saleStopClear(HttpServletRequest request, String data) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "stockInfo", GlobalVal.MENU_FUNCTION.UPDATE)) {
			stockInfoService.saleStopClear(data, request);
			
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}
	
	/**
	 * 删除不合格品种信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的不合格品种信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "stockInfo", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除不合格品种信息
			stockInfoService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
}