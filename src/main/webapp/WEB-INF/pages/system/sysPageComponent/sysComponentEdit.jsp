<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	function submitForm() {
		var data = util.submit('Com_form');
		if (data) {
			if (data.result == "success") {
				
				util.show(data.message);
				util.closeWindow('componentsEditWindow');
		
				$('#componentsdg').datagrid('reload');
				
			} else {
				util.error(data.message);
			}
		}
	}
</script>

<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
			<form:form id="Com_form" action="${formUrl_Com}" method="${method_Com}">
				<input name="id" type="hidden" value="${sysComponentCtrl.id}" />
				<input name="page_id" id="page_id" type="hidden" value="${sysComponentCtrl.page_id}" />
				<input name="SPRING_TOKEN" type="hidden" value="${sessionScope.SPRING_TOKEN}" />
				
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="ctrl_name" class="field">控件名称：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="ctrl_name" name="ctrl_name"
							class="easyui-validatebox" data-options="required:true"
							value="${sysComponentCtrl.ctrl_name}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="ctrl_id" class="field">控件id：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="ctrl_id" name="ctrl_id"
							class="easyui-validatebox" data-options="required:true"
							value="${sysComponentCtrl.ctrl_id}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="ctrl_div_id" class="field">控件DivID：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="ctrl_div_id" name="ctrl_div_id"
							class="easyui-validatebox" data-options="required:true"
							value="${sysComponentCtrl.ctrl_div_id}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="ctrl_type" class="field">控件类型：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="ctrl_type" name="ctrl_type" class="easyui-combobox" style="width:152px;"
							data-options="valueField:'label',textField:'value',data:[{
							label:'input',value:'输入框'},{label:'button',value:'按钮'},{label:'combo',value:'下拉列表框'},{label:'datagrid',value:'datagrid'}]"
							value="${sysComponentCtrl.ctrl_type}" />
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
			onclick="util.closeWindow('componentsEditWindow');" />
	</div>
</div>