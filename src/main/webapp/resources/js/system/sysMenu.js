$(function() {
	// 初始化表格
	util.treegrid('dg', {
		url : global_param.context_name + "/system/menu/menus", // 数据来源
		title : '菜单列表', // 标题
		idField : 'menu_id',
		parentField : 'parent_menu',
		treeField : 'menu_name',
		sortName : 'orders', // 排序的列
		sortOrder : 'asc', // 排序方式
		queryParams : {},
		columns : [ [ {
			field : 'menu_name',
			title : '菜单名称',
			width : 10,
			sortable : true
		}, {
			field : 'menu_code',
			title : '编码',
			width : 10,
			sortable : true
		}, {
			field : 'menu_url',
			title : '菜单链接',
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
		title : '菜单信息'
	});
});

function query() {
	$('#dg').treegrid('options').queryParams = util.formParams("searchForm");
	$('#dg').treegrid('reload');
}

function addFunc() {
	var node = $('#dg').treegrid('getSelected');
	var parent_menu = "";
	if (node) {
		parent_menu = node.menu_id;
	}
	util.openWindow('editWindow', "menu/new?parent_menu=" + parent_menu);
}

function updateFunc() {
	var node = $('#dg').treegrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editWindow', "menu/" + node.menu_id);
}

function deleteFunc() {
	var node = $('#dg').treegrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/system/menu/" + node.menu_id);
		if (data.result == "success") {
			$('#dg').treegrid('reload');
		}
		util.show(data.message);
	});
}