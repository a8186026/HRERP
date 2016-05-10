/**
 * 返货审核   YuechenWANG
 */

var checkedlist;

$(function(){
	$("#refund_order_dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/refund/check/checkSupplyList", // 数据来源
		title : '存在待审核返货记录的供方信息', // 标题
		sortName : 'sup_id', // 排序的列
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
		queryParams : {},
		columns:[[{
			field : 'sup_code',
			title : '供方编码',
			width : 80,
			sortable : true
		}, {
			field : 'sup_name',
			title : '客户全称',
			width : 120,
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
			width : 150,
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
			width : 120,
			sortable : true
		}, {
			field : 'sup_buyname',
			title : '采购员',
			width : 80,
			sortable : true
		},]],
		
		toolbar : [{
			id: 'updateRefundOrderList',
			text : '刷新',
			iconCls : 'icon-reload',
			handler : queryForSupInfoDatagrid
		}],
		
		onClickRow:function(index,data){
			checkedlist = data;
			$('#refund_orderList_dg').datagrid('options').url = global_param.context_name + "/refund/check/checkRefundDetailList";// 数据来源
			$('#refund_orderList_dg').datagrid('options').queryParams.sup_id = data.sup_id;
			$('#refund_orderList_dg').datagrid('reload');
	
		}
	});
	
	$("#refund_orderList_dg").datagrid({
		method : 'get',
		//url : global_param.context_name + "/purchase/orderList/lists", // 数据来源
 		title : '需审核的返货订单详细信息管理', // 标题
		sortName : 'refund_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitcolumns : true,
		singleSelect : false,//单选
		
		fit:true,
		pageSize : 10,
		pageList : [10,20,50,100],
		pagination : true,
		rownumbers : true,
		nowrap : true,
		striped : true,
		queryParams : {
			sup_id:'',
			sup_code:'',
		},
		onClickRow:function(index,data){	
/*			alert(data.refund_ticketId);*/
			$("#refund_ticket_id").attr("value",data.refund_ticketId);
		},
		columns:[[ 
		          {
			field : 'refund_ticketId',
			title : '票号',
			width : 150,
			sortable : true
		} ,{
			field : 'product_id',
			title : '产品序号',
			width : 80,
			sortable : true
		} ,{
			field : 'sup_id',
			title : '供方序号',
			width : 80,
			sortable : true
		}, {
			field : 'sup_code',
			title : '供方编码',
			width : 80,
			sortable : true
		},{
			field : 'sup_name',
			title : '客户全称',
			width : 80,
			sortable : true
		},{
			field : 'refund_operator',
			title : '操作员',
			width : 80,
			sortable : true
		},{ 
			field : 'product_code',
			title : '产品编号',
			width : 80,
			sortable : true
		},{
			field : 'product_name',
			title : '产品名称',
			width : 80,
			sortable : true,
		},{
			field : 'product_specification',
			title : '规格',
			width : 80,
			sortable : true
		},{
			field : 'product_productarea',
			title : '产地',
			width : 120,
			sortable : true
		},{
			field : 'product_unit',
			title : '单位',
			width : 50,
			sortable : true
		},{
			field : 'refund_number',
			title : '退货数量',
			width : 80,
			sortable : true
		},{
			field : 'refund_unitPrice',
			title : '单价',
			width : 80,
			sortable : true
		},{
			field : 'refund_money',
			title : '返货金额',
			width : 80,
			sortable : true
		}, {
			field : 'stock_storage',
			title : '库房',
			width : 50,
			sortable : true
		},
		{
			field : 'refund_registerDate',
			title : '登记时间',
			width : 150,
			sortable : true,
			/*设置日期格式*/
			formatter : function (value, row, index) {
				return util.formatDate(value);
			}
		}, {
			field : 'refund_register',
			title : '登记状态',
			width : 100,
			sortable : true,
			/*设置返货登记状态格式*/
			formatter: function(value, row, index){
				if(value == 0){
					return "<font color='green'>已完成</font>";
				}else if(value == 1){
					return "<font color='red'>未完成</font>";
				}else {
					return " ";
				}
			}
		}, {
			field : 'refund_operator',
			title : '登记操作员',
			width : 120,
			sortable : true
		}, ]],
		toolbar : [{
			id: 'updateRefundOrderList',
			text : '审核通过',
			iconCls : 'icon-add',
			handler : updateRefundOrderList
		},{
			text : '全选',
			iconCls : 'icon-modify',
			handler : checkAllFunc
		},{
			text : '清除',
			iconCls : 'icon-delete',
			handler : uncheckAllFunc
		}]
		
		
	});

		
	//如果当前焦点在客户编码上，失去焦点触发该动作，输入非空字符进行查询，进入选择供方界面
	$('#supply_code').bind('blur', function() {
		//输入后，打开供方客户页面
		var supContent = $('#supply_code').attr("value");
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
				content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/refund/check/viewSupList?callback=setSupContent&supContent=' + supContent.replace("%", "%25") +'"></iframe>',
			});
			$('#supListWindow').window('open');
		}
	});
	
	
	//这段我还不知道是做什么用的 ??????????????
	$("#updateRefundOrderList").focus();
	 document.onkeydown=function(event){ 
        var e = event || window.event || arguments.callee.caller.arguments[0]; 
        if(e && e.keyCode==27){ // 按 Esc  
            //要做的事情 
            //alert("按 esc"); 
          } 
         if(e && e.keyCode==13){ // enter 键 
             //要做的事情 
       	 $("#updateRefundOrderList").focus();
        } 
    };  
});

/*
 * 子菜单回调函数组
 * */
//获得子窗口选择的供方信息,并执行相关操作
function setSupContent(node){
	//获得选择的供方信息
	//表单信息插入
	$('#supply_code').attr("value",node.sup_code);
	$('#sup_ctactperson').attr("value",node.sup_ctactperson);
	
	$('#refund_orderList_dg').datagrid('options').url = global_param.context_name + "/refund/check/searchRefunItemBySupcode";// 数据来源
	$('#refund_orderList_dg').datagrid('options').queryParams.sup_id = node.sup_id;
	$('#refund_orderList_dg').datagrid('reload');
/*	query();*/
	
};

/*全选与清除所有选择内容 easyui自带的东西*/
function checkAllFunc(){
	 $("#refund_orderList_dg").datagrid("checkAll");
}
function uncheckAllFunc(){
	$("#refund_orderList_dg").datagrid("uncheckAll");
}

function clearForm(){
	 $("#searchForm input[type='text']").val("");
	 $("#searchForm select").find("option[value=0]").attr("selected","selected");
}

function query() {
/*	$('#refund_orderList_dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#refund_orderList_dg').datagrid('reload');
	$('#refund_orderList_dg').datagrid('options').url = global_param.context_name + "/refund/check/searchRefunItemBySupcode";// 数据来源
	$('#refund_orderList_dg').datagrid('options').queryParams.sup_id = node.sup_id;*/
	
	$('#refund_orderList_dg').datagrid('reload');
}

function queryForSupInfoDatagrid() {
	$('#refund_order_dg').datagrid('reload');
	$('#refund_orderList_dg').datagrid('loadData', { total: 0, rows: [] });
}

function checkPurOrder(){
	var node = $('#refund_order_dg').datagrid('getSelected');
	
	if(!node){
		util.show("请选择一条记录");
	}
	else if(node.checkStatus == 0){
		util.show("此记录已审核");
	}
	else{
		util.confirm("确认审核通过此订单？", function() {
			var data = util.get(global_param.context_name + "/purchase/check/pass?id=" + node.id);
			if (data.result == "success") {
				$('#refund_order_dg').datagrid('reload');
				$('#refund_orderList_dg').datagrid('loadData',{total:0,rows:[]});
			}
			util.show(data.message);
		});
	}
}
function updatePurOrder(){
	var node = $('#refund_order_dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条大单记录");
		return;
	}
	if(node.checkStatus == 0){
		util.show("订单已通过审核，无法对其进行操作!");
		return;
	}
	util.openWindow('editPurOrderWindow', "/HRERP/purchase/order/check/" + node.id);
}



/*对所有选中的订单进行审核通过处理*/
function updateRefundOrderList(){
	var node = $('#refund_orderList_dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条小单记录！");
		return;
	}
	
	var selections=$('#refund_orderList_dg').datagrid("getChecked");
	var dataArr = new Array();
	$.each(selections ,function(index,item){
		var RefundCheckItem=new Object();
		RefundCheckItem.id=selections[index].id;
		RefundCheckItem.refund_id=selections[index].refund_id;
		dataArr.push(RefundCheckItem);
	});
	
/*	alert(JSON.stringify(dataArr));*/
	
	$.ajax({  
	        url : global_param.context_name + "/refund/check/refundPassAudit",  
	        type : "get",  
	       // data : "dataArr="+JSON.stringify(dataArr),
	        data : {"dataArr":JSON.stringify(dataArr)},
	        contentType: "application/json", //必须有
            dataType: "json", //表示返回值类型，不必须
	        success : function(data, status) {  
	        	queryForSupInfoDatagrid();
/*	            alert("成功"); */
	        },  
	        Error : function(error, exception) {  
	            alert(exception.toString());  
	        }  
	 });
	
}

