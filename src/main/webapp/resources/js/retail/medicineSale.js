// 控件ID组
var ctrlsID = ["input_member_card_id", 
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
	$("#input_member_card_id").focus();

	//为所有文本框添加  点击全选 事件
	$(".easyui-validatebox").click(function(){
		this.select();
	});
	
	// 如果数量改变，则计算总金额
	$('#input_order_product_quantity').bind('keydown', function() {
        var e = event || window.event;
        var keyCode = e.keyCode || e.which;
        var actElement = document.activeElement;
        //如果按键是enter并且当前焦点是数量，则修改数量和金额
        if(keyCode == 13&&actElement.id == "input_order_product_quantity"){
        	//如果输入负数，则表示退货
        	if(Number($('#input_order_product_quantity').val())<0){
        		$.messager.prompt("退货", "请输入零售批号:", function(value){
        			returnGoods(value);
        		});
        	}else{
        		//重置数量
        		$("#input_order_product_quantity").val(Number($("#input_order_product_quantity").val()).toFixed(numberDigit));
        		//重置金额
        		$("#input_order_product_amount").val((Number($("#input_order_product_quantity").val())* Number($("#input_discountedPrice").val())).toFixed(priceDigit));
        		//提交小单
        		submitMed();
        	}
        }
	});
	
	// 如果输入市医保
	$("#input_health_insurance").bind("blur",function() {
		if(Number($("#input_health_insurance").val())-Number($("#input_retail_order_receivableAmount").val())>0){
			util.show("市医保不能大于应收金额!");
			return;
		}else{
			
			var cash = Number($("#input_retail_order_receivableAmount").val())-Number($("#input_health_insurance").val());
			//重置现金
			$("#input_retail_order_cash").val((cash).toFixed(1));
			//重置抹零
			$("#input_retail_order_wipeZero").val((cash - (cash).toFixed(1)).toFixed(2));
			//实收现金聚焦
			$("#input_retail_order_realIncomeAmount").focus();
		}
	});
	
	// 实收金额事件监听
	$('#input_retail_order_realIncomeAmount').bind('keydown', function() {
        var e = event || window.event;
        var keyCode = e.keyCode || e.which;
        var actElement = document.activeElement;
        //如果按键是enter并且当前焦点是实收金额，则修改找零
        if(keyCode == 13&&actElement.id == "input_retail_order_realIncomeAmount"){
        	if(Number($("#input_retail_order_realIncomeAmount").val())-Number($("#input_retail_order_cash").val())<0){
    			util.show("实收现金少于现金!");
    			return;
    		}else{
    			//重置找零
    			$("#input_retail_order_change").val((Number($("#input_retail_order_realIncomeAmount").val())-Number($("#input_retail_order_cash").val())).toFixed(1));
    		}
        	//提交订单
        	prepareSubmitOrder();
        }
	});
	
	//医保类别
	$('#health_insurance_combobox').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=990",
		method :"get",
		valueField : "cbs_id",
		textField : "cbs_chn",
		value:991//默认选中市医保
	});

	//获取并显示营业员
	user = util.get(global_param.context_name + "/userinfo");
	$("#input_sale_clerk").val(user.display_name);
	
	//会员类别
	$('#input_member_card_type').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=51",
		method :"get",
		valueField : "cbs_id",
		textField : "cbs_chn"
	});
	
	//打印格式
	$('#input_print_type').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=1009",
		method :"get",
		valueField : "cbs_code",
		textField : "cbs_chn",
		editable:false,
	});
	
	//产品查询
	$('#input_product_code').bind('keydown', function() {
        var e = event || window.event;
        var keyCode = e.keyCode || e.which;
        var actElement = document.activeElement;
        //如果按键是enter并且当前焦点是产品编号，则弹出产品选择框
        if(keyCode == 13&&actElement.id == "input_product_code"){
        	//输入后，打开产品选择页面
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
	
	//会员卡卡号查询
	$('#input_member_card_id').bind('keydown', function() {
        var e = event || window.event;
        var keyCode = e.keyCode || e.which;
        var actElement = document.activeElement;
        //如果按键是enter并且当前焦点是会员卡号，则查找会员信息
        if(keyCode == 13&&actElement.id == "input_member_card_id"){
        	//输入后，获取会员卡号信息
     		var mem_card_number = $('#input_member_card_id').attr("value");
     		getMemberInfo(mem_card_number);
        }
	});
	
	
	//付数改变
	$('#input_retail_order_doseNumber').bind('keydown', function() {
        var e = event || window.event;
        var keyCode = e.keyCode || e.which;
        var actElement = document.activeElement;
        //如果按键是enter并且当前焦点是付数
        if(keyCode == 13&&actElement.id == "input_retail_order_doseNumber"){
        	
        	//判断付数能否满足
        	var maxDoseNumber = $("#input_retail_order_doseNumber").val();	//最大可满足的付数
        	for(var i =0; i <$("#products_info_dg").datagrid("getRows").length; i++){
        		var data = $("#products_info_dg").datagrid("getRows")[i];
        		if(maxDoseNumber * data.number > data.accept_Number){
        			maxDoseNumber = parseInt(data.accept_Number/data.number);
        		}
        	}
        	if(maxDoseNumber!=$("#input_retail_order_doseNumber").val()){
        		alert("当前最大可用的付数为："+maxDoseNumber);
        		$("#input_retail_order_doseNumber").val(maxDoseNumber);
        	}
        	
        	//修改总金额,应收金额
        	$("#input_retail_order_settlementAmount").val(Number($("#input_retail_order_SingleDoseAmount").val())*
        			Number($("#input_retail_order_doseNumber").val()));
        	
        	//应收金额
        	var receive_cost = Number($("#input_retail_order_settlementAmount").val())+
        		Number($("#input_retail_order_otherCharge").val()) - Number($("#input_sale_cust_billDiscount").val()) -
        		Number($("#input_retail_order_giftCard").val()) - Number($("#input_retail_order_pointPay").val()) - 
        		Number($("#input_retail_order_wipeZero").val()) ;
        	$("#input_retail_order_receivableAmount").val(receive_cost);
        	
        	//计算现金
        	$("#input_retail_order_cash").val((Number($("#input_retail_order_receivableAmount").val())-Number($("#input_health_insurance").val())).toFixed(1));
        	
        	//计算积分
        	$("#input_retail_order_point").val((Number($("#input_retail_order_point").val())*Number($("#input_retail_order_doseNumber").val())).toFixed(1));
        	
        	//还需要修改现金等
        	
        	//计算满额赠金额
        	$("#input_retail_order_fullGiftAmount").val(Number($("#input_retail_order_fullGiftAmount").val())*Number($("#input_retail_order_doseNumber").val()));
        	//未兑金额
        	$("#input_retail_order_fullGiftRemain").val(Number($("#input_retail_order_fullGiftAmount").val())-Number($("#input_retail_order_fullGiftExchange").val()));

        	
        	
        	//市医保聚焦
        	keyPress.changeCtrlNumber(health_insurance_number);
        }
	});
	
	
	//获取零售系统参数
	var list = util.get(global_param.context_name + "/system/parameter/getParamCode?param="+"RETAIL_SALE");
	for(var i=0;i<list.data.length;i++){
		//如果是主单打折
		if(list.data[i].param_sub_id==7){
			//主单打折上限赋值
			$("#sale_cust_billDiscountMax").val(list.data[i].param_sub_value);
		}
	}
	
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
			title : '销售类型',
			width : 50,
			sortable : true
		},{
			field : 'stock_storage',
			title : '库房',
			width : 100,
			sortable : true,
			formatter :function(value, row, index){
				for(var i=0;i<depts.length;i++){
					if(value = depts[i].department_id)
						return depts[i].department_fullname;
				}
			}
		}, {
			field : 'order_productId',
			title : '序号',
			width : 50,
			sortable : true
		}, {
			field : 'order_batch_smallNumber',
			title : '小号',
			width : 100,
			sortable : true
		}, {
			field : 'product_barcode',
			title : '产品条码',
			width : 100,
			sortable : true
		}, {
			field : 'product_name',
			title : '产品名称',
			width : 100,
			sortable : true
		}, {
			field : 'product_specification',
			title : '规格',
			width : 100,
			sortable : true
		}, {
			field : 'product_specification',
			title : '剂型',
			width : 100,
			sortable : true,
			formatter :function(value, row, index){
				for(var i = 0 ; i < dosageTypes.length ;i++){
					if(value == dosageTypes[i].cbs_id){
						return dosageTypes[i].cbs_chn;
					}
				}
			} 
		}, {
			field : 'product_factoryname',
			title : '生产厂家',
			width : 100,
			sortable : true
		}, {
			field : 'product_approvalnum',
			title : '批准文号',
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
			title : '批号',
			width : 50,
			sortable : true
		}, {
			field : 'number',
			title : '数量',
			width : 50,
			sortable : true
		},/* {
			field : '',
			title : '满额赠兑换金额',
			width : 50,
			sortable : true
		}, */{
			field : 'discountedPrice',
			title : '折后价',
			width : 50,
			sortable : true
		}, {
			field : 'product_dsurveillanceid',
			title : '药监ID',
			width : 50,
			sortable : true
		}, {
			field : 'product_id',
			title : '产品序号',
			width : 50,
			sortable : true
		}, {
			field : 'order_product_unitPrice',
			title : '单价',
			width : 50,
			sortable : true
		}, {
			field : 'order_product_discountAmount',
			title : '折让单价',
			width : 50,
			sortable : true
		}, {
			field : 'memDescCost',
			title : '减差价',
			width : 50,
			sortable : true
		}, {
			field : 'order_product_amount',
			title : '金额',
			width : 50,
			sortable : true
		}, {
			field : 'product_saleprice1',
			title : '销售价1',
			width : 50,
			sortable : true
		}, {
			field : 'order_batch_pointMutiple',
			title : '积分倍数',
			width : 50,
			sortable : true
		}, {
			field : 'order_batch_id',
			title : '出库小号',
			width : 50,
			sortable : true
		}, {
			field : 'retail_order_ticketId',
			title : '票号',
			width : 50,
			sortable : true
		}, {
			field : 'product_medinsuvariety',
			title : '医保品种',
			width : 50,
			sortable : true
		}, {
			field : 'stock_invalidDate',
			title : '失效期',
			width : 50,
			sortable : true,
			formatter:function(value, row, index){
				return util.formatDate(value);
			}
		}, /*{			
			//field : 'sale_med_saleDate',
			title : '销售日期',
			width : 50,
			sortable : true,
			formatter:function(){
				return new Date();
			}
		},*/{
			field : 'order_product_saleId',
			title : '销售序号',
			width : 50,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '拆零组数',
			width : 50,
			sortable : true
		} , {
			field : 'procedure1',
			title : '手续1',
			width : 50,
			sortable : true,
			formatter:function(){
				return 1;
			}
		}, {
			field : 'procedure2',
			title : '手续2',
			width : 50,
			sortable : true,
			formatter:function(){
				return 1;
			}
		}, {
			field : 'procedure3',
			title : '手续3',
			width : 50,
			sortable : true,
			formatter:function(){
				return 1;
			}
		}, {
			field : 'product_specialvariety',
			title : '特殊品种',
			width : 50,
			sortable : true
		}, {
			field : 'product_prescriptiondrug',
			title : '处方药',
			width : 50,
			sortable : true
		}] ],toolbar : [{
			text : '删除',
			iconCls : 'icon-delete',
			handler : deleteBatchFunc
		},{
			text : '买赠',
			iconCls : 'icon-add',
			handler : chooseGiftSale
		} ,{
			text : '满额赠',
			iconCls : 'icon-add',
			handler : chooseFullFillGift
		}  ],
		onLoadSuccess:function(data){
			if(data.total>0){
				isSaveMember = false;
				document.getElementById("input_member_card_id").readOnly="readOnly";
			}else{
				document.getElementById("input_member_card_id").readOnly="";
			}
				
		}
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
			title : '销售类型',
			width : 80,
			sortable : true
		},{
			field : 'product_code',
			title : '产品编号',
			width : 80,
			sortable : true
		}, {
			field : 'product_barcode',
			title : '产品条码',
			width : 80,
			sortable : true
		}, {
			field : 'product_name',
			title : '产品名称',
			width : 100,
			sortable : true
		}, {
			field : 'product_specification',
			title : '产品规格',
			width : 100,
			sortable : true
		}, {
			field : 'product_factoryname',
			title : '生产厂家',
			width : 100,
			sortable : true
		}, {
			field : 'product_packingunit',
			title : '包装单位',
			width : 50,
			sortable : true,
		 	formatter :function(value, row, index){
				for(var i = 0 ; i < packingunitTypes.length ;i++){
					if(value == packingunitTypes[i].cbs_id){
						return packingunitTypes[i].cbs_chn;
					}
				}
				return value;
			} 
		},  {
			field : 'product_packingamount',
			title : '包装量',
			width : 50,
			sortable : true
		}, {
			field : 'order_product_unitPrice',
			title : '原单价',
			width : 80,
			sortable : true
		}, {
			field : 'order_product_memUnitPrice',
			title : '折扣后单价',
			width : 80,
			sortable : true
		}, {
			field : 'accept_Amount',
			title : '能开价',
			width : 50,
			sortable : true
		}, {
			field : 'product_lastprice',
			title : '最后进价',
			width : 50,
			sortable : true
		}, {
			field : 'accept_Number',
			title : '可用数',
			width : 80,
			sortable : true
		}, {
			field : 'stock_storageNumber',
			title : '库房数量',
			width : 80,
			sortable : true
		}, {
			field : 'number',
			title : '数量',
			width : 50,
			sortable : true,
			formatter: function(value,row,index){
				return 1;//默认值
			}
		}, {
			field : 'product_chnpy',
			title : '拼音',
			width : 80,
			sortable : true
		}, {
			field : 'product_amountFormat',
			title : '价格格式',
			width : 80,
			sortable : true
		}, {
			field : 'product_numberFormat',
			title : '数量格式',
			width : 80,
			sortable : true
		}, {
			field : 'product_id',
			title : '产品序号',
			width : 80,
			sortable : true
		}, {
			field : 'product_point',
			title : '产品积分',
			width : 50,
			sortable : true
		}, {
			field : 'stock_intakeSmallNumber',
			title : '入库小号',
			width : 100,
			sortable : true
		}, {
			field : 'product_retailcommission',
			title : '零售提成',
			width : 80,
			sortable : true
		}, {
			field : 'is_memberDay',
			title : '是否会员日',
			width : 50,
			sortable : true,
			formatter: function(value,row,index){
				if(value == 0)
					return "<font color='green'>是</font>";
				else if(value == 1)
					return "<font color='red'>否</font>";
				else
					return "<font color='blue'>出错了!</font>";
			}
		}, {
			field : 'product_moneyPerPoint',
			title : '记分金额',
			width : 50,
			sortable : true
		}, {
			field : 'order_batch_pointMutiple',
			title : '积分倍数',
			width : 50,
			sortable : true
		}, {
			field : 'order_product_unitPrice',
			title : '零售价',
			width : 80,
			sortable : true
		}, {
			field : 'product_tinynum',
			title : '拆零组数',
			width : 50,
			sortable : true
		}] ]
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
	
	//设置草稿
	var result = util.get(global_param.context_name+"/retail/order/checkDraft");
	if(result == null || result == ""){
		alert("数据异常：当前操作员出现多条未完成的订单记录，窗口即将关闭");
		parent.$('#tt').tabs('close',"零售销售");
	}else if(result.retail_order_ticketId !=null &&result.retail_order_ticketId !=""){
		$("#input_retail_order_ticketId").val(result.retail_order_ticketId);
		$("#retail_order_ticketId").val(result.retail_order_ticketId);
		//无数据时显示滚动条
		scrollShow($("#products_info_dg"));
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
	keyPress.bindKeyPressToCtrl(ctrlsID);
	
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
	});


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
	

	//$("#input_order_product_unitPrice").val(node.order_product_memUnitPrice);这个order_product_memUnitPrice在nodes里没找到啊
	$("#input_order_product_unitPrice").val(node.order_product_unitPrice);//先改成这个   zhengyx
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
function perviewPrint(){
	var printType = $("#input_print_type").combo('getValue');
	
	if(printType==""){
		util.show("请选择打印格式");
	}else{
		var node = {
				text : '零售报表',
				attributes : {url : "basInfo/report/report?fileName="+printType}
			};
		self.parent.addTab(node);
		//util.get(global_param.context_name + "/basInfo/report/report?fileName="+printType);
		/*$('#tt').tabs('add',{
			title:"测试",
			content:'<iframe style="width: 100%; height: 100%;" frameborder="0" src="' + global_param.context_name + "/basInfo/report/report?fileName="+printType + '"></iframe>',
			closable:true
		});*/
	}
	
}

function returnGoods(batch_id){
	//退货请求，参数为  零售批号
	var result = util.get(global_param.context_name + "/retail/order/returnGoods?batch_id="+batch_id+"&number="+$('#input_order_product_quantity').val());
	
	if(result.result=="success"){
		util.show(result.message);
		clearMed();
	}else{
		util.show(result.message);
	}
	
	
	
}

