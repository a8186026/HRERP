$(function() {
	var dosageTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=98");
	var unitTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=132");
	
	// 初始化表格
	$("#dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/quality/maintainInfo/lists", // 数据来源
		title : '养护情况登记', // 标题
		sortName : 'maintain_id', // 排序的列
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
			field : 'maintain_ticketNo',
			title : '养护票号',
			width : 100,
			sortable : true
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
			field : 'stock_batchCode',
			title : '批号',
			width : 80,
			sortable : true
		}, {
			field : 'stock_storage',
			title : '库房',
			width : 80,
			sortable : true
		}, {
			field : 'pro_group_no',
			title : '组号',
			width : 80,
		}, {
			field : 'pro_location_no',
			title : '货位号',
			width : 80,
		}, {
			field : 'product_code',
			title : '产品编号',
			width : 80,
		}, {
			field : 'product_name',
			title : '产品名称',
			width : 80,
		}, {
			field : 'product_immaintain',
			title : '重点品种',
			formatter:function(value){
				if(value==0){
					return "<font color='green'>普通品种</font>";
				}else if(value==1){
					return "<font color='red'>重点品种</font>";
				}
			}
		}, {
			field : 'maintain_checkTime',
			title : '检查日期',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value,"yyyy-MM-dd");
			}
		}, {
			field : 'maintain_number',
			title : '库房数量',
			width : 80,
			sortable : true
		}, {
			field : 'product_specification',
			title : '规格',
			width : 80,
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
		},  {
			field : 'product_productarea',
			title : '产地',
			width : 100,
		}, {
			field : 'product_factoryname',
			title : '生产厂家',
			width : 100,
		}, {
			field : 'product_approvalnum',
			title : '批准文号',
			width : 100,
		}, {
			field : 'stock_invalidDate',
			title : '失效期',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value,"yyyy-MM-dd");
			}
		}, {
			field : 'stock_intakeCheckTime',
			title : '入库日期',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value,"yyyy-MM-dd");
			}
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
			field : 'maintain_storagePlace',
			title : '存放地点',
			width : 80,
			sortable : true
		}, {
			field : 'maintain_time',
			title : '养护时间',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value);
			}
		}, {
			field : 'maintain_days',
			title : '养护天数',
			width : 80,
		}, {
			field : 'maintain_person',
			title : '养护员',
			width : 80,
		}, {
			field : 'maintain_quality',
			title : '质量状况',
			width : 80,
		}, {
			field : 'maintain_measure',
			title : '养护措施',
			width : 80,
			sortable : true
		}, {
			field : 'maintain_suggestion',
			title : '处理意见',
			width : 80,
		}, {
			field : 'maintain_remark',
			title : '备注',
			width : 80,
		} ]],
		toolbar : [ {
			text : '登记确认',
			iconCls : 'icon-modify',
			handler : updateMaintainFunc
		}, {
			text : '删除',
			iconCls : 'icon-delete',
			handler : deleteMaintainFunc
		} ],
		onLoadSuccess:function(data){
			 if(data.rows.length == 0)
				 scrollShow($("#dg"));
		},
	});
	// 初始化编辑窗口
	util.window('editWindow', {
		title : '养护情况登记'
	});
	
});

function query() {
	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#dg').datagrid('reload');
}

function updateMaintainFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editWindow', "maintainInfo/" + node.maintain_id);
}

function deleteMaintainFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/quality/maintainInfo/" + node.maintain_id);
		if (data.result == "success") {
			$('#dg').datagrid('reload');
		}
		util.show(data.message);
	});
} 

function scrollShow(datagrid) {  
    datagrid.prev(".datagrid-view2").children(".datagrid-body").html("<div style='width:" + datagrid.prev(".datagrid-view2").find(".datagrid-header-row").width() + "px;border:solid 0px;height:1px;'></div>");  
} 
