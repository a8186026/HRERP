package com.goldcow.emanage.member.web;

import java.util.Map;




import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.member.service.IMemCardManageService;
import com.goldcow.emanage.member.service.IMemPointManageService;
import com.goldcow.emanage.util.gen.entity.MemCardManage;
import com.goldcow.emanage.util.gen.entity.MemPointManage;
import com.goldcow.emanage.util.gen.entity.valueObject.MemCard.MemCardVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 会员卡
 * 
 * @author wubin
 * @version v1.0
 * @since 2015-6-12
 */
@Controller
@RequestMapping(value = "/member/memCardManage")
public class MemCardManageController {
	/** 药品管理服务 */

	@Autowired
	protected IMemCardManageService memCardManageService;
	@Autowired
	protected IMemPointManageService memPointManageService;

	/**
	 * 会员卡信息管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 会员卡管理页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "memCardManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "member/memCardManage/memCardManageList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询会员卡信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> list(HttpServletRequest request, MemCardManage bean) {
		if (SysUtil.hasRight(request, "memCardManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			return memCardManageService.list(bean);
		} else {
			return Maps.newHashMap(); 
		}
	}

	/**
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 新增会员卡页面路径
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newMenu(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "memCardManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			MemCardManage memCardManage = new MemCardManage();
			model.addAttribute("memCardManage",memCardManage);
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/member/memCardManage/new");
			SysUtil.getToken(request);
			return "/member/memCardManage/memCardManageEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	
	/**
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 批量添加会员卡页面路径
	 */
	@RequestMapping(value = "addMembers", method = RequestMethod.GET)
	public String addMembers(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "memCardManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/member/memCardManage/addMembers");
			SysUtil.getToken(request);
			return "/member/memCardManage/addMembersEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 修改会员卡信息
	 *  
	 * @param request HttpServletRequest
	 * @return 查看会员卡信息页面路径
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "memCardManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			MemCardManage memCardManage = memCardManageService.getById(id);
			model.addAttribute("memCardManage", memCardManage);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/member/memCardManage/" + id);
			SysUtil.getToken(request);
			return "/member/memCardManage/memCardManageEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 新增会员卡
	 * 
	 * @param request HttpServletRequest
	 * @param bean 会员卡信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, MemCardManage bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "memCardManage", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 新增会员卡
				memCardManageService.add(bean, request);
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
	 * 批量增加会员卡
	 * 
	 * @param request HttpServletRequest
	 * @param bean 会员卡VO
	 * @return 操作结果
	 */
	@RequestMapping(value = "addMembers", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addMembers(HttpServletRequest request, MemCardVO bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "memCardManage", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 新增会员卡
				memCardManageService.addMembers(bean, request);
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
	 * 修改会员卡信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 会员卡信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, MemCardManage bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "memCardManage", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 修改会员卡信息
				memCardManageService.update(bean, request);

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
	 * 删除会员卡信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的会员卡信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "memCardManage", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除会员卡信息
			memCardManageService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
	/**
	 * 启用会员卡
	 * 
	 * @param request HttpServletRequest
	 * @param id 会员卡ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}/enable", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> enable(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "memCardManage", GlobalVal.MENU_FUNCTION.UPDATE)) {
			// 删除用户
			memCardManageService.enable(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}

	/**
	 * 停用会员卡
	 * 
	 * @param request HttpServletRequest
	 * @param id 会员卡ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}/disable", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> disable(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "memCardManage", GlobalVal.MENU_FUNCTION.UPDATE)) {
			// 删除用户
			memCardManageService.disable(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
	/**
	 * 会员卡查重
	 * 
	 * @param request HttpServletRequest
	 * @param id 会员卡号
	 * @return 操作结果 true 可用，false不可用
	 */
	@RequestMapping(value = "checkMemCardID", method = RequestMethod.GET)
	@ResponseBody
	public Boolean checkMemCardID(HttpServletRequest request, String CardId) {
		if(memCardManageService.getMemCardByNumber(CardId)==null){
			return true;
		}else{
			return false;
		}
	}
	
	
	/**
	 * 会员卡信息查询
	 * 
	 * @param request HttpServletRequest
	 * @param id 会员卡号
	 * @return 会员卡信息
	 */
	@RequestMapping(value = "getMemCardByNumber", method = RequestMethod.GET)
	@ResponseBody
	public MemCardManage getMemCardByNumber(HttpServletRequest request, String mem_card_number) {
		return memCardManageService.getMemCardByNumber(mem_card_number);
	}

	
}