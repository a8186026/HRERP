var supCheckList = util.get(global_param.context_name + "/system/combobox/lists?pid=638");
var supLocalList = util.get(global_param.context_name + "/system/combobox/lists?pid=642");
//记录选中的行，以便行切换的时候关闭当前行编辑
var currentEdit = 0;
var ctrlsID = ["begin_time","end_time","stock_intakeTicket",
               "sup_code","sup_ctactperson","rkfp_ticket",
               "rkfp_taxrate","rkfp_code","rkfp_sumMoney",
               "rkfp_discount","rkfp_supplier","rkfp_accepter",
               "rkfp_abstract","rkfp_category","rkfp_remark","sup_id"];

//确认BUTTON
var sumbitButtonID = "submitWarehousingInvoice";

$(function() {
	var keyPress = new pageKeyPress();
	keyPress.bindKeyPressToCtrl(ctrlsID, sumbitButtonID);

});
//获得子窗口选择的供方信息,并执行相关操作
function setSupContent(node){
	//获得选择的供方信息
	$("#sup_code").attr("value",node.sup_code);
	$("#sup_ctactperson").attr("value",node.sup_ctactperson);
	$("#sup_id").attr("value",node.sup_id);
	var data = util.formParams("searchForm");
	var code = data.sup_code;
	if(code != null){//当sup_code不为空时，查询并更新第一栏产品信息表
		$("#supplInfoDg").datagrid("options").queryParams.sup_code = code;
		$("#supplInfoDg").datagrid('reload');
	}
};
//给product_code绑定回车键，弹出产品信息窗口，获取product_code
$(function() {
	$('#rkfp_ticket').attr("value", function() {
		var code = util.get(global_param.context_name + "/financial/warehousingInvoice/getMaxInvoiceTicket");
		return code;
	});
	
	//如果当前焦点在客户编码上，失去焦点触发该动作，输入非空字符进行查询，进入选择供方界面
	$('#sup_code').bind('keydown', function() {//输入后，打开供方客户页面
		 var e = event || window.event;
		    var keyCode = e.keyCode || e.which;
		    var actElement = document.activeElement;
		    //如果按键是enter并且当前焦点是产品编号，则弹出产品选择框
		    if(keyCode == 13&&actElement.id == "sup_code"){
				//输入后，打开供方客户页面
				var supContent = $('#sup_code').attr("value");
				if(supContent!=null && supContent!=""){
					$("#supListWindow").window({
						title : '供方选择',
						width : 1250,
						height : 650,
						shadow : true,
						modal : true,
						closed : true,
						minimizable : false,	
						maximizable : false,
						collapsible : false,
						resizable : false,
						content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/purchase/order/viewSupList?callback=setSupContent&supContent=' + supContent.replace("%", "%25") +'"></iframe>',
					});
					$('#supListWindow').window('open');
				}
		    }
	});
	//税率
	$('#rkfp_taxrate').combobox({
		method :"get",
		url : global_param.context_name + "/system/combobox/lists?pid=140",
		valueField : "cbs_code",
		textField : "cbs_chn",
	});
	
	//发票摘要
	$('#rkfp_abstract').combobox({
		method :"get",
		url : global_param.context_name + "/system/combobox/lists?pid=999",
		valueField : "cbs_code",
		textField : "cbs_chn",
		
	});
	//包装量
	var packingunitTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=125");
	// 初始化供方信息表格，在页面第一栏
	$("#supplInfoDg").datagrid({
			method : 'get',
			url : global_param.context_name + "/supply/supInfoManage/lists", // 数据来源
			sortName : 'sup_id', // 排序的列
			sortOrder : 'asc', // 排序方式
			fitColumns : true,
			singleSelect : true,//单选
			columns:[[{
				field : 'sup_code',
				title : '供方编码',
				width : 80,
				sortable : true
			}, {
				field : 'sup_name',
				title : '客户全称',
				width : 100,
				sortable : true
			}, {
				field : 'sup_shortname',
				title : '简称',
				width : 80,
				sortable : true
			}, {
				field : 'sup_chnpy',
				title : '拼音码',
				width : 80,
				sortable : true
			}, {
				field : 'sup_address',
				title : '地址',
				width : 100,
				sortable : true
			}, {
				field : 'sup_ctactperson',
				title : '联系人',
				width : 80,
				sortable : true
			}, {
				field : 'sup_tel',
				title : '电话',
				width : 100,
				sortable : true
			}, {
				field : 'sup_mobile',
				title : '手机 ',
				width : 100,
				sortable : true
			}, {
				field : 'sup_fax',
				title : '传真',
				width : 100,
				sortable : true
			}, {
				field : 'sup_email',
				title : '电子邮箱',
				width : 100,
				sortable : true
			}, {
				field : 'sup_buyname',
				title : '采购员',
				width : 80,
				sortable : true
			}, {
				field : 'sup_drugid',
				title : '地区药监ID',
				sortable : true,
				//hidden : true,
				formatter : function(value, row, index){
					if(null == value){
						return supLocalList[0].cbs_chn;
						value = 0;
					}else{
						return supLocalList[1].cbs_chn;
						value = 1;
					}
				}
			}]],
		});
	
	// 初始化产品信息和库存信息关联表格，在页面第二栏
	$("#RefundInfoDg").datagrid({
		method : 'get',
		url : global_param.context_name + "/financial/warehousingInvoice/RefundInfoList", // 数据来源
		title : '返货信息', // 标题
		sortName : 'refund_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitColumns : true,
		rownumbers: true,
		singleSelect: false,
		selectOnCheck: true,
		checkOnSelect: true,
		pageSize : 10,
		pageList : [10, 20, 50, 100],
		fit : true,
		//striped : true, // 奇偶行颜色不同
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		nowrap : true,
		striped : true, // 奇偶行颜色不同
		queryParams : {},
		columns:[[{ 
			field:'ck',
			checkbox:true 
		},{
			field : 'accept_check_id',
			title : '收货验收id',
			width : 50,
			sortable : true
		},{
			field : 'stock_intakeTicket',
			title : '入库票号',
			width : 60,
			sortable : true
		},{
			field : 'rkfp_ticket',
			title : '发票票号',
			width : 60,
			sortable : true
		},{
			field : 'rkfpb_invoicedMoney',
			title : '已开发票金额',
			width : 50,
			sortable : true
		},{
			field : 'rkfpb_invoicedNum',
			title : '已开发票数量',
			width : 50,
			sortable : true
		},{
			field : 'accept_sum',
			title : '未开发票金额',
			width : 60,
			sortable : true,
			formatter: function(value, row, index){
				if(row.rkfpb_invoicedMoney != null){
					return value - row.rkfpb_invoicedMoney;
				}
				return value;
			}
		},{
			field : 'accept_checkQualitedNumber',
			title : '未开发票数量',
			width : 60,
			sortable : true,
			formatter: function(value, row, index){
				if(value - row.rkfpb_invoicedNum != null){
					return value - row.rkfpb_invoicedNum;
				}
				return value;
			}
		},{
			field : 'department_id',//分店计划申请数量
			title : '库房',
			width : 60,
			sortable : true
		},{
			field : 'stock_intakeSmallNumber',//分店计划申请数量
			title : '入库小号',
			width : 60,
			sortable : true
		},{
			field : 'stock_intakeCheckTime',
			title : '入库时间',
			width : 60,
			sortable : true,
			formatter: function(value,row,index){
				return util.formatDate(value);
			}
		},{
			field : 'sup_id',
			title : '供方序号',
			width : 60,
			sortable : true
		},{
			field : 'sup_name',
			title : '客户全称',
			width : 80,
			sortable : true
		},{
			field : 'sup_ctactperson',//分店计划申请数量
			title : '联系人',
			width : 60,
			sortable : true
		},{
			field : 'sup_tel',//分店计划申请数量
			title : '电话',
			width : 60,
			sortable : true
		},{
			field : 'rkfp_registerTime',
			title : '登记时间',
			width : 60,
			sortable : true,
			formatter: function(value,row,index){
				return util.formatDate(value);
			}
		},{
			field : 'refund_reason',
			title : '返货原因',
			width : 60,
			sortable : true
		},{
			field : 'refund_chiefPerson',
			title : '负责人',
			width : 60,
			sortable : true
		},{
			field : 'product_name',
			title : '产品名称',
			width : 60,
			sortable : true
		},{
			field : 'product_specification',
			title : '规格',
			width : 60,
			sortable : true
		}, {
			field : 'product_unit',
			title : '单位',
			width : 60,
			sortable : true
		},{
			field : 'stock_batchCode',
			title : '批号',
			width : 60,
			sortable : true
		},{
			field : 'refund_number',
			title : '退货数量',
			width : 60,
			sortable : true
		},{
			field : 'refund_money',
			title : '退货金额',
			width : 60,
			sortable : true
		}]],
	});
	
	// 初始化分店定价表格，在页面第三栏
	$("#StockCheckedDg").datagrid({
		method : 'get',
		url : global_param.context_name + "/financial/warehousingInvoice/stockCheckedlists", // 数据来源
		title : '入库信息', // 标题
		sortName : 'stock_info_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitColumns : true,
		singleSelect: false,
		selectOnCheck: true,
		checkOnSelect: true,
		pageSize : 10,
		pageList : [10, 20, 50, 100],
		fit : true,
		//striped : true, // 奇偶行颜色不同
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		nowrap : true,
		striped : true, // 奇偶行颜色不同
		queryParams : {},
		columns:[[{ 
			field:'ck',
			checkbox:true 
		},{
			field : 'accept_check_id',
			title : '收货验收id',
			width : 50,
			sortable : true
		},{
			field : 'stock_intakeTicket',
			title : '入库票号',
			width : 80,
			sortable : true
		},{
			field : 'accept_sum',
			title : '未开发票金额',
			width : 60,
			sortable : true
		},{
			field : 'accept_checkQualitedNumber',
			title : '未开发票数量',
			width : 60,
			sortable : true
		},{
			field : 'department_id',//分店计划申请数量
			title : '库房',
			width : 60,
			sortable : true
		},{
			field : 'stock_intakeSmallNumber',//分店计划申请数量
			title : '入库小号',
			width : 60,
			sortable : true
		},{
			field : 'stock_intakeCheckTime',
			title : '入库时间',
			width : 60,
			sortable : true,
			formatter: function(value,row,index){
				return util.formatDate(value);
			}
		},{
			field : 'sup_id',
			title : '供方序号',
			width : 60,
			sortable : true
		},{
			field : 'sup_name',
			title : '客户全称',
			width : 80,
			sortable : true
		},{
			field : 'sup_ctactperson',//分店计划申请数量
			title : '联系人',
			width : 60,
			sortable : true
		},{
			field : 'sup_tel',//分店计划申请数量
			title : '电话',
			width : 60,
			sortable : true
		},{
			field : 'product_id',
			title : '产品序号',
			width : 60,
			sortable : true
		},{
			field : 'product_name',
			title : '产品名称',
			width : 60,
			sortable : true
		},{
			field : 'product_specification',
			title : '规格',
			width : 60,
			sortable : true,
		},{
			field : 'product_unit',
			title : '单位',
			width : 60,
			sortable : true,
		}]],
	});
	
	// 初始化表格
	$("#TestDg").datagrid({
		method : 'get',
		//url : global_param.context_name + "/financial/warehousingInvoice/confirm", // 数据来源
		title : '合并信息', // 标题
		sortName : 'accept_check_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitColumns : true,
		singleSelect : true, // 单选
		pageSize : 10,
		pageList : [10, 20, 50, 100],
		fit : true,
		//striped : true, // 奇偶行颜色不同
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		nowrap : true,
		striped : true, // 奇偶行颜色不同
		queryParams : {},
		columns:[[{ 
			field : 'accept_check_id',
			title : '收货验收id',
			width : 50,
			sortable : true
		},{ 
			field : 'refund_id',
			title : '退货id',
			width : 50,
			sortable : true
		},{
			field : 'stock_intakeTicket',
			title : '入库票号',
			width : 60,
			sortable : true
		},{
			field : 'rkfp_ticket',
			title : '发票票号',
			width : 60,
			sortable : true
		},{
			field : 'rkfpb_invoicedMoney',
			title : '已开发票金额',
			width : 50,
			sortable : true
		},{
			field : 'rkfpb_invoicedNum',
			title : '已开发票数量',
			width : 50,
			sortable : true
		},{
			field : 'accept_sum',
			title : '未开发票金额',
			width : 60,
			sortable : true,
			formatter: function(value, row, index){
				if(row.rkfpb_invoicedMoney != null){
					return value - row.rkfpb_invoicedMoney;
				}
				return value;
			}
		},{
			field : 'accept_checkQualitedNumber',
			title : '未开发票数量',
			width : 60,
			sortable : true,
			formatter: function(value, row, index){
				if(value - row.rkfpb_invoicedNum != null){
					return value - row.rkfpb_invoicedNum;
				}
				return value;
			}
		},{
			field : 'sup_id',
			title : '供方序号',
			width : 60,
			sortable : true
		},{
			field : 'sup_name',
			title : '客户全称',
			width : 80,
			sortable : true
		},{
			field : 'sup_ctactperson',//分店计划申请数量
			title : '联系人',
			width : 60,
			sortable : true
		},{
			field : 'sup_tel',//分店计划申请数量
			title : '电话',
			width : 60,
			sortable : true
		},{
			field : 'product_id',
			title : '产品序号',
			width : 60,
			sortable : true
		},{
			field : 'product_name',
			title : '产品名称',
			width : 60,
			sortable : true
		},{
			field : 'product_specification',
			title : '规格',
			width : 60,
			sortable : true,
		},{
			field : 'product_unit',
			title : '单位',
			width : 60,
			sortable : true,
		}]],
		toolbar :"#tb",
	});
});
// 验证文本框只能输入数字和小数点
function checkNum(e) { 
    if (/^(\d)*$/i.test(e)) { 
        return true;
    }
    return false;
} 

function query() {
	
	$('#RefundInfoDg').datagrid('options').queryParams = util.formParams("searchForm");
	$("#StockCheckedDg").datagrid("options").queryParams = util.formParams("searchForm");
	$('#RefundInfoDg').datagrid('reload');
	$('#StockCheckedDg').datagrid('reload');
}
//清空方法
function clearsearchForm(){
	$('#searchForm').form('clear');
	$('#rkfp_ticket').attr("value", function() {
		var code = util.get(global_param.context_name + "/financial/warehousingInvoice/getMaxInvoiceTicket");
		return code;
	});
}

function saveFunc() {
	$("#TestDg").datagrid("selectAll");
	var nodes = $('#TestDg').datagrid('getSelections');
	
	if (nodes.length == 0) {
		alert("无生成发票的信息");
		return;
	}
	util.confirm("确认生成发票吗？", function() {
	
		var searchInfo = util.formParams("searchForm");
		var info="";
		if(searchInfo.sup_id == null || searchInfo.sup_id == ""){
			alert("请选择供方!");
			return;
		}
		info += searchInfo.sup_id + "_" ;
		if(searchInfo.rkfp_taxrate == null){
			alert("请输入发票税率!");
			return;
		}
		info += $('#rkfp_taxrate').combobox('getText') + "_" ;
		if(searchInfo.rkfp_code == null){
			alert("请输入发票号!");
			return;
		}
		info += searchInfo.rkfp_code + "_" ;
		if(searchInfo.rkfp_sumMoney == null){
			alert("请输入发票总金额!");
			return;
		}
		info += searchInfo.rkfp_sumMoney + "_" ;
		if(searchInfo.rkfp_discount == null){
			alert("请输入折让总金额!");
			return;
		}
		info += searchInfo.rkfp_discount + "_" ;
		if(searchInfo.rkfp_supplier == null){
			alert("请输入开票单位!");
			return;
		}
		info += searchInfo.rkfp_supplier + "_" ;
		if(searchInfo.rkfp_accepter == null){
			alert("请输入发票名头!");
			return;
		}
		info += searchInfo.rkfp_accepter + "_" ;
		if(searchInfo.rkfp_abstract == null){
			alert("请输入发票摘要!");
			return;
		}
		info += searchInfo.rkfp_abstract + "_" ;
		if(searchInfo.rkfp_category == null){
			alert("请输入发票种类!");
			return;
		}
		info += $('#rkfp_category').combobox('getText') + "_" ;
		if(searchInfo.rkfp_remark == null){
			alert("请输入备注!");
			return;
		}
		info += searchInfo.rkfp_remark + "_" ;
		
		var invalid="";//记录发票作废的返货id	
		var inrefund="";//记录收货id
		var instock="";//记录库存id
		for(var i = 0 ; i < nodes.length ; i++){
			if(nodes[i].rkfp_ticket == null){
				if(nodes[i].refund_id != null ){
					inrefund += nodes[i].refund_id+"_";
					alert("inrefund=" + inrefund);
				}else{
					instock += nodes[i].accept_check_id+"_";
					instock += nodes[i].stock_info_id;
					alert("instock=" + instock);
					if(i<nodes.length-1)
						instock +="|";
				}
			}else{
				invalid += nodes[i].refund_id+"_";
				alert("invalid=" + invalid);
			}
		}
//		info += $("#sumRefundMoney").attr('value')+"_"+$("#sumStockMoney").attr('value')+"_"+$("#sumInvoiceMoney").attr('value')+"_";
		alert("info=" + info);
		var re = util.get(global_param.context_name + "/financial/warehousingInvoice/saveInvoice?invalid=" + invalid 
													  + "&inrefund=" + inrefund  + "&instock=" +  instock + "&info=" + info );
		if (re.result == "success") {
			$('#RefundInfoDg').datagrid('reload');
			$('#StockCheckedDg').datagrid('reload');
			$('#TestDg').datagrid('loadData',{total:0,rows:[]});  
			clearsearchForm();
		}
		util.show(re.message);
	});
	
}
//修改计划，多条修改
function confirmFunc() {
	var nodesRefund = $('#RefundInfoDg').datagrid('getSelections');
	var nodesStock = $('#StockCheckedDg').datagrid('getSelections');
	var refundMoney = 0;
	var stockMoney = 0;
	var data="";
	for(var i = 0 ; i < nodesRefund.length ; i++){
		data += nodesRefund[i].accept_check_id+"_";
		if(nodesRefund[i].rkfp_ticket != null){
			refundMoney = Number(refundMoney) + Number(nodesRefund[i].rkfpb_invoicedMoney);
		}
	}
	for(var i = 0 ; i < nodesStock.length ; i++){
		data += nodesStock[i].accept_check_id+"_";
		if(nodesStock[i].accept_sum != null){
			stockMoney = Number(stockMoney) + Number(nodesStock[i].accept_sum);
		}
	}
	if(data!=""){
		$('#TestDg').datagrid({
			url : "/HRERP/financial/warehousingInvoice/confirm?data=" + data,
			method :"get",
			onLoadSuccess:function(){
				$("#sumRefundMoney").attr("value",refundMoney);
				$("#sumStockMoney").attr("value",stockMoney);
				$("#sumInvoiceMoney").attr("value",Number(stockMoney) - Number(refundMoney));
				$('#rkfp_sumMoney').attr("value", function() {
					return Number(stockMoney) - Number(refundMoney);
				});
				util.show("操作成功");
			}
		});
	}
}
//删除计划
function deleteFunc() {
	var node = $('#TestDg').datagrid('getSelected');
	if (!node) {
		alert("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		$("#TestDg").datagrid("selectAll");
		var nodes = $('#TestDg').datagrid('getSelections');
		var data="";
		var refundMoney = 0;
		var stockMoney = 0;
		for(var i = 0 ; i < nodes.length ; i++){
			if(nodes[i].accept_check_id != node.accept_check_id){
				data += nodes[i].accept_check_id+"_";
				alert(data);
				if(nodes[i].refund_id != null ){
					if(nodes[i].rkfp_ticket != null){
						refundMoney = Number(refundMoney) + Number(nodes[i].rkfpb_invoicedMoney);
					}
				}else{
					if(nodes[i].accept_sum != null){
						stockMoney = Number(stockMoney) + Number(nodes[i].accept_sum);
					}
				}
			}
		}
		if(data!=""){
				$('#TestDg').datagrid({
					url : "/HRERP/financial/warehousingInvoice/confirm?data=" + data,
					method :"get",
					onLoadSuccess:function(){
						$("#sumRefundMoney").attr("value",refundMoney);
						$("#sumStockMoney").attr("value",stockMoney);
						$("#sumInvoiceMoney").attr("value",Number(stockMoney) - Number(refundMoney));
						util.show("操作成功");
					}
				});
		}
	});
}