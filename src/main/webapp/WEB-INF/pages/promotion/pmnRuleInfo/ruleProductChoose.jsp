<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript"	src="/HRERP/resources/js/JsUtil/screening.js"></script>
<script type="text/javascript">
	var bool = true;
	var s = new screening();
	$(function() {
		/*准备显示数据*/
		$("#pro_dg").datagrid({
			method : 'get',
			url : global_param.context_name + "/product/proInfoManage/listRuleProduct", // 数据来源
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
				rule_id: $("#rule_id").val(),
			},
			columns:[[{
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
				text : '选择',
				iconCls : 'icon-add',
				handler : choosePro
			}, {
				text : '全选',
				iconCls : 'icon-modify',
				handler : selectAllFunc
			}, {
				text : '清除全选',
				iconCls : 'icon-delete',
				handler : unSelectAllFunc
			}],
			onLoadSuccess:function(data){
				if(bool){
					s.data = data;
					bool = false;
				}
			}
	});
		
		//为此表格添加筛选条件(必须在datagrid声明之后)
		/***
		 * @param ctrl_id datagrid的ID
		 * @param window 筛选窗口ID
		 * @param className 类名
		 * @param userHabits 用户习惯对象
		 */
		s.loadScreening("pro_dg","screeningWindow","ProInfoManage",null);
		
	// 初始化编辑窗口
	util.window('screeningWindow', {
		title : '信息筛选'
	});
});

function selectAllFunc(){
	 $("#pro_dg").datagrid("checkAll");
}
function unSelectAllFunc(){
	$("#pro_dg").datagrid("uncheckAll");
}
	
function queryProduct() {
	$('#pro_dg').datagrid('options').queryParams = util.formParams("searchProductForm");
	$('#pro_dg').datagrid('options').queryParams.rule_id = $("#rule_id").val();
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
			data = data + nodes[i].product_id+"_";
		}
		else{
			data = data+nodes[nodes.length-1].product_id;
		}
	}
		var result = util.get("/HRERP/promotion/pmnRuleInfo/chooseProducts?data="+data+
				"&rule_id="+$("#rule_id").val()); 
		
		 
		if(result.result=="success"){
			util.show(result.message);
			$("#editRuleProductWindow").window('close');
			$('#RuleProductInfoDg').datagrid('reload');
		}else if(result.result=="failure"){
			util.show(result.message);
		}else{
			util.show("出错了");
		}
	}

</script>	
 <div class="easyui-layout" fit="true">
 	<!-- <!-- toolbar显示区域 -->
	<div id="tb" style="padding:2px;height:auto">
		<div style="margin-bottom:1px">
		<font style="float:left">
		<a id="choosePro" href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="choosePro()">选择</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input id="allCheck" type="checkbox" name="allCheck" style="width:20px" onclick="selectAll()"><label for="allCheck">全选</label>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
		</div>
	</div> -->
	<div region="center" border="false" class="Dialog-Bg">	
			<div class="l-searchbar clearfix centertoolbar-w1">
				<div class="l-panel-search-cond clearfix">
					<form id="searchProductForm" onsubmit="return false;">
						<input id="rule_id" type="hidden" value="${rule_id}" />
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