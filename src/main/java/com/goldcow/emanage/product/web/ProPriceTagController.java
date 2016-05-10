package com.goldcow.emanage.product.web;

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
import com.goldcow.emanage.accept.vo.PurSupAndProVO;
import com.goldcow.emanage.product.service.IProPriceTagService;
import com.goldcow.emanage.transCargo.service.ITransCargoService;
import com.goldcow.emanage.util.gen.entity.BasMedicineInfo;
import com.goldcow.emanage.util.gen.entity.ProPriceTag;
import com.goldcow.emanage.util.gen.entity.PurAcceptCheck;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.JsonUtil;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 服务层接口
 * 
 * @author cyx
 * @version v1.0
 * @since 2015-1-7
 */
@Controller
@RequestMapping(value = "product/proPriceTag")
public class ProPriceTagController{
	
	@Autowired
	private IProPriceTagService proPriceTagService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "ProPriceTag", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "product/proPriceTag/proPriceTagList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> lists(HttpServletRequest request,ProPriceTag bean,Date start_time, Date end_time) {
		if (SysUtil.hasRight(request, "ProPriceTag", GlobalVal.MENU_FUNCTION.VIEW)) {
			bean.setDepartment_id(SysUtil.getLoginUser(request).getDepart_id());
			return proPriceTagService.list(bean, start_time, end_time, request);
		} else {
			return Maps.newHashMap();
		}
	}
	/**
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 新增药品页面路径
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newMenu(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "ProPriceTag", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/product/proPriceTag/new");
			SysUtil.getToken(request);
			return "product/proPriceTag/proPriceTagEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	

	/**
	 * 新增药品
	 * 
	 * @param request HttpServletRequest
	 * @param bean 药品信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, ProPriceTag bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "ProPriceTag", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				
				
				// 新增药品
				proPriceTagService.add(bean, request);
				result.put("result", "success");
				result.put("message", "操作成功！");
			} else {
				result.put("result", "failure");
				result.put("message", "非法的请求！");
			}
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}


	/**
	 * 删除药品信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的药品信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "ProPriceTag", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除药品信息
			proPriceTagService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
}