package com.goldcow.emanage.promotion.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.promotion.service.IPmnGiftSaleService;
import com.goldcow.emanage.util.gen.entity.PmnGiftSale;
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
@RequestMapping(value = "/promotion/giftsale")
public class PmnGiftSaleController {
	/** 赠品信息服务 */
	@Autowired
	protected IPmnGiftSaleService PmnGiftSaleService;

	/**
	 * 赠品信息页面
	 * 
	 * @param request HttpServletRequest
	 * @return 赠品信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "PmnGiftSale", GlobalVal.MENU_FUNCTION.VIEW)) {
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
	public Map<String, Object> list(HttpServletRequest request, PmnGiftSale bean) {
		if (SysUtil.hasRight(request, "PmnGiftSale", GlobalVal.MENU_FUNCTION.VIEW)) {
			return PmnGiftSaleService.list(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	
	/**
	 * 新增买赠信息
	 *  
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 新增买赠信息页面路径
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newMenu(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "PmnGiftSale", GlobalVal.MENU_FUNCTION.VIEW)) {	
			PmnGiftSale pmnGiftSale = new PmnGiftSale();
			pmnGiftSale.setGift_sal_code(PmnGiftSaleService.getMaxGiftSaleCode());
			model.addAttribute("pmnGiftSale", pmnGiftSale);
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/promotion/giftsale/new");
			SysUtil.getToken(request);
			return "promotion/giftSale/giftSaleEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}


	
	
	/**
	 * 新增买赠信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 买赠信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, PmnGiftSale bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "PmnGiftSale", GlobalVal.MENU_FUNCTION.UPDATE)) {
				// 新增买赠信息
				PmnGiftSaleService.add(bean, request);
				result.put("result", "success");
				result.put("message", "操作成功！");

		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		
		return result;
	}
		
		 
	
	/**
	 * 查看买赠信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 买赠信息ID
	 * @param model Model
	 * @return 查看买赠信息页面路径
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "PmnGiftSale", GlobalVal.MENU_FUNCTION.VIEW)) {
			PmnGiftSale pmnGiftSale = PmnGiftSaleService.getById(id);
			model.addAttribute("pmnGiftSale", pmnGiftSale);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/promotion/giftsale/" + id);
			SysUtil.getToken(request);
			return "promotion/giftSale/giftSaleEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}



	/**
	 * 修改买赠信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 买赠信息ID
	 * @param bean 买赠信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, PmnGiftSale bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "PmnGiftSale", GlobalVal.MENU_FUNCTION.UPDATE)) {
			// 修改买赠信息
			PmnGiftSaleService.update(bean, request);

			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}

	/**
	 * 删除买赠信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的买赠信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "PmnGiftSale", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除买赠信息
			PmnGiftSaleService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
	/**
	 * 增加买赠产品页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 买赠信息ID
	 * @param model Model
	 * @return 增加买赠产品页面路径
	 */
	@RequestMapping(value = "addgift/{id}", method = RequestMethod.GET)
	public String addGift(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "PmnGiftSale", GlobalVal.MENU_FUNCTION.VIEW)) {
		//	GiftSale giftSale = giftSaleService.getById(id);
			model.addAttribute("gift_sal_id", id);
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/promotion/giftinfo/new");
			SysUtil.getToken(request);
			return "promotion/giftSale/giftInfoEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 查询编号是否已存在
	 * 
	 * @param value 编号
	 * @return	0表示不存在，1表示已存在
	 */
	@RequestMapping(value = "/checkUnique")
	@ResponseBody
	public Integer checkUnique(String value){
		return PmnGiftSaleService.checkUnique(value);
	}
	

	/**
	 * 新增页面注册
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 页面路径
	 *//*
	@RequestMapping(value = "newMemday", method = RequestMethod.GET)
	public String newPermission(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "GiftInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			GiftInfo giftInfo = new GiftInfo();
			model.addAttribute("giftInfo",giftInfo);
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/promotion/MemDayManage/newMemday");
			SysUtil.getToken(request);
			return "promotion/MemDayManage/MemDayManageEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	*//**
	 * 新增页面
	 * 
	 * @param request HttpServletRequest
	 * @param bean 新增对象
	 * @return 操作结果
	 *//*
	@RequestMapping(value = "newMemday", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, MemDayManage bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "GiftInfo", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				memDayManageService.add(bean, request);

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
	
	*//**
	 * 修改页面
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @param Id Integer
	 * @return 页面路径
	 *//*
	@RequestMapping(value = "updateMemday/{memid}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("memid") Integer memid, Model model) {
		if (SysUtil.hasRight(request, "GiftInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
		    MemDayManage MemDayManage = memDayManageService.getById(memid);
			model.addAttribute("MemDayManage", MemDayManage);
			model.addAttribute("method_Memday", "PUT");
			model.addAttribute("formUrl_Memday", "/promotion/MemDayManage/updateMemday/" + memid);
			SysUtil.getToken(request);
			return "promotion/MemDayManage/MemDayManageEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	*//**
	 * 修改页面
	 * 
	 * @param request HttpServletRequest
	 * @param Id Integer
	 * @param bean 修改对象
	 * @return 操作结果
	 *//*
	@RequestMapping(value = "updateMemday/{memid}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("memid") Integer memid, MemDayManage MemDayManage) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "GiftInfo", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
			
				memDayManageService.update(MemDayManage, request);

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

	*//**
	 * 删除页面
	 * 
	 * @param request HttpServletRequest
	 * @param Id Integer
	 * @return 操作结果
	 *//*
	@RequestMapping(value = "delMemday/{memid}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("memid") Integer memid) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "GiftInfo", GlobalVal.MENU_FUNCTION.DELETE)) {
			memDayManageService.delete(memid, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}
	*/
	
	
	
}