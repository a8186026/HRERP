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

import com.goldcow.emanage.basInfo.service.IBasDepartmentInfoService;
import com.goldcow.emanage.product.persist.ProInfoManageDao;
import com.goldcow.emanage.product.persist.ProStockPriceDao;
import com.goldcow.emanage.product.service.IProInfoManageService;
import com.goldcow.emanage.product.service.IProStockPriceService;
import com.goldcow.emanage.util.gen.entity.ProInfoManage;
import com.goldcow.emanage.util.gen.entity.StockPrice;
import com.goldcow.emanage.util.gen.entity.BasDepartmentInfo;
import com.goldcow.emanage.util.gen.entity.valueObject.product.ProStockPriceVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 组号信息
 * 
 * @author zhanxiaotong
 * @version v1.0
 * @since 2015-10-22
 */
@Controller
@RequestMapping(value = "/basInfo/basGroupNoInfo")
public class BasGroupNoInfoController {
	
	@Autowired
	protected IProStockPriceService stockPriceServiceService;
	@Autowired
	protected IBasDepartmentInfoService departmentInfoService;
	@Autowired
	protected IProInfoManageService proInfoManageService;
	@Autowired
	protected ProInfoManageDao proInfoManageDao;
	@Autowired
	protected ProStockPriceDao stockPriceDao;
	/**
	 * 组号信息查询页面
	 * 
	 * @param request HttpServletRequest
	 * @return 组号信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "proStockPriceVO", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "basInfo/basGroupNoInfo/basGroupNoInfoList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 组号信息list
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, ProStockPriceVO bean) {
		if (SysUtil.hasRight(request, "proStockPriceVO", GlobalVal.MENU_FUNCTION.VIEW)) {	
			
			BasDepartmentInfo dpm = departmentInfoService.getById(bean.getDepartment_id());//获取要查询的库房id
			if (dpm != null){
				Integer dpm_number = dpm.getDepartment_id();//id不为空时，查出id对应的库房号
				//插入查询条件
				bean.setDepartment_id(dpm_number);//库房号
				bean.setPro_group_no(bean.getPro_group_no());//组号
				return stockPriceServiceService.lists(bean);
			}
		} 
		return Maps.newHashMap();
	}
	
	/**
	 * 新增组号信息
	 *  
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 新增组号信息页面路径
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newMenu(HttpServletRequest request, Model model) {//因为要多表查询显示，所以使用ProStockPriceVO，是StockPrice的子类。
		if (SysUtil.hasRight(request, "proStockPriceVO", GlobalVal.MENU_FUNCTION.VIEW)) {
			ProStockPriceVO proStockPriceVO = new ProStockPriceVO(); 
			model.addAttribute("proStockPriceVO", proStockPriceVO);
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/basInfo/basGroupNoInfo/new");
			return "basInfo/basGroupNoInfo/basGroupNoInfoEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 新增组号信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 组号信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, ProStockPriceVO bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "proStockPriceVO", GlobalVal.MENU_FUNCTION.UPDATE)) {
			// 新增组号信息
			stockPriceServiceService.add(bean, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		
		return result;
	}
	
	

	/**
	 * 查看组号信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 组号信息ID
	 * @param model Model
	 * @return 查看组号信息页面路径
	 */
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, Integer prod_id,Integer department_id,Integer product_id, Model model) {
		if (SysUtil.hasRight(request, "proStockPriceVO", GlobalVal.MENU_FUNCTION.VIEW)) {
			//不能用类型强制转换，代码不报错，但是运行时报错。
			StockPrice stockP = stockPriceServiceService.getById(prod_id);
			ProStockPriceVO proStockPriceVO = new ProStockPriceVO();
			//所以下面只能手动set一下更新了的属性。
			proStockPriceVO.setProd_id(prod_id);
			proStockPriceVO.setProduct_id(product_id);
			proStockPriceVO.setPro_group_no(stockP.getPro_group_no());
			proStockPriceVO.setPro_location_no(stockP.getPro_location_no());                                                                                                                                                                                     
			proStockPriceVO.setDepartment_id(department_id);
			
			model.addAttribute("proStockPriceVO", proStockPriceVO);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/basInfo/basGroupNoInfo/update");
			return "basInfo/basGroupNoInfo/basGroupNoInfoEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}


	
	/**
	 * 修改组号信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 组号信息ID
	 * @param bean 组号信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "update", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, ProStockPriceVO bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "proStockPriceVO", GlobalVal.MENU_FUNCTION.UPDATE)) {
				// 修改组号信息
			stockPriceServiceService.update(bean, request);

			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}

	/**
	 * 删除组号信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的组号信息ID
 	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "stockPrice", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除组号信息
			stockPriceServiceService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
	/**
	 * 返回库房信息列表
	 * @return 验证结果
	 * 
	 * @author zhanxiaotong
	 * @since 2015-10-20
	 */
	@RequestMapping(value = "getDepartmentList", method = RequestMethod.GET)
	@ResponseBody
	public List<BasDepartmentInfo> getDepartmentList(HttpServletRequest request, BasDepartmentInfo bean) {
		if (SysUtil.hasRight(request, "basDepartmentInfo", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return departmentInfoService.getList(bean);
		} else {
			return Lists.newArrayList();
		}
	}
	
	/**
	 * 返回产品信息列表
	 * @return 验证结果
	 * 
	 * @author zhanxiaotong
	 * @since 2015-10-20
	 */
	@RequestMapping(value = "getProINfoList", method = RequestMethod.GET)
	@ResponseBody
	public List<ProInfoManage> getProINfoList(HttpServletRequest request, ProInfoManage bean) {
		if (SysUtil.hasRight(request, "proInfoManage", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return proInfoManageDao.list(bean,null);
		} else {
			return Lists.newArrayList();
		}
	}
	
	/**
	 * 获取组号list，用于组号的combobox
	 * @return 操作结果
	 * 
	 *  @author zhanxiaotong
	 * @since 2015-10-20
	 */
	@RequestMapping(value = "groupNOList", method = RequestMethod.GET) 
	@ResponseBody
	public List<StockPrice> groupNOList(HttpServletRequest request,Integer department_id) {
		if(department_id == null){
			return null;
		}
		if (department_id > 0) {
		
			
			StockPrice stockP = new StockPrice();
			stockP.setDepartment_id(department_id);
			
			List<StockPrice> stockPriceList = stockPriceDao.lists(stockP);
			return stockPriceList;

		}
		return null;
	}
	
	/**
	 * 通过库房id查到库房number
	 * 
	 * @return 库房number
	 * 
	 *  @author zhanxiaotong
	 * @since 2015-10-26
	 */
	public String getDnumByDid (Integer id){
		
		BasDepartmentInfo department = departmentInfoService.getById(id);
		String dept_num = department.getDepartment_number();
		return dept_num;
		
	}
	
	/**
	 * 通过产品id查到产品code
	 * 
	 * @return product_code
	 * 
	 *  @author zhanxiaotong
	 * @since 2015-10-29
	 */
	public String getCodeByProId(Integer product_id) {
		ProInfoManage proInfoMng = proInfoManageService.getById(product_id);
		String code = proInfoMng.getProduct_code();
		return code;
	}
}