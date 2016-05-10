<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/chnToLetter.js"></script>
<script type="text/javascript">
	var ctrlsID = ["factory_areacode","factory_filenumber","factory_code","factory_name","factory_shortname","factory_chnpy","factory_website","factory_address","factory_zipcode",
			"factory_tel","factory_contactperson","factory_chiefperson","factory_productvariety","factory_description","factory_license"];
	
	//确认BUTTON
	var sumbitButtonID = "submitFactory";

	$(function() {
		$('#factory_chnpy').combobox({
			url : "",
			method :"",
			valueField : "id",
			textField : "text",
			editable:false,
		});
		$('#factory_chnpy').combobox("setValue", $('#factory_chnpy').attr("value"));
		
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
	
	//检验是否重复
	function checkFactoryCode(factoryCode){
		var data = util.get(global_param.context_name + "/basInfo/basFactoryInfo/checkFactoryCode?factory_code="+factoryCode);
		if(data == false){
			util.show("厂家编码已存在");
			$("#factory_code").attr("value","");
		}
	}
</script>

<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
			<form:form id="_form" action="${formUrl}" method="${method}" onsubmit="return false;">
				<input name="factory_id" type="hidden" value="${basFactoryInfo.factory_id}" />
				<input name="SPRING_TOKEN" type="hidden"
					value="${sessionScope.SPRING_TOKEN}" />
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="factory_areacode" class="field">区号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="factory_areacode" name="factory_areacode" class="easyui-validatebox"
							data-options="required:true"  value="${basFactoryInfo.factory_areacode}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="factory_filenumber" class="field">档案号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="factory_filenumber" name="factory_filenumber" class="easyui-validatebox"
							value="${basFactoryInfo.factory_filenumber}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="factory_code" class="field">厂家编码：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="factory_code" name="factory_code" class="easyui-validatebox" onblur="checkFactoryCode($(this).val())"
							value="${basFactoryInfo.factory_code}"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="factory_name" class="field">厂家名称：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="factory_name" name="factory_name" class="easyui-validatebox"
							data-options="required:true" value="${basFactoryInfo.factory_name}" />
					</div>
				</div> 
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="factory_shortname" class="field">简称：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="factory_shortname" name="factory_shortname" class="easyui-validatebox"
							data-options="validType:'length[1,50]'"  value="${basFactoryInfo.factory_shortname}" 
							onblur="chnToLetter($(this).val(),'factory_chnpy')"/>
							
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="factory_chnpy" class="field">拼音码：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="factory_chnpy" name="factory_chnpy" type="text" value="${basFactoryInfo.factory_chnpy}"
							class="easyui-combobox" style="width: 152px;" />
					</div>
				</div> 
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="factory_website" class="field">网址：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="factory_website" name="factory_website" class="easyui-validatebox"
							value="${basFactoryInfo.factory_website}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="factory_address" class="field">地址：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="factory_address" name="factory_address" class="easyui-validatebox"
							value="${basFactoryInfo.factory_address}" />
					</div>
				</div> 
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="factory_zipcode" class="field">邮编：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="factory_zipcode" name="factory_zipcode" class="easyui-validatebox"
							data-options="validType:'length[1,6]'"  value="${basFactoryInfo.factory_zipcode}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="factory_tel" class="field">电话 ：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="factory_tel" name="factory_tel" class="easyui-validatebox"
							data-options="validType:['phone','maxLength[12]']" value="${basFactoryInfo.factory_tel}" />
					</div>
				</div> 
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="factory_contactperson" class="field">联系人：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="factory_contactperson" name="factory_contactperson" class="easyui-validatebox"
							value="${basFactoryInfo.factory_contactperson}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="factory_chiefperson" class="field">负责人 ：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="factory_chiefperson" name="factory_chiefperson" class="easyui-validatebox"
							value="${basFactoryInfo.factory_chiefperson}" />
					</div>
				</div> 
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="factory_productvariety" class="field">生产品种：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="factory_productvariety" name="factory_productvariety" class="easyui-validatebox"
							value="${basFactoryInfo.factory_productvariety}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="factory_description" class="field">厂家简介 ：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="factory_description" name="factory_description" class="easyui-validatebox"
							value="${basFactoryInfo.factory_description}" />
					</div>
				</div>  
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="factory_license" class="field">厂家证照 ：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="factory_license" name="factory_license" class="easyui-validatebox"
							value="${basFactoryInfo.factory_license}" />
					</div>
				</div>  
			</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submitFactory" class="btn-save"
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