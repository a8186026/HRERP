<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	function submitForm() {
		var data = util.submit('_form');
		if (data) {
			if (data.result == "success") {
				util.show(data.message);
				$('#dg').treegrid('reload');
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
			<form:form id="_form" action="${formUrl}" method="${method}">
				<input name="dict_sub_id" type="hidden"
					value="${sysDictionarySub.dict_sub_id}" />
				<input name="SPRING_TOKEN" type="hidden"
					value="${sessionScope.SPRING_TOKEN}" />
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="dict_parent" class="field">数据字典：</label>
					</div>
					<div class="Dialog-form-item">
						<input class="easyui-validatebox" readonly="readonly"
							value="${sysDictionary.dict_name}" /> <input type="hidden"
							name="dict_parent" value="${sysDictionary.dict_id}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="dict_sub_name" class="field">名称：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="dict_sub_name" name="dict_sub_name"
							class="easyui-validatebox"
							data-options="required:true,validType:['length[0,16]']"
							value="${sysDictionarySub.dict_sub_name}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="dict_sub_value" class="field">值：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="dict_sub_value" name="dict_sub_value" type="text"
							class="easyui-validatebox"
							data-options="validType:'length[0,20]'"
							value="${sysDictionarySub.dict_sub_value}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="orders" class="field">顺序：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="orders" name="orders" type="text"
							value="${sysDictionarySub.orders}" class="easyui-validatebox" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="dict_sub_note" class="field">备注：</label>
					</div>
					<div class="Dialog-form-item">
						<textarea id="dict_sub_note" name="dict_sub_note" type="text"
							class="easyui-validatebox textareaW1" maxlength="100">${sysDictionarySub.dict_sub_note}</textarea>
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="" class="btn-save"
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