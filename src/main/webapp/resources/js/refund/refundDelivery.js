  $.extend($.fn.datagrid.methods, {
	//键盘上下键换行
    keyCtr : function (jq) {
        return jq.each(function () {
            var grid = $(this);
            grid.datagrid('getPanel').panel('panel').attr('tabindex', 1).bind('keydown', function (e) {
            	var rows = grid.datagrid('getRows');
                var selected = grid.datagrid('getSelected');
                var index = grid.datagrid('getRowIndex', selected);
                var data=grid.datagrid("getRows");
                switch (e.keyCode) {
                case 38: // up
                    if (index>0) {
                    	grid.datagrid('uncheckRow', index);
                        grid.datagrid('selectRow', index - 1);
                        updatePanel(index - 1,data[index - 1]);
                        
                    } else {
                    	grid.datagrid('uncheckRow', 0);
                        grid.datagrid('selectRow', rows.length - 1);
                        updatePanel(rows.length - 1,data[rows.length - 1]);
                    }
                    break;
                case 40: // down
                    if (index<rows.length - 1) {
                    	grid.datagrid('uncheckRow', index);
                        grid.datagrid('selectRow', index + 1);
                        updatePanel(index + 1,data[index + 1]);
                    } else {
                        grid.datagrid('uncheckRow', rows.length - 1);
                        grid.datagrid('selectRow', 0);
                        updatePanel(0,data[0]);
                    }
                    break;
                }
            });
        });
    }
});


$(function() {
	
	//第一个参数页面ID,第二个参数datagrid ID，第三个参数配置显示框ID,第四个参数是否启动上下键换行，第五个参数是否启动列拖拽
/*	var userHabits1 = new userHabits();
	userHabits1.loadColumns("refundDelivery","dg","",true,true);
	*/
	// 初始化表格
	$("#dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/refund/delivery/lists", // 数据来源
		title : '返货付货登记', // 标题
		sortName : 'refund_id', // 排序的列
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
		onSelect : function(rowIndex,rowData){
			updatePanel(rowIndex,rowData);
		},
		columns:[[ {
			field : 'refund_ticketId',
			title : '返货票号',
			width : 150,
			sortable : true
		},{
			field : 'refund_date',
			title : '日期',
			width : 150,
			sortable : true
		},{
			field : 'product_name',
			title : '产品名称',
			width : 80,
			sortable : true,
		},  {
			field : 'product_specification',
			title : '规格',
			width : 30,
			sortable : true
		}, {
			field : 'product_productarea',
			title : '产地',
			width : 50,
			sortable : true
		}, {
			field : 'product_packingamount',
			title : '包装量',
			width : 50,
			sortable : true
		}, {
			field : 'product_unit',
			title : '单位',
			width : 50,
			sortable : true
		}, {
			field : 'product_category',
			title : '分类',
			width : 50,
			sortable : true
		}, {
			field : 'stock_intakeBrief',
			title : '摘要',
			width : 50,      
			sortable : true
		}, {
			field : 'refund_unitPrice',
			title : '单价',
			width : 50,
			sortable : true
		}, {
			field : 'refund_number',
			title : '退货数量',
			width : 80,
			sortable : true
		}, {
			field : 'refund_money',
			title : '返货金额',
			width : 80,
			sortable : true
		}, {
			field : 'refund_invoiceTaxRate',
			title : '发票税率',
			width : 80,
			sortable : true
		}, {
			field : 'stock_purchasePrice',
			title : '进价',
			width : 50,
			sortable : true
		}, {
			field : 'product_dosagetype',
			title : '剂型',
			width : 50,
			sortable : true
		}, { 
			field : 'product_code',
			title : '产品编号',
			width : 80,
			sortable : true
		}, {
			field : 'sup_name',
			title : '客户全称',
			width : 80,
			sortable : true
		}, {
			field : 'product_barcode',
			title : '产品条码',
			width : 80,
			sortable : true
		}, {
			field : 'product_retailprice',
			title : '零售价',
			width : 50,
			sortable : true
		}, {
			field : 'sup_code',
			title : '供方编码',
			width : 80,
			sortable : true
		}, {
			field : 'refund_operator',
			title : '操作员',
			width : 50,
			sortable : true
		}, {
			field : 'stock_batchCode',
			title : '批号',
			width : 50,
			sortable : true
		}, {
			field : 'stock_invalidDate',
			title : '失效期',
			width : 50,
			sortable : true
		}, {
			field : 'stock_storage',
			title : '库房',
			width : 50,
			sortable : true
		}, {
			field : 'refund_invoiceNumber',
			title : '票号',
			width : 50,
			sortable : true
		}, {
			field : 'refund_accountId',
			title : '记账票号',
			width : 80,
			sortable : true
		}, {
			field : 'product_description',
			title : '简介',
			width : 100,
			sortable : true
		}, {
			field : 'product_approvalnum',
			title : '批准文号',
			width : 100,
			sortable : true
	}]],
		toolbar : [{
			id: 'save',
			text : '存盘',
			iconCls : 'icon-save',
			handler : save
		},{
			id: 'delete',
			text : '删除',
			iconCls : 'icon-delete',
			handler : deleteTickets
		},{
			text : '全选',
			iconCls : 'icon-modify',
			handler : checkAllFunc
		},{
			text : '清除',
			iconCls : 'icon-delete',
			handler : uncheckAllFunc
		}]
	}).datagrid("keyCtr");
	
	
/*	// 初始化编辑窗口
	util.window('saveWindow', {
		title : '付货记录存盘',
		resizable:true,
		width : 650,
		height : 650
	
	}); */
	
	
	 document.onkeydown=function(event){ 
         var e = event || window.event || arguments.callee.caller.arguments[0]; 
         if(e && e.keyCode==27){ // 按 Esc  
             //要做的事情 
             //alert("按 esc"); 
           } 
          if(e && e.keyCode==13){ // enter 键 
              //要做的事情 
        	 $("#save").focus();
         } 
     };  
});
function updatePanel(index,data){
	$("#refund_ticketId").attr("value",data.refund_ticketId);
	$("#sup_name").attr("value",data.sup_name);
	$("#sup_ctactperson").attr("value",data.sup_ctactperson);
	$("#sup_tel").attr("value",data.sup_tel);
}
function query() {
	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#dg').datagrid('reload');
}
function clearForm(){
	 $("#searchForm input[type='text']").val("");
	 $("#searchForm select").find("option[value=0]").attr("selected","selected");
}
/*全选与清除所有选择内容 easyui自带的东西*/
function checkAllFunc(){
	 $("#dg").datagrid("checkAll");
}
function uncheckAllFunc(){
	$("#dg").datagrid("uncheckAll");
}
function save() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	alert("成功1111"); 
	var selections=$('#dg').datagrid("getSelections");
	var dataArr = new Array();
	$.each(selections ,function(index,item){
		var RefundDeliveryItem=new Object();
		RefundDeliveryItem.refund_id=selections[index].refund_id;
		dataArr.push(RefundDeliveryItem);
	});
	//var userid = $("#refund_operator_id").val();
	alert(JSON.stringify(dataArr)); 
	$.ajax({  
        url : global_param.context_name + "/refund/delivery/saveStatus",  
        type : "get",  
        data : {"dataArr":JSON.stringify(dataArr)},
        contentType: "application/json", //必须有
        dataType: "json", //表示返回值类型，不必须
        success : function(data, status) {  
        	$('#dg').datagrid('reload');
        },  
        Error : function(error, exception) {  
            alert(exception.toString());  
        }  
 });
	
}

function deleteTickets() {
/*	var node = $('#dg').datagrid('getSelected');
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
	});*/
}
 