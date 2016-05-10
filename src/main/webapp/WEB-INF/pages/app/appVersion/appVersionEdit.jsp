<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	$(function() {
		util.datebox('publish_time', {
			dateFmt:"yyyy-MM-dd hh:mm:ss"
		});
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
			<form:form id="_form" action="${formUrl}" method="${method}">
				<input name="app_version_id" type="hidden"
					value="${appVersion.app_version_id}" />
				<input name="SPRING_TOKEN" type="hidden"
					value="${sessionScope.SPRING_TOKEN}" />
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="app_version" class="field">版本号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="app_version_no" name="app_version_no"
							class="easyui-validatebox"
							data-options="required:true,validType:['length[1,20]']"
							value="${appVersion.app_version_no}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="publish_time" class="field">发布时间：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="publish_time" name="publish_time" type="text"
							class="easyui-validatebox"
							data-options="validType:'length[1,20]'"
							value="${appVersion.publish_time}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="update_content" class="field">更新内容：</label>
					</div>
					<div class="Dialog-form-item">
						<textarea id="update_content" name="update_content"
							class="textareaW1" maxlength="1000">${appVersion.update_content}</textarea>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="apk_file" class="field">apk文件上传：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="apk_file" name="apk_file" class="easyui-validatebox"
							data-options="required:true]" value="${appVersion.apk_file}" />
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