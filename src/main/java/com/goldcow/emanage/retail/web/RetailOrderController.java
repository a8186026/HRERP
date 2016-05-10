package com.goldcow.emanage.retail.web;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.retail.service.IRetailOrderBatchService;
import com.goldcow.emanage.retail.service.IRetailOrderProductService;
import com.goldcow.emanage.retail.service.IRetailOrderService;
import com.goldcow.emanage.stock.service.IStockInfoService;
import com.goldcow.emanage.util.gen.entity.MemCardManage;
import com.goldcow.emanage.util.gen.entity.RetailOrder;
import com.goldcow.emanage.util.gen.entity.RetailOrderBatch;
import com.goldcow.emanage.util.gen.entity.RetailOrderProduct;
import com.goldcow.emanage.util.gen.entity.StockInfo;
import com.goldcow.emanage.util.gen.entity.valueObject.retail.RetailOrderVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.JsonUtil;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonParseException;


@Controller
@RequestMapping(value = "/retail/order")
public class RetailOrderController {
	
	@Autowired
	IRetailOrderService retailOrderService;
	@Autowired
	IRetailOrderProductService retailOrderProductService;
	@Autowired
	IRetailOrderBatchService retailOrderBatchService;
	@Autowired
	IStockInfoService stockInfoService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request, Model model) {

		if (SysUtil.hasRight(request, "retail", GlobalVal.MENU_FUNCTION.VIEW)) {
			SysUtil.getToken(request);
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/retail/order/submitOrder");
			return "retail/order/retailOrder.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 查询当前操作员是否有草稿 
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 草稿信息
	 */
	@RequestMapping(value = "checkDraft", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> checkDraft(HttpServletRequest request, Model model) {
		SysUtil.getToken(request);
		Map<String, Object> result = Maps.newHashMap();
		
		RetailOrder retailOrder = new RetailOrder();
		retailOrder.setRetail_order_operator(SysUtil.getLoginUser(request).getDisplay_name());		//当前操作员
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");  			
		retailOrder.setRetail_order_ticketId(SysUtil.getSqlLikeParam(sdf.format(new Date())));		//当前时间
		retailOrder.setRetail_order_draft(1);														//草稿
		retailOrder.setRows(10);
		retailOrder.setPage(1);
		List<RetailOrder> list = (List<RetailOrder>) retailOrderService.list(retailOrder).get("rows");
		
		if(list.size() == 0){			//当前数据库不存在草稿
			//获取当前日期，查到最大票号，并插入数据库
			String retail_order_ticketId = retailOrderService.getMaxRetailOrderCode(sdf.format(new Date()),request);
			retailOrder.setRetail_order_ticketId(retail_order_ticketId);
			retailOrder.setRetail_order_checkout(1);
			retailOrder.setRetail_order_upload(1);
			retailOrder.setRetail_order_orderTime(new Date());
			
			
			retailOrderService.add(retailOrder, request);
			result.put("retail_order_ticketId", retail_order_ticketId);
			return result;
		}else if (list.size() == 1){		//只有一条草稿，则取出当条记录所对应的订单产品批次VO
			List<RetailOrderVO> listvo = retailOrderService.getDraftInfo(list.get(0).getRetail_order_ticketId());
			if(listvo.size() == 0){			//订单下没有任何卖出产品
				result.put("retail_order_ticketId", list.get(0).getRetail_order_ticketId());
				return result;
			}else{
				result.put("listvo", listvo);
				return result;
			}
			
		}else{						//数据库异常，同时有多条草稿，雪崩
			System.out.println("系统数据异常：当前操作员出现多条未完成的订单记录	操作员帐号："+SysUtil.getLoginUser(request).getUser_name());
			return result;
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
	public String viewProList(HttpServletRequest request, Model model, String callbackPro,String product_id,String proContent,Integer dept_id,Double mem_card_discount) {
		if (SysUtil.hasRight(request, "retail", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("callbackPro", callbackPro);
			model.addAttribute("proContent", proContent);
			model.addAttribute("dept_id", dept_id);
			model.addAttribute("mem_card_discount", mem_card_discount);
			if(product_id!=null&&!product_id.equals(""))
				model.addAttribute("product_id", product_id);
			return "retail/order/retailViewProList.jsp";
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
	public String viewStockList(HttpServletRequest request, Model model, String callbackStock, String product_id,Integer dept_id,Double mem_card_discount,Integer stock_buyPresentSpecial) {
		if (SysUtil.hasRight(request, "retail", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("callbackStock", callbackStock);
			model.addAttribute("product_id", product_id);
			model.addAttribute("dept_id", dept_id);
			model.addAttribute("mem_card_discount", mem_card_discount);
			if(stock_buyPresentSpecial!=null)
				model.addAttribute("stock_buyPresentSpecial", stock_buyPresentSpecial);
			return "retail/order/retailViewStockList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 修改折扣信息
	 * 
	 * @param request HttpServletRequest
	 * @return 修改折扣信息信息页面路径
	 */
	@RequestMapping(value = "setDiscount", method = RequestMethod.GET)
	public String setDiscount(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "retail", GlobalVal.MENU_FUNCTION.VIEW)) {
			
			return "retail/order/setDiscount.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	/**
	 * dll调用页面
	 * 
	 * @param request HttpServletRequest
	 * @return dll调用页面页面路径
	 */
	@RequestMapping(value = "callDll", method = RequestMethod.GET)
	public String callDll(HttpServletRequest request, Model model,String parameter) {
		if (SysUtil.hasRight(request, "retail", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("parameter", parameter);
			return "retail/order/callDll.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 特殊药品信息审核
	 * 
	 * @param request HttpServletRequest
	 * @return 特殊药品信息审核页面路径
	 */
	@RequestMapping(value = "specialMedCheck", method = RequestMethod.GET)
	public String specialMedCheck(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "retail", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "retail/order/specialMedCheck.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	
	/**
	 * 根据票号删除
	 * 
	 * @param request HttpServletRequest
	 * @return 操作结果
	 */
	@RequestMapping(value = "deleteByTicketId", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> deleteByTicketId(HttpServletRequest request, String ticketId, Model model) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "retail", GlobalVal.MENU_FUNCTION.VIEW)) {
			// 删除销方信息
			retailOrderService.deleteByTicketId(ticketId);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	/**-----
	 * 查询批次库存
	 * 
	 * @param request HttpServletRequest
	 * @return 操作结果
	 */
	@RequestMapping(value = "getRetailOrderVO", method = RequestMethod.GET)
	@ResponseBody
	public List<RetailOrderVO> getRetailOrderVO(HttpServletRequest request,RetailOrderVO bean,String product_ids,String type ,MemCardManage memCardManage) {
			return retailOrderService.getRetailOrderVO(bean,product_ids,type,memCardManage);
	}
	
	/**-----
	 * 提交单项订单产品
	 * 
	 * @param request HttpServletRequest
	 * @return 操作结果
	 */
	@RequestMapping(value = "saveRetail", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveRetail(HttpServletRequest request,String data) throws JsonProcessingException, JsonParseException, IOException {
		Map<String, Object> result = Maps.newHashMap();
		
		
		System.out.println("data:"+data);
		
		
		//JSON数据转换为RetailOrderVO
		RetailOrderVO bean = JsonUtil.jsonStringToObject(data,RetailOrderVO.class);	
		
		List<Integer> product_ids = new ArrayList<Integer>();
		product_ids.add(bean.getProduct_id());
		
		//查询当前产品所在库存表中的所有批次,按失效期排序
		bean.setOrder("stock_invalidDate");
		List<RetailOrderVO> list = retailOrderService.getStockByProductId(bean,product_ids);

		//重新计算当前的库存余量
		Double total = 0.0;
		for(int i = 0; i <list.size();i++){
			total+=list.get(i).getStock_storageNumber();
		}
		System.out.println("total:"+total);
		if(total < bean.getNumber()){
			result.put("result", "failure");
			result.put("message", "库存余量不足");
		}else{
			//订单产品表中插入该条记录
			bean  = retailOrderProductService.addVO(bean,bean.getNumber());
			
			
			//插入Batch表过程中的订单余量
			Double remainNumber = bean.getNumber();
			StockInfo stockInfo = new StockInfo();
			List<RetailOrderVO> soldProductBatch = new ArrayList<RetailOrderVO>();
			for(int i = 0; i < list.size(); i++){
				RetailOrderVO retailOrderVO = new RetailOrderVO();
				retailOrderVO = bean;
				
				//初始化库存实体，用于库存的更新操作
				stockInfo.setStock_info_id(list.get(i).getStock_info_id());
				if(list.get(i).getAccept_Number()>0.0&&list.get(i).getAccept_Number()< remainNumber){		//当前批次数量不足以支付用户需求的数量，表示全部售完
					
					//更新库存数量=占用数量
					stockInfo.setStock_storageNumber(list.get(i).getStock_occupyNumber());
					stockInfoService.update(stockInfo, request);
					
					//插入Batch表数据
					remainNumber = remainNumber-list.get(i).getAccept_Number();
					RetailOrderVO newbean1 = new RetailOrderVO();
					newbean1 = retailOrderBatchService.addVO(retailOrderVO,list.get(i).getAccept_Number(),list.get(i));
					soldProductBatch.add(newbean1);
					
					System.out.println("stock_info_id:"+soldProductBatch.get(0).getStock_info_id());
					
					
				}else if(list.get(i).getAccept_Number()>= remainNumber){				//当前批次数量足够
					//更新库存数量=总数量-需求量
					stockInfo.setStock_storageNumber(list.get(i).getStock_storageNumber()-remainNumber);
					stockInfoService.update(stockInfo, request);
					
					RetailOrderVO newbean2 = new RetailOrderVO();
					newbean2 = retailOrderBatchService.addVO(retailOrderVO,remainNumber,list.get(i));
					soldProductBatch.add(newbean2);
					break;
				}
			}
			
			result.put("result", "success");
			result.put("message", "操作成功！");
			result.put("bean", soldProductBatch);
		}
		return result;
	}
	
	/**
	 * 满额赠活动信息页面
	 * 
	 * @param request HttpServletRequest
	 * @return 操作结果
	 */
	@RequestMapping(value = "fullFillGift", method = RequestMethod.GET)
	public String fullFillGift(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "retail", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "retail/order/chooseFullFillGift.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 活动信息页面
	 * 
	 * @param request HttpServletRequest
	 * @return 操作结果
	 */
	@RequestMapping(value = "giftSale", method = RequestMethod.GET)
	public String giftSale(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "retail", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "retail/order/chooseGiftSale.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	
	/**
	 * 活动信息查询
	 * 
	 * @param request HttpServletRequest
	 * @return 操作结果
	 */
	@RequestMapping(value = "getGiftSales", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getGiftSales(HttpServletRequest request,String member_id,String products){
		return retailOrderService.getGiftSales(request,member_id,products);
	}
	
	/**
	 * 活动信息查询
	 * 
	 * @param request HttpServletRequest
	 * @return 操作结果
	 */
	@RequestMapping(value = "getFullFillGifts", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getPromotions(HttpServletRequest request,String member_id,String data){
		return retailOrderService.getFullFillGifts(request,member_id,data);
	}
	
	
	/**
	 * 删除批次信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的产品批次ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "retail", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除批次信息
			retailOrderBatchService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
	
	/**
	 * 保存活动订单产品信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的产品批次ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "salePromotion", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> salePromotion(HttpServletRequest request,String product_id,String number, String discountedPrice, String retail_order_ticketId,String retail_type) {
		Map<String, Object> result = Maps.newHashMap();
		
		if (SysUtil.hasRight(request, "retail", GlobalVal.MENU_FUNCTION.DELETE)) {
			List<RetailOrderVO> bean =  retailOrderService.salePromotion(product_id,number,discountedPrice,retail_order_ticketId,retail_type,request);
			if(bean != null){
				result.put("result", "success");
				result.put("message", "操作成功！");
				result.put("bean", bean);
			}else{
				result.put("result", "failure");
				result.put("message", "库存不足");
			}
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
	/**
	 * 保存整个订单
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的产品批次ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "submitOrder", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> submitOrder(HttpServletRequest request,RetailOrder bean) {
		Map<String, Object> result = Maps.newHashMap();
		
		if (SysUtil.hasRight(request, "retail", GlobalVal.MENU_FUNCTION.DELETE)) {
			bean.setRetail_order_saveTime(new Date());
			bean.setRetail_order_draft(0);		//设置订单已完成
			bean.setRetail_order_currentReceivable(bean.getRetail_order_receivableAmount());
			try {
				bean.setRetail_order_machineName(InetAddress.getLocalHost().getHostName());
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			retailOrderService.update(bean, request);
			result.put("result", "success");
			result.put("message", "提交成功!");
		}
		else {
		result.put("result", "failure");
		result.put("message", "没有权限!");
		}
		return result;
	}
	
	/**
	 * 更新订单信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 订单信息 
	 * @return 操作结果
	 */
	@RequestMapping(value = "updateOrder", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> updateOrder(HttpServletRequest request,RetailOrder bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "retail", GlobalVal.MENU_FUNCTION.DELETE)) {
			retailOrderService.update(bean, request);
			result.put("result", "success");
			result.put("message", "提交成功!");
		}else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
	/**
	 * 零售退货
	 * 
	 * @param request HttpServletRequest
	 * @param batch_id 退货批号
	 * @param number 退货数量
	 * @return 操作结果
	 */
	@RequestMapping(value = "returnGoods", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> returnGoods(HttpServletRequest request,Integer batch_id,Integer number) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "retail", GlobalVal.MENU_FUNCTION.UPDATE)) {
			
			RetailOrderBatch rob= retailOrderBatchService.getById(batch_id);
		
			if(rob==null||rob.getStock_info_id()==null){
				result.put("result", "failure");
				result.put("message", "零售批号不存在,退货失败!");
			}else{
				//获取库存信息
				StockInfo si = stockInfoService.getById(rob.getStock_info_id());
				
				Double refund = rob.getOrder_batch_refundQuantity()==null?0:rob.getOrder_batch_refundQuantity();
				
				if(rob.getOrder_batch_quantity()-refund>=Math.abs(number)){
					//更新原单返货数量
					RetailOrderBatch batch = new RetailOrderBatch();
					batch.setOrder_batch_id(rob.getOrder_batch_id());
					batch.setOrder_batch_refundQuantity((double)Math.abs(number));
					retailOrderBatchService.update(batch, request);
					
					//退货结算价(负数)
					rob.setStock_settlementPrice(rob.getStock_settlementPrice()/rob.getOrder_batch_quantity()*number);
					//退货数量(负数)
					rob.setOrder_batch_quantity((double)number);
					rob.setOrder_batch_date(new Date());
					rob.setOrder_batch_saleType("退货");
					//添加退货记录
					retailOrderBatchService.add(rob, request);
					
					//更新库存信息
					StockInfo stockInfo = new StockInfo();
					stockInfo.setStock_info_id(rob.getStock_info_id());
					stockInfo.setStock_storageNumber(si.getStock_storageNumber()-number);
					stockInfoService.update(stockInfo, request);
					
					result.put("result", "success");
					result.put("message", "退货成功!");
				}else{
					result.put("result", "failure");
					result.put("message", "购买数量少于退货数量,退货失败!");
				}
			}
			
		}else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
}
