package com.goldcow.emanage.refund.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.refund.service.IRefundService;
import com.goldcow.emanage.util.gen.entity.BasFactoryInfo;
import com.goldcow.emanage.util.gen.entity.Refund;
import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.emanage.util.gen.entity.valueObject.Refund.RefundVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.JsonUtil;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;


@Controller
@RequestMapping(value="/refund/delivery")
public class RefundDeliveryController {
	@Autowired
	private IRefundService refundService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String  view(HttpServletRequest request, Model model) {
		System.out.println("打开返货付货页面");
		if (SysUtil.hasRight(request, "/refund/delivery", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("user",SysUtil.getLoginUser(request));
			SysUtil.getToken(request);
			return "refund/refundDelivery/refundDelivery.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 付货存盘信息
	 *  
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 付货存盘信息页面路径
	 */
/*	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newMenu(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "RefundDelivery", GlobalVal.MENU_FUNCTION.VIEW)) {			
			Refund refund = new Refund(); 
			model.addAttribute("basFactoryInfo", refund);
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "refund/refundDelivery/new");
			SysUtil.getToken(request);
			return "refund/refundDelivery/refundDeliverySave.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}*/
	
	//给datagrid数据填充
	@RequestMapping(value = "/lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> list(HttpServletRequest request,
			@ModelAttribute Refund bean) {
		System.out.println("请求list");
		if (SysUtil.hasRight(request, "/refund/delivery", GlobalVal.MENU_FUNCTION.VIEW)) {
			if(bean.getRefund_ticketId()!=null){
			String str = "%"+bean.getRefund_ticketId()+"%";
			bean.setRefund_ticketId(str);
			}
			System.out.println(bean.toString());
			return refundService.getDeliveryList(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	
	@RequestMapping(value="/saveStatus",  method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Integer saveStatus(HttpServletRequest request, Model model,@RequestParam("dataArr") String dataArr
			){
		System.out.println(dataArr);
			List<RefundVO> list=JsonUtil.jsonArrayToList(dataArr, RefundVO.class);
			for (RefundVO Refund : list) {
			System.out.println(Refund.toString());
			Refund.setRefund_deliveryPerson(SysUtil.getLoginUser(request).getUser_id().toString());
			}
		if (SysUtil.hasRight(request, "/refund/delivery/saveStatus", GlobalVal.MENU_FUNCTION.VIEW)) {
				return refundService.saveDeliveryStatus(list);
		}
		return 0;
	}
}
