$(function() {


	// 初始化表格
	$("#dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/purchase/order/lists", // 数据来源
		title : '订单信息管理', // 标题
		sortName : 'id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitcolumns : true,
		singleSelect : true,//单选
		fit:true,
		pageSize : 10,
		pageList : [10,20,50,100],
		pagination : true,
		rownumbers : true,
		nowrap : true,
		striped : true,
		queryParams : {},
		columns:[[ {
			field : 'ticket_id',
			title : '票号',
			width : 50,
			sortable : true
		},{
			field : 'type',
			title : '种类',
			width : 100,
			sortable : true
		}, {
			field : 'orderDate',
			title : '订货时间',
			width : 100,
			sortable : true,
			
		}, {
			field : 'supply_fullname',
			title : '供方全称',
			width : 100,
			sortable : true,
		}, {
			field : 'personinCharge',
			title : '负责人',
			width : 100,
			sortable : true
		}, {
			field : 'order_storage',
			title : '订货库房',
			width : 100,
			sortable : true
		}]],
		toolbar : [{
			text : '审核',
			iconCls : 'icon-add',
			handler : setPurOrderList
		},{
			text : '修改',
			iconCls : 'icon-modify',
			handler : updatePurOrder
		},{
			text : '删除',
			iconCls : 'icon-delete',
			handler : deletePurOrder
		} ]
	});
	
	// 初始化编辑窗口
	util.window('editWindow', {
		title : '产品档案信息',
		resizable:true,
		width : 650,
		height : 650
	
	}); 
	// 初始化编辑窗口
	util.window('checkWindow', {
		title : '订单详细信息审核',
		resizable:true,
		width : 650,
		height : 650
	
	}); 
});

function query() {
	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#dg').datagrid('reload');
}
function setPurOrderList() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('checkWindow', "order/checkList?ticket_id="+node.ticket_id);
}

function updatePurOrder(){
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editWindow', "order/" + node.id);
}

function deletePurOrder() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/purchase/order/" + node.id);
		if (data.result == "success") {
			$('#dg').datagrid('reload');
		}
		util.show(data.message);
	});
}

