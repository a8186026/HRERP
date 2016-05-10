$(function() {
	// 初始化表格
	util.treegrid('dg', {
		url : global_param.context_name + "/system/parameter/parameters", // 数据来源
		title : '系统参数列表', // 标题
		idField : 'param_id',
		parentField : 'param_parent',
		treeField : 'param_name',
		sortName : 'orders', // 排序的列
		sortOrder : 'asc', // 排序方式
		queryParams : {},
		columns : [ [ {
			field : 'param_name',
			title : '系统参数名称',
			width : 10,
			sortable : true
		}, {
			field : 'param_code',
			title : '编码',
			width : 10,
			sortable : true
		}, {
			field : 'param_sub_value',
			title : '值',
			width : 10,
			sortable : true
		}, {
			field : 'orders',
			title : '顺序',
			width : 10,
			sortable : true
		}, {
			field : 'note',
			title : '备注',
			width : 10,
			sortable : true
		} ] ],
		toolbar : [ {
			text : '增加参数',
			iconCls : 'icon-add',
			handler : addFunc
		}, {
			text : '增加子项',
			iconCls : 'icon-add',
			handler : addSubFunc
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
		title : '系统参数信息'
	});
});

function query() {
	$('#dg').treegrid('options').queryParams = util.formParams("searchForm");
	$('#dg').treegrid('reload');
}

function addFunc() {
	util.openWindow('editWindow', "parameter/new");
}

function addSubFunc() {
	var node = $('#dg').treegrid('getSelected');
	if (node && !node.param_sub_id) {
		util.openWindow('editWindow', "parameter/newSub?param_parent=" + node.param_id);
	} else {
		util.show("请选择一条系统参数记录");
	}
}

function updateFunc() {
	var node = $('#dg').treegrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
	} else if (!node.param_sub_id) {
		util.openWindow('editWindow', "parameter/" + node.param_id);
	} else {
		util.openWindow('editWindow', "parameter/sub/" + node.param_sub_id);
	}
}

function deleteFunc() {
	var node = $('#dg').treegrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data;
		if (!node.param_sub_id) {
			data = util.del(global_param.context_name + "/system/parameter/" + node.param_id);
		} else {
			data = util.del(global_param.context_name + "/system/parameter/sub/" + node.param_sub_id);
		}
		if (data.result == "success") {
			$('#dg').treegrid('reload');
		}
		util.show(data.message);
	});
}