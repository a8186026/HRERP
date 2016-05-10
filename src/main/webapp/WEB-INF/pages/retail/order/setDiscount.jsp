<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">


function submitDiscountForm() {
	//表单验证
	$("#discount_form").form('enableValidation');
	var checkResult = $("#discount_form").form('validate');
	
	if(checkResult){
		if($("#input_order_product_unitPrice").val()==""){
			util.show("请先选择产品!");
			return;
		}
		if(Number($("#input_order_product_unitPrice").val())<Number($("#dicountedAmount").val())){
			util.show("折后价不能大于单价!");
			return;
		}
		alert(Number($("#dicountedAmount").val()).toFixed(priceDigit));
		
		//设置折后价
		$("#input_discountedPrice").val(Number($("#dicountedAmount").val()).toFixed(priceDigit));
		//重置折扣
		$("#input_product_discount").val((Number($("#input_discountedPrice").val())/
					Number($("#input_order_product_unitPrice").val())*100).toFixed(priceDigit));
		//重置总金额
		$("#input_order_product_amount").val((Number($("#input_discountedPrice").val())*
					Number($("#input_order_product_quantity").val())).toFixed(priceDigit));
		
		//设置打折授权人
		$("#discountPerson").val($("#dicountLoginId").val());
		//设置打折原因
		$("#discountReason").val($("#discountedReason").val());
		
		//聚焦到数量
		$("#input_order_product_quantity").focus();
		
		util.closeWindow('setDiscountWindow');
	}
};
</script>

<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
			<form:form id="discount_form" onsubmit="return false;">
				<input name="SPRING_TOKEN" type="hidden" value="${sessionScope.SPRING_TOKEN}" />
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="dicountLoginId" class="field">登陆号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="dicountLoginId" name="dicountLoginId" class="easyui-validatebox" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="dicountPassword" class="field">密码：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="dicountPassword" name="dicountPassword" class="easyui-validatebox" />
					</div>
				</div>
				
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="dicountedAmount" class="field">折后价：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="dicountedAmount" name="dicountedAmount" class="easyui-validatebox"
							data-options="required:true,validType:['decimal']" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="discountedReason" class="field">打折原因：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="discountedReason" name="discountedReason" class="easyui-validatebox" />
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
			onclick="submitDiscountForm();" /> <input id="" class="btn-cancel"
			onmouseover="this.className='btn-cancel-over'"
			onmouseout="this.className='btn-cancel'"
			onmousedown="this.className='btn-cancel-down'" type="button"
			onclick="util.closeWindow('setDiscountWindow');" />
	</div>
</div>