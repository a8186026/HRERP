<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">	
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
				<input name="maintain_id" type="hidden" value="${qltMaintainInfoVO.maintain_id}" />
				<input name="SPRING_TOKEN" type="hidden"
					value="${sessionScope.SPRING_TOKEN}" />
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="maintain_remark" class="field">养护措施 ：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="maintain_measure" name="maintain_measure" class="easyui-validatebox"
							value="${qltMaintainInfoVO.maintain_measure}" />
					</div>
				</div> 
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="maintain_quality" class="field">质量状况：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="maintain_quality" name="maintain_quality" class="easyui-validatebox"
							value="${qltMaintainInfoVO.maintain_quality}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="maintain_suggestion" class="field">处理意见：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="maintain_suggestion" name="maintain_suggestion" class="easyui-validatebox"
							value="${qltMaintainInfoVO.maintain_suggestion}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="maintain_remark" class="field">备注：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="maintain_remark" name="maintain_remark" class="easyui-validatebox"
							value="${qltMaintainInfoVO.maintain_remark}" />
					</div>
				</div>
				
			</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submit" class="btn-save"
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