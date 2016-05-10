//记录orderList_dg选中的行，以便行切换的时候关闭当前行编辑
var currentEdit = 0;
$(function(){	
	$("#rejectOrderCheck_dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/accept/quality/listDetail", // 数据来源
		title : '小单信息', // 标题
		sortName : 'accept_check_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitcolumns : true,
		fit : true,
		rownumbers : true,
		nowrap : true,
		striped : true,
		queryParams : {
			accept_rejectStatus:2,
		},
		columns:[[ {
			field : 'ticket_id',
			title : '票号',
			width : 100,
			sortable : true
		},{
			field : 'product_code',
			title : '产品编码',
			width : 100,
			sortable : true
		},{
			field : 'product_commonname',
			title : '通用名',
			width : 100,
			sortable : true
		}, {
			field : 'quantity',
			title : '数量',
			width : 100,
			sortable : true,
			
		}, {
			field : 'unitprice',
			title : '单价',
			width : 100,
			sortable : true,
		}, {
			field : 'accept_acceptNumber',
			title : '收货数量',
			width : 100,
			sortable : true
		}, {
			field : 'accept_rejectNumber',
			title : '拒货数量',
			width : 100,
			sortable : true
		}, {
			field : 'accept_rejectReason',
			title : '拒收理由',
			width : 200,
			editor:{  
                type:'text',  
                options:{
                    required:true  
                }  
            }
		}, {
			field : 'accept_rejectStatus',
			title : '拒收审核状态',
			width : 100,
			sortable : true,
			formatter: function(value, row, index){
				if(value==0 ){
					return "<font color='green'>审核通过</font>";
				}else if(value==1){
					return "<font color='green'>审核不通过</font>";
				}else if(value==2){
					return "<font color='red'>待审核</font>";				
				}
			}
		
		}]],
		toolbar : "#tb",
		onClickCell:function(index, field, value){
			//开始行编辑
			 $("#rejectOrderCheck_dg").datagrid('beginEdit', index);
			 //编辑单元格
			 var ed = $("#rejectOrderCheck_dg").datagrid('getEditor', { index: index, field: field });
			 //如果当前行和选中的行不一样，则关闭当前行的编辑
			 if(currentEdit!=index)
				 $("#rejectOrderCheck_dg").datagrid('endEdit', currentEdit);
			 //更新当前行
			 currentEdit = index;
		},
		onLoadSuccess:function(data){
			//写入拒收审核人和拒收审核日期
			var user = util.get(global_param.context_name + "/userinfo");
			$("#rejectCheckPerson").val(user.display_name);
			var time = new Date();
			$("#rejectCheckDate").val(time.format("yyyy-mm-dd"));
		},
	});

});


function query() {
	$('#rejectOrderCheck_dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#rejectOrderCheck_dg').datagrid('options').queryParams.accept_rejectStatus = 2;
	$('#rejectOrderCheck_dg').datagrid('reload');
	
}
function checkResult(flag){
	//保存时关闭当前行的编辑
	$("#rejectOrderCheck_dg").datagrid('endEdit', currentEdit);	

	//获取所有选中的行
	var nodes = $('#rejectOrderCheck_dg').datagrid('getSelections');
	if(nodes.length==0){
		util.show("请选择拒收审核条目");
		return;
	}
	var data="";
	for(var i=0;i<nodes.length;i++){
		if(nodes[i].accept_rejectReason ==""){
			util.show("请输入拒收理由");
			return;
		}
		//将行数据封装成字符串，以便传到后台
		data+=nodes[i].accept_check_id+"_";
		data+=nodes[i].accept_rejectReason;
		
		if(i<nodes.length-1)
			data+="|";
	}
	var re = util.get(global_param.context_name + "/accept/reject/checkResult?data="+data+"&flag="+flag);
	if(re.result == "success"){
		util.show(re.message);
		$('#rejectOrderCheck_dg').datagrid('reload');
	}else{
		util.error(re.message);
	}

}
