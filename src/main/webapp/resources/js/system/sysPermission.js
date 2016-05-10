
var json="";
$(function() {
	// 初始化表格
	$('#group_id').combobox({
		url : global_param.context_name
				+ "/system/permission/groups",
		method : 'get',
		valueField : 'group_id',
		textField : 'group_name',
		editable:false,
		onChange : function change(n, o) {
			//根据组获取用户列表
			var queryParams = $('#u_dg').datagrid('options').queryParams;
			queryParams.group_id = n;
			$("#u_dg").datagrid('reload');
			
			//获取某组的权限
			$.ajax({
				type : "get", // 表单提交类型
				url : global_param.context_name + "/system/permission/getNoPageGroupMenu?group_id="+n, // 表单提交目标
				data :"json", // 表单数据
				success : function(data) {
					json=data;
					$("#menu_dg").treegrid('reload');
				}
			});
		}
	});
	
	util.table('u_dg', {
		url : global_param.context_name + "/system/permission/getUsersByGroup", // 数据来源
		title : '用户组用户列表', // 标题
		method:'get',
		sortName : 'user_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		rownumbers : true, // 显示行号
		pagination : false, // 显示分页
		queryParams : {
			group_id:""
		},
		columns : [ [ {
			field : 'user_name',
			title : '登录名',
			width : 120,
			sortable : true
		}, {
			field : 'display_name',
			title : '用户姓名',
			width : 120,
			sortable : true
		}] ],
	});
	
	
	$("#menu_dg").treegrid({
		url : global_param.context_name + "/system/menu/menus", // 数据来源
		title : '菜单列表', // 标题
		idField : 'menu_id',
		method:'get',
		parentField : 'parent_menu',
		treeField : 'menu_name',
		sortName : 'menu_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		singleSelect : false, // 多选
	    checkOnSelect:true,
		selectOnCheck:true,
		queryParams : {
		},
		columns : [ [ {
			field : 'menu_name',
			title : '名称',
			width : 600,
			sortable : true
		}, {
			field : 'menu_code',
			title : '菜单编码',
			width : 700,
			sortable : true
		}, {
			field : 'ck',
			checkbox:true,
			width : 200,
		}]],
		onLoadSuccess : function(row,data) {
			$("#menu_dg").treegrid('expandAll');
			$("#menu_dg").treegrid("unselectAll");
			initPermission(data);
		},
		onSelect:function(row){
			if(row.parent_menu!=null){
				$("#menu_dg").treegrid('select', row.parent_menu);//此处又会执行onselect函数
			}	
		},
		onUnselect:function(row){
			var nodes=$("#menu_dg").treegrid("getSelections");		
			if(row.children!=null){
				//取消所有子节点的选中
				for(var i=0;i<row.children.length;i++){
					for(var j=0;j<nodes.length;j++)
						if(row.children[i]==nodes[j])
							$("#menu_dg").treegrid('unselect', row.children[i].menu_id);
				}
			}
			
			//如果父节点无选中子节点，则取消父节点的选中
			var f=true;
			if(row.parent_menu==null)
				return;
			var children = $("#menu_dg").treegrid("getChildren",row.parent_menu);
			for(var j=0;j<children.length;j++)
			{
				for(var i=0;i<nodes.length;i++)
				{
					if(children[j]==nodes[i])
						f=false;
				}
			}
			if(f)
				$("#menu_dg").treegrid('unselect', row.parent_menu);//此处还会执行onunselect函数
		},
		toolbar : [ {
			text : '保存',
			iconCls : 'icon-add',
			handler : savePermissionFunc
		},{
			text : '展开',
			iconCls : 'icon-redo',
			handler : function() {
				var node = $("#menu_dg").treegrid('getSelected');
				if (node) {
					$("#menu_dg").treegrid('expandAll', node.cid);
				} else {
					$("#menu_dg").treegrid('expandAll');
				}
			}
		}, '-', {
			text : '折叠',
			iconCls : 'icon-undo',
			handler : function() {
				var node = $("#menu_dg").treegrid('getSelected');
				if (node) {
					$("#menu_dg").treegrid('collapseAll', node.cid);
				} else {
					$("#menu_dg").treegrid('collapseAll');
				}
			}
		}, '-', {
			text : '刷新',
			iconCls : 'icon-reload',
			handler : function() {
				var group_id=$('#group_id').combobox("getValue");
				reloadMenu(group_id);
			}
		} ]
	});
});

function savePermissionFunc()
{
	//封装新的菜单权限列表
	var permission="";
	var nodes=$("#menu_dg").treegrid("getSelections");
	for(var i=0;i<nodes.length;i++)
	{
		permission=permission+nodes[i].menu_id;
		if(i+1<nodes.length)
			permission=permission+"_";
	}
	//封装旧的权限列表，用于比对
	var oldPermission="";
	for(var i=0;i<json.length;i++)
	{
		oldPermission=oldPermission+json[i].menu_id;
		if(i+1<json.length)
			oldPermission=oldPermission+"_";
	}
	var group_id=$('#group_id').combobox("getValue");
	if(group_id!=null&&group_id!=""){
		$.ajax({
			type : "post", // 表单提交类型
			url : global_param.context_name + "/system/permission/saveGroupMenu?group_id="+group_id+"&permissions="+permission+"&oldPermissions="+oldPermission, // 表单提交目标
			data :"json", // 表单数据
			success : function(data) {
				if(data.result=="success")
				{
					reloadMenu(group_id);
					util.show("保存成功！");
				}
					
			}
		});
	}else{
		util.show("请先选择权限组！");
	}
	
}
//重新加载表单
function reloadMenu(group_id)
{
	$.ajax({
		type : "get", // 表单提交类型
		url : global_param.context_name + "/system/permission/getGroupMenu?group_id="+group_id, // 表单提交目标
		data :"json", // 表单数据
		success : function(data) {
			json=data;
			$("#menu_dg").treegrid('reload');
		}
	});
}

//用递归遍历树,选中已有的权限
function initPermission(data)
{
	for(var j=0;j<json.length;j++)
	{
		$("#menu_dg").treegrid('select', json[j].menu_id);
	}
}
