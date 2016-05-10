<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript"	src="/HRERP/resources/js/JsUtil/screening.js"></script>
<script type="text/javascript">

	$(function() {

		/*准备显示数据*/
		$("#pro_dg").datagrid({
			method : 'get',
			url : global_param.context_name + "/product/proInfoManage/lists?product_check=0", // 数据来源
			title : '产品信息', // 标题
			sortName : 'product_id', // 排序的列
			sortOrder : 'asc', // 排序方式
			fitcolumns : true,
			//singleSelect : true,//单选
			ctrlSelect : true ,//ctrl多选
			pagination : false,
			rownumbers : true,
			height: 750 ,
			queryParams: {
				//rule_id: $("#rule_id").val(),
			},
			columns:[[/* {
				field : 'product_id',
				title : '产品id',
				width : 20,
				sortable : true
			},  */{
				field : 'product_name',
				title : '产品名称',
				width : 80,
				sortable : true
			}, {
				field : 'product_chnpy',
				title : '拼音码',
				width : 80,
				sortable : true
			}, {
				field : 'product_code',
				title : '产品编号',
				width : 100,
				sortable : true
			}, {
				field : 'product_barcode',
				title : '产品条码',
				width : 120,
				sortable : true
			}, {
				field : 'product_proname',
				title : '商品名',
				width : 70,
				sortable : true
			}, {
				field : 'product_specification',
				title : '规格',
				width : 100,
				sortable : true
			}, {
				field : 'product_productarea',
				title : '产地',
				width : 150,
				sortable : true
			}, {
				field : 'product_description',
				title : '简介',
				width : 150,
				sortable : true
			}, {
				field : 'product_basremark',
				title : '基本备注',
				width : 150,
				sortable : true
			}]],
			toolbar : [ {
				id: 'choosePro',
				text : '选择',
				iconCls : 'icon-add',
				handler : choosePro
			},{
				id: 'selectAll',
				text : '全选',
				iconCls : 'icon-add',
				handler : selectAll
			}],
			onLoadSuccess:function(){
				var ids = new Array();
				var rows = $("#pro_dg").datagrid('getRows');
				for(var i = 0; i < rows.length; i++){
					ids.push(rows[i].product_id);
				}
				if($("#product_ids").val()!= ""){
					var productList = $("#product_ids").val().split(",");
					for(var i = 0; i<productList.length; i++){
						for(var j = 0; j<rows.length; j++)
						if(productList[i] == rows[j].product_id){
							$("#pro_dg").datagrid('selectRow',j);
							break;
						}
					}
				}
			}
	});
	//为此表格添加筛选条件
	/***
	 * @param ctrl_id datagrid的ID
	 * @param window 筛选窗口ID
	 * @param className 类名
	 */
	var s = new screening();
	s.loadScreening("pro_dg","screeningWindow","ProInfoManage",null);

});

//全选和反选
function selectAll(){
	
	if($('#pro_dg').datagrid('getSelections').length != $('#pro_dg').datagrid('getRows').length){
		$("#pro_dg").datagrid("selectAll");
	}
	else
		$("#pro_dg").datagrid("unselectAll");	
}
	
function queryProduct() {
	$('#pro_dg').datagrid('options').queryParams = util.formParams("searchProductForm");
	//$('#pro_dg').datagrid('options').queryParams.rule_id = $("#rule_id").val();
	$('#pro_dg').datagrid('reload');
}		
	
function choosePro(){
	
	var nodes = $('#pro_dg').datagrid('getSelections');
	var data ="";
	if(nodes.length==0){
		util.show("请选择一条产品");
		return;
	}
	for(var i=0;i<nodes.length;i++){
		if(i<nodes.length-1){
			data = data + nodes[i].product_id+",";
		}
		else{
			data = data+nodes[nodes.length-1].product_id;
		}
	}
		/* var result = util.get("/HRERP/promotion/pmnRuleInfo/chooseProducts?data="+data+
				"&rule_id="+$("#rule_id").val()); 
 */		util.show("添加商品完成");
		$("#editFulfillProductWindow").window('close');

		$("#full_gift_joinProduct").val(data);
		
	}

</script>	
 <div class="easyui-layout" fit="true">
 	<!-- toolbar显示区域 -->
<!-- 	<div id="tb" style="padding:2px;height:auto">
		<div style="margin-bottom:1px">
		<font style="float:left">
		<a id="choosePro" href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="choosePro()">选择</a>
		<input id="allCheck" type="checkbox" name="allCheck" style="width:20px" onclick="selectAll()"><label for="allCheck">全选</label>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
		</div>
	</div> -->
	<div region="center" border="false" class="Dialog-Bg">	
			<div class="l-searchbar clearfix centertoolbar-w1">
				<div class="l-panel-search-cond clearfix">
					<form id="searchProductForm" onsubmit="return false;">
						<input id="product_ids" type="hidden" value="${product_ids}" />
						<div class="float-l">
							<div class="l-panel-search-title">产品编码：</div>
							<div class="l-panel-search-item">
								<input id="search_product_code" name="product_code" type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">产品名称：</div>
							<div class="l-panel-search-item">
								<input id="search_product_name" name="product_name" type="text" />
							</div>
						</div>
					</form>
				</div>
				<div class="l-panel-search-btn">
					<input id="" class="btn-search"
						onmouseover="this.className='btn-search-over'"
						onmouseout="this.className='btn-search'"
						onmousedown="this.className='btn-search-down'" type="button"
						onclick="queryProduct();" /> <input id="" class="btn-empty"
						onmouseover="this.className='btn-empty-over'"
						onmouseout="this.className='btn-empty'"
						onmousedown="this.className='btn-empty-down'" type="button"
						onclick="$('#searchProductForm').form('reset');" />
				</div>
			</div>
		
		<!-- 显示区域 -->
		<div id="proData" data-options="region:'center',border:false" style="overflow: hidden;width: 100%; height: 1000">
			<table id="pro_dg"></table>
		</div>
		
		 <!-- 编辑窗口 -->
		<div id="screeningWindow"></div>
	 </div>
 </div>