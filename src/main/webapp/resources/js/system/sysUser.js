var columns=new Array();




$(function() {
	//获取用户习惯并且加载datagrid
	$.ajax({
		type : "get", // 表单提交类型
		url : global_param.context_name + "/system/user/getHabits?page_id=sysUserList&ctrl_id=users", // 表单提交目标
		success : function(data) {
			if(data.result=="success")
			{
				//封装datagrid的列及其属性
				columns=[];
				for(var i=0;i<data.sysUserHabits.length;i++)
				{
					var column={};
					column["field"]=data.sysUserHabits[i].habit_field;
					column["title"]=data.sysUserHabits[i].habit_field_name;	
					column["width"]=data.sysUserHabits[i].habit_width;
					column["sortable"]=true;
					if(data.sysUserHabits[i].habit_field=="sex")
					{
						column["formatter"] = function(value, row, index) {return util.getDict('SEX', value);};
					}
					if(data.sysUserHabits[i].habit_field=="status")
					{
						column["formatter"] = function (value, row, index) {return util.getDict('STATUS', value);};
					}
					columns.push(column);
				}
					
				
				// 初始化表格
				$('#users').datagrid({
					url : global_param.context_name + "/system/user/users", // 数据来源
					method:'get',
					title : '用户列表', // 标题
					sortName : 'user_id', // 排序的列
					sortOrder : 'asc', // 排序方式
					queryParams : {},
					singleSelect:true,
					pagination: true,
					rownumbers:true,
					height:790,//必须要设置高度，否则分页不会居底
					pageSize : 10,
					pageList : [10, 20, 50, 100],
					columns :[columns],
					toolbar : [ {
						text : '增加',
						iconCls : 'icon-add',
						width : 10,
						handler : addFunc
					}, {
						text : '修改',
						iconCls : 'icon-modify',
						width : 10,
						handler : updateFunc
					}, {
						text : '启用',
						iconCls : 'icon-enable',
						width : 10,
						handler : enableFunc
					}, {
						text : '停用',
						iconCls : 'icon-disable',
						width : 10,
						handler : disableFunc
					}, {
						text : '删除',
						iconCls : 'icon-delete',
						width : 10,
						handler : deleteFunc
					},{
						text : '配置',
						iconCls : 'icon-modify',
						width : 10,
						handler : configFunc
					},{
						text : '保存',
						iconCls : 'icon-add',
						width : 10,
						handler : saveConfig
					}]
				}).datagrid("columnMoving");//此处是支持列拖拽
				
				//分页
				var p = $('#users').datagrid('getPager'); 
			    $(p).pagination({ 
			        beforePageText: '第',//页数文本框前显示的汉字 
			        afterPageText: '页    共 {pages} 页', 
			        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
			    }); 
				
			}
		}
	});
	// 初始化编辑窗口
	util.window('editWindow', {
		title : '用户信息'
	});
	util.window('editWindow2', {
		title : '用户信息',
	});
	// 性别下拉框
	util.select({
		id : 'search_sex',
		param : {
			typeCode : 'SEX'
		}
	});
			
});


function query() {
	$('#users').datagrid('options').queryParams = util.formParams("searchForm");
	$('#users').datagrid('reload');
}

function addFunc() {
	util.openWindow('editWindow', "user/new");
}

function updateFunc() {
	var node = $('#users').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editWindow', "user/" + node.user_id);
}

function enableFunc() {
	var node = $('#users').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	var data = util.put(global_param.context_name + "/system/user/" + node.user_id + "/enable");
	if (data.result == "success") {
		$('#users').datagrid('reload');
	}
	util.show(data.message);
}

function disableFunc() {
	var node = $('#users').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	var data = util.put(global_param.context_name + "/system/user/" + node.user_id + "/disable");
	if (data.result == "success") {
		$('#users').datagrid('reload');
	}
	util.show(data.message);
}

function deleteFunc() {
	var node = $('#users').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/system/user/" + node.user_id);
		if (data.result == "success") {
			$('#users').datagrid('reload');
		}
		util.show(data.message);
	});

}

function configFunc() {
	util.openWindow('editWindow2', "user/config");
}

function saveConfig(){
	var fields = "";
	var fieldNames = "";
	var widths = "";
	var opts = $("#users").datagrid('getColumnFields');//获取所有列属性
	for(var i=0;i<opts.length;i++){
		fields = fields + opts[i];
		var pro = $("#users").datagrid("getColumnOption",opts[i]);
		fieldNames = fieldNames + pro.title;
		widths = widths + pro.width;
		if(i<opts.length-1)
		{
			fields = fields + ",";
			fieldNames = fieldNames + ",";
			widths = widths + ",";
		}
	}
	
	//提交用户习惯
	$.ajax({
		type : "get", // 表单提交类型
		url : global_param.context_name + "/system/user/saveHabits?fields="+fields+"&fieldNames="+fieldNames+"&widths="+widths+"&page_id=sysUserList&ctrl_id=users", // 表单提交目标
		success : function(data) {
			if(data.result=="success")
			{
				util.show("保存成功!");
			}
		}
	});
}