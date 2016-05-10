$(function() {
	var dosageTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=98");
	var unitTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=132");
	var stock_storage = null;
	var product_immaintain = null;
	var pro_group_no = null;
	
	//获取库房
	$('#stock_storage').combobox({
		url : global_param.context_name + "/accept/stock/getDepartmentList",
		method :"get",
		valueField : "department_id",
		textField : "department_fullname",
	    onSelect:function(record){  
	    	var url = global_param.context_name + "/accept/stock/groupNOList?department_id=" + record.department_id;
	        $('#pro_group_no').combobox('reload',url);  
	    }  
	});  
	
	//获取组号
	$('#pro_group_no').combobox({
		method :"get",
		valueField : "pro_group_no",
		textField : "pro_group_no"
	});
	
	//获取品种类型
	$('#search_product_immaintain').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=996",
		method :"get",
		valueField : "cbs_code",
		textField : "cbs_chn",
		editable:false,
		onLoadSuccess: function(){
			productCheckList = $('#search_product_immaintain').combobox("getData");
		}
	});
	
	// 初始化表格
	$("#dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/quality/maintainInfoCheck/lists", // 数据来源
		title : '养护品种信息管理', // 标题
		sortName : 'stock_intakeTicket', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitColumns : true,
		singleSelect : false, // 单选
		pageSize : 10,
		pageList : [10, 20, 50, 100],
		fitColumns : true,
		fit : true,
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		nowrap : true,
		striped : true, // 奇偶行颜色不同
		queryParams : {
			stock_storage:stock_storage,
			product_immaintain:product_immaintain,
			pro_group_no:pro_group_no,
		},
		columns :[ [ {
			field : 'stock_intakeTicket',
			title : '入库票号',
			width : 100,
			sortable : true
		}, {
			field : 'stock_intakeSmallNumber',
			title : '入库小号',
			width : 80,
			sortable : true
		}, {
			field : 'stock_batchCode',
			title : '批号',
			width : 80,
			sortable : true
		}, {
			field : 'stock_storage',
			title : '库房',
			width : 80,
			sortable : true
		}, {
			field : 'department_fullname',
			title : '库房名称',
			width : 80,
			sortable : true
		}, {
			field : 'pro_group_no',
			title : '组号',
			width : 80,
		}, {
			field : 'pro_location_no',
			title : '货位号',
			width : 80,
		}, {
			field : 'product_code',
			title : '产品编号',
			width : 80,
		}, {
			field : 'product_name',
			title : '产品名称',
			width : 80,
		}, {
			field : 'product_immaintain',
			title : '重点品种',
			formatter:function(value){
				if(value==0){
					return "<font color='green'>普通品种</font>";
				}else if(value==1){
					return "<font color='red'>重点品种</font>";
				}
			}
		}, {
			field : 'product_specification',
			title : '规格',
			width : 80,
		}, {
			field : 'product_unit',
			title : '单位',
			width : 60,
			formatter :function(value, row, index){
				for(var i = 0 ; i < unitTypes.length ;i++){
					if(value == unitTypes[i].cbs_id){
						return unitTypes[i].cbs_chn;
					}
				}
			}
		},  {
			field : 'product_productarea',
			title : '产地',
			width : 100,
		}, {
			field : 'product_factoryname',
			title : '生产厂家',
			width : 100,
		}, {
			field : 'product_dosagetype',
			title : '剂型',
			width : 50,
			formatter :function(value, row, index){
				for(var i = 0 ; i < dosageTypes.length ;i++){
					if(value == dosageTypes[i].cbs_id){
						return dosageTypes[i].cbs_chn;
					}
				}
			}
		}, {
			field : 'product_approvalnum',
			title : '批准文号',
			width : 100,
		}, {
			field : 'stock_invalidDate',
			title : '失效期',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value,"yyyy-MM-dd");
			}
		}, {
			field : 'stock_produceDate',
			title : '生产日期',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value,"yyyy-MM-dd");
			}
		}, {
			field : 'stock_sterilizationbnum',
			title : '灭菌批号',
			width : 80,
			sortable : true
		}, {
			field : 'stock_sterilizationtime',
			title : '灭菌日期',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value,"yyyy-MM-dd");
			}
		}, {
			field : 'stock_maintaintime',
			title : '养护时间',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value,"yyyy-MM-dd");
			}
		}, {
			field : 'stock_storageNumber',
			title : '库房数量',
			width : 80,
			sortable : true
		}, {
			field : 'maintain_days',
			title : '养护天数',
			width : 80,
		}] ],
		toolbar :"#tb",
		onLoadSuccess:function(data){
			var maintain_ticketNo = util.get(global_param.context_name + "/quality/maintainInfoCheck/getMaintainTicket");
			$("#maintain_ticketNo").val(maintain_ticketNo);
	}
});
	// 初始化编辑窗口
	util.window('editWindow', {
		title : '重点品种信息'
	});
	
});

function query() {
	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#dg').datagrid('reload');
}

function selectAll(){
	 $("#dg").datagrid("checkAll");
}

function unSelectAll(){
	$("#dg").datagrid("uncheckAll");
}

function maintain() {
	var nodes = $('#dg').datagrid('getSelections');
	var data ="";
	if(nodes.length==0){
		util.show("请选择要确认的养护品种");
		return;
	}
	//alert(JSON.stringify(nodes));
	
	for(var i=0;i<nodes.length;i++){
		
		if(i<nodes.length-1){
			data = data+nodes[i].stock_info_id+"_";
			data += nodes[i].product_id+"_";
			data += nodes[i].stock_storageNumber+"_";
			data += nodes[i].department_fullname+"_";
			if(nodes[i].product_immaintain == 1)
				data += $("#immaintain_days").val();
			else
				data += $("#ptmaintain_days").val();
			data+="|";
			
		}
		else{
			data = data+nodes[nodes.length-1].stock_info_id+"_";
			data = data+nodes[nodes.length-1].product_id+"_";
			data = data+nodes[nodes.length-1].stock_storageNumber+"_";
			data = data+nodes[nodes.length-1].department_fullname+"_";
			if(nodes[nodes.length-1].product_immaintain == 1)
				data += $("#immaintain_days").val();
			else
				data += $("#ptmaintain_days").val();
		}
	}
	//养护操作
	var result = util.get("/HRERP/quality/maintainInfoCheck/maintain?data="+data+
			"&oldMaintainTicket="+$("#maintain_ticketNo").val());
	
	if(result.result=="success"){
		util.show(result.message);
		if(result.maintain_ticketNo!=null)
			util.alert("票号冲突,新入库票号为："+result.maintain_ticketNo);
		$("#dg").datagrid("reload");
	}else if(result.result=="failure"){
		util.show(result.message);
	}else{
		util.show("出错了");
	}
}
