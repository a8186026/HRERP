var flag;//qurey的参数
var proHave;//输入验证是否合理的参数

//记录选中的行，以便行切换的时候关闭当前行编辑
var currentEdit = 0;
var ctrlsID = ["shouldDeliveryNum","deptPlan_applyNum","deptPlan_applyPrice"];

//确认BUTTON
var sumbitButtonID = "submitDeptPlan";

$(function() {
	var keyPress = new pageKeyPress();
	keyPress.bindKeyPressToCtrl(ctrlsID, sumbitButtonID);

});
//获得子窗口的产品信息
function setProContent(node){
	//修改控件信息
	$("#product_code").attr("value",node.product_code);
	$("#deptPlan_applyPrice").attr("value",node.product_retailprice);
	if(node.product_code != null){//当product_code不为空时，查询并更新第一栏产品信息表
		$("#ProInfoDg").datagrid("options").queryParams.product_code = node.product_code;
		$("#ProInfoDg").datagrid("reload");
	}
	
};
//给product_code绑定回车键，弹出产品信息窗口，获取product_code
$(function() {
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
	 				width : 1250,
	 				height : 650,
	 				shadow : true,
	 				modal : true,
	 				closed : true,
	 				minimizable : false,	
	 				maximizable : false,
	 				collapsible : false,
	 				resizable : false,
	 				content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/dept/plan/viewProList?callback=setProContent&proContent=' + proContent.replace("%", "%25") +'"></iframe>',
	 			});
	 			$('#proListWindow').window('open');
	 		}
	    }
	});
	//调出库房信息的下拉框列表
	$('#oh_deptPlan_exportDept').combobox({
		url : global_param.context_name + "/basInfo/basGroupNoInfo/getDepartmentList",
		method :"get",
		valueField : "department_id",
		textField : "department_fullname",
		disabled:true,//不可修改
		onLoadSuccess:function(){
			var dept_id = null;
			var list = util.get(global_param.context_name + "/system/parameter/getParamCode?param="+"DEPT_MANAGE");
			for(var i=0;i<list.data.length;i++){
				if(list.data[i].param_sub_id==5){
					dept_id = list.data[i].param_sub_value;
				}
			}
			$(this).combobox("setValue",dept_id);
		}
	});
	//申请库房信息的下拉框列表
	$('#oh_deptPlan_applyDept').combobox({//edit页面中的库房信息的下拉框列表，为防止与searchForm中的department_id冲突，取名pro_department_id
		url : global_param.context_name + "/basInfo/basGroupNoInfo/getDepartmentList",
		method :"get",
		disabled:true,//不可修改
		valueField : "department_id",
		textField : "department_fullname",
		onLoadSuccess:function(){
			var my_dept_id = null;
			var list = util.get( global_param.context_name + "/dept/plan/getApplyDept");
			my_dept_id = list[0].department_id;
			$(this).combobox("setValue",my_dept_id);
		}
	});
	//包装量
	var packingunitTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=125");
	// 初始化产品信息表格，在页面第一栏
	$("#ProInfoDg").datagrid({
		method : 'get',
		url : global_param.context_name + "/product/proInfoManage/lists", // 数据来源
		//title : '产品档案信息管理', // 标题
		sortName : 'product_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitcolumns : true,
		singleSelect : true,//单选
		columns:[[{
			field : 'product_code',
			title : '产品编号',
			width : 100,
			sortable : true
		},{
			field : 'product_barcode',
			title : '产品条码',
			width : 100,
			sortable : true
		},{
			field : 'product_name',
			title : '产品名称',
			width : 100,
			sortable : true
		},{
			field : 'product_chnpy',
			title : '拼音码',
			width : 100,
			sortable : true
		},{
			field : 'product_specification',
			title : '规格',
			width : 100,
			sortable : true
		},{
			field : 'product_unit',
			title : '单位',
			width : 60,
			sortable : true
		},{
			field : 'product_productarea',
			title : '产地',
			width : 150,
			sortable : true
		},{
			field : 'product_packingamount',
			title : '包装量',
			width : 60,
			sortable : true
		},{
			field : 'product_packingunit',
			title : '包装单位',
			width : 60,
			sortable : true,
			formatter :function(value, row, index){
				for(var i = 0 ; i < packingunitTypes.length ;i++){
					if(value == packingunitTypes[i].cbs_id){
						return packingunitTypes[i].cbs_chn;
					}
				}
			}
		},{
			field : 'product_lastprice',
			title : '最后进价',
			width : 60,
			sortable : true
		},{
			field : 'product_marketprice',
			title : '市场价',
			width : 60,
			sortable : true
		},{
			field : 'product_tradeprice',
			title : '批发价',
			width : 60,
			sortable : true
		},{
			field : 'product_retailprice',
			title : '零售价',
			width : 60,
			sortable : true
		},{
			field : 'product_Iretaillprice',
			title : '最低价',
			width : 60,
			sortable : true
		},{
			field : 'product_saleprice1',
			title : '销售价1',
			width : 60,
			sortable : true
		},{
			field : 'product_saleprice2',
			title : '销售价2',
			width : 60,
			sortable : true
		},{
			field : 'product_saleprice3',
			title : '销售价3',
			width : 60,
			sortable : true
		},{
			field : 'product_saleprice4',
			title : '销售价4',
			width : 60,
			sortable : true
		}, {
			field : 'product_stocknum',
			title : '库存数量',
			width : 60,
			sortable : true
		}]],
	});
	// 初始化编辑窗口
	util.window('editWindow', {
		title : '产品档案信息',
	});
	
	
	// 初始化产品信息和库存信息关联表格，在页面第二栏
	$("#StockInfoDg").datagrid({
		method : 'get',
		url : global_param.context_name + "/dept/plan/stockInfoList", // 数据来源
		title : '调货信息', // 标题
		sortName : 'stock_info_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitColumns : true,
		singleSelect : false, // 单选
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
			field : 'product_musthave',
			title : '必调',
			width : 60,
			sortable : true,
			formatter: function(value, row, index){
				if(value==0 ){
					return "<font color='green'>非必调</font>";
				}else if(value==1){
					return "<font color='red'>必调</font>";	
				}
			}
		},{
			field : 'must',
			title : '应调',
			width : 50,
			sortable : true,
			formatter: function(value, row, index){
				if(row.stock_storageNumber < row.product_minnunmber){
					return "<font color='red'>应调</font>";
				}else{
					return "<font color='green'>非应调</font>";	
				}
			}
		},{
			field : 'stock_info_id',
			title : '库房序号',
			width : 60,
			sortable : true
		},{
			field : 'product_id',
			title : '产品序号',
			width : 60,
			sortable : true
		},{
			field : 'product_code',
			title : '产品编号',
			width : 80,
			sortable : true
		},{
			field : 'product_unit',
			title : '生产厂家',
			width : 60,
			sortable : true
		},{
			field : 'product_specification',
			title : '规格',
			width : 60,
			sortable : true
		},{
			field : 'product_minnunmber',
			title : '最低限量',
			width : 60,
			sortable : true
		},{
			field : 'product_essentialvar',
			title : '必备品种',
			width : 60,
			sortable : true
		},{
			field : 'product_retailprice',
			title : '零售价',
			width : 60,
			sortable : true
		},{
			field : 'product_retailprice',
			title : '下浮',
			width : 60,
			sortable : true
		},{
			field : 'product_shiphandlingnum',
			title : '配送包装',
			width : 60,
			sortable : true
		},{
			field : 'product_name',
			title : '产品名称',
			width : 80,
			sortable : true
		},{
			field : 'proPlan_applyNum',//分店计划申请数量
			title : '申请数量',
			width : 60,
			editor:{  
                type:'text',  
                options:{
                    required:true  
                }  
            }
		},{
			field : 'product_minnunmber',
			title : '最低限量',
			width : 60,
			sortable : true
		}, {
			field : 'product_maxnunmber',
			title : '最高限量',
			width : 60,
			sortable : true
		}, {
			field : 'product_purchasenum',
			title : '进货量',
			width : 60,
			sortable : true
		}, {
			field : 'stock_storageNumber',
			title : '库房数量',
			width : 60,
			sortable : true
		},{
			field : 'product_productarea',
			title : '产地',
			width : 60,
			sortable : true
		},{
			field : 'product_unit',
			title : '单位',
			width : 60,
			sortable : true
		},{
			field : 'product_category',
			title : '分类',
			width : 60,
			sortable : true
		},{
			field : 'product_category2',
			title : '分类2',
			width : 60,
			sortable : true
		},{
			field : 'product_category3',
			title : '分类3',
			width : 60,
			sortable : true
		}, {
			field : 'product_category4',
			title : '分类4',
			width : 60,
			sortable : true
		}, {
			field : 'product_category5',
			title : '分类5',
			width : 60,
			sortable : true
		}, {
			field : 'product_othercategory',
			title : '其它分类',
			width : 80,
			sortable : true
		}, {
			field : 'product_othercategory2',
			title : '其它分类2',
			width : 80,
			sortable : true
		}, {
			field : 'product_othercategory3',
			title : '其它分类3',
			width : 80,
			sortable : true
		},{
			field : 'product_marketprice',
			title : '其他分类4',
			width : 80,
			sortable : true
		},{
			field : 'product_tradeprice',
			title : '其他分类5',
			width : 80,
			sortable : true
		}
		]],
		toolbar : [{
			text : '必调查询',
			iconCls : 'icon-search',
			handler : mustDeliverySearchFunc
		},{
			text : '新建调货计划',
			iconCls : 'icon-add',
			handler : addPlanFunc
		}],
//		onClickRow:function(index,data){
//			var code = data.product_code;
//			var fullname = data.product_commonname;
//			$("#PriceDg").datagrid("options").queryParams.product_code = code;
//			$("#PriceDg").datagrid("options").queryParams.product_commonname = fullname;
//			$("#PriceDg").datagrid("reload");
//		}
		onClickCell:function(index, field, value){
			//开始行编辑
			 $("#StockInfoDg").datagrid('beginEdit', index);
			 //编辑单元格
			 var ed = $("#StockInfoDg").datagrid('getEditor', { index: index, field: field });
			 //如果当前行和选中的行不一样，则关闭当前行的编辑
			 if(currentEdit!=index)
				 $("#StockInfoDg").datagrid('endEdit', currentEdit);
			 //更新当前行
			 currentEdit = index;
		},
	});
	// 初始化编辑窗口
	util.window('editDeptPlanWindow', {
		title : '调价计划信息管理',
		resizable:true,
		width : 400,
		height : 350
	
	});

	
	// 初始化分店定价表格，在页面第三栏
	$("#PlanDg").datagrid({
		method : 'get',
		url : global_param.context_name + "/dept/plan/lists", // 数据来源
		title : '调货计划表', // 标题
		sortName : 'deptPlan_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitColumns : true,
		singleSelect : false,//多选
		//ctrlSelect : true,
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
			field : 'product_musthave',
			title : '必调',
			width : 60,
			sortable : true,
			formatter: function(value, row, index){
				if(value==0 ){
					return "<font color='green'>非必调</font>";
				}else if(value==1){
					return "<font color='red'>必调</font>";	
				}
			}
		},{
			field : 'deptPlan_ticket_id',
			title : '票号',
			width : 80,
			sortable : true
		},{
			field : 'deptPlan_id',
			title : '申请序号',
			width : 60,
			sortable : true
		}, {
			field : 'deptPlan_pro_id',
			title : '产品序号',
			width : 60,
			sortable : true
		},{
			field : 'product_name',
			title : '产品名称',
			width : 60,
			sortable : true,
		},{
			field : 'product_productarea',
			title : '产地',
			width : 60,
			sortable : true,
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
		},{
			field : 'product_factoryname',
			title : '生产厂家',
			width : 60,
			sortable : true,
		},{
			field : 'deptPlan_exportDept',
			title : '调出库房',
			width : 60,
			sortable : true
		},{
			field : 'deptPlan_applyDept',
			title : '申请库房',
			width : 60,
			sortable : true
		},{
			field : 'deptPlan_applyPerson',
			title : '申请人',
			width : 60,
			sortable : true
		},{
			field : 'product_minnunmber',
			title : '最低限量',
			width : 60,
			sortable : true
		},{
			field : 'deptPlan_storageNumber',
			title : '库存数量',
			width : 60,
			sortable : true
		},{
			field : 'deptPlan_applyNum',
			title : '申请数量',
			width : 60,
			editor:{  
                type:'text',  
                options:{
                    required:true,
                    validType:'num',
                    minLength:1,
                    maxLength:2,
                }  
            }
		},{
			field : 'product_maxnunmber',
			title : '最高限量',
			width : 60,
			sortable : true
		},{
			field : 'deptPlan_applyPrice',
			title : '申请单价',
			width : 60,
			sortable : true
		},{
			field : 'deptPlan_applyMoney',
			title : '申请金额',
			width : 60,
			sortable : true
		},{
			field : 'deptPlan_applyTime',
			title : '申请时间',
			width : 120,
			sortable : true,
			formatter: function(value,row,index){
				return util.formatDate(value);
			}
		},{
			field : 'deptPlan_checkStatus',
			title : '审核状态',
			width : 60,
			sortable : true,
			formatter: function(value, row, index){
				if(value==0 ){
					return "<font color='green'>审核已通过</font>";
				}else if(value==1){
					return "<font color='red'>待审核</font>";	
				}
			}
		}]],
		toolbar :"#tb",
		onClickCell:function(index, field, value){
			//开始行编辑
			 $("#PlanDg").datagrid('beginEdit', index);
			 //编辑单元格
			 var ed = $("#PlanDg").datagrid('getEditor', { index: index, field: field });
			 //如果当前行和选中的行不一样，则关闭当前行的编辑
			 if(currentEdit!=index)
				 $("#PlanDg").datagrid('endEdit', currentEdit);
			 //更新当前行
			 currentEdit = index;
		},
	});
	// 初始化编辑窗口
	util.window('editDeptPlanWindow1', {
		title : '分店定价信息',
		width:410,    
	    height:360
	});	
});
// 验证文本框只能输入数字和小数点
function checkNum(e) { 
    if (/^(\d)*$/i.test(e)) { 
        return true;
    }
    return false;
} 
//全选和反选
function selectAll(){
	//如果全选复选框是勾选状态
	if(document.getElementById("allCheck").checked){
		$("#PlanDg").datagrid("selectAll");//选择全部记录
		var sumNum = 0, sumCost = 0;
		var nodes = $('#PlanDg').datagrid('getSelections');
		for(var i=0;i<nodes.length;i++){
			sumNum = sumNum + nodes[i].deptPlan_applyNum;
			sumCost = sumCost + nodes[i].deptPlan_applyMoney;
		}
		$("#sumNumber").val(sumNum);
		$("#sumCost").val(sumCost);
	}else{
		$("#PlanDg").datagrid("unselectAll");
		$("#sumNumber").val(0);
		$("#sumCost").val(0);
	}
}
//qurey方法，当flag=1时，必调查询
function stockInfoQuery(flag) {
	
	$('#StockInfoDg').datagrid('options').queryParams = util.formParams("searchForm");
	$("#StockInfoDg").datagrid("options").queryParams.product_musthave = flag;
	var code = $('#StockInfoDg').datagrid('options').queryParams.product_code;
	if(code != null){//当product_code不为空时，查询并更新第一栏产品信息表
		$("#ProInfoDg").datagrid("options").queryParams.product_code = code;
		$("#ProInfoDg").datagrid("reload");
	}
	$('#StockInfoDg').datagrid('reload');
}
//清空方法
function clearsearchForm(){
	$('#searchForm').form('clear');
	$('#oh_deptPlan_exportDept').combobox('reload');
	$('#oh_deptPlan_applyDept').combobox('reload');
}
//在searchForm中手动添加调货计划
function addPlanManual(){
	$('#StockInfoDg').datagrid('options').queryParams = util.formParams("searchForm");
	var code = $('#StockInfoDg').datagrid('options').queryParams.product_code;
	if (!code) {
		alert("产品编码不能为空！");
		return;
	}
	//判断产品是否存在
	check_proidIsALreadyinPlanByProCodeInPro(code);
	if(proHave){
		alert("您所填产品并不存在，请添加合理的产品编号。");
		proHave = false;
		return;
	}
	//判断产品已经做过计划
	check_proidIsALreadyinPlanByProCodeInDeptPlan(code);
	if(proHave){
		//alert("您所填产品已经在调货计划中了，不需要做重复的计划。");
		if(confirm("产品已经在调货计划中了，是否需要继续做重复的计划？")) {
			proHave = false;
		}
	}
	if(!proHave){
		var	applyNum =  $('#StockInfoDg').datagrid('options').queryParams.deptPlan_applyNum;
		if (!applyNum) {
			alert("申请数量不能为空！");
			return;
		}
		if(!checkNum(applyNum)){
			util.error("申请数量错误");
			return;
		}
		var	applyPrice =  $('#StockInfoDg').datagrid('options').queryParams.deptPlan_applyPrice;
		if (!applyPrice) {
			alert("申请单价不能为空！");
			return;
		}
		if(!checkNum(applyPrice)){
			util.error(" 申请单价错误");
			return;
		}
		var data="";
		data=code+"_"+applyNum+"_"+applyPrice;
		var re = util.get("/HRERP/dept/plan/addPlanManual?data="+data);
		if(re.result == "success"){
			util.show(re.message);
			$('#PlanDg').datagrid('reload');
			
		}else{
			util.error(re.message);
		}
		clearsearchForm();
	}
}
//审核计划
function checkPlanFunc() {
	var node = $('#PlanDg').datagrid('getSelected');
	
	if (!node) {
		alert("请选择一条记录");
		return;
	}
	if(!node.deptPlan_ticket_id){
		util.confirm("确定该条记录通过审核？", function() {
			var data = util.get("/HRERP/dept/plan/check?deptPlan_id=" + node.deptPlan_id);
			//$('#PlanDg').datagrid('reload');
			if (data.result == "success") {
				$('#PlanDg').datagrid('reload');
			}
			util.show(data.message);
		});
	}else{
		alert("该条记录已经审核并提交。您无需再审核！");
	}
}
//修改计划，多条修改
function updatePlanFunc() {
	$("#PlanDg").datagrid('endEdit', currentEdit);	

	//获取所有选中的行
	var nodes = $('#PlanDg').datagrid('getSelections');
	if(nodes.length==0){
		alert("请选择一条记录");
		return;
	}
	var data="";
	var sumStorage;
	for(var i=0;i<nodes.length;i++){
		if(nodes[i].deptPlan_applyNum < 1){
			alert("请输入申请数量");
			return;
		}
		if(checkNum( nodes[i].deptPlan_applyNum )){
			sumStorage = Number(nodes[i].deptPlan_applyNum) + Number(nodes[i].deptPlan_storageNumber);
			if(sumStorage < nodes[i].product_minnunmber){
				$('#PlanDg').datagrid('reload');
				alert("库存数量和申请数量的和不能小于最低限量");
				return;
			}else if(sumStorage > nodes[i].product_maxnunmber){
				$('#PlanDg').datagrid('reload');
				alert("库存数量和申请数量的和不能大于最高限量");
				return;
			}else{
				//将行数据封装成字符串，以便传到后台
				data+=nodes[i].deptPlan_id+"_";
				data+=nodes[i].deptPlan_applyNum;
				
				if(i<nodes.length-1)
					data+="|";
			}
		}else{
			util.error("产品："+nodes[i].product_name+" 申请数量错误");
		}
			
	}
	var re = util.get("/HRERP/dept/plan/update?data="+data);
	if(re.result == "success"){
		util.show(re.message);
		$('#PlanDg').datagrid('reload');
	}else{
		util.error(re.message);
	}
}
//删除计划
function deletePlanFunc() {
	var node = $('#PlanDg').datagrid('getSelected');
	if (!node) {
		alert("请选择一条记录");
		return;
	}
	
	if(!node.deptPlan_ticket_id){
		util.confirm("确定要执行删除操作？", function() {
			var data = util.del(global_param.context_name + "/dept/plan/" + node.deptPlan_id);
			if (data.result == "success") {
				$('#PlanDg').datagrid('reload');
			}
			util.show(data.message);
		});
	}else{
		alert("该条记录已经审核并提交。您不能删除！");
	}
}
//必调查询
function mustDeliverySearchFunc() {
	stockInfoQuery("1");
}
//判断产品已经做过计划by Proid
function check_proidIsALreadyinPlan(product_id){
	var re = util.get(global_param.context_name + "/dept/plan/checkPro?product_id="+product_id);
	if(re.result == "success"){
		proHave = false;
	}else{
		proHave = true;
	}
}
//判断产品是否存在by ProCode
function check_proidIsALreadyinPlanByProCodeInPro(product_code){
	var re = util.get(global_param.context_name + "/dept/plan/checkProByCodeInPro?product_code="+product_code);
	if(re.result == "success"){
		proHave = false;
	}else{
		proHave = true;
	}
}
//判断产品已经做过计划by ProCode
function check_proidIsALreadyinPlanByProCodeInDeptPlan(product_code){
	var re = util.get(global_param.context_name + "/dept/plan/checkProByCodeInDeptPlan?product_code="+product_code);
	if(re.result == "success"){
		proHave = false;
	}else{
		proHave = true;
	}
}
//添加计划，多条添加
function addPlanFunc() {
	
	$("#StockInfoDg").datagrid('endEdit', currentEdit);
	$("#StockInfoDg").datagrid("selectAll");//自动加计划时 对于所有条 只要申请数量不为空 都进行添加操作
	var nodes = $("#StockInfoDg").datagrid("getSelections");//选择全部记录
	if(nodes.length==0){
		alert("没有需要添加的记录");
		return;
	}
	var data="";
	var sumStorage;
	for(var i=0;i<nodes.length;i++){
		if(nodes[i].proPlan_applyNum==null){
			break;
		}
		check_proidIsALreadyinPlan(nodes[i].product_id);
		if(proHave){
			if(confirm("产品已经在调货计划中了，是否需要继续做重复的计划？")) {
				proHave = false;
			}
		}
		if(!proHave){
				if(checkNum( nodes[i].proPlan_applyNum )){
					//验证输入的数量是否合理
					sumStorage = Number(nodes[i].proPlan_applyNum) + Number(nodes[i].stock_storageNumber);
					if(sumStorage < nodes[i].product_minnunmber){
						$('#StockInfoDg').datagrid('reload');
						alert("库存数量和申请数量的和不能小于最低限量");
						return;
					}else if(sumStorage > nodes[i].product_maxnunmber){
						$('#StockInfoDg').datagrid('reload');
						alert("库存数量和申请数量的和不能大于最高限量");
						return;
					}else{
						//将行数据封装成字符串，以便传到后台
						data+=nodes[i].product_id+"_";
						data+=nodes[i].proPlan_applyNum+"_";
						data+=nodes[i].product_retailprice+"_";
						data+=nodes[i].stock_storageNumber+"_";
						
						if(i<nodes.length-1)
							data+="|";
					}
				}else{
					util.error("产品："+nodes[i].product_name+" 申请数量错误");
				}
			}
	}
	if(data==null || data==""){
		return;
	}else{
		var re = util.get("/HRERP/dept/plan/addPlan?data="+data);
		if(re.result == "success"){
			util.show(re.message);
			$('#PlanDg').datagrid('reload');
			$('#StockInfoDg').datagrid('reload');
		}else{
			util.error(re.message);
		}
	}
	
}