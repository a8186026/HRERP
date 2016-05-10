var ctrlsID = ["orderabstract","knotStyle","policyStyle","remarks",
               "carryMode","orderTransMode","carryCompany","delivaryPlace",
               "orderDepartureTime","arrivalDate",
               "supply_code",
               "product_code","unitprice","discount","settlementPrice","package","quantity","prepaidForList"];

//确认BUTTON
var sumbitButtonID = "submit_current";

//当前选择的产品
var proInfo = null;

//键盘动作初始化
var keyPress = new pageKeyPress();

$(function(){
	/*
	 * 载入用户习惯
	 * */
	var userHabits0 = new userHabits();
	userHabits0.loadColumns("purOrderEdit","dg_purchase_product","editWindow0",true,true);
	var userHabits1 = new userHabits();
	userHabits1.loadColumns("purOrderEdit","dg_other_product","editWindow1",true,true);
	var userHabits2 = new userHabits();
	userHabits2.loadColumns("purOrderEdit","dg_purOrderList","editWindow2",true,true);
	var userHabits3 = new userHabits();
	userHabits3.loadColumns("purOrderEdit","dg_supply_product","editWindow3",true,true);
	/* 
	 * 下拉菜单数据初始化
	 */ 
	//实现时间控件注册，并赋初值 启运时间
 	$('#orderDepartureTime').datebox({
		required:true,	
	}); 
	if($('#orderDepartureTime').val()!="")
		$('#orderDepartureTime').datebox("setValue",util.toDate($('#orderDepartureTime').val()));
	
	//实现时间控件注册，并赋初值  到货时间
 	$('#arrivalDate').datebox({
		required:true,	
	}); 
	if($('#arrivalDate').val()!="")
		$('#arrivalDate').datebox("setValue",util.toDate($('#arrivalDate').val()));
	//摘要
	$('#orderabstract').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=600",
		method :"get",
		valueField : "cbs_chn",
		textField : "cbs_chn"
	});
	//结款方式
	$('#knotStyle').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=604",
		method :"get",
		valueField : "cbs_chn",
		textField : "cbs_chn"
	});
	//政策形式
	$('#policyStyle').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=611",
		method :"get",
		valueField : "cbs_chn",
		textField : "cbs_chn"
	});
	//承运方式
	$('#carryMode').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=615",
		method :"get",
		valueField : "cbs_chn",
		textField : "cbs_chn"
	});
	//运输方式
	$('#orderTransMode').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=619",
		method :"get",
		valueField : "cbs_chn",
		textField : "cbs_chn"
	});
	
	/* 
	 * 初始化表格 
	 * */
	// 产品详情
	$("#dg_purchase_product").datagrid({
		title : '产品详情', // 标题
		sortName : 'product_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		pagination : false, // 显示分页
		rownumbers : false, // 显示行号
		queryParams : {},
		
	});
	$('#dg_supply_product').datagrid({
		url:global_param.context_name + "/purchase/orderList/listRecent",
		title : '当前产品所有供货商最近三次进货记录', // 标题
		//sortName : 'arrivalDate', // 排序的列
		sortOrder : 'asc', // 排序方式
		pagination : false, // 显示分页
		fit:true,
		singleSelect:true,
		method:"get",
	});
	$('#dg_other_product').datagrid({
		url:global_param.context_name + "/product/proInfoManage/listSimilar?product_check=0",
		title : '其他相近产品', // 标题
		fit:true,
		sortName : 'product_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		method:"get",
		pagination : false, // 显示分页
		singleSelect:true,
	});

	// 订单产品列表
	$("#dg_purOrderList").datagrid({
		fit:true,
		method : "GET",
		title : '订单产品列表', // 标题
		sortName : 'id', // 排序的列
		sortOrder : 'asc', // 排序方式
		pagination : false, // 显示分页
		singleSelect:true,
		queryParams : {},
	});
	// 加载订单订单产品列表
	reloadDg_PurOrderList();
	
	/*
	 * 各控件动作管理
	 * 
	 * */
	//如果当前焦点在客户编码上，失去焦点触发该动作，输入非空字符进行查询，进入选择供方界面
	$('#supply_code').bind('blur', function() {
		//输入后，打开供方客户页面
		var supContent = $('#supply_code').attr("value");
		if(supContent!=null && supContent!=""){
			$("#supListWindow").window({
				title : '客户选择',
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
	     				width : 1250,
	     				height : 650,
	     				shadow : true,
	     				modal : true,
	     				closed : true,
	     				minimizable : false,	
	     				maximizable : false,
	     				collapsible : false,
	     				resizable : false,
	     				content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/purchase/order/viewProList?callback=setProContent&proContent=' + proContent.replace("%", "%25") +'"></iframe>',
	     			});
	     			$('#proListWindow').window('open');
	     		}else{
	     			submitAll();//提交大订单
	     		}
	        }
	});
	
	// 小单各控件填写初始值
	initPurOrderList();
	
	//控件关联管理
	//单价值改变后，计算结算价
	document.getElementById('unitprice').onchange = function (){
		//如果单价折扣为空，则不计算
		if($("#unitprice").val()==""||$('#discount').val()=="")
			return;
		//计算结算价
		var unitprice = parseFloat($('#unitprice').attr("value"));
		var discount = parseInt($('#discount').attr("value"));
		$('#settlementPrice').attr("value",(unitprice/100*discount).toFixed(6));
		setSum();
	};
	//折扣值改变后，计算结算价
	document.getElementById('discount').onchange = function (){
		//如果折扣单价为空，则不计算
		if($("#unitprice").val()==""||$('#discount').val()=="")
			return;
		//计算结算价
		var unitprice = parseFloat($('#unitprice').attr("value"));
		var discount = parseInt($('#discount').attr("value"));
		$('#settlementPrice').attr("value",(unitprice/100*discount).toFixed(4));
		setSum();
	};
	//结算价改变后，修改折扣
	document.getElementById('settlementPrice').onchange = function (){
		//如果结算价单价为空，则不计算
		if($("#settlementPrice").val()==""||$("#unitprice").val()=="")
			return;
		//计算结算价
		var unitprice = parseFloat($('#unitprice').attr("value"));
		var settlementPrice = parseInt($('#settlementPrice').attr("value"));
		$('#discount').attr("value",(settlementPrice*100/unitprice).toFixed(2));
		setSum();
	};
	
	//件数值改变后，计算订货数量
	document.getElementById('package').onchange = function (){
		var packages = parseInt($('#package').attr("value"));
		//得到包装量(如果当前记录的产品信息不为空并且产品已选择)
		if(proInfo!=null && $('#product_code').attr("value")!=null && $('#product_code').attr("value") !=""){
			var countInPackage = proInfo.product_packingamount;
			$('#quantity').attr("value",parseFloat(countInPackage)*packages);
			setSum();
		}
	};
	//订货数量改变后，计算件数
	document.getElementById('quantity').onchange = function (){
		var quantity = parseFloat($('#quantity').attr("value"));
		//得到包装量(如果当前记录的产品信息不为空并且产品已选择)
		if(proInfo!=null && $('#product_code').attr("value")!=null && $('#product_code').attr("value") !=""){
			var countInPackage = proInfo.product_packingamount;
			$('#package').attr("value",quantity/countInPackage);
			setSum();
		}
	};
	//计算全额
	function setSum(){
		var settlementPrice = parseFloat($('#settlementPrice').attr("value"));
		var quantity =  parseFloat($('#quantity').attr("value"));
		$('#sum').attr("value",(settlementPrice*quantity).toFixed(4));
	}
	
	/*
	 * 根据当前的大单是否存在小单来决定大单是否可以修改以及页面的按键顺序
	 * */
	var purOrderListData = $('#dg_purOrderList').datagrid('getRows');
	if(purOrderListData.length > 0){
		// 存在产品记录， 禁用大单信息修改
		forbiddenPurOrderCtrls();
		// 回车按键操作，只在小单范围进行
		changeKeyPressForPurOrderList();
		
	}else{
		// 不存在产品记录，正常操作
		//绑定键盘动作
		keyPress.bindKeyPressToCtrl(ctrlsID, sumbitButtonID);
		//为保证当前页面被聚焦，当前页面上某一控件进行聚焦
		$("#ticket_id").focus();
	}
});

/*
 * 子菜单回调函数组
 * */
//获得子窗口选择的供方信息,并执行相关操作
function setSupContent(node){
	//获得选择的供方信息
	//表单信息插入
	document.getElementById("supply_fullnameForShow").innerHTML=node.sup_name;
	$("#supply_fullname").attr("value",node.sup_name);
	$("#supply_code").attr("value",node.sup_code);
	$('#supply_id').attr("value",node.sup_id);
	$('#product_code').focus();
};

//获得子窗口的产品信息
function setProContent(node){
	//存储当前的产品信息
	proInfo = node;
	//修改控件信息
	$("#product_code").attr("value",node.product_code);
	$('#product_id').attr("value",node.product_id);
	$('#packageNum').attr("value",node.product_packingamount);
	//刷新上方产品详情表
	reloadDg_proInfo(node);
	//刷新最近三次进货
	reloadDg_supInfo();
	//刷新中部相近产品列表
	reloadDg_other_product();
	//重新定位
	$('#unitprice').focus();
};


/*
 * DG刷新函数组
 * */
//刷新上方产品详情表
function reloadDg_proInfo(node){
	//产品详情表格显示，构建显示的行，并刷新表格
	var proRow = {total:1,rows:[node]};
	$('#dg_purchase_product').datagrid('loadData',proRow);
};
//刷新最近三次进货表
function reloadDg_supInfo(){
	if(proInfo!=null){
		//最近此产品三次进货显示
		 var queryParams = $('#dg_supply_product').datagrid('options').queryParams;  
	     queryParams.product_id = proInfo.product_id;
	     $("#dg_supply_product").datagrid('reload'); 
	}
};
//刷新相近产品列表
function reloadDg_other_product(){
	//根据选择的产品选择相近产品进行展示
	if(proInfo!=null){
		 var queryParams = $('#dg_other_product').datagrid('options').queryParams;  
	     queryParams.product_chnpy = proInfo.product_chnpy;
	     queryParams.product_id =proInfo.product_id; 
	     $("#dg_other_product").datagrid('reload'); 
	}
}
//刷新下方订单产品列表
function reloadDg_PurOrderList(){
	var ticket_id = $("#ticket_id").attr("value");
	var url = global_param.context_name + "/purchase/order/getPurOrderListAndProductInfo?ticket_id="+ticket_id;
	var data = util.get(url);
	$("#dg_purOrderList").datagrid('loadData',data);
}

/*
 * 控件控制函数组
 * */
//存在小单后，大单控件禁用
function forbiddenPurOrderCtrls(){
	//禁用控件
	$("#orderabstract").combobox({disabled: true});
	$("#knotStyle").combobox({disabled: true});
	$("#policyStyle").combobox({disabled: true});
	$("#remarks").attr("readonly","readonly");
	$("#carryMode").combobox({disabled: true});
	$("#orderTransMode").combobox({disabled: true});
	$("#carryCompany").attr("readonly","readonly");
	$("#delivaryPlace").attr("readonly","readonly");
	$("#orderDepartureTime").attr("readonly","readonly");
	$("#arrivalDate").attr("readonly","readonly");
	$("#supply_code").attr("readonly","readonly");
}

// 调整按键序列-只在小单启用
function changeKeyPressForPurOrderList(){
	ctrlsID =  ["product_code","unitprice","discount","settlementPrice","package","quantity","prepaidForList"];
	//var keyPress = new pageKeyPress();
	keyPress.bindKeyPressToCtrl(ctrlsID, sumbitButtonID);
	//小单-产品编号聚焦
	$("#product_code").focus();
};



/*
 * 保存菜单函数组
 * */
// 保存大单
function submitPurOrder(draft){
	// 按下保存当前
	// 提交大单信息
	/*
	 * 2015-07-15 By RiverYao
	 * 原实现方案：Form表单提交，出现数据异常，后台无法将Form中的数据封装程bean,并且无法获得表单中的数据
	 * 现实现方案：取消Form表单提交，改用URL拼接参数后，由Ajax进行提交
	 * */
	var flag = false;                                       //是否成功
	var orderFormUrl = $('#orderFormUrl').attr("value");	//表单提交地址
	var orderMethod = $('#orderMethod').attr("value");		//表单提交方式
	// 原表单提交数据
	var id = $("#id").attr("value"); 
	var ticket_id = $("#ticket_id").attr("value");
	var operator = $("#operator").attr("value");
	var orderabstract = $("#orderabstract").combobox('getValue');
	var knotStyle = $("#knotStyle").combobox('getValue');
	var policyStyle = $("#policyStyle").combobox('getValue');
	var remarks = $("#remarks").attr("value");
	var carryMode = $("#carryMode").combobox('getValue');
	var orderTransMode = $("#orderTransMode").combobox('getValue');
	var carryCompany = $("#carryCompany").attr("value");
	var delivaryPlace = $("#delivaryPlace").attr("value");
	var orderDepartureTime = $("#orderDepartureTime").datebox('getValue');
	var arrivalDate = $("#arrivalDate").datebox('getValue');
	var supply_code = $("#supply_code").attr("value");
	var supply_id = $("#supply_id").attr("value");
	var supply_fullname = $("#supply_fullname").attr("value");
	// 地址拼接
	var url = global_param.context_name+orderFormUrl; 
	url += "?id="+id;
	url += "&ticket_id="+ticket_id;
	url += "&operator="+operator;
	url += "&orderabstract="+orderabstract;
	url += "&knotStyle="+knotStyle;
	url += "&policyStyle="+policyStyle;
	url += "&remarks="+remarks;
	url += "&carryMode="+carryMode;
	url += "&orderTransMode="+orderTransMode;
	url += "&carryCompany="+carryCompany;
	url += "&delivaryPlace="+delivaryPlace;
	url += "&orderDepartureTime="+orderDepartureTime;
	url += "&arrivalDate="+arrivalDate;
	url += "&supply_code="+supply_code;
	url += "&supply_id="+supply_id;
	url += "&supply_fullname="+supply_fullname;
	url += "&draft="+draft;
	//验证当前的供方是否是选择状态
	if(supply_code==null || supply_code==""){
		util.error("请选择供方！");
		flag = false;
	}
	// 改用Ajax提交
	$.ajax({
		type : orderMethod, // 表单提交类型
		async: false,
		url : url, // 表单提交目标
		success : function(data) {
			if(data.result == 'success'){
				flag = true;
			}
		}
	});
	return flag;
}

// 保存小单
function submitPurOrderList(){
	// 验证小单是否可以提交
	var product_code = $("#product_code").attr("value");
	var unitprice = $("#unitprice").attr("value");
	var discount = $("#discount").attr("value");
	var quantity = $("#quantity").attr("value");
	if(product_code == null || product_code == ""){
		util.show("请选择产品！");
		return false;
	}
	if(unitprice==null || unitprice=="" || parseFloat($('#unitprice').attr("value"))==0){
		util.show("单价填写异常！");
		return false;
	}
	if(discount==null || discount=="" || parseInt($('#discount').attr("value"))<=0 || parseInt($('#discount').attr("value"))>100){
		util.show("折扣填写异常！");
		return false;
	}
	if(quantity==null || quantity=="" || parseFloat($('#quantity').attr("value"))==0){
		util.show("数量填写异常！");
		return false;
	}
	var purOrderListdata = util.submit('orderList_form');
	if(purOrderListdata.result == "success"){
		return true;
	}
	return false;
	
}

/*
 * 控件初始化
 * */
// 小单信息初始化
function initPurOrderList(){
	//各控件填写初始值
	$('#product_code').attr("value","");
	$('#product_id').attr("value","");
	$('#unitprice').attr("value","0.000000");
	$('#discount').attr("value","100");
	$('#settlementPrice').attr("value","0.000000");
	$('#package').attr("value","0");
	$('#packageNum').attr("value","0");
	$('#quantity').attr("value","0.00");
	$('#sum').attr("value","0.00");
	$('#prepaidForList').attr("value","0.00");
}


//按下 保存当前 按钮
function submitCurrent(){
	if(proInfo==null||$('#product_code').val()!=proInfo.product_code){
		util.show("产品编码不存在！");
		return;
	}
	//折扣验证
	if(Number($("#discount").val())>100){
		util.show("折扣不能大于100");
		return;
	}
	// 验证大单是否需要提交
	var purOrderListData = $('#dg_purOrderList').datagrid('getRows');
	// 如果产品列表为空，则可以提交大单信息
	if(purOrderListData.length == 0){
		var flagForPurOrder = submitPurOrder(1);//参数为是否存盘，0已存盘，1未存盘	
		if(!flagForPurOrder){
			util.show("订单大单保存失败！");
			return;//如果大单保存失败，则不保存小单，否则大单无法改变
		}
	}
	// 产品的小单信息一定会被提交，检验小单信息正确性
	submitPurOrderList();

	// 刷新产品列表
	reloadDg_PurOrderList();
	
	//重新聚焦
	$("#product_code").focus();
	
}

//按下 取消当前 按钮
function cancelCurrent(){
	//小单信息初始化
	initPurOrderList();
	//重新聚焦
	$("#product_code").focus();
}
//按下 保存全部 按钮
function submitAll(){
	$.messager.confirm("确认","提交大单会关闭页面，是否提交？",function(r){
		if(r){
			submitPurOrder(0);//参数为是否存盘，0已存盘，1未存盘	
			parent.$('#tt').tabs('close',"进货订单");//关闭进货订单页面
		}else{
			//小单-产品编号聚焦
			$("#product_code").focus();
		}
		
	});
	
}
//按下 取消全部 按钮
function cancelAll(){
	$.messager.confirm("确认","是否删除订单下所有产品？",function(r){
		if(r){
			var ticket_id = $("#ticket_id").attr("value");
			var url = global_param.context_name + "/purchase/order/deletePurOrderAll?ticket_id="+ticket_id;
			var data = util.get(url);
			if(data.result == "success"){
				util.show("删除成功");
			}
			// 刷新产品列表
			//reloadDg_PurOrderList();
			parent.$('#tt').tabs('close',"进货订单");//关闭进货订单页面
		}
	});
}

function initBindBlur(){
	
}
