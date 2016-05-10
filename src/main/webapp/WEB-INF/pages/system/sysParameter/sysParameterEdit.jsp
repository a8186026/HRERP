<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	$(function() {
// 		// 性别下拉框
// 		util.select({
// 			id : 'sex',
// 			required : true,
// 			param : {
// 				typeCode : 'SEX'
// 			}
// 		});
// 		// 角色下拉框
// 		util.select({
// 			id : 'role_ids',
// 			valueField : 'personnel_id',
// 			multiple : true,
// 			url : global_param.context_name + "/system/role/list",
// 			param : {
// 				sort : "personnel_"
// 			},
// 			onLoadSuccess : function() {
// 				$("#role_ids").combobox('setValues', _role_ids);
// 			}
// 		});
// 		// 部门下拉框
// 		util.selectTree({
// 			id : 'dept_id',
// 			url : global_param.context_name + "/system/dept/list",
// 			idField : 'dept_id',
// 			textField : 'dept_name',
// 			param : {
// 				sort : "dept_id"
// 			}
// 		});
	});

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
				<input name="param_id" type="hidden"
					value="${sysParameter.param_id}" />
				<input name="SPRING_TOKEN" type="hidden"
					value="${sessionScope.SPRING_TOKEN}" />
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="menu_name" class="field">名称：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="param_name" name="param_name"
							class="easyui-validatebox"
							data-options="validType:'length[0,20]'"
							value="${sysParameter.param_name}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="param_code" class="field">编码：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="param_code" name="param_code" type="text"
							class="easyui-validatebox"
							data-options="validType:'length[0,20]'"
							value="${sysParameter.param_code}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="note" class="field">备注：</label>
					</div>
					<div class="Dialog-form-item">
						<textarea id="note" name="note" type="text"
							class="easyui-validatebox textareaW1" maxlength="100">${sysParameter.note}</textarea>
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