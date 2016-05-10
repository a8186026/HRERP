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

import com.goldcow.emanage.basInfo.service.IBasCheckSaleService;
import com.goldcow.emanage.sale.service.ISalInfoManageService;
import com.goldcow.emanage.system.service.ISysCheckSettingService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.BasCheck;
import com.goldcow.emanage.util.gen.entity.SalInfoManage;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 销方审批
 * 
 * @author wangqi
 * @version v1.0
 * @since 2015-07-14
 */
@Controller
@RequestMapping(value = "/basInfo/basCheckSale")
public class BasCheckSaleController {
	/** 销方审批信息信息管理服务 */
	@Autowired
	protected IBasCheckSaleService checkSaleService;
	@Autowired
	protected ISalInfoManageService salInfoManageService;
	@Autowired
	protected ISysCheckSettingService sysCheckService;
	
	
	/**
	 * 销方审批信息管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 销方审批信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request,String type,Model model) {
		if (SysUtil.hasRight(request, "basCheckSale", GlobalVal.MENU_FUNCTION.VIEW)) {
			SysUtil.getToken(request);
			model.addAttribute("type", SysTools.decode(type));
			return "basInfo/basCheckSale/basCheckSaleList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}	

	/**
	 * 查询所有可供审批的销方
	 * 
	 * @param request HttpServletRequest
	 * @return 销方list
	 */
	@RequestMapping(value = "chooseSale", method = RequestMethod.POST)
	@ResponseBody
	public List<SalInfoManage> list(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "basCheckSale", GlobalVal.MENU_FUNCTION.VIEW)) {
			return checkSaleService.getSales();
		} else
			return new ArrayList<SalInfoManage>();

	}
	
	/**
	 * 查询销方id为具体某值的审批结果
	 * 
	 * @param request HttpServletRequest
	 * @param id 销方id
	 * @param type 审批类型（产品、供方、销方）
	 * @param checktype 审批方式（平行、流水）
	 * @return 销方list
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> checklist(HttpServletRequest request,String id,String type) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "basCheckSale", GlobalVal.MENU_FUNCTION.VIEW)) {	
			BasCheck bascheck = new BasCheck();
			bascheck.setCheck_type_id(Integer.parseInt(type));
			bascheck.setCheck_comment_id(id);

			result.put("method", "POST");
			result.put("formUrl","/basInfo/basCheckSale/new");
			result.put("list", checkSaleService.getSalesCheck(bascheck));
			return result;
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 新增销方审批信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 销方审批项
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, BasCheck bean,Integer type,Integer lastId,Integer checkType) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "basCheckSale", GlobalVal.MENU_FUNCTION.UPDATE)) {

			
			if(bean.getCheck_result()==3&&checkType==0){//流水审批且该条记录取消审核
				System.out.println("取消审核");
				BasCheck lastBean = checkSaleService.getById(lastId);
				lastBean.setCheck_modified(1);
				checkSaleService.update(lastBean, request);
				
				if(bean.getCheck_id() != null){
					checkSaleService.delete(bean.getCheck_id(), request);
				}
			}else{
				//判断是新增还是修改
				if (bean.getCheck_id() == null) {
					System.out.println("新增");
					checkSaleService.add(bean, request);
				}
				else{
					System.out.println("修改");
					bean.setCheck_modified(0);
					checkSaleService.update(bean, request);
				}
			}
			
			BasCheck bascheck = new BasCheck();
			bascheck.setCheck_type_id(bean.getCheck_type_id());
			bascheck.setCheck_comment_id(bean.getCheck_comment_id().substring(0, bean.getCheck_comment_id().indexOf("_")));
						
			List<BasCheck> checklist = checkSaleService.getSalesCheck(bascheck);
			SalInfoManage salbean = new SalInfoManage();
			salbean.setSal_id(new Integer(bean.getCheck_comment_id().substring(0, bean.getCheck_comment_id().indexOf("_"))));
			
			//设置该销方的审核状态，0表示审核完成，1表示审核中，2表示审核不通过
			if(bean.getCheck_result() == 2)
				salbean.setSal_check(2);
			else if(checklist.size()<sysCheckService.getById(2).getCheck_times())
				salbean.setSal_check(1);
			else
				salbean.setSal_check(0);
			salInfoManageService.update(salbean, request);
			
			
			result.put("bascheck", bean);			
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}		
		return result;
	}
	
	/**
	 * 删除审批项
	 * 
	 * @param request HttpServletRequest
	 * @param id 审批id
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "basCheckSale", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除药品信息
			checkSaleService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
}
	
