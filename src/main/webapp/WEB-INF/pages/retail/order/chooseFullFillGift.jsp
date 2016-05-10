<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/chnToLetter.js"></script>
<script type="text/javascript">

//兑换规则
var exchangeRule = util.get(global_param.context_name + "/system/combobox/lists?pid=983");
//已买产品
var nodes = null;

//已选中的满额赠活动
var fullFill = null;
//当前满额赠活动产品参与总金额
var allCost = 0;

//已选中的满赠活动
var giftSale = null;
//当前满赠活动产品list
var giftInfos = null;


//参加当前活动的产品行号
var current_join_rows = new Array();


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
                        grid.datagrid('selectRow', index - 1);
                    } else {
                        grid.datagrid('selectRow', rows.length - 1);
                    }
                    break;
                case 40: // down
                    if (index<rows.length - 1) {
                        grid.datagrid('selectRow', index + 1);
                    } else {
                        grid.datagrid('selectRow', 0);
                    }
                    break;
                case 46:
                	parent.$('#fullFillGift').window('close');
                	break;
                }
            });
        });
    }
});


$(function(){
	// 满额赠表格
	$("#fullFillGiftDg").datagrid({
		title : '满额赠信息管理', // 标题
		sortName : 'full_gift_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		//fitColumns : true,
		singleSelect : true, // 单选
		//fit : true,
		height:460,
		rownumbers : true, // 显示行号
		striped : true, // 奇偶行颜色不同
		queryParams : {},
		columns:[[{
			field : 'full_gift_id',
			title : '序号',
			width : 50,
			sortable : true
		}, {
			field : 'product_id',
			title : '产品序号',
			width : 50,
			sortable : true
		}, {
			field : 'full_gift_unitPrice',
			title : '单价',
			width : 50,
			sortable : true
		},{
			field : 'full_gift_amount',
			title : '数量',
			width : 50,
			sortable : true
		}, {
			field : 'full_gift_exchangeAmount',
			title : '兑换金额',
			width : 50,
			sortable : true
		},  {
			field : 'full_gift_exchangeRule',
			title : '兑换规则',
			width : 80,
			sortable : true,
			formatter : function (value, row, index) {
				for(var i = 0; i <exchangeRule.length; i++){
					if(value == exchangeRule[i].cbs_id)
						return exchangeRule[i].cbs_chn;
				}
				return "崩了";
			}
		}, {
			field : 'full_gift_startDate',
			title : '开始日期',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value);
			}
		}, {
			field : 'full_gift_endDate',
			title : '结束日期',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value);
			}
		}, {
			field : 'full_gift_specialBatch',
			title : '特殊批次',
			width : 50,
			formatter : function (value, row, index) {
				if(value == 0)
					return "<font color='green'>否</font>";
				else if(value == 1)
					return "<font color='red'>是</font>";
				else
					return "<font color='blue'>出错了!</font>";
			}
		}, {
			field : 'full_gift_remark',
			title : '备注',
			width : 80,
			sortable : true
		}]],
		toolbar : [ {
			id:'fullFillButton',
			text : '选择',
			iconCls : 'icon-add',
			handler : chooseFullFillAll
		}],
/*		onClickRow:function(rowIndex,rowData){
			gift_sal_id = rowData.gift_sal_id;
			$('#GiftInfoDg').datagrid('options').queryParams.gift_sal_id = rowData.gift_sal_id;
			$('#GiftInfoDg').datagrid('options').url = global_param.context_name + "/promotion/giftinfo/listsVO";
			$('#GiftInfoDg').datagrid('reload');
		}*/
	}).datagrid("keyCtr");
	
	
	//加载活动
	loadPromotions();
	
	//初始化   满额赠活动   聚焦
	$("#fullFillButton").focus();
});

function chooseFullFillAll(){
	fullFill = $("#fullFillGiftDg").datagrid("getSelected");
	if(!fullFill){
		util.show("请选择满额赠活动");
		return;
	}
	
	//需要选择批次的产品
	var products = new Array();
	
	for(var i=0;i<nodes.rows.length;i++){
		//如果销售产品存在于活动中，则累加金额
		if(fullFill.full_gift_joinProduct.indexOf(","+nodes.rows[i].product_id+",")>-1){
			//排除已参与活动的产品
			if(parent.all_join_rows.indexOf(i)==-1){
				allCost = allCost + nodes.rows[i].order_product_amount*nodes.rows[i].number;
				current_join_rows.push(i);//记录活动产品
			}
			//products.push(nodes.rows[i].product_id);//暂时取消批次选择
		}
	}
	
	//如果消费金额低于满额赠金额，则无法参与活动
	if(allCost==0){
		util.show("没有产品参与活动");
		return;
	}
	if(allCost<fullFill.full_gift_exchangeAmount){
		util.show("参与产品消费金额小于"+fullFill.full_gift_exchangeAmount+",不满足条件!");
		return;
	}
	
	/* //选择赠品
	$("#proListWindow").window({
			title : '赠品选择',
			width : 1250,
			height : 650,
			shadow : true,
			modal : true,
			closed : true,
			minimizable : false,	
			maximizable : false,
			collapsible : false,
			resizable : false,
			content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/retail/order/viewProList?callback=setGiftProContent&product_id='+fullFill.product_id+'&dept_id='+user.depart_id+'&mem_card_discount='+$("#mem_card_discount").val()+'"></iframe>',
	});
	$('#proListWindow').window('open'); */
	
	var number = fullFill.full_gift_amount;
	
	
	//如果是兑换金额(按比例兑换),其余情况暂时没考虑
	if(fullFill.full_gift_exchangeRule == 984){
		number = fullFill.full_gift_amount*parseInt(allCost/fullFill.full_gift_exchangeAmount);//向下取整
		
	}
	//更新已卖产品的兑换金额
	window.parent['updateSaledMed'](fullFill.full_gift_joinProduct.substring(1,fullFill.full_gift_joinProduct.length-1),
			parent.$("#input_retail_order_ticketId").val());
	//更新销售页面兑换金额
	parent.$("#input_retail_order_fullGiftExchange").val(Number(parent.$("#input_retail_order_fullGiftExchange").val())+allCost);
	//未兑金额
	parent.$("#input_retail_order_fullGiftRemain").val(Number(parent.$("#input_retail_order_fullGiftAmount").val())-parent.Number($("#input_retail_order_fullGiftExchange").val()));
	
	
	
	var data = util.get(global_param.context_name + "/retail/order/salePromotion?product_id=" + fullFill.product_id+
			"&number="+number+"&discountedPrice="+fullFill.full_gift_unitPrice+"&retail_order_ticketId="+
			parent.$("#input_retail_order_ticketId").val()+"&retail_type='满额赠'");
	
	
	if(data.result == "failure"){
		util.show(data.message);
	}else{
		util.show(data.message);
		//更新已经参加活动的产品
		parent.all_join_rows = parent.all_join_rows.concat(current_join_rows);
		for(var i = 0; i <data.bean.length;i++){
			parent.$("#products_info_dg").datagrid("appendRow",data.bean[i]);
			//更新金额信息等
			window.parent['updateCost'](data.bean[i]);
		}
		//关闭当前活动页面
		parent.$('#fullFillGift').window('close');
		//清空药品信息
		window.parent['clearMed']();
	}
}

//回调函数
/* function setGiftProContent(nodes){
	//清空当前参与活动产品
	current_join_rows = [];
	
	var giftNode = nodes[0];//赠品信息，只可能返回一个
	//如果是兑换金额(按比例兑换)
	if(fullFill.full_gift_exchangeRule == 984)
		giftNode.number = fullFill.full_gift_amount*parseInt(allCost/fullFill.full_gift_exchangeAmount);//向下取整
	else//其余情况选择一种兑换
		giftNode.number = fullFill.full_gift_amount;//数量
	
	//赠品赋值
	giftNode.order_product_amount = fullFill.full_gift_amount*fullFill.full_gift_unitPrice;//金额
	giftNode.discountedPrice = fullFill.full_gift_unitPrice;//折后价
	//折让 = 单价 - 折后价
	giftNode.order_product_discountAmount = 0;
	giftNode.retail_order_ticketId = $("#input_retail_order_ticketId").val();//票号
	
	
	//删除此属性，用于分页的属性(否则后台json对象装换出错)
	delete giftNode["offset"];
	
	
	//ajax提交销售信息
	var data = util.ajax(global_param.context_name + "/retail/order/saveRetail",{"data":JSON.stringify(giftNode)});
	if(data.result == "failure"){
		util.show(data.message);
	}else{
		util.show(data.message);
		for(var i = 0; i <data.bean.length;i++){
			$("#products_info_dg").datagrid("appendRow",data.bean[i]);//添加销售产品
			//更新金额信息等,此函数在主单js里面
			updateCost(data.bean[i]);
			//添加赠品(当前活动参与产品)
			current_join_rows.push($("#products_info_dg").datagrid("getData").rows.length-1);
			//更新已经参加活动的产品
			all_join_rows = all_join_rows.concat(current_join_rows);
		}
		
		//重新加载满额赠活动
		loadPromotions();
	}
} */

//加载满额赠活动
function loadPromotions(){
	//活动数据准备
	var data="";
	nodes = parent.$('#products_info_dg').datagrid('getData');
	if(nodes.rows.length==0){
		util.show("请先出售药品");
		return;
	}
	
	for(var i=0;i<nodes.rows.length;i++){
		//将行数据封装成字符串，以便传到后台
		data+=nodes.rows[i].product_id+"_";
		data+=0;//nodes.rows[i].stock_buyPresentSpecial;
			if(i<nodes.rows.length-1)
				data+="|";
	}
	
	if(data!=""){
		//获取活动信息
		var result = util.get(global_param.context_name + "/retail/order/getFullFillGifts?member_id="+(parent.member==null?"":parent.member.mem_card_id)+"&data="+data);
		
		alert(JSON.stringify(result.pfgs));
		//加载满额赠数据
		$("#fullFillGiftDg").datagrid("loadData",{total:result.pfgs.length,rows:result.pfgs});
	}else{
		//清空满额赠数据
		$("#fullFillGiftDg").datagrid("loadData",{total:0,rows:[]});
	}
		
	
}

</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
				<input name="SPRING_TOKEN" type="hidden" value="${sessionScope.SPRING_TOKEN}" />
						<%-- <div class="float-l">
							<div class="Dialog-form-title">
								<label for="mem_card_type" class="field">类别：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="mem_card_type" name="mem_card_type"
									class="easyui-combobox" data-options="required:true" style="width:152px;"
									value="${memCardManage.mem_card_type}" />
							</div>
						</div> --%>
						<!-- 显示区域 -->
						<div data-options="region:'center',border:false" style="height:100%">
							<table id="fullFillGiftDg"></table>
				 		</div>
					
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<!-- <input id="submitMem" class="btn-save"
			onmouseover="this.className='btn-save-over'"
			onmouseout="this.className='btn-save'"
			onmousedown="this.className='btn-save-down'" type="button"
			onclick="submitForm();" /> --> <input id="" class="btn-cancel"
			onmouseover="this.className='btn-cancel-over'"
			onmouseout="this.className='btn-cancel'"
			onmousedown="this.className='btn-cancel-down'" type="button"
			onclick="parent.$('#fullFillGift').window('close');" />
	</div>
</div>