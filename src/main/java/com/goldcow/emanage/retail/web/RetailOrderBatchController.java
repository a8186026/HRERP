package com.goldcow.emanage.retail.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.retail.service.IRetailOrderBatchService;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;


@Controller
@RequestMapping(value = "/retail/batch")
public class RetailOrderBatchController {
	
	@Autowired
	IRetailOrderBatchService retailOrderBatchService;
	
	/**
	 * 更新已卖产品信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 订单信息 
	 * @return 操作结果
	 */
	@RequestMapping(value = "updateOrderBatch", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> updateOrderBatch(HttpServletRequest request,String ids,String ticket_id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "retail", GlobalVal.MENU_FUNCTION.UPDATE)) {
			retailOrderBatchService.updateOrderBatch(ids,ticket_id, request);
			result.put("result", "success");
			result.put("message", "提交成功!");
		}else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
	
}
