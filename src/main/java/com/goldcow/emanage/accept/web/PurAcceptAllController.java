package com.goldcow.emanage.accept.web;

import java.text.SimpleDateFormat;
import java.util.Arrays;
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

import com.goldcow.emanage.accept.service.IPurAcceptCheckService;
import com.goldcow.emanage.product.service.IProInfoManageService;
import com.goldcow.emanage.purchase.service.IPurOrderListService;
import com.goldcow.emanage.purchase.service.IPurOrderService;
import com.goldcow.emanage.stock.service.IStockInfoService;
import com.goldcow.emanage.util.gen.entity.ProInfoManage;
import com.goldcow.emanage.util.gen.entity.PurAcceptCheck;
import com.goldcow.emanage.util.gen.entity.PurOrder;
import com.goldcow.emanage.util.gen.entity.PurOrderList;
import com.goldcow.emanage.util.gen.entity.StockInfo;
import com.goldcow.emanage.util.gen.entity.valueObject.Refund.RefundVO;
import com.goldcow.emanage.util.gen.entity.valueObject.accept.PurAcceptCheckVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.GlobalVal.STOCK_STATUS;
import com.goldcow.sframe.util.JsonUtil;
import com.goldcow.sframe.util.GlobalVal.ACCEPT_STATUS;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 一键入库
 * 
 * @author YuanXin
 * @version v1.0
 * @since 2015-11-10
 */
@Controller
@RequestMapping(value = "/purchase/all")
public class PurAcceptAllController {
	/** 已存储的状态*/
	public static Integer SAVEED_DRAFT = 0;
	/** 未存储的状态*/
	public static Integer NOSAVE_DRAFT = 1;

	private PurOrderList newPurOrderList;
	private Integer PurOrderID;

	/*设置*/
	private Integer orderPlanPerson;
	private String orderCheckPerson;
	private String orderAcceptPerson;
	private Date orderPlanTime;
	private Date orderCheckTime;
	private Date orderAcceptTime;
	private Integer supply_id;
	@Autowired
	protected IPurAcceptCheckService acceptCheckService;
	@Autowired
	protected IPurOrderListService purOrderListService;
	@Autowired
	private IPurOrderService purOrderService;
	@Autowired
	private IProInfoManageService proInfoManageService;
	@Autowired
	private IStockInfoService stockInfoService;
	/**
	 * 页面跳转
	 * @param request HttpServletRequest
	 * @return 页面路径
	 * 
	 * */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String viewPurOrder(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "", GlobalVal.MENU_FUNCTION.VIEW)) {
			PurOrder bean = null;
		
			
				// 生成订单票号算法 年月日+流水号
				// 查询当前日期下最大的流水号
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
				PurOrder newBean = new PurOrder();
				newBean.setTicket_id(formatter.format(date)); 
				String maxTicketID = purOrderService.getMaxTicketID(); 
				// 生成成功后,写入Bean,进行添加,返回ID以及ticket_id
				newBean.setTicket_id(maxTicketID);
				// 标记为完成订单
				newBean.setDraft(SAVEED_DRAFT);	
				newBean = purOrderService.add(newBean, request);
				// 前台传入大单信息
				bean = newBean;
				PurOrderID=bean.getId();
			// 将Bean传入前台
			model.addAttribute("purOrder", bean);
			//当前登录用户(由于逻辑设定，该传入前台的用户一定会与bean中的修改人员保持一致)
			model.addAttribute("user",SysUtil.getLoginUser(request));
			model.addAttribute("orderFormUrl","/purchase/order/updatePurOrderByGet");
			model.addAttribute("orderMethod","GET");
			model.addAttribute("orderListFormUrl","/purchase/all/newlist");
			model.addAttribute("orderListMethod","POST");
			model.addAttribute("acceptUrl","/purchase/all/accept");
			model.addAttribute("acceptMethod","POST");
		/*	model.addAttribute("personDateUrl","/purchase/all/setSetting");
			model.addAttribute("personDateMethod","POST");*/
			return "accept/acceptAll/acceptAllList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	/**
	 * 确认收货——查询大单下的所有小单信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, PurAcceptCheckVO bean) {
		if (SysUtil.hasRight(request, "PurAcceptCheck", GlobalVal.MENU_FUNCTION.VIEW)) {
			bean.setAccept_acceptStatus(ACCEPT_STATUS.UNACCEPT);
			bean.setAccept_rejectStatus(ACCEPT_STATUS.UNREJECT);
			bean.setAccept_quantityStatus(ACCEPT_STATUS.UNQUANTITY);
			
			return acceptCheckService.listDetail(bean);
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
	public Map<String, Object> add(HttpServletRequest request, PurOrderList purOrderList) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "purOrderList", GlobalVal.MENU_FUNCTION.UPDATE)) {
			
			
			purOrderList.setCreate_user(orderPlanPerson);
			purOrderList.setCreate_time(orderPlanTime);
			purOrderList.setLast_modify_user(orderPlanPerson);
			purOrderList.setLast_modify_time(orderPlanTime);
			purOrderList.setArrivalDate(orderCheckTime);
			newPurOrderList = purOrderListService.addForStraight(purOrderList, request);
			
			
			result.put("result", "success");
			result.put("message", "操作成功！");
			
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		
		return result;
	}
	/**
	 * 设置订单计划，收货，验收等人员和时间信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "setSetting", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> setSetting(HttpServletRequest request,Integer planPerson,String acceptPerson,String checkPerson,Date planTime,Date acceptTime,Date checkTime,Integer sup_id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "purOrderList", GlobalVal.MENU_FUNCTION.UPDATE)) {			
			
		System.out.println("_________________setSetting_________________"+planPerson+"____"+planTime);
		  orderPlanPerson = planPerson;
		  orderCheckPerson = checkPerson;
	      orderAcceptPerson = acceptPerson;
	      orderPlanTime = planTime;
	      orderCheckTime = checkTime;
	      orderAcceptTime = acceptTime;
	      supply_id = sup_id;
			result.put("result", "success");
			result.put("message", "操作成功！");
			
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		
		return result;
	}
	
	@RequestMapping(value = "accept", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> accept(HttpServletRequest request,PurAcceptCheck purAcceptCheck) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "purOrderList", GlobalVal.MENU_FUNCTION.UPDATE)) {			
			
			
			purAcceptCheck.setPur_orderList_id(newPurOrderList.getId());
			purAcceptCheck.setPur_order_id(PurOrderID);
			purAcceptCheck.setAccept_sum(newPurOrderList.getOrderSettlementPrice());
			purAcceptCheck.setAccept_checkQualitedNumber(newPurOrderList.getArrivalNumber());
			
			
			purAcceptCheck.setAccept_acceptPerson(orderAcceptPerson);
			purAcceptCheck.setAccept_checkStockPerson(SysUtil.getLoginUser(request).getDisplay_name());
		/*	purAcceptCheck.setAccept_checkStockTime(new Date());
			purAcceptCheck.setAccept_startTime(new Date());
			purAcceptCheck.setAccept_checkQualityDate(new Date());
			purAcceptCheck.setAccept_rejectCheckDate(new Date());*/
			purAcceptCheck.setAccept_acceptDate(orderAcceptTime);
			purAcceptCheck.setAccept_checkQualityDate(orderCheckTime);
			purAcceptCheck.setAccept_rejectCheckDate(orderCheckTime);
			purAcceptCheck.setAccept_checkQualityDate(orderCheckTime);
			purAcceptCheck.setAccept_checkStockTime(orderCheckTime);
			purAcceptCheck.setAccept_checkStockPerson(orderCheckPerson);
			
			purAcceptCheck.setAccept_acceptStatus(ACCEPT_STATUS.ACCEPTED);
			purAcceptCheck.setAccept_quantityStatus(ACCEPT_STATUS.QUANTITIED);
			purAcceptCheck.setAccept_rejectStatus(ACCEPT_STATUS.REJECT_SUCCESS);
			purAcceptCheck.setAccept_quantityCheckStatus(ACCEPT_STATUS.QUANTIY_CHECKE_SUCCESS);
			purAcceptCheck.setAccept_stockStatus(ACCEPT_STATUS.STOCKED);
			
			acceptCheckService.Straightadd(purAcceptCheck, request);
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
	 * 
	 * @param request HttpServletRequest
	 * @param id 入库信息ID
	 * @param bean 入库信息
	 * @return 操作结果
	 */

	@RequestMapping(value = "delete", method = {RequestMethod.GET,RequestMethod.POST})

	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, Model model,@RequestParam("idArr") String idArr) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "purStorageCheck", GlobalVal.MENU_FUNCTION.UPDATE)) {
			
		
		
		  String b=idArr.substring(1, idArr.length()-1);
		  String c[]=b.split("\\,");
		  int[]num = new int[c.length];
		  for(int i=0;i<c.length;i++){
			  num[i]=Integer.parseInt(c[i]);
			//  PurAcceptCheck purAcceptCheck=acceptCheckService.getByOrderListID(num[i]);
			  purOrderListService.delete(num[i], request);
				PurAcceptCheck purAcceptCheck=acceptCheckService.getByOrderListID(num[i]);
				acceptCheckService.delete(purAcceptCheck.getAccept_check_id(), request);
		  }
		
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
	
	

	
	@RequestMapping(value = "pass", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public  Map<String, Object> refundPassAudit(HttpServletRequest request, Model model,@RequestParam("idArr") String idArr){
		System.out.println("                  22222                   "+idArr);
		Map<String, Object> result = Maps.newHashMap();
		  String b=idArr.substring(1, idArr.length()-1);
		  String c[]=b.split("\\,");
		  int[]num = new int[c.length];
		  for(int i=0;i<c.length;i++){
			  num[i]=Integer.parseInt(c[i]);
			  PurAcceptCheck purAcceptCheck=acceptCheckService.getByOrderListID(num[i]);
				PurOrderList purOrderList =  purOrderListService.getById(num[i]);
				ProInfoManage purInfo = proInfoManageService.getById(purOrderList.getProduct_id());
				purOrderList.setArrivalDate(orderAcceptTime);
				purOrderList.setArrivalNumber(purOrderList.getQuantity());
				purOrderList.setIsArrival(ACCEPT_STATUS.ARRAIVALED);
				purOrderListService.update(purOrderList, request);
				
				StockInfo stockInfo = new StockInfo();
				stockInfo.setProduct_id(purOrderList.getProduct_id());
				stockInfo.setStock_intakeSmallNumber(purAcceptCheck.getAccept_check_id());
				stockInfo.setStock_purchasePrice(purOrderList.getUnitprice());
				stockInfo.setStock_batchCode(purAcceptCheck.getAccept_batchCode());
				stockInfo.setStock_packunit(purInfo.getProduct_packingamount().doubleValue());
				stockInfo.setStock_storageNumber(purOrderList.getQuantity());
				stockInfo.setStock_settlementPrice(purOrderList.getSettlementPrice());
				stockInfo.setStock_salepPrice1(purInfo.getProduct_saleprice1());
				stockInfo.setStock_lowPrice(purInfo.getProduct_lowsalelprice());
				stockInfo.setStock_marketPrice(purInfo.getProduct_marketprice());
				stockInfo.setStock_produceDate(purAcceptCheck.getAccept_productionDate());
				stockInfo.setStock_maintaintime(new Date());
				stockInfo.setStock_saleStop(STOCK_STATUS.STOCK_START);
				stockInfo.setStock_intakeTicket(acceptCheckService.getMaxIntakeTicket());
				
				
				stockInfo.setStock_sterilizationbnum(purAcceptCheck.getAccept_sterilizationbNum());
				stockInfo.setStock_sterilizationtime(purAcceptCheck.getAccept_sterilizationbDate());
				stockInfo.setStock_batchSmallCode(purAcceptCheck.getAccept_batchSmallCode());
				stockInfo.setStock_intakeCheckPerson(SysUtil.getLoginUser(request).getDisplay_name());
				stockInfo.setStock_invalidDate(purAcceptCheck.getAccept_expirationDate());
				stockInfo.setStock_buyPresentSpecial(0);
				stockInfo.setStock_occupyNumber(.000);
				stockInfo.setStock_priceTime(new Date());
				stockInfo.setSup_id(supply_id);
				stockInfo.setStock_intakeCheckTime(new Date());
				stockInfo.setStock_storage(purAcceptCheck.getDepartment_id());
				stockInfo.setStock_intakeCheck(1);
				stockInfoService.add(stockInfo, request);
			  
		  }
		if (SysUtil.hasRight(request, "RefundCheckController", GlobalVal.MENU_FUNCTION.VIEW)) {
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
      return result;
	}
	
	
}