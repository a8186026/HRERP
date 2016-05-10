package com.goldcow.emanage.system.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.util.gen.entity.SysCtrlPermissionCtrl;
import com.goldcow.emanage.util.gen.entity.SysMenu;
import com.goldcow.emanage.util.system.SysCtrlPermission.SysCtrlPermission;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 权限组控件级别权限管理
 * 
 * @author RiverYao
 * @version v1.0
 * @since 2014-10-15
 */
@Controller
@RequestMapping(value = "/system/ctrlpermission")
public class SysCtrlPermissionController {
	/**
	 * 权限组控件级别权限管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 权限组控件级别权限管理页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "ctrlpermission", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "system/sysCtrlPermission/sysCtrlPermission.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 新增用户组控件权限
	 * 
	 * @param request HttpServletRequest
	 * @param pageGroupID 用户组ID
	 * @param pageName 页面JSP名
	 * @param ctrlPermissions 控件权限字符串
	 * @return 操作结果
	 */
	@RequestMapping(value = "saveCtrlPermission", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveCtrlPermission(HttpServletRequest request,String pageGroupID,String pageName ,String ctrlPermissions) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysCtrlPermission", GlobalVal.MENU_FUNCTION.UPDATE)) {
				//封装CtrlList
			  	List<SysCtrlPermissionCtrl> ctrlList = new ArrayList<SysCtrlPermissionCtrl>();
				String[] ctrls = ctrlPermissions.split(";");
				for(int i = 0 ; i< ctrls.length ;i++){
					if(ctrls[i]!=null &&!ctrls[i].equals("")){
						String[] params = ctrls[i].split(",");
						SysCtrlPermissionCtrl ctrl = new SysCtrlPermissionCtrl();
						if(!ctrl.createCtrl(params)){
							System.out.println("控件权限读取异常!");
							result.put("result", "failure");
							result.put("message", "控件权限读取异常!");
							return result;
						}
						ctrlList.add(ctrl);
					}
				}
				//获取工程下的upload目录，用来保存文件
				String fileDirPath = request.getSession().getServletContext().getRealPath("/");
				fileDirPath += "\\upload\\CtrlPermissionXml\\"+"pageGroupID_"+pageGroupID;
				File dirPath = new File(fileDirPath);
		        //判断目录是否存在，不存在则创建目录
		        if (!dirPath.exists()) {
		            dirPath.mkdirs();
		        }
				String filePath = fileDirPath+"\\"+pageGroupID+"_"+pageName+".xml";
				//调用工具类
				if(SysCtrlPermission.CreateXMLPermissionForCtrls(filePath, pageName, pageGroupID, ctrlList)){
					
					result.put("result", "success");
					result.put("message", "操作成功！");
				}else{
					result.put("result", "failure");
					result.put("message", "控件权限存储失败!");
				}
		} else {
			result.put("result", "failure");
			result.put("message", "控件权限读取异常!");
		}
		return result;
	}
	
	/**
	 * 查询用户组控件权限XML文件，并封装为数组
	 * 
	 * @param request HttpServletRequest
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value = "getCtrlPermission", method = RequestMethod.GET)
	@ResponseBody
	public List<SysCtrlPermissionCtrl> getCtrlPermission(HttpServletRequest request,String pageGroupID,String pageName) {
		if (SysUtil.hasRight(request, "sysCtrlPermission", GlobalVal.MENU_FUNCTION.VIEW)) {
			//构建文件路径
			String fileDirPath = request.getSession().getServletContext().getRealPath("/");
			fileDirPath += "\\upload\\CtrlPermissionXml\\"+"pageGroupID_"+pageGroupID;
			String filePath = fileDirPath+"\\"+pageGroupID+"_"+pageName+".xml";
			List<SysCtrlPermissionCtrl> ctrllist =  SysCtrlPermission.ReadXMLPermissionForCtrls(filePath, pageName, pageGroupID);
			if(ctrllist!=null){
				return ctrllist;
			} else {
				return Lists.newArrayList();
			}
		} else {
			return Lists.newArrayList();
		}
	}

}