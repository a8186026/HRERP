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
	
	// 初始化表格
	$("#dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/refund/register/lists", // 数据来源
		title : '返货付货登记', // 标题
		sortName : 'stock_info_id', // 排序的列
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
		onSelect : function(rowIndex,rowData){
			updatePanel(rowIndex,rowData);
			$('#refund_chiefPerson').attr('value','');
			$('#refund_reason').attr('value','');
			$('#refund_number').attr('value','');
			//$('#refund_unitPrice').attr('value','');
			$('#refund_money').attr('value','');
		
		},
		
		columns:[[ {
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
			field : 'stock_storage',
			title : '库房',
			width : 150,
			sortable : true
		}, {
			field : 'stock_storageNumber',
			title : '库房数量',
			width : 150,
			sortable : true,
			
		}, {
			field : 'stock_batchCode',
			title : '批号',
			width : 150,
			sortable : true,
		}, {
			field : 'stock_invalidDate',
			title : '失效期',
			width : 200,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value);
			}
		}, {
			field : 'stock_purchasePrice',
			title : '进价',
			width : 150,
			sortable : true
		}, {
			field : 'stock_intakeTicket',
			title : '入库票号',
			width : 150,
			sortable : true
		}, {
			field : 'stock_intakeSmallNumber',
			title : '入库小号',
			width : 150,
			sortable : true
		}, {
			field : 'sup_name',
			title : '供方全称',
			width : 150,
			sortable : true
		}]],
		toolbar : [{
			id: 'save',
			text : '存盘',
			iconCls : 'icon-add',
			handler : save
		}]
	}).datagrid("keyCtr");
	
	// 初始化编辑窗口
	util.window('proListWindow', {
		title : '库存产品',
		resizable:true,
		width : 650,
		height : 650
	
	}); 
	
	 document.onkeydown=function(event){ 
         var e = event || window.event || arguments.callee.caller.arguments[0]; 
         if(e && e.keyCode==27){ // 按 Esc  
             //要做的事情 
           } 
          if(e && e.keyCode==13){ // enter 键 
              //要做的事情 
        	 $("#save").focus();
         } 
     };  
     
   //如果当前焦点在产品编码上，失去焦点触发该动作，输入非空字符进行查询，进入产品选择界面
     $('#product_code').bind('blur', function() {
     	//输入后，打开供方客户页面
     	var proContent = $('#product_code').attr("value");
     	if(proContent!=null && proContent!=""){
     		$("#proListWindow").window({
     			title : '产品选择',
     			width : 1250,
     			height : 650,
     			shadow : true,
     			modal : true,
     			closed : true,
     			minimizable : false,	
     			maximizable : false,
     			collapsible : false,
     			resizable : false,
     			content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/refund/register/viewStockProList?callback=getStockbyProduct&proContent=' + proContent.replace("%", "%25") +'"></iframe>',
     		});
     		$('#proListWindow').window('open');
     	}
     });
     
     $('#refund_number').bind('blur', function(){
    	
    	 if(isNaN(this.value)){
     		util.show("请输入正确退货数量");
     		$('#refund_number').val();
     	}else{
	    	if(this.value>parseInt($("#refund_number_limit").text())){
	    		util.show("退货数量不能超过库存");
	    		this.value=parseInt($("#refund_number_limit").text());
	    	}
	    	multi();//数量单价都与计算翻或金额事件绑定
     	}
     });
     $('#refund_unitPrice').bind('blur', function(){
    	 if(isNaN(this.value)){
    		 util.show("请输入正确退货金额");
    		 $('#refund_unitPrice').val();
    	 }else{
	    	 var refundPrice =  parseFloat(this.value);
	     	if(refundPrice!=parseFloat($("#stock_purchasePrice").attr("value"))){
	     		util.show("请注意价格与进货价不同");
	     	}
	     	multi();
    	 }
      });

});



function save() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	if($('#refund_money').val()==""||$('#sup_ctactperson').val()==""){
		util.show("请填入完整的返货信息");
		return;
	}

	
	$('#product_id').attr('value',node.product_id);
	$('#stock_intakeSmallNumber').attr('value',node.stock_intakeSmallNumber);
	$('#stock_invalidDate').attr('value',util.formatDate(node.stock_invalidDate));


	
	var data = util.submit('form');
	
	if (data.result == "success") {
		$('#dg').datagrid('reload');
	}

	util.show(data.message);
	
	
}

function updatePurOrder(){
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.get();
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

function updatePanel(index,data){
	
	//alert(data.stock_batchCode);
	
	$("#stock_intakeTicket").attr("value",data.stock_intakeTicket);
	$("#stock_storage").attr("value",data.stock_storage);
	$("#sup_ctactperson").attr("value",data.sup_ctactperson);
	$("#sup_code").attr("value",data.sup_code);
	//负责人？stock_batchCode
	$("#stock_batchCode").attr("value",data.stock_batchCode);

	$("#stock_purchasePrice").attr("value",data.stock_purchasePrice);
	$("#refund_number_limit").text(data.stock_storageNumber);
	$("#product_code").attr("value",data.product_code);
	$("#stock_info_id").attr("value",data.stock_info_id);
	$("#sup_id").attr("value",data.sup_id);
	
	//退货单价也默认为进价
	$("#refund_unitPrice").val(data.stock_purchasePrice);

	
}

//更新列表
function getStockbyProduct(node){

	$('#dg').datagrid({
		queryParams: {
			product_id:node.product_id,
		}
	});
	$("#product_code").attr("value",node.product_code);
	$('#dg').datagrid('reload');
	$("#save").focus();
}

function multi(){
	var a=$('#refund_number')[0].value;
	var b=$('#refund_unitPrice').attr("value");
	if(a!=null&&b!=null){
		$("#refund_money").attr("value",a*b);
	}
}
