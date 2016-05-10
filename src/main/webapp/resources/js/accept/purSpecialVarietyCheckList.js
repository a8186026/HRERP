
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

$(function() {
	// 初始化表格
	$("#dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/specialvariety/check/lists", // 数据来源
		title : '特殊药品审核', // 标题
		sortName : 'id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitcolumns : true,
		singleSelect : false,//单选
		pageSize : 10,
		pageList : [10,20,50,100],
		fit : true,
		align : 'center',
		//triped : true,//奇偶行颜色不同
		pagination : true,
		rownumbers : true,
		nowrap : true,
		striped : true,
		queryParams : {},
		columns:[[
       /* {   
        	field:'ck',
        	width : 80,
        	align : 'center',
        	formatter:function (value, row, index) {
				return "<input id='checkbox_"+index+"' name='DataGridCheckbox' style='width:35px' type='checkbox'></input>";
			}
        	
        },*/{
			field : 'accept_check_id',
			title : '检测单号',
			width : 80,
			align : 'center',
			sortable : true
		},{
			field : 'ticket_id',
			title : '票号',
			width : 80,
			align : 'center',
			sortable : true
		}, {
			field : 'product_id',
			title : '产品序号',
			width : 100,
			align : 'center',
			sortable : true
		}, {
			field : 'product_code',
			title : '产品编码',
			width : 80,
			align : 'center',
			sortable : true
		}, {
			field : 'quantity',
			title : '数量',
			width : 80,
			align : 'center',
			sortable : true
		}, {
			field : 'unitprice',
			title : '单价',
			width : 80,
			align : 'center',
			sortable : true
		}, {
			field : 'sum',
			title : '结算价',
			width : 80,
			align : 'center',
			sortable : true
		}]],
		toolbar : [ {
			id: 'specialVarietyCheck',
			text : '验收审核',
			iconCls : 'icon-add',
			handler : checkSpecialVariety
		}, {
			text : '全选',
			iconCls : 'icon-modify',
			handler : checkAllFunc
		}, {
			text : '清除',
			iconCls : 'icon-delete',
			handler : uncheckAllFunc
		}]/*,
		onClickCell : function(index,field,value){
			$("input[id='checkbox_"+index+"']").each(function(){
				 if (this.checked) {
		             this.checked = false;
		         }
		         else {
		             this.checked = true;
		         }
			});
		}*/
	}).datagrid("keyCtr");
	
	
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
				content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/specialvariety/check/viewSupList?callback=setSupContent&supContent=' + supContent.replace("%", "%25") +'"></iframe>',
			});
			$('#supListWindow').window('open');
		}
	});
	//如果当前焦点在产品编码上，失去焦点触发该动作，输入非空字符进行查询，进入产品选择界面
	$('#product_code').bind('blur', function() {
		//输入后，打开供方客户页面
		var proContent = $('#product_code').attr("value");
		if(proContent!=null && proContent!=""){
			$("#proListWindow").window({
				title : '产品选择',
				width : 1250,
				height : 650,
				shadow : true,
				modal : true,
				closed : true,
				minimizable : false,	
				maximizable : false,
				collapsible : false,
				resizable : false,
				content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/specialvariety/check/viewProList?callback=setProContent&proContent=' + proContent.replace("%", "%25") +'"></iframe>',
			});
			$('#proListWindow').window('open');
		}
	});
	
	$("#specialVarietyCheck").focus();
	
	
	 document.onkeydown=function(event){ 
         var e = event || window.event || arguments.callee.caller.arguments[0]; 
         if(e && e.keyCode==27){ // 按 Esc  
             //要做的事情 
             //alert("按 esc"); 
           } 
          if(e && e.keyCode==13){ // enter 键 
              //要做的事情 
        	 $("#specialVarietyCheck").focus();
         } 
     };  
	
});

function checkSpecialVariety(){
	
	var selections=$('#dg').datagrid("getChecked");
	var accept_specialQuantityCondition=$("#accept_specialQuantityCondition").val();
	var accept_specialCheckConclusion=$("#accept_specialCheckConclusion").val();
	/*for (var i = 0; i < selections.length; i++) {
		 $.ajax({  
		        url : global_param.context_name + "/specialvariety/check/checkspecialvariety",  
		        type : "post",  
		        data : "pur_orderList_id="+selections[i].id+"&accept_check_id="+selections[i].accept_check_id+"&accept_specialQuantityCondition="+accept_specialQuantityCondition+"&accept_specialCheckConclusion="+accept_specialCheckConclusion+"&accept_specialStatus="+accept_specialCheckConclusion,
		        //dataType:'json',
		        //contentType: 'application/json;chart=UTF-8',
		        success : function(data, status) {  
		        	 query();
		        },  
		        Error : function(error, exception) {  
		            alert(exception.toString());  
		        }  
		    });  
	}*/
	var dataArr = new Array();
	$.each(selections ,function(index,item){
		var PurAcceptCheck=new Object();
		PurAcceptCheck.id=selections[index].id;
		PurAcceptCheck.accept_check_id=selections[index].accept_check_id;
		PurAcceptCheck.accept_specialQuantityCondition=accept_specialQuantityCondition;
		PurAcceptCheck.accept_specialCheckConclusion=accept_specialCheckConclusion;
		PurAcceptCheck.accept_specialStatus=accept_specialCheckConclusion;
		dataArr.push(PurAcceptCheck);
	});
	//alert(JSON.stringify(dataArr));
	$.ajax({  
	        url : global_param.context_name + "/specialvariety/check/checkspecialvariety",  
	        type : "get",  
	       // data : "dataArr="+JSON.stringify(dataArr),
	        data : {"dataArr":JSON.stringify(dataArr)},
	        contentType: "application/json", //必须有
            dataType: "json", //表示返回值类型，不必须
	        success : function(data, status) {  
	        	query();
	        },  
	        Error : function(error, exception) {  
	            alert(exception.toString());  
	        }  
	 });
}

function checkAllFunc(){
	 $("#dg").datagrid("checkAll");
}
function uncheckAllFunc(){
	$("#dg").datagrid("uncheckAll");
}
/*
 * 子菜单回调函数组
 * */
//获得子窗口选择的供方信息,并执行相关操作
function setSupContent(node){
	//获得选择的供方信息
	//表单信息插入
	$("#supply_id").attr("value",node.sup_id);
	$("#sup_name").attr("value",node.sup_name);
	$('#supply_code').attr("value",node.sup_code);
	$('#sup_ctactperson').attr("value",node.sup_ctactperson);
	$('#sup_tel').attr("value",node.sup_tel);
	$('#product_code').focus();
	query();
};

//获得子窗口的产品信息
function setProContent(node){
	//存储当前的产品信息
	proInfo = node;
	//修改控件信息
	$('#product_id').attr("value",node.product_id);
	$("#product_code").attr("value",node.product_code);
	$('#product_name').attr("value",node.product_name);
	$('#product_factoryname').attr("value",node.product_factoryname);
	$('#product_specification').attr("value",node.product_specification);
	$('#product_unit').attr("value",node.product_unit);
	query();
};


function query() {
	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#dg').datagrid('reload');
}

function getNextElement (field) {
	var form = field.form;
    for (var e = 0; e < form.elements.length; e++) { 
    	if (field == form.elements[e])
            break;
    }
    return form.elements[++e % form.elements.length];
} 

function clearForm(){
	 $("#searchForm input[type='text']").val("");
	 $("#searchForm select").find("option[value=0]").attr("selected","selected");
	}
