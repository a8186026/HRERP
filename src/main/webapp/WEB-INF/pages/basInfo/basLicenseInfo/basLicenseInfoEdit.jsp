<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript">
	var ctrlsID = ["license_name","license_no","license_starttime","license_endtime","license_issuer","license_tipdays","license_checkyeartime",
	               "license_person","license_fund","license_property","license_scope","license_remark","license_other"];
	 
	//确认BUTTON
	var sumbitButtonID = "submitLicense";

	$(function() {
		var keyPress = new pageKeyPress();
		keyPress.bindKeyPressToCtrl(ctrlsID, sumbitButtonID);

	});
	util.select({
		id : 'acc_istaxpayer',
		param : {
			typeCode : 'YN'
		}
	});
	
	$('#license_starttime').datebox({
		required:true,	
	});
	
	$('#license_endtime').datebox({
		required:true,	
	});
	$('#license_checkyeartime').datebox({
		required:true,	
	});
	if($('#license_starttime').val()!="")
		$('#license_starttime').datebox("setValue",util.toDate($('#license_starttime').val()));
	
	if($('#license_endtime').val()!="")
		$('#license_endtime').datebox("setValue",util.toDate($('#license_endtime').val()));
	
	if($('#license_checkyeartime').val()!="")
		$('#license_checkyeartime').datebox("setValue",util.toDate($('#license_checkyeartime').val()));
	
	function submitLicenseForm() {
		var data = util.submit('_licenseForm');
		if (data) {
			if (data.result == "success") {
				util.show(data.message);
				$('#licenseDg').datagrid('reload');
				util.closeWindow('editLicenseWindow');
			} else {
				util.error(data.message);
			}
		}
	}
</script>

<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
			<form:form id="_licenseForm" action="${formUrl}" method="${method}" onsubmit="return false;">
				<input name="license_id" type="hidden" value="${basLicenseInfo.license_id}" />
				<input name="license_type" type="hidden" value="${basLicenseInfo.license_type}" />
				<input name="license_type_id" type="hidden" value="${basLicenseInfo.license_type_id}" /> 
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="license_name" class="field">证照名称：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="license_name" name="license_name" class="easyui-validatebox"
							data-options="required:true"  value="${basLicenseInfo.license_name}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="license_no" class="field">证照编号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="license_no" name="license_no" class="easyui-validatebox"
							data-options="required:true,validType:['en','nosp']"  value="${basLicenseInfo.license_no}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="license_starttime" class="field">认证时间：</label>
					</div>
					<div class="Dialog-form-item"> 
						<input id="license_starttime" name="license_starttime" class="" style="width:152px"
							data-options="required:true"  value="${basLicenseInfo.license_starttime}" />
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="license_endtime" class="field">有效期至：</label>
					</div>
					<div class="Dialog-form-item"> 
						<input id="license_endtime" name="license_endtime" class="" style="width:152px"
							data-options="required:true"  value="${basLicenseInfo.license_endtime}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="license_issuer" class="field">发证机关：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="license_issuer" name="license_issuer" class="easyui-validatebox"
							value="${basLicenseInfo.license_issuer}" />
					</div>
				</div>  
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="license_tipdays" class="field">提示天数：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="license_tipdays" name="license_tipdays" class="easyui-validatebox"
							data-options="validType:['num','nosp']" value="${basLicenseInfo.license_tipdays}" />
					</div>
				</div> 
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="license_checkyeartime" class="field">年检截至：</label>
					</div>
					<div class="Dialog-form-item"> 
						<input id="license_checkyeartime" name="license_checkyeartime" class="" style="width:152px"
							data-options="required:true"  value="${basLicenseInfo.license_checkyeartime}" />
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="license_person" class="field">相关人员：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="license_person" name="license_person" class="easyui-validatebox"
							value="${basLicenseInfo.license_person}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="license_fund" class="field">证照资金：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="license_fund" name="license_fund" class="easyui-validatebox"
							data-options="validType:['num','nosp']" value="${basLicenseInfo.license_fund}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="license_property" class="field">证照性质：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="license_property" name="license_property" class="easyui-validatebox"
							value="${basLicenseInfo.license_property}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="license_scope" class="field">证照范围：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="license_scope" name="license_scope" class="easyui-validatebox"
							value="${basLicenseInfo.license_scope}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="license_remark" class="field">证照备注：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="license_remark" name="license_remark" class="easyui-validatebox"
							value="${basLicenseInfo.license_remark}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="license_other" class="field">证照其他：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="license_other" name="license_other" class="easyui-validatebox"
							value="${basLicenseInfo.license_other}" />
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submitLicense" class="btn-save"
			onmouseover="this.className='btn-save-over'"
			onmouseout="this.className='btn-save'"
			onmousedown="this.className='btn-save-down'" type="button"
			onclick="submitLicenseForm();" /> <input id="" class="btn-cancel"
			onmouseover="this.className='btn-cancel-over'"
			onmouseout="this.className='btn-cancel'"
			onmousedown="this.className='btn-cancel-down'" type="button"
			onclick="util.closeWindow('editLicenseWindow');" />
	</div>
</div>