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
 * 会员卡积分操作
 * 
 * @author YuanXin
 * @version v1.0
 * @since 2015-10-20
 */
@Controller
@RequestMapping(value = "/member/memPointManage")
public class MemPointManageController {

	@Autowired
	protected IMemPointManageService memPointManageService;
	@Autowired
	protected IMemCardManageService memCardManageService;
	/**
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 追加积分页面路径
	 */
	@RequestMapping(value = "addPoint", method = RequestMethod.GET)
	public String addPoint(HttpServletRequest request, Model model,Integer point,String card) {
		if (SysUtil.hasRight(request, "memPointManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			
			MemPointManage memPointManage = new MemPointManage();
			memPointManage.setMem_card_number(card);
			MemCardManage memCardManage = new MemCardManage();
			memCardManage = memCardManageService.getMemCardByNumber(card);
			model.addAttribute("memCardManage",memCardManage);
			model.addAttribute("memPointManage",memPointManage);
			//model.addAttribute("point",point);
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/member/memPointManage/add");
			SysUtil.getToken(request);
			return "/member/memPointManage/memPointManageAdd.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	/**
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 消减积分页面路径
	 */
	@RequestMapping(value = "reducePoint", method = RequestMethod.GET)
	public String reducePoint(HttpServletRequest request, Model model,Integer point,String card) {
		if (SysUtil.hasRight(request, "memPointManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			
			MemPointManage memPointManage = new MemPointManage();
			memPointManage.setMem_card_number(card);
			MemCardManage memCardManage = new MemCardManage();
			memCardManage = memCardManageService.getMemCardByNumber(card);
			model.addAttribute("memCardManage",memCardManage);
			model.addAttribute("memPointManage",memPointManage);
			//model.addAttribute("point",point);
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/member/memPointManage/reduce");
			SysUtil.getToken(request);
			return "/member/memPointManage/memPointManageReduce.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 追加积分
	 * 
	 * @param request HttpServletRequest
	 * @param bean  MemPointManage 
	 * @return 操作结果
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request,MemPointManage bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "memPointManage", GlobalVal.MENU_FUNCTION.UPDATE)) {
			
			bean.setStatus(0);//加积分状态码
			memPointManageService.add(bean, request);
		
			MemCardManage memCardManage = new MemCardManage();
			memCardManage=memCardManageService.getMemCardByNumber(bean.getMem_card_number());
			
			memCardManage.setMem_card_addPoint(memCardManage.getMem_card_addPoint()+bean.getMem_increasePoint());
			memCardManageService.update(memCardManage, request);
			
			
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	/**
	 * 消减积分
	 * 
	 * @param request HttpServletRequest
	 * @param bean  MemPointManage 
	 * @return 操作结果
	 */
	@RequestMapping(value = "reduce", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> reduce(HttpServletRequest request,MemPointManage bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "memPointManage", GlobalVal.MENU_FUNCTION.UPDATE)) {
			
			bean.setStatus(1);//减积分状态码
			memPointManageService.add(bean, request);
			
			MemCardManage memCardManage = new MemCardManage();
			memCardManage=memCardManageService.getMemCardByNumber(bean.getMem_card_number());
			System.out.println("_________________________________"+memCardManage.getMem_card_addPoint());
			System.out.println("_________________________________"+bean.getMem_reducePoint());
			memCardManage.setMem_card_addPoint(memCardManage.getMem_card_addPoint()-bean.getMem_reducePoint());
			memCardManageService.update(memCardManage, request);
			
			
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
	
	
	
	
}