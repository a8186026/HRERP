$(function() {
	//打印格式
	$('#printType').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=1012",
		method :"get",
		valueField : "cbs_code",
		textField : "cbs_chn",
		editable:false,
		onLoadSuccess:function(){
			$(this).combobox("setValue",891);
		},
	});
	//获取库房
	$('#department_id').combobox({
		url : global_param.context_name + "/accept/stock/getDepartmentList",
		method :"get",
		valueField : "department_id",
		textField : "department_fullname",
	    onSelect:function(record){  
	    	var url = global_param.context_name + "/accept/stock/groupNOList?department_id=" + record.department_id;
	        $('#accept_groupNo').combobox('reload',url);  
	    }  
	});  
	//获取组号
	$('#accept_groupNo').combobox({
		method :"get",
		valueField : "pro_group_no",
		textField : "pro_group_no"
	});

	
	// 初始化表格
	$("#dg").datagrid({
		url : global_param.context_name + "/accept/quality/listDetail",// 数据来源
		method : 'get',
		title : '收货确认管理', // 标题
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
			accept_quantityStatus:0,//质检状态为0表示已经质检
			accept_stockStatus:1,//入库状态为1表示未入库
			accept_intakeTicket:'null',//查询未入库即没有入库票号的数据（数据库数据是NULL，此处任意字符串表示）
		},
		columns:[[ {
			field : 'ticket_id',
			title : '票号',
			width : 100,
			sortable : true
		}, {
			field : 'pur_order_id',
			title : '订单单号',
			width : 100,
			sortable : true
		}, {
			field : 'product_code',
			title : '产品编码',
			width : 100,
			sortable : true
		},  {
			field : 'product_commonname',
			title : '通用名',
			width : 100,
			sortable : true
		}, {
			field : 'accept_groupNo',
			title : '组号',
			width : 100,
			sortable : true
		}, {
			field : 'accept_tempCtrlSituation',
			title : '温控情况',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.getDict('CHECK_RESULT', value);
			}
		}, {
			field : 'accept_transMode',
			title : '运输方式',
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
			field : 'accept_arrivalNumber',
			title : '到货数量',
			width : 100,
			sortable : true
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
		},{
			field : 'accept_checkQualitedNumber',
			title : '合格数量',
			width : 100,
			sortable : true
		},{
			field : 'accept_checkUnqualitedNumber',
			title : '不合格数量',
			width : 100,
			sortable : true
		},{
			field : 'accept_quantityStatus',
			title : '质检状态',
			width : 100,
			sortable : true,
			formatter: function(value, row, index){
				if(value==1){
					return "<font color='red'>质检未完成</font>";
				}else if(value==0){
					return "<font color='green'>质检完成</font>";
				}else if(value==2){
					return "<font color='blue'>待质检合格审核</font>";
				}else if(value==3){
					return "<font color='blue'>待特殊药品审核</font>";
				}
			}
		},{
			field : 'accept_check_id',
			title : '收货审核ID',
			hidden:true,
		}, {
			field : 'accept_stockStatus',
			title : '库房验收状态',
			width : 100,
			sortable : true,
			formatter : function(value, row, index){
				return "<font color='red'>未入库</font>";	
			}
		}]],
		/*toolbar : [{
			text : '入库',
			iconCls : 'icon-add',
			handler : stockCheck
		}] */
		toolbar :"#tb",
		onLoadSuccess:function(data){
			var accept_intakeTicket = util.get(global_param.context_name + "/accept/quality/getIntakeTicket");
			$("#accept_intakeTicket").val(accept_intakeTicket);
			$("#sumNumber").val(0);
			$("#sumCost").val(0);
	},
	onSelect:function(index,data){
		//选中一行时计算当前选中的金额和数量
		var nodes = $('#dg').datagrid('getSelections');
		if(nodes.length==1){
			$("#sumNumber").val(data.accept_checkQualitedNumber);
			$("#sumCost").val(data.accept_checkQualitedNumber*data.unitprice);
		}else{
			$("#sumNumber").val(Number($("#sumNumber").val())+data.accept_checkQualitedNumber);
			$("#sumCost").val(Number($("#sumCost").val())+data.accept_checkQualitedNumber*data.unitprice);
		}
	},
	onUnselect:function(index,data){
		$("#sumNumber").val(Number($("#sumNumber").val())-data.accept_checkQualitedNumber);
		$("#sumCost").val(Number($("#sumCost").val())-data.accept_checkQualitedNumber*data.unitprice);
	}
});
	// 初始化编辑窗口
	util.window('editStockCheckWindow', {
	}); 
});
function query() {
	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	//需要带产品ID查询
	$('#dg').datagrid('options').queryParams.accept_quantityStatus=0,//质检状态为0表示已经质检
	$('#dg').datagrid('options').queryParams.accept_stockStatus=1,//入库状态为1表示未入库
	$('#dg').datagrid('options').queryParams.accept_intakeTicket='null',//查询未入库即没有入库票号的数据（数据库数据是NULL，此处任意字符串表示）
	
	$('#dg').datagrid('reload');
}

function printList(){
	var nodes = $('#dg').datagrid('getSelections');
	var data ="";
	if(nodes.length==0){
		util.show("请选择一条记录进行入库");
		return;
	}
	for(var i=0;i<nodes.length;i++){
		if(nodes[i].accept_quantityStatus!=0){
			util.show("有条目质检未完成,无法入库！");
			return;
		}
		if(i<nodes.length-1){
			data = data+nodes[i].accept_check_id+"_";
			data += nodes[i].quantity+"_";
			data += nodes[i].product_id+"_";
			data += nodes[i].department_id;
			data+="|";
		}
		else{
			data = data+nodes[nodes.length-1].accept_check_id+"_";
			data = data+nodes[nodes.length-1].quantity+"_";
			data = data+nodes[nodes.length-1].product_id+"_";
			data = data+nodes[nodes.length-1].department_id;
		}
	}
	alert(data);
	//入库操作
	var result = util.get("/HRERP/accept/quality/intake?data="+data+
			"&oldIntakeTicket="+$("#accept_intakeTicket").val()+"&printType="+$("#printType").combobox("getValue"));
	
	if(result.result=="success"){
		util.show(result.message);
		if(result.accept_intakeTicket!=null){
			util.show("票号冲突,新入库票号为："+result.accept_intakeTicket);
		}
		$("#dg").datagrid("reload");
	}else if(result.result=="failure"){
		util.show(result.message);
	}else{
		util.show("出错了");
	}
}
function perviewPrint(){
	var printType = $("#printType").combo('getValue');
	
	if(printType==""){
		util.show("请选择打印格式");
	}else{
		var node = {
				text : '入库报表',
				attributes : {url : "basInfo/report/report?fileName="+printType}
			};
		self.parent.addTab(node);
		//util.get(global_param.context_name + "/basInfo/report/report?fileName="+printType);
		/*$('#tt').tabs('add',{
			title:"测试",
			content:'<iframe style="width: 100%; height: 100%;" frameborder="0" src="' + global_param.context_name + "/basInfo/report/report?fileName="+printType + '"></iframe>',
			closable:true
		});*/
	}
	
}
