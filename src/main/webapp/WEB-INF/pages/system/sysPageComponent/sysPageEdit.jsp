<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	function submitForm() {
		var data = util.submit('_form');
		if (data) {
			if (data.result == "success") {
				
				util.show(data.message);
				util.closeWindow('pageEditWindow');
		
				$('#pagedg').datagrid('reload');
				
			} else {
				util.error(data.message);
			}
		}
	}
</script>

<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
			<form:form id="_form" action="${formUrl_Page}" method="${method_Page}">
				<input name="id" type="hidden" value="${sysPageCtrl.id}" />
				<input name="SPRING_TOKEN" type="hidden"
					value="${sessionScope.SPRING_TOKEN}" />
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="page_name" class="field">页面名称：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="page_name" name="page_name"
							class="easyui-validatebox" data-options="required:true"
							value="${sysPageCtrl.page_name}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="page_jsp" class="field">页面jsp名称：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="page_jsp" name="page_jsp" class="easyui-validatebox"
							data-options="required:true"
							value="${sysPageCtrl.page_jsp}" />
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
			onclick="util.closeWindow('pageEditWindow');" />
	</div>
</div>