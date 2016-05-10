<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/chnToLetter.js"></script>

<script type="text/javascript">

var ctrlsID = ["medicine_code","medicine_proname","medicine_name","medicine_engname","medicine_effect",
               "medicine_description","medicine_note"];

//确认BUTTON
var sumbitButtonID = "submitMedicine";

$(function() {
	
	//实现注册该控件
	$('#medicine_chnpy').combobox({
		url : "",
		editable:false,
	});
	
	$('#medicine_chnpy').combobox("setValue", $('#medicine_chnpy').attr("value"));
	
	var keyPress = new pageKeyPress();
	keyPress.bindKeyPressToCtrl(ctrlsID, sumbitButtonID);

});

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
				<input name="medicine_id" type="hidden" value="${BasMedicineInfo.medicine_id}" />
				<input name="SPRING_TOKEN" type="hidden" value="${sessionScope.SPRING_TOKEN}" />
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="medicine_code" class="field">药品编码：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="medicine_code" name="medicine_code" class="easyui-validatebox"
							value="${BasMedicineInfo.medicine_code}" readonly = "readonly"/>
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="medicine_proname" class="field">药品学名：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="medicine_proname" name="medicine_proname" class="easyui-validatebox"
							value="${BasMedicineInfo.medicine_proname}"  data-options="required:true, validType:'length[1,20]'" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="medicine_name" class="field">药品名称：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="medicine_name" name="medicine_name" class="easyui-validatebox"
							value="${BasMedicineInfo.medicine_name}" onblur="chnToLetter($(this).val(),'medicine_chnpy')" 
							data-options="required:true, validType:'length[1,20]'" />
					</div>
				</div>		
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="medicine_chnpy" class="field">药品拼音码：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="medicine_chnpy" name="medicine_chnpy" class="easyui-combobox" style="width:152px"
							value="${BasMedicineInfo.medicine_chnpy}"
						/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="medicine_engname" class="field">药品英文名：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="medicine_engname" name="medicine_engname" class="easyui-validatebox"
							value="${BasMedicineInfo.medicine_engname}" data-options="required:true, validType:'length[1,20]'"  />
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="medicine_effect" class="field">药品功效：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="medicine_effect" name="medicine_effect" class="easyui-validatebox"
							value="${BasMedicineInfo.medicine_effect}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="medicine_description" class="field">药品简介：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="medicine_description" name="medicine_description" class="easyui-validatebox"
							value="${BasMedicineInfo.medicine_description}" />
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="medicine_note" class="field">药品注意事项：</label>
					</div>
					<div class="Dialog-form-item">
						<textarea id="medicine_note" name="medicine_note" type="text"
							class="easyui-validatebox textareaW1" maxlength="100">${BasMedicineInfo.medicine_note}</textarea>
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