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
		singleSelect : true, // 单选
		pageSize : 10,
		pageList : [10, 20, 50, 100],
		//fitColumns : true,
		fit : true,
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		nowrap : true,
		striped : true, // 奇偶行颜色不同
		columns :[ [{
			field : 'defects_date',
			title : '日期',
			width : 80,
			sortable : true
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
			field : 'product_productarea',
			title : '产地',
			width : 100,
		}, {
			field : 'product_factoryname',
			title : '分类',
			width : 100,
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
			field : 'stock_storageNumber',
			title : '库存数量',
			width : 80,
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
			field : 'stock_intakeSmallNumber',
			title : '入库小号',
			width : 80,
			sortable : true
		}, {
			field : 'department_id',
			title : '调出库房',
			width : 80,
			sortable : true
		}, {//供货企业 供货序号
			field : 'defects_storagePlace',
			title : '存放地点',
			width : 80,
			sortable : true
		}, {
			field : 'stock_intakeSmallNumber',
			title : '入库小号',
			width : 80,
			sortable : true
		}, {
			field : 'defects_suggestion',
			title : '处理意见',
			width : 80,
			sortable : true
		}, {
			field : 'defects_result',
			title : '处理结果',
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
		}, {
			field : 'defects_ticketNo',
			title : '票号',
			width : 80,
			sortable : true
		}, {
			field : 'defects_reason',
			title : '不合格原因',
			width : 80,
			sortable : true
		}, {
			field : 'defects_quality',
			title : '质量状况',
			width : 80,
			sortable : true
		}, {
			field : 'defects_check',
			title : '审核',
			width : 80,
			sortable : true
		}, {
			field : 'defects_checkPerson',
			title : '审核人',
			width : 80,
			sortable : true
		}, {
			field : 'defects_checkTime',
			title : '审核时间',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value,"yyyy-MM-dd");
			}
		}, {
			field : 'defects_checkSuggestion',
			title : '审核意见',
			width : 80,
			sortable : true
		}, {
			field : 'defects_profitLoss',
			title : '损益',
			width : 80,
			sortable : true
		}, {
			field : 'defects_profitLossTime',
			title : '损益时间',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value,"yyyy-MM-dd");
			}
		}, {
			field : 'defects_destruction',
			title : '销毁',
			width : 80,
			sortable : true
		}, {
			field : 'defects_destructionTime',
			title : '销毁时间',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value,"yyyy-MM-dd");
			}
		}, {
			field : 'defects_destructionReason',
			title : '销毁原因',
			width : 80,
			sortable : true
		}, {
			field : 'defects_destructionWay',
			title : '销毁方式',
			width : 80,
			sortable : true
		}, {
			field : 'defects_destructionPlace',
			title : '销毁地点',
			width : 80,
			sortable : true
		}, {
			field : 'defects_destructionDate',
			title : '销毁日期',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value,"yyyy-MM-dd");
			}
		}, {
			field : 'defects_destructionSituation',
			title : '销毁后情况',
			width : 80,
			sortable : true
		}, {
			field : 'defects_transMode',
			title : '运输工具',
			width : 80,
			sortable : true
		}, {
			field : 'defects_transPerson',
			title : '运输人员',
			width : 80,
			sortable : true
		}]],
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
		}],
		onLoadSuccess:function(data){
			 if(data.rows.length == 0)
				 scrollShow($("#dg"));
		},
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

function updateFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editWindow', "quality/defectsInfo/" + node.defects_id);
}

function deleteFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/quality/defectsInfo/" + node.defects_id);
		if (data.result == "success") {
			$('#dg').datagrid('reload');
		}
		util.show(data.message);
	});
}

function scrollShow(datagrid) {  
    datagrid.prev(".datagrid-view2").children(".datagrid-body").html("<div style='width:" + datagrid.prev(".datagrid-view2").find(".datagrid-header-row").width() + "px;border:solid 0px;height:1px;'></div>");  
} 
