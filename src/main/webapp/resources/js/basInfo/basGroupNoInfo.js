$(function() {
	
	$('#department_id').combobox({//库房信息的下拉框列表
		url : global_param.context_name + "/basInfo/basGroupNoInfo/getDepartmentList",
		method :"get",
		valueField : "department_id",
		textField : "department_fullname",
	    onSelect:function(record){  
	    	//根据库房信息查到下级组号信息
	    	var url = global_param.context_name + "/basInfo/basGroupNoInfo/groupNOList?department_id=" + record.department_id;
	        $('#pro_group_no').combobox('reload',url);
	        $('#pro_group_no').combobox('clear');
	    }  
	});  

	$('#pro_group_no').combobox({//组号信息的下拉框列表，是库房的下级列表，所以不指定url，
		method :"get",
		valueField : "pro_group_no",
		textField : "pro_group_no",
	});
	
	// 初始化表格
	$("#dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/basInfo/basGroupNoInfo/lists", // 数据来源
		title : '组号信息管理', // 标题
		sortName : 'prod_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitcolumns : true,//使列自动展开/收缩到合适的DataGrid宽度。
		singleSelect : true,//单选
		fit:true,
		pageSize : 10,
		pageList : [10,20,50,100],
		pagination : true,
		rownumbers : true,
		nowrap : true,
		striped : true,
		queryParams : {},
		columns:[[{
			field : 'product_id',
			title : '产品id',
			width : 120,
			hidden : true//在edit页面中用于查询显示数据，这里隐藏，不在datagrid列表里显示。
		},{
			field : 'product_commonname',
			title : '产品名称',
			width : 100,
			sortable : true
		},{
			field : 'department_fullname',
			title : '库房',
			width : 120,
			sortable : true
		},{
			field : 'department_id',
			title : '库房id',
			width : 120,
			hidden : true//在edit页面中用于查询显示数据，这里隐藏，不在datagrid列表里显示。
		},{
			field : 'pro_group_no',
			title : '组号',
			width : 60,
			sortable : true
		},{
			field : 'pro_location_no',
			title : '货位号',
			width : 60,
			sortable : true
		}] ],
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
		title : '组号信息管理',
		width:400,    
	    height:240
	});
});

function query() {//searchForm查询方法

	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#dg').datagrid('reload');
	$('#pro_group_no').combobox('clear');
}
function clearSearchForm(){//searchForm清空方法，重置后，清空组号下拉框内信息，避免只对组号进行查询。
	$('#searchForm').form('reset');
	$('#pro_group_no').combobox('loadData',[{text:'', value:'1'}]);//用loadData来清空组号下拉框。
}

function addFunc() {
	util.openWindow('editWindow', "basGroupNoInfo/new" );
}

function updateFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	//在edit页面中，department_id用于查询库房name。
	util.openWindow('editWindow', "basGroupNoInfo/update/?prod_id=" + node.prod_id + "&department_id=" + node.department_id + "&product_id="+ node.product_id);
}

function deleteFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/basInfo/basGroupNoInfo/" + node.prod_id);
		if (data.result == "success") {
			$('#dg').datagrid('reload');
		}
		util.show(data.message);
	});
}