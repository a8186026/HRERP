package com.goldcow.emanage.dept.web;

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

import com.goldcow.emanage.basInfo.service.IBasDepartmentInfoService;
import com.goldcow.emanage.dept.persist.DeptPlanManageDao;
import com.goldcow.emanage.dept.service.IDeptPlanManageService;
import com.goldcow.emanage.product.persist.ProInfoManageDao;
import com.goldcow.emanage.product.service.IProInfoManageService;
import com.goldcow.emanage.system.service.ISysUserService;
import com.goldcow.emanage.util.gen.entity.BasDepartmentInfo;
import com.goldcow.emanage.util.gen.entity.DeptPlanManage;
import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.emanage.util.gen.entity.valueObject.dept.ProStockInfoVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 调货计划
 * 
 * @author 战晓桐
 * @version v1.0
 * @since 2015-10-30
 */
@Controller
@RequestMapping(value = "/dept/plan")
public class DeptPlanManageController {
	/** 调货计划管理 */
	@Autowired
	protected IDeptPlanManageService deptPlanManageService;
	@Autowired
	protected IProInfoManageService proInfoManageService;
	@Autowired
	protected ISysUserService sysUserService;
	@Autowired
	protected IBasDepartmentInfoService basDepartmenService;
	@Autowired
	protected DeptPlanManageDao dao;
	@Autowired
	protected ProInfoManageDao proInfoManageDao;
	
	
	/**
	 * 调货计划管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 调货计划页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "deptPlanManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "/dept/deptPlanManage/deptPlanManageList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询调货计划
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, DeptPlanManage bean) {
		if (SysUtil.hasRight(request, "deptPlanManage", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return deptPlanManageService.lists(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	/**
	 * 查询门店库存信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "stockInfoList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> stockInfoList(HttpServletRequest request,ProStockInfoVO bean) {
		if (SysUtil.hasRight(request, "deptPlanManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			//获取当前user
			SysUser user = sysUserService.getByUserId(SysUtil.getLoginUserId(request));
			//获取当前user所在库房
			Integer deptID = user.getDepart_id();
			bean.setStock_storage(deptID);
			return deptPlanManageService.stockInfoList(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 新增调货计划
	 * 
	 * @param request HttpServletRequest
	 * @param bean 调货计划
	 * @return 操作结果
	 */
	@RequestMapping(value = "addPlan", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request,String data) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "deptPlanManage", GlobalVal.MENU_FUNCTION.UPDATE)) {
			// 新增调货计划
			deptPlanManageService.add(data, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}	
	/**
	 * 在searchForm手动新增调货计划
	 * 
	 * @param request HttpServletRequest
	 * @param bean 调货计划
	 * @return 操作结果
	 */
	@RequestMapping(value = "addPlanManual", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> addPlanManual(HttpServletRequest request,String data) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "deptPlanManage", GlobalVal.MENU_FUNCTION.UPDATE)) {
			// 新增调货计划
			deptPlanManageService.addPlanManual(data, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}	
	
	/**
	 * 修改调货计划页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 调货计划ID
	 * @param model Model
	 * @return 查看调货计划页面路径
	 */
	@RequestMapping(value = "update", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, String data) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "deptPlanManage", GlobalVal.MENU_FUNCTION.UPDATE)) {
			// 审核调货计划
			deptPlanManageService.update(data, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	/**
	 * 删除调货计划
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的调货计划ID
 	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "deptPlanManage", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除调货计划
			deptPlanManageService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
	/**
	 * 审核调货计划页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 调货计划ID
	 * @param model Model
	 * @return 查看调货计划页面路径
	 */
	@RequestMapping(value = "check", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> check(HttpServletRequest request, Integer deptPlan_id) {
		DeptPlanManage bean = dao.get(deptPlan_id);
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		
		if (SysUtil.hasRight(request, "deptPlanManage", GlobalVal.MENU_FUNCTION.UPDATE)) {
			// 审核调货计划
			
			deptPlanManageService.check(bean, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
	/**
	 * 返回user对应的库房
	 * @return 验证结果
	 * 
	 * @author zhanxiaotong
	 * @since 2015-10-20
	 */
	@RequestMapping(value = "getApplyDept", method = RequestMethod.GET)
	@ResponseBody
	public List<BasDepartmentInfo> getDepartmentList(HttpServletRequest request, BasDepartmentInfo bean) {
		List<BasDepartmentInfo> list = null;
		SysUser user = sysUserService.getByUserId(SysUtil.getLoginUserId(request));
		Integer deptID = user.getDepart_id();
		bean.setDepartment_id(deptID);
		if (SysUtil.hasRight(request, "basDepartmentInfo", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return basDepartmenService.getList(bean);
		} else {
			return Lists.newArrayList();
		}
	}

	/**
	 * 查看申请产品是否已经存在by ProId
	 * 
	 * @param request HttpServletRequest
	 * @param id 系统参数子项ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "checkPro", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> checkPro(HttpServletRequest request,Integer product_id) {
		System.out.println("---------------- deptPlan_pro_id=" + product_id + "------------------");
		Map<String, Object> result = Maps.newHashMap();
		List<DeptPlanManage> list = dao.getBeanByProID(product_id);
		System.out.println("---------------- list.size()=" + list.size() + "------------------");
		if (list.size() < 1) {
			//如果查到 则表示该产品已经申请过啦
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}//getidbyCode

	/**
	 * 查看申请产品是否存在by procode
	 * 
	 * @param request HttpServletRequest
	 * @param id 系统参数子项ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "checkProByCodeInPro", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> checkProByCodeInPro(HttpServletRequest request,String product_code) {
		
		Map<String, Object> result = Maps.newHashMap();
		Integer product_id = 0;
		product_id = proInfoManageDao.getIDByProCode(product_code);
		System.out.println("---------------- product_id=" + product_id + "------------------");
		if(product_id != null){
			//如果查到 则表示该产品已经申请过啦
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}
	
	/**
	 * 查看申请产品是否存在在计划中by procode
	 * 
	 * @param request HttpServletRequest
	 * @param id 系统参数子项ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "checkProByCodeInDeptPlan", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> checkProByCodeInDeptPlan(HttpServletRequest request,String product_code) {
		
		Map<String, Object> result = Maps.newHashMap();
		Integer product_id = 0;
		product_id = proInfoManageDao.getIDByProCode(product_code);
		result = checkPro(request,product_id);
		return result;
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
		if (SysUtil.hasRight(request, "purchase", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("callback", callback);
			model.addAttribute("proContent", proContent);
			return "/dept/deptPlanManage/deptViewProList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
}