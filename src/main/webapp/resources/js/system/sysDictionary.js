$(function() {
	// 初始化表格
	util.treegrid('dg', {
		url : global_param.context_name + "/system/dictionary/dictionarys", // 数据来源
		title : '数据字典列表', // 标题
		idField : 'dict_id',
		parentField : 'dict_parent',
		treeField : 'dict_name',
		sortName : 'orders', // 排序的列
		sortOrder : 'asc', // 排序方式
		queryParams : {},
		columns : [ [ {
			field : 'dict_name',
			title : '数据字典名称',
			width : 10,
			sortable : true
		}, {
			field : 'dict_code',
			title : '编码',
			width : 10,
			sortable : true
		}, {
			field : 'dict_sub_value',
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
			text : '增加字典',
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
		title : '数据字典信息'
	});
});

function query() {
	$('#dg').treegrid('options').queryParams = util.formParams("searchForm");
	$('#dg').treegrid('reload');
}

function addFunc() {
	util.openWindow('editWindow', "dictionary/new");
}

function addSubFunc() {
	var node = $('#dg').treegrid('getSelected');
	if (node && !node.dict_sub_id) {
		util.openWindow('editWindow', "dictionary/newSub?dict_parent=" + node.dict_id);
	} else {
		util.show("请选择一条数据字典记录");
	}
}

function updateFunc() {
	var node = $('#dg').treegrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
	} else if (!node.dict_sub_id) {
		util.openWindow('editWindow', "dictionary/" + node.dict_id);
	} else {
		util.openWindow('editWindow', "dictionary/sub/" + node.dict_sub_id);
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
		if (!node.dict_sub_id) {
			data = util.del(global_param.context_name + "/system/dictionary/" + node.dict_id);
		} else {
			data = util.del(global_param.context_name + "/system/dictionary/sub/" + node.dict_sub_id);
		}
		if (data.result == "success") {
			$('#dg').treegrid('reload');
		}
		util.show(data.message);
	});
}