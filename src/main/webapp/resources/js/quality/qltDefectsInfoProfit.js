//是否需要经过审核报损标识
var flag = true;
//记录orderList_dg选中的行，以便行切换的时候关闭当前行编辑
var currentEdit = 0;
$(function() {
	var dosageTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=98");
	var unitTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=132");
	
	// 初始化表格
	$("#dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/quality/defectsInfo/lists", // 数据来源
		title : '不合格品种报损', // 标题
		sortName : 'defects_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitColumns : true,
		pageSize : 10,
		pageList : [10, 20, 50, 100],
		//fitColumns : true,
		fit : true,
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		nowrap : true,
		striped : true, // 奇偶行颜色不同
		queryParams: {
			defects_check:0,
			defects_profitLoss:1,
			defects_destruction:1,
		},
		columns :[ [{
			field : 'defects_date',
			title : '日期',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value,"yyyy-MM-dd");
			}
		}, {
			field : 'product_code',
			title : '产品编号',
			width : 80,
		}, {
			field : 'product_name',
			title : '产品名称',
			width : 80,
		}, {
			field : 'product_specification',
			title : '规格',
			width : 80,
		}, {
			field : 'product_productarea',
			title : '产地',
			width : 100,
		}, {
			field : 'product_factoryname',
			title : '分类',
			width : 100,
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
			field : 'stock_storageNumber',
			title : '库存数量',
			width : 80,
		}, {
			field : 'stock_batchCode',
			title : '批号',
			width : 80, 
		}, {
			field : 'stock_invalidDate',
			title : '失效期',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value,"yyyy-MM-dd");
			}
		}, {
			field : 'stock_intakeSmallNumber',
			title : '入库小号',
			width : 80,
			sortable : true
		}, {
			field : 'department_id',
			title : '调出库房',
			width : 80,
			sortable : true
		}, { 
			field : 'defects_storagePlace',
			title : '存放地点',
			width : 80,
			sortable : true
		}, {
			field : 'stock_intakeSmallNumber',
			title : '入库小号',
			width : 80,
			sortable : true
		}, {
			field : 'defects_suggestion',
			title : '处理意见',
			width : 80,
			sortable : true
		}, {
			field : 'defects_remark',
			title : '备注',
			width : 80,
			sortable : true
		}, {
			field : 'defects_person',
			title : '操作员',
			width : 80,
			sortable : true
		}, {
			field : 'defects_ticketNo',
			title : '票号',
			width : 80,
			sortable : true
		}, {
			field : 'defects_reason',
			title : '不合格原因',
			width : 80,
			sortable : true
		}, {
			field : 'defects_quality',
			title : '质量状况',
			width : 80,
			sortable : true
		}, {
			field : 'defects_check',
			title : '审核',
			width : 80,
			sortable : true,
			formatter:function(value){
				if(value==1){
					return "<font color='red'>待审核</font>";
				}else if(value==0){
					return "<font color='green'>审核完成</font>";
				}else{
					return "";
				}
			}
		}, {
			field : 'defects_checkPerson',
			title : '确认人',
			width : 80,
			sortable : true
		}, {
			field : 'defects_checkSuggestion',
			title : '确认意见',
			width : 150,
		}, {
			field : 'defects_checkTime',
			title : '确认时间',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value,"yyyy-MM-dd");
			}
		}, {
			field : 'defects_profitLoss',
			title : '报损',
			width : 80,
			sortable : true,
			formatter:function(value){
				if(value==1){
					return "<font color='red'>待审核</font>";
				}else if(value==0){
					return "<font color='green'>审核完成</font>";
				}else{
					return "";
				}
			}
		}, {
			field : 'defects_profitReason',
			title : '报损原因',
			width : 150,
			editor:{  
                type:'text',  
                options:{
                    required:true  
                }  
            }
		}]],
		/*toolbar : [{
			text : '报损申请',
			iconCls : 'icon-add',
			handler : profitLoss
		}, {
			text : '直接报损',
			iconCls : 'icon-modify',
			handler : check
		}, {
			text : '删除',
			iconCls : 'icon-delete',
			handler : deleteFunc
		}],*/
		toolbar :"#tb",
	
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
		onLoadSuccess:function(data){
			var defects_profitTicketNo = util.get(global_param.context_name + "/quality/profit/getMaxDefectsProfitTicket");
			$("#defects_profitTicketNo").val(defects_profitTicketNo);
			
			//获取报损系统参数
			var list = util.get(global_param.context_name + "/system/parameter/getParamCode?param="+"QUALITY_CHECK");
			
			for(var i=0;i<list.data.length;i++){
				//如果是报损审核参数
				if(list.data[i].param_sub_id==8){
					//如果不需要报损审核,则点击报损申请按钮可进行报损
					if(list.data[i].param_sub_value==1)//1不需要经过审核直接报损，0需要进过审核报损
						flag = false;
					break;
				}
			}
			if(flag){
				document.getElementById("check").style.visibility="hidden";
			}else{
				document.getElementById("check").style.visibility="visible";
			}
			
			
			if(data.rows.length == 0)
				scrollShow($("#dg"));
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

function profitLoss(){
	//保存时关闭当前行的编辑
	$("#dg").datagrid('endEdit', currentEdit);	

	//获取所有选中的行
	var nodes = $('#dg').datagrid('getSelections');
	if(nodes.length==0){
		util.show("请选择报损申请条目");
		return;
	}
	var data="";
	for(var i=0;i<nodes.length;i++){
		if(nodes[i].defects_profitReason ==""){
			util.show("请输入报损原因");
			return;
		}
		//将行数据封装成字符串，以便传到后台
		data+=nodes[i].defects_id+"_";
		data+=nodes[i].defects_profitReason;
		
		if(i<nodes.length-1)
			data+="|";
	}
	//报损申请操作
	var result = util.get("/HRERP/quality/profit/profitLoss?data="+data+
			"&oldProfitTicket="+$("#defects_profitTicketNo").val());
	
	if(result.result=="success"){
		util.show(result.message);
		if(result.defects_profitTicketNo!=null)
			util.alert("票号冲突,新报损票号为："+result.defects_profitTicketNo);
		$("#dg").datagrid("reload");
	}else if(result.result=="failure"){
		util.show(result.message);
	}else{
		util.show("出错了");
	}
}

function check(){
	if(flag){
		util.show("需要逐步审核！");
		return;
	}
	//保存时关闭当前行的编辑
	$("#dg").datagrid('endEdit', currentEdit);	

	//获取所有选中的行
	var nodes = $('#dg').datagrid('getSelections');
	if(nodes.length==0){
		util.show("请选择报损申请条目");
		return;
	}
	var data="";
	for(var i=0;i<nodes.length;i++){
		if(nodes[i].defects_profitReason ==""){
			util.show("请输入报损原因");
			return;
		}
		//将行数据封装成字符串，以便传到后台
		data+=nodes[i].defects_id+"_";
		data+=nodes[i].defects_profitReason;
		
		if(i<nodes.length-1)
			data+="|";
	}
	

	alert(data);
	
	//直接报损申请操作
	var result = util.get("/HRERP/quality/profit/profitCheck?data="+data+
			"&oldProfitTicket="+$("#defects_profitTicketNo").val());
	
	if(result.result=="success"){
		util.show(result.message);
		if(result.defects_profitTicketNo!=null)
			util.alert("票号冲突,新报损票号为："+result.defects_profitTicketNo);
		$("#dg").datagrid("reload");
	}else if(result.result=="failure"){
		util.show(result.message);
	}else{
		util.show("出错了");
	}
}

function deleteFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/quality/profit/" + node.defects_id);
		if (data.result == "success") {
			$('#dg').datagrid('reload');
		}
		util.show(data.message);
	});
}

function scrollShow(datagrid) {  
    datagrid.prev(".datagrid-view2").children(".datagrid-body").html("<div style='width:" + datagrid.prev(".datagrid-view2").find(".datagrid-header-row").width() + "px;border:solid 0px;height:1px;'></div>");  
} 
