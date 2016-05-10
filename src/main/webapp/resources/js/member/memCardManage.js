var cardTypeList = null;
$(function() {
	$('#search_mem_card_type').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=51",
		method :"get",
		valueField : "cbs_id",
		textField : "cbs_chn",
		editable:false,
		onLoadSuccess: function(){
			cardTypeList = $('#search_mem_card_type').combobox("getData");
		}
	});
	// 初始化表格
	util.table('dg', {
		url :  global_param.context_name + "/member/memCardManage/lists", // 数据来源
		title : '会员档案列表', // 标题
		sortName : 'create_time', // 排序的列
		sortOrder : 'desc', // 排序方式
		pageSize : 10,
		pageList : [10 , 20, 50, 100],
		queryParams : {},
		columns : [ [ {
			field : 'mem_card_type',
			title : '类别',
			width : 10,
			sortable : true,
			formatter :function(value, row, index){
				for(var i = 0 ; i < cardTypeList.length ;i++){
					if(value == cardTypeList[i].cbs_id){
						return cardTypeList[i].cbs_chn;
					}
				}
				return "";
			}
		},{
			field : 'mem_card_number',
			title : '卡号',
			width : 10,
			sortable : true
		}, {
			field : 'mem_card_name',
			title : '姓名',
			width : 10,
			sortable : true
		} , {
			field : 'mem_card_chn',
			title : '拼音码',
			width : 10,
			sortable : true
		}, {
			field : 'mem_card_gender',
			title : '性别',
			width : 5,
			sortable : true,
			formatter :function(value, row, index){
				return util.getDict('SEX', value);
			}
		}, {
			field : 'mem_card_allCost',
			title : '累计消费',
			width : 10,
			sortable : true
		}, {
			field : 'mem_card_pointCost',
			title : '积分消费',
			width : 10,
			sortable : true
		}, {
			field : 'mem_card_acountMoney',
			title : '记分金额',
			width : 10,
			sortable : true
		}, {
			field : 'mem_card_startMoney',
			title : '初始金额',
			width : 10,
			sortable : true
		}, {
			field : 'mem_card_point',
			title : '积分',
			width : 10,
			sortable : true
		}, {
			field : 'mem_card_addPoint',
			title : '追加积分',
			width : 10,
			sortable : true
		}, {
			field : 'mem_card_discount',
			title : '扣率',
			width : 10,
			sortable : true
		}, {
			field : 'mem_lowest_discount',
			title : '最低扣率',
			width : 10,
			sortable : true
		}, {
			field : 'mem_card_barCode',
			title : '条码',
			width : 10,
			sortable : true
		}, {
			field : 'mem_card_tel',
			title : '电话',
			width : 10,
			sortable : true
		}, {
			field : 'mem_card_cellPhone',
			title : '手机',
			width : 12,
			sortable : true
		}, {
			field : 'mem_card_address',
			title : '地址',
			width : 15,
			sortable : true
		}, {
			field : 'mem_card_postCode',
			title : '邮编',
			width : 10,
			sortable : true
		}, {
			field : 'mem_card_idCard',
			title : '身份证',
			width : 15,
			sortable : true
		}, {
			field : 'mem_card_applyDate',
			title : '申请日期',
			width : 20,
			sortable : true,
			formatter :function(value, row, index){
				return util.formatDate(value);
			}
		}, {
			field : 'mem_card_failedDate',
			title : '失效日期',
			width : 20,
			sortable : true,
			formatter :function(value, row, index){
				return util.formatDate(value);
			}
		}, {
			field : 'status',
			title : '停用',
			width : 10,
			sortable : true,
			formatter :function(value, row, index){
				var statusChn = util.getDict('STATUS', value);
				if(statusChn == "停用"){
					return "<font color='red'>"+statusChn+"</font>";
				}else{
					return "<font color='green'>"+statusChn+"</font>";
				}
			}
		}, {
			field : 'DK',
			title : '未兑积分',
			width : 10,
			sortable : true,
			formatter :function(value, row, index){
				return Number(row.mem_card_point) - Number(row.mem_card_usedPoint);
			}
			
		}, {
			field : 'mem_card_usedPoint',
			title : '已兑积分',
			width : 10,
			sortable : true
		}] ],
		toolbar : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : addFunc
		}, {
			text : '批量增加',
			iconCls : 'icon-add',
			handler : addMembers
		},{
			text : '修改',
			iconCls : 'icon-modify',
			handler : updateFunc
		}, {
			text : '删除',
			iconCls : 'icon-delete',
			handler : deleteFunc
		}, {
			text : '启用',
			iconCls : 'icon-enable',
			handler : enableFunc
		}, {
			text : '停用',
			iconCls : 'icon-disable',
			handler : disableFunc
		}, {
			text : '追加积分',
			iconCls : 'icon-add',
			handler : addPoints
		}, {
			text : '消减积分',
			iconCls : 'icon-reduce',
			handler : reducePoints
		}]
	});
	// 初始化编辑窗口
	util.window('editWindow', {
		title : '会员卡信息',
		width : 625,
		height : 500,
	});
	util.window('editWindow1', {
		title : '批量添加会员卡',
		width : 350,
		height : 350,
	});
	util.window('editWindowAdd', {
		title : '追加积分',
		width : 350,
		height : 350,
	});
	util.window('editWindowReduce', {
		title : '消减积分',
		width : 350,
		height : 350,
	});
});

function query() {
	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#dg').datagrid('reload');
}

function addFunc() {
	util.openWindow('editWindow', "memCardManage/new");
}
function addMembers(){
	util.openWindow('editWindow1', "memCardManage/addMembers");
}


function updateFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editWindow', "memCardManage/" + node.mem_card_id);
}

function deleteFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/member/memCardManage/" + node.mem_card_id);
		if (data.result == "success") {
			$('#dg').datagrid('reload');
		}
		util.show(data.message);
	});
}

function enableFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	var data = util.put(global_param.context_name + "/member/memCardManage/" + node.mem_card_id + "/enable");
	if (data.result == "success") {
		$('#dg').datagrid('reload');
	}
	util.show(data.message);
}

function disableFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	var data = util.put(global_param.context_name + "/member/memCardManage/" + node.mem_card_id + "/disable");
	if (data.result == "success") {
		$('#dg').datagrid('reload');
	}
	util.show(data.message);
}

function addPoints(){
	var node = $('#dg').datagrid('getSelected');

	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editWindowAdd',global_param.context_name+ "/member/memPointManage/addPoint?point="+node.mem_card_point+"&card="+node.mem_card_number);
}

function reducePoints(){
	var node = $('#dg').datagrid('getSelected');

	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editWindowReduce',global_param.context_name+ "/member/memPointManage/reducePoint?point="+node.mem_card_point+"&card="+node.mem_card_number);
}



	

