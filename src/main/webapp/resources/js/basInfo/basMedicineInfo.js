$(function() {
	
	//第一个参数页面ID,第二个参数datagrid ID，第三个参数配置显示框ID,第四个参数是否启动上下键换行，第五个参数是否启动列拖拽
	var userHabits1 = new userHabits();
	userHabits1.loadColumns("basMedicineInfoList","dg","editWindow",true,true);
	
	// 初始化表格
	$("#dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/basInfo/basMedicineInfo/lists", // 数据来源
		title : '药品信息管理', // 标题
		sortName : 'medicine_id', // 排序的列
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
		/*columns :
			[ [ {
			field : 'medicine_code',
			title : '药品编码',
			width : 100,
			sortable : true
		}, { 
			field : 'medicine_name',
			title : '药品名称',
			width : 100,
			sortable : true
		}, {
			field : 'medicine_proname',
			title : '学名',
			width : 100,
			sortable : true
		},  {
			field : 'medicine_engname',
			title : '药品英文名',
			width : 100,
			sortable : true
		},  {
			field : 'medicine_chnpy',
			title : '拼音码',
			width : 100,
			sortable : true
		},  {
			field : 'medicine_effect',
			title : '功效',
			sortable : true
		},  {
			field : 'medicine_note',
			title : '注意事项',
			width : 100,
			sortable : true
		},  {
			field : 'medicine_description',
			title : '药品简介',
			width : 100,
			sortable : true
		},  {
			field : 'medicine_update_time',
			title : '药品更新时间',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value);
			}
		}] ],*/
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
		title : '药品信息'
	});
});

function query() {
	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#dg').datagrid('reload');
}

function addFunc() {
	util.openWindow('editWindow', "basMedicineInfo/new");
}

function updateFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editWindow', "basMedicineInfo/" + node.medicine_id);
}

function deleteFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/basInfo/basMedicineInfo/" + node.medicine_id);
		if (data.result == "success") {
			$('#dg').datagrid('reload');
		}
		util.show(data.message);
	});
}

function initDatagrid(columns){
	// 初始化表格
	$("#dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/basInfo/basMedicineInfo/lists", // 数据来源
		title : '药品信息管理', // 标题
		sortName : 'medicine_id', // 排序的列
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
		columns : [columns],
			/*[ [ {
			field : 'medicine_code',
			title : '药品编码',
			width : 100,
			sortable : true
		}, { 
			field : 'medicine_name',
			title : '药品名称',
			width : 100,
			sortable : true
		}, {
			field : 'medicine_proname',
			title : '学名',
			width : 100,
			sortable : true
		},  {
			field : 'medicine_engname',
			title : '药品英文名',
			width : 100,
			sortable : true
		},  {
			field : 'medicine_chnpy',
			title : '拼音码',
			width : 100,
			sortable : true
		},  {
			field : 'medicine_effect',
			title : '功效',
			sortable : true
		},  {
			field : 'medicine_note',
			title : '注意事项',
			width : 100,
			sortable : true
		},  {
			field : 'medicine_description',
			title : '药品简介',
			width : 100,
			sortable : true
		},  {
			field : 'medicine_update_time',
			title : '药品更新时间',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value);
			}
		}] ],*/
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
	//$('#dg').datagrid("columnMoving");
	//$('#dg').datagrid("keyCtr").datagrid("columnMoving");
	//$('#dg').datagrid("columnMoving");
	// 初始化编辑窗口
	util.window('editWindow', {
		title : '药品信息'
	});
}

