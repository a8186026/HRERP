$(function() {
	//查询框combobox初始化
	
	
	
	$('#search_accept_acceptStatus').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=905",
		method:"get",
		valueField : "cbs_code",
		textField : "cbs_chn",
		editable:false,
	});
	$('#search_accept_rejectStatus').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=897",
		method:"get",
		valueField : "cbs_code",
		textField : "cbs_chn",
		editable:false,
	});
	$('#search_accept_quantityStatus').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=909",
		method:"get",
		valueField : "cbs_code",
		textField : "cbs_chn",
		editable:false,
	});
	$('#search_accept_quantityCheckStatus').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=897",
		method:"get",
		valueField : "cbs_code",
		textField : "cbs_chn",
		editable:false,
	});
	$('#search_accept_specialStatus').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=897",
		method:"get",
		valueField : "cbs_code",
		textField : "cbs_chn",
		editable:false,
	});
	$('#search_accept_stockStatus').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=914",
		method:"get",
		valueField : "cbs_code",
		textField : "cbs_chn",
		editable:false,
	});
	
	// 初始化表格
	$("#dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/purchase/acceptSchedule/lists", // 数据来源
		title : '收货进度查看', // 标题
		sortName : 'accept_check_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitcolumns : true,
		singleSelect : true,//单选
		fit:true,
		pageSize : 10,
		pageList : [10,20,50,100],
		pagination : true,
		rownumbers : true,
		nowrap : true,
		striped : true,
		queryParams : {
			accept_stockStatus:1,
		},
		columns:[[ {
			field : 'ticket_id',
			title : '票号',
			width : 120,
			sortable : true
		}, {
			field : 'product_code',
			title : '产品编码',
			width : 120,
			sortable : true
		}, {
			field : 'product_commonname',
			title : '通用名',
			width : 150,
			sortable : true
		}, {
			field : 'accept_sum',
			title : '订货金额',
			width : 90,
			sortable : true
		}, {
			field : 'accept_acceptDate',
			title : '到货时间',
			width : 200,
			sortable : true,
			formatter :function(value, row, index){
				return util.formatDate(value);
			}
		}, {
			field : 'accept_acceptNumber',
			title : '收货数量',
			width : 200,
			sortable : true,
			
		}, {
			field : 'accept_arrivalNumber',
			title : '到货数量',
			width : 200,
			sortable : true,
			
		}, {
			field : 'accept_acceptStatus',
			title : '收货审核',
			width : 100,
			sortable : true,
			formatter:function(value){
				if(value==2){
					return "待审核";
				}else if(value==0){
					return "<font color='green'>已收货</font>";
				}else if(value==1){
					return "<font color='red'>未收货</font>";
				}else{
					return "";
				}
				
			}
		}, {
			field : 'accept_rejectStatus',
			title : '拒收审核',
			width : 100,
			sortable : true,
			formatter:function(value){
				if(value==2){
					return "<font color='green'>待审核</font>";
				}else if(value==0 ||value==1){
					return "<font color='green'>审核完成</font>";
				}else{
					return "";
				}
				
			}
		}, {
			field : 'accept_quantityStatus',
			title : '质量验收',
			width : 100,
			sortable : true,
			formatter:function(value){
				if(value==2){
					return "待质检合格审核";
				}else if(value==0){
					return "<font color='green'>质检完成</font>";
				}else if(value==1){
					return "<font color='red'>未质检</font>";
				}else if(value==3){
					return "<font color='blue'>待特殊药品审核</font>";
				}else{
					return "";
				}
				
			}
		}, {
			field : 'accept_quantityCheckStatus',
			title : '不合格审核',
			width : 100,
			sortable : true,
			formatter:function(value){
				if(value==2){
					return "未审核";
				}else if(value==0){
					return "<font color='green'>审核通过</font>";
				}else if(value==1){
					return "<font color='red'>审核不通过</font>";
				}else{
					return "";
				}
				
			}
		}, {
			field : 'accept_specialStatus',
			title : '特殊药品审核',
			width : 100,
			sortable : true,
			formatter:function(value){
				if(value==2){
					return "未审核";
				}else if(value==0){
					return "<font color='green'>审核通过</font>";
				}else if(value==1){
					return "<font color='red'>审核不通过</font>";
				}else{
					return "";
				}
				
			}
		},{
			field : 'accept_stockStatus',
			title : '库房验收状态',
			width : 100,
			sortable : true,
			formatter:function(value){
				 if(value==0){
					return "<font color='green'>已入库</font>";
				}else if(value==1){
					return "<font color='red'>未入库</font>";
				}else{
					return "";
				}
				
			}
		}]],
		onLoadSuccess:function(data){
			//获取收货系统参数
			var list = util.get(global_param.context_name + "/system/parameter/getParamCode?param="+"ACCEPT_CHECK");			
			for(var i=0;i<list.data.length;i++){
				//如果是入库验收参数
				if(list.data[i].param_sub_id==3){
					//如果不需要入库验收,则点击入库按钮可进行入库
					if(list.data[i].param_sub_value==1)//1不需要进过入库验收，0需要进过入库验收
						$("#dg").datagrid("hideColumn","accept_stockStatus");
					break;
				}
			}
		}
	
	});
	
	
});

function query(){
	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#dg').datagrid('reload');
}


