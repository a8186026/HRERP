
var memCard_type = util.getList(global_param.context_name + "/system/combobox/lists?pid=51");
//获取所有库房信息
var depts = util.get(global_param.context_name + "/basInfo/basDepartmentInfo/getList");
//获取单位信息
var packingunitTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=132");
//当前会员
var member_card = null;

$(function() {
	//第一个参数页面ID,第二个参数datagrid ID，第三个参数配置显示框ID,第四个参数是否启动上下键换行，第五个参数是否启动列拖拽
	//loadColumns("basLicenseInfoList","dg","editWindow",true,true);
	
	// 初始化表格
	/*$("#returnDg").datagrid({
		method : 'get',
		url : global_param.context_name + "/basInfo/ethicalPerson/lists", 		
		title : '会员退货信息', // 标题
		sortName : 'refundID', // 排序的列
		sortOrder : 'asc', // 排序方式
		singleSelect : true, // 单选
		pageSize : 10,
		pageList : [10, 20, 50, 100],
		//fit : true,
		height:520,
		//striped : true, // 奇偶行颜色不同
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		nowrap : true,
		striped : true, // 奇偶行颜色不同
		queryParams : {},
		columns:[[{
			field : 'ethical_personId',
			title : '序号',
			width : 80,
			sortable : true
		}, {
			field : 'ethical_personCode',
			title : '类别',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '卡号',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '姓名',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '累计消费',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '积分消费',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '记分金额',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '初始金额',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '积分',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '输入扣率',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '扣率',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '最低扣率',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '条码',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '卡值',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '电话',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '手机',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '地址',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '邮编',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '摘要',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '身份证',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '申请日期',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '失效日期',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '登记人',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '登记时间',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '停用',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '业务1',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '业务2',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '业务3',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '已兑积分',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '未兑积分',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '原始积分',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '简介',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '所属部门',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '代金卡',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '上次销售时间',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '追加积分',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '生日',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '病史',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '持卡',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '传送',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '类型标记',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '修改次数',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '年龄',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '完善人',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '常用药',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '上次代金时间',
			width : 100,
			sortable : true
		}, {
			field : 'ethical_personName',
			title : '经办人',
			width : 100,
			sortable : true
		}]],
	});*/
	
	$("#returnDg").datagrid({
		method : 'get',
		/*url : global_param.context_name + "/basInfo/ethicalPerson/lists",*/		
		title : '会员退货信息', // 标题
		sortName : 'refundID', // 排序的列
		sortOrder : 'asc', // 排序方式
		//fitColumns : true,
		singleSelect : true, // 单选
		pageSize : 10,
		pageList : [10, 20, 50, 100],
		//fit : true,
		height:520,
		//striped : true, // 奇偶行颜色不同
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		nowrap : true,
		striped : true, // 奇偶行颜色不同
		queryParams : {},
		columns:[[{
			field : 'order_batch_date',
			title : '日期',
			width : 150,
			sortable : true,
			formatter:function(value, row, index){
				return util.formatDate(value);
			}
		
		}, {
			field : 'product_name',
			title : '产品名称',
			width : 100,
			sortable : true
		}, {
			field : 'product_barcode',
			title : '产品条码',
			width : 100,
			sortable : true
		}, {
			field : 'product_specification',
			title : '规格',
			width : 100,
			sortable : true
		}, {
			field : 'product_unit',
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
			field : 'product_productarea',
			title : '产地',
			width : 100,
			sortable : true
		}, {
			field : 'order_batch_quantity',
			title : '数量',
			width : 100,
			sortable : true
		}, {
			field : 'stock_settlementPrice',
			title : '结算价',
			width : 100,
			sortable : true
		}, {
			field : 'order_batch_unitPrice',
			title : '单价',
			width : 100,
			sortable : true
		}, {
			field : 'stock_storage',
			title : '部门',
			width : 100,
			sortable : true,
			formatter :function(value, row, index){
				for(var i=0;i<depts.length;i++){
					if(value = depts[i].department_id)
						return depts[i].department_fullname;
				}
			}
		}]],
	});
	
	
	// 初始化表格
	$("#purchaseDg").datagrid({
		method : 'get',
		/*url : global_param.context_name + "/basInfo/ethicalPerson/lists",*/		
		title : '会员销售信息', // 标题
		sortName : 'refundID', // 排序的列
		sortOrder : 'asc', // 排序方式
		//fitColumns : true,
		singleSelect : true, // 单选
		pageSize : 10,
		pageList : [10, 20, 50, 100],
		//fit : true,
		height:520,
		//striped : true, // 奇偶行颜色不同
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		nowrap : true,
		striped : true, // 奇偶行颜色不同
		queryParams : {},
		columns:[[{
			field : 'order_batch_date',
			title : '日期',
			width : 150,
			sortable : true,
			formatter:function(value, row, index){
				return util.formatDate(value);
			}
		
		}, {
			field : 'product_name',
			title : '产品名称',
			width : 100,
			sortable : true
		}, {
			field : 'product_barcode',
			title : '产品条码',
			width : 100,
			sortable : true
		}, {
			field : 'product_specification',
			title : '规格',
			width : 100,
			sortable : true
		}, {
			field : 'product_unit',
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
			field : 'product_productarea',
			title : '产地',
			width : 100,
			sortable : true
		}, {
			field : 'order_batch_quantity',
			title : '数量',
			width : 100,
			sortable : true
		}, {
			field : 'stock_settlementPrice',
			title : '金额',
			width : 100,
			sortable : true
		}, {
			field : 'order_batch_unitPrice',
			title : '单价',
			width : 100,
			sortable : true
		}, {
			field : 'stock_storage',
			title : '部门',
			width : 100,
			sortable : true,
			formatter :function(value, row, index){
				for(var i=0;i<depts.length;i++){
					if(value = depts[i].department_id)
						return depts[i].department_fullname;
				}
			}
		}]],
	});
	
	scrollShow($("#returnDg"));
	scrollShow($("#purchaseDg"));
	
	
	// 如果数量改变，则计算总金额
	$('#search_memCard_id').bind('keydown', function() {
        var e = event || window.event;
        var keyCode = e.keyCode || e.which;
        var actElement = document.activeElement;
        //如果按键是enter并且当前焦点是数量，则修改数量和金额
        if(keyCode == 13&&actElement.id == "search_memCard_id"){
        	if($('#search_memCard_id').val()=="%"){
        		//如果输入的是百分号
     			$("#memCardWindow").window({
     				title : '会员选择',
     				width : 1250,
     				height : 650,
     				shadow : true,
     				modal : true,
     				closed : true,
     				minimizable : false,	
     				maximizable : false,
     				collapsible : false,
     				resizable : false,
     				content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/search/memCard/viewMemCardList?callback=setMemCardContent"></iframe>',
     			});
     			$('#memCardWindow').window('open');
        	}else{
        		//如果输入的是编号
        		member_card = util.get(global_param.context_name + "/search/memCard/getMemCardById?number=" +$('#search_memCard_id').val());
            	if(member_card!=null&&member_card!=""){
            		loadData(member_card);
            	}else{
            		util.show("会员卡号不存在!");
            		$("#search_memCard_id").val("");
            	}
        	}
        }
	});
	
	
	
	
	// 初始化编辑窗口
	util.window('editWindow', {
		title : '处方药人员信息',
		width:600,
		height:200
	});	
});

function queryMember() {
	if($("#search_memCard_id").val()==member_card.mem_card_number){
		//获取选项卡选中项
		var tab = $('#tabs').tabs('getSelected');
		var index = $('#tabs').tabs('getTabIndex',tab);
		
		//如果选择退货信息，则请求退货数据
		if(index==0){
			//如果是退货信息
			$('#returnDg').datagrid('options').url = global_param.context_name + "/search/memCard/getPurchaseByNum";
			$('#returnDg').datagrid('options').queryParams = util.formParams("searchForm");
			$('#returnDg').datagrid('options').queryParams.type = 0;
			$('#returnDg').datagrid('reload');
		}else if(index==1){
			//如果是销售信息
			$('#purchaseDg').datagrid('options').url = global_param.context_name + "/search/memCard/getPurchaseByNum";
			$('#purchaseDg').datagrid('options').queryParams = util.formParams("searchForm");
			$('#purchaseDg').datagrid('options').queryParams.type = 1;
			$('#purchaseDg').datagrid('reload');
		}
	}else{
		util.show("卡号出错!");
	}
	
	
	
}

//无数据时显示滚动条
function scrollShow(datagrid) {  
    datagrid.prev(".datagrid-view2").children(".datagrid-body").html("<div style='width:" + datagrid.prev(".datagrid-view2").find(".datagrid-header-row").width() + "px;border:solid 0px;height:1px;'></div>");  
} 

function loadData(node){
	//赋值
	$("#search_memCard_id").val(node.mem_card_number);
	$("#mem_card_name").val(node.mem_card_name);
	$("#mem_card_gender").val(node.mem_card_gender);
	$("#mem_card_tel").val(node.mem_card_tel);
	//会员卡赋值
	for(var i=0;i<memCard_type.length;i++){
		if(node.mem_card_type == memCard_type[i].cbs_id)
			$("#mem_card_type").val(memCard_type[i].cbs_chn);
	}
	$("#mem_card_cellPhone").val(node.mem_card_cellPhone);
	$("#mem_card_address").val(node.mem_card_address);
	$("#mem_card_allCost").val(node.mem_card_allCost);
	$("#mem_card_idCard").val(node.mem_card_idCard);
	$("#mem_card_pointCost").val(node.mem_card_pointCost);
	$("#mem_card_acountMoney").val(node.mem_card_acountMoney);
	$("#mem_card_point").val(node.mem_card_point);
	$("#mem_card_barCode").val(node.mem_card_barCode);
	$("#mem_card_startMoney").val(node.mem_card_startMoney);
	$("#mem_card_applyDate").datebox("setValue",util.formatDate(node.mem_card_applyDate));
	$("#mem_card_postCode").val(node.mem_card_postCode);
	//获取用户信息
	var user = util.get(global_param.context_name + "/system/user/getUserById?id="+node.create_user);
	$("#create_user").val(user.display_name);
	$("#create_time").datebox("setValue",util.formatDate(node.create_time));
	$("#mem_card_discount").val(node.mem_card_discount);
	$("#mem_lowest_discount").val(node.mem_lowest_discount);
	$("#mem_card_failedDate").datebox("setValue",util.formatDate(node.mem_card_failedDate));
	
	util.select({
		id : 'mem_card_gender',
		required : true,
		param : {
			typeCode : 'SEX'
		}
	});
}
function setMemCardContent(node){
	member_card = node;
	loadData(member_card);//加载数据
}
