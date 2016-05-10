<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function submitForm() {
		var data = util.submit('_form');
		if (data) {
			if (data.result == "success") {
				util.show(data.message);
				$('#companyInfoDg').datagrid('reload');
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
				<input name="company_id" type="hidden" value="${companyInfo.company_id}" />
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="company_fullName" class="field">企业全称：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="company_fullName" name="company_fullName" class="easyui-validatebox"
							data-options="validType:'length[1,20]'"  value="${companyInfo.company_fullName}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="company_lisenceCode" class="field">许可证编号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="company_lisenceCode" name="company_lisenceCode" class="easyui-validatebox"
							data-options="validType:'length[1,20]'"  value="${companyInfo.company_lisenceCode}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="company_code" class="field">企业编号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="company_code" name="company_code" class="easyui-validatebox"
							data-options="validType:'length[1,20]'"  value="${companyInfo.company_code}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="company_drugCode" class="field">国家药监码：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="company_drugCode" name="company_drugCode" class="easyui-validatebox"
							data-options="validType:'length[1,20]'"  value="${companyInfo.company_drugCode}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="company_ticketStart" class="field">药监票号开始：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="company_ticketStart" name="company_ticketStart" class="easyui-validatebox"
							data-options="validType:'length[1,20]'"  value="${companyInfo.company_ticketStart}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="company_forbidDrug" class="field">经营禁售：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="company_forbidDrug" name="company_forbidDrug" class="easyui-validatebox"
							data-options="validType:'length[1,20]'"  value="${companyInfo.company_forbidDrug}" />
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
			onclick="submitForm();" /> <input id="" class="btn-cancel"
			onmouseover="this.className='btn-cancel-over'"
			onmouseout="this.className='btn-cancel'"
			onmousedown="this.className='btn-cancel-down'" type="button"
			onclick="util.closeWindow('editWindow');" />
	</div>
</div>