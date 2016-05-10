<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">



function submitForm() {
	
	

	var data = util.submit('_form');
	if (data) {
		if (data.result == "success") {
			util.show(data.message);
			$('#dg').datagrid('reload');
			util.closeWindow('editWindowAdd');
		} else {
			util.error(data.message);
		}
	}

}
</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
		
		     <div class="float-l">
					<div class="Dialog-form-title">
						<label for="mem_card_type" class="field">会员卡类别：</label>
					</div>
					<div class="Dialog-form-item">
						<input   value="${memCardManage.mem_card_type}"  style="width:152px; background:;border:0"  readonly="readonly" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="mem_card_number" class="field">会员卡号：</label>
					</div>
					<div class="Dialog-form-item">
						<input   value="${memCardManage.mem_card_number}"  style="width:152px; background:;border:0" readonly="readonly" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="mem_card_name" class="field"> 会员卡姓名：</label>
					</div>
					<div class="Dialog-form-item">
						<input   value="${memCardManage.mem_card_name}"  style="width:152px; background:;border:0" readonly="readonly" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="mem_card_point" class="field">原有积分：</label>
					</div>
					<div class="Dialog-form-item">
						<input   value="${memCardManage.mem_card_point}"  style="width:152px; background:;border:0" readonly="readonly" />
					</div>
				</div>
		      <div class="float-l">
					<div class="Dialog-form-title">
						<label for="mem_card_addPoint" class="field">追加积分：</label>
					</div>
					<div class="Dialog-form-item">
						<input   value="${memCardManage.mem_card_addPoint}"  style="width:152px; background:;border:0" readonly="readonly" />
					</div>
				</div>
				
		       
			<form:form id="_form" action="${formUrl}" method="${method}">
				<input name="SPRING_TOKEN" type="hidden" value="${sessionScope.SPRING_TOKEN}" />
				<input id="mem_card_number" name="mem_card_number" type="hidden" value="${memPointManage.mem_card_number}" />
				
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="mem_increasePoint" class="field">增加积分：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="mem_increasePoint" name="mem_increasePoint"
							data-options="required:true" style="width:152px;" />
					</div>
				</div>
				
			
		</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submitMem" class="btn-save"
			onmouseover="this.className='btn-save-over'"
			onmouseout="this.className='btn-save'"
			onmousedown="this.className='btn-save-down'" type="button"
			onclick="submitForm();" /> <input id="" class="btn-cancel"
			onmouseover="this.className='btn-cancel-over'"
			onmouseout="this.className='btn-cancel'"
			onmousedown="this.className='btn-cancel-down'" type="button"
			onclick="util.closeWindow('editWindowAdd');" />
	</div>
</div>