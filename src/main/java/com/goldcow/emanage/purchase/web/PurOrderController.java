package com.goldcow.emanage.purchase.web;

import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.purchase.service.IPurOrderService;
import com.goldcow.emanage.util.gen.entity.PurOrder;
import com.goldcow.emanage.util.gen.entity.PurOrderList;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.GlobalVal.ACCEPT_STATUS;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 进货订单
 * 
 * @author RiverYao
 * @version v1.0
 * @since 2015-07-02
 */
@Controller
@RequestMapping(value = "/purchase/order")
public class PurOrderController {
	/** 已存储的状态*/
	public static Integer SAVEED_DRAFT = 0;
	/** 未存储的状态*/
	public static Integer NOSAVE_DRAFT = 1;
	
	
	/*@Autowired
	private IPurOrderListService purOrderListService;*/
	@Autowired
	private IPurOrderService purOrderService;
	
	
	
	/**
	 * 所有订单
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 所有订单页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String viewPurOrder(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "purchase", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "purchase/purOrder/purOrder.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	/**
	 * 进货订单
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 进货订单页面路径
	 */
	@RequestMapping(value = "viewPurOrderEdit", method = RequestMethod.GET)
	public String view(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "purchase", GlobalVal.MENU_FUNCTION.VIEW)) {
			PurOrder bean = null;
			// 查询当前用户的未完成订单
			PurOrder oldBean = new PurOrder();
			oldBean.setCreate_user(SysUtil.getLoginUser(request).getUser_id());
			oldBean.setDraft(NOSAVE_DRAFT);
			List<PurOrder> beanList = purOrderService.getList(oldBean);
			if(beanList.size() == 1){
				// 存在未完成订单
				bean = beanList.get(0);
			}else if(beanList.size() == 0){
				// 不存在未完成订单
				// 生成订单票号算法 年月日+流水号
				// 查询当前日期下最大的流水号
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
				PurOrder newBean = new PurOrder();
				newBean.setTicket_id(formatter.format(date)); 
				String maxTicketID = purOrderService.getMaxTicketID(); 
				// 生成成功后,写入Bean,进行添加,返回ID以及ticket_id
				newBean.setTicket_id(maxTicketID);
				// 标记为未完成订单
				newBean.setDraft(NOSAVE_DRAFT);	
				newBean = purOrderService.add(newBean, request);
				// 前台传入大单信息
				bean = newBean;
			}else{
				System.out.println("系统数据异常：当前操作员出现多条未完成的订单记录	操作员帐号："+SysUtil.getLoginUser(request).getUser_name());
				return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
			}
			// 将Bean传入前台
			model.addAttribute("purOrder", bean);
			//当前登录用户(由于逻辑设定，该传入前台的用户一定会与bean中的修改人员保持一致)
			model.addAttribute("user",SysUtil.getLoginUser(request));
			model.addAttribute("orderFormUrl","/purchase/order/updatePurOrderByGet");
			model.addAttribute("orderMethod","GET");
			model.addAttribute("orderListFormUrl","/purchase/orderList/newlist");
			model.addAttribute("orderListMethod","POST");
			return "purchase/purOrder/purOrderEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 查询所有供方信息
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @param callback 回调方法
	 * @param supContent 查询参数
	 * @return 供方信息页面路径
	 */
	@RequestMapping(value = "viewSupList", method = RequestMethod.GET)
	public String viewSupList(HttpServletRequest request, Model model, String callback, String supContent) {
		if (SysUtil.hasRight(request, "purchase", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("callback", callback);
			model.addAttribute("supContent", supContent);
			return "purchase/purOrder/purOrderViewSupList.jsp";
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
	@RequestMapping(value = "viewProList", method = RequestMethod.GET)
	public String viewProList(HttpServletRequest request, Model model, String callback, String proContent) {
		if (SysUtil.hasRight(request, "purchase", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("callback", callback);
			model.addAttribute("proContent", proContent);
			return "purchase/purOrder/purOrderViewProList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 查询订单下的所有小单并且审核
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 小单信息显示页面
	 */
	@RequestMapping(value = "check", method = RequestMethod.GET)
	public String checkList(HttpServletRequest request, Model model,String ticket_id) {
		if (SysUtil.hasRight(request, "purchase", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("ticket_id", ticket_id);
			return "purchase/purOrder/purOrderCheck.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 查询产品进货订单
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request,PurOrder bean) {
		if (SysUtil.hasRight(request, "purchase", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return purOrderService.list(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 新增订购产品信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 订购产品信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "newlist", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, PurOrder bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "purchase", GlobalVal.MENU_FUNCTION.UPDATE)) {			
			
			purOrderService.add(bean, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
			
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param request HttpServletRequest
	 * @param bean 订购产品信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addOrder(HttpServletRequest request, PurOrder bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "purchase", GlobalVal.MENU_FUNCTION.UPDATE)) {			
			purOrderService.add(bean, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
			
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		
		return result;
	}
	
	
	/**
	 * 查看大单信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 大单ID
	 * @param model Model
	 * @return 查看大单信息页面路径
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "purchase", GlobalVal.MENU_FUNCTION.VIEW)) {
			PurOrder purOrder = purOrderService.getById(id);
			model.addAttribute("purOrder", purOrder);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/purchase/order/" + id);
			return "purchase/purOrder/purOrderUpdate.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	
	/**
	 * 修改大单信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 大单信息ID
	 * @param bean 大单信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> updatePurOrder(HttpServletRequest request, @PathVariable("id") Integer id, PurOrder bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "purchase", GlobalVal.MENU_FUNCTION.UPDATE)) {
			
			System.out.println("++++++++++++++++++++++"+bean.getCarryMode());
			
			// 修改大单信息
			purOrderService.update(bean, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
	/**
	 * 查看大单信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 大单ID
	 * @param model Model
	 * @return 查看大单信息页面路径
	 */
	@RequestMapping(value = "check/{id}", method = RequestMethod.GET)
	public String checkDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "purchase", GlobalVal.MENU_FUNCTION.VIEW)) {
			PurOrder purOrder = purOrderService.getById(id);
			model.addAttribute("purOrder", purOrder);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/purchase/order/check/" + id);
			return "purchase/purOrder/purOrderCheckEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	
	/**
	 * 修改大单信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 大单信息ID
	 * @param bean 大单信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "check/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> checkPurOrder(HttpServletRequest request, @PathVariable("id") Integer id, PurOrder bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "purchase", GlobalVal.MENU_FUNCTION.UPDATE)) {
			// 修改大单信息
			purOrderService.checkUpdate(bean, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
	
	/**
	 * 修改大单信息 AJAX特殊情况用GET进行请求
	 * 
	 * @param request HttpServletRequest
	 * @param id 大单信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "updatePurOrderByGet", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> updatePurOrderByGet(HttpServletRequest request, PurOrder bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "purchase", GlobalVal.MENU_FUNCTION.UPDATE)) {
			// 修改大单信息
			System.out.println(bean.getOrderDepartureTime());
			purOrderService.update(bean, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}


	
	
	/**
	 * 删除订购产品信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的订购产品信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "list/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "purchase", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除供方客户信息
			purOrderService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}

	/**
	 * 查询产品进货订单
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "getPurOrderListAndProductInfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getPurOrderListAndProductInfo(HttpServletRequest request,PurOrder bean) {
		if (SysUtil.hasRight(request, "purchase", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return purOrderService.getPurOrderListAndProductInfo(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	/**
	 * 一键入库查询未收货和审核的小单
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "getPurOrderListAndProductInfoForStraight", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getPurOrderListAndProductInfoFor(HttpServletRequest request,String ticket_id) {
		if (SysUtil.hasRight(request, "purchase", GlobalVal.MENU_FUNCTION.VIEW)) {	
			PurOrderList purOrderList = new PurOrderList();
			purOrderList.setTicket_id(ticket_id);
			purOrderList.setIsArrival(ACCEPT_STATUS.UNARRAIVAL);
			purOrderList.setStatus(0);
			return purOrderService.getPurOrderListAndProductInfoForStaright(purOrderList);
		} else {
			return Maps.newHashMap();
		}
	}
	/**
	 * 查询产品进货订单
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "deletePurOrderAll", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> deletePurOrderAll(HttpServletRequest request,PurOrder bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "purchase", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除供方客户信息
			purOrderService.deletePurOrderAll(bean);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
 

	/**
	 * 删除订购产品信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的订购产品信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> deletePurOrder(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "purchase", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除供方客户信息
			purOrderService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
		}
	
	
	/**
	 * 查询需要进行订单审核的大单信息
	 * 
	 * * @author gaoxiang
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "listCheckOrder", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listCheckOrder(HttpServletRequest request,PurOrder bean) {
		if (SysUtil.hasRight(request, "purchase", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return purOrderService.listCheckOrder(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 查询通过订单审核的大单信息
	 * 
	 * * @author gaoxiang
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "listRecepitOrder", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listRecepitOrder(HttpServletRequest request,PurOrder bean) {
		if (SysUtil.hasRight(request, "purchase", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return purOrderService.listRecepitOrder(bean);
		} else {
			return Maps.newHashMap();
		}
	}
}
