var gift_sal_id = null;

var data = util.get(global_param.context_name + "/basInfo/basDepartmentInfo/getList");

$(function() {
	// 初始化表格
	$("#GiftSaleDg").datagrid({
		method : 'get',
		url : global_param.context_name + "/promotion/giftsale/lists", // 数据来源
		title : '买赠信息管理', // 标题
		sortName : 'gift_sal_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitColumns : true,
		singleSelect : true, // 单选
		pageSize : 10,
		pageList : [10, 20, 50, 100],
		//fit : true,
		height:400,
		//striped : true, // 奇偶行颜色不同
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		striped : true, // 奇偶行颜色不同
		queryParams : {},
		columns:[[{
			field : 'gift_sal_code',
			title : '编号',
			width : 80,
			sortable : true
		}, {
			field : 'gift_sal_activityTitle',
			title : '活动标题',
			width : 100,
			sortable : true
		}, {
			field : 'gift_sal_fullGiveCoefficient',
			title : '满额赠系数',
			width : 55,
			sortable : true
		}, {
			field : 'gift_sal_introduction',
			title : '说明',
			width : 100,
			sortable : true
		}, {
			field : 'gift_sal_name',
			title : '名称',
			width : 100,
			sortable : true
		}, {
			field : 'gift_sal_chn',
			title : '拼音码',
			width : 80,
			sortable : true
		}, {
			field : 'gift_sal_startDate',
			title : '开始日期',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value);
			}
		}, {
			field : 'gift_sal_endDate',
			title : '结束日期',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value);
			}
		}, {
			field : 'gift_sal_productPoint',
			title : '产品积分',
			width : 50,
			sortable : true,
		}, {
			field : 'gift_sal_joinDepartment',
			title : '参与部门',
			width : 300,
			sortable : true,
			formatter : function (value, row, index) {
				var str = "";
				for(var i = 0; i <data.length; i++){
					if(value.indexOf(","+data[i].department_id+",")!= -1){
						str += data[i].department_fullname+",";
					}
				}
				return str.substring(0,str.length-1);
			}
		}, {
			field : 'gift_sal_retailPrice',
			title : '零售价',
			width : 50,
			sortable : true,
		}, {
			field : 'gift_sal_commission',
			title : '提成',
			width : 50,
			sortable : true,
		}, {
			field : 'gift_sal_memberPrice',
			title : '会员价',
			width : 50,
			sortable : true
		}, {
			field : 'gift_sal_joinProductId',
			title : '参与产品序号',
			width : 100,
			sortable : true,
		}]],
		toolbar : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : addGiftSaleFunc,
		}, {
			text : '修改',
			iconCls : 'icon-modify',
			handler : updateGiftSaleFunc,
		}, {
			text : '删除',
			iconCls : 'icon-delete',
			handler : deleteGiftSaleFunc,
		}],
		onClickRow:function(rowIndex,rowData){
			gift_sal_id = rowData.gift_sal_id;
			$('#GiftInfoDg').datagrid('options').queryParams.gift_sal_id = rowData.gift_sal_id;
			$('#GiftInfoDg').datagrid('options').url = global_param.context_name + "/promotion/giftinfo/listsVO";
			$('#GiftInfoDg').datagrid('reload');
		},
	});
	// 初始化编辑窗口
	util.window('editWindow', {
		title : '买赠信息'
	});	
	util.window('editGiftInfoWindow', {
		title : '买赠产品信息'
	});	
	
	
	$("#GiftInfoDg").datagrid({
		method : 'get',
		//url : global_param.context_name + "/promotion/giftsale/lists", // 数据来源
		title : '买赠产品信息管理', // 标题
		sortName : 'gift_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		singleSelect : true, // 单选
		pageSize : 10,
		pageList : [10, 20, 50, 100],
		fit : true,
		//striped : true, // 奇偶行颜色不同
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		striped : true, // 奇偶行颜色不同
		queryParams : {},
		columns:[[{
			field : 'gift_varietyId',
			title : '品种序号',
			width : 80,
			sortable : true,
		}, {
			field : 'gift_productId',
			title : '产品序号',
			width : 100,
			sortable : true,
		}, {
			field : 'product_name',
			title : '产品名称',
			width : 200,
			sortable : true,
		}, {
			field : 'product_specification',
			title : '规格',
			width : 100,
			sortable : true,
		}, {
			field : 'product_unit',
			title : '单位',
			width : 100,
			sortable : true,
		}, {
			field : 'product_productarea',
			title : '产地',
			width : 200,
			sortable : true,
		}, {
			field : 'gift_amount',
			title : '数量',
			width : 100,
			sortable : true,
		}, {
			field : 'gift_retailPrice',
			title : '零售价',
			width : 100,
			sortable : true,
		}, {
			field : 'gift_memberPrice',
			title : '会员价',
			width : 100,
			sortable : true,
		}, {
			field : 'gift_commission',
			title : '提成',
			width : 100,
			sortable : true,
		}]],
		toolbar : [{
			text : '删除',
			iconCls : 'icon-add',
			handler : deleteGiftInfoFunc,
		}, {
			text : '增加买赠产品信息',
			iconCls : 'icon-add',
			handler : addGiftInfoFunc,
		}]
	});

});

function query() {
	$('#GiftSaleDg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#GiftSaleDg').datagrid('reload');
}

function addGiftSaleFunc() {
	util.openWindow('editWindow', global_param.context_name + "/promotion/giftsale/new");
}

function updateGiftSaleFunc() {
	var node = $('#GiftSaleDg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editWindow', "giftsale/" + node.gift_sal_id);
}

function deleteGiftSaleFunc() {
	var node = $('#GiftSaleDg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/promotion/giftsale/" + node.gift_sal_id);
		if (data.result == "success") {
			$('#GiftSaleDg').datagrid('reload');
		}
		util.show(data.message);
	});
}



function addGiftInfoFunc() {
	var node = $('#GiftSaleDg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editGiftInfoWindow', "giftsale/addgift/" + node.gift_sal_id);
}

function deleteGiftInfoFunc() {
	var node = $('#GiftInfoDg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/promotion/giftinfo/" + node.gift_id+"?giftSaleId="+gift_sal_id);
		if (data.result == "success") {
			$('#GiftInfoDg').datagrid('reload');
			$('#GiftSaleDg').datagrid('reload');
		}
		util.show(data.message);
	});
};
