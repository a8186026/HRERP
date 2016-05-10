$(function() {
	var userHabits1 = new userHabits();
	userHabits1.loadColumns("basDepartmentInfoList","dg","editWindow",true,true);
	
	// 初始化表格
	$("#dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/basInfo/basDepartmentInfo/lists", // 数据来源
		title : '部门信息管理', // 标题
		sortName : 'department_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitcolumns : true,
		singleSelect : true,//单选
		pageSize : 10,
		pageList : [10,20,50,100],
		fit : true,
		//triped : true,//奇偶行颜色不同
		pagination : true,
		rownumbers : true,
		nowrap : true,
		striped : true,
		queryParams : {},			
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
		title : '部门信息'

	}); 
	
});

function query() {
	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#dg').datagrid('reload');
}

function addFunc() {
	util.openWindow('editWindow', "basDepartmentInfo/new");
}

function updateFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editWindow', "basDepartmentInfo/" + node.department_id);
}

function deleteFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/basInfo/basDepartmentInfo/" + node.department_id);
		if (data.result == "success") {
			$('#dg').datagrid('reload');
		}
		util.show(data.message);
	});
}

