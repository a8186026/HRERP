<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	$(function() {
	});

	function submitForm() {
		var data = util.submit('_form');
		if (data) {
			if (data.result == "success") {
				util.show(data.message);
				$('#groupdg').datagrid('reload');
				util.closeWindow('groupEditWindow');
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
				<input name="group_id" type="hidden" value="${sysGroup.group_id}" />
				<input name="SPRING_TOKEN" type="hidden"
					value="${sessionScope.SPRING_TOKEN}" />
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="group_name" class="field">名称：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="group_name" name="group_name"
							class="easyui-validatebox"
							data-options="required:true,validType:['zh_en','nosp','length[3,16]']"
							value="${sysGroup.group_name}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="note" class="field">备注：</label>
					</div>
					<div class="Dialog-form-item">
						<textarea id="note" name="note" type="text"
							class="easyui-validatebox textareaW1" maxlength="200">${sysGroup.note}</textarea>
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
			onclick="util.closeWindow('groupEditWindow');" />
	</div>
</div>