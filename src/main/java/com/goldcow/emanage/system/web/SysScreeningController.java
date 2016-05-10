package com.goldcow.emanage.system.web;

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

import sun.java2d.loops.MaskBlit;

import com.goldcow.emanage.system.service.ISysCheckSettingService;
import com.goldcow.emanage.system.service.ISysScreeningService;
import com.goldcow.emanage.util.gen.entity.SysCheckSetting;
import com.goldcow.emanage.util.gen.entity.valueObject.SysCheck.SysCheckVO;
import com.goldcow.emanage.util.gen.entity.valueObject.sysScreening.SysScreeningVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 系统筛选管理
 * 
 * @author wubin
 * @version v1.0
 * @since 2015-11-3
 */
@Controller
@RequestMapping(value = "/system/screening")
public class SysScreeningController {
	/** 用户组管理服务 */
	@Autowired
	protected ISysScreeningService sysScreeningService;

	/**
	 * 信息筛选页面
	 * 
	 * @param request HttpServletRequest
	 * @param fields 字段
	 * @param fieldName 字段名
	 * @param className 类名
	 * @return 信息筛选页面
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request,Model model,String fields,String fieldNames,String className,String win,String ctrl_id) {
		if (SysUtil.hasRight(request, "sysScreening", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("fields", fields);
			model.addAttribute("fieldNames", fieldNames);
			model.addAttribute("className", className);
			model.addAttribute("win", win);
			model.addAttribute("ctrl_id", ctrl_id);
			return "system/sysScreening/sysScreeningEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 获取下拉列表
	 * 
	 * @param request HttpServletRequest
	 * @param fields 字段
	 * @param fieldName 字段名
	 * @param className 类名
	 * @return 查询结果
	 */
	@RequestMapping(value = "getList", method = RequestMethod.GET)
	@ResponseBody
	public List<SysScreeningVO> getList(HttpServletRequest request ,String fields,String fieldNames,String className) {
		if (SysUtil.hasRight(request, "sysScreening", GlobalVal.MENU_FUNCTION.VIEW)) {
			List<SysScreeningVO> ss = sysScreeningService.deal(fields,fieldNames,className);
			return ss;
		} else {
			return Lists.newArrayList();
		}
	}
	
	/**
	 * 返回筛选结果(此方法暂时没用到)
	 * 
	 * @param request HttpServletRequest
	 * @param data 查询条件
	 * @param sqlName 数据库名
	 * @return 筛选结果
	 */
	@RequestMapping(value = "search", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest request ,String data,String sqlName,String className,String ids) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysScreening", GlobalVal.MENU_FUNCTION.UPDATE)) {
			List<Object> ss = sysScreeningService.search(request,data,sqlName,className,ids);
			result.put("data",ss);
			result.put("result", "success");
			result.put("message", "操作成功！");
			
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
	
}