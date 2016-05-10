package com.goldcow.emanage.basInfo.web;

import java.util.ArrayList;
import java.util.Date;
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

import com.goldcow.emanage.basInfo.service.IBasCheckProductService;
import com.goldcow.emanage.product.service.IProInfoManageService;
import com.goldcow.emanage.system.service.ISysCheckSettingService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.BasCheck;
import com.goldcow.emanage.util.gen.entity.ProInfoManage;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil; 
import com.google.common.collect.Maps;

/**
 * 产品审批
 * 
 * @author Chenyuxuan
 * @version v1.0
 * @since 2015-6-5
 */
@Controller
@RequestMapping(value = "/basInfo/basCheckProduct")
public class BasCheckProductController {
	/** 产品审批信息信息管理服务 */
	@Autowired
	protected IBasCheckProductService checkProductService;
	@Autowired
	protected IProInfoManageService proInfoManageService;
	@Autowired
	protected ISysCheckSettingService sysCheckService;
	
	
	/**
	 * 产品审批信息管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 产品审批信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request,String type,Model model) {
		if (SysUtil.hasRight(request, "basCheckProduct", GlobalVal.MENU_FUNCTION.VIEW)) {
			SysUtil.getToken(request);
			model.addAttribute("type", SysTools.decode(type));
			return "basInfo/basCheckProduct/basCheckProductList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}	

	/**
	 * 查询所有可供审批的产品
	 * 
	 * @param request HttpServletRequest
	 * @return 产品list
	 */
	@RequestMapping(value = "chooseProduct", method = RequestMethod.POST)
	@ResponseBody
	public List<ProInfoManage> list(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "basCheckProduct", GlobalVal.MENU_FUNCTION.VIEW)) {
			return checkProductService.getProducts();
		} else
			return new ArrayList<ProInfoManage>();

	}
	
	/**
	 * 查询产品id为具体某值的审批结果
	 * 
	 * @param request HttpServletRequest
	 * @param id 产品id
	 * @param type 审批类型（产品、供方、销方）
	 * @param checktype 审批方式（平行、流水）
	 * @return 产品list
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> checklist(HttpServletRequest request,String id,String type) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "basCheckProduct", GlobalVal.MENU_FUNCTION.VIEW)) {	
			BasCheck bascheck = new BasCheck();
			bascheck.setCheck_type_id(Integer.parseInt(type));
			bascheck.setCheck_comment_id(id);
			/*
			Integer count;	//当前已经审批的条目数
			
			if(Integer.parseInt(checktype) == 0)	//流水审批
			{
				count = checkProductService.count(bascheck);
			}else
				count = -1;							//平行审批
			result.put("count", count);
			*/
			result.put("method", "POST");
			result.put("formUrl","/basInfo/basCheckProduct/new");
			result.put("list", checkProductService.getProductsCheck(bascheck));
			return result;
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 新增产品审批信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 产品审批项
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, BasCheck bean,Integer type,Integer lastId,Integer checkType) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "basCheckProduct", GlobalVal.MENU_FUNCTION.UPDATE)) {

			
			if(bean.getCheck_result()==3&&checkType==0){//流水审批且该条记录取消审核
				System.out.println("取消审核");
				BasCheck lastBean = checkProductService.getById(lastId);
				lastBean.setCheck_modified(1);
				checkProductService.update(lastBean, request);
				
				if(bean.getCheck_id() != null){
					checkProductService.delete(bean.getCheck_id(), request);
				}
			}else{
				//判断是新增还是修改
				if (bean.getCheck_id() == null) {
					System.out.println("新增");
					checkProductService.add(bean, request);
				}
				else{
					System.out.println("修改");
					bean.setCheck_modified(0);
					checkProductService.update(bean, request);
				}
			}
			
			BasCheck bascheck = new BasCheck();
			bascheck.setCheck_type_id(bean.getCheck_type_id());
			bascheck.setCheck_comment_id(bean.getCheck_comment_id().substring(0, bean.getCheck_comment_id().indexOf("_")));
						
			List<BasCheck> checklist = checkProductService.getProductsCheck(bascheck);
			ProInfoManage probean = new ProInfoManage();
			probean.setProduct_id(new Integer(bean.getCheck_comment_id().substring(0, bean.getCheck_comment_id().indexOf("_"))));
			
			//设置该产品的审核状态，0表示审核完成，1表示审核中，2表示审核不通过
			if(bean.getCheck_result() == 2)
				probean.setProduct_check(2);
			else if(checklist.size()<sysCheckService.getById(2).getCheck_times())
				probean.setProduct_check(1);
			else
				probean.setProduct_check(0);
			System.out.println("bean.getCheck_person_name():"+bean.getCheck_person_name());
			probean.setProduct_checkperson(bean.getCheck_person_name());
			probean.setProduct_checktime(new Date());
			proInfoManageService.update(probean, request);
			
			
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
		if (SysUtil.hasRight(request, "basCheckProduct", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除药品信息
			checkProductService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}

}
	
