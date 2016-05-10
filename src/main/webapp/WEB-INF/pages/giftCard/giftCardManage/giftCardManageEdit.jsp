<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/chnToLetter.js"></script>

<script type="text/javascript">


$(function() {
	
	$('#department_id').combobox({
		url:global_param.context_name + "/basInfo/basDepartmentInfo/getList",
		method :"get",
		valueField : "department_id",
		textField : "department_fullname",
		editable:false,
	});	
	$('#department_id').combobox("setValue", $('#department_id').attr("value"));
	
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
				<input name="gift_card_id" type="hidden" value="${giftCardManage.gift_card_id}" />
				<input name="SPRING_TOKEN" type="hidden" value="${sessionScope.SPRING_TOKEN}" />
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_id" class="field">部门：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="department_id" name="department_id" class="easyui-combobox" style="width:152px"
							value="${giftCardManage.department_id}"/>
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="gift_card_fulfilAmount" class="field">代金卡满足金额：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="gift_card_fulfilAmount" name="gift_card_fulfilAmount" class="easyui-validatebox"
							value="${giftCardManage.gift_card_fulfilAmount}"  data-options="required:true, validType:'length[1,20]'" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="gift_card_offsetAmount" class="field">代金卡抵消金额：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="gift_card_offsetAmount" name="gift_card_offsetAmount" class="easyui-validatebox"
							value="${giftCardManage.gift_card_offsetAmount}" />
					</div>
				</div>				 
			</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submitGiftCard" class="btn-save"
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