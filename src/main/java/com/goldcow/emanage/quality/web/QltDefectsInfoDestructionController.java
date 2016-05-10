package com.goldcow.emanage.quality.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.quality.service.IQltDefectsInfoService;
import com.goldcow.emanage.util.gen.entity.valueObject.quality.QltDefectsInfoVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 不合格品种信息确定
 * 
 * @author gaoxiang
 * @since 2015-11-30
 */

@Controller
@RequestMapping(value = "/quality/destruction")
public class QltDefectsInfoDestructionController {
	/** 不合格品种信息管理服务 */
	@Autowired
	protected IQltDefectsInfoService qltDefectsInfoService;
	
	/**
	 * 不合格品种信息管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 不合格品种信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "qltDefectsInfoVO", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "/quality/defectsInfo/qltDefectsInfoDestructionList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询不合格品种信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listDefectsInfoVO(HttpServletRequest request, QltDefectsInfoVO bean) {
		if (SysUtil.hasRight(request, "qltDefectsInfoVO", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return qltDefectsInfoService.list(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 查看不合格品种信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 不合格品种信息ID
	 * @param model Model
	 * @return 查看不合格品种信息页面路径
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "qltDefectsInfoVO", GlobalVal.MENU_FUNCTION.VIEW)) {
			QltDefectsInfoVO qltDefectsInfoVO = qltDefectsInfoService.getById(id);
			model.addAttribute("qltDefectsInfoVO", qltDefectsInfoVO);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/quality/destruction/" + id);
			SysUtil.getToken(request);
			return "/quality/defectsInfo/qltDefectsInfoDestructionEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 获取不合格品种销毁票号
	 *  
	 * @param request HttpServletRequest
	 * @return 不合格品种销毁票号
	 */
	@RequestMapping(value = "getMaxDefectsDestructionTicket", method = RequestMethod.GET)
	@ResponseBody
	public String getDefectsTicket(HttpServletRequest request) {
		String  defects_destrucTicketNo = "";
		if (SysUtil.hasRight(request, "qltDefectsInfo", GlobalVal.MENU_FUNCTION.UPDATE)) {
			//根据不合格品种销毁票号
			defects_destrucTicketNo = qltDefectsInfoService.getMaxDefectsDestructionTicket();
		}
		return defects_destrucTicketNo;
	}

	/**
	 * 修改不合格品种销毁信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 不合格品种信息ID
	 * @param bean 不合格品种信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> destruction(HttpServletRequest request, @PathVariable("id") Integer id, QltDefectsInfoVO bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "qltDefectsInfoVO", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				
				// 修改不合格品种信息
				qltDefectsInfoService.destruction(bean, request);

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
	 * 删除不合格品种信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的不合格品种信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "qltDefectsInfo", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除不合格品种信息
			qltDefectsInfoService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
}