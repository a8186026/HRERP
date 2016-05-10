$(function() {
	
	/* 其他财务信息模块*/
	// 初始化表格
	$("#AccountDg").datagrid({
		method : 'get',
		//url : global_param.context_name + "/basInfo/basAccount/lists", // 数据来源
		url : "",
		title : '财务信息管理', // 标题
		sortName : 'acc_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitColumns : true,
		singleSelect : true, // 单选
		pageSize : 10,
		pageList : [10, 20, 50, 100],
		fit : true,
		//striped : true, // 奇偶行颜色不同
		pagination : false, // 显示分页
		rownumbers : true, // 显示行号
		nowrap : true,
		striped : true, // 奇偶行颜色不同
		queryParams : {},
		columns:[[{
			field : 'acc_name',
			title : '开户名称',
			width : 80,
			sortable : true
		}, {
			field : 'acc_bank',
			title : '开户银行',
			width : 100,
			sortable : true
		}, {
			field : 'acc_no',
			title : '开户账号',
			width : 100,
			sortable : true
		}]],
		toolbar : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : addAccountFunc
		}, {
			text : '修改',
			iconCls : 'icon-modify',
			handler : updateAccountFunc
		}, {
			text : '删除',
			iconCls : 'icon-delete',
			handler : deleteAccountFunc
		} ]
	});
	
	/* 其他证照信息*/
	$("#licenseDg").datagrid({
		method : 'get',
		url : "", // 数据来源
		title : '证照信息管理', // 标题
		sortName : 'license_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitColumns : false,
		singleSelect : true, // 单选
		pageSize : 10,
		pageList : [10, 20, 50, 100],
		fit : true,
		width : '570px',
		height : '600px',
		//striped : true, // 奇偶行颜色不同
		pagination : false, // 显示分页
		rownumbers : true, // 显示行号
		nowrap : true,
		striped : true, // 奇偶行颜色不同
		queryParams : {},
		columns:[[{
			field : 'license_name',
			title : '证照名称',
			width : 80,
			sortable : true
		}, {
			field : 'license_no',
			title : '证照编号',
			width : 80,
			sortable : true
		}, {
			field : 'license_person',
			title : '相关人员',
			width : 80,
			sortable : true
		},  {
			field : 'license_other',
			title : '其他',
			width : 100,
			sortable : true
		}, {
			field : 'license_scope',
			title : '范围',
			width : 100,
			sortable : true
		}, {
			field : 'license_endtime',
			title : '有效期至',
			width : 80,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value);}
		}, {
			field : 'license_issuer',
			title : '发证机关',
			width : 80,
			sortable : true
		}, {
			field : 'license_checkyeartime',
			title : '年检截止',
			width : 80,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value);}
		}, {
			field : 'license_tipdays',
			title : '提示天数',
			width : 80,
			sortable : true
		}, {
			field : 'license_issuer',
			title : '发证机关',
			width : 80,
			sortable : true
		}, {
			field : 'license_remark',
			title : '备注',
			width : 80,
			sortable : true
		}]],
		toolbar : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : addLicenseFunc
		}, {
			text : '修改',
			iconCls : 'icon-modify',
			handler : updateLicenseFunc
		}, {
			text : '删除',
			iconCls : 'icon-delete',
			handler : deleteLicenseFunc
		} ]
	});
	
	/* 其他图片信息*/
	// 初始化表格
	$("#pictureDg").datagrid({
		method : 'get',
		url : "", // 数据来源
		title : '图片信息管理', // 标题
		sortName : 'picture_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitColumns : true,
		singleSelect : true, // 单选
		pageSize : 10,
		pageList : [10, 20, 50, 100],
		fit : true,
		width : '570px',
		height : '800px',
		//striped : true, // 奇偶行颜色不同
		pagination : false, // 显示分页
		rownumbers : true, // 显示行号
		nowrap : true,
		striped : true, // 奇偶行颜色不同
		queryParams : {},
		columns:[[{
			field : 'picture_name',
			title : '图片名称',
			width : 100,
			sortable : true
		}, {
			field : 'picture_position',
			title : '图片位置',
			width : 100,
			sortable : true
		}, {
			field : 'picture_remark',
			title : '图片备注',
			width : 100,
			sortable : true
		}]],
	
		toolbar : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : addPictureFunc
		}, {
			text : '修改',
			iconCls : 'icon-modify',
			handler : updatePictureFunc
		}, {
			text : '删除',
			iconCls : 'icon-delete',
			handler : deletePictureFunc
		} ]
	});
	
	if($("#sup_id").val()!=null&&$("#sup_id").val()!=""){
		
		$('#AccountDg').datagrid('options').url =  global_param.context_name + 
 		"/basInfo/basAccount/lists?acc_type_id="+$("#sup_id").val()+"&acc_type=supply";
 		$('#AccountDg').datagrid('reload');
 		
 		$('#licenseDg').datagrid('options').url =  global_param.context_name + 
 		"/basInfo/basLicenseInfo/lists?license_type_id="+$("#sup_id").val()+"&license_type=supply";
 		$('#licenseDg').datagrid('reload');
 		
 		$('#pictureDg').datagrid('options').url =  global_param.context_name + 
 		"/basInfo/basPictureInfo/lists?picture_type_id="+$("#sup_id").val()+"&picture_type=supply";
 		$('#pictureDg').datagrid('reload');
 	}

	util.select({
		id : 'acc_istaxpayer',
		param : {
			typeCode : 'YN'
		}
	});
	// 初始化其他财务信息编辑窗口
	util.window('editAccountWindow', {
		title : '财务信息'
	});
	
	//初始化其他证照信息编辑窗口
	util.window('editLicenseWindow', {
		title : '证照信息'
	});
	//初始化其他证照信息编辑窗口
	util.window('editPictureWindow', {
		title : '图片信息'
	});
});

//其他财务信息增删改查方法开始

function addAccountFunc() {
	if (!$("#sup_id").val()) {
		util.show("只能对已有的产品添加其他开户信息");
		return;
	}
	util.openWindow('editAccountWindow', "/HRERP/basInfo/basAccount/new?acc_type_id="+$("#sup_id").val()+"&acc_type=supply");
}

function updateAccountFunc() {
	var node = $('#AccountDg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editAccountWindow', "/HRERP/basInfo/basAccount/" + node.acc_id);
}

function deleteAccountFunc() {
	var node = $('#AccountDg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/basInfo/basAccount/" + node.acc_id);
		if (data.result == "success") {
			$('#AccountDg').datagrid('reload');
		}
		util.show(data.message);
	});
}
//END

//其他证照信息增删改查开始

function addLicenseFunc() {
	if (!$("#sup_id").val()) {
		util.show("只能对已有的产品添加其他证照信息");
		return;
	}
	util.openWindow('editLicenseWindow', "/HRERP/basInfo/basLicenseInfo/new?license_type_id="+$("#sup_id").val()+"&license_type=supply");
}

function updateLicenseFunc() {
	var node = $('#licenseDg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editLicenseWindow', "/HRERP/basInfo/basLicenseInfo/" + node.license_id);
}

function deleteLicenseFunc() {
	var node = $('#licenseDg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/basInfo/basLicenseInfo/" + node.license_id);
		if (data.result == "success") {
			$('#licenseDg').datagrid('reload');
		}
		util.show(data.message);
	});
}
//其他图片信息增删改查开始
function addPictureFunc() {
	if (!$("#sup_id").val()) {
		util.show("只能对已有的产品添加其他证照信息");
		return;
	}
	util.openWindow('editPictureWindow', "/HRERP/basInfo/basPictureInfo/new?picture_type_id="+$("#sup_id").val()+"&picture_type=supply");
}

function updatePictureFunc() {
	var node = $('#pictureDg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editPictureWindow', "/HRERP/basInfo/basPictureInfo/" + node.picture_id);
}

function deletePictureFunc() {
	var node = $('#pictureDg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/basInfo/basPictureInfo/" + node.picture_id);
		if (data.result == "success") {
			$('#pictureDg').datagrid('reload');
		}
		util.show(data.message);
	});
}

