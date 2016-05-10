$(function() {
	var packingunitTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=125");
	
	// 初始化分店定价表格
	$("#PriceDg").datagrid({
		method : 'get',
		//url : global_param.context_name + "/product/stockPrice/getDeptsByPro", // 数据来源
		title : '产品库存信息', // 标题
		sortName : 'stock_info_id', // 排序的列
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
		},
		columns:[[{
			field : 'stock_batchCode',
			title : '批号',
			width : 120,
			sortable : true
		},{
			field : 'stock_storage',
			title : '库房',
			width : 120,
			sortable : true
		},{
			field : 'stock_storageNumber',
			title : '库房数量',
			width : 60,
			sortable : true
		},{
			field : 'stock_salepPrice1',
			title : '最低价',
			width : 60,
			sortable : true
		},{
			field : 'stock_marketPrice',
			title : '市场价',
			width : 60,
			sortable : true
		},{
			field : 'stock_produceDate',
			title : '生产日期',
			width : 160,
			sortable : true
		},{
			field : 'stock_marketPrice',
			title : '失效期',
			width : 160,
			sortable : true
		},{
			field : 'stock_lowPrice',
			title : '最低价',
			width : 100,
			sortable : true
		},{
			field : 'stock_tradeprice',
			title : '批发价',
			width : 60,
			sortable : true
		}]],
		toolbar : [ {
			text : '修改',
			iconCls : 'icon-modify',
			handler : updateStockPriceFunc
		}]
	});
	
	// 初始化产品信息表格
	$("#Prodg").datagrid({
		method : 'get',
		url : global_param.context_name + "/product/stockPrice/proLists?product_check=0", // 数据来源
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
		},
		columns:[[{
			field : 'product_code',
			title : '产品编号',
			width : 100,
			sortable : true
		},{
			field : 'product_barcode',
			title : '产品条码',
			width : 100,
			sortable : true
		},{
			field : 'product_name',
			title : '产品名称',
			width : 100,
			sortable : true
		},{
			field : 'product_chnpy',
			title : '拼音码',
			width : 100,
			sortable : true
		},{
			field : 'product_specification',
			title : '规格',
			width : 100,
			sortable : true
		},{
			field : 'product_unit',
			title : '单位',
			width : 60,
			sortable : true
		},{
			field : 'product_productarea',
			title : '产地',
			width : 150,
			sortable : true
		},{
			field : 'product_packingamount',
			title : '包装量',
			width : 60,
			sortable : true
		},{
			field : 'product_packingunit',
			title : '包装单位',
			width : 60,
			sortable : true,
			formatter :function(value, row, index){
				for(var i = 0 ; i < packingunitTypes.length ;i++){
					if(value == packingunitTypes[i].cbs_id){
						return packingunitTypes[i].cbs_chn;
					}
				}
			}
		},{
			field : 'product_lastprice',
			title : '最后进价',
			width : 60,
			sortable : true
		},{
			field : 'product_marketprice',
			title : '市场价',
			width : 60,
			sortable : true
		},{
			field : 'product_tradeprice',
			title : '批发价',
			width : 60,
			sortable : true
		},{
			field : 'product_retailprice',
			title : '零售价',
			width : 60,
			sortable : true
		},{
			field : 'product_Iretaillprice',
			title : '最低价',
			width : 60,
			sortable : true
		},{
			field : 'product_saleprice1',
			title : '销售价1',
			width : 60,
			sortable : true
		},{
			field : 'product_saleprice2',
			title : '销售价2',
			width : 60,
			sortable : true
		},{
			field : 'product_saleprice3',
			title : '销售价3',
			width : 60,
			sortable : true
		},{
			field : 'product_saleprice4',
			title : '销售价4',
			width : 60,
			sortable : true
		}, {
			field : 'product_stocknum',
			title : '库存数量',
			width : 60,
			sortable : true
		}]],
		toolbar : [{
			text : '修改',
			iconCls : 'icon-modify',
			handler : updateProFunc
		}],
		onClickRow:function(index,data){
			$("#PriceDg").datagrid("options").url = global_param.context_name + "/product/wholeSalePrice/getInfoByPro?product_id="+data.product_id,
			//$("#PriceDg").datagrid("options").queryParams.product_id = data.product_id;
			//$("#PriceDg").datagrid("options").queryParams.product_name = data.product_name;
			$("#PriceDg").datagrid("reload");
		}
	});
	// 初始化编辑窗口
	util.window('editWindow', {
		title : '产品档案信息',
		resizable:true,
		width : 650,
		height : 650
	
	});
	// 初始化编辑窗口
	util.window('editStockPriceWindow', {
		title : '分店定价信息',
		width:610,    
	    height:430
	});	
});


function query() {
	$('#Prodg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#Prodg').datagrid('reload');
}






/*function updateStockPriceFunc() {
	var node = $('#PriceDg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	if(node.prod_id == null){
		node.prod_id = -1;
	}
	//获取选择的产品
	var pro = $('#Prodg').datagrid('getSelected');
	
	util.openWindow('editStockPriceWindow', "stockPrice/dept/update?prod_id="+node.prod_id+"&product_id="+
			pro.product_id+"&department_id="+node.department_id+"&product_name="+node.product_name+"&department_fullname="+node.department_fullname);
}*/

function updateProFunc() {
	var node = $('#Prodg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	/*if(node.product_check == 1){
		util.show("产品正在审核中，无法进行更改操作");
		return;
	}*/
	util.openWindow('editWindow', "product/wholeSalePrice/proPrice/" + node.stock_info_id);
}