package com.goldcow.emanage.log.web;

/**
 * 日志记录管理
 * 
 * @author YuechenWANG
 * @version v1.0
 * @since 2015-9-24
 */

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.log.service.ISupPhotosManageService;
import com.goldcow.emanage.util.gen.entity.SupInfoManage;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;


@Controller
@RequestMapping(value = "/log/supphotosmanagecontroller")
public class SupPhotosManageController {
	

	
	
	@Autowired
	protected ISupPhotosManageService  iSupPhotosManageService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "SupPhotosManageController", GlobalVal.MENU_FUNCTION.VIEW)) {
			
			System.out.println("hello SupPhotosManageController");
			return "log/logMenuPermissionCheck/SupPhotosManage.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	
	/**
	 * 
	 * @param request  将前台的ajax等路径传入的参数进行获取 暂时无需咋iweb.xml中配置相关内容，前人已经配置完成
	 * @return
	 */
	@RequestMapping(value = "/getStampFileUrl.do", method = RequestMethod.GET)
	@ResponseBody
	public String getStampFileUrl(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "SupPhotosManageController", GlobalVal.MENU_FUNCTION.VIEW)) {
			
			String searchId = request.getParameter("searchId");
			String userOption = request.getParameter("userOption");
			
			
			SupInfoManage supInfoManage = new SupInfoManage();
			supInfoManage.setSup_code(searchId);
			
			System.out.println(searchId);
			
			List<SupInfoManage> supInfoManageRecv = iSupPhotosManageService.list(supInfoManage);
		
		
			SupInfoManage FileUrl = supInfoManageRecv.get(0);
			
			String fileUrlString;
			if ("sup_goodticketphoto".equals(userOption)) {
				fileUrlString = FileUrl.getSup_goodticketphoto();
			}
			
			else {
				fileUrlString = FileUrl.getSup_sealmoldphoto();
			} 
			
			
			
			System.out.println(fileUrlString);
			
			
			System.out.println("hello getStampFileUrl"+searchId+" "+userOption);
			
			return fileUrlString;
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

}
