<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">

	//开始日期
	$('#batch_startTime').datebox({
	required:true,	
	value:($('#batch_startTime').val()=="")?"":util.toDate($('#batch_startTime').val()),
}); 

	//结束日期
	$('#batch_endTime').datebox({
	required:true,	
	value:($('#batch_endTime').val()=="")?"":util.toDate($('#batch_endTime').val()),
}); 
//从产品信息里选择产品
$('#product_id').bind('keydown', function() {
    var e = event || window.event;
    var keyCode = e.keyCode || e.which;
    var actElement = document.activeElement;
    //如果按键是enter并且当前焦点是产品编号，则弹出产品选择框
    if(keyCode == 13&&actElement.id == "product_id"){
 		var proContent = $('#product_id').attr("value");
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
			//content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/quality/varietyCheck/viewProList?callback=setProContent&proContent=' + proContent.replace("%", "%25") +'"></iframe>',
			content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/quality/varietyCheck/viewProList?callback=setProContent&proContent=' + proContent.replace("%", "%25") +'"></iframe>',
		});
		$('#proListWindow').window('open');
 		
    }
});

//获得子窗口的产品信息
function setProContent(node){
	//存储当前的产品信息
	proInfo = node;

	//修改控件信息
	//$("#product_name").attr("value",node.product_name);
	$("#product_id").attr("value",node.product_id);

	//alert($("#product_id").val());
};

//从库存信息里选择产品
$('#stock_batchCode').bind('keydown', function() {
    var e = event || window.event;
    var keyCode = e.keyCode || e.which;
    var actElement = document.activeElement;
    var product_id = $("#product_id").val();
    //如果按键是enter并且当前焦点是产品编号，则弹出产品选择框
    if(keyCode == 13&&actElement.id == "stock_batchCode"){
 		var stockContent = $('#stock_batchCode').attr("value");
		$("#stockListWindow").window({
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
			content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/quality/batchCheck/viewStockList?callback=setStockContent&stockContent=' + stockContent.replace("%", "%25") +'&product_id='+product_id+'"></iframe>',
		});
		$('#stockListWindow').window('open');
 		
    }
});

//获得子窗口的产品信息
function setStockContent(nodes){
	//存储当前的产品信息
	stockInfo = nodes;
	//修改控件信息
	//alert(JSON.stringify(nodes));
	
	$("#stock_info_id").attr("value",nodes.stock_info_id);
	$("#stock_batchCode").attr("value",nodes.stock_batchCode);
	 
};


function submitForm() {
	var startTime = $('#batch_startTime').datebox('getValue');
	var endTime = $('#batch_endTime').datebox('getValue');

	var d1 = new Date(startTime.replace(/\-/g, "\/"));  
	var d2 = new Date(endTime.replace(/\-/g, "\/")); 
	//日期验证提示
	if(d1 > d2){  
		util.show("开始日期不能大于结束日期");  
		return;
	}
	
	if(proInfo==null||$('#product_id').val()!=proInfo.product_id){
		util.show("请回车后在页面选择产品！");
		return;
	}

	var data = util.submit('_form');
	if (data) {
		if (data.result == "success") {
			util.show(data.message);
			$('#dg').datagrid('reload');
			util.closeWindow('editWindow');
		} else {
			util.error(data.message);
		}
	}
}
	
</script>

<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
			<form:form id="_form" action="${formUrl}" method="${method}" onsubmit="return false;">
				<input name="batch_checkId" type="hidden" value="${qltBatchCheckVO.batch_checkId}" />
				<input id="stock_info_id" name="stock_info_id" type="hidden" value="${qltBatchCheckVO.stock_info_id}" />
				<%-- <input id="stock_invalidDate" name="stock_invalidDate" type="hidden" value="${qltBatchCheckVO.stock_invalidDate}" />
				 --%> 
				<input name="SPRING_TOKEN" type="hidden"
					value="${sessionScope.SPRING_TOKEN}" />
				<%-- <div class="float-l">
					<div class="Dialog-form-title">
						<label for="product_name" class="field">产品名称：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="product_name" class="easyui-validatebox"
							data-options="required:true"  value="${qltBatchCheckVO.product_name}" />
					</div>
				</div> --%>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="product_id" class="field">产品序号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="product_id" name="product_id" class="easyui-validatebox"
							data-options="required:true"  value="${qltBatchCheckVO.product_id}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="stock_batchCode" class="field">产品批号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="stock_batchCode" name="batch_no" class="easyui-validatebox"
							data-options="required:true"  value="${qltBatchCheckVO.stock_batchCode}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="batch_checkReason" class="field">确认理由：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="batch_checkReason" name="batch_checkReason" class="easyui-validatebox"
							value="${qltBatchCheckVO.batch_checkReason}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="batch_majorMaintain" class="field">养护重点：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="batch_majorMaintain" name="batch_majorMaintain" class="easyui-validatebox"
							value="${qltBatchCheckVO.batch_majorMaintain}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="batch_remark" class="field">备注：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="batch_remark" name="batch_remark" class="easyui-validatebox"
							value="${qltBatchCheckVO.batch_remark}" />
					</div>
				</div>
				
				
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="batch_startTime" class="field">开始时间：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="batch_startTime" name="batch_startTime" class="easyui-datebox" 
	                  	 data-options="required:true" style="width: 152px;" value="${qltBatchCheckVO.batch_startTime}" />
	               </div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="batch_endTime" class="field">结束时间：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="batch_endTime" name="batch_endTime" class="easyui-datebox" 
	                  	 data-options="required:true" style="width: 152px;" value="${qltBatchCheckVO.batch_endTime}" />
	               </div>
				</div>
			</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submitBatch" class="btn-save"
			onmouseover="this.className='btn-save-over'"
			onmouseout="this.className='btn-save'"
			onmousedown="this.className='btn-save-down'" type="button"
			onclick="submitForm();" /> <input id="" class="btn-cancel"
			onmouseover="this.className='btn-cancel-over'"
			onmouseout="this.className='btn-cancel'"
			onmousedown="this.className='btn-cancel-down'" type="button"
			onclick="util.closeWindow('editWindow');" />
	</div>
</div>