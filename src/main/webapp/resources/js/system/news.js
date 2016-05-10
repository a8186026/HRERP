$(function() {
	// 初始化表格
	util.table('dg', {
		url : global_param.context_name + '/system/notice/notices', // 数据来源
		title : '公告管理', // 标题
		sortName : 'news_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		queryParams : {},
		columns : [ [ {
			title : '公告标题',
			field : 'news_title',
			width : 50,
			align : 'center',
			sortable : false
		},{
			title : '来源',
			field : 'author',
			width : 30,
			align : 'center',
			sortable : false
		}, {
			title : '公告时间',
			field : 'news_time',
			width : 20,
			align : 'center',
			sortable : false
		}, {
			title : '公告类别',
			field : 'news_type',
			width : 20,
			align : 'center',
			sortable : false,
			formatter : function (value, row, index) {
				return util.getDict('NEWS_TYPE', value);
			}
		}, {
			title : '发布状态',
			field : 'status',
			width : 10,
			align : 'center',
			sortable : false,
			formatter : function (value, row, index) {
				return util.getDict('CHECK_STATUS', value);
			}
		}] ],
		toolbar : [ {
			text : '新增',
			iconCls : 'icon-add',
			handler : addNews
		},'-',{
			text : '修改',
			iconCls : 'icon-modify',
			handler : updateNews
		},'-', {
			text : '删除',
			iconCls : 'icon-delete',
			handler : deleteNews
		},'-', {
			text : '发布',
			iconCls : 'icon-ok',
			handler : enableFunc
		},'-', {
			text : '取消发布',
			iconCls : 'icon-no',
			handler : disableFunc
		} ]
	});
	// 初始化编辑窗口
	util.window('editWindow', {
		title : '公告管理'
	});
	util.select({
		id : 'search_news_type',
		param : {
			typeCode : 'NEWS_TYPE'
		}
	});
});

function query() {
	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#dg').datagrid('reload');
}

function addNews() {
	util.openWindow('editWindow', "notice/new");
}

function updateNews() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editWindow', "notice/" + node.news_id);
}

function deleteNews() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/system/notice/" + node.news_id);
		if (data.result == "success") {
			$('#dg').datagrid('reload');
		}
		util.show(data.message);
	});
}
//发布,取消发布
function enableFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	var data = util.put(global_param.context_name + "/system/notice/" + node.news_id + "/enable");
	if (data.result == "success") {
		$('#dg').datagrid('reload');
	}
	util.show(data.message);
}

function disableFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	var data = util.put(global_param.context_name + "/system/notice/" + node.news_id + "/disable");
	if (data.result == "success") {
		$('#dg').datagrid('reload');
	}
	util.show(data.message);
}