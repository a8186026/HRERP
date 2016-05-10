/**
 * 载入当前页面以及当前组的控件权限
 * 
 * @author RiverYao
 * @version v1.0
 * @since 2015-06-08
 * */
function pageCtrlPermission(){
	/** 类对象*/
	var obj = new Object();  
	/**需要绑定按键的控件ID数组*/
	obj.ctrlsID = null;
	/** 权限组ID*/
	obj.pageGroupID = null;
	/** 页面名称*/
	obj.pageName = null;
	/** 控件权限列表*/
	obj.ctrlPermission = null;
	
	/**
	 * 根据权限组ID与页面名称为当前控件组的存在权限的控件绑定权限
	 * @param param_ctrlsID 需要绑定权限的控件的ID组
	 * @param param_pageGroupID 用户所属组ID
	 * @param param_pageName 页面名称
	 * */
	obj.bindCtrlPermission = function(param_ctrlsID, param_pageGroupID, param_pageName){
		//获得参数
		obj.ctrlsID = param_ctrlsID;
		obj.pageGroupID = param_pageGroupID;
		obj.pageName = param_pageName;
		
		//验证GroupID是否存在，如果不存在，后台取得登录用户信息获得GroupID
		if(obj.pageGroupID == null || obj.pageGroupID == ""){
			obj.pageGroupID = getGroupIDbyUserSession();
		}

		// 通过pageName与pageGroupID获得权限列表
		if (obj.pageGroupID != null && obj.pageGroupID != ""
				&& obj.pageName != null && obj.pageName != null) {
			obj.ctrlPermission = util.getList(global_param.context_name + "/system/ctrlpermission/getCtrlPermission?pageGroupID="+obj.pageGroupID+"&pageName="+obj.pageName);
			setCtrlPermission(obj.ctrlsID,obj.ctrlPermission);
		}
	};
	
	/**
	 * 根据控件权限捆绑权限
	 * @param param_ctrlsID 需要绑定权限的控件的ID组
	 * @param param_ctrlPermission 控件权限
	 * */
	var setCtrlPermission = function(param_ctrlsID, param_ctrlPermission){
		for(var i = 0 ; i < param_ctrlPermission.length ; i++){
			//验证param_ctrlsID组中是否包含该param_ctrlPermission控件的ID
			if(param_ctrlsID.indexOf(param_ctrlPermission[i].ctrlID)>-1){
				//加载权限
				if(param_ctrlPermission[i].readOnly=="false"){
					$("#"+param_ctrlPermission[i].ctrlID).attr("readonly","readonly");
				}
				if(param_ctrlPermission[i].hidden=="false"){
					$("#"+param_ctrlPermission[i].ctrlDivID).hide();
				}
				if(param_ctrlPermission[i].written=="true"){
					//必填验证
					//如果是输入框validatebox
					if(document.getElementById(param_ctrlPermission[i].ctrlID).className.indexOf("easyui-validatebox")>-1){
						var data_options = $("#"+param_ctrlPermission[i].ctrlID).attr("data-options");
						data_options = "required:true,"+data_options;
						$("#"+param_ctrlPermission[i].ctrlID).attr("data-options",data_options);
					}
				}
			}
		}
	};
	
	/**
	 * 向后台申请数据以获得GroupID
	 * */
	var getGroupIDbyUserSession = function(){
		/* 取得登录用户信息 */
		var usergroup = util.get(global_param.context_name + "/usergroup");
		return usergroup.group_id;
	};
	return obj;
	
}