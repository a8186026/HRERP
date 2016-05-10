<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript">
	/* var ctrlsID = ["rkfp_code","rkfp_supplier","rkfp_accepter","rkfp_category","acc_rmtway","acc_payway",
	               "acc_bond","acc_tax","acc_fnlnote","acc_istaxpayer"];
	 
	//确认BUTTON
	var sumbitButtonID = "editSumInvoice";
	
	$(function() {
		var keyPress = new pageKeyPress();
		keyPress.bindKeyPressToCtrl(ctrlsID, sumbitButtonID);

	}); */
	
	function editSumInvoiceForm() {
		var data = util.submit('_invoiceManageForm');
		if (data) {
			if (data.result == "success") {
				util.show(data.message);
				$('#sumDg').datagrid('reload');
				util.closeWindow('editSumInvoiceWindow');
			} else {
				util.error(data.message);
			}
		}
	}
</script>

<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
			<form:form id="_invoiceManageForm" action="${formUrl}" method="${method}" onsubmit="return false;">
				<%-- <input name="acc_id" type="hidden" value="${financialInvoice.acc_id}" />
				<input name="acc_type" type="hidden" value="${financialInvoice.acc_type}" />
				<input name="acc_type_id" type="hidden" value="${financialInvoice.acc_type_id}" /> --%>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="rkfp_code" class="field">发票号:</label>
					</div>
					<div class="Dialog-form-item">
						<input id="rkfp_code" name="rkfp_code" class="easyui-validatebox"
							data-options="required:true,validType:['nosp']" value="${financialInvoice.rkfp_code}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="rkfp_supplier" class="field">开票单位：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="rkfp_supplier" name="rkfp_supplier" class="easyui-validatebox"
							data-options="validType:'length[1,20]'"  value="${financialInvoice.rkfp_supplier}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="rkfp_accepter" class="field">发票名头：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="rkfp_accepter" name="rkfp_accepter" class="easyui-validatebox"
							data-options="validType:'length[1,20]'"  value="${financialInvoice.rkfp_accepter}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="rkfp_category" class="field">发票种类：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="update_rkfp_category" name="rkfp_category" class="easyui-validatebox"
							data-options="validType:'length[1,20]'"  value="${financialInvoice.rkfp_category}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="acc_rmtway" class="field">付款方式：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="acc_rmtway" name="acc_rmtway" class="easyui-validatebox"
							data-options="validType:'length[1,20]'"  value="${financialInvoice.acc_rmtway}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="acc_payway" class="field">结款方式：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="acc_payway" name="acc_payway" class="easyui-validatebox"
							data-options="validType:'length[1,20]'"  value="${financialInvoice.acc_payway}" />
					</div>
				</div>
				<div class="float-l">
								<div class="Dialog-form-title">
									<label for="acc_paydate" class="field">结款日期：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="acc_paydate" name="acc_paydate" class="" style="width:152px"
										data-options="required:true,validType:'length[1,20]'"  value="${financialInvoice.acc_paydate}" />
								</div>
							</div> 
				<div class="float-l">
								<div class="Dialog-form-title">
									<label for=acc_bond class="field">保证金：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="acc_bond" name="acc_bond" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${financialInvoice.acc_bond}" />
								</div>
							</div>
				<div class="float-l">
								<div class="Dialog-form-title">
									<label for="acc_tax" class="field">税号：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="acc_tax" name="acc_tax" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${financialInvoice.acc_tax}" />
								</div>
							</div>
				<div class="float-l">
								<div class="Dialog-form-title">
									<label for="acc_fnlnote" class="field">财务备注：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="acc_fnlnote" name="acc_fnlnote" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${financialInvoice.acc_fnlnote}" />
								</div>
							</div>
				<div class="float-l">
								<div class="Dialog-form-title">
									<label for="acc_istaxpayer" class="field">是否为一般纳税人：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="acc_istaxpayer" name="acc_istaxpayer" class="easyui-combobox" style = "width:152px"
										data-options="validType:'length[1,20]'"  value="${financialInvoice.acc_istaxpayer}" />
								</div>
							</div>
			</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="editSumInvoice" class="btn-save"
			onmouseover="this.className='btn-save-over'"
			onmouseout="this.className='btn-save'"
			onmousedown="this.className='btn-save-down'" type="button"
			onclick="editSumInvoiceForm();" /> <input id="" class="btn-cancel"
			onmouseover="this.className='btn-cancel-over'"
			onmouseout="this.className='btn-cancel'"
			onmousedown="this.className='btn-cancel-down'" type="button"
			onclick="util.closeWindow('editSumInvoiceWindow');" />
	</div>
</div>