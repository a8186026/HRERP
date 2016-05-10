$(function() {
	
	
	var data = util.get(global_param.context_name + "/basInfo/basDepartmentInfo/getList");
	
	$('#search_department_id').combobox({
		data:data,
		valueField : "department_id",
		textField : "department_fullname",
		editable:false,
	});

	
	// 初始化表格
	$("#dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/giftCard/giftCardManage/lists", // 数据来源
		title : '代金卡管理', // 标题
		sortName : 'gift_card_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		//fitColumns : true,
		singleSelect : true, // 单选
		pageSize : 10,
		pageList : [10, 20, 50, 100],
		fit : true,
		//striped : true, // 奇偶行颜色不同
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		nowrap : true,
		striped : true, // 奇偶行颜色不同
		queryParams : {},
		columns :
			[ [ {
				field : 'gift_card_id',
				title : '代金卡id',
				width : 100,
				sortable : true
		}, {
			field : 'department_id',
			title : '部门',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				for(var i = 0; i <data.length; i++){
					if(value==data[i].department_id){
						return data[i].department_fullname;
					}
				}
				return "出错了";
			}
		}, { 
			field : 'gift_card_fulfilAmount',
			title : '代金卡满足金额',
			width : 100,
			sortable : true
		}, {
			field : 'gift_card_offsetAmount',
			title : '代金卡抵消金额',
			width : 100,
			sortable : true
		}] ],
		toolbar : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : addFunc
		},{
			text : '修改',
			iconCls : 'icon-modify',
			handler : updateFunc
		},{
			text : '删除',
			iconCls : 'icon-delete',
			handler : deleteFunc
		}]
	});
	
	
	
	// 初始化编辑窗口
	util.window('editWindow', {
		title : '代金卡信息'
	});
});

function query() {
	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#dg').datagrid('reload');
}

function addFunc() {
	util.openWindow('editWindow', "giftCardManage/new");
}

function updateFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editWindow', "giftCardManage/" + node.gift_card_id);
}

function deleteFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/giftCard/giftCardManage/" + node.gift_card_id);
		if (data.result == "success") {
			$('#dg').datagrid('reload');
		}
		util.show(data.message);
	});
}


