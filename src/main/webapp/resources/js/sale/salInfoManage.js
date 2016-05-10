$(function() {
	
	var salCheckList = null;
	var salLocalList = null;
	$('#search_sal_check').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=638",
		method :"get",
		valueField : "cbs_code",
		textField : "cbs_chn",
		editable:false,
		onLoadSuccess: function(){
			salCheckList = $('#search_sal_check').combobox("getData");
		}
	});
	$('#search_sal_drugid').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=642",
		method :"get",
		valueField : "cbs_code",
		textField : "cbs_chn",
		editable:false,
		onLoadSuccess: function(){
			salLocalList = $('#search_sal_drugid').combobox("getData");
		}
	});
	
	// 初始化表格
	$("#dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/sale/salInfoManage/lists", // 数据来源
		title : '销方客户信息管理', // 标题
		sortName : 'sal_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitcolumns : true,
		singleSelect : true,//单选
		pageSize : 10,
		pageList : [10,20,50,100],
		fit : true,
		//triped : true,//奇偶行颜色不同
		pagination : true,
		rownumbers : true,
		nowrap : true,
		striped : true,
		queryParams : {},
		columns:[[{
			field : 'sal_code',
			title : '销方编码',
			width : 80,
			sortable : true
		}, {
			field : 'sal_customername',
			title : '客户全称',
			width : 100,
			sortable : true
		}, {
			field : 'sal_shortname',
			title : '简称',
			width : 80,
			sortable : true
		}, {
			field : 'sal_chnpy',
			title : '拼音码',
			width : 80,
			sortable : true
		}, {
			field : 'sal_address',
			title : '地址',
			width : 100,
			sortable : true
		}, {
			field : 'sal_contactperson',
			title : '联系人',
			width : 80,
			sortable : true
		}, {
			field : 'sal_phone',
			title : '电话',
			width : 100,
			sortable : true
		}, {
			field : 'sal_telephone',
			title : '手机 ',
			width : 100,
			sortable : true
		}, {
			field : 'sal_fax',
			title : '传真',
			width : 100,
			sortable : true
		}, {
			field : 'sal_check',
			title : '审核状态',
			width : 80,
			sortable : true,
			//hidden: true,
			formatter : function(value, row, index){
				for(var i = 0 ; i < salCheckList.length ;i++){
					if(value == salCheckList[i].cbs_code){
						return salCheckList[i].cbs_chn;
					}
				}
				return "";	
			}
		},{
			field : 'sal_drugid',
			title : '地区药监ID',
			sortable : true,
			//hidden : true,
			formatter : function(value, row, index){
				if(null == value){
					return salLocalList[0].cbs_chn;
					value = 0;
				}else{
					return salLocalList[1].cbs_chn;
					value = 1;
				}
			}
		} ]],
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
		title : '销方客户信息',
		resizable:true,
		width : 640,
		height : 697
	
	}); 
	
});

function getNextElement (field) {
	var form = field.form;
    for (var e = 0; e < form.elements.length; e++) { 
    	if (field == form.elements[e])
            break;
    }
    return form.elements[++e % form.elements.length];
} 


	
function query() {
	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#dg').datagrid('reload');
}

function addFunc() {
	
	util.openWindow('editWindow', "salInfoManage/new");
}

function updateFunc() {
	var node = $('#dg').datagrid('getSelected');

	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	if(node.sal_check == 1){
		util.show("该销方正在审批，不允许修改");
		return;
	}
	util.openWindow('editWindow', "salInfoManage/" + node.sal_id);
}

function deleteFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/sale/salInfoManage/" + node.sal_id);
		if (data.result == "success") {
			$('#dg').datagrid('reload');
		}
		util.show(data.message);
	});

}

