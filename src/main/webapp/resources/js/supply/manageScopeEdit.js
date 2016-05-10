$(function(){
	// 初始化表格
	$("#dg1").datagrid({
		method : 'get',
		url : global_param.context_name + "/system/combobox/lists?pid=584", // 数据来源
		//title : '经营范围页面', // 标题
		sortName : 'sup_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitcolumns : true,
		singleSelect : false,//单选
		fit : true,
		fitColumns : true,
		//triped : true,//奇偶行颜色不同
		rownumbers : true,
		nowrap : true,
		//width:100,
		striped : true,
		queryParams : {},
		columns:[[ {
			field : 'ck',
			checkbox:true,
			width : 200,
		},{
			field : 'cbs_chn',
			title : '经营范围',
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
				parent.$("#scopeChooseWin").window('close');
			}
		}],
		onLoadSuccess:function(data){
			//alert(JSON.stringify(data));
			var scopes = $("#scope").val().split(",");
			for(var j=0;j<scopes.length;j++){
				for(var i=0;i<data.total;i++){
					if(scopes[j]==data.rows[i].cbs_chn){
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
	parent.$("#scopeChooseWin").window('close');
}

