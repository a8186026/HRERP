package com.goldcow.emanage.accept.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.basInfo.service.IBasDepartmentInfoService;
import com.goldcow.emanage.product.persist.ProStockPriceDao;
import com.goldcow.emanage.purchase.service.IPurStockAcceptanceCheckService;
import com.goldcow.emanage.util.gen.entity.BasDepartmentInfo;
import com.goldcow.emanage.util.gen.entity.PurAcceptCheck;
import com.goldcow.emanage.util.gen.entity.StockPrice;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 库存验收确认信息
 * 
 * @author zhanxiaotong
 * @version v1.0
 * @since 2015-7-15
 */
@Controller
@RequestMapping(value = "/accept/stock")
public class PurStockCheckController {
	/** 库存验收确认信息管理服务 */
	@Autowired
	protected IPurStockAcceptanceCheckService purStockAcceptanceCheckService;
	@Autowired
	protected ProStockPriceDao stockPriceDao;
	@Autowired
	protected IBasDepartmentInfoService departmentInfoService;
	/**
	 * 库存验收确认信息管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 库存验收确认信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "purAcceptCheck", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "accept/stockCheck/purStockCheckList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询库存验收确认信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, PurAcceptCheck bean) {
		
		if (SysUtil.hasRight(request, "purAcceptCheck", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return purStockAcceptanceCheckService.list(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 入库确认
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的财务信息ID
 	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "purAcceptCheck", GlobalVal.MENU_FUNCTION.DELETE)) {
			PurAcceptCheck purAcceptCheck = purStockAcceptanceCheckService.getById(id);
			purAcceptCheck.setAccept_stockStatus(GlobalVal.ACCEPT_STATUS.STOCKED);
			purStockAcceptanceCheckService.update(purAcceptCheck, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
	/**
	 * 获取组号list，用于组号的combobox
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "groupNOList", method = RequestMethod.GET) 
	@ResponseBody
	public List<StockPrice> groupNOList(HttpServletRequest request,Integer department_id) {
		if(department_id == null){
			return null;
		}
		if (department_id > 0) {
		
			
			StockPrice stockP = new StockPrice();
			stockP.setDepartment_id(department_id);
			
			List<StockPrice> stockPriceList = stockPriceDao.lists(stockP);
			return stockPriceList;

		}
		return null;
	}

	/**
	 * 返回数组的查询方法
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 验证结果
	 * 
	 * @author RiverYao
	 * @since 2015-06-10
	 */
	@RequestMapping(value = "getDepartmentList", method = RequestMethod.GET)
	@ResponseBody
	public List<BasDepartmentInfo> getDepartmentList(HttpServletRequest request, BasDepartmentInfo bean) {
		if (SysUtil.hasRight(request, "basDepartmentInfo", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return departmentInfoService.getList(bean);
		} else {
			return Lists.newArrayList();
		}
	}
}