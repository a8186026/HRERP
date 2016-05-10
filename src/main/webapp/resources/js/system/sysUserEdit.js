//所有控件ID
var ctrlsID = [ "user_name", "password", "display_name", "sex", "tel", "email", "address"];
//确认BUTTON
var sumbitButtonID = "submitUser";
//由于JS加载顺序的问题，聚焦到第一序列
var ctrlNumber = 0;


$(function() {
	// 性别下拉框
	util.select({
		id : 'sex',
		required : true,
		param : {
			typeCode : 'SEX'
		}
	});
	
	var keyPress = new pageKeyPress();
	keyPress.bindKeyPressToCtrl(ctrlsID, sumbitButtonID);
	
	
	var ctrlPermission = new pageCtrlPermission();
	ctrlPermission.bindCtrlPermission(ctrlsID,"","sysUserEdit");
	
	$('#depart_id').combobox({
		url : global_param.context_name + "/basInfo/basDepartmentInfo/getList",
		method : 'get',
		valueField : 'department_id',
		textField : 'department_fullname',
		editable:false,
	});

});





function submitForm() {
	var data = util.submit('_form');
	if (data) {
		if (data.result == "success") {
			util.show(data.message);
			$('#userdg').datagrid('reload');
			util.closeWindow('userEditWindow');
		} else {
			util.error(data.message);
		}
	}
}


