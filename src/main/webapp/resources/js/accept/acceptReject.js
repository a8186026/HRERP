var dosageTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=98");
var storageconditionTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=344");
var unitTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=132");
var categoryTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=588");
	//获得子窗口的产品信息
	function setProContent(node){
		//修改控件信息
		$("#product_code").attr("value",node.product_code);
		$("#product_id").attr("value",node.product_id);
		$("#product_barcode").attr("value",node.product_barcode);
		$("#product_specification").attr("value",node.product_specification);
		$("#product_dosagetype").attr("value",function(index){
			for(var i = 0 ; i < dosageTypes.length ;i++){
				if(node.product_dosagetype == dosageTypes[i].cbs_id){
					return dosageTypes[i].cbs_chn;
				}
			}
		});
		$("#product_name").attr("value",node.product_name);
		$("#product_factoryname").attr("value",node.product_factoryname);
		$("#product_unit").attr("value",function(index){
			for(var i = 0 ; i < unitTypes.length ;i++){
				if(node.product_unit == unitTypes[i].cbs_id){
					return unitTypes[i].cbs_chn;
				}
			}
		});
		$("#product_productarea").attr("value",node.product_productarea);
		$("#product_category").attr("value",function(index){
			for(var i = 0 ; i < categoryTypes.length ;i++){
				if(node.product_category == categoryTypes[i].cbs_id){
					return categoryTypes[i].cbs_chn;
				}
			}
		});
		$("#product_packingamount").attr("value",node.product_packingamount);
		$("#product_storagecondition").attr("value",function(index){
			for(var i = 0 ; i < storageconditionTypes.length ;i++){
				if(node.product_storagecondition == storageconditionTypes[i].cbs_id){
					return storageconditionTypes[i].cbs_chn;
				}
			}
		});
		$("#product_approvalname").attr("value",node.product_approvalname);
	};
	/*
	 * 子菜单回调函数组
	 * */
	//获得子窗口选择的供方信息,并执行相关操作
	function setSupContent(node){
		//获得选择的供方信息
		$("#supply_code").attr("value",node.sup_code);
		$("#sup_name").attr("value",node.sup_name);
		$("#sup_ctactperson").attr("value",node.sup_ctactperson);
		$('#sup_tel').attr("value",node.sup_tel);
		$('#sup_id').attr("value",node.sup_id);
	};
	//给product_code绑定回车键，弹出产品信息窗口，获取product_code
	$(function() {
		$('#reject_validtime').datebox({
			required:true,	
		}); 
		$('#reject_productdate').datebox({
			required:true,	
		}); 
		$('#product_code').bind('keydown', function() {
		    var e = event || window.event;
		    var keyCode = e.keyCode || e.which;
		    var actElement = document.activeElement;
		    //如果按键是enter并且当前焦点是产品编号，则弹出产品选择框
		    if(keyCode == 13&&actElement.id == "product_code"){
		    	//输入后，打开供方客户页面
		 		var proContent = $('#product_code').attr("value");
		 		if(proContent!=null && proContent!=""){
		 			$("#proListWindow").window({
		 				title : '产品选择',
		 				width : 1400,
		 				height : 650,
		 				shadow : true,
		 				modal : true,
		 				closed : true,
		 				minimizable : false,	
		 				maximizable : false,
		 				collapsible : false,
		 				resizable : false,
		 				content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/purchase/acceptReject/viewProList?callback=setProContent&proContent=' + proContent.replace("%", "%25") +'"></iframe>',
		 			});
		 			$('#proListWindow').window('open');
		 		}
		    }
		});
		/*
		 * 各控件动作管理
		 * 
		 * */
		//如果当前焦点在客户编码上，失去焦点触发该动作，输入非空字符进行查询，进入选择供方界面
		$('#supply_code').bind('keydown', function() {//输入后，打开供方客户页面
			 var e = event || window.event;
			    var keyCode = e.keyCode || e.which;
			    var actElement = document.activeElement;
			    //如果按键是enter并且当前焦点是产品编号，则弹出产品选择框
			    if(keyCode == 13&&actElement.id == "supply_code"){
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
							content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/purchase/order/viewSupList?callback=setSupContent&supContent=' + supContent.replace("%", "%25") +'"></iframe>',
						});
						$('#supListWindow').window('open');
					}
			    }
		});
		$('#user_name').attr("value", function() {
			var username = util.get(global_param.context_name + "/purchase/acceptReject/getUserName");
			return username;
		});
		$('#reject_ticket').attr("value", function() {
			var username = util.get(global_param.context_name + "/purchase/acceptReject/getReject_ticket");
			return username;
		});
		
	// 初始化表格
	$("#dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/purchase/acceptReject/lists", // 数据来源
		title : '收货拒收处理', // 标题
		sortName : 'reject_id', // 排序的列
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
			field : 'reject_ticket',
			title : '票号',
			width : 120,
			sortable : true
		}, {
			field : 'product_id',
			title : '产品序号',
			width : 80,
			sortable : true
		}, {
			field : 'sup_id',
			title : '供方序号',
			width : 80,
			sortable : true
		}, {
			field : 'product_code',
			title : '产品编码',
			width : 100,
			sortable : true
		}, {
			field : 'product_name',
			title : '产品名称',
			width : 100,
			sortable : true
		}, {
			field : 'reject_num',
			title : '数量',
			width : 80,
			sortable : true
		}, {
			field : 'reject_price',
			title : '单价',
			width : 80,
			sortable : true
		},{
			field : 'product_specification',
			title : '规格',
			width : 80,
			sortable : true
		}, {
			field : 'product_unit',
			title : '单位',
			width : 60,
			sortable : true,
			formatter : function (value, row, index) {
				for(var i = 0 ; i < unitTypes.length ;i++){
					if(value == unitTypes[i].cbs_id){
						return unitTypes[i].cbs_chn;
					}
				}
			}
		}, {
			field : 'product_productarea',
			title : '产地',
			width : 120,
			sortable : true
		}, {
			field : 'product_factoryname',
			title : '生产厂家',
			width : 150,
			sortable : true
		}, {
			field : 'product_packingamount',
			title : '包装量',
			width : 60,
			sortable : true
		}, {
			field : 'product_storagecondition',
			title : '储存条件',
			width : 80,
			sortable : true,
			formatter : function (value, row, index) {
				for(var i = 0 ; i < storageconditionTypes.length ;i++){
					if(value == storageconditionTypes[i].cbs_id){
						return storageconditionTypes[i].cbs_chn;
					}
				}
			}
		}, {
			field : 'product_approvalname',
			title : '批准文号',
			width : 80,
			sortable : true
		}, {
			field : 'sup_name',
			title : '客户全称',
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
			width : 120,
			sortable : true
		}, {
			field : 'display_name',
			title : '操作员',
			width : 80,
			sortable : true
		}, {
			field : 'reject_batchnum',
			title : '批号',
			width : 80,
			sortable : true
		}, {
			field : 'reject_validtime',
			title : '有效期',
			width : 100,
			sortable : true,
			formatter: function(value,row,index){
				return util.formatDate(value);
			}
		}, {
			field : 'reject_productdate',
			title : '生产日期',
			width : 100,
			sortable : true,
			formatter: function(value,row,index){
				return util.formatDate(value);
			}
		}, {
			field : 'reject_reason',
			title : '拒收理由',
			width : 160,
			sortable : true
		}, {
			field : 'reject_quality',
			title : '质量状况',
			width : 80,
			sortable : true
		},{
			field : 'reject_status',
			title : '拒收状态',
			width : 120,
			sortable : true,
			formatter:function(value){
				 if(value==0){
					return "<font color='green'>已提交</font>";
				}else if(value==1){
					return "<font color='red'>未提交</font>";
				}else{
					return "";
				}
				
			}
		}]],
		toolbar :"#tb",
	});
	// 初始化编辑窗口
	util.window('editAcceptRejectWindow', {
		title : '拒收信息',
		width:610,    
	    height:270
	});	
});

//function query() {
//	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
//	$('#dg').datagrid('reload');
//}
//???如何获取供方对应的财务信息
function save() {
	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	var product_id = $('#dg').datagrid('options').queryParams.product_id;
	if( product_id== null || product_id==""){
		alert("请选择产品编号");
		return;
	}
	var sup_id = $('#dg').datagrid('options').queryParams.sup_id;
	if( sup_id== null ||sup_id ==""){
		alert("请选择供方编号");
		return;
	}
	var reject_batchnum = $('#dg').datagrid('options').queryParams.reject_batchnum;
	if( reject_batchnum== null || reject_batchnum==""){
		alert("请输入批号");
		return;
	}
	var reject_validtime = $('#dg').datagrid('options').queryParams.reject_validtime;
	if( reject_validtime== null || reject_validtime==""){
		alert("请输入有效期");
		return;
	}
	var reject_productdate = $('#dg').datagrid('options').queryParams.reject_productdate;
	if( reject_productdate== null || reject_productdate==""){
		alert("请输入生产日期");
		return;
	}
	var reject_num = $('#dg').datagrid('options').queryParams.reject_num;
	if( reject_num== null || reject_num==""){
		alert("请输入数量");
		return;
	}
	var reject_price = $('#dg').datagrid('options').queryParams.reject_price;
	if(reject_price == null || reject_price==""){
		alert("请输入单价");
		return;
	}
	var reject_reason = $('#dg').datagrid('options').queryParams.reject_reason;
	if( reject_price== null ||reject_price ==""){
		alert("请输入拒收原因");
		return;
	}
	var reject_quality = $('#dg').datagrid('options').queryParams.reject_quality;
	if( reject_quality== null || reject_quality==""){
		alert("请输入质量状况");
		return;
	}
	
	var data="";
	//将行数据封装成字符串，以便传到后台
	data+=product_id+"_";
	data+=sup_id+"_";
	data+=reject_batchnum+"_";
	data+=reject_validtime+"_";
	data+=reject_productdate+"_";
	data+=reject_num+"_";
	data+=reject_price+"_";
	data+=reject_reason+"_";
	data+=reject_quality+"_";

	if( data== null || data==""){
		return;
	}
	util.get("/HRERP/purchase/acceptReject/new?data="+data);
	$('#dg').datagrid('reload');
}	

function updateRejectFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editAcceptRejectWindow',  "/HRERP/purchase/acceptReject/update?reject_id=" + node.reject_id);
}

function deleteRejectFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del( "/HRERP/purchase/acceptReject/" + node.reject_id);
		if (data.result == "success") {
			$('#dg').datagrid('reload');
		}
		util.show(data.message);
	});
}

function saveRejectFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要提交这条拒收信息？提交后，您将不能修改。", function() {
		var data = util.get( "/HRERP/purchase/acceptReject/submit?reject_id=" + node.reject_id);
		if (data.result == "success") {
			$('#reject_ticket').attr("value", function() {
				var username = util.get(global_param.context_name + "/purchase/acceptReject/getReject_ticket");
				return username;
			});
			$('#dg').datagrid('reload');
		}
		util.show(data.message);
	});
	
}

