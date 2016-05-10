var saleMeds = new Array();

// 控件ID组
var ctrls = [ "input_sale_cust_memberId", "input_sale_cust_salesClerk",
		"input_sale_cust_handle", "stock_productName", "cutoff",
		"input_sale_med_quantity", "btn_salemed" ,
		"input_sale_cust_payNumber","input_sale_cust_otherCash", "input_health_insurance","input_sale_cust_reciveCash","btn_submitCust"];

// 由于JS加载顺序的问题，聚焦到第一序列
var ctrlNumber = 0;

$(function() {


	// 绑定按键动作，回车进入初始位置
	var e = (typeof event != 'undefined') ? window.event : e;
	// 绑定键盘动作
	document.onkeydown = function(e) {
		var actElement = document.activeElement;
		if (e.keyCode == 13 && document.activeElement.type != "button"&& actElement.id != "stock_productName") {
			ctrlNumber = SystemKeyPress.FocusForKeyPress(ctrls, ctrlNumber);
		}
		else if (e.keyCode == 13 && document.activeElement.type != "button"&& actElement.id == "stock_productName"){
			if($("#stock_productName").attr("value")!="" && $("#stock_productName").attr("value")!=null){
				productList();
			}
			else if($("#stock_productName").attr("value")==""){
				ctrlNumber = SystemKeyPress.FocusForKeyPress(ctrls, 7);
			}
		}
		
	};
/*
	SystemKeyPress.ChangeNumberAndSelectWhenFocusByID("input_sale_cust_memberId",ctrls,ctrlNumber);
	SystemKeyPress.ChangeNumberAndSelectWhenFocusByID("input_sale_cust_salesClerk",ctrls,ctrlNumber);
	SystemKeyPress.ChangeNumberAndSelectWhenFocusByID("input_sale_cust_handle",ctrls,ctrlNumber);
	SystemKeyPress.ChangeNumberAndSelectWhenFocusByID("stock_productName",ctrls,ctrlNumber);
	SystemKeyPress.ChangeNumberAndSelectWhenFocusByID("cutoff",ctrls,ctrlNumber);
	SystemKeyPress.ChangeNumberAndSelectWhenFocusByID("input_sale_med_quantity",ctrls,ctrlNumber);
	SystemKeyPress.ChangeNumberAndSelectWhenFocusByID("btn_salemed",ctrls,ctrlNumber);
	SystemKeyPress.ChangeNumberAndSelectWhenFocusByID("input_sale_cust_payNumber",ctrls,ctrlNumber);
	SystemKeyPress.ChangeNumberAndSelectWhenFocusByID("input_sale_cust_otherCash",ctrls,ctrlNumber);
	SystemKeyPress.ChangeNumberAndSelectWhenFocusByID("input_health_insurance",ctrls,ctrlNumber);
	SystemKeyPress.ChangeNumberAndSelectWhenFocusByID("input_sale_cust_reciveCash",ctrls,ctrlNumber);
*/
	// 绑定失去焦点
	$("#cutoff").bind(
			"blur",
			function() {
				$("#input_sale_med_afterCutoff")
						.val(
								$("#stock_unitPrice").val()
										* $("#cutoff").val()/100);
				$("#input_sale_med_cash").val(
						$("#input_sale_med_quantity").val()
								* $("#input_sale_med_afterCutoff").val());
			});

	$("#input_sale_med_quantity").bind(
			"blur",
			function() {
				$("#input_sale_med_cash").val(
						$("#input_sale_med_quantity").val()
								* $("#input_sale_med_afterCutoff").val());
			});
	
	//付数
	$("#input_sale_cust_payNumber").bind(
			"blur",
			function() {
				$("#input_sale_cust_totalCash").val($("#input_sale_cust_payNumber").val()*$("#input_sale_single_cush").val());
	});

	//其他收费
	$("#input_sale_cust_otherCash").bind(
			"blur",
			function() {
				$("#input_sale_cust_amountReceivable").val(new Number($("#input_sale_cust_totalCash").val())+new Number($("#input_sale_cust_otherCash").val()));
	});
	//市医保
	$("#input_health_insurance").bind(
			"blur",
			function() {
				$("#input_sale_cust_cash").val(($("#input_sale_cust_amountReceivable").val()-$("#input_health_insurance").val()).toFixed(2));
	});
	//实收现金
	$("#input_sale_cust_reciveCash").bind(
			"blur",
			function() {
				$("#input_sale_cust_change").val(($("#input_sale_cust_reciveCash").val()-$("#input_sale_cust_cash").val()).toFixed(2));
	});
	
	// 初始化表格
	$("#dg_sale_med").datagrid({
		url : global_param.context_name + "/medicine/sale/saleMedList", // 数据来源
		title : '购买药品列表', // 标题
		idField : 'sale_med_id',
		sortName : 'sale_med_id', // 排序的列
		sortOrder : 'dec', // 排序方式
		method : 'get',
		singleSelect : true,
		fitColumns : true,
		fit:true,
		nowrap : true,
		queryParams : {
			ticketNumber : "",
		},
		pageSize : 10,
		pageList : [ 10, 20, 50, 100 ],
		columns : [ [ {
			field : 'sale_stock',
			title : '库房',
			width : 50,
			sortable : true
		}, {
			field : 'sale_med_serialId',
			title : '序号',
			width : 50,
			sortable : true
		}, {
			field : 'sale_med_code',
			title : '小号',
			width : 50,
			sortable : true
		}, {
			field : 'sale_med_productQuantity',
			title : '产品条码',
			width : 50,
			sortable : true
		}, {
			field : 'sale_med_reachCash',
			title : '满额',
			width : 50,
			sortable : true
		}, {
			field : 'sale_med_afterCutoff',
			title : '折后价',
			width : 50,
			sortable : true
		}, {
			field : 'sale_med_priceOff',
			title : '折让单价',
			width : 50,
			sortable : true
		}, {
			field : 'sale_med_cash',
			title : '金额',
			width : 50,
			sortable : true
		}, {
			field : 'sale_med_salePrice',
			title : '销售价',
			width : 50,
			sortable : true
		}, {
			field : 'sale_med_quantity',
			title : '件数',
			width : 50,
			sortable : true
		}, {
			field : 'sale_med_pointMul',
			title : '积分倍数',
			width : 50,
			sortable : true
		}, {
			field : 'sale_med_stockOutCode',
			title : '出库小号',
			width : 50,
			sortable : true
		}, {
			field : 'sale_med_Ticket',
			title : '票号',
			width : 50,
			sortable : true
		}, {
			field : 'sale_med_ifInsuranceType',
			title : '是否医保品种',
			width : 50,
			sortable : true
		}, {
			field : 'sale_med_loseDate',
			title : '失效期',
			width : 50,
			sortable : true
		}, {
			field : 'sale_med_saleDate',
			title : '销售日期',
			width : 50,
			sortable : true
		} ] ],
		onLoadSuccess:function(data){
			var totalCash=0;//当前产品总价格
			for(var i = 0 ; i < data.total ;i++){
				totalCash =totalCash + data.rows[i].sale_med_cash;
			}
			$("#input_sale_single_cush").val(totalCash);
			$("#input_sale_cust_totalCash").val($("#input_sale_cust_payNumber").val()*totalCash);
		}
	});
	$("#dg2_stock").datagrid({
		title : '药品库存列表', // 标题
		idField : 'stock_id',
		sortName : 'stock_id', // 排序的列
		sortOrder : 'desc', // 顺序
		method : 'get',
		singleSelect : true,
		fitColumns : true,
		fit :true,
		nowrap : true,
		queryParams : {},
		pageSize : 10,
		pageList : [ 10, 20, 50, 100 ],
		columns : [ [ {
			field : 'stock_saleType',
			title : '销售类型',
			width : 10,
			sortable : true
		}, {
			field : 'stock_productNumber',
			title : '产品编号',
			width : 15,
			sortable : true
		}, {
			field : 'stock_productCode',
			title : '产品条码',
			width : 15,
			sortable : true
		}, {
			field : 'stock_productName',
			title : '产品名称',
			width : 15,
			sortable : true
		}, {
			field : 'stock_productFormat',
			title : '产品规格',
			width : 15,
			sortable : true
		}, {
			field : 'stock_productCompany',
			title : '生产厂家',
			width : 18,
			sortable : true
		}, {
			field : 'stock_productUnit',
			title : '单位',
			width : 10,
			sortable : true
		}, {
			field : 'stock_packaging',
			title : '包装量',
			width : 10,
			sortable : true
		}, {
			field : 'stock_unitPrice',
			title : '单价',
			width : 10,
			sortable : true
		}, {
			field : 'sotck_lowestPrice',
			title : '能开价',
			width : 10,
			sortable : true
		}, {
			field : 'stock_buyPrice',
			title : '最后进价',
			width : 10,
			sortable : true
		}, {
			field : 'stock_availableQuantity',
			title : '可用数',
			width : 10,
			sortable : true
		}, {
			field : 'stock_stockQuantity',
			title : '库房数量',
			width : 10,
			sortable : true
		}, {
			field : 'stock_quantity',
			title : '数量',
			width : 10,
			sortable : true
		}, {
			field : 'stock_PinYin',
			title : '拼音',
			width : 10,
			sortable : true
		}, {
			field : 'stock_priceFormat',
			title : '价格格式',
			width : 10,
			sortable : true
		}, {
			field : 'stock_quantityFormat',
			title : '数量格式',
			width : 10,
			sortable : true
		}, {
			field : 'stock_productSerial',
			title : '产品序号',
			width : 10,
			sortable : true
		}, {
			field : 'stock_productPoint',
			title : '产品积分',
			width : 10,
			sortable : true
		}, {
			field : 'stock_giveRatio',
			title : '满额赠系数',
			width : 10,
			sortable : true
		}, {
			field : 'stock_stockInCode',
			title : '入库小号',
			width : 10,
			sortable : true
		}, {
			field : 'stock_saleBouns',
			title : '零售提成',
			width : 10,
			sortable : true
		}, {
			field : 'stock_ifMemberDay',
			title : '是否会员日',
			width : 10,
			sortable : true
		}, {
			field : 'stock_storeCash',
			title : '计分金额',
			width : 10,
			sortable : true
		}, {
			field : 'stock_pointMul',
			title : '积分倍数',
			width : 10,
			sortable : true
		}, {
			field : 'stock_retatil',
			title : '零售',
			width : 10,
			sortable : true
		}, {
			field : 'stock_retatilMakeGroup',
			title : '拆零组',
			width : 10,
			sortable : true
		} ] ],

	});
	$("#activity").datagrid({
		title : '活动信息', // 标题
		idField : 'stock_id',
		sortName : 'stock_id', // 排序的列
		sortOrder : 'desc', // 顺序
		method : 'get',
		singleSelect : true,
		fitColumns : true,
		fit :true,
		nowrap : true,
		queryParams : {},
		pageSize : 10,
		pageList : [ 10, 20, 50, 100 ],
		columns : [ [ {
			field : 'stock_id',
			title : '类型',
			width : 10,
			sortable : true
		}, {
			field : 'stock_productNumber',
			title : '个数',
			width : 15,
			sortable : true
		} ] ],

	});
});
function submitOrder() {
	if($("#input_sale_cust_memberId").val()=="")
	{
		util.show("请输入会员号！");
		return;
	}
	if($("#cutoff").val()=="")
	{
		util.show("请输入折扣！");
		return;
	}
	if ($("#input_sale_cust_ticketNumber").val() == "") {
		// 提交订单
		$.ajax({
			type : $("#method").val(), // 表单提交类型
			url : $("#orderUrl").val(), // 表单提交目标
			data : $("#order").serialize(), // 表单数据
			success : function(msg) {
				$("#input_sale_cust_ticketNumber").val(msg.sale_cust_ticketNumber);
				$("#sale_cust_ticketNumber").val(msg.sale_cust_ticketNumber);
				$("#sale_med_Ticket").val(msg.sale_cust_ticketNumber);
				submitMed();
			}
		});
	}


	// 提交药品信息
	if ($("#input_sale_cust_ticketNumber").val() != "") {
		submitMed();
	}

	// 提交药品信息
	function submitMed() {
		$.ajax({
			type : $("#method").val(), // 表单提交类型
			url : $("#medUrl").val(), // 表单提交目标
			data : $("#med").serialize(), // 表单数据
			success : function() {
				var queryParams = $('#dg_sale_med').datagrid('options').queryParams;
				queryParams.ticketNumber = $(
						"#input_sale_cust_ticketNumber").val();
				$("#dg_sale_med").datagrid('reload');
				
				//如果成功 清空表格
				clearMedInfo();
				//重新将焦点聚集到产品
				$("#stock_productName").focus();
				
			}
		});
	}
	
	
	
	// 清空药品信息
	function clearMedInfo() {
		var ctrlsForMed = [ "stock_productName", "stock_productNumber",
				"stock_unitPrice", "cutoff", "input_sale_med_afterCutoff",
				"input_sale_med_quantity", "input_sale_med_cash" ];
		
		for(var i = 0 ; i < ctrlsForMed.length ; i++){
			$("#"+ctrlsForMed[i]).attr("value","");
			if(ctrlsForMed[i] == "cutoff" ){
				$("#"+ctrlsForMed[i]).attr("value","100");
			}
			else if(ctrlsForMed[i] == "input_sale_med_quantity"){
				$("#"+ctrlsForMed[i]).attr("value","1");
			}
		}
	}
}

//删除药品信息
function cancelMed(){
	
	var node = $('#dg_sale_med').datagrid('getSelected');
	if (!node) {
		util.show("请选择要删除的记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/medicine/sale/" + node.sale_med_id);
		if (data.result == "success") {
			$('#dg_sale_med').datagrid('reload');
		}
		util.show(data.message);
	});
	
}
//删除整个订单
function cancelOrder(){
	var ticketNumber = $("#sale_cust_ticketNumber").val();
	if(ticketNumber != ""){
			util.confirm("确定要执行删除订单操作？", function() {
			var data = util.del(global_param.context_name + "/medicine/sale/deleteOrder?sale_cust_ticketNumber="+ ticketNumber);
			if (data.result == "success") {
				window.location.reload();
				util.show(data.message);
			}
			else {
				util.error(data.message);
			}
			});
		}
	else{
		util.show("当前未有正在进行的订单");
	}
	
	
}
//提交顾客信息
function submitCustForm(){
	$.ajax({
		type : $("#form_cust").attr("method"), // 表单提交类型
		url :  $("#form_cust").attr("action"), // 表单提交目标
		data : $("#form_cust").serialize(), // 表单数据
		success : function(data) {
			if (data) {
				if (data.result == "success") {
					util.show(data.message);
					window.location.reload();

				} else {
					util.error(data.message);
				}
			}
		}
	});
}	



