package com.goldcow.emanage.accept.web;

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
import com.goldcow.emanage.stock.service.IStockInfoService;
import com.goldcow.emanage.util.gen.entity.ProInfoManage;
import com.goldcow.emanage.util.gen.entity.PurAcceptCheck;
import com.goldcow.emanage.util.gen.entity.valueObject.accept.PurAcceptCheckVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 收货质量审核
 * 
 * @author wubin
 * @version v1.0
 * @since 2015-9-24
 */
@Controller
@RequestMapping(value = "/accept/quality")
public class PurQualityCheckController {
	/** 收货质量审核 */
	@Autowired
	protected IPurAcceptCheckService purAcceptCheckService;
	/** 库存信息 */
	@Autowired
	protected IStockInfoService stockInfoService;
	/**
	 * 质量审核页面
	 * 
	 * @param request HttpServletRequest
	 * @return 质量审核页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "purAcceptCheck", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "accept/qualityCheck/purQualityCheckList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 查询所有大单
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "listDetail", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listDetail(HttpServletRequest request, PurAcceptCheckVO bean) {
		if (SysUtil.hasRight(request, "purAcceptCheck", GlobalVal.MENU_FUNCTION.VIEW)) {
			return purAcceptCheckService.listDetail(bean); 
		} else {
			return Maps.newHashMap();
		}
	}

	/**
	 * 获取入库票号
	 *  
	 * @param request HttpServletRequest
	 * @return 入库票号
	 */
	@RequestMapping(value = "getIntakeTicket", method = RequestMethod.GET)
	@ResponseBody
	public String getIntakeTicket(HttpServletRequest request) {
		String  accept_intakeTicket = "";
		if (SysUtil.hasRight(request, "purAcceptCheck", GlobalVal.MENU_FUNCTION.UPDATE)) {
			//根据入库单号
			accept_intakeTicket = purAcceptCheckService.getMaxIntakeTicket();
		}
		return accept_intakeTicket;
	}
		
	
	/**
	 * 入库操作
	 *  
	 * @param request HttpServletRequest
	 * @param data 入库条目ID
	 * @param oldIntakeTicket 前台票号(用于核对最新票号)
	 * @param printType  打印方式
	 * @return 入库结果
	 */
	@RequestMapping(value = "intake", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> intake(HttpServletRequest request, String data,String oldIntakeTicket,String printType) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "purAcceptCheck", GlobalVal.MENU_FUNCTION.UPDATE)) {
			String accept_intakeTicket = purAcceptCheckService.intake(data,oldIntakeTicket,printType,request);
			if(accept_intakeTicket!=null)
				result.put("accept_intakeTicket", accept_intakeTicket);
			result.put("result", "success");
			result.put("message", "操作成功！");
		}else{
			result.put("result", "failure");
			result.put("message", "没有失败!");
		}
		return result;
	}
	
	
	/**
	 * 查看小单信息并且录入质检信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 订单收货确认信息ID
	 * @param ticket_id 票号
	 * @param commonName 产品通用名
	 * @param model Model
	 * @return 查看订单收货确认信息页面路径
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request,@PathVariable("id") Integer id,String ticket_id,String commonName, Model model) {
		if (SysUtil.hasRight(request, "purAcceptCheck", GlobalVal.MENU_FUNCTION.VIEW)) {
			PurAcceptCheck purAcceptCheck = purAcceptCheckService.getById(id);
			ProInfoManage pim = purAcceptCheckService.getProductByAcceptId(id);
			model.addAttribute("proInfoManage", pim);
			model.addAttribute("purAcceptCheck", purAcceptCheck);
			model.addAttribute("ticket_id", ticket_id);
			model.addAttribute("commonName", commonName);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/accept/quality/" + id);
			return "accept/qualityCheck/purQualityCheckEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}



	/**
	 * 保存质检结果
	 * 
	 * @param request HttpServletRequest
	 * @param id 质检条目ID
	 * @param bean 质检结果信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, PurAcceptCheck bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "purAcceptCheck", GlobalVal.MENU_FUNCTION.UPDATE)) {
				PurAcceptCheck pac = purAcceptCheckService.getById(bean.getAccept_check_id());
				if(bean.getAccept_checkQualitedNumber()==bean.getAccept_acceptNumber()){
					bean.setAccept_quantityCheckStatus(GlobalVal.ACCEPT_STATUS.QUANTIY_CHECKE_SUCCESS);//无需合格审核
					if(pac.getAccept_specialStatus()==GlobalVal.ACCEPT_STATUS.SPECIAL_CHECKE_SUCCESS)
						bean.setAccept_quantityStatus(GlobalVal.ACCEPT_STATUS.QUANTITIED);//质检完成
					else
						bean.setAccept_quantityStatus(GlobalVal.ACCEPT_STATUS.WAIT_SPECIAL_CHECK);//表示待特殊药品检查
				}
				else
					bean.setAccept_quantityStatus(GlobalVal.ACCEPT_STATUS.WAIT_QUANTITY_CHECK);//待质检审核
			
				// 修改厂家档案信息
				purAcceptCheckService.update(bean, request);

				result.put("result", "success");
				result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}
}