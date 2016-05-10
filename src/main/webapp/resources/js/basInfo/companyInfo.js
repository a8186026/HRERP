$(function() {
	//第一个参数页面ID,第二个参数datagrid ID，第三个参数配置显示框ID,第四个参数是否启动上下键换行，第五个参数是否启动列拖拽
	//loadColumns("basLicenseInfoList","dg","editWindow",true,true);
	
	// 初始化表格
	$("#companyInfoDg").datagrid({
		method : 'get',
		url : global_param.context_name + "/basInfo/companyInfo/lists", // 数据来源
		title : '公司信息管理', // 标题
		sortName : 'company_id', // 排序的列
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
			field : 'company_fullName',
			title : '企业全称',
			width : 80,
			sortable : true
		}, {
			field : 'company_code',
			title : '企业编码',
			width : 100,
			sortable : true
		}, {
			field : 'company_lisenceCode',
			title : '许可证编号',
			width : 100,
			sortable : true
		}, {
			field : 'company_drugCode',
			title : '国家药监码',
			width : 100,
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
		} ]
	});
	// 初始化编辑窗口
	util.window('editWindow', {
		title : '财务信息'
	});	
});

function query() {
	$('#companyInfoDg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#companyInfoDg').datagrid('reload');
}
//???如何获取供方对应的财务信息
function addFunc() {
	util.openWindow('editWindow', "/HRERP/basInfo/companyInfo/new");
}

function updateFunc() {
	var node = $('#companyInfoDg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editWindow', "/HRERP/basInfo/companyInfo/" + node.company_id);
}

function deleteFunc() {
	var node = $('#companyInfoDg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/basInfo/companyInfo/" + node.company_id);
		if (data.result == "success") {
			$('#companyInfoDg').datagrid('reload');
		}
		util.show(data.message);
	});
}