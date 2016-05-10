$(function(){
	$('#search_group_id').combobox({
		url : global_param.context_name
				+ "/system/permission/groups",
		method : 'get',
		valueField : 'group_id',
		textField : 'group_name',
		editable:false
	});
	// 初始化表格
	util.treegrid('dg', {
		url : global_param.context_name + "/system/permission/permissionCheckList", // 数据来源
		title : '菜单权限审核列表', // 标题
		idField : 'menu_id',
		parentField : 'parent_menu',
		treeField : 'menu_name',
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
		}, {
			field : 'note',
			title : '备注',
			width : 10,
			sortable : true
		},{
			field : 'group_name',
			title : '申请的用户组',
			width : 10,
			sortable : true
		},{
			field : 'sgm_status',
			title : '申请状态',
			width : 20,
			sortable : true,
			formatter: function(value, row, index){
				if(value =="2"){
					return "<font color='red'>删除申请</font>";
				}else if(value == "3"){
					return "<font color='green'>获得申请</font>";
				}
			}
		} ] ],
		toolbar : [ {
			text : '审核通过',
			iconCls : 'icon-add',
			handler : passFunc
		}, {
			text : '审核不通过',
			iconCls : 'icon-modify',
			handler : nopassFunc
		} ]
	});
});
function query() {
	$('#dg').treegrid('options').queryParams = util.formParams("searchForm");
	$('#dg').treegrid('reload');
}

function passFunc() {
	var node = $('#dg').treegrid('getSelected');
	if (node) {	
		//当前项为子项时，修改父项
		var parentNode = $('#dg').treegrid('getParent', node.menu_id);
		if(parentNode){
			var parentdata = util.put(global_param.context_name + "/system/permission/permissionCheckCheck?group_menu_id="
					+parentNode.group_menu_id+"&sgm_status="+parentNode.sgm_status+"&checkContent=pass");
		}
		var data = util.put(global_param.context_name + "/system/permission/permissionCheckCheck?group_menu_id="
				+node.group_menu_id+"&sgm_status="+node.sgm_status+"&checkContent=pass");
		if (data.result == "success") {
			util.show(data.message);
			$('#dg').treegrid('reload');
		}
		util.show(data.message);
	}
}

function nopassFunc() {
	var node = $('#dg').treegrid('getSelected');
	if (node) {
		var data = util.put(global_param.context_name + "/system/permission/permissionCheckCheck?group_menu_id="
				+node.group_menu_id+"&sgm_status="+node.sgm_status+"&checkContent=nopass");
		if (data.result == "success") {
			$('#dg').treegrid('reload');
		}
		util.show(data.message);
	}
}