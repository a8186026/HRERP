$(function() {
	// 初始化表格
	util.table('dg', {
		url : global_param.context_name + "/system/setting/settings", // 数据来源
		title : '审核次数设置列表', // 标题
		sortName : 'check_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		queryParams : {},
		columns : [ [ {
			field : 'check_name',
			title : '审核名称',
			width : 10,
			sortable : true
		},{
			field : 'check_code',
			title : '审核编码',
			width : 10,
			sortable : true
		}, {
			field : 'check_times',
			title : '审核次数',
			width : 10,
			sortable : true
		}, {
			field : 'check_type',
			title : '审核类型',
			width : 10,
			formatter:function (value, row, index) {
				return util.getDict('CHECK_TYPE', value);
			}
		}] ],
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
	util.window('editWindow', {
		title : '审核次数设置信息'
	});
	util.window('selectWindow', {
		title : '选择用户'
	});
});

function query() {
	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#dg').datagrid('reload');
}

function addFunc() {
	util.openWindow('editWindow', "setting/new");
}

function updateFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editWindow', "setting/" + node.check_id);
}

function settingFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	var data = util.put(global_param.context_name + "/system/setting/" + node.check_id + "/default");
	if (data.result == "success") {
		$('#dg').datagrid('reload');
	}
	util.show(data.message);
}

function deleteFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/system/setting/" + node.check_id);
		if (data.result == "success") {
			$('#dg').datagrid('reload');
		}
		util.show(data.message);
	});
}
