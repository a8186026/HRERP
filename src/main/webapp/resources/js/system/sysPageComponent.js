$(function() {
	// 初始化表格
	util.table('pagedg', {
		url : global_param.context_name + "/system/pageComponent/pages", // 数据来源
		title : '页面列表', // 标题
		sortName : 'id', // 排序的列
		sortOrder : 'asc', // 排序方式
		queryParams : {},
		columns : [ [ {
			field : 'page_name',
			title : '页面名称',
			width : 20,
			sortable : true
		}, {
			field : 'page_jsp',
			title : 'jsp名称',
			width : 20,
			sortable : true
			
		} ] ],
		toolbar : [ {
			
			text : '增加',
			iconCls : 'icon-add',
			handler : addPage
		}, {
			text : '修改',
			iconCls : 'icon-modify',
			handler : updatePage
		}, {
			text : '删除',
			iconCls : 'icon-delete',
			handler : deletePage
	
		} ]
	});
	util.table('componentsdg', {
		url : "", // 数据来源
		title : '控件列表', // 标题
		sortName : 'id', // 排序的列
		sortOrder : 'asc', // 排序方式
		queryParams : {},
		columns : [ [ {
			field : 'ctrl_name',
			title : '控件名称',
			width : 25,
			sortable : true
		}, {
			field : 'ctrl_id',
			title : '控件id',
			width : 20,
			sortable : true
	
		}, {
			field : 'ctrl_div_id',
			title : '控件所属div的id',
			width : 20,
			sortable : true
		}, {
			field : 'ctrl_type',
			title : '控件类型',
			width : 20,
			sortable : true
			
		} ] ],
		toolbar : [ {
			
			text : '增加',
			iconCls : 'icon-add',
			handler : addCom
		}, {
			text : '修改',
			iconCls : 'icon-modify',
			handler : updateCom
		}, {
			text : '删除',
			iconCls : 'icon-delete',
			handler : deleteCom
	
		} ]
	});
	// 初始化编辑窗口
	util.window('pageEditWindow', {
		title : '页面'
	});
	util.window('componentsEditWindow', {
		title : '控件'
	});
	$('#pagedg').datagrid({
		onClickRow:function(index,data){
			$('#searchComForm').form('reset');
			queryCom();
			var url=global_param.context_name + "/system/pageComponent/components";
			$('#componentsdg').datagrid('options').url=url+"/"+data.id;
			$('#componentsdg').datagrid('reload');
			
		}
	
	});
});

function query() {
	$('#pagedg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#pagedg').datagrid('reload');
}

function queryCom() {
	var a =util.formParams("searchComForm");
	$('#componentsdg').datagrid('options').queryParams = util.formParams("searchComForm");
	$('#componentsdg').datagrid('reload');
}

function addPage() {
	util.openWindow('pageEditWindow', "pageComponent/newPage");
}

function addCom() {
	var node = $('#pagedg').treegrid('getSelected');
	if (!node) {
		util.show("请选择一条页面记录");
		return;
	}
	util.openWindow('componentsEditWindow', "pageComponent/newCom/"+node.id);
}


function updatePage() {
	var node = $('#pagedg').treegrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
	
	} else {
		util.openWindow('pageEditWindow', "pageComponent/updatePage/" + node.id);
	}
}

function updateCom() {
	var node = $('#componentsdg').treegrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
	} else {
		util.openWindow('componentsEditWindow', "pageComponent/updateCom/" + node.id);
	}
}

function deletePage() {
	var node = $('#pagedg').treegrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("此操作将会删除该页面下所有控件信息，确定要执行删除操作？", function() {
		var data;
			 data=util.del(global_param.context_name + "/system/pageComponent/delPage/" + node.id);
		
		if (data.result == "success") {
			$('#pagedg').datagrid('reload');
			$('#componentsdg').datagrid('reload');
		}
		util.show(data.message);
	});
}
function deleteCom() {
	var node = $('#componentsdg').treegrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data;
			data = util.del(global_param.context_name + "/system/pageComponent/delCom/" + node.id);
		if (data.result == "success") {
			$('#componentsdg').datagrid('reload');
		}
		util.show(data.message);
	});
}