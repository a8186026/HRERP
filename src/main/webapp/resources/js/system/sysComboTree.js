$(function() {
	util.treegrid('dg',{
		url : global_param.context_name + "/system/combobox/listTree", // 数据来源
		title : '菜单列表', // 标题
		idField : 'cbs_id',
		method:'get',
		parentField : 'cbs_pid',
		treeField : 'cbs_chn',
		sortName : 'cbs_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		singleSelect : true, // 多选
		queryParams : {
			type:$("#cbs_type").val(),
		},
		columns : [ [{
			field : 'cbs_chn',
			title : '名称',
			width : 300,
			sortable : true
		},{
			field : 'cbs_id',
			title : 'ID',
			width : 300,
			sortable : true
		}, {
			field : 'cbs_code',
			title : '编码',
			width : 300,
			sortable : true
		}]],
		toolbar : [ {
			text : '新增',
			iconCls : 'icon-add',
			handler : addFunc
		},{
			text : '修改',
			iconCls : 'icon-modify',
			handler : updateFunc
		}, {
			text : '删除',
			iconCls : 'icon-delete',
			handler : deleteFunc
		}]
	});
	// 初始化编辑窗口
	util.window('editWindow', {
		title : '下拉菜单选项编辑'
	});
});

function query() {
	$('#dg').treegrid('options').queryParams = util.formParams("searchForm");
	$('#dg').treegrid('reload');
}

function addFunc() {
	var node = $('#dg').treegrid('getSelected');
	var parent_cbs_id = "";
	var parent_cbs_chn = "";
	if (node) {
		parent_cbs_id = node.cbs_id;
		parent_cbs_chn = node.cbs_chn;
	}
	util.openWindow('editWindow', "/HRERP/system/combobox/new?cbs_pid="+parent_cbs_id+"&cbs_pchn="+parent_cbs_chn+"&type="+$("#cbs_type").val());
}

function updateFunc() {
	var node = $('#dg').treegrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	var parent_chn = "";
	var parent = $('#dg').treegrid('getParent',node.cbs_id);//获取父节点
	if(parent)
		parent_chn = parent.cbs_chn;//获取父节点名称
	util.openWindow('editWindow', "/HRERP/system/combobox/" + node.cbs_id+"?cbs_pchn="+parent_chn+"&type="+$("#cbs_type").val());
}

function deleteFunc() {
	var node = $('#dg').treegrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	var childrens = $('#dg').treegrid('getChildren',node.cbs_id);
	//如果存在子节点,则无法删除
	if(childrens.length==0){
		util.confirm("确定要执行删除操作？", function() {
			var data = util.del(global_param.context_name + "/system/combobox/" + node.cbs_id+"?cbs_pid="+node.cbs_pid);
			if (data.result == "success") {
				$('#dg').treegrid('reload');
			}
			util.show(data.message);
		});
	}else{
		util.show("存在子目录，无法删除！");
	}
	
}