$(function() {
	// 初始化表格
	util.table('groupdg', {
		url : global_param.context_name + "/system/group/groups", // 数据来源
		title : '用户组列表', // 标题
		sortName : 'group_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		pagination : true, // 显示分页
		queryParams : {},
		columns : [ [ {
			field : 'group_name',
			title : '组名称',
			width : 10,
			sortable : true
		}, {
			field : 'group_id',
			title : '组ID',
			width : 10,
			sortable : true,
		}, {
			field : 'note',
			title : '备注',
			width : 10,
			sortable : true
		} ] ],
		toolbar : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : addGroup
		}, {
			text : '修改',
			iconCls : 'icon-modify',
			handler : updateGroup
		}, {
			text : '删除',
			iconCls : 'icon-delete',
			handler : deleteGroup
		} ]
	});
	// 初始化编辑窗口
	util.window('groupEditWindow', {
		title : '用户组信息'
	});
	
	var departList = util.getList(global_param.context_name + "/basInfo/basDepartmentInfo/getList");
	
	// 初始化表格
	util.table('userdg', {
		url : "", // 数据来源
		title : '用户组列表', // 标题
		sortName : 'group_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		pagination : true, // 显示分页
		queryParams : {},
		columns : [ [ {
			field : 'display_name',
			title : '用户姓名',
			width : 10,
			sortable : true
		}, {
			field : 'user_name',
			title : '登录名',
			width : 10,
			sortable : true,
		}, {
			field : 'sex',
			title : '性别',
			width : 10,
			sortable : true,
			formatter : function (value, row, index) {
				return util.getDict('SEX', value);
			}
		}, {
			field : 'tel',
			title : '电话',
			width : 10,
			sortable : true,
		}, {
			field : 'depart_id',
			title : '所属部门',
			width : 10,
			sortable : true,
			formatter : function (value, row, index) {
				for(var i = 0 ; i<departList.length ; i++){
					if(value == departList[i].department_id){
						return departList[i].department_fullname;
					}
				}
			}
		}, {
			field : 'address',
			title : '地址',
			width : 10,
			sortable : true
		}, {
			field : 'status',
			title : '状态',
			width : 10,
			sortable : true,
			formatter : function (value, row, index) {
				return util.getDict('STATUS', value);
			}
		}] ],
		toolbar : [ {
			text : '增加',
			iconCls : 'icon-add',
			width : 10,
			handler : addUser
		}, {
			text : '修改',
			iconCls : 'icon-modify',
			width : 10,
			handler : updateUser
		}, {
			text : '删除',
			iconCls : 'icon-delete',
			width : 10,
			handler : deleteUser
		}, {
			text : '启用',
			iconCls : 'icon-enable',
			width : 10,
			handler : enableUser
		}, {
			text : '停用',
			iconCls : 'icon-disable',
			width : 10,
			handler : disableUser
		}]
	});
	$('#groupdg').datagrid({
		onClickRow:function(index,data){
			var url=global_param.context_name + "/system/group/"+data.group_id+"/users/all";
			$('#userdg').datagrid('options').url=url;
			$('#userdg').datagrid('reload');
			//绑定右侧搜索栏的group_id搜索条件
			$("#search_group_id").attr("value",data.group_id);
		}
	});
	
	// 初始化编辑窗口
	util.window('userEditWindow', {
		title : '用户信息'
	});
	// 性别下拉框
	util.select({
		id : 'search_sex',
		param : {
			typeCode : 'SEX'
		}
	});
});


function queryGroup() {
	$('#groupdg').datagrid('options').queryParams = util.formParams("searchGroupForm");
	$('#groupdg').datagrid('reload');
}

function addGroup() {
	util.openWindow('groupEditWindow', "/HRERP/system/group/new");
}

function updateGroup() {
	var node = $('#groupdg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('groupEditWindow', "/HRERP/system/group/" + node.group_id);
}

function deleteGroup() {
	var node = $('#groupdg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/system/group/" + node.group_id);
		if (data.result == "success") {
			$('#groupdg').datagrid('reload');
		}
		util.show(data.message);
	});
}


function queryUser() {
	$('#userdg').datagrid('options').queryParams = util.formParams("searchUserForm");
	$('#userdg').datagrid('reload');
}

function addUser() {
	if($('#groupdg').datagrid('getSelected')){
		var group_id = $('#groupdg').datagrid('getSelected').group_id;	
		util.openWindow('userEditWindow', "/HRERP/system/user/new?group_id="+group_id);
	}else{
		util.show("请选择一个组");
	}
}

function updateUser() {
	var node = $('#userdg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('userEditWindow', "/HRERP/system/user/" + node.user_id);
}

function enableUser() {
	var node = $('#userdg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	var data = util.put(global_param.context_name + "/system/user/" + node.user_id + "/enable");
	if (data.result == "success") {
		$('#userdg').datagrid('reload');
	}
	util.show(data.message);
}

function disableUser() {
	var node = $('#userdg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	var data = util.put(global_param.context_name + "/system/user/" + node.user_id + "/disable");
	if (data.result == "success") {
		$('#userdg').datagrid('reload');
	}
	util.show(data.message);
}

function deleteUser() {
	var node = $('#userdg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/system/user/" + node.user_id);
		if (data.result == "success") {
			$('#userdg').datagrid('reload');
		}
		util.show(data.message);
	});

}
