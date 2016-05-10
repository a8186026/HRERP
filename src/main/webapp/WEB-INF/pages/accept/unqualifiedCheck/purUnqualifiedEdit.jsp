<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/chnToLetter.js"></script>
<script type="text/javascript">

     $(function(){
    	 $('#accept_checkUnqualitedReason').combobox({
    			url : global_param.context_name + "/system/combobox/lists?pid=881",
    			method :"get",
    			valueField : "cbs_chn",
    			textField : "cbs_chn",
    			editable:true
    		});
    	 $('#accept_treatMeasures').combobox({
 			url : global_param.context_name + "/system/combobox/lists?pid=895",
 			method :"get",
 			valueField : "cbs_chn",
 			textField : "cbs_chn",
 			editable:true
 		});
    	 $('#department_id').combobox({
  			url : global_param.context_name + "/basInfo/basDepartmentInfo/getList",
  			method :"get",
  			valueField : "department_id",
  			textField : "department_fullname",
  			editable:false
  		});
     });
     function submitForm(){
    	 var data = util.submit('_form');
 		if (data) {
 			if (data.result == "success") {
 				util.show(data.message);
 				$('#dg').datagrid('reload');
 				util.closeWindow('checkWindow');
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
			
				<input id="accept_quantityCheckStatus" name="accept_quantityCheckStatus" type="hidden" value="${purAcceptCheck.accept_quantityCheckStatus}" />
				
				<input name="SPRING_TOKEN" type="hidden"
					value="${sessionScope.SPRING_TOKEN}" />
				
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_checkUnqualitedReason" class="field">不合格原因：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_checkUnqualitedReason" name="accept_checkUnqualitedReason" class="easyui-validatebox" 
							value="${purAcceptCheck.accept_checkUnqualitedReason}" />
					</div>
				</div>  
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_treatMeasures" class="field">处理措施：</label>
					</div>
					<div class="Dialog-form-item">
						<input id=accept_treatMeasures name="accept_treatMeasures" class="easyui-validatebox" 
							value="${purAcceptCheck.accept_treatMeasures}" />
					</div>
				</div>  
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_id" class="field">选择存入库房编号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id=department_id name="department_id" class="easyui-validatebox" 
							value="${purAcceptCheck.department_id}" />
					</div>
				</div>  
				
			</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submitFactory" class="btn-save"
			onmouseover="this.className='btn-save-over'"
			onmouseout="this.className='btn-save'"
			onmousedown="this.className='btn-save-down'" type="button"
			onclick="submitForm();" /> <input id="" class="btn-cancel"
			onmouseover="this.className='btn-cancel-over'"
			onmouseout="this.className='btn-cancel'"
			onmousedown="this.className='btn-cancel-down'" type="button"
			onclick="util.closeWindow('checkWindow');" />
	</div>
</div>