<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	function submitForm() {
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
				<input name="id" type="hidden" value="${purOrder.id}" />
				<input name="SPRING_TOKEN" type="hidden" value="${sessionScope.SPRING_TOKEN}" />
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="type" class="field">种类：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="type" name="type" class="easyui-validatebox"
							value="${purOrder.type}" />
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="contactPerson" class="field">联系人：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="contactPerson" name="contactPerson" class="easyui-validatebox"
							value="${purOrder.contactPerson}"  data-options="required:true" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="personinCharge" class="field">负责人：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="personinCharge" name="personinCharge" class="easyui-validatebox"
							value="${purOrder.personinCharge}" data-options="required:true" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="explain" class="field">说明：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="explain" name="explain" class="easyui-validatebox"
							value="${purOrder.explain}" data-options="required:true" />
					</div>
				</div>		
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="prepaid" class="field">预付款：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="prepaid" name="prepaid" class="easyui-validatebox"
							value="${purOrder.prepaid}" data-options="required:true" />
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="knotStyle" class="field">结款方式：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="knotStyle" name="knotStyle" class="easyui-validatebox"
							value="${purOrder.knotStyle}" data-options="required:true"  />
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="policyStyle" class="field">政策形势：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="policyStyle" name="policyStyle" class="easyui-validatebox"
							value="${purOrder.policyStyle}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="priceReason" class="field">价格原因：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="priceReason" name="priceReason" class="easyui-validatebox"
							value="${purOrder.priceReason}" />
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="orderTransMode" class="field">运输方式：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="orderTransMode" name="orderTransMode" class="easyui-validatebox"
							value="${purOrder.orderTransMode}" />
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="tempCtrlMode" class="field">温控方式：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="tempCtrlMode" name="tempCtrlMode" class="easyui-validatebox"
							value="${purOrder.tempCtrlMode}" />
					</div>
				</div>		
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="tempCtrlSituation" class="field">温控情况：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="tempCtrlSituation" name="tempCtrlSituation" class="easyui-validatebox"
							value="${purOrder.tempCtrlSituation}" />
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="transUnit" class="field">运输单位：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="transUnit" name="transUnit" class="easyui-validatebox"
							value="${purOrder.transUnit}" />
					</div>
				</div>	 
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="carryMode" class="field">承运方式：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="carryMode" name="carryMode" class="easyui-validatebox"
							value="${purOrder.carryMode}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="carryCompany" class="field">承运单位：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="carryCompany" name="carryCompany" class="easyui-validatebox"
							value="${purOrder.carryCompany}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="orderTransFunc" class="field">运输工具：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="orderTransFunc" name="orderTransFunc" class="easyui-validatebox"
							value="${purOrder.orderTransFunc}" />
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submitMedicine" class="btn-save"
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