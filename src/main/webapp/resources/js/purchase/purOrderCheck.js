var checkedlist;

$(function(){
	$("#order_dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/purchase/order/listCheckOrder", // 数据来源
		title : '订单信息管理', // 标题
		sortName : 'id', // 排序的列
		sortOrder : 'asc', // 排序方式
		//fitcolumns : true,
		singleSelect : true,//单选
		//fit:true,
		height:325,
		rownumbers : true,
		nowrap : true,
		striped : true,
		queryParams : {},
		columns:[[ {
			field : 'ticket_id',
			title : '票号',
			width : 100,
			sortable : true
		}, {
			field : 'supply_code',
			title : '供方编码',
			width : 100,
			sortable : true
		}, {
			field : 'supply_fullname',
			title : '供方全称',
			width : 100,
			sortable : true,
		}, {
			field : 'orderabstract',
			title : '摘要',
			width : 100,
			sortable : true
		}, {
			field : 'knotStyle',
			title : '结款方式',
			width : 100,
			sortable : true
		}, {
			field : 'carryMode',
			title : '承运方式',
			width : 100,
			sortable : true
		}, {
			field : 'carryCompany',
			title : '承运单位',
			width : 150,
			sortable : true
		}, {
			field : 'orderTransMode',
			title : '运输方式',
			width : 100,
			sortable : true
		}, {
			field : 'delivaryPlace',
			title : '发运地点',
			width : 100,
			sortable : true
		}, {
			field : 'orderDate',
			title : '订货时间',
			width : 150,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value);
			}
		}, {
			field : 'orderDepartureTime',
			title : '启运时间',
			width : 150,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value);
			}
		}, {
			field : 'arrivalDate',
			title : '到货时间',
			width : 150,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value);
			}
		}, {
			field : 'checkStatus',
			title : '审批状态',
			width : 100,
			sortable : true,
			formatter: function(value, row, index){
				if(value == 0){
					return "<font color='green'>已审核</font>";
				}else if(value == 1){
					return "<font color='red'>未审核</font>";
				}
			}
		
		}]],
		toolbar : [{
			text : '审核',
			iconCls : 'icon-add',
			handler : checkPurOrder
		},{
			text : '修改',
			iconCls : 'icon-modify',
			handler : updatePurOrder
		},{
			text : '删除',
			iconCls : 'icon-delete',
			handler : deletePurOrder
		}],
		onClickRow:function(index,data){
			checkedlist = data;
			$('#orderList_dg').datagrid('options').url = global_param.context_name + "/purchase/orderList/listCheckOrderList";
			$('#orderList_dg').datagrid('options').queryParams.ticket_id = data.ticket_id;
			$('#orderList_dg').datagrid('reload');
		}
	});
	
	$("#orderList_dg").datagrid({
		method : 'get',
		//url : global_param.context_name + "/purchase/orderList/lists", // 数据来源
 		title : '订单详细信息管理', // 标题
		sortName : 'id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitcolumns : true,
		singleSelect : true,//单选
		fit:true,
		rownumbers : true,
		nowrap : true,
		striped : true,
		queryParams : {
			ticket_id:'',//'${ticket_id}',
		},
		columns:[[ {
			field : 'ticket_id',
			title : '票号',
			width : 150,
			sortable : true
		}, {
			field : 'product_commonname',
			title : '通用名',
			width : 150,
			sortable : true
		}, {
			field : 'quantity',
			title : '数量',
			width : 200,
			sortable : true,
		}, {
			field : 'unitprice',
			title : '单价',
			width : 200,
			sortable : true,
		}, {
			field : 'discount',
			title : '折扣',
			width : 200,
			sortable : true,
			formatter: function(value, row, index){
				return (row.settlementPrice/row.unitprice)*100;
			}
		}, {
			field : 'settlementPrice',
			title : '结算价',
			width : 200,
			sortable : true,
		}, {
			field : 'sum',
			title : '订单总金额',
			width : 200,
			sortable : true
		}]],
		toolbar : [{
			text : '修改',
			iconCls : 'icon-modify',
			handler : updatePurOrderList
		},{
			text : '删除',
			iconCls : 'icon-delete',
			handler : deletePurOrderList
		} ]
	});
	
	util.window('editWindow', {
		title : '修改订货信息',
		resizable:true,
		width : 650,
		height : 250

	}); 
	
	util.window('editPurOrderWindow', {
		title : '修改订货信息',
	}); 
});


function query() {
	$('#order_dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#order_dg').datagrid('reload');
	$('#orderList_dg').datagrid('loadData', { total: 0, rows: [] });
}
function checkPurOrder(){
	var node = $('#order_dg').datagrid('getSelected');
	
	if(!node){
		util.show("请选择一条记录");
	}
	else if(node.checkStatus == 0){
		util.show("此记录已审核");
	}
	else{
		util.confirm("确认审核通过此订单？", function() {
			var data = util.get(global_param.context_name + "/purchase/check/pass?id=" + node.id);
			if (data.result == "success") {
				$('#order_dg').datagrid('reload');
				$('#orderList_dg').datagrid('loadData',{total:0,rows:[]});
			}
			util.show(data.message);
		});
	}
}
function updatePurOrder(){
	var node = $('#order_dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条大单记录");
		return;
	}
	if(node.checkStatus == 0){
		util.show("订单已通过审核，无法对其进行操作!");
		return;
	}
	util.openWindow('editPurOrderWindow', "/HRERP/purchase/order/check/" + node.id);
}

function deletePurOrder(){
	var node = $('#order_dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条大单记录！");
		return;
	}
	if(node.checkStatus == 0){
		util.show("订单已通过审核，无法对其进行操作!");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/purchase/order/" + node.id);
		if (data.result == "success") {
			$('#order_dg').datagrid('reload');
			
			//list_dg 表格reload不成功
			$('#orderList_dg').datagrid('loadData', { total: 0, rows: [] });
			
		}
		util.show(data.message);
	});
}

function submitOrderForm() {
	var data = util.submit('_formPurOrder');
	if (data) {
		if (data.result == "success") {
			util.show(data.message);
			$('#order_dg').datagrid('reload');
			util.closeWindow('editPurOrderWindow');
		} else {
			util.error(data.message);
		}
	}
}


function updatePurOrderList(){
	var node = $('#orderList_dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条小单记录！");
		return;
	}
	if(checkedlist.checkStatus == 0){
		util.show("订单已通过审核，无法对其进行操作!");
		return;
	}
	util.openWindow('editWindow', "/HRERP/purchase/check/" + node.id);
}

function deletePurOrderList(){
	var node = $('#orderList_dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条小单记录！");
		return;
	}
	if(checkedlist.checkStatus == 0){
		util.show("订单已通过审核，无法对其进行操作!");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data=$('#orderList_dg').datagrid('getData');
		if(data.rows.length==1){
			util.confirm("若该条记录被删除，其所属的大单也将被删除，是否删除？",function(){
					 var data = util.del(global_param.context_name + "/purchase/check/" + node.id);
						if (data.result == "success") {
							$('#orderList_dg').datagrid('reload');
							$('#order_dg').datagrid('reload');
							util.show(data.message);
						}
			   }
			);
		}else{
	  var data = util.del(global_param.context_name + "/purchase/check/" + node.id);
		if (data.result == "success") {
			$('#orderList_dg').datagrid('reload');
			$('#order_dg').datagrid('reload');
			util.show(data.message);
		}
		}
	});

}


