
var packingunitTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=125");
$(function() {
	// 初始化表格
	$("#dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/stock/stockInfo/lists", // 数据来源
		title : '批次停售信息管理', // 标题
		sortName : 'stock_info_id', // 排序的列
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
		},
		columns :[[{
			field : 'stock_info_id',
			title : '库存编号',
			width : 80,
			sortable : true
		}, {
			field : 'stock_batchCode',
			title : '产品批号',
			width : 80,
			sortable : true
		}, {
			field : 'stock_invalidDate',
			title : '失效期',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value);
			}
		}, {
			field : 'stock_storageNumber',
			title : '剩余库存',
			width : 100,
			sortable : true
		}, {
			field : 'stock_intakeTicket',
			title : '入库票号',
			width : 80,
			sortable : true
		},{
			field : 'stock_intakeSmallNumber',
			title : '入库小号',
			width : 80,
			sortable : true
		}]],
		toolbar : [{
			id : 'saleStop',
			text : '停售',
			iconCls : 'icon-delete',
			handler : saleStopFunc
		},{
			text : '全选',
			iconCls : 'icon-modify',
			handler : checkAllFunc
		},{
			text : '清除',
			iconCls : 'icon-modify',
			handler : uncheckAllFunc
		},{
			text : '重置',
			iconCls : 'icon-reload',
			handler : reloadDatagrid
		}]
	});
	// 初始化编辑窗口
	util.window('batchListWindow', {
		title : '停售批次信息'
	});
	
	//如果当前焦点在客户编码上，失去焦点触发该动作，输入非空字符进行查询，进入选择供方界面
	$('#product_code').bind('blur', function() {
		//输入后，打开供方客户页面
		var proContent = $('#product_code').attr("value");
		if(proContent!=null && proContent!=""){
			$("#batchListWindow").window({
				title : '停售批次选择',
				width : 1250,
				height : 650,
				shadow : true,
				modal : true,
				closed : true,
				minimizable : false,	
				maximizable : false,
				collapsible : false,
				resizable : false,
				content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/stock/stockInfo/viewProList?callback=setProContent&proContent=' + proContent.replace("%", "%25") +'"></iframe>',
			});
			$('#batchListWindow').window('open');
		}
	});
	
    $('#stock_stopReason').focus(function(){
    	$("#stock_stopReason").attr("readonly",false);
    	var node = $('#dg').datagrid('getSelected');
    	if(!node){ 
    		$("#stock_stopReason").attr("readonly",true);
    		util.show("请选择一条记录");
    		}
     });
 
    
	 document.onkeydown=function(event){ 
         var e = event || window.event || arguments.callee.caller.arguments[0]; 
         if(e && e.keyCode==27){ // 按 Esc  
             //要做的事情 
             //alert("按 esc"); 
           } 
          if(e && e.keyCode==13){ // enter 键 
              //要做的事情 
        	 $("#saleStop").focus();
         } 
     };  
});

function query() {
	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#dg').datagrid('reload');
}
function reloadDatagrid(){
	$('#searchForm').form('clear');
	$("#stock_stopReason").attr("value",'');
	$('#displayForm').form('clear');
	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#dg').datagrid('reload');
}
function saleStopFunc() {
	alert($('#stock_stopReason').val());
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	var selections=$('#dg').datagrid("getSelections");
	var dataArr = new Array();
	$.each(selections ,function(index,item){
		var BatchSaleStopItem=new Object();
		BatchSaleStopItem.stock_info_id=selections[index].stock_info_id;
		BatchSaleStopItem.stock_stopReason=encodeURI(encodeURI($('#stock_stopReason').val()));
		dataArr.push(BatchSaleStopItem);
	});
/*	$.each(selections ,function(index,item){
		data = data +selections[index].stock_info_id+"_";
	});
	alert(document.charset);
	data = data.substring(0,data.length-1);
	alert(data);*/
	//var result = util.get(global_param.context_name + "/stock/stockInfo/saleStop?reason="+encodeURI(encodeURI("哈哈")));
	alert(JSON.stringify(dataArr));
	$.ajax({  
        url : global_param.context_name + "/stock/stockInfo/saleStop",  
        type : "get",  
        data : {"dataArr":JSON.stringify(dataArr)},
        contentType: "application/json;charset=UTF-8", //必须有
        dataType: "json", //表示返回值类型，不必须
        success : function(data, status) {  
        	$('#dg').datagrid('reload');
        },  
        Error : function(error, exception) {  
            alert(exception.toString());  
        }  
 });
}
/*
 * 子菜单回调函数组
 * */
//获得子窗口选择的供方信息,并执行相关操作
function setProContent(node){
	//获得选择的库存产品批次停售信息
	//表单信息插入
	$("#product_code").attr("value",node.product_code);
	$("#product_id").attr("value",node.product_id);
	$("#product_name").attr("value",node.product_name);
	$("#product_specification").attr("value",node.product_specification);
	$("#product_unit").attr("value",node.product_unit);
	for(var i = 0 ; i < packingunitTypes.length ;i++){
		if(node.product_packingunit == packingunitTypes[i].cbs_id){
			$("#product_packingunit").attr("value",packingunitTypes[i].cbs_chn);
		}
	}
	$("#product_productarea").attr("value",node.product_productarea);
	query();
};

function checkAllFunc(){
	 $("#dg").datagrid("checkAll");
}
function uncheckAllFunc(){
	$("#dg").datagrid("uncheckAll");
}
$.extend($.fn.datagrid.methods, {
	//键盘上下键换行
    keyCtr : function (jq) {
        return jq.each(function () {
            var grid = $(this);
            grid.datagrid('getPanel').panel('panel').attr('tabindex', 1).bind('keydown', function (e) {
            	var rows = grid.datagrid('getRows');
                var selected = grid.datagrid('getSelected');
                var index = grid.datagrid('getRowIndex', selected);
                switch (e.keyCode) {
                case 38: // up
                    if (index>0) {
                    	grid.datagrid('uncheckRow', index);
                        grid.datagrid('selectRow', index - 1);
                    } else {
                    	grid.datagrid('uncheckRow', 0);
                        grid.datagrid('selectRow', rows.length - 1);
                    }
                    break;
                case 40: // down
                    if (index<rows.length - 1) {
                    	grid.datagrid('uncheckRow', index);
                        grid.datagrid('selectRow', index + 1);
                    } else {
                        grid.datagrid('uncheckRow', rows.length - 1);
                        grid.datagrid('selectRow', 0);
                    }
                    break;
                }
            });
        });
    }
});