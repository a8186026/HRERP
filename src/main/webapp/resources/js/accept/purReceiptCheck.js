//记录orderList_dg选中的行，以便行切换的时候关闭当前行编辑
var currentEdit = 0;
$(function(){
	$("#order_dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/purchase/order/listRecepitOrder", // 数据来源
		title : '订单信息管理', // 标题
		sortName : 'id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitcolumns : true,
		singleSelect : true,//单选
		fit:true,
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
			width : 80,
			sortable : true
		}, {
			field : 'knotStyle',
			title : '结款方式',
			width : 80,
			sortable : true
		}, {
			field : 'carryMode',
			title : '承运方式',
			width : 80,
			sortable : true
		}, {
			field : 'carryCompany',
			title : '承运单位',
			width : 80,
			sortable : true
		}, {
			field : 'orderTransMode',
			title : '运输方式',
			width : 80,
			sortable : true
		}, {
			field : 'delivaryPlace',
			title : '发运地点',
			width : 80,
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
			width : 80,
			sortable : true,
			formatter: function(value, row, index){
				if(value == 0){
					return "<font color='green'>已审核</font>";
				}else if(value == 1){
					return "<font color='red'>未审核</font>";
				}
			}
		}, {
			field : 'goodsReceive',
			title : '接收状态',
			width : 80,
			sortable : true,
			formatter: function(value, row, index){
				if(value == 0){
					return "<font color='green'>已接收</font>";
				}else if(value == 1){
					return "<font color='red'>未接收</font>";
				}
			}
		}]],
		onClickRow:function(index,data){
			currentOrderID = data.id;
			
			checkedlist = data;
			$('#orderList_dg').datagrid('options').url = global_param.context_name + "/purchase/orderList/listRecepitOrderList";
			$('#orderList_dg').datagrid('options').queryParams.ticket_id = data.ticket_id;
			$('#orderList_dg').datagrid('reload');
			
		}
	});
	
	$("#orderList_dg").datagrid({
		method : 'get',
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
			width : 100,
			sortable : true
		}, {
			field : 'id',
			title : '小单ID',
			width : 100,
			hidden : true 
		}, {
			field : 'product_commonname',
			title : '通用名',
			width : 150,
			sortable : true
		}, {
			field : 'unitprice',
			title : '小单单价',
			width : 100,
			sortable : true,
		}, {
			field : 'sum',
			title : '小单总金额',
			width : 100,
			sortable : true
		}, {
			field : 'quantity',
			title : '小单订单数量',
			width : 100,
			sortable : true,
			//hidden:true,
		}, {
			field : 'arrivalNumber',
			title : '收货数量',
			width : 100,
			sortable : true,
			//hidden:true,
		}, {
			field : 'remainNumber',
			title : '余量',
			width : 100,
			sortable : true,
			formatter: function(value, row, index){
				return row.quantity-row.arrivalNumber;
			}
		},{
			field : 'isArrival',
			title : '收货状态',
			width : 100,
			sortable : true,
			formatter: function(value, row, index){
				if(value==1){
					return "<font color='red'>等待接收审核</font>";
				}else if(value==0){
					return "<font color='green'>接收审核通过</font>";
				}else if(value==2){
					return "<font color='blue'>等待拒收审核</font>";
				} 
			}
		}]],

		toolbar : "#tb",
	});
	
	util.window('editReceiptWindow', {
		title : '确认收货订单信息',
	}); 
});


function query() {
	$('#order_dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#order_dg').datagrid('reload');
	$('#orderList_dg').datagrid('loadData', { total: 0, rows: [] });
}

function submitForm(){
	var data = $("#orderList_dg").datagrid("getData");
}

function checkReceiptList(){
	var node = $('#orderList_dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}/*else if (node.isArrival == 2) {
		util.show("该订单正在等待拒收审核，无法进行其他操作");
		return;
	}*/
	var checked = 0;
	var unchecked =0;
	var selections=$('#orderList_dg').datagrid("getChecked");
	var dataArr = new Array();
	$.each(selections ,function(index,item){
		if(selections[index].isArrival==2){
			util.show("有订单正在等待拒收审核，无法进行其他操作");
			unchecked++;
			return;
			
		}
		var orderList=new Object();
		orderList.id=selections[index].id;
		orderList.isArrival=selections[index].isArrival;
		
		dataArr.push(orderList);
		checked++;
	});
	
	alert(dataArr.length);
	//选择1条记录时
	if(checked==1&&unchecked==0){
		alert("1条请求");
		util.openWindow('editReceiptWindow', "/HRERP/accept/receipt/" + node.id);
		
	}
	//选择多条记录时
	else if(checked>1&&unchecked==0){
		alert("多条请求");
		/*$.ajax({  
        url : global_param.context_name + "/refund/check/refundPassAudit",  
        type : "get",  
      
        data : {"dataArr":JSON.stringify(dataArr)},
        contentType: "application/json", //必须有
        dataType: "json", //表示返回值类型，不必须
        success : function(data, status) {  
        	queryForSupInfoDatagrid();
        },  
        Error : function(error, exception) {  
            alert(exception.toString());  
        }  
     });*/
	
	}
}
function checkAll(){
	 $("#orderList_dg").datagrid("checkAll");
}
function uncheckAllFunc(){
	$("#orderList_dg").datagrid("uncheckAll");
}
