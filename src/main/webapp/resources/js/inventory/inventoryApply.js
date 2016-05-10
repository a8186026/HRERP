var packingunitTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=125");
$(function() {
	var department_id = null;
	
	//获取库房
	$('#department_id').combobox({
		url : global_param.context_name + "/accept/stock/getDepartmentList",
		method :"get",
		valueField : "department_id",
		textField : "department_fullname",
	    /*onSelect:function(record){  
	        $('#department_fullname').attr('value',record.department_fullname); 
	    } */
	}); 
	
	// 初始化表格
	$("#dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/store/inventroy/lists", // 数据来源
		title : '盘点损益申请', // 标题
		fitColumns : true,
		singleSelect : true, // 单选
		pageSize : 10,
		pageList : [10, 20, 50, 100],
		fitColumns : true,
		fit : true,
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		nowrap : true,
		striped : true, // 奇偶行颜色不同
		queryParams : {
		},
		columns :[[{
			field : 'inventory_id',
			title : '序号',
			width : 20,
			sortable : true
		}, {
			field : 'inventory_operator',
			title : '操作员',
			width : 30,
			sortable : true
		}, {
			field : 'department_fullname',
			title : '库房'	,
			width : 50,
			sortable : true
		}, {
			field : 'product_id',
			title : '产品序号',
			width : 50,
			sortable : true
		}, {
			field : 'product_name',
			title : '产品名称',
			width : 80,
			sortable : true
		}, {
			field : 'product_specification',
			title : '规格',
			width : 50,
			sortable : true
		}, {
			field : 'product_unit',
			title : '单位',
			width : 30,
			sortable : true
		}, {
			field : 'product_productarea',
			title : '产地',
			width : 80,
			sortable : true
		}, {
			field : 'stock_batchCode',
			title : '批号',
			width : 50,
			sortable : true
		}, {
			field : 'stock_invalidDate',
			title : '失效期',
			width : 80,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value);
			}
		}, {
			field : 'stock_intakeTicket',
			title : '入库票号',
			width : 80,
			sortable : true
		},{
			field : 'stock_intakeSmallNumber',
			title : '入库小号',
			width : 80,
			sortable : true
		},{
			field : 'inventory_countedNumber',
			title : '盘点数量',
			width : 50,
			sortable : true
		},{
			field : 'inventory_profitLossNumber',
			title : '损益数量',
			width : 80,
			sortable : true
		},{
			field : 'inventory_profitLossMoney',
			title : '损益金额',
			width : 80,
			sortable : true
		}]],
		toolbar : [{
			id : 'editApply',
			text : '修改',
			iconCls : 'icon-add',
			handler : editApplyFunc
		},{
			text : '全选',
			iconCls : 'icon-modify',
			handler : checkAllFunc
		},{
			text : '删除',
			iconCls : 'icon-modify',
			handler : delApplyFunc
		},{
			text : '重置',
			iconCls : 'icon-reload',
			handler : reloadDatagrid
		}]
	});
	// 初始化编辑窗口
	util.window('proListWindow', {
		title : '产品编码选择'
	});
	// 初始化编辑窗口
	util.window('editWindow', {
		title : '盘点损益信息编辑',
		height:500,
		width:600
	}); 
	
	//库房非空设置
	//产品查询
	$('#product_code').bind(
		'keydown',function() {
	    var e = event || window.event;
	    var keyCode = e.keyCode || e.which;
	    var actElement = document.activeElement;
	    //如果按键是enter并且当前焦点是产品编号，则弹出产品选择框
	    if(keyCode == 13&&actElement.id == "product_code"){
	    	//输入后，打开产品选择页面
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
	 				content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/store/inventroy/viewProList?callback=setProContent&proContent=' + proContent.replace("%", "%25") +'"></iframe>',
	 			});
	 			$('#proListWindow').window('open');
	 		}
	    }
	}).bind('focus',function(){
		    //alert("123"+$('#department_id').combobox('getValue'));
			if($('#department_id').combobox('getValue')==null||$('#department_id').combobox('getValue')==""){
				$('#department_id').combobox().next('span').find('input').focus();  
	    		//$("#product_code").attr("readonly",true);
	    	    util.show("请先选择库存编号！");
	    	    } 
			
		});
	
	$('#inventory_countedNumber').bind('blur', function(){
		if(isNaN(this.value)){
	   		 util.show("请输入正确盘点数量！");
	   		 $('#inventory_countedNumber').val();
	   	 }else{
	   		$('#inventory_profitLossNumber').attr("value",profitLossNum());
		     	multi();
	   	 }
	});
	
/*    $('#inventory_profitLossNumber').bind('blur', function(){
   	 if(isNaN(this.value)){
   		 util.show("请输入正确损益数量");
   		 $('#inventory_profitLossNumber').val();
   	 }else{
	     	multi();
   	 }
     });*/
});
function  profitLossNum(){
	var a=$('#inventory_countedNumber')[0].value;
	var b=$('#stock_storageNumber').attr("value");
	if(a!=null&&b!=null){
		$("#inventory_profitLossNumber").attr("value",a-b);
	}
}	

function multi(){
	var a=$('#inventory_profitLossNumber')[0].value;
	var b=$('#stock_purchasePrice').attr("value");
	if(a!=null&&b!=null){
		$("#inventory_profitLossMoney").attr("value",a*b);
	}
}

/*
 * 子菜单回调函数组
 * */
//获得子窗口选择的供方信息,并执行相关操作
function setProContent(node,stock){
	//获得选择的库存产品批次停售信息
	//表单信息插入
	//alert("node:"+node);
	if(node!=null&&node!=""){
	$("#product_code").attr("value",node.product_code);
	$("#product_id").attr("value",node.product_id);
	$("#stock_info_id").attr("value",stock.stock_info_id);
	$("#product_name").attr("value",node.product_name);
	$("#product_specification").attr("value",node.product_specification);
	$("#product_unit").attr("value",node.product_unit);
	for(var i = 0 ; i < packingunitTypes.length ;i++){
		if(node.product_packingunit == packingunitTypes[i].cbs_id){
			$("#product_packingunit").attr("value",packingunitTypes[i].cbs_chn);
		}
	}
	$("#product_retailprice").attr("value",node.product_retailprice);
	$("#product_productarea").attr("value",node.product_productarea);
	$("#stock_invalidDate").attr("value",stock.stock_invalidDate);
	$("#stock_purchasePrice").attr("value",stock.stock_purchasePrice);
	$("#stock_intakeSmallNumber").attr("value",stock.stock_intakeSmallNumber);
	$("#stock_intakeTicket").attr("value",stock.stock_intakeTicket);
	$("#stock_storageNumber").attr("value",stock.stock_storageNumber);
	$("#stock_batchCode").attr("value",stock.stock_batchCode);
	$("#sup_id").attr("value",stock.sup_id);
	} else{
		alert("该库房已无此产品");
	}
};	
	
function query() {
	$('#dg').datagrid('options').queryParams = util.formParams("");
	$('#dg').datagrid('reload');
}
function reloadDatagrid(){
	$('#searchForm').form('clear');
	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#dg').datagrid('reload');
}
	
function apply() {
	if($('#department_id').combobox('getValue')==null||$('#department_id').combobox('getValue')==""
		||$('#product_code').val()==null||$('#product_code').val()==""
		||$('#inventory_countedNumber').val()==null||$('#inventory_countedNumber').val()==""
		||$('#inventory_reason').val()==null||$('#inventory_reason').val()==""){
		util.show("请输入完整信息!");
	}else{
		var data = util.submit('searchForm');
		if (data) {
			if (data.result == "success") {
				util.show(data.message);
				$('#dg').datagrid('reload');
			} else {
				util.error(data.message);
			}
		}
		$('#searchForm').form('clear');
	}
}
function editApplyFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editWindow', "/HRERP/store/inventroy/edit/" + node.stock_info_id);
}

function checkAllFunc(){
	 $("#dg").datagrid("checkAll");
}
function delApplyFunc(){
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/store/inventroy/" + node.stock_info_id);
		if (data.result == "success") {
			$('#dg').datagrid('reload');
		}
		util.show(data.message);
	});
}

$.extend($.fn.datagrid.methods, {
	//键盘上下键换行
    keyCtr : function (jq) {
        return jq.each(function () {
            var grid = $(this);
            grid.datagrid('getPanel').panel('panel').attr('tabindex', 1).bind('keydown', function (e) {
            	var rows = grid.datagrid('getRows');
                var selected = grid.datagrid('getSelected');
                var index = grid.datagrid('getRowIndex', selected);
                switch (e.keyCode) {
                case 38: // up
                    if (index>0) {
                    	grid.datagrid('uncheckRow', index);
                        grid.datagrid('selectRow', index - 1);
                    } else {
                    	grid.datagrid('uncheckRow', 0);
                        grid.datagrid('selectRow', rows.length - 1);
                    }
                    break;
                case 40: // down
                    if (index<rows.length - 1) {
                    	grid.datagrid('uncheckRow', index);
                        grid.datagrid('selectRow', index + 1);
                    } else {
                        grid.datagrid('uncheckRow', rows.length - 1);
                        grid.datagrid('selectRow', 0);
                    }
                    break;
                }
            });
        });
    }
});