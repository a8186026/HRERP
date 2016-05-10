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

import com.goldcow.emanage.accept.service.IPurAcceptRejectService;
import com.goldcow.emanage.util.gen.entity.BasAccount;
import com.goldcow.emanage.util.gen.entity.PurAcceptReject;
import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 收货拒收处理
 * 
 * @author zhanxiaotong
 * @since15-11-27
 * @version v1.0
 */
@Controller
@RequestMapping(value = "/purchase/acceptReject")
public class PurAcceptRejectController {
	
	@Autowired
	protected IPurAcceptRejectService acceptRejectService;
	
	
	/**
	 * 页面跳转
	 * @param request HttpServletRequest
	 * @return 页面路径
	 * 
	 * */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String viewPurOrder(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "accept/acceptReject/acceptRejectList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	/**
	 * 确认收货——查询大单下的所有小单信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, PurAcceptReject bean) {
		if (SysUtil.hasRight(request, "purAcceptReject", GlobalVal.MENU_FUNCTION.VIEW)) {
			return acceptRejectService.lists(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 查询所有产品信息
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @param callback 回调方法
	 * @param proContent 查询参数
	 * @return 供方信息页面路径
	 */
	@RequestMapping(value = "viewProList", method = RequestMethod.GET)
	public String viewProList(HttpServletRequest request, Model model, String callback, String proContent) {
		if (SysUtil.hasRight(request, "purAcceptReject", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("callback", callback);
			model.addAttribute("proContent", proContent);
			return "accept/acceptReject/acceptRejectViewProList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	
	/**
	 * 添加拒收信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 入库信息ID
	 * @param bean 入库信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> updatePass(HttpServletRequest request,String data) {
		if(data==null || data==""){
			return null;
		}
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "purAcceptReject", GlobalVal.MENU_FUNCTION.UPDATE)) {
			
			acceptRejectService.add(data, request);

			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	/**
	 * 修改拒收信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 拒收信息ID
	 * @param model Model
	 * @return 查看拒收信息页面路径
	 */
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, Integer reject_id, Model model) {
		if (SysUtil.hasRight(request, "purAcceptReject", GlobalVal.MENU_FUNCTION.VIEW)) {
			System.out.println("----------------------come in------------" + reject_id);
			PurAcceptReject purAcceptReject = acceptRejectService.getById(reject_id);
			model.addAttribute("purAcceptReject", purAcceptReject);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/purchase/acceptReject/update/"+ reject_id);
			return "accept/acceptReject/acceptRejectEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}



	/**
	 * 修改拒收信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 拒收信息ID
	 * @param bean 拒收信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id , PurAcceptReject bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "purAcceptReject", GlobalVal.MENU_FUNCTION.UPDATE)) {
				// 修改拒收信息
			acceptRejectService.update(bean, request);

			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}
	/**
	 * 删除订单收货确认信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的订单收货确认信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "purAcceptReject", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除订单收货确认信息
			acceptRejectService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
	/**
	 * 获取登陆用户名字
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的订单收货确认信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "getUserName", method = RequestMethod.GET)
	@ResponseBody
	public String getUserName(HttpServletRequest request) {
		String user_name = null;
		SysUser user = SysUtil.getLoginUser(request);
		user_name = user.getDisplay_name();
		return user_name;
	}
	
	/**
	 * 添加拒收信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 入库信息ID
	 * @param bean 入库信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "submit", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> submit(HttpServletRequest request,Integer reject_id) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "purAcceptReject", GlobalVal.MENU_FUNCTION.UPDATE)) {
			
			acceptRejectService.submit(reject_id, request);

			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
	/**
	 * 获取最大票号
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的订单收货确认信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "getReject_ticket", method = RequestMethod.GET)
	@ResponseBody
	public String getReject_ticket(HttpServletRequest request) {
		String maxTicket = acceptRejectService.getMaxRejectTicket();
		return maxTicket;
	}
}