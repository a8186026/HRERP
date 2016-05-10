var supCheckList = null;
var supLocalList = null;
$(function() {
	$('#search_sup_check').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=638",
		method :"get",
		valueField : "cbs_code",
		textField : "cbs_chn",
		editable:false,
		onLoadSuccess: function(){
			supCheckList = $('#search_sup_check').combobox("getData");
		}
	});
	$('#search_sup_drugid').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=642",
		method :"get",
		valueField : "cbs_code",
		textField : "cbs_chn",
		editable:false,
		onLoadSuccess: function(){
			supLocalList = $('#search_sup_drugid').combobox("getData");
		}
	});
	// 初始化表格
	$("#dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/supply/supInfoManage/lists", // 数据来源
		title : '供方客户信息管理', // 标题
		sortName : 'sup_id', // 排序的列
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
			field : 'sup_code',
			title : '供方编码',
			width : 80,
			sortable : true
		}, {
			field : 'sup_name',
			title : '客户全称',
			width : 100,
			sortable : true
		}, {
			field : 'sup_shortname',
			title : '简称',
			width : 80,
			sortable : true
		}, {
			field : 'sup_chnpy',
			title : '拼音码',
			width : 80,
			sortable : true
		}, {
			field : 'sup_address',
			title : '地址',
			width : 100,
			sortable : true
		}, {
			field : 'sup_ctactperson',
			title : '联系人',
			width : 80,
			sortable : true
		}, {
			field : 'sup_tel',
			title : '电话',
			width : 100,
			sortable : true
		}, {
			field : 'sup_mobile',
			title : '手机 ',
			width : 100,
			sortable : true
		}, {
			field : 'sup_fax',
			title : '传真',
			width : 100,
			sortable : true
		}, {
			field : 'sup_email',
			title : '电子邮箱',
			width : 100,
			sortable : true
		}, {
			field : 'sup_buyname',
			title : '采购员',
			width : 80,
			sortable : true
		}, {
			field : 'sup_check',
			title : '审核状态',
			width : 80,
			sortable : true,
			//hidden: true,
			formatter : function(value, row, index){
				for(var i = 0 ; i < supCheckList.length ;i++){
					if(value == supCheckList[i].cbs_code){
						return supCheckList[i].cbs_chn;
					}
				}
				return "";	
			}
		}, {
			field : 'sup_drugid',
			title : '地区药监ID',
			sortable : true,
			//hidden : true,
			formatter : function(value, row, index){
				if(null == value){
					return supLocalList[0].cbs_chn;
					value = 0;
				}else{
					return supLocalList[1].cbs_chn;
					value = 1;
				}
			}
		}]],
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
		title : '供方客户信息',
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
	
	util.openWindow('editWindow', "supInfoManage/new");
}

function updateFunc() {
	var node = $('#dg').datagrid('getSelected');

	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	if(node.sup_check == 1){
		util.show("供方正在审批，不允许修改");
		return;
	}
	util.openWindow('editWindow', "supInfoManage/" + node.sup_id);
}

function deleteFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/supply/supInfoManage/" + node.sup_id);
		if (data.result == "success") {
			$('#dg').datagrid('reload');
		}
		util.show(data.message);
	});

}

