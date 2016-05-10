var rule_id = null;
$(function() {
	// 初始化表格
	$("#RuleInfoDg").datagrid({
		method : 'get',
		url : global_param.context_name + "/promotion/pmnRuleInfo/lists", // 数据来源
		title : '折扣规则信息管理', // 标题
		sortName : 'rule_priority', // 排序的列
		sortOrder : 'asc', // 排序方式
		singleSelect : true, // 单选
		/*pageSize : 10,
		pageList : [10, 20, 50, 100],*/
		height : 390,
		fitColumns : true,
		//pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		nowrap : true,
		striped : true, // 奇偶行颜色不同.
		queryParams : {},
		columns :[ [ {
			field : 'rule_name',
			title : '规则名称',
			width : 100,
			sortable : true
		}, {
			field : 'rule_priority',
			title : '规则优先级',
			width : 100,
			sortable : true
		}, {
			field : 'rule_startdate',
			title : '开始日期',
			width : 100, 
			formatter : function (value, row, index) {
				return util.formatDate(value,"yyyy-MM-dd");
			}
		}, {
			field : 'rule_enddate',
			title : '结束日期',
			width : 100, 
			formatter : function (value, row, index) {
				return util.formatDate(value,"yyyy-MM-dd");
			}
		}, {
			field : 'rule_starttime',
			title : '开始时间',
			width : 100, 
		}, {
			field : 'rule_endtime',
			title : '结束时间',
			width : 100, 
		}, {
			field : 'rule_set',
			title : '规则详情',
			width : 200,
			formatter: function(value,row,index){
				return value.substring(1,value.length-1);
			}
		}, {
			field : 'rule_number',
			title : '数量',
			width : 100,
		}, {
			field : 'rule_discount',
			title : '折扣',
			width : 100,
		}, {
			field : 'rule_remark',
			title : '规则备注',
			width : 100, 
		}] ],
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
		}, {
			text : '添加明细',
			iconCls : 'icon-modify',
			handler : ruleProductFunc
		}, {
			text : '保存配置',
			iconCls : 'icon-modify',
			handler : savePriorityFunc
		}],
		onLoadSuccess:function(data){
			$(this).datagrid('enableDnd');
		},
		onClickRow:function(rowIndex,rowData){
			$('#RuleProductInfoDg').datagrid('options').url = global_param.context_name + "/product/proInfoManage/listRuleProductInfo";
			$('#RuleProductInfoDg').datagrid('options').queryParams.rule_id = rowData.rule_id;
			$('#RuleProductInfoDg').datagrid('reload');
			rule_id = rowData.rule_id;
		}
	});
	// 初始化编辑窗口
	util.window('editWindow', {
		title : '折扣规则信息'
	});
	
	// 初始化编辑窗口
	util.window('editRuleProductWindow', {
		title : '折扣规则产品',
		width : 1000,
		height : 800,
	});
	
	
	
	$("#RuleProductInfoDg").datagrid({
		method : 'get',
		title : '折扣规则产品信息', // 标题
		sortName : 'product_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		singleSelect : true, // 单选
		fit : true,
		fitColumns : true,
		pagination : false, // 显示分页
		rownumbers : true, // 显示行号
		striped : true, // 奇偶行颜色不同
		queryParams : {},
		columns:[[{
			field : 'product_name',
			title : '产品名称',
			width : 200,
			sortable : true
		}, {
			field : 'product_specification',
			title : '规格',
			width : 100,
			sortable : true
		}, {
			field : 'product_unit',
			title : '单位',
			width : 100,
			sortable : true
		}, {
			field : 'product_productarea',
			title : '产地',
			width : 200,
			sortable : true
		}, {
			field : 'gift_amount',
			title : '数量',
			width : 100,
			sortable : true
		}]],
		toolbar : [{
			text : '删除',
			iconCls : 'icon-add',
			handler : deleteRuleProductFunc
		}]
	});


});

function query() {
	$('#RuleInfoDg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#RuleInfoDg').datagrid('reload');
}

function addFunc() {
	util.openWindow('editWindow', "pmnRuleInfo/new");
}

function updateFunc() {
	var node = $('#RuleInfoDg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editWindow', "pmnRuleInfo/" + node.rule_id);
}

function deleteFunc() {
	var node = $('#RuleInfoDg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/promotion/pmnRuleInfo/" + node.rule_id);
		if (data.result == "success") {
			$('#RuleInfoDg').datagrid('reload');
		}
		util.show(data.message);
	});
}	
	
function ruleProductFunc() {
	var node = $('#RuleInfoDg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editRuleProductWindow', global_param.context_name + "/promotion/pmnRuleInfo/ruleProduct/" + node.rule_id);
}

function deleteRuleProductFunc() {
	var node = $('#RuleProductInfoDg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	//alert(JSON.stringify(node));
	//alert(node.product_id +"_"+ rule_id);
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/promotion/pmnRuleInfo/deleteRuleProduct?product_id="+node.product_id+"&rule_id="+rule_id);
		if (data.result == "success") {
			$('#RuleProductInfoDg').datagrid('reload');
		}
		util.show(data.message);
	});
}

function savePriorityFunc(){
	var rows = $('#RuleInfoDg').datagrid('getRows');     
	for(var i = 0;i < rows.length;i++){
		rows[i].rule_priority = i+1;
	}
	var data ="";
	for(var i = 0;i < rows.length;i++){
		if(i < rows.length-1){
			data = data + rows[i].rule_id+"_";
			data += rows[i].rule_priority;
			data+="|";
		}
		else{
			data = data+rows[rows.length-1].rule_id+"_";
			data = data+rows[rows.length-1].rule_priority;
		}
	}
	
	 //alert(JSON.stringify($('#RuleInfoDg').datagrid('getRows')));
	 var result = util.get("/HRERP/promotion/pmnRuleInfo/savePriority?data="+data); 
	
	 
	if(result.result=="success"){
		util.show(result.message);
		$('#RuleInfoDg').datagrid('reload');
	}else if(result.result=="failure"){
		util.show(result.message);
	}else{
		util.show("出错了");
	}
}

