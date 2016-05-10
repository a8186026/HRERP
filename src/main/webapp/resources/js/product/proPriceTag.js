$(function() {
	var dosageTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=98");
	var units = util.getList(global_param.context_name + "/system/combobox/lists?pid=132");
	var othercategorys = util.getList(global_param.context_name + "/system/combobox/lists?pid=244");

	// 初始化表格
	$("#dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/product/proPriceTag/lists", // 数据来源
		title : '价签申请管理', // 标题
		sortName : 'priceTag_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		//fitColumns : true,
		singleSelect : true, // 单选
		//pageSize : 10,
		///pageList : [10, 20, 50, 100],
		fit : true,
		//striped : true, // 奇偶行颜色不同
		//pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		nowrap : true,
		striped : true, // 奇偶行颜色不同
		queryParams : {},
		columns :
			[ [ {
			field : 'priceTag_id',
			title : '序号',
			width : 100,
			sortable : true
		}, { 
			field : 'product_code',
			title : '产品编号',
			width : 100,
			sortable : true
		}, {
			field : 'product_name',
			title : '产品名称',
			width : 100,
			sortable : true
		},  {
			field : 'product_specification',
			title : '规格',
			width : 100,
			sortable : true
		},  {
			field : 'product_productarea',
			title : '产地',
			width : 100,
			sortable : true
		},  {
			field : 'product_unit',
			title : '单位',
			sortable : true,
			formatter :function(value, row, index){
				for(var i = 0 ; i < units.length ;i++){
					if(value == units[i].cbs_id){
						return units[i].cbs_chn;
					}
				}
			}
		},  {
			field : 'product_dosagetype',
			title : '剂型',
			width : 100,
			sortable : true,
			formatter :function(value, row, index){
				for(var i = 0 ; i < dosageTypes.length ;i++){
					if(value == dosageTypes[i].cbs_id){
						return dosageTypes[i].cbs_chn;
					}
				}
				return value;
			}
		},  {
			field : 'product_retailprice',
			title : '零售价',
			width : 100,
			sortable : true
		}, {
			field : 'product_memberprice',
			title : '会员价',
			width : 100,
			sortable : true
		},  {
			field : 'product_othercategory',
			title : '其它分类',
			width : 100,
			sortable : true,
			formatter :function(value, row, index){
				for(var i = 0 ; i < othercategorys.length ;i++){
					if(value == othercategorys[i].cbs_id){
						return othercategorys[i].cbs_chn;
					}
				}
			}
		}] ],
		toolbar : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : addFunc
		},/*{
			text : '修改',
			iconCls : 'icon-modify',
			handler : updateFunc
		},*/{
			text : '删除',
			iconCls : 'icon-delete',
			handler : deleteFunc
		}]
	});
	
	
	
	// 初始化编辑窗口
	util.window('editWindow', {
		title : '价签申请'
	});
});

function query() {
	$('#dg').datagrid('options').queryParams = {"start_time":$("#search_start_time").datebox("getValue"),"end_time":$("#search_end_time").datebox("getValue")};
	$('#dg').datagrid('reload');
}

function addFunc() {
	util.openWindow('editWindow', global_param.context_name + "/product/proPriceTag/new");
}


function deleteFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/product/proPriceTag/" + node.priceTag_id);
		if (data.result == "success") {
			$('#dg').datagrid('reload');
		}
		util.show(data.message);
	});
}

