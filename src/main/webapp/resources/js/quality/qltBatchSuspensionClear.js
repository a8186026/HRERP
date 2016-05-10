//记录orderList_dg选中的行，以便行切换的时候关闭当前行编辑
var currentEdit = 0;
$(function() {
	// 初始化表格
	$("#dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/stock/stockInfo/listSaleStop", // 数据来源
		title : '批次停售解锁', // 标题
		sortName : 'stock_info_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitColumns : true,
		fit : true,
		pageSize : 10,
		pageList : [10, 20, 50, 100],
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		nowrap : true,
		striped : true, // 奇偶行颜色不同
		columns :[[{
			field : 'stock_batchCode',
			title : '产品批号',
			width : 80,
			sortable : true
		}, {
			field : 'stock_invalidDate',
			title : '失效期',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value);
			}
		}, {
			field : 'stock_storageNumber',
			title : '剩余库存',
			width : 100,
			sortable : true
		}, {
			field : 'stock_intakeTicket',
			title : '入库票号',
			width : 80,
			sortable : true
		},{
			field : 'stock_intakeSmallNumber',
			title : '入库小号',
			width : 80,
			sortable : true
		},{
			field : 'stock_stopClearReason',
			title : '批次停售解锁原因',
			width : 150,
			editor:{  
	            type:'text',  
	            options:{
	                required:true  
	            }  
        }}]],
		toolbar : [{
			text : '审核',
			iconCls : 'icon-modify',
			handler : saleStopClear
		}, {
			text : '删除',
			iconCls : 'icon-delete',
			handler : deleteFunc
		}],
		onClickCell:function(index, field, value){
			//开始行编辑
			 $("#dg").datagrid('beginEdit', index);
			 //编辑单元格
			 var ed = $("#dg").datagrid('getEditor', { index: index, field: field });
			 //如果当前行和选中的行不一样，则关闭当前行的编辑
			 if(currentEdit!=index)
				 $("#dg").datagrid('endEdit', currentEdit);
			 //更新当前行
			 currentEdit = index;
		},
	});
	// 初始化编辑窗口
	util.window('editWindow', {
		title : '不合格品种确定'
	});
	
});

function query() {
	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#dg').datagrid('reload');
}

function saleStopClear(){
	//保存时关闭当前行的编辑
	$("#dg").datagrid('endEdit', currentEdit);	

	//获取所有选中的行
	var nodes = $('#dg').datagrid('getSelections');
	if(nodes.length==0){
		util.show("请选择批次停售解锁条目");
		return;
	}
	var data="";
	for(var i=0;i<nodes.length;i++){
		if(nodes[i].stock_stopClearReason ==""){
			util.show("请输入批次停售解锁原因");
			return;
		}
		//将行数据封装成字符串，以便传到后台
		data+=nodes[i].stock_info_id+"_";
		data+=nodes[i].stock_stopClearReason;
		
		if(i<nodes.length-1)
			data+="|";
	}
	var re = util.get(global_param.context_name + "/quality/bacthClear/saleStopClear?data="+data);
	if(re.result == "success"){
		util.show(re.message);
		$('#dg').datagrid('reload');
	}else{
		util.error(re.message);
	}
}

function deleteFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/stock/stockInfo/" + node.stock_info_id);
		if (data.result == "success") {
			$('#dg').datagrid('reload');
		}
		util.show(data.message);
	});
}
