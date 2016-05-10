// 控件ID组
var ctrlsID = ["input_member_card_id", "input_print_type",
		"input_product_code","input_order_product_quantity","input_order_product_amount",
		"health_insurance_combobox","input_health_insurance","input_retail_order_realIncomeAmount"
	];
//确认BUTTON
//var sumbitButtonID = "submit_current";

csID =  ["health_insurance_combobox","input_health_insurance","input_retail_order_realIncomeAmount"];

//键盘动作初始化
var keyPress = new pageKeyPress();

var health_insurance_number = 5;

//当前添加产品
var node = null;
//当前登陆用户
var user = null;
//当前价格格式(小数位数)
var priceDigit = 2;
//当前数量格式(小数位数)
var numberDigit = 2;
//获取所有库房信息
var depts = util.get(global_param.context_name + "/basInfo/basDepartmentInfo/getList");
//当前会员
var member = null;
//已经参加活动的产品行号
var all_join_rows = new Array();
//是否保存会员信息
var isSaveMember = true;


var dosageTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=98");
var packingunitTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=125");
var approvalname = util.getList(global_param.context_name + "/system/combobox/lists?pid=145");

$(function() {
	//载入用户习惯
	//var userHabits0 = new userHabits();
	//userHabits0.loadColumns("retailOrder","product_info_dg","editWindow1",false,true);
	
	
	//结款方式
	 $('#input_sale_clerk').combobox({
		 url : global_param.context_name + "/system/combobox/lists?pid=883",
			method :"get",
			valueField : "cbs_chn",
			textField : "cbs_chn",
			editable:false
		});
	 //送货方式
	 $('#wholeSale_order_departStyle').combobox({
		 url : global_param.context_name + "/system/combobox/lists?pid=883",
			method :"get",
			valueField : "cbs_chn",
			textField : "cbs_chn",
			editable:false
         });
	 
	 
	//为所有文本框添加  点击全选 事件
	$(".easyui-validatebox").click(function(){
		this.select();
	});
	
	//产品查询
	$('#input_product_code').bind('keydown', function() {
        var e = event || window.event;
        var keyCode = e.keyCode || e.which;
        var actElement = document.activeElement;
        //如果按键是enter并且当前焦点是产品编号，则弹出产品选择框
        if(keyCode == 13&&actElement.id == "input_product_code"){
        	//输入后，打开供方客户页面
     		var proContent = $('#input_product_code').attr("value");
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
     				content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/retail/order/viewProList?callbackPro=setProContent&proContent=' + proContent.replace("%", "%25") +'&dept_id='+user.depart_id+'&mem_card_discount='+$("#mem_card_discount").val()+'"></iframe>'
     			});
     			$('#proListWindow').window('open');
     		}else{
     			//如果输入为空，则开始结算所有产品
     			//市医保聚焦
     			keyPress.changeCtrlNumber(health_insurance_number);
     		}
        }
	});
	
	// 销方信息
	$("#sale_info_dg").datagrid({
		title : '销方客户信息管理', // 标题
		sortName : 'sal_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitcolumns : true,
		singleSelect : true,//单选
		fit : true,
		//triped : true,//奇偶行颜色不同
		rownumbers : true,
		nowrap : true,
		striped : true,
		queryParams : {},
		columns:[[{
			field : 'sal_code',
			title : '区号',
			width : 80,
			sortable : true
		}, {
			field : 'sal_customername',
			title : '客户全称',
			width : 100,
			sortable : true
		}, {
			field : 'sal_shortname',
			title : '信用额度',
			width : 80,
			sortable : true
		}, {
			field : 'sal_chnpy',
			title : '联系人',
			width : 80,
			sortable : true
		}, {
			field : 'sal_address',
			title : '业务员',
			width : 100,
			sortable : true
		}, {
			field : 'sal_contactperson',
			title : '电话',
			width : 80,
			sortable : true
		}, {
			field : 'sal_phone',
			title : '简称',
			width : 100,
			sortable : true
		}, {
			field : 'sal_telephone',
			title : '返点',
			width : 100,
			sortable : true
		}, {
			field : 'sal_fax',
			title : '销方编码',
			width : 100,
			sortable : true
		}, {
			field : 'sal_check',
			title : '所属部门',
			width : 80,
			sortable : true,
			//hidden: true,
			/*formatter : function(value, row, index){
				for(var i = 0 ; i < salCheckList.length ;i++){
					if(value == salCheckList[i].cbs_code){
						return salCheckList[i].cbs_chn;
					}
				}
				return "";	
			}*/
		},{
			field : 'sal_drugid',
			title : '性质',
			sortable : true,
			//hidden : true,
			/*formatter : function(value, row, index){
				if(null == value){
					return salLocalList[0].cbs_chn;
					value = 0;
				}else{
					return salLocalList[1].cbs_chn;
					value = 1;
				}
			}*/
		} , {
			field : 'sal_fax',
			title : '应收',
			width : 100,
			sortable : true
		}, {
			field : 'sal_fax',
			title : '客户欠款',
			width : 100,
			sortable : true
		}, {
			field : 'sal_fax',
			title : '手机',
			width : 100,
			sortable : true
		}, {
			field : 'sal_fax',
			title : '简介',
			width : 100,
			sortable : true
		}, {
			field : 'sal_fax',
			title : '执行价格',
			width : 100,
			sortable : true
		}, {
			field : 'sal_fax',
			title : '下浮率',
			width : 100,
			sortable : true
		}, {
			field : 'sal_fax',
			title : '结算方式',
			width : 100,
			sortable : true
		}, {
			field : 'sal_fax',
			title : '手续',
			width : 100,
			sortable : true
		}, {
			field : 'sal_fax',
			title : '经营范围',
			width : 100,
			sortable : true
		}, {
			field : 'sal_fax',
			title : '失效日期',
			width : 100,
			sortable : true
		}, {
			field : 'sal_fax',
			title : '业务员身份认证',
			width : 100,
			sortable : true
		}, {
			field : 'sal_fax',
			title : '结算价输入',
			width : 100,
			sortable : true
		}]]
	});
	
	
	
	//产品信息
	$("#product_info_dg").datagrid({
		title : '产品信息', // 标题
		idField : 'stock_id',
		sortName : 'stock_id', // 排序的列
		sortOrder : 'desc', // 顺序
		method : 'get',
		singleSelect : true,
		fitColumns : false,
		fit :true,
		nowrap : true,
		queryParams : {},
		columns : [ [{
			field : 'order_product_saleType',
			title : '产品序号',
			width : 80,
			sortable : true
		},{
			field : 'product_code',
			title : '货号',
			width : 80,
			sortable : true
		}, {
			field : 'product_barcode',
			title : '规格',
			width : 80,
			sortable : true
		}, {
			field : 'product_name',
			title : '产地',
			width : 100,
			sortable : true
		}, {
			field : 'product_specification',
			title : '单价',
			width : 100,
			sortable : true
		}, {
			field : 'product_factoryname',
			title : '市场价',
			width : 100,
			sortable : true
		}, {
			field : 'product_packingunit',
			title : '最后销价',
			width : 50,
			sortable : true,
		},  {
			field : 'product_packingamount',
			title : '赠品库',
			width : 50,
			sortable : true
		}, {
			field : 'order_product_unitPrice',
			title : '包装量',
			width : 80,
			sortable : true
		}, {
			field : 'order_product_memUnitPrice',
			title : '注册商标',
			width : 80,
			sortable : true
		}, {
			field : 'accept_Amount',
			title : '中包量1',
			width : 50,
			sortable : true
		}, {
			field : 'product_lastprice',
			title : '批准文号',
			width : 50,
			sortable : true
		}, {
			field : 'accept_Number',
			title : '库存数量',
			width : 80,
			sortable : true
		}, {
			field : 'stock_storageNumber',
			title : '件数',
			width : 80,
			sortable : true
		}, {
			field : 'number',
			title : '中包量',
			width : 50,
			sortable : true,
		}, {
			field : 'product_chnpy',
			title : '能开价',
			width : 80,
			sortable : true
		}, {
			field : 'product_amountFormat',
			title : '批发价',
			width : 80,
			sortable : true
		}, {
			field : 'product_numberFormat',
			title : '零售价',
			width : 80,
			sortable : true
		}, {
			field : 'product_id',
			title : '销售价1',
			width : 80,
			sortable : true
		}, {
			field : 'product_point',
			title : '销售价2',
			width : 50,
			sortable : true
		}, {
			field : 'stock_intakeSmallNumber',
			title : '销售价3',
			width : 100,
			sortable : true
		}, {
			field : 'product_retailcommission',
			title : '销售价4',
			width : 80,
			sortable : true
		}, {
			field : 'is_memberDay',
			title : '最低价',
			width : 50,
			sortable : true,
		}, {
			field : 'product_moneyPerPoint',
			title : '出库票号',
			width : 50,
			sortable : true
		}, {
			field : 'order_batch_pointMutiple',
			title : '默认数量',
			width : 50,
			sortable : true
		}, {
			field : 'order_product_unitPrice',
			title : '产品编号',
			width : 80,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '开票提成',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '开票提成',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '开票提成',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '开票提成',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '其他分类',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '其他分类2',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '缺货提示',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '简介',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '最后销售日期',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '中标价',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '规格',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '产地',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '单位',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '包装量',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '批准文号',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '注册商标',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '最后进价',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '批号',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '失效期',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '库房',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '库房数量',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '调拨库数量',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '调拨库',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '入库票号',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '入库小号',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '中包量',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '批发价',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '零售价',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '销售价1',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '最低价',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '批号小号',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '灭菌批号',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '生产日期',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '提成金额',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '促销费用',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '其他分类',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '其他分类2',
			width : 50,
			sortable : true
		}] ]
	});
	
	// 已销售产品
	$("#products_info_dg").datagrid({
		//url : global_param.context_name + "/medicine/sale/saleMedList", // 数据来源
		title : '销售信息', // 标题
		idField : 'sale_med_id',
		sortName : 'sale_med_id', // 排序的列
		sortOrder : 'dec', // 排序方式
		method : 'get',
		fit:true,
		//width:1500,
		fitColumns:false,
		singleSelect : true,
		nowrap : true,
		queryParams : {
		},
		columns : [ [ {
			field : 'order_product_saleType',
			title : '产品名称',
			width : 50,
			sortable : true
		},{
			field : 'stock_storage',
			title : '规格',
			width : 100,
			sortable : true,
		}, {
			field : 'order_productId',
			title : '产地',
			width : 50,
			sortable : true
		}, {
			field : 'order_batch_smallNumber',
			title : '库房',
			width : 100,
			sortable : true
		}, {
			field : 'product_barcode',
			title : '数量',
			width : 100,
			sortable : true
		}, {
			field : 'product_name',
			title : '单价',
			width : 100,
			sortable : true
		}, {
			field : 'product_specification',
			title : '金额',
			width : 100,
			sortable : true
		}, {
			field : 'product_specification',
			title : '批号',
			width : 100,
			sortable : true,
		}, {
			field : 'product_factoryname',
			title : '失效期',
			width : 100,
			sortable : true
		}, {
			field : 'product_approvalnum',
			title : '序号',
			width : 100,
			sortable : true
		}, {
			field : 'product_packingunit',
			title : '单位',
			width : 100,
			sortable : true,
			formatter :function(value, row, index){
				for(var i = 0 ; i < packingunitTypes.length ;i++){
					if(value == packingunitTypes[i].cbs_id){
						return packingunitTypes[i].cbs_chn;
					}
				}
				return value;
			} 
		}, {
			field : 'stock_batchCode',
			title : '产品序号',
			width : 50,
			sortable : true
		}, {
			field : 'number',
			title : '产品编号',
			width : 50,
			sortable : true
		},{
			field : 'discountedPrice',
			title : '包装量',
			width : 50,
			sortable : true
		}, {
			field : 'product_dsurveillanceid',
			title : '小号',
			width : 50,
			sortable : true
		}, {
			field : 'product_id',
			title : '销售价1',
			width : 50,
			sortable : true
		}, {
			field : 'order_product_unitPrice',
			title : '件数',
			width : 50,
			sortable : true
		}, {
			field : 'order_product_discountAmount',
			title : '减差价',
			width : 50,
			sortable : true
		}, {
			field : 'memDescCost',
			title : '批发价',
			width : 50,
			sortable : true
		}, {
			field : 'order_product_amount',
			title : '结算价',
			width : 50,
			sortable : true
		}, {
			field : 'product_saleprice1',
			title : '折后单价',
			width : 50,
			sortable : true
		}, {
			field : 'order_batch_pointMutiple',
			title : '折前单价',
			width : 50,
			sortable : true
		}] ],
		onLoadSuccess:function(data){
			if(data.total>0){
				isSaveMember = false;
				document.getElementById("input_member_card_id").readOnly="readOnly";
			}else{
				document.getElementById("input_member_card_id").readOnly="";
			}
				
		}
	});
	
	
	util.window('setDiscountWindow', {
		title : '店长打折授权',
		width : 350,
		height : 250
	});
	util.window('specialMedCheck', {
		title : '特殊药品购买信息',
		width : 450,
		height : 250
	});
	
	//无数据显示滚动条
	scrollShow($("#product_info_dg"));
	scrollShow($("#products_info_dg"));
	scrollShow($("#sale_info_dg"));
	
	
	//设置草稿
	/*var result = util.get(global_param.context_name+"/retail/order/checkDraft");
	if(result == null || result == ""){
		alert("数据异常：当前操作员出现多条未完成的订单记录，窗口即将关闭");
		parent.$('#tt').tabs('close',"零售销售");
	}else if(result.retail_order_ticketId !=null &&result.retail_order_ticketId !=""){
		$("#input_retail_order_ticketId").val(result.retail_order_ticketId);
		$("#retail_order_ticketId").val(result.retail_order_ticketId);
		//无数据时显示滚动条
		scrollShow($("#sale_info_dg"));
	}else{
		$("#input_retail_order_ticketId").val(result.listvo[0].retail_order_ticketId);
		$("#retail_order_ticketId").val(result.listvo[0].retail_order_ticketId);
		$("#products_info_dg").datagrid("loadData",result.listvo);
		$("#input_member_card_id").val(result.listvo[0].mem_card_id);//会员卡赋值
		//获取会员信息
		getMemberInfo(result.listvo[0].mem_card_id);
		
		for(var i = 0; i <result.listvo.length;i++){
			//更新金额信息等
			updateCost(result.listvo[i],i);
		}
	}
	
	
	
	
	//绑定产品键盘动作
	//keyPress.bindKeyPressToCtrl(ctrlsID);
	
	//绑定按键事件
	bindkeyDown();
	
	//关闭销售页面时删除订单
	parent.$('#tt').tabs({
		onBeforeClose : function(title,index) {
			var sysMenu = util.get(global_param.context_name+"/system/menu/check?id="+131);
			if(title == sysMenu.menu_name){
				var result =  confirm('关闭销售页面将删除当前订单，确定？');
				if (result){
					util.get(global_param.context_name+"/retail/order/deleteByTicketId?ticketId="+$("#input_retail_order_ticketId").val());
					return true;
				}else{
					return false;
				}
			}
		}
	});*/


});




function submitOrder() {
	var data = util.submit('form_cust');
	if (data) {
		if (data.result == "success") {
			util.show(data.message);
		} else {
			util.error(data.message);
		}
	}
	
	document.location.reload();//重新载入整个页面
	
}
//提交整体订单准备阶段
function prepareSubmitOrder(){
	util.confirm("确定要提交整个销售订单？", function() {
		var meds = $("#products_info_dg").datagrid("getData");
		var flag = false;
		
		for(var i=0;i<meds.rows.length;i++){
			if(meds.rows[i].product_specialvariety==1){
				flag = true;
				break;
			}
		}
		//特殊药品检测
		if(flag)
			util.openWindow('specialMedCheck', global_param.context_name + "/retail/order/specialMedCheck");
		else
			submitOrder();
	});
}	




//提交药品信息
function submitMed() {
	//数量验证
	if(!/^[0-9]+\.{0,1}[0-9]{0,6}$/i.test($("#input_order_product_quantity").val())){
		$("#input_order_product_quantity").val("1.00");
		$("#input_order_product_amount").val("");
		util.show("数量错误!");
		return;
	}
	//折后价验证
	if(!/^[0-9]+\.{0,1}[0-9]{0,6}$/i.test($("#input_discountedPrice").val())){
		$("#input_discountedPrice").val($("#input_order_product_unitPrice").val());
		$("#input_order_product_quantity").val("1.00");
		$("#input_order_product_amount").val("");
		keyPress.changeCtrlNumber(3);//折后价聚焦
		util.show("折后价错误!");
		return;
	}
	
	//数据验证
	if($("#input_product_code").val()==""||node==null||$("#input_product_code").val()!=node.product_code){
		util.show("产品编号错误");
		$("#input_product_code").focus();
		return;
	}
	//比较折后价和能开价
	if(Number($("#input_discountedPrice").val())<node.accept_Amount){
		util.show("折扣价不能低于能开!");
		return;
	}
	//比较库存数量和需要销售的数量
	if(Number($("#input_order_product_quantity").val())>node.stock_storageNumber){
		util.show("库存数量不足!");
		return;
	}
	//主单打折，代金卡，积分当钱数据验证
	if(!checkPreferential())
		return;
	
	
	//数量赋值
	node.number = $("#input_order_product_quantity").val();//数量
	node.order_product_amount = $("#input_order_product_amount").val();//金额
	node.discountedPrice = $("#input_discountedPrice").val();//折后价
	//折让 = 单价 - 折后价
	node.order_product_discountAmount = Number($("#input_order_product_unitPrice").val())-Number($("#input_discountedPrice").val());
	node.retail_order_ticketId = $("#input_retail_order_ticketId").val();//票号
	
	node.discountPerson = $("#discountPerson").val();	//打折授权人
	node.discountReason = $("#discountReason").val();	//打折原因
	node.mem_card_id = $("#input_member_card_id").val();	//会员卡号
	node.order_batch_fullGiftAmount = node.order_product_amount;	//满额赠金额
	node.order_batch_fullGiftExchange = 0;	//兑换满额赠金额
	node.order_product_saleType = "零售";
	
	//积分计算
	if(member!=null){
		//会员是否积分
		if(node.product_mbernointegral==0){//积分倍数*折后价*计分金额
			node.point = (node.order_batch_pointMutiple*node.discountedPrice*member.mem_card_acountMoney).toFixed(0);
		}else
			node.point = 0;
	}else
		node.point = 0;
	
	//删除此属性，用于分页的属性(否则后台json对象装换出错)
	delete node["offset"];
	
	
	/*//指纹验证数据准备
	//参数声明
	var ticket_id = $("#input_retail_order_ticketId").val();//销售单号
	var saleDate = "";//销售日期
	var approvalname = "";//批准文号
	var procuct_commonname = node.product_commonname;//通用名
	var product_dsurveillanceid = node.product_dsurveillanceid;//药监ID
	var dosageTypes = "";//药品剂型
	var product_specification = node.product_specification;//药品规格
	var packingunitTypes = "";//药品单位
	var number = node.number;//数量
	var unitPrice = node.discountedPrice;//价格
	
	
	//时间转换
	var currentDate = new Date();
	year = currentDate.getFullYear();
	month = currentDate.getMonth() + 1; 
	day = currentDate.getDate(); 
	month =month <10?"0"+month :month;
	day=day<10?"0"+day:day;
	saleDate = year+"-"+month+"-"+day;
	
	
	//添加批准文号前缀
	for(var i = 0 ; i < approvalname.length ;i++){
		if(node.product_approvalname == approvalname[i].cbs_id){
			approvalname = approvalname[i].cbs_chn;
		}
	}
	approvalname = approvalname + node.product_approvalnum;//批准文号后面数字
	
	//添加剂型
	for(var i = 0 ; i < dosageTypes.length ;i++){
		if(node.product_dosagetype == dosageTypes[i].cbs_id){
			dosageTypes = dosageTypes[i].cbs_chn;
		}
	}
	//添加单位
	for(var i = 0 ; i < packingunitTypes.length ;i++){
		if(node.product_packingunit == packingunitTypes[i].cbs_id){
			packingunitTypes = packingunitTypes[i].cbs_chn;
		}
	}
	
	var objCard = document.getElementById("callDllFile");
	
	
	alert(ticket_id+"+"+saleDate+"+"+approvalname+"+"+procuct_commonname+"+"+product_dsurveillanceid+"+"+
			dosageTypes+"+"+product_specification+"+"+packingunitTypes+"+"+number+"+"+unitPrice);
	
	//指纹验证
	alert(objCard.GetVer());
	objCard.YsdaInit(3,0);//初始化
	objCard.CheckRx2(ticket_id,saleDate,approvalname,procuct_commonname,product_dsurveillanceid,dosageTypes,
			product_specification,packingunitTypes,number,unitPrice);
	
	alert("result"+objCard.YsdaCheck());*/
	
	//销售药品
	saleProduct();
	
}



// 清空药品信息
function clearMed() {
	var ctrlsForMed = [ "input_product_code", "input_order_product_unitPrice",
			"input_product_discount", "input_discountedPrice", "input_order_product_quantity",
			"input_order_product_amount"];
	
	for(var i = 0 ; i < ctrlsForMed.length ; i++){
		$("#"+ctrlsForMed[i]).attr("value","");
		if(ctrlsForMed[i] == "input_product_discount" ){
			$("#"+ctrlsForMed[i]).attr("value",Number(100).toFixed(priceDigit));
		}
		else if(ctrlsForMed[i] == "input_order_product_quantity"){
			$("#"+ctrlsForMed[i]).attr("value",Number(1).toFixed(numberDigit));
		}
	}
	//产品聚焦
	$("#input_product_code").focus();
}

//回调选择批次
function setProContent(nodes){
	this.node = nodes[0];//取第一个数据(有且只有一条数据)
	
	$("#input_product_code").val(node.product_code);
	$("#product_info_dg").datagrid("loadData",{total:1,rows:[node]});
	
	//当前价格格式(小数位数)
	priceDigit = (node.product_amountFormat.split(".")[1].split("0")).length-1;
	//当前数量格式(小数位数)
	numberDigit = (node.product_numberFormat.split(".")[1].split("0")).length-1;
	
	//写入价格
	$("#input_order_product_unitPrice").val(node.order_product_memUnitPrice);
	//重置数量
	$("#input_order_product_quantity").val(Number($("#input_order_product_quantity").val()).toFixed(numberDigit));
	//重置折扣
	$("#input_product_discount").val(Number($("#input_product_discount").val()).toFixed(priceDigit));
	
	
	//修改折后价
	$("#input_discountedPrice").val((Number($("#input_order_product_unitPrice").val())*Number($("#input_product_discount").val())/100).toFixed(priceDigit));
	//修改金额
	$("#input_order_product_amount").val((Number($("#input_discountedPrice").val())*Number($("#input_order_product_quantity").val())).toFixed(priceDigit));
	
	$("#input_stock_info_id").val(node.stock_info_id);//选择的库存批次
	
	//修改折扣是否可改
	if(node.discount_modify==1){
		document.getElementById("input_discountedPrice").disabled="";
		//从第三个位置删除一个元素,然后跳转到
		ctrlsID.splice(3,0,"input_discountedPrice");
		health_insurance_number++;
		keyPress.bindKeyPressToCtrl(ctrlsID);//重新加载按键序列
		$("#input_discountedPrice").focus();
	}else{
		$("#input_order_product_quantity").focus();
	}
	
	//弹出买赠活动
	chooseGiftSale();
}

function bindkeyDown(){
	var IDs = ["input_retail_order_otherCharge","input_sale_cust_billDiscount"
	           ,"input_retail_order_giftCard","input_retail_order_pointPay"];
	
	//绑定事件
	for(var i=0;i<IDs.length;i++){
		$('#'+IDs[i]).bind('keydown', function() {
			var e = event || window.event;
	        var keyCode = e.keyCode || e.which;
	        var actElement = document.activeElement;
	        if(actElement.id == this.id){
	        	if(keyCode == 13){//按确认键则直接跳转到市医保
	        		if(checkPreferential()){
	        			//更新应收金额
		        		var receive_cost = Number($("#input_retail_order_settlementAmount").val())+
		        			Number($("#input_retail_order_otherCharge").val()) - Number($("#input_sale_cust_billDiscount").val()) -
		        			Number($("#input_retail_order_giftCard").val()) - Number($("#input_retail_order_pointPay").val()) - 
		        			Number($("#input_retail_order_wipeZero").val()) ;
		        		$("#input_retail_order_receivableAmount").val(receive_cost);
		        		//跳转到市医保
		        		keyPress.changeCtrlNumber(health_insurance_number);
	        		}
	        	}else if(keyCode==40){
	        		//按向下键
	        		for(var j =0;j<IDs.length;j++){
	        			if(IDs[j]==this.id){
	        				$("#"+IDs[j<IDs.length-1?j+1:j]).focus();
	        				//$("#"+IDs[j<IDs.length-1?j+1:j]).select();//暂时无效，原因不详
	        			}
	        		}
	        	}else if(keyCode==38){
	        		//按向上键
	        		for(var j =0;j<IDs.length;j++){
	        			if(IDs[j]==this.id){
	        				$("#"+IDs[j>0?j-1:j]).focus();
	        				document.getElementById(IDs[j>0?j-1:j]).select();
	        				//$("#"+IDs[j>0?j-1:j]).select();
	        			}
	        				
	        		}
	        	}
	        }
		});
	}
}


function modifyDiscount(){
	if($("#input_product_code").val()==""){
		util.show("请先输入产品!");
		return;
	}
	//数据验证
	if($("#input_product_code").val()!=node.product_code){
		util.show("产品编号错误");
		$("#input_product_code").focus();
		return;
	}
	util.openWindow('setDiscountWindow', global_param.context_name + "/retail/order/setDiscount");
}

//无数据时显示滚动条
function scrollShow(datagrid) {  
    datagrid.prev(".datagrid-view2").children(".datagrid-body").html("<div style='width:" + datagrid.prev(".datagrid-view2").find(".datagrid-header-row").width() + "px;border:solid 0px;height:1px;'></div>");  
} 
//选择满额赠活动
function chooseFullFillGift(){
	//勾选活动信息才跳转到活动页面
	if(document.getElementById("is_choose_promotion").checked){
		$("#fullFillGift").window({
			title : '产品选择',
			width : 800,
			height : 600,
			shadow : true,
			modal : true,
			closed : true,
			minimizable : false,	
			maximizable : false,
			collapsible : false,
			resizable : false,
			content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/retail/order/fullFillGift"></iframe>',
			onBeforeClose:function(){
				//产品聚焦
				$("#input_product_code").focus();
			}
		});
		$('#fullFillGift').window('open');
	}
}
//选择买赠活动
function chooseGiftSale(){
	//勾选活动信息才跳转到活动页面
	if(document.getElementById("is_choose_promotion").checked){
		$("#giftSale").window({
				title : '产品选择',
				width : 800,
				height : 600,
				shadow : true,
				modal : true,
				closed : true,
				minimizable : false,	
				maximizable : false,
				collapsible : false,
				resizable : false,
				content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/retail/order/giftSale"></iframe>',
				onBeforeClose:function(){
					//如果折扣可改，则聚焦折扣
					if(node!=null&&node.discount_modify==1)
						$("#input_discountedPrice").focus();//折扣聚焦
					else
						$("#input_order_product_quantity").focus();//数量聚焦
				}
		});
		$('#giftSale').window('open');
	}
}


function updateCost(node,index){
	//计算单付金额，总金额，应收金额,单品打折，本次积分
	$("#input_retail_order_SingleDoseAmount").val(Number($("#input_retail_order_SingleDoseAmount").val())+
			node.order_product_amount);
	$("#input_retail_order_settlementAmount").val(Number($("#input_retail_order_SingleDoseAmount").val())*
			$("#input_retail_order_doseNumber").val());
	
	
	//应收金额
	var receive_cost = Number($("#input_retail_order_settlementAmount").val())+
		Number($("#input_retail_order_otherCharge").val()) - Number($("#input_sale_cust_billDiscount").val()) -
		Number($("#input_retail_order_giftCard").val()) - Number($("#input_retail_order_pointPay").val()) - 
		Number($("#input_retail_order_wipeZero").val()) ;
	$("#input_retail_order_receivableAmount").val(receive_cost);
	
	
	//单品打折
	$("#input_sale_cust_productOff").val((Number($("#input_sale_cust_productOff").val())+(node.order_product_unitPrice-node.discountedPrice)*node.number).toFixed(2));
	
	//计算现金
	$("#input_retail_order_cash").val((Number($("#input_retail_order_receivableAmount").val())-Number($("#input_health_insurance").val())).toFixed(1));
	
	//计算积分
	if(member!=null){
		var point = ((Number($("#input_retail_order_point").val()) + Number($("#input_retail_order_receivableAmount").val())*
				node.order_batch_pointMutiple*node.product_moneyPerPoint)).toFixed(0);
		$("#input_retail_order_point").val(point);
	}
	
	//计算满赠金额
	$("#input_retail_order_fullGiftAmount").val(Number($("#input_retail_order_fullGiftAmount").val())+Number(node.order_batch_fullGiftAmount));
	
	//如果兑换金额不为0,则是已参加活动产品
	if(node.order_batch_fullGiftExchange>0){
		var data = $("#products_info_dg").datagrid("getData");
		if(index=="undefined"||index==null)
			all_join_rows.push(data.rows.length-1);
		else
			all_join_rows.push(index);
	}
	//兑换金额
	$("#input_retail_order_fullGiftExchange").val(Number($("#input_retail_order_fullGiftExchange").val())+Number(node.order_batch_fullGiftExchange));
	
	//未兑金额
	$("#input_retail_order_fullGiftRemain").val(Number($("#input_retail_order_fullGiftAmount").val())-Number($("#input_retail_order_fullGiftExchange").val()));
	
	
	//更新代金卡金额
	var giftCard = util.get(global_param.context_name + "/giftCard/giftCardManage/getOffsetAmount?department_id="+user.depart_id+"&Amount="+$("#input_retail_order_settlementAmount").val());
	if(giftCard.result == "success"){
		$("#input_retail_order_giftCardMax").val(giftCard.gift_amount);
	}

	
}
	
function deleteBatchFunc() {
	var node = $('#products_info_dg').datagrid('getSelected');
	
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/retail/order/" + node.order_batch_id);
		if (data.result == "success") {
			$('#products_info_dg').datagrid('deleteRow',$('#products_info_dg').datagrid('getRowIndex',node));
		}
		util.show(data.message);
	});

}
//删除整个订单
function deleteOrder(){
	$.messager.confirm('删除订单', '您确定要删除整个订单？', function(r){
		if (r){
			util.get(global_param.context_name+"/retail/order/deleteByTicketId?ticketId="+$("#input_retail_order_ticketId").val());
			document.location.reload();//重新载入整个页面
		}
	});
	
}
function saleProduct(){
	//ajax提交销售信息
	var data = util.ajax(global_param.context_name + "/retail/order/saveRetail",{"data":JSON.stringify(node)});
	if(data.result == "failure"){
		util.show(data.message);
	}else{
		util.show(data.message);

		for(var i = 0; i <data.bean.length;i++){
			$("#products_info_dg").datagrid("appendRow",data.bean[i]);
			$("#product_info_dg").datagrid("deleteRow",0);
			//更新金额信息等
			updateCost(data.bean[i]);
		}
		//满额赠活动选择
		chooseFullFillGift();
	}
	
	//清空药品信息
	clearMed();
}
//代金卡数据检查
function checkPreferential(){
	if(!/^[0-9]+\.{0,1}[0-9]{0,2}$/i.test($("#input_sale_cust_billDiscount").val())){
		util.show("主单打折数据格式错误");
		return false;
	}
	if(!/^[0-9]+\.{0,1}[0-9]{0,2}$/i.test($("#input_retail_order_giftCard").val())){
		util.show("代金卡数据格式错误");
		return false;
	}
	if(!/^[0-9]+\.{0,1}[0-9]{0,2}$/i.test($("#input_retail_order_pointPay").val())){
		util.show("积分当钱数据格式错误");
		return false;
	}
	if(Number($("#input_sale_cust_billDiscount").val())>Number($("#sale_cust_billDiscountMax").val())){
		util.show("主单打折不能超过上限");
		return false;
	}
	if(Number($("#input_retail_order_giftCard").val())>Number($("#input_retail_order_giftCardMax").val())){
		util.show("代金卡不能超过上限");
		return false;
	}
	if(Number($("#input_retail_order_pointPay").val())>Number($("#input_retail_order_pointPayMax").val())){
		util.show("积分当钱不能超过上限");
		return false;
	}
	return true;
}
//更新大单会员及满额赠信息
function updateOrderInfo(){
	var url  = global_param.context_name + "/retail/order/updateOrder";
	url = url + "?retail_order_fullGiftAmount="+$("#input_retail_order_fullGiftAmount").val();//满额赠金额
	url = url + "&input_retail_order_fullGiftExchange="+$("#retail_order_fullGiftExchange").val();//兑换金额
	url = url + "&input_retail_order_fullGiftRemain="+$("#retail_order_fullGiftRemain").val();//未兑金额
	
	//提交会员信息
	if(isSaveMember)
		url = url + "&mem_card_id="+ (member==null?"":member.mem_card_number);//会员卡号
	
	url = url +"&retail_order_ticketId="+$("#input_retail_order_ticketId").val();
	
	var result = util.get(url);
	if(result.reuslt=="failure"){
		util.show("临时保存大单信息失败");
	}
}
//更新已卖产品兑换金额
function updateSaledMed(ids,ticket_id){
	var result = util.get(global_param.context_name + "/retail/batch/updateOrderBatch?ids="+ids+"&ticket_id="+ticket_id);
	if(result.reuslt=="failure"){
		util.show("更新已卖产品兑换金额失败");
	}
}

function getMemberInfo(mem_card_number){
	if(mem_card_number!=null && mem_card_number!=""){
		member = util.get(global_param.context_name + "/member/memCardManage/getMemCardByNumber?mem_card_number="+mem_card_number);
		if(member==null||member==""){
			util.show("暂无会员卡信息");
		}else{
			//会员卡信息显示
			$('#input_member_card_type').combobox("setValue",member.mem_card_type);
			$("#input_member_card_name").val(member.mem_card_name);
			$("#input_member_card_remainPoint").val(member.mem_card_point-member.mem_card_usedPoint);
			$("#input_member_card_tel").val(member.mem_card_tel);
			$("#mem_card_discount").val(member.mem_card_discount);
			//添加隐藏框，存储会员卡号，提交
			$("#mem_card_id").val(mem_card_number);
			
			
			//计算积分当钱最大
		$("#input_retail_order_pointPayMax").val(member.mem_card_point * member.mem_point_toMoney.toFixed(0));
		
		}
	}else{
		//如果输入为空，则表示清空会员信息
		$('#input_member_card_type').combobox('clear');//清空下拉框
		$("#input_member_card_name").val("");
		$("#input_member_card_remainPoint").val("");
		$("#input_member_card_tel").val("");
		$("#mem_card_discount").val("");
		member = null;//清空会员对象
	}
	//保存大单会员卡满额赠信息
	//updateOrderInfo();
}
