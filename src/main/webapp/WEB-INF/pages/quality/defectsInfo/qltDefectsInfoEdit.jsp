<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">	
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
	$("#stock_storageNumber").attr("value",nodes.stock_storageNumber);
	$("#department_id").attr("value",nodes.stock_storage);
	
};


function submitForm() {
	
	if(proInfo==null||$('#product_id').val()!=proInfo.product_id){
		util.show("请回车后在页面选择产品！");
		return;
	}
	if($('#stock_storageNumber').val()<$('#defects_number').val()){
		util.show("不合格品种数量不能超过库存剩余数量！");
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
				<input name="defects_id" type="hidden" value="${qltDefectsInfoVO.defects_id}" />
				<%-- <input id="product_id" name="product_id" type="hidden" value="${qltDefectsInfoVO.product_id}" />
				 --%>
				 <input id="stock_info_id" name="stock_info_id" type="hidden" value="${qltDefectsInfoVO.stock_info_id}" />
				<input id="department_id" name="department_id" type="hidden" value="${qltDefectsInfoVO.department_id}" />
				<input name="SPRING_TOKEN" type="hidden"
					value="${sessionScope.SPRING_TOKEN}" />
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="product_id" class="field">产品序号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="product_id" name="product_id" class="easyui-validatebox"
							data-options="required:true"  value="${qltDefectsInfoVO.product_id}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="stock_batchCode" class="field">产品批号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="stock_batchCode" class="easyui-validatebox" 
							data-options="required:true"  value="${qltDefectsInfoVO.stock_batchCode}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="stock_storageNumber" class="field">库存数量：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="stock_storageNumber" name="stock_storageNumber" readonly="readonly"
						class="easyui-validatebox" value="${qltDefectsInfoVO.stock_storageNumber}" />
							
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="defects_number" class="field">不合格数量：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="defects_number" name="defects_number" class="easyui-validatebox"
							value="${qltDefectsInfoVO.defects_number}" />
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="defects_quality" class="field">质量状况：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="defects_quality" name="defects_quality" class="easyui-validatebox"
							value="${qltDefectsInfoVO.stock_storageNumber}" />
					</div>
				</div>		
			 	<div class="float-l">
					<div class="Dialog-form-title">
						<label for="defects_reason" class="field">不合格原因：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="defects_reason" name="defects_reason" class="easyui-validatebox"
							value="${qltDefectsInfoVO.defects_reason}" />
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="defects_suggestion" class="field">处理意见：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="defects_suggestion" name="defects_suggestion" class="easyui-validatebox"
							value="${qltDefectsInfoVO.defects_suggestion}" />
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="defects_storagePlace" class="field">存放地点：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="defects_storagePlace" name="defects_storagePlace" class="easyui-validatebox"
							value="${qltDefectsInfoVO.defects_storagePlace}" />
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="defects_remark" class="field">备注：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="defects_remark" name="defects_remark" class="easyui-validatebox"
							value="${qltDefectsInfoVO.defects_remark}" />
					</div>
				</div> 
			</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submit" class="btn-save"
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