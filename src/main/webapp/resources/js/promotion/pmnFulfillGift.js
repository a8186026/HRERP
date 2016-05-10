var gift_sal_id = null;
var data = util.get(global_param.context_name + "/system/combobox/lists?pid=983");

var basDepartmentInfo = util.get(global_param.context_name + "/basInfo/basDepartmentInfo/getList");






$(function() {
	
	$('#search_full_gift_exchangeRule').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=983",
		method :"get",
		valueField : "cbs_id",
		textField : "cbs_chn",
		editable:false,
/*		onLoadSuccess: function(){
			data = $('#search_full_gift_exchangeRule').combobox("getData");
		}*/
	});
	
	$('#search_full_gift_joinDepartment').combobox({
		url:global_param.context_name + "/basInfo/basDepartmentInfo/getList",
		method :"get",
		valueField : "department_id",
		textField : "department_fullname",
		editable:false,
/*		onLoadSuccess: function(){
			data = $('#search_full_gift_exchangeRule').combobox("getData");
		}*/
	});
	
	
	
	// 初始化表格
	$("#FulfillGiftDg").datagrid({
		method : 'get',
		url : global_param.context_name + "/promotion/fulfillgift/lists", // 数据来源
		title : '满额赠信息管理', // 标题
		sortName : 'full_gift_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitColumns : true,
		singleSelect : true, // 单选
		pageSize : 10,
		pageList : [10, 20, 50, 100],
		fit : true,
		//height:400,
		//striped : true, // 奇偶行颜色不同
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		striped : true, // 奇偶行颜色不同
		queryParams : {},
		columns:[[{
			field : 'full_gift_id',
			title : '序号',
			width : 80,
			sortable : true
		}, {
			field : 'product_id',
			title : '产品序号',
			width : 100,
			sortable : true
		}, {
			field : 'full_gift_unitPrice',
			title : '单价',
			width : 55,
			sortable : true
		},{
			field : 'full_gift_amount',
			title : '数量',
			width : 100,
			sortable : true
		}, {
			field : 'full_gift_exchangeAmount',
			title : '兑换金额',
			width : 100,
			sortable : true
		},  {
			field : 'full_gift_exchangeRule',
			title : '兑换规则',
			width : 80,
			sortable : true,
			formatter : function (value, row, index) {
				for(var i = 0; i <data.length; i++){
					if(value == data[i].cbs_id)
						return data[i].cbs_chn;
				}
				return "崩了";
			}
		}, {
			field : 'full_gift_startDate',
			title : '开始日期',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value);
			}
		}, {
			field : 'full_gift_endDate',
			title : '结束日期',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value);
			}
		}, {
			field : 'full_gift_productPoint',
			title : '产品积分',
			width : 50,
			sortable : true
		}, {
			field : 'full_gift_joinDepartment',
			title : '参与部门',
			width : 300,
			sortable : true,
			formatter : function (value, row, index) {
				var str = "";
				for(var i = 0; i <basDepartmentInfo.length; i++){
					if(value.indexOf(","+basDepartmentInfo[i].department_id+",")!= -1){
						str += basDepartmentInfo[i].department_fullname+",";
					}
				}
				return str.substring(0,str.length-1);
			}
		},{
			field : 'full_gift_retailCommission',
			title : '零售提成',
			width : 50,
			sortable : true
		}, {
			field : 'full_gift_remark',
			title : '备注',
			width : 50,
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
/*		onClickRow:function(rowIndex,rowData){
			gift_sal_id = rowData.gift_sal_id;
			$('#GiftInfoDg').datagrid('options').queryParams.gift_sal_id = rowData.gift_sal_id;
			$('#GiftInfoDg').datagrid('options').url = global_param.context_name + "/promotion/giftinfo/listsVO";
			$('#GiftInfoDg').datagrid('reload');
		}*/
	});
	// 初始化编辑窗口
	util.window('editWindow', {
		title : '满额赠信息'
	});	
	util.window('editGiftInfoWindow', {
		title : '买赠产品信息'
	});	
	util.window('editFulfillProductWindow', {
		title : '参与产品信息',
		width : 1000,
		height : 800,
	});	
			
});

function query() {
	$('#FulfillGiftDg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#FulfillGiftDg').datagrid('reload');
}

function addFunc() {
	util.openWindow('editWindow', global_param.context_name + "/promotion/fulfillgift/new");
}

function updateFunc() {
	var node = $('#FulfillGiftDg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editWindow', "fulfillgift/" + node.full_gift_id);
}

function deleteFunc() {
	var node = $('#FulfillGiftDg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/promotion/fulfillgift/" + node.full_gift_id);
		if (data.result == "success") {
			$('#FulfillGiftDg').datagrid('reload');
		}
		util.show(data.message);
	});
}
