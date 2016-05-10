$(function(){
	// 初始化表格
	$("#dg1").datagrid({
		method : 'get',
		url : global_param.context_name + "/system/combobox/lists?pid=350", // 数据来源
		sortName : 'product_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitcolumns : true,
		singleSelect : false,//单选
		fit : true,
		fitColumns : true,
		rownumbers : true,
		nowrap : true,
		striped : true,
		queryParams : {},
		columns:[[ {
			field : 'ck',
			checkbox:true,
			width : 200,
		},{
			field : 'cbs_chn',
			title : '经营分类',
			width : 200,
		}]],
		toolbar : [ {
			text : '保存',
			iconCls : 'icon-add',
			handler : saveScope
		} , {
			text : '清除',
			iconCls : 'icon-delete',
			handler : function() {
				callParent(null);
				parent.$("#categoryChooseWin").window('close');
			}
		}],
		onLoadSuccess:function(data){
			
			var categorys = $("#category").val().split(",");
			for(var j=0;j<categorys.length;j++){
				for(var i=0;i<data.total;i++){
					if(categorys[j]==data.rows[i].cbs_chn){
						$("#dg1").datagrid('selectRow',i);
					}						
				}
			}
		}
	});	 
	
});
function saveScope(){
	var nodes = $('#dg1').datagrid('getSelections');
	if (!nodes.length) {
		util.show("请选择经营范围");
		return;
	}
	callParent(nodes);
	parent.$("#categoryChooseWin").window('close');
}

