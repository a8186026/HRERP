
$(function(){
	document.getElementById("OrderDg").style.display="none";
	document.getElementById("StockDg").style.display="";
	document.getElementById("RetailDg").style.display="none";
	document.getElementById("SaleDg").style.display="none";
	$('#bill_type').combobox({
			url : global_param.context_name + "/system/combobox/lists?pid=1018",
			method :"get",
			valueField : "cbs_code",
			textField : "cbs_chn",
			editable:false,
			onChange:function(newValue, oldValue){
			
				$('#print_type').combobox('reload',global_param.context_name+'/system/combobox/lists?pid='+newValue);
				if(newValue==1023){
					$("#tab-dg").tabs('select',0);//第二个参数是对应tab索引值
					$('#OrderDg').datagrid({url:global_param.context_name + '/purchase/order/lists'});
					$("#OrderDg").datagrid('reload');
				}
				else if(newValue==1003){
					$("#tab-dg").tabs('select',1);
					$('#StockDg').datagrid({url:global_param.context_name + '/purchase/order/lists'});
					$("#StockDg").datagrid('reload');
				}
				else if(newValue==1026){
					$("#tab-dg").tabs('select',2);
					$('#RetailDg').datagrid({url:global_param.context_name + '/purchase/order/lists'});
					$("#RetailDg").datagrid('reload');
				}
				else{
					$("#tab-dg").tabs('select',3);
					$('#SaleDg').datagrid({url:global_param.context_name + '/purchase/order/lists'});
					$("#SaleDg").datagrid('reload');
				}
					
			}
		});
	
	$('#print_type').combobox({
		url : "",
		method :"get",
		valueField : "cbs_code",
		textField : "cbs_chn",
		editable:false,
		onChange:function(newValue, oldValue){
			
		}
	});
	
	// 初始化表格
	$("#OrderDg").datagrid({
		method : 'get',
		//url : global_param.context_name + "/purchase/order/lists", // 数据来源
		title : '订单查询打印', // 标题
		sortName : 'id', // 排序的列
		sortOrder : 'asc', // 排序方式
		//fitcolumns : true,
		singleSelect : true,//单选
		//fit:true,
		pageSize : 20,
		pageList : [20, 50, 100],
		rownumbers : true,
		nowrap : true,
		striped : true,
		queryParams : {},
		columns:[[ {
			field : 'ticket_id',
			title : '票号',
			width : 100,
			sortable : true
		}, {
			field : 'supply_code',
			title : '供方编码',
			width : 100,
			sortable : true
		}, {
			field : 'supply_fullname',
			title : '供方全称',
			width : 100,
			sortable : true,
		}, {
			field : 'orderabstract',
			title : '摘要',
			width : 100,
			sortable : true
		}, {
			field : 'knotStyle',
			title : '结款方式',
			width : 100,
			sortable : true
		}, {
			field : 'carryMode',
			title : '承运方式',
			width : 100,
			sortable : true
		}, {
			field : 'carryCompany',
			title : '承运单位',
			width : 150,
			sortable : true
		}, {
			field : 'orderTransMode',
			title : '运输方式',
			width : 100,
			sortable : true
		}, {
			field : 'delivaryPlace',
			title : '发运地点',
			width : 100,
			sortable : true
		}, {
			field : 'orderDate',
			title : '订货时间',
			width : 150,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value);
			}
		}, {
			field : 'orderDepartureTime',
			title : '启运时间',
			width : 150,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value);
			}
		}, {
			field : 'arrivalDate',
			title : '到货时间',
			width : 150,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value);
			}
		}, {
			field : 'checkStatus',
			title : '审批状态',
			width : 100,
			sortable : true,
			formatter: function(value, row, index){
				if(value == 0){
					return "<font color='green'>已审核</font>";
				}else if(value == 1){
					return "<font color='red'>未审核</font>";
				}
			}
		
		}]],
		toolbar :"",
	});

	
	
	$("#StockDg").datagrid({
		method : 'get',
		//url : global_param.context_name + "/financial/warehousingInvoice/confirm", // 数据来源
		title : '入库结算单打印', // 标题
		sortName : 'accept_check_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitColumns : true,
		singleSelect : true, // 单选
		pageSize : 20,
		pageList : [20, 50, 100],
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
		toolbar :"",
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

function perviewPrint(){
	 var grfName = $("#print_type").combo('getValue');
     var name = $("#bill_type").combo('getText');
		if(grfName==""){
			util.show("请选择打印格式");
		}else{
			var node = {
					text : name+"报表",
					attributes : {url : "basInfo/report/report?fileName="+grfName}
				};
			self.parent.addTab(node);
}
}
function billPrint(){
	 var grfName = $("#print_type").combo('getValue');
     var name = $("#bill_type").combo('getText');
		if(name==""||grfName==""){
			util.show("请选择打印格式");
		}else{
			var grfUrl= $("#realPath").val()+("resources/grf/")+$("#loginUserID").val()+"/"+grfName+".grf";
			Report.LoadFromURL(grfUrl);
            alert("创建隐藏报表成功");
            //Report.Print(true);
            Report.PrintPreview(true);
		}
}

function billRightPrint(){
	 var grfName = $("#print_type").combo('getValue');
     var name = $("#bill_type").combo('getText');
		if(name==""||grfName==""){
			util.show("请选择打印格式");
		}else{
			var grfUrl= $("#realPath").val()+("resources/grf/")+$("#loginUserID").val()+"/"+grfName+".grf";
			Report.LoadFromURL(grfUrl);
            alert("创建隐藏报表成功");
            Report.Print(true);
           // Report.PrintPreview(true);
		}
}
