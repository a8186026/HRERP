$(function() {
	//第一个参数页面ID,第二个参数datagrid ID，第三个参数配置显示框ID,第四个参数是否启动上下键换行，第五个参数是否启动列拖拽
	//loadColumns("basLicenseInfoList","dg","editWindow",true,true);
	
	// 初始化表格
	$("#AccountDg").datagrid({
		method : 'get',
		url : global_param.context_name + "/basInfo/basAccount/lists", // 数据来源
		title : '财务信息管理', // 标题
		sortName : 'acc_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitColumns : true,
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
		columns:[[{
			field : 'acc_name',
			title : '开户名称',
			width : 80,
			sortable : true
		}, {
			field : 'acc_bank',
			title : '开户银行',
			width : 100,
			sortable : true
		}, {
			field : 'acc_no',
			title : '开户账号',
			width : 100,
			sortable : true
		}]],
		toolbar : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : addAccountFunc
		}, {
			text : '修改',
			iconCls : 'icon-modify',
			handler : updateAccountFunc
		}, {
			text : '删除',
			iconCls : 'icon-delete',
			handler : deleteAccountFunc
		} ]
	});
	// 初始化编辑窗口
	util.window('editAccountWindow', {
		title : '财务信息'
	});	
});

function query() {
	$('#AccountDg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#AccountDg').datagrid('reload');
}
//???如何获取供方对应的财务信息
function addAccountFunc() {
	util.openWindow('editAccountWindow', "/HRERP/basInfo/basAccount/new");
}

function updateAccountFunc() {
	var node = $('#AccountDg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editAccountWindow', "/HRERP/basInfo/basAccount/" + node.acc_id);
}

function deleteAccountFunc() {
	var node = $('#AccountDg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/basInfo/basAccount/" + node.acc_id);
		if (data.result == "success") {
			$('#AccountDg').datagrid('reload');
		}
		util.show(data.message);
	});
}