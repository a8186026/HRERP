$(function() {
	// 初始化表格
	$("#licenseDg").datagrid({
		method : 'get',
		url : global_param.context_name + "/basInfo/basLicenseInfo/lists", // 数据来源
		title : '证照信息管理', // 标题
		sortName : 'license_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitColumns : true,
		singleSelect : true, // 单选
		pageSize : 10,
		pageList : [10, 20, 50, 100],
		fit : true,
		width : '570px',
		height : '600px',
		//striped : true, // 奇偶行颜色不同
		pagination : true, // 显示分页
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
			field : 'license_starttime',
			title : '认证时间',
			width : 80,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value);}
		}, {
			field : 'license_endtime',
			title : '有效期至',
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
		}, {
			field : 'license_other',
			title : '其他',
			width : 100,
			sortable : true
		}]],
		toolbar : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : addFunc
		}, {
			text : '修改',
			iconCls : 'icon-modify',
			handler : updateFunc
		}, {
			text : '删除',
			iconCls : 'icon-delete',
			handler : deleteFunc
		} ]
	});
	// 初始化编辑窗口
	util.window('editLicenseWindow', {
		title : '证照信息'
	});
	
});

function query() {
	$('#licenseDg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#licenseDg').datagrid('reload');
}

function addFunc() {
	util.openWindow('editLicenseWindow', "/HRERP/basInfo/basLicenseInfo/new");
}

function updateFunc() {
	var node = $('#licenseDg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editLicenseWindow', "basLicenseInfo/" + node.license_id);
}

function deleteFunc() {
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