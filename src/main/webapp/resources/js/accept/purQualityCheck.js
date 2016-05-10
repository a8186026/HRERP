//是否需要经过入库验收标识
var flag = true;
$(function(){
	//供方编码
	$('#search_supply_code').combobox({
		url : global_param.context_name + "/supply/supInfoManage/getList",
		method :"get",
		valueField : "sup_code",
		textField : "sup_name",
		editable:false,
	});
	//质检状态
	$('#search_accept_quantityStatus').combobox({
		data: [{
			label: '质检完成',
			value: 0
		},{
			label: '未质检',
			value: 1
		},{
			label: '待质检合格审核',
			value: 2
		},{
			label: '待特殊药品审核',
			value: 3
		}],
		method :"get",
		valueField : "value",
		textField : "label",
		editable:false,
		onLoadSuccess:function(){
			$(this).combobox("clear");
		}
	});
	//打印格式
	$('#printType').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=1006",
		method :"get",
		valueField : "cbs_code",
		textField : "cbs_chn",
		editable:false,
		onLoadSuccess:function(){
			$(this).combobox("setValue",quality-1);
		},
	});
	
	
	$("#orderList_dg").datagrid({
		url : global_param.context_name + "/accept/quality/listDetail",
		method : 'get',
		title : '货物信息管理', // 标题
		sortName : 'accept_quantityStatus', // 排序的列
		sortOrder : 'desc', // 排序方式
		fitcolumns : true,
		fit:true,
		rownumbers : true,
		nowrap : true,
		striped : true,
		ctrlSelect:true,//ctrl多选
		pageSize : 20,
		pageList : [20,50,100,200,500],
		pagination : true,
		queryParams : {
			accept_acceptStatus:0,//收货状态为0表示已经收货
			accept_stockStatus:1,//查询未入库1表示未入库
		},
		columns:[[ {
			field : 'ticket_id',
			title : '票号',
			width : 150,
			sortable : true
		},{
			field : 'product_code',
			title : '产品编码',
			width : 150,
			sortable : true
		},{
			field : 'product_commonname',
			title : '通用名',
			width : 150,
			sortable : true
		}, {
			field : 'quantity',
			title : '数量',
			width : 150,
			sortable : true,
			
		}, {
			field : 'unitprice',
			title : '单价',
			width : 150,
			sortable : true,
		}, {
			field : 'accept_arrivalNumber',
			title : '到货数量',
			width : 150,
			sortable : true
		}, {
			field : 'accept_acceptNumber',
			title : '收货数量',
			width : 150,
			sortable : true
		}, {
			field : 'accept_rejectNumber',
			title : '拒货数量',
			width : 150,
			sortable : true
		},{
			field : 'accept_checkQualitedNumber',
			title : '合格数量',
			width : 150,
			sortable : true
		},{
			field : 'accept_checkUnqualitedNumber',
			title : '不合格数量',
			width : 150,
			sortable : true
		},{
			field : 'accept_quantityStatus',
			title : '质检状态',
			width : 150,
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
		}]],
		toolbar :"#tb",
		onLoadSuccess:function(data){
			var accept_intakeTicket = util.get(global_param.context_name + "/accept/quality/getIntakeTicket");
			$("#accept_intakeTicket").val(accept_intakeTicket);
			$("#sumNumber").val(0);
			$("#sumCost").val(0);
			
			//获取收货系统参数
			var list = util.get(global_param.context_name + "/system/parameter/getParamCode?param="+"ACCEPT_CHECK");
			
			for(var i=0;i<list.data.length;i++){
				//如果是入库验收参数
				if(list.data[i].param_sub_id==3){
					//如果不需要入库验收,则点击入库按钮可进行入库
					if(list.data[i].param_sub_value==1)//1不需要进过入库验收，0需要进过入库验收
						flag = false;
					break;
				}
			}
			if(flag){
				document.getElementById("intake").style.visibility="hidden";
			}else{
				document.getElementById("intake").style.visibility="visible";
			}
				
		},
		onSelect:function(index,data){
			//选中一行时计算当前选中的金额和数量
			var nodes = $('#orderList_dg').datagrid('getSelections');
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
	
	
	util.window('editWindow', {
		title : '货物质量审核',
	}); 
});

//全选和反选
function selectAll(){
	//如果全选复选框是勾选状态
	if(document.getElementById("allCheck").checked){
		$("#orderList_dg").datagrid("selectAll");//选择全部记录
		var sumNum = 0, sumCost = 0;
		var nodes = $('#orderList_dg').datagrid('getSelections');
		for(var i=0;i<nodes.length;i++){
			sumNum = sumNum + nodes[i].accept_checkQualitedNumber;
			sumCost = sumCost + nodes[i].accept_checkQualitedNumber*nodes[i].unitprice;
		}
		$("#sumNumber").val(sumNum);
		$("#sumCost").val(sumCost);
	}else{
		$("#orderList_dg").datagrid("unselectAll");
		$("#sumNumber").val(0);
		$("#sumCost").val(0);
	}
}
function query() {
	$('#orderList_dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#orderList_dg').datagrid('options').queryParams.accept_acceptStatus = 0,//收货状态为0表示已经收货
	$('#orderList_dg').datagrid('options').queryParams.accept_stockStatus = 1,//查询未入库1表示未入库
	$('#orderList_dg').datagrid('reload');
}
function updatePurOrderList(){
	var nodes = $('#orderList_dg').datagrid('getSelections');
	//用多选来区分审核和入库
	if (nodes.length==0) {
		util.show("请选择一条记录");
		return;
	}else if(nodes.length>1){
		util.show("请选择单条记录");
		return;
	}else if(nodes[0].accept_quantityStatus!=1){
		util.show("此记录已审核");
		return;
	}
	util.openWindow('editWindow', "/HRERP/accept/quality/"+nodes[0].accept_check_id+"?ticket_id=" + nodes[0].ticket_id+"&commonName="+nodes[0].product_commonname);
}
function printList(){
	if(flag){
		util.show("需要经过库房验收才能入库！");
		return;
	}
	var nodes = $('#orderList_dg').datagrid('getSelections');
	var data ="";
	if(nodes.length==0){
		util.show("请选择入库记录");
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
		if(result.accept_intakeTicket!=null)
			util.alert("票号冲突,新入库票号为："+result.accept_intakeTicket);
		$("#orderList_dg").datagrid("reload");
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
				text : '质量审核报表',
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
