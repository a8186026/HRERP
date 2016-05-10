<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript">
	var ctrlsID = ["acc_name","acc_bank","acc_no","acc_addr","acc_rmtway","acc_payway",
	               "acc_bond","acc_tax","acc_fnlnote","acc_istaxpayer"];
	 
	//确认BUTTON
	var sumbitButtonID = "submitAccount";
	util.select({
			id : 'acc_istaxpayer',
			param : {
				typeCode : 'YN'
			}
		});
	
	$('#acc_paydate').datebox({
		required:true,	
	});
	if($('#acc_paydate').val()!="")
		$('#acc_paydate').datebox("setValue",util.toDate($('#acc_paydate').val()));
		
	
	$(function() {
		var keyPress = new pageKeyPress();
		keyPress.bindKeyPressToCtrl(ctrlsID, sumbitButtonID);

	});
	
	function submitAccountForm() {
		var data = util.submit('_accountForm');
		if (data) {
			if (data.result == "success") {
				util.show(data.message);
				$('#AccountDg').datagrid('reload');
				util.closeWindow('editAccountWindow');
			} else {
				util.error(data.message);
			}
		}
	}
</script>

<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
			<form:form id="_accountForm" action="${formUrl}" method="${method}" onsubmit="return false;">
				<input name="acc_id" type="hidden" value="${basAccount.acc_id}" />
				<input name="acc_type" type="hidden" value="${basAccount.acc_type}" />
				<input name="acc_type_id" type="hidden" value="${basAccount.acc_type_id}" />
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="acc_name" class="field">开户户名：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="acc_name" name="acc_name" class="easyui-validatebox"
							data-options="validType:'length[1,20]'"  value="${basAccount.acc_name}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="acc_bank" class="field">开户银行：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="acc_bank" name="acc_bank" class="easyui-validatebox"
							data-options="validType:'length[1,20]'"  value="${basAccount.acc_bank}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="acc_no" class="field">开户账号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="acc_no" name="acc_no" class="easyui-validatebox"
							data-options="validType:'length[1,20]'"  value="${basAccount.acc_no}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="acc_addr" class="field">注册地址：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="acc_addr" name="acc_addr" class="easyui-validatebox"
							data-options="validType:'length[1,20]'"  value="${basAccount.acc_addr}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="acc_rmtway" class="field">付款方式：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="acc_rmtway" name="acc_rmtway" class="easyui-validatebox"
							data-options="validType:'length[1,20]'"  value="${basAccount.acc_rmtway}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="acc_payway" class="field">结款方式：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="acc_payway" name="acc_payway" class="easyui-validatebox"
							data-options="validType:'length[1,20]'"  value="${basAccount.acc_payway}" />
					</div>
				</div>
				<div class="float-l">
								<div class="Dialog-form-title">
									<label for="acc_paydate" class="field">结款日期：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="acc_paydate" name="acc_paydate" class="" style="width:152px"
										data-options="required:true,validType:'length[1,20]'"  value="${basAccount.acc_paydate}" />
								</div>
							</div> 
				<div class="float-l">
								<div class="Dialog-form-title">
									<label for=acc_bond class="field">保证金：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="acc_bond" name="acc_bond" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${basAccount.acc_bond}" />
								</div>
							</div>
				<div class="float-l">
								<div class="Dialog-form-title">
									<label for="acc_tax" class="field">税号：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="acc_tax" name="acc_tax" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${basAccount.acc_tax}" />
								</div>
							</div>
				<div class="float-l">
								<div class="Dialog-form-title">
									<label for="acc_fnlnote" class="field">财务备注：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="acc_fnlnote" name="acc_fnlnote" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${basAccount.acc_fnlnote}" />
								</div>
							</div>
				<div class="float-l">
								<div class="Dialog-form-title">
									<label for="acc_istaxpayer" class="field">是否为一般纳税人：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="acc_istaxpayer" name="acc_istaxpayer" class="easyui-combobox" style = "width:152px"
										data-options="validType:'length[1,20]'"  value="${basAccount.acc_istaxpayer}" />
								</div>
							</div>
			</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submitAccount" class="btn-save"
			onmouseover="this.className='btn-save-over'"
			onmouseout="this.className='btn-save'"
			onmousedown="this.className='btn-save-down'" type="button"
			onclick="submitAccountForm();" /> <input id="" class="btn-cancel"
			onmouseover="this.className='btn-cancel-over'"
			onmouseout="this.className='btn-cancel'"
			onmousedown="this.className='btn-cancel-down'" type="button"
			onclick="util.closeWindow('editAccountWindow');" />
	</div>
</div>