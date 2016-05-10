package com.goldcow.emanage.refund.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.refund.service.IRefundService;
import com.goldcow.emanage.util.gen.entity.SupInfoManage;
import com.goldcow.emanage.util.gen.entity.valueObject.Refund.RefundVO;
import com.goldcow.emanage.util.gen.entity.valueObject.SupInfoManage.SupInfoManageVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.JsonUtil;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 返货审核  YuechenWANG
 * 
 * */
 

@Controller
@RequestMapping(value = "/refund/check")
public class RefundCheckController {
  
	@Autowired
	private IRefundService refundService;
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "RefundCheckController", GlobalVal.MENU_FUNCTION.VIEW)) {
			
			//下面这句是取当前操作员的字段，并回传给前台的jsp页面，对应看返回的jsp名称，model里面设置user的value是SysUtil.getLoginUser(request)
			model.addAttribute("user",SysUtil.getLoginUser(request));
/*			System.out.println(SysUtil.getLoginUser(request));*/
			return "refund/refundCheck/refundCheck.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 查询所有有退货行为的供方名单
	 * 
	 * * @author YuechenWANG
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "/checkSupplyList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listCheckOrder(HttpServletRequest request,SupInfoManageVO bean) {
		if (SysUtil.hasRight(request, "RefundCheckController", GlobalVal.MENU_FUNCTION.VIEW)) {	
/*			System.out.println("请求返货列表中存在的所有供应商的记录");*/
			return refundService.getDeliveryToCheckList(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 查询点击的有退货行为的供方的全部退货订单记录
	 * 
	 * * @author YuechenWANG
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "/checkRefundDetailList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> refundDetialList(HttpServletRequest request,RefundVO bean) {
		if (SysUtil.hasRight(request, "RefundCheckController", GlobalVal.MENU_FUNCTION.VIEW)) {	
/*			System.out.println("请求每个供应商的详细返货记录列表");
			System.out.println(bean.toString());*/
			/*			System.out.println("111111"+SysUtil.getLoginUser(request).getUser_id().toString());*/
		
			return refundService.getDeliveryToCheckListDetail(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	
	/**
	 * 查询点击的有退货行为的供方的全部退货订单记录
	 * 
	 * * @author YuechenWANG
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "/searchRefunItemBySupcode", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> searchRefunItemBySupcode(HttpServletRequest request,RefundVO bean) {
		if (SysUtil.hasRight(request, "RefundCheckController", GlobalVal.MENU_FUNCTION.VIEW)) {	
/*			System.out.println("通过查询来获得该用户编码下所有的详细返货记录");
			System.out.println(bean.toString());*/
			return refundService.searchRefundItemBySupcode(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	@RequestMapping(value = "refundPassAudit", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public int refundPassAudit(HttpServletRequest request, Model model,@RequestParam("dataArr") String dataArr){
	
		List<RefundVO> list=JsonUtil.jsonArrayToList(dataArr, RefundVO.class);
		for (RefundVO Refund : list) {
			System.out.println(Refund.toString());
			Refund.setRefund_payCheckPerson(SysUtil.getLoginUser(request).getUser_id().toString());
		}
	/*	bean.setRefund_payCheckPerson(SysUtil.getLoginUser(request).getUser_id().toString());*/
		
		if (SysUtil.hasRight(request, "RefundCheckController", GlobalVal.MENU_FUNCTION.VIEW)) {
			return refundService.passAuditToSetStatus(list);
		}else{
		    return 0;
		}
	}
	
	@RequestMapping(value = "viewSupList", method = RequestMethod.GET)
	public String viewSupList(HttpServletRequest request, Model model, String callback, String supContent) {
		if (SysUtil.hasRight(request, "RefundCheckController", GlobalVal.MENU_FUNCTION.VIEW)) {
			System.out.println("查询所有提供了返货申请的供方信息");
			model.addAttribute("callback", callback);
			model.addAttribute("supContent", supContent);
			return "refund/refundCheck/chooseRefundSupply.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	

	/**
	 * 查询供方客户信息信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	
	//这次出错的原因在我查询的时候  没有对输入的字符串做模糊查询处理，粘错了函数，粘出了一个lists，实际是在service里面的list函数，那里面有一个没用的lists函数，迷惑了我，记住问题
	@RequestMapping(value = "getRefundSupplyInfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getRefundSupplyInfo(HttpServletRequest request, SupInfoManageVO bean) {
		if (SysUtil.hasRight(request, "supInfoManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			return refundService.getRefundSupplyInfo(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	
	
}



