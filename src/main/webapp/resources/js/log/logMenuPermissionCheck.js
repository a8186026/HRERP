var userList = null;
$(function(){
	$('#search_group_id').combobox({
		url : global_param.context_name
				+ "/system/permission/groups",
		method : 'get',
		valueField : 'group_id',
		textField : 'group_name',
		editable:false
	});
	
	userList = util.getList(global_param.context_name + "/system/user/users");
	// 初始化表格
	util.table('dg', {
		url : global_param.context_name + "/log/logManage/menuPermissionCheckLogList", // 数据来源
		title : '菜单权限审核列表', // 标题
		idField : 'menu_id',
		sortName : 'orders', // 排序的列
		sortOrder : 'asc', // 排序方式
		queryParams : {},
		columns : [ [ {
			field : 'menu_name',
			title : '菜单名称',
			width : 10,
			sortable : true
		}, {
			field : 'menu_code',
			title : '编码',
			width : 10,
			sortable : true
		},{
			field : 'group_name',
			title : '申请的用户组',
			width : 10,
			sortable : true
		},{
			field : 'remark',
			title : '备注',
			width : 10,
			sortable : true
		},{
			field : 'create_user',
			title : '审核人',
			width : 10,
			sortable : true,
			formatter :function(value, row, index){
				for(var i = 0 ; i < userList.length ; i++){
					if(value == userList[i].user_id){
						return userList[i].display_name;
					}
				}
			}
		},{
			field : 'create_time',
			title : '审核时间',
			width : 10,
			sortable : true,
			formatter :function(value, row, index){
				return util.formatDate(value);
			}
		}] ]
	});
});
function query() {
	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#dg').datagrid('reload');
}