package com.goldcow.emanage.basInfo.web;

import java.util.List;
import java.util.Map; 

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
 


import com.goldcow.emanage.basInfo.service.IBasPictureInfoService;
import com.goldcow.emanage.util.gen.entity.BasPictureInfo;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil; 
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 图片信息
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-6-30
 */
@Controller
@RequestMapping(value = "/basInfo/basPictureInfo")
public class BasPictureInfoController {
	/** 图片信息管理服务 */
	@Autowired
	protected IBasPictureInfoService pictureInfoService;
	
	/**
	 * 图片信息管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 图片信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "basPictureInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "basInfo/basPictureInfo/basPictureInfoList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询图片信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, BasPictureInfo bean, Integer picture_type_id, String picture_type) {
		if (SysUtil.hasRight(request, "basPictureInfo", GlobalVal.MENU_FUNCTION.VIEW)) {	
			bean.setPicture_type_id(picture_type_id);
			bean.setPicture_type(picture_type);
			return pictureInfoService.lists(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 新增图片信息
	 *  
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 新增图片信息页面路径
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newMenu(HttpServletRequest request, Model model, Integer picture_type_id, String picture_type) {
		if (SysUtil.hasRight(request, "basPictureInfo", GlobalVal.MENU_FUNCTION.VIEW)) {			
			BasPictureInfo basPictureInfo = new BasPictureInfo(); 
			basPictureInfo.setPicture_type(picture_type);
			basPictureInfo.setPicture_type_id(picture_type_id);
			model.addAttribute("basPictureInfo", basPictureInfo);
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/basInfo/basPictureInfo/new");
			return "basInfo/basPictureInfo/basPictureInfoEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 新增图片信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 图片信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, BasPictureInfo bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "basPictureInfo", GlobalVal.MENU_FUNCTION.UPDATE)) {
				pictureInfoService.add(bean, request);
				result.put("result", "success");
				result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		
		return result;
	}
		
		 
	
	/**
	 * 查看图片信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 图片信息ID
	 * @param model Model
	 * @return 查看图片信息页面路径
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "basPictureInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			BasPictureInfo basPictureInfo = pictureInfoService.getById(id);
			model.addAttribute("basPictureInfo", basPictureInfo);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/basInfo/basPictureInfo/" + id);
			return "basInfo/basPictureInfo/basPictureInfoEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}



	/**
	 * 修改图片信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 图片信息ID
	 * @param bean 图片信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, BasPictureInfo bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "basPictureInfo", GlobalVal.MENU_FUNCTION.UPDATE)) {
				// 修改图片信息
				pictureInfoService.update(bean, request);
				result.put("result", "success");
				result.put("message", "操作成功！");
			} 
		else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}

	/**
	 * 删除图片信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的图片信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "basPictureInfo", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除图片信息
			pictureInfoService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
}