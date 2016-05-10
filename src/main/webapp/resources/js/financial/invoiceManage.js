var supCheckList = util.get(global_param.context_name + "/system/combobox/lists?pid=638");
var supLocalList = util.get(global_param.context_name + "/system/combobox/lists?pid=642");
//记录选中的行，以便行切换的时候关闭当前行编辑
var currentEdit = 0;
//var ctrlsID = ["begin_time","end_time","rkfp_ticket",
//               "rkfp_code","stock_intakeTicket","sup_code",
//               "sup_ctactperson","rkfp_supplier","rkfp_accepter",
//               "rkfp_abstract","rkfp_category","sup_id"];
//
////确认BUTTON
//var sumbitButtonID = "submitWarehousingInvoice";
//
//$(function() {
//	var keyPress = new pageKeyPress();
//	keyPress.bindKeyPressToCtrl(ctrlsID, sumbitButtonID);
//
//});
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
	$('#rkfp_category').combobox({
		method :"get",
		url : global_param.context_name + "/system/combobox/lists?pid=999",
		valueField : "cbs_code",
		textField : "cbs_chn",
	});
	
	$('#update_rkfp_category').combobox({
		method :"get",
		url : global_param.context_name + "/system/combobox/lists?pid=999",
		valueField : "cbs_code",
		textField : "cbs_chn",
	});
	//包装量
	var packingunitTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=125");

	// 初始化表格
	$("#sumDg").datagrid({
		url : global_param.context_name + "/financial/invoiceManage/sumInvoiceList", // 数据来源
		method : 'get',
		title : '发票信息', // 标题
		sortName : 'rkfp_id', // 排序的列
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
			field : 'rkfp_id',
			title : '总发票id',
			width : 50,
			hidden : true
		},{ 
			field : 'rkfp_code',
			title : '发票号',
			width : 100,
			sortable : true
		},{
			field : 'rkfp_ticket',
			title : '发票票号',
			width : 100,
			sortable : true
		},{
			field : 'rkfp_sumMoney',
			title : '已开发票金额',
			width : 60,
			sortable : true
		},{
			field : 'rkfp_sumNum',
			title : '已开发票数量',
			width : 60,
			sortable : true
		},{
			field : 'sup_id',
			title : '供方序号',
			width : 60,
			hidden : true
		},{
			field : 'sup_name',
			title : '供方全称',
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
			field : 'rkfp_accepter',
			title : '单位名头',
			width : 60,
			sortable : true
		},{
			field : 'rkfp_supplier',
			title : '开票单位',
			width : 60,
			sortable : true
		},{
			field : 'rkfp_taxrate',
			title : '发票税率',
			width : 60,
			sortable : true,
		},{
			field : 'rkfp_invoiceDate',
			title : '发票日期',
			width : 60,
			sortable : true,
			formatter: function(value,row,index){
				return util.formatDate(value);
			}
		},{
			field : 'rkfp_abstract',//分店计划申请数量
			title : '种类',
			width : 60,
			sortable : true
		},{
			field : 'rkfp_discount',//分店计划申请数量
			title : '折让',
			width : 60,
			sortable : true
		},{
			field : 'rkfp_creater',
			title : '创建人',
			width : 60,
			sortable : true
		},{
			field : 'rkfp_createTime',
			title : '创建时间',
			width : 60,
			sortable : true,
			formatter: function(value,row,index){
				return util.formatDate(value);
			}
		},{
			field : 'rkfp_last_modify_user',
			title : '最后修改人',
			width : 60,
			sortable : true,
		},{
			field : 'rkfp_last_modify_time',
			title : '最后修改时间',
			width : 60,
			sortable : true,
			formatter: function(value,row,index){
				return util.formatDate(value);
			}
		}]],
		toolbar : [ {
			text : '修改',
			iconCls : 'icon-modify',
			handler : updateSumInvoice
		}],
		onClickRow:function(index,data){
			$('#detailDg').datagrid({
				url : "/HRERP/financial/invoiceManage/invoiceList",
				method :"get",
				queryParams : {
					rkfp_ticket:data.rkfp_ticket
				}
			});
		}
	});
	// 初始化编辑窗口
	util.window('editSumInvoiceWindow', {
		title : '发票总信息'
	});
	
	$("#detailDg").datagrid({
		//url : global_param.context_name + "/financial/invoiceManage/invoiceList", // 数据来源
		method : 'get',
		title : '详细信息', // 标题
		sortName : 'rkfpb_id', // 排序的列
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
		columns:[[
		{
			field : 'rkfp_ticket',
			title : '发票票号',
			width : 100,
			sortable : true
		},{
			field : 'stock_intakeTicket',
			title : '入库票号',
			width : 100,
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
			field : 'sup_id',
			title : '供方序号',
			width : 60,
			hidden : true
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
			sortable : true
		},{
			field : 'product_unit',
			title : '单位',
			width : 60,
			sortable : true
		}]],
		toolbar :"#tb",
	});
});

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
	
}

function updateSumInvoice() {
	var node = $('#sumDg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录！");
		return;
	}
	util.openWindow('editSumInvoiceWindow', global_param.context_name + "/financial/invoiceManage/updateSumInvoice?rkfp_id=" + node.rkfp_id);
}
//修改计划，多条修改
function modifyFunc() {
	var node = $('#detailDg').datagrid('getSelectioned');
	
}
//删除计划
function deleteFunc() {
	var node = $('#TestDg').datagrid('getSelected');
	if (!node) {
		alert("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		
	});
}