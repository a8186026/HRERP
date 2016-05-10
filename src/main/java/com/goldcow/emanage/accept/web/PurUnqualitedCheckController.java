package com.goldcow.emanage.accept.web;

import java.util.Date;
import java.util.Map;







import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.accept.service.IPurAcceptCheckService;
import com.goldcow.emanage.purchase.service.IPurOrderListService;
import com.goldcow.emanage.sale.service.ISalInfoManageService;
import com.goldcow.emanage.stock.service.IStockInfoService;
import com.goldcow.emanage.system.service.ISysParameterService;
import com.goldcow.emanage.util.gen.entity.PurAcceptCheck;
import com.goldcow.emanage.util.gen.entity.PurOrderList;
import com.goldcow.emanage.util.gen.entity.StockInfo;
import com.goldcow.emanage.util.gen.entity.valueObject.accept.PurAcceptCheckVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.GlobalVal.ACCEPT_STATUS;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 不合格审核
 * 
 * @author YuanXin
 * @version v1.0
 * @since 2015-9-24
 */
@Controller
@RequestMapping(value = "/purchase/Unqualitedcheck")
public class PurUnqualitedCheckController {
	
	@Autowired
	protected IPurAcceptCheckService acceptCheckService;
	@Autowired
	protected ISysParameterService sysParameterService;
	@Autowired
	protected IPurOrderListService purOrderListService;
	@Autowired
	protected IStockInfoService stockInfoService;
	Integer olddep_id;
	
	/**
	 * 页面跳转
	 * @param request HttpServletRequest
	 * @return 页面路径
	 * 
	 * */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String viewPurOrder(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "accept/unqualifiedCheck/purUnqualifiedList.jsp";
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
	public Map<String, Object> list(HttpServletRequest request, PurAcceptCheckVO bean,Integer accept_quantityStatus) {
		if (SysUtil.hasRight(request, "PurAcceptCheck", GlobalVal.MENU_FUNCTION.VIEW)) {
		
			bean.setAccept_quantityStatus(ACCEPT_STATUS.WAIT_QUANTITY_CHECK);
			return acceptCheckService.listUnqualified(bean);
			//return acceptCheckService.listUnqualified(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	@RequestMapping(value = "checkView", method = RequestMethod.GET )
		public String check(HttpServletRequest request, Model model,Integer id) {
			if (SysUtil.hasRight(request, "", GlobalVal.MENU_FUNCTION.VIEW)) {
				
				PurAcceptCheck purAcceptCheck = new PurAcceptCheck();
				purAcceptCheck.setAccept_quantityCheckStatus(ACCEPT_STATUS.QUANTIY_CHECKE_FAILED);
				purAcceptCheck.setAccept_quantityStatus(ACCEPT_STATUS.QUANTITIED);
				purAcceptCheck.setAccept_check_id(id);
				
				olddep_id = purAcceptCheck.getDepartment_id();
				model.addAttribute("purAcceptCheck", purAcceptCheck);
				model.addAttribute("method","PUT");
				//不合格品有选择原因和处理方式的页面
				model.addAttribute("formUrl", "/purchase/Unqualitedcheck/" + id);
				SysUtil.getToken(request);
				return "accept/unqualifiedCheck/purUnqualifiedEdit.jsp";
			} else {
				return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
			}
		}
	/**
	 * 不合格
	 * 
	 * @param request HttpServletRequest
	 * @param id 入库信息ID
	 * @param bean 入库信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, PurAcceptCheck bean,Integer dep_id) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "purStorageCheck", GlobalVal.MENU_FUNCTION.UPDATE)) {
			bean.setAccept_check_id(id);
			int newdep = bean.getDepartment_id();
			bean.setDepartment_id(olddep_id);
			bean.setAccept_checkUnqualitedNumber(0.0);
			acceptCheckService.update(bean, request);
			//不合格时先在pur_accept_check中加入一条数据
			
			bean= acceptCheckService.getById(id);
			bean.setAccept_checkQualitedNumber(0.0);
			bean.setDepartment_id(newdep);
             //存入坏库房
             acceptCheckService.addUnqualified(bean); 
             
             //若没有接下来的入库审核步骤，给stock_info中加入一条数据
             PurOrderList purOrderList = purOrderListService.getById(bean.getPur_orderList_id());//表中没有的属性从小单表中取
             if(sysParameterService.getSubById(4).getParam_sub_value().equals("1")){
            	 
            	// acceptCheckService.intake(bean.getAccept_check_id().toString(), "", "", request);
            	 
            	StockInfo stockInfo = new StockInfo();
            	 stockInfo.setProduct_id(purOrderList.getProduct_id());//产品id
            	 stockInfo.setStock_intakeSmallNumber(bean.getAccept_check_id());//入库小号
            	 stockInfo.setStock_storage(bean.getDepartment_id());//库房
            	 stockInfo.setStock_batchCode(bean.getAccept_batchCode());//批号
            	 stockInfo.setStock_batchSmallCode(bean.getAccept_batchSmallCode());
            	 stockInfo.setProduct_id(bean.getAccept_check_id());
            	 stockInfo.setStock_storage(bean.getDepartment_id());
            	 stockInfo.setStock_purchasePrice(purOrderList.getUnitprice());//进价
            	 stockInfo.setStock_storageNumber((double)bean.getAccept_checkUnqualitedNumber());//库房数量
            	 stockInfo.setStock_settlementPrice(purOrderList.getSettlementPrice());//结算价
            	
            	 stockInfo.setStock_sterilizationbnum(bean.getAccept_sterilizationbNum());//灭菌批号
            	
            	 stockInfo.setStock_produceDate(bean.getAccept_productionDate());//生产日期
            	 stockInfo.setStock_intakeCheckTime(new Date());//入库审核时间
            	 stockInfo.setStock_invalidDate(bean.getAccept_expirationDate());//失效期
            	 stockInfo.setStock_sterilizationtime(bean.getAccept_sterilizationbDate());//灭菌日期
            	 stockInfo.setStock_maintaintime(new Date());//养护时间
            	 stockInfo.setStock_priceTime(new Date());//调价时间
           	 
            	 
            	/*stockInfo.setStock_batchNumAmount(pacvo.getAccept_checkQualitedNumber());
     			stockInfo.setStock_batchAmount(quantitys.get(i));
     			stockInfo.setStock_varietyAmount(varietyAmounts.get(i)+pacvo.getAccept_checkQualitedNumber());
     			stockInfo.setStock_varietyStorageAmount(varietyStorageAmounts.get(i)+pacvo.getAccept_checkQualitedNumber());*/
     			
            	 stockInfoService.add(stockInfo, request);
            	
             }
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	/**
	 * 合格
	 * 
	 * @param request HttpServletRequest
	 * @param id 入库信息ID
	 * @param bean 入库信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "pass", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> updatePass(HttpServletRequest request,Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "purStorageCheck", GlobalVal.MENU_FUNCTION.UPDATE)) {
			// 修改销方信息
			PurAcceptCheck bean = acceptCheckService.getById(id);
		
			bean.setAccept_checkQualitedNumber(bean.getAccept_checkQualitedNumber()+bean.getAccept_checkUnqualitedNumber());
		    bean.setAccept_checkUnqualitedNumber(0.0);
		    
		    if(bean.getAccept_specialStatus()==ACCEPT_STATUS.UNSPECIAL_CHECK){//若是特殊药品
				bean.setAccept_quantityStatus(ACCEPT_STATUS.WAIT_SPECIAL_CHECK);//质量验收结果为待特殊药品审核
				}
		    else{
		    	bean.setAccept_quantityStatus(ACCEPT_STATUS.QUANTITIED);//不是特殊药品直接通过
		    }
			bean.setAccept_quantityCheckStatus(ACCEPT_STATUS.QUANTIY_CHECKE_SUCCESS);//质量合格审核通过
			
			acceptCheckService.update(bean, request);

			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}

/**
	 * 删除订单收货确认信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的订单收货确认信息ID
	 * @return 操作结果
	 *//*
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "purOrderCheck", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除订单收货确认信息
			purOrderListService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}*/
	
}