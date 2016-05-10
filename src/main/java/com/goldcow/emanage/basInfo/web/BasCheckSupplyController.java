package com.goldcow.emanage.basInfo.web;

import java.util.ArrayList;
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

import com.goldcow.emanage.basInfo.service.IBasCheckSupplyService;
import com.goldcow.emanage.supply.service.ISupInfoManageService;
import com.goldcow.emanage.system.service.ISysCheckSettingService;
import com.goldcow.emanage.system.service.ISysGroupService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.BasCheck;
import com.goldcow.emanage.util.gen.entity.ProInfoManage;
import com.goldcow.emanage.util.gen.entity.SupInfoManage;
import com.goldcow.emanage.util.gen.entity.SysGroup;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil; 
import com.google.common.collect.Maps;

/**
 * 供方审批
 * 
 * @author Yuanxin
 * @version v1.0
 * @since 2015-6-4
 */
@Controller
@RequestMapping(value = "/basInfo/basCheckSupply")
public class BasCheckSupplyController {
	/** 厂家档案信息信息管理服务 */
	@Autowired
	protected IBasCheckSupplyService checkSupplyService;
	@Autowired
	protected ISupInfoManageService supInfoManageService;
	@Autowired
	protected ISysGroupService sysGroupService;
	@Autowired
	protected ISysCheckSettingService sysCheckService;
	
	
	/**
	 * 厂家档案信息管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 厂家档案信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request,String type,Model model) {
		if (SysUtil.hasRight(request, "basCheckSupply", GlobalVal.MENU_FUNCTION.VIEW)) {
			SysUtil.getToken(request);
			model.addAttribute("type", SysTools.decode(type));
			return "basInfo/basCheckSupply/basCheckSupplyList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询厂家档案信息信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "chooseSupply", method = RequestMethod.POST)
	@ResponseBody
	public List<SupInfoManage> list(HttpServletRequest request, Model model,SupInfoManage bean,BasCheck basCheckBean) {
		if (SysUtil.hasRight(request, "basCheckSupply", GlobalVal.MENU_FUNCTION.VIEW)) {
			 bean.setSup_check(1);//0-已审核 1-未审核 2-未通过
			
			List<SupInfoManage> list=new ArrayList<SupInfoManage>();
			System.out.println("#############"+bean.getRows());
			list=supInfoManageService.lists(bean);
			
			
		//	return supInfoManageService.list(bean);
			return list;
		} else {
			
			return null;
		}
	}
	/**
	 * 得到审批记录
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "getCheckComment", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getCheck(HttpServletRequest request,Integer id,String type,Model model) {
		if (SysUtil.hasRight(request, "basCheckProduct", GlobalVal.MENU_FUNCTION.VIEW)) {	
		Map<String, Object> result = Maps.newHashMap();
		BasCheck bascheck = new BasCheck();
		bascheck.setCheck_type_id(Integer.parseInt(type));
		bascheck.setCheck_comment_id(id.toString());
		Integer count;
		/*if(checkType==0){//如果为流水审核，查出当前审核的环节
			count = checkSupplyService.count(bascheck);
			System.out.println("当前审核的环节:"+checkSupplyService.count(bascheck));
		}
		else{
			count = -1;
		}
		result.put("count",count);*/
		result.put("method", "POST");
		result.put("formUrl","/basInfo/basCheckSupply/new");
		result.put("list", checkSupplyService.lists(bascheck));
		return result;
	} else {
		return Maps.newHashMap();
	}
	}
		
	
	
	/**
	 * 新增厂家档案信息
	 *  
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 新增厂家档案信息页面路径
	 */
	@RequestMapping(value = "parameter", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> newMenu(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "basCheckSupply", GlobalVal.MENU_FUNCTION.VIEW)) {			
			BasCheck basCheck = new BasCheck(); 
			Map<String, Object> map = Maps.newHashMap();
			SysGroup sysGroup =new SysGroup();
			sysGroup = sysGroupService.getUserGroup(request);
			map.put("groupId", sysGroup.getGroup_id());
			map.put("basCheck", basCheck);
			map.put("method", "POST");
			map.put("formUrl", "/basInfo/basCheckSupply/new");
			
			SysUtil.getToken(request);
			System.out.println("basCheck:"+map);
			return map;
		} else {
			return null;
		}
	}
	
	/**
	 * 新增厂家档案信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 厂家档案信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, BasCheck bean,Integer type,Integer lastId,Integer checkType ) {
	
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "basCheckSupply", GlobalVal.MENU_FUNCTION.UPDATE)) {

		
			if(bean.getCheck_result()==3&&checkType==0){//流水审批且该条记录取消审核
		
				BasCheck lastBean = checkSupplyService.get(lastId);
				lastBean.setCheck_modified(1);
				checkSupplyService.update(lastBean, request);
				if(bean.getCheck_id() != null){
					checkSupplyService.delete(bean.getCheck_id(), request);
				}
				
			}else{
			   
				//判断是新增还是修改
				if (bean.getCheck_id() == null) 
					checkSupplyService.add(bean, request);
				else{
					bean.setCheck_modified(0);
					checkSupplyService.update(bean, request);
				}
			}
			
			BasCheck bascheck = new BasCheck();
			bascheck.setCheck_type_id(bean.getCheck_type_id());
			bascheck.setCheck_comment_id(bean.getCheck_comment_id().substring(0, bean.getCheck_comment_id().indexOf("_")));
			
			List<BasCheck> checklist = checkSupplyService.lists(bascheck);
			SupInfoManage supbean = new SupInfoManage();
			supbean.setSup_id(new Integer(bean.getCheck_comment_id().substring(0, bean.getCheck_comment_id().indexOf("_"))));
			
			//设置该产品的审核状态，0表示审核完成，1表示审核中，2表示审核不通过
			if(bean.getCheck_result() == 2)
				supbean.setSup_check(2);
			else if(checklist.size()<sysCheckService.getById(2).getCheck_times())
				supbean.setSup_check(1);
			else
				supbean.setSup_check(0);
			supInfoManageService.update(supbean, request);
         
			    result.put("bascheck", bean);			
				result.put("result", "success");
				result.put("message", "操作成功！");
			} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
		
	
}