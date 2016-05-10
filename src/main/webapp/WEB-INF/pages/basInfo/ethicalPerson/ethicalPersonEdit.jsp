<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	$(function() {
	});
	
	function submitAccountForm() {
		var data = util.submit('_ethicalForm');
		if (data) {
			if (data.result == "success") {
				util.show(data.message);
				$('#ethicalPersonDg').datagrid('reload');
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
			<form:form id="_ethicalForm" action="${formUrl}" method="${method}" onsubmit="return false;">
				<input name="ethical_personId" type="hidden" value="${ethicalPerson.ethical_personId}" />
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="ethical_personCode" class="field">处方药人员编号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="ethical_personCode" name="ethical_personCode" class="easyui-validatebox"
							data-options="validType:'length[1,12]'"  value="${ethicalPerson.ethical_personCode}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="ethical_personName" class="field">处方药人员姓名：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="ethical_personName" name="ethical_personName" class="easyui-validatebox"
							data-options="validType:'length[1,20]'"  value="${ethicalPerson.ethical_personName}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="ethical_personPassword" class="field">处方药人员密码：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="ethical_personPassword" name="ethical_personPassword" type="password" class="easyui-validatebox"
							data-options="validType:'length[1,20]'"  value="${ethicalPerson.ethical_personPassword}" />
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submitAccount" class="btn-save"
			onmouseover="this.className='btn-save-over'"
			onmouseout="this.className='btn-save'"
			onmousedown="this.className='btn-save-down'" type="button"
			onclick="submitAccountForm();" /> <input id="" class="btn-cancel"
			onmouseover="this.className='btn-cancel-over'"
			onmouseout="this.className='btn-cancel'"
			onmousedown="this.className='btn-cancel-down'" type="button"
			onclick="util.closeWindow('editWindow');" />
	</div>
</div>