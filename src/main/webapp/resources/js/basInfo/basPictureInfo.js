$(function() {
	// 初始化表格
	$("#pictureDg").datagrid({
		method : 'get',
		url : global_param.context_name + "/basInfo/basPictureInfo/lists", // 数据来源
		title : '图片信息管理', // 标题
		sortName : 'picture_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitColumns : true,
		singleSelect : true, // 单选
		pageSize : 10,
		pageList : [10, 20, 50, 100],
		fit : true,
		width : '570px',
		height : '600px',
		//striped : true, // 奇偶行颜色不同
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		nowrap : true,
		striped : true, // 奇偶行颜色不同
		queryParams : {},
		columns:[[{
			field : 'picture_name',
			title : '图片名称',
			width : 100,
			sortable : true
		}, {
			field : 'picture_position',
			title : '图片位置',
			width : 100,
			sortable : true
		}, {
			field : 'picture_remark',
			title : '图片备注',
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
	util.window('editPictureWindow', {
		title : '图片信息'
	});
	
});

function query() {
	$('#pictureDg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#pictureDg').datagrid('reload');
}

function addFunc() {
	util.openWindow('editPictureWindow', "/HRERP/basInfo/basPictureInfo/new");
}

function updateFunc() {
	var node = $('#pictureDg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editPictureWindow', "basPictureInfo/" + node.picture_id);
}

function deleteFunc() {
	var node = $('#pictureDg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/basInfo/basPictureInfo/" + node.picture_id);
		if (data.result == "success") {
			$('#pictureDg').datagrid('reload');
		}
		util.show(data.message);
	});
}