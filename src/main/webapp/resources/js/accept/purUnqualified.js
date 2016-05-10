$(function() {
	// 初始化表格
	$("#dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/purchase/Unqualitedcheck/lists", // 数据来源
		title : '不合格品验收', // 标题
		sortName : 'accept_check_id', // 排序的列
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
			width : 150,
			sortable : true
		},{
			field : 'product_code',
			title : '产品编码',
			width : 150,
			sortable : true
		},{
			field : 'product_commonname',
			title : '通用名',
			width : 150,
			sortable : true
		}, {
			field : 'quantity',
			title : '数量',
			width : 100,
			sortable : true,
			
		}, {
			field : 'unitprice',
			title : '单价',
			width : 200,
			sortable : true,
		}, {
			field : 'accept_arrivalNumber',
			title : '到货数量',
			width : 200,
			sortable : true
		}, {
			field : 'accept_acceptNumber',
			title : '收货数量',
			width : 200,
			sortable : true
		}, {
			field : 'accept_rejectNumber',
			title : '拒货数量',
			width : 200,
			sortable : true
		},{
			field : 'accept_checkQualitedNumber',
			title : '合格数量',
			width : 150,
			sortable : true
		},{
			field : 'accept_checkUnqualitedNumber',
			title : '不合格数量',
			width : 150,
			sortable : true
		}]],
		toolbar : [{
			text : '合格',
			iconCls : 'icon-add',
			handler : pass
		},{
			text:'不合格',
			iconCls:'icon-delete',
			handler: unQualified
		}] 
	});
	
	// 初始化编辑窗口
	util.window('checkWindow', {
		title : '合格确认',
		resizable:true,
		width : 350,
		height : 350
	
	}); 
	
});

function query(){
	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#dg').datagrid('reload');
}

function pass(){
	var node = $('#dg').datagrid('getSelected');
	//alert(node.accept_check_id);
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	var re=util.get(global_param.context_name+"/purchase/Unqualitedcheck/pass?id="+node.accept_check_id);
	if(re.result == "success"){
		util.show(re.message);
		$('#dg').datagrid('reload');
		
	}else{
		util.error(re.message);
		$('#dg').datagrid('reload');
	}
}

function unQualified(){
	var node = $('#dg').datagrid('getSelected');
	//alert(node.accept_check_id);
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('checkWindow',"Unqualitedcheck/checkView?id="+node.accept_check_id);
}
