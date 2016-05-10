var unitTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=132");
$(function() {
	$('#user_name').attr("value", function() {
		var username = util.get(global_param.context_name + "/purchase/acceptReject/getUserName");
		return username;
	});
	
	// 初始化表格
	$("#dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/store/inventroyCheck/lists", // 数据来源
		title : '盘点损益申请', // 标题
		sortName : 'inventory_id', // 排序的列
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
		columns :[[
		{
			field : ' inventory_operator',
			title : '操作员',
			width : 50,
			sortable : true
		},  {
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
			width : 60,
			sortable : true
		}, {
			field : 'product_unit',
			title : '单位',
			width : 60,
			sortable : true,
			formatter : function (value, row, index) {
				for(var i = 0 ; i < unitTypes.length ;i++){
					if(value == unitTypes[i].cbs_id){
						return unitTypes[i].cbs_chn;
					}
				}
			}
		}, {
			field : 'product_productarea',
			title : '产地',
			width : 100,
			sortable : true
		}, {
			field : 'department_fullname',
			title : '库房'	,
			width : 60,
			sortable : true
		}, {
			field : 'stock_intakeTicket',
			title : '入库票号',
			width : 100,
			sortable : true
		},	{
			field : 'stock_intakeSmallNumber',
			title : '入库小号',
			width : 60,
			sortable : true
		}, {
			field : 'stock_batchCode',
			title : '批号',
			width : 60,
			sortable : true
		}, {
			field : 'stock_invalidDate',
			title : '失效期',
			width : 80,
			sortable : true,
			formatter: function(value,row,index){
				return util.formatDate(value);
			}
		}, {
			field : 'inventory_countedNumber',
			title : '盘点数量',
			width : 60,
			sortable : true
		}, {
			field : 'stock_purchasePrice',
			title : '进价',
			width : 60,
			sortable : true
		},{
			field : 'inventory_profitLossNumber',
			title : '损益数量',
			width : 60,
			sortable : true
		},{
			field : 'stock_inventory_money',
			title : '损益金额',
			width : 60,
			sortable : true,
			formatter : function (value, row, index) {
				return Number(row.inventory_profitLossNumber) * Number(row.stock_purchasePrice);
			}
		},{
			field : 'inventory_check_status',
			title : '拒收状态',
			width : 60,
			sortable : true,
			formatter:function(value){
					return "<font color='red'>未审核</font>";
			}
		}]],
		toolbar : [{
			text : '审核',
			iconCls : 'icon-modify',
			handler : inventoryCheck
		}]
	});
	// 初始化编辑窗口
	util.window('editWindow', {
		title : '盘点损益审核'
	});
});
function query() {
	$('#dg').datagrid('options').queryParams = util.formParams("");
	$('#dg').datagrid('reload');
}
function inventoryCheck(){
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定通过这条申请吗？", function() {
		var data = util.get( "/HRERP/store/inventroyCheck/checked?inventory_id=" + node.inventory_id);
		if (data.result == "success") {
			$('#dg').datagrid('reload');
		}
		util.show(data.message);
	});
}
