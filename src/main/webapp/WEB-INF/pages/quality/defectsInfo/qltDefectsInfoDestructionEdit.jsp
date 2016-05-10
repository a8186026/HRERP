<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">	
	//销毁日期
	$('#defects_destructionDate').datebox({
	required:true,	
	value:($('#defects_destructionDate').val()=="")?"":util.toDate($('#defects_destructionDate').val()),
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
				<input name="defects_id" type="hidden" value="${qltDefectsInfoVO.defects_id}" />
				<input name="SPRING_TOKEN" type="hidden"
					value="${sessionScope.SPRING_TOKEN}" />
		
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="defects_destructionReason" class="field">销毁原因：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="defects_destructionReason" name="defects_destructionReason" class="easyui-validatebox"
							value="${qltDefectsInfoVO.defects_destructionReason}" />
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="defects_destructionWay" class="field">销毁方式：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="defects_destructionWay" name="defects_destructionWay" class="easyui-validatebox"
							value="${qltDefectsInfoVO.defects_destructionWay}" />
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="defects_destructionPlace" class="field">销毁地点：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="defects_destructionPlace" name="defects_destructionPlace" class="easyui-validatebox"
							value="${qltDefectsInfoVO.defects_destructionPlace}" />
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="defects_destructionDate" class="field">销毁日期：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="defects_destructionDate" name="defects_destructionDate" class="easyui-datebox" 
							style="width: 152px;" value="${qltDefectsInfoVO.defects_destructionDate}" />
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="defects_transMode" class="field">运输工具：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="defects_transMode" name="defects_transMode" class="easyui-validatebox"
							value="${qltDefectsInfoVO.defects_transMode}" />
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="defects_transPerson" class="field">运输人员：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="defects_transPerson" name="defects_transPerson" class="easyui-validatebox"
							value="${qltDefectsInfoVO.defects_transPerson}" />
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="defects_destructionSituation" class="field">销毁后情况：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="defects_destructionSituation" name="defects_destructionSituation" class="easyui-validatebox"
							value="${qltDefectsInfoVO.defects_destructionSituation}" />
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="defects_destructionExecutor" class="field">销毁执行人：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="defects_destructionExecutor" name="defects_destructionExecutor" class="easyui-validatebox"
							value="${qltDefectsInfoVO.defects_destructionExecutor}" />
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="defects_destructionCustodian" class="field">销毁监督人：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="defects_destructionCustodian" name="defects_destructionCustodian" class="easyui-validatebox"
							value="${qltDefectsInfoVO.defects_destructionCustodian}" />
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