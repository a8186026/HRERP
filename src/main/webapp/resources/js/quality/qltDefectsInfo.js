$(function() {
	var dosageTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=98");
	var unitTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=132");
	
	// 初始化表格
	$("#dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/quality/defectsInfo/lists", // 数据来源
		title : '不合格品种确定', // 标题
		sortName : 'defects_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		singleSelect : false, // 单选
		pageSize : 10,
		pageList : [10, 20, 50, 100],
		fitColumns : true,
		fit : true,
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		nowrap : true,
		striped : true, // 奇偶行颜色不同
		queryParams: {
			defects_draft:1,
			defects_check:1,
			defects_profitLoss:1,
			defects_destruction:1,
		},
		columns :[ [{
			field : 'defects_date',
			title : '日期',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value,"yyyy-MM-dd");
			}
		}, {
			field : 'product_code',
			title : '产品编号',
			width : 80,
		}, {
			field : 'product_name',
			title : '产品名称',
			width : 80,
		}, {
			field : 'product_specification',
			title : '规格',
			width : 80,
		}, {
			field : 'product_unit',
			title : '单位',
			width : 60,
			formatter :function(value, row, index){
				for(var i = 0 ; i < unitTypes.length ;i++){
					if(value == unitTypes[i].cbs_id){
						return unitTypes[i].cbs_chn;
					}
				}
			}
		}, {
			field : 'product_factoryname',
			title : '生产厂家',
			width : 100,
		}, {
			field : 'product_dosagetype',
			title : '剂型',
			width : 50,
			formatter :function(value, row, index){
				for(var i = 0 ; i < dosageTypes.length ;i++){
					if(value == dosageTypes[i].cbs_id){
						return dosageTypes[i].cbs_chn;
					}
				}
			}
		}, {
			field : 'product_approvalnum',
			title : '批准文号',
			width : 100,
		}, {
			field : 'stock_batchCode',
			title : '批号',
			width : 80, 
		}, {
			field : 'stock_invalidDate',
			title : '失效期',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value,"yyyy-MM-dd");
			}
		}, {
			field : 'stock_intakeTicket',
			title : '入库票号',
			width : 100,
			sortable : true
		}, {
			field : 'stock_intakeSmallNumber',
			title : '入库小号',
			width : 80,
			sortable : true
		}, {
			field : 'stock_produceDate',
			title : '生产日期',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value,"yyyy-MM-dd");
			}
		}, {
			field : 'stock_sterilizationbnum',
			title : '灭菌批号',
			width : 80,
			sortable : true
		}, {
			field : 'stock_sterilizationtime',
			title : '灭菌日期',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value,"yyyy-MM-dd");
			}
		}, {
			field : 'stock_storageNumber',
			title : '库存数量',
			width : 80,
		}, 
		 {
			field : 'defects_number',
			title : '不合格品种数量',
			width : 80,
			sortable : true
		}, {
			field : 'defects_quality',
			title : '质量状况',
			width : 80,
			sortable : true
		}, {
			field : 'defects_reason',
			title : '不合格原因',
			width : 80,
		}, {
			field : 'defects_number',
			title : '不合格数量',
			width : 80,
		}, {
			field : 'defects_suggestion',
			title : '处理意见',
			width : 80,
			sortable : true
		}, {
			field : 'defects_storagePlace',
			title : '存放地点',
			width : 80,
			sortable : true
		}, {
			field : 'defects_remark',
			title : '备注',
			width : 80,
			sortable : true
		}, {
			field : 'defects_person',
			title : '操作员',
			width : 80,
			sortable : true
		}]],
		toolbar :"#tb",
		onLoadSuccess:function(data){
			var defects_ticketNo = util.get(global_param.context_name + "/quality/defectsInfo/getDefectsTicket");
			$("#defects_ticketNo").val(defects_ticketNo);
	}
	});
	// 初始化编辑窗口
	util.window('editWindow', {
		title : '不合格品种确定'
	});
	
});

function query() {
	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#dg').datagrid('reload');
}

function addFunc() {
	util.openWindow('editWindow', "defectsInfo/new");
}

function selectAll(){
	 $("#dg").datagrid("checkAll");
}

function unSelectAll(){
	$("#dg").datagrid("uncheckAll");
}

function saveFunc() {
	var nodes = $('#dg').datagrid('getSelections');
	var data ="";
	if(nodes.length==0){
		util.show("请选择要确认的不合格品种信息");
		return;
	}
	
	for(var i=0;i<nodes.length-1;i++){
		data = data+nodes[i].defects_id+"_";
	}
	data = data+nodes[nodes.length-1].defects_id;
	
	//保存操作
	var result = util.get("/HRERP/quality/defectsInfo/save?data="+data+
			"&oldDefectsTicket="+$("#defects_ticketNo").val());
	
	if(result.result=="success"){
		util.show(result.message);
		if(result.defects_ticketNo != null)
			util.alert("票号冲突,新入库票号为："+result.defects_ticketNo);
		$("#dg").datagrid("reload");
	}else if(result.result == "failure"){
		util.show(result.message);
	}else{
		util.show("出错了");
	}
}
