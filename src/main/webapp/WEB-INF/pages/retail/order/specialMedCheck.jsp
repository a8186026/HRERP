<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
$(function(){
	//聚焦
	//document.getElementById("input_retail_order_pharmacistName").focus();
	$("#retail_order_pharmacistElectronicSignature").focus();
});

function submitCheckForm() {
	//表单验证
	$("#specialMedCheck_form").form('enableValidation');
	var checkResult = $("#specialMedCheck_form").form('validate');
	if(checkResult){
		//设置属性
		$("#retail_order_pharmacistName").val($("#input_retail_order_pharmacistName").val());
		$("#retail_order_pharmacistElectronicSignature").val($("#input_retail_order_pharmacistElectronicSignature").val());
		$("#retail_order_purchasePersonName").val($("#input_retail_order_purchasePersonName").val());
		$("#retail_order_purchasePersonID").val($("#input_retail_order_purchasePersonID").val());
		$("#retail_order_purchasePersonPhone").val($("#input_retail_order_purchasePersonPhone").val());
		
		//提交整个订单
		submitOrder();
		//关闭当前窗口
		util.closeWindow('specialMedCheck');
	}
};
</script>

<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
			<form:form id="specialMedCheck_form" onsubmit="return false;">
				<input name="SPRING_TOKEN" type="hidden" value="${sessionScope.SPRING_TOKEN}" />
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="input_retail_order_pharmacistName" class="field">审核人：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="input_retail_order_pharmacistName" name="retail_order_pharmacistName" type="text"
						class="easyui-validatebox" required="required"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="input_retail_order_pharmacistElectronicSignature" class="field">审核密码：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="input_retail_order_pharmacistElectronicSignature" name="input_retail_order_pharmacistElectronicSignature" 
						class="easyui-validatebox" required="required" type="text"/>
					</div>
				</div>
				
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="retail_order_purchasePersonName" class="field">购买人姓名：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="input_retail_order_purchasePersonName" name="input_retail_order_purchasePersonName" class="easyui-validatebox"
							required="required" type="text"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="input_retail_order_purchasePersonID" class="field">购买人身份证：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="input_retail_order_purchasePersonID" name="input_retail_order_purchasePersonID"
						 class="easyui-validatebox" data-options="required:true,validType:'id'" type="text"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="input_retail_order_purchasePersonPhone" class="field">购买人电话：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="input_retail_order_purchasePersonPhone" name="input_retail_order_purchasePersonPhone" 
						class="easyui-validatebox" required="required" type="text"/>
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: center;">
		<input id="submitMedicine" class="btn-save"
			onmouseover="this.className='btn-save-over'"
			onmouseout="this.className='btn-save'"
			onmousedown="this.className='btn-save-down'" type="button"
			onclick="submitCheckForm();" /> <!-- <input id="" class="btn-cancel"
			onmouseover="this.className='btn-cancel-over'"
			onmouseout="this.className='btn-cancel'"
			onmousedown="this.className='btn-cancel-down'" type="button"
			onclick="util.closeWindow('specialMedCheck');" /> -->
	</div>
</div>