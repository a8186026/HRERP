<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/chnToLetter.js"></script>
<script type="text/javascript">


//已买产品
var nodes = null;

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
                	parent.$('#giftSale').window('close');
                	break;
                }
            });
        });
    }
});


$(function(){
	//买赠表格
	$("#giftSaleDg").datagrid({
		title : '买赠信息管理', // 标题
		sortName : 'gift_sal_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitColumns : true,
		singleSelect : true, // 单选
		//fit : true,
		height:260,
		//striped : true, // 奇偶行颜色不同
		rownumbers : true, // 显示行号
		striped : true, // 奇偶行颜色不同
		queryParams : {},
		columns:[[{
			field : 'gift_sal_code',
			title : '编号',
			width : 80,
			sortable : true
		}, {
			field : 'gift_sal_activityTitle',
			title : '活动标题',
			width : 80,
			sortable : true
		}, {
			field : 'gift_sal_introduction',
			title : '说明',
			width : 100,
			sortable : true
		}, {
			field : 'gift_sal_name',
			title : '名称',
			width : 80,
			sortable : true
		}, {
			field : 'gift_sal_chn',
			title : '拼音码',
			width : 80,
			sortable : true
		}, {
			field : 'gift_sal_startDate',
			title : '开始日期',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value);
			}
		}, {
			field : 'gift_sal_endDate',
			title : '结束日期',
			width : 100,
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDate(value);
			}
		}, {
			field : 'gift_sal_productPoint',
			title : '产品积分',
			width : 50,
			sortable : true
		}, {
			field : 'gift_sal_retailPrice',
			title : '零售价',
			width : 50,
			sortable : true
		},{
			field : 'gift_sal_memberPrice',
			title : '会员价',
			width : 50,
			sortable : true
		}]],
		onSelect:function(index,data){
			//获取满赠产品
			giftInfos = util.get(global_param.context_name + "/promotion/giftinfo/listsVO?gift_sal_id="+data.gift_sal_id);
			//加载满赠数据
			$("#giftInfoDg").datagrid("loadData",giftInfos);
		},
		toolbar : [ {
			id:'giftSaleButton',
			text : '选择买赠',
			iconCls : 'icon-add',
			handler : chooseGiftSaleAll
		}]
	}).datagrid("keyCtr");
	
	
	
	//满赠活动捆绑产品信息
	$("#giftInfoDg").datagrid({
		method : 'get',
		//url : global_param.context_name + "/promotion/giftsale/lists", // 数据来源
		title : '买赠产品信息管理', // 标题
		sortName : 'gift_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		singleSelect : true, // 单选
		height:250,
		rownumbers : true, // 显示行号
		striped : true, // 奇偶行颜色不同
		queryParams : {},
		columns:[[{
			field : 'gift_productId',
			title : '产品序号',
			width : 100,
			sortable : true
		}, {
			field : 'product_name',
			title : '产品名称',
			width : 200,
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
			sortable : true
		}, {
			field : 'product_productarea',
			title : '产地',
			width : 200,
			sortable : true
		}, {
			field : 'gift_amount',
			title : '数量',
			width : 100,
			sortable : true
		}, {
			field : 'gift_retailPrice',
			title : '零售价',
			width : 100,
			sortable : true
		}, {
			field : 'gift_memberPrice',
			title : '会员价',
			width : 100,
			sortable : true
		}]]
	});
	
	//加载满赠活动
	loadGiftSale();
});

//买赠活动选择
function chooseGiftSaleAll(){
	giftSale = $("#giftSaleDg").datagrid("getSelected");
	
	if(!giftSale||!giftInfos){
		util.show("请选择满赠活动");
		return;
	}
	
	var prodcut_ids = "";//产品ids
	var numbers = "";//数量
	var discountedPrices = "";//折后价
	
	for(var i=0;i<giftInfos.total;i++){
		prodcut_ids = prodcut_ids + giftInfos.rows[i].gift_productId +"_";
		numbers = numbers + giftInfos.rows[i].gift_amount +"_";
		if(parent.member!=null)
			discountedPrices =  discountedPrices + giftInfos.rows[i].gift_memberPrice + "_";//如果是会员，则选择会员价
		else
			discountedPrices = discountedPrices + giftInfos.rows[i].gift_retailPrice + "_";//不是会员，选择零售价
	}
	
	prodcut_ids = prodcut_ids.substring(0, prodcut_ids.length-1);
	numbers = numbers.substring(0, numbers.length-1);
	discountedPrices = discountedPrices.substring(0, discountedPrices.length-1);
	
	var data = util.get(global_param.context_name + "/retail/order/salePromotion?product_id=" + prodcut_ids+
			"&number="+numbers+"&discountedPrice="+discountedPrices+"&retail_order_ticketId="+
			parent.$("#input_retail_order_ticketId").val()+"&retail_type='买赠'");
	
	
	if(data.result == "failure"){
		util.show(data.message);
	}else{
		util.show(data.message);
		for(var j = 0; j <data.bean.length;j++){
			parent.$("#products_info_dg").datagrid("appendRow",data.bean[j]);
			//更新金额信息等
			window.parent['updateCost'](data.bean[j]);
		}
		//关闭当前活动页面
		parent.$('#giftSale').window('close');
		//清空产品信息
		window.parent['clearMed']();
	}
	
	/* //选择捆绑产品
	$("#proListWindow").window({
		title : '买赠产品选择',
		width : 1250,
		height : 650,
		shadow : true,
		modal : true,
		closed : true,
		minimizable : false,	
		maximizable : false,
		collapsible : false,
		resizable : false,
		content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/retail/order/viewProList?callback=setStockBatch&product_id='+product_ids+'&dept_id='+user.depart_id+'&mem_card_discount='+$("#mem_card_discount").val()+'"></iframe>',
	});
	$('#proListWindow').window('open'); */
	
	
	
	
	
	
	
	
}



/* function setStockBatch(giftProducts){
	//清空当前参加活动的产品序号
	current_join_rows = [];
	
	for(var i=0;i<giftProducts.length;i++){
		//给出售产品赋值，价格金额等
		for(var j=0;j<giftInfos.total;j++){
			//如果选择的产品和捆绑产品id相同，则对此产品赋值出售
			if(giftProducts[i].product_id == giftInfos.rows[j].gift_productId){
				giftProducts[i].number = giftInfos.rows[j].gift_amount;//数量
				
					
				giftProducts[i].order_product_amount = giftProducts[i].number*giftProducts[i].discountedPrice;//金额
				giftProducts[i].order_product_discountAmount = 0;//折让
				giftProducts[i].retail_order_ticketId = $("#input_retail_order_ticketId").val();//票号
				
				//删除此属性，用于分页的属性(否则后台json对象装换出错)
				delete giftProducts[i]["offset"];
				
				//ajax提交销售信息
				var data = util.ajax(global_param.context_name + "/retail/order/saveRetail",{"data":JSON.stringify(giftProducts[i])});
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
					}
					//更新已经参加活动的产品
					all_join_rows = all_join_rows.concat(current_join_rows);
					
					//关闭满赠活动选择
					util.closeWindow('promotions');
					
					//清空选择产品
					cancelMed();
				}
			}
		}

	}
	
	
} */
//加载满赠活动
function loadGiftSale(){
	//获取活动信息
	var result = util.get(global_param.context_name + "/retail/order/getGiftSales?member_id="+(parent.member==null?"":parent.member.mem_card_id)+"&products="+
			(parent.node==null?null:parent.node.product_id));
	//加载满赠数据
	$("#giftSaleDg").datagrid("loadData",{total:result.pgss.length,rows:result.pgss});
	
	
	//初始化   满赠活动   聚焦
	$("#giftSaleButton").focus();
}
</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
				<input name="SPRING_TOKEN" type="hidden" value="${sessionScope.SPRING_TOKEN}" />
						<!-- 满赠活动显示显示区域 -->
						<div data-options="region:'center',border:false">
							<table id="giftSaleDg"></table>
				 		</div>
				 		<!-- 捆绑产品显示区域 -->
						<div data-options="region:'center',border:false">
							<table id="giftInfoDg"></table>
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
			onclick="parent.$('#giftSale').window('close');" />
	</div>
</div>