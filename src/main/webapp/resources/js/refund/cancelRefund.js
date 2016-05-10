
$(function(){
	$('#refund_payCheck').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=987",
		method :"get",
		valueField : "cbs_code",
		textField : "cbs_chn",
		editable:false,
		onLoadSuccess: function(){
			//salCheckList = $('#search_sal_check').combobox("getData");
		}
	});
	$('#refund_payCheck').combobox('select',1);
	$('#refund_deliveryCheck').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=987",
		method :"get",
		valueField : "cbs_code",
		textField : "cbs_chn",
		editable:false,
		onLoadSuccess: function(){
			//salLocalList = $('#search_sal_drugid').combobox("getData");
			 query();
		}
	});
	
	
	$("#dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/refund/cancel/lists", // 数据来源
		title : '返货信息', // 标题
		sortName : 'refund_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitcolumns : true,
		singleSelect : true,//单选
		fit:true,
		pageSize : 20,
		pageList : [10,20,50,100],
		pagination : true,
		rownumbers : true,
		nowrap : true,
		striped : true,
		queryParams : {},
		columns:[[ {
			field : 'refund_ticketId',
			title : '票号',
			width : 150,
			sortable : true
		},  {
			field : 'product_id',
			title : '产品序号',
			width : 150,
			sortable : true
		},{
			field : 'product_name',
			title : '产品名称',
			width : 150,
			sortable : true
		},{
			field : 'refund_number',
			title : '返货数量',
			width : 150,
			sortable : true
		},{
			field : 'refund_unitPrice',
			title : '返货单价',
			width : 100,
			sortable : true
		},{
			field : 'refund_money',
			title : '返货金额',
			width : 150,
			sortable : true
		}, {
			field : 'stock_invalidDate',
			title : '失效期',
			width : 150,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value);
			}
		}, {
			field : 'stock_intakeTicket',
			title : '入库票号',
			width : 150,
			sortable : true
		}, {
			field : 'stock_intakeSmallNumber',
			title : '入库小号',
			width : 100,
			sortable : true
		}, {
			field : 'sup_name',
			title : '供方全称',
			width : 80,
			sortable : true
		}, {
			field : 'refund_payCheck',
			title : '返货审核',
			width : 100,
			sortable : true,
			formatter: function(value, row, index){
				if(value == 0){
					return "<font color='green'>已完成</font>";
				}else if(value == 1){
					return "<font color='red'>未完成</font>";
				}else {
					return " ";
				}
			}
		}, {
			field : 'refund_deliveryCheck',
			title : '返货付货',
			width : 100,
			sortable : true,
			formatter: function(value, row, index){
				if(value == 0){
					return "<font color='green'>已完成</font>";
				}else if(value == 1){
					return "<font color='red'>未完成</font>";
				}else {
					return " ";
				}
			}
		
		/*}, {
			field : 'refund_cancel',
			title : '取消返货',
			width : 100,
			sortable : true,
			formatter: function(value, row, index){
				if(value == 0){
					return "<font color='green'>已取消</font>";
				}else if(value == 1){
					return "<font color='red'>未取消</font>";
				}else {
					return " ";
				}
			}*/
		
		}]],
		toolbar : [{
			text : '取消返货',
			iconCls : 'icon-delete',
			handler : deleteRefund
		},{
				text : '全选',
				iconCls : 'icon-modify',
				handler : checkAllFunc
		},{
			text : '清除',
			iconCls : 'icon-delete',
			handler : uncheckAllFunc
		}
		],
		
	});

});
	
	

function query() {
	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#dg').datagrid('reload');
}

function deleteRefund(){
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条返货记录！");
		return;
	}else if(node.refund_deliveryCheck!=1){
		util.show("记录已被审核，不能取消");
		return;
	}
	var selections=$('#dg').datagrid("getChecked");
	var dataArr = new Array();
	$.each(selections ,function(index,item){
		var RefundCheckItem=new Object();
		RefundCheckItem.id=selections[index].id;
		RefundCheckItem.refund_id=selections[index].refund_id;
		dataArr.push(RefundCheckItem);
	});
	

	
	$.ajax({  
	        url : global_param.context_name + "/refund/cancel/all",  
	        type : "get",  
	       // data : "dataArr="+JSON.stringify(dataArr),
	        data : {"dataArr":JSON.stringify(dataArr)},
	        contentType: "application/json", //必须有
            dataType: "json", //表示返回值类型，不必须
	        success : function(data, status) {  
	        	queryForSupInfoDatagrid();
/*	            alert("成功"); */
	        },  
	        Error : function(error, exception) {  
	            alert(exception.toString());  
	        }  
	 });
	//var data = util.get(global_param.context_name + "/refund/cancel/" + node.refund_id);
	$('#dg').datagrid('reload');
	util.show(data.message);
}

function checkAllFunc(){
	 $("#dg").datagrid("checkAll");
}

function uncheckAllFunc(){
	$("#dg").datagrid("uncheckAll");
}

