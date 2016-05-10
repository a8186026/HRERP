<%@page contentType="text/html;charset=UTF-8"%>

<script type="text/javascript">
$(function() {
	// 初始化表格
	util.table('function_grid', {
		url : global_param.context_name + "/system/function/functions", // 数据来源
		title : '菜单功能列表', // 标题
		idField : 'func_id',
		sortName : 'func_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		singleSelect : false, // 多选
		pagination : false, // 不显示分页
		queryParams : {},
		columns : [ [ {
			field : 'ck',
			checkbox : true,
			width : 2
		}, {
			field : 'func_name',
			title : '名称',
			width : 10,
			sortable : true
		} ] ],
		toolbar : [ {
			text : '保存',
			iconCls : 'icon-save',
			handler : saveFunc
		} ],
		onLoadSuccess : function(data) {
			var v = ${value};
			for (var i = 0; i < data.rows.length; i++) {
				if ((v & 1 << data.rows[i].func_position) != 0) {
					$("#function_grid").datagrid('selectRecord', data.rows[i].func_id);
				}
			}
		}
	});
});

function saveFunc() {
	var data = $("#function_grid").datagrid('getSelections');
	var r = 0;
	var id = ${id};
	for (var i = 0; i < data.length; i++) {
		r = r | (1 << data[i].func_position);
	}
// 	$('#menu_grid').treegrid('update',{
// 		id: id,
// 		row: {
// 			menu_func : r
// 		}
// 	});
	setFuncValue(id, r);
	util.closeWindow('funcWindow');
}
</script>

<div class="easyui-layout" fit="true">
	<div data-options="region:'center',border:false"
		style="overflow: hidden; background: #fff;">
		<table id="function_grid"></table>
	</div>
</div>