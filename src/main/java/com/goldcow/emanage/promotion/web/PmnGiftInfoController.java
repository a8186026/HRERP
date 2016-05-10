package com.goldcow.emanage.promotion.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.promotion.service.IPmnGiftInfoService;
import com.goldcow.emanage.util.gen.entity.PmnGiftInfo;
import com.goldcow.emanage.util.gen.entity.valueObject.promotion.GiftInfoVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 赠品信息控制页面
 * 
 * @author cyx
 * @version v1.0
 * @since 2015-10-27
 */
@Controller
@RequestMapping(value = "/promotion/giftinfo")
public class PmnGiftInfoController {
	/** 赠品信息服务 */
	@Autowired
	protected IPmnGiftInfoService PmnGiftInfoService;

	/**
	 * 赠品信息页面
	 * 
	 * @param request HttpServletRequest
	 * @return 赠品信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "PmnGiftInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "promotion/giftSale/giftSaleList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询页面
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 查询结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, PmnGiftInfo bean) {
		if (SysUtil.hasRight(request, "PmnGiftInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			return PmnGiftInfoService.list(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 查询页面
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 查询结果
	 */
	@RequestMapping(value = "listsVO", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listVO(HttpServletRequest request, GiftInfoVO bean) {
		if (SysUtil.hasRight(request, "PmnGiftInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			return PmnGiftInfoService.list(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 新增买赠产品信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 买赠产品信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, PmnGiftInfo bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "PmnGiftInfo", GlobalVal.MENU_FUNCTION.UPDATE)) {
				// 新增买赠产品信息
				PmnGiftInfoService.add(bean, request);
				result.put("result", "success");
				result.put("message", "操作成功！");

		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		
		return result;
	}	
	
	/**
	 * 删除买赠产品信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的买赠产品信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id,Integer giftSaleId) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "PmnGiftInfo", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除买赠信息
			PmnGiftInfoService.delete(id,giftSaleId, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
}