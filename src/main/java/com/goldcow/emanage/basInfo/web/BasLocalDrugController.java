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

import com.goldcow.emanage.basInfo.service.IBasLocalDrugService;
import com.goldcow.emanage.util.gen.entity.BasDrugInfo;
import com.goldcow.emanage.util.gen.entity.TCatalog;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 地区药监信息
 * 
 * @author wangqi
 * @version v1.0
 * @since 2015-7-9
 */
@Controller
@RequestMapping(value = "/basInfo/basLocalDrug")
public class BasLocalDrugController {
	/** 地区药监信息管理服务 */
	@Autowired
	protected IBasLocalDrugService localDrugService;	

	/**
	 * 查询地区药监信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, TCatalog bean) {
		if (SysUtil.hasRight(request, "basLocalDrug", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return localDrugService.list(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
}