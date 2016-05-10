package com.goldcow.emanage.basInfo.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.basInfo.service.IBasMedicineInfoService;
import com.goldcow.emanage.util.gen.entity.BasMedicineInfo;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 药品
 * 
 * @author chenyuxuan
 * @version v1.0
 * @since 2015-6-2
 */
@Controller
@RequestMapping(value = "/basInfo/basMedicineInfo")
public class BasMedicineInfoController {
	/** 药品管理服务 */

	@Autowired
	protected IBasMedicineInfoService medicineInfoService;

	/**
	 * 药品信息管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 药品管理页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "basMedicineInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "basInfo/basMedicineInfo/basMedicineInfoList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询药品信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> list(HttpServletRequest request, BasMedicineInfo bean) {
		if (SysUtil.hasRight(request, "BasMedicineInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			return medicineInfoService.list(bean);
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
		if (SysUtil.hasRight(request, "BasMedicineInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			String code = medicineInfoService.getMaxMedicineCode();
			BasMedicineInfo basMedicineInfo = new BasMedicineInfo();
			basMedicineInfo.setMedicine_code(code);
			model.addAttribute("BasMedicineInfo", basMedicineInfo);
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/basInfo/basMedicineInfo/new");
			SysUtil.getToken(request);
			return "basInfo/basMedicineInfo/basMedicineInfoEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查看药品信息页面
	 *  
	 * @param request HttpServletRequest
	 * @return 查看药品信息页面路径
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "basMedicineInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			BasMedicineInfo basMedicineInfo = medicineInfoService.getById(id);
			model.addAttribute("BasMedicineInfo", basMedicineInfo);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/basInfo/basMedicineInfo/" + id);
			SysUtil.getToken(request);
			return "basInfo/basMedicineInfo/basMedicineInfoEdit.jsp";
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
	public Map<String, Object> add(HttpServletRequest request, BasMedicineInfo bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "BasMedicineInfo", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				
				
				// 新增药品
				medicineInfoService.add(bean, request);
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
	 * 修改药品信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 药品信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, BasMedicineInfo bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "BasMedicineInfo", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 修改药品信息
				medicineInfoService.update(bean, request);

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
		//System.out.println("+++++++++delete++++++++++++");
		if (SysUtil.hasRight(request, "BasMedicineInfo", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除药品信息
			medicineInfoService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}

}