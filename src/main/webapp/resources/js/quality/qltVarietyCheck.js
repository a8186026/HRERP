$(function() {
	var dosageTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=98");
	var unitTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=132");
	// 初始化表格
	$("#dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/quality/varietyCheck/lists", // 数据来源
		title : '重点品种信息管理', // 标题
		sortName : 'variety_checkId', // 排序的列
		sortOrder : 'asc', // 排序方式
		//fitColumns : true,
		singleSelect : true, // 单选
		pageSize : 10,
		pageList : [10, 20, 50, 100],
		fitColumns : true,
		fit : true,
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		nowrap : true,
		striped : true, // 奇偶行颜色不同
		queryParams : {},
		columns :[ [ {
			field : 'product_code',
			title : '产品编号',
			width : 80,
			sortable : true
		}, {
			field : 'product_name',
			title : '产品名称',
			width : 80,
			sortable : true
		}, {
			field : 'product_specification',
			title : '规格',
			width : 80,
			sortable : true
		}, {
			field : 'product_unit',
			title : '单位',
			width : 60,
			sortable : true,
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
			sortable : true
		}, {
			field : 'product_factoryname',
			title : '生产厂家',
			width : 100,
			sortable : true
		}, {
			field : 'product_approvalnum',
			title : '批准文号',
			width : 100,
			sortable : true
		}, {
			field : 'product_dosagetype',
			title : '剂型',
			width : 50,
			sortable : true,
			formatter :function(value, row, index){
				for(var i = 0 ; i < dosageTypes.length ;i++){
					if(value == dosageTypes[i].cbs_id){
						return dosageTypes[i].cbs_chn;
					}
				}
			}
		}, {
			field : 'variety_checkTime',
			title : '确认时间',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value);
			}
		}, {
			field : 'variety_checkPerson',
			title : '确认人',
			width : 80,
			sortable : true
		}, {
			field : 'variety_checkReason',
			title : '确认理由',
			width : 100,
			sortable : true
		}, {
			field : 'variety_majorMaintain',
			title : '养护重点',
			width : 100,
			sortable : true
		}, {
			field : 'variety_remark',
			title : '备注',
			width : 100,
			sortable : true
		}, {
			field : 'variety_startTime',
			title : '开始时间',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value);
			}
		}, {
			field : 'variety_endTime',
			title : '结束时间',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value);
			}
		} ] ],
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
		}]
	});
	// 初始化编辑窗口
	util.window('editWindow', {
		title : '重点品种信息'
	});
	
});

function query() {
	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#dg').datagrid('reload');
}

function addFunc() {
	util.openWindow('editWindow', "varietyCheck/new");
}

function updateFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editWindow', "varietyCheck/" + node.variety_checkId);
}

function deleteFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/quality/varietyCheck/" + node.variety_checkId);
		if (data.result == "success") {
			$('#dg').datagrid('reload');
		}
		util.show(data.message);
	});
}