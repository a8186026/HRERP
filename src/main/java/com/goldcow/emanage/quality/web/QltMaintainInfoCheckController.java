package com.goldcow.emanage.quality.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.quality.service.IQltMaintainInfoService;
import com.goldcow.emanage.util.gen.entity.QltMaintainInfo;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 养护情况登记
 * 
 * @author gaoxiang
 * @since 2015-11-26
 */

@Controller
@RequestMapping(value = "/quality/maintainInfoCheck")
public class QltMaintainInfoCheckController {
	/** 养护品种信息管理服务 */
	@Autowired
	protected IQltMaintainInfoService qltMaintainInfoService;
	
	/**
	 * 养护品种信息管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 养护品种信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "qltMaintainInfoVO", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "/quality/maintainInfo/qltMaintainInfoCheckList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询养护品种信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listMaintainInfoVO(HttpServletRequest request, QltMaintainInfo bean, Integer stock_storage,Integer product_immaintain,Integer pro_group_no) {
		if (SysUtil.hasRight(request, "qltMaintainInfoVO", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return qltMaintainInfoService.listMaintainInfoVO(bean, stock_storage, product_immaintain, pro_group_no);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 获取养护票号
	 *  
	 * @param request HttpServletRequest
	 * @return 养护票号
	 */
	@RequestMapping(value = "getMaintainTicket", method = RequestMethod.GET)
	@ResponseBody
	public String getMaintainTicket(HttpServletRequest request) {
		String  maintain_ticketNo = "";
		if (SysUtil.hasRight(request, "qltMaintainInfo", GlobalVal.MENU_FUNCTION.UPDATE)) {
			//根据入库单号
			maintain_ticketNo = qltMaintainInfoService.getMaxMaintainTicket();
		}
		return maintain_ticketNo;
	}
	
	/**
	 * 养护操作
	 *  
	 * @param request HttpServletRequest
	 * @param data 养护条目ID
	 * @param oldMaintainTicket 前台票号(用于核对最新票号)
	 * @return 确认结果
	 */
	@RequestMapping(value = "maintain", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> maintain(HttpServletRequest request, String data,String oldMaintainTicket) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "qltMaintainInfo", GlobalVal.MENU_FUNCTION.UPDATE)) {
			String maintain_ticketNo = qltMaintainInfoService.maintain(data,oldMaintainTicket,request);
			if(maintain_ticketNo!=null)
				result.put("maintain_ticketNo", maintain_ticketNo);
			result.put("result", "success");
			result.put("message", "操作成功！");
		}else{
			result.put("result", "failure");
			result.put("message", "没有失败!");
		}
		return result;
	}
}