$(function() {
	$("#dg").datagrid({
		url : global_param.context_name + "/promotion/memday/lists",
		method : 'get',
		title : '会员日管理', // 标题
		sortName : 'mem_day_priority', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitcolumns : true,
		fit:true,
		singleSelect:true,//单选
		rownumbers : true,
		nowrap : true,
		striped : true,
		pageSize : 10,
		pageList : [10,20,50,100],
		pagination : true,
		queryParams : {
		},
		columns:[[ {
			field : 'mem_day_type',
			title : '会员日类型',
			width : 150,
			sortable : true
		},{
			field : 'mem_day_priority',
			title : '会员日优先级',
			width : 150,
			sortable : true
		},{
			field : 'mem_day_date',
			title : '会员日时间',
			width : 250,
			formatter: function(value,row,index){
				return value.substring(1,value.length-1);
			}
		},{
			field : 'mem_day_startDate',
			title : '起始时间',
			width : 150,
			formatter: function(value,row,index){
				return util.formatDate(value);
			}
		},{
			field : 'mem_day_endDate',
			title : '结束时间',
			width : 150,
			formatter: function(value,row,index){
				return util.formatDate(value);
			}
		},{
			field : 'mem_day_pointMultiple',
			title : '积分倍数',
			width : 150,
			sortable : true
		}]],
		
		toolbar:[ {
			text : '增加',
			iconCls : 'icon-add',
			handler : addMemday
		}, {
			text : '修改',
			iconCls : 'icon-modify',
			handler : updateMemday
		}, {
			text : '删除',
			iconCls : 'icon-delete',
			handler : deleteMemday
		}, {
			text : '保存配置',
			iconCls : 'icon-modify',
			handler : savePriorityFunc
		}], 
		onLoadSuccess:function(data){
		
			$(this).datagrid('enableDnd');
		}
		
	});
	// 初始化编辑窗口
	util.window('editWindow', {
		height:560,
		title : '会员日设置'
	});
});
  

function query() {
	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#dg').datagrid('reload');
}

function addMemday() {
	util.openWindow('editWindow', "memday/new");
}

function updateMemday() {
	var node = $('#dg').treegrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
	} else {
		util.openWindow('editWindow', "memday/" + node.mem_day_id);
	}
}

function deleteMemday() {
	var node = $('#dg').treegrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var result = util.get(global_param.context_name + "/basInfo/basDepartmentInfo/getListByPromotion?mem_day_id="+node.mem_day_id);
		if(result.length>0){
			util.show("此会员日还有部门参与，请先删除部门");
			return;
		}
		var data = util.del(global_param.context_name + "/promotion/memday/" + node.mem_day_id);
		
		if (data.result == "success") {
			$('#dg').datagrid('reload');
		}
		util.show(data.message);
	});
}
function savePriorityFunc(){
	var rows = $('#dg').datagrid('getRows');     
	for(var i = 0;i < rows.length;i++){
		rows[i].mem_day_priority = i+1;
	}
	var data ="";
	for(var i = 0;i < rows.length;i++){
		if(i < rows.length-1){
			data = data + rows[i].mem_day_id+"_";
			data += rows[i].mem_day_priority;
			data+="|";
		}
		else{
			data = data+rows[rows.length-1].mem_day_id+"_";
			data = data+rows[rows.length-1].mem_day_priority;
		}
	}
	 //alert(JSON.stringify($('#RuleInfoDg').datagrid('getRows')));
	 var result = util.get("/HRERP/promotion/memday/savePriority?data="+data); 
	
	 
	if(result.result=="success"){
		util.show(result.message);
		$('#dg').datagrid('reload');
	}else if(result.result=="failure"){
		util.show(result.message);
	}else{
		util.show("出错了");
	}
}
