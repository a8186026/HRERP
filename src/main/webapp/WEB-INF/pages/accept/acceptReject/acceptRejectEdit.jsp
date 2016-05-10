<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript">
	var ctrlsID = ["edit_reject_batchnum","edit_reject_num","edit_reject_price","edit_reject_reason","edit_reject_quality","edit_reject_validtime","edit_reject_productdate"];
	 
	//确认BUTTON
	var sumbitButtonID = "submitAcceptReject";
	/* $('#edit_reject_validtime').datebox(function(value){
		return util.formatDate(value);
	});
	$('#edit_reject_productdate').datebox(function(value){
		return util.formatDate(value);
	}); */
		
	
	$(function() {
		//日期的格式！如果不转换，则显示当前日期。讨厌！
		$("#edit_reject_validtime").val(util.toDate($("#edit_reject_validtime").val()));
		$("#edit_reject_productdate").val(util.toDate($("#edit_reject_productdate").val()));
		
		var keyPress = new pageKeyPress();
		keyPress.bindKeyPressToCtrl(ctrlsID, sumbitButtonID);

	});
	
	function submitAcceptRejectForm() {
		var data = util.submit('_rejectForm');
		if (data) {
			if (data.result == "success") {
				util.show(data.message);
				$('#dg').datagrid('reload');
				util.closeWindow('editAcceptRejectWindow');
			} else {
				util.error(data.message);
			}
		}
	}
</script>

<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
			<form:form id="_rejectForm" action="${formUrl}" method="${method}" onsubmit="return false;">
				<input name="reject_id" type="hidden" value="${purAcceptReject.reject_id}" />
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="edit_reject_batchnum" class="field">批号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="edit_reject_batchnum" name="reject_batchnum" class="easyui-validatebox"
							data-options="required:true,validType:['num','nosp']"  value="${purAcceptReject.reject_batchnum}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="edit_reject_num" class="field">数量：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="edit_reject_num" name="reject_num" class="easyui-validatebox"
							data-options="required:true,validType:['decimal','nosp']" value="${purAcceptReject.reject_num}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="edit_reject_price" class="field">单价：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="edit_reject_price" name="reject_price" class="easyui-validatebox"
							data-options="required:true,validType:['decimal','nosp']" value="${purAcceptReject.reject_price}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="edit_reject_reason" class="field">拒收原因：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="edit_reject_reason" name="reject_reason" class="easyui-validatebox"
							data-options="required:true,validType:'length[1,50]'"  value="${purAcceptReject.reject_reason}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="edit_reject_quality" class="field">质量状况：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="edit_reject_quality" name="reject_quality" class="" style="width:152px"
							data-options="required:true,validType:'length[1,20]'"  value="${purAcceptReject.reject_quality}" />
					</div>
				</div> 
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="edit_reject_validtime" class="field">有效期：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="edit_reject_validtime" name="reject_validtime" class="easyui-datebox"
							 value="${purAcceptReject.reject_validtime}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="edit_reject_productdate" class="field">生产日期：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="edit_reject_productdate" name="reject_productdate" class="easyui-datebox"
							  value="${purAcceptReject.reject_productdate}" />
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submitAcceptReject" class="btn-save"
			onmouseover="this.className='btn-save-over'"
			onmouseout="this.className='btn-save'"
			onmousedown="this.className='btn-save-down'" type="button"
			onclick="submitAcceptRejectForm();" /> <input id="" class="btn-cancel"
			onmouseover="this.className='btn-cancel-over'"
			onmouseout="this.className='btn-cancel'"
			onmousedown="this.className='btn-cancel-down'" type="button"
			onclick="util.closeWindow('editAcceptRejectWindow');" />
	</div>
</div>