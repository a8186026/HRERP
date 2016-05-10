$(function() {
	//第一个参数页面ID,第二个参数datagrid ID，第三个参数配置显示框ID,第四个参数是否启动上下键换行，第五个参数是否启动列拖拽
	//loadColumns("basLicenseInfoList","dg","editWindow",true,true);
	
	// 初始化表格
	$("#ethicalPersonDg").datagrid({
		method : 'get',
		url : global_param.context_name + "/basInfo/ethicalPerson/lists", // 数据来源
		title : '处方药人员信息管理', // 标题
		sortName : 'ethical_personId', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitColumns : true,
		singleSelect : true, // 单选
		pageSize : 10,
		pageList : [10, 20, 50, 100],
		fit : true,
		//striped : true, // 奇偶行颜色不同
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		nowrap : true,
		striped : true, // 奇偶行颜色不同
		queryParams : {},
		columns:[[{
			field : 'ethical_personId',
			title : '处方药人员ID',
			width : 80,
			sortable : true
		}, {
			field : 'ethical_personCode',
			title : '处方药人员编号',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '处方药人员姓名',
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
	util.window('editWindow', {
		title : '处方药人员信息',
		width:600,
		height:200
	});	
});

function query() {
	$('#ethicalPersonDg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#ethicalPersonDg').datagrid('reload');
}
function addFunc() {
	util.openWindow('editWindow', "/HRERP/basInfo/ethicalPerson/new");
}

function updateFunc() {
	var node = $('#ethicalPersonDg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editWindow', "/HRERP/basInfo/ethicalPerson/" + node.ethical_personId);
}

function deleteFunc() {
	var node = $('#ethicalPersonDg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/basInfo/ethicalPerson/" + node.ethical_personId);
		if (data.result == "success") {
			$('#ethicalPersonDg').datagrid('reload');
		}
		util.show(data.message);
	});
}