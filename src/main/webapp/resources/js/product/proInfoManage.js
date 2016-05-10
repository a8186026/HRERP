var productCheckList = null;
var productDrugList = null;
$(function() {
	var dosageTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=98");
	var packingunitTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=125");

	util.treegrid('menuDiv',{
		url : global_param.context_name + "/system/combobox/listTree?type=product_tree", // 数据来源
		title : '菜单列表', // 标题
		idField : 'cbs_id',
		method:'get',
		parentField : 'cbs_pid',
		treeField : 'cbs_chn',
		sortName : 'cbs_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		singleSelect : true, // 多选
		fit:true,
		queryParams : {
		},
		columns : [ [ {
			field : 'cbs_chn',
			title : '名称',
			width : 300,
			sortable : true
		}]],
		onClickRow:function(data){
			//查询此节点的所有父节点然后去数据库匹配查询此目录下所有产品
			var code = data.cbs_code;
			var parent = $(this).treegrid('getParent',data.cbs_id);
			while(parent!=null){
				code = parent.cbs_code + code;
				parent = $(this).treegrid('getParent',parent.cbs_id);
			}
			
			$("#dg").datagrid("options").queryParams.code = code;
			$("#dg").datagrid("reload");
		}
	});
	
	$('#search_product_check').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=645",
		method :"get",
		valueField : "cbs_code",
		textField : "cbs_chn",
		editable:false,
		onLoadSuccess: function(){
			productCheckList = $('#search_product_check').combobox("getData");
		}
	});
	
	$('#search_product_dsurveillanceid').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=642",
		method :"get",
		valueField : "cbs_code",
		textField : "cbs_chn",
		editable:false,
		onLoadSuccess: function(){
			productDrugList = $('#search_product_dsurveillanceid').combobox("getData");
		}
	});
	
	// 初始化表格
	$("#dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/product/proInfoManage/lists", // 数据来源
		title : '产品档案信息管理', // 标题
		sortName : 'product_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitcolumns : true,
		singleSelect : true,//单选
		pageSize : 10,
		pageList : [10,20,50,100],
		fit : true,
		pagination : true,
		rownumbers : true,
		nowrap : true,
		striped : true,
		queryParams : {
			code:null,
		},
		columns:[[{
			field : 'product_name',
			title : '产品名称',
			width : 80,
			sortable : true
		}, {
			field : 'product_chnpy',
			title : '拼音码',
			width : 50,
			sortable : true
		}/*, {
			field : 'product_category',
			title : '产品分类',
			width : 80,
			sortable : true,
			formatter :function(value, row, index){
				for(var i = 0 ; i < categoryTypes.length ;i++){
					if(value == categoryTypes[i].cbs_id){
						return categoryTypes[i].cbs_chn;
					}
				}
			} 
		}*/, {
			field : 'product_dosagetype',
			title : '剂型',
			width : 50,
			sortable : true,
			formatter :function(value, row, index){
				for(var i = 0 ; i < dosageTypes.length ;i++){
					if(value == dosageTypes[i].cbs_id){
						return dosageTypes[i].cbs_chn;
					}
				}
			}
		}, {
			field : 'product_packingunit',
			title : '包装单位',
			width : 50,
			sortable : true,
			formatter :function(value, row, index){
				for(var i = 0 ; i < packingunitTypes.length ;i++){
					if(value == packingunitTypes[i].cbs_id){
						return packingunitTypes[i].cbs_chn;
					}
				}
			}
		}, {
			field : 'product_packingamount',
			title : '包装量',
			width : 80,
			sortable : true
		}, {
			field : 'product_code',
			title : '产品编号',
			width : 80,
			sortable : true
		}, {
			field : 'product_barcode',
			title : '产品条码',
			width : 90,
			sortable : true
		}, {
			field : 'product_proname',
			title : '商品名',
			width : 70,
			sortable : true
		}, {
			field : 'product_specification',
			title : '规格',
			width : 100,
			sortable : true
		}, {
			field : 'product_unit',
			title : '单位',
			width : 80,
			sortable : true
		}, {
			field : 'product_productarea',
			title : '产地',
			width : 100,
			sortable : true
		}, {
			field : 'product_description',
			title : '简介',
			width : 100,
			sortable : true
		}, {
			field : 'product_check',
			title : '审核状态',
			width : 80,
			sortable : true,
			//hidden: true,
			formatter : function(value, row, index){
				for(var i = 0 ; i < productCheckList.length ;i++){
					if(value == productCheckList[i].cbs_code){
						return productCheckList[i].cbs_chn;
					}
				}
				return "";	
			}
		}, {
			field : 'product_dsurveillanceid',
			title : '地区药监ID',
			sortable : true,
			formatter : function(value, row, index){
				if(null == value){
					return productDrugList[0].cbs_chn;
					value = 0;
				}else{
					return productDrugList[1].cbs_chn;
					value = 1;
				}
			}
		}]],
		toolbar : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : addProFunc
		}, {
			text : '修改',
			iconCls : 'icon-modify',
			handler : updateProFunc
		}, {
			text : '删除',
			iconCls : 'icon-delete',
			handler : deleteProFunc
		} ],
	});
	// 初始化编辑窗口
	util.window('editWindow', {
		title : '产品档案信息',
		resizable:true,
		width : 650,
		height : 650,
		onBeforeClose: function () { 
			//取消子窗口的聚焦和enter跳转功能(此函数存在于子窗口里面)
			keyPress.stopFocus(false,ctrlsID);
        }
	}); 
	
});

function query() {
	
	var node = $('#menuDiv').treegrid("getSelected");
	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	if(node){
		$('#dg').datagrid('options').queryParams.code = node.cbs_code;
	}
	$('#dg').datagrid('reload');
}

function addProFunc() {
	
	var id = "";
	var node = $('#menuDiv').treegrid("getSelected");
	if(node){
		id = node.cbs_id;
	}
	util.openWindow('editWindow', "proInfoManage/new?id="+id);
}

function updateProFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	if(node.product_check == 1){
		util.show("产品正在审核中，无法进行更改操作");
		return;
	}
	util.openWindow('editWindow', "proInfoManage/" + node.product_id);
}

function deleteProFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/product/proInfoManage/" + node.product_id);
		if (data.result == "success") {
			$('#dg').datagrid('reload');
		}
		util.show(data.message);
	});
}

