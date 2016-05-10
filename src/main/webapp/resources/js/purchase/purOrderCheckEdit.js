

$(function() {
	//日期框初始化(如果不注册则无法使用keypress来实现控件跳转)
	$('#orderDepartureTime').datebox({    
	    required:false,   
	});
	
	//日期框初始化(如果不注册则无法使用keypress来实现控件跳转)
	$('#arrivalDate').datebox({    
	    required:false,   
	});	
	
	/* 
	 * 下拉菜单数据初始化
	 */
	//摘要
	$('#orderabstract').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=600",
		method :"get",
		valueField : "cbs_chn",
		textField : "cbs_chn"
	});
	//结款方式
	$('#knotStyle').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=604",
		method :"get",
		valueField : "cbs_chn",
		textField : "cbs_chn"
	});
	//政策形式
	$('#policyStyle').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=611",
		method :"get",
		valueField : "cbs_chn",
		textField : "cbs_chn"
	});
	//承运方式
	$('#carryMode').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=615",
		method :"get",
		valueField : "cbs_chn",
		textField : "cbs_chn"
	});
	//运输方式
	$('#orderTransMode').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=619",
		method :"get",
		valueField : "cbs_chn",
		textField : "cbs_chn"
	});
	
	var keyPress = new pageKeyPress();
	keyPress.bindKeyPressToCtrl(ctrlsID, sumbitButtonID);

	
});