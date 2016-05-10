package com.goldcow.emanage.refund.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.refund.service.IRefundService;
import com.goldcow.emanage.stock.service.IStockInfoService;
import com.goldcow.emanage.util.gen.entity.Refund;
import com.goldcow.emanage.util.gen.entity.StockInfo;
import com.goldcow.emanage.util.gen.entity.valueObject.Refund.RefundVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.JsonUtil;
import com.goldcow.sframe.util.GlobalVal.REFUND_STATUS;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;


@Controller
@RequestMapping(value = "/refund/cancel")
public class CancelRefundController {

	
	@Autowired
	private IRefundService service;
	@Autowired
	private IStockInfoService stockInfoService;
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request, Model model) {

		if (SysUtil.hasRight(request, "refund", GlobalVal.MENU_FUNCTION.VIEW)) {
			SysUtil.getToken(request);
			return "refund/cancelRefund/cancelRefundList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	
	@RequestMapping(value = "/lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> lists(HttpServletRequest request,RefundVO bean,Model model) {
		if (SysUtil.hasRight(request, "refund", GlobalVal.MENU_FUNCTION.VIEW)) {
		    System.out.println("获取返货列表");

			return service.listCancel(bean);
			

		} else {
			return Maps.newHashMap();
		}
	}
	
	
	
	@RequestMapping(value = "all", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public int refundPassAudit(HttpServletRequest request, Model model,@RequestParam("dataArr") String dataArr){
		System.out.println("22222"+dataArr);
		List<RefundVO> list=JsonUtil.jsonArrayToList(dataArr, RefundVO.class);
		for (RefundVO Refund : list) {
			System.out.println(Refund.toString());
		
			
			Refund.setRefund_cancelPerson(SysUtil.getLoginUserId(request).toString());
			
		}
		System.out.println("正在前往通过审核通过的路途中" +list.get(0).getRefund_cancelPerson());
	/*	bean.setRefund_payCheckPerson(SysUtil.getLoginUser(request).getUser_id().toString());*/
		
		if (SysUtil.hasRight(request, "RefundCheckController", GlobalVal.MENU_FUNCTION.VIEW)) {
			return service.updateList(list);
		}else{
		    return 0;
		}
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request,@PathVariable("id") Integer id,Model model) {
		Map<String,Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "refund", GlobalVal.MENU_FUNCTION.VIEW)) {
		    System.out.println("获取返货列表");
		    //原库房加上不返货的库存
		    Refund bean = new Refund();
		    bean.setRefund_id(id);
		    bean.setRefund_cancel(REFUND_STATUS.REFUND_CANCEL_SUCCESS);
		    bean.setRefund_cancelPerson(SysUtil.getLoginUserId(request).toString());
		    bean.setRefund_cancelDate(new Date());
		    bean.setStatus(9);
		    service.update(bean, request);
		    
		    bean = service.getById(id);
		    StockInfo stockInfo = new StockInfo();
			stockInfo = stockInfoService.getById(bean.getStock_info_id());
			stockInfo.setStock_storageNumber(stockInfo.getStock_storageNumber()+bean.getRefund_number());
			stockInfoService.update(stockInfo, request);
			
			
			//
			service.update(bean, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
			
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
	
}
