
var packingunitTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=125");
$(function() {
	// 初始化表格
	$("#dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/stock/stockInfo/listBatch", // 数据来源
		title : '批次信息', // 标题
		sortName : 'stock_info_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitColumns : true,
		singleSelect : false, // 单选
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
			field : 'product_name',
			title : '产品名称',
			width : 80,
			sortable : true
		},{
			field : 'product_code',
			title : '产品编号',
			width : 80,
			sortable : true
		}, {
			field : 'stock_batchCode',
			title : '产品批号',
			width : 80,
			sortable : true
		},{
			field : 'stock_produceDate',
			title : '生产日期',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value);
			}
		}, {
			field : 'stock_invalidDate',
			title : '失效期',
			width : 100,
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
			field : 'stock_storageNumber',
			title : '剩余库存',
			width : 100,
			sortable : true
		}]],
		toolbar : [{
			text : '效期修改',
			iconCls : 'icon-modify',
			handler : modify
		}]
	});
	// 初始化编辑窗口
	util.window('editWindow', {
		title : '批次效期修改信息'
	});
});

function query() {
	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#dg').datagrid('reload');
}

function modify() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editWindow', "/HRERP/stock/stockInfo/" + node.stock_info_id);
}