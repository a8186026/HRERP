<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/chnToLetter.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>

<script type="text/javascript">

     $(function(){
    	 $('#department_id').combobox({
   			url : global_param.context_name + "/basInfo/basDepartmentInfo/getList",
   			method :"get",
   			valueField : "department_id",
   			textField : "department_fullname",
   			editable:false
   		});
    
    	//质量状况
 		$('#accept_quantityCondition').combobox({
 			url : global_param.context_name + "/system/combobox/lists?pid=883",
 			method :"get",
 			valueField : "cbs_chn",
 			textField : "cbs_chn",
 			editable:false,
 		});
 		//验收结论
 		$('#accept_checkConclusion').combobox({
 			url : global_param.context_name + "/system/combobox/lists?pid=887",
 			method :"get",
 			valueField : "cbs_chn",
 			textField : "cbs_chn",
 			editable:false,
 		});
 		//打印格式
 		$('#accept_quantityPrintType').combobox({
 			url : global_param.context_name + "/system/combobox/lists?pid=890",
 			method :"get",
 			valueField : "cbs_chn",
 			textField : "cbs_chn",
 			editable:false,
 		});
 		$("#accept_sum").val($("#accept_arrivalNumber").val()*$("#unitprice").val());
     });
 	
     function submitForm(){
    	 if($('#department_id').val()==""){
    		 util.show("请填入存入库房");	
    	 }
    	 var productionDate = $('#accept_productionDate').datebox('getValue');
 		var expirationDate = $('#accept_expirationDate').datebox('getValue');
 		var sterilizationbDate = $('#accept_sterilizationbDate').datebox('getValue');

 		var d1 = new Date(productionDate.replace(/\-/g, "\/"));  
 		var d2 = new Date(expirationDate.replace(/\-/g, "\/")); 
 		var d3 = new Date(sterilizationbDate.replace(/\-/g, "\/")); 
 		//日期验证提示
 		if(d1>d2){  
 			util.show("有效期至不能少于生产日期");  
 			return;
 		}
 		if(d1>d3){  
 			util.show("灭菌日期不能少于生产日期");
 			return;
 		}
    	 var data = util.submit('_form');
    	 alert(data);
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
		<!-- 	<input id="accept_quantityCheckStatus" name="accept_quantityCheckStatus" type="hidden" value="${purAcceptCheck.accept_quantityCheckStatus}" />
				 -->
				<input name="SPRING_TOKEN" type="hidden" value="${sessionScope.SPRING_TOKEN}" />
				<input id="accept_rejectNumber" name="accept_rejectNumber" class="easyui-validatebox" type="hidden"  value=0 />
				<input id="accept_sum" name="accept_sum" class="easyui-validatebox" value="" type="hidden"/>
			    <input id="accept_checkQualitedNumber" name="accept_checkQualitedNumber"type="hidden"class="easyui-validatebox" value="${purOrderList.quantity-purOrderList.arrivalNumber}"/>
				<input id="accept_checkUnqualitedNumber" name="accept_checkUnqualitedNumber" class="easyui-validatebox" value=0  type="hidden"/>
						
			<div class="float-l">
					<div class="Dialog-form-title">
						<label for="ticket_id" class="field">票号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="ticket_id" name="ticket_id" class="easyui-validatebox" disabled="disabled"
					  value="${purOrderList.ticket_id}" />
					</div>
				</div>
				
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="unitprice" class="field">小单收货单价：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="unitprice" name="unitprice" class="easyui-validatebox" disabled="disabled"
							 value="${purOrderList.unitprice}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="sum" class="field">小单订单金额：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="sum" name="sum" class="easyui-validatebox" disabled="disabled"
						  value="${purOrderList.sum}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="quantity" class="field">小单待收数量：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="quantity" name="quantity" class="easyui-validatebox" disabled="disabled"
							 value="${purOrderList.quantity-purOrderList.arrivalNumber}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_startPlace" class="field">起运地点：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_startPlace" name="accept_startPlace" class="easyui-validatebox"
							value="" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_startTime" class="field">起运时间：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_startTime" name="accept_startTime" class="easyui-datebox" style="width:152px"
							data-options="required:true" value="" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_tempCtrlMode" class="field">温控方式：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_tempCtrlMode" name="accept_tempCtrlMode" class="combobox" style="width:152px"
							value="" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_tempCtrlSituation" class="field">温控情况：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_tempCtrlSituation" name="accept_tempCtrlSituation" class="easyui-validatebox"
							 style="width:152px" value=""/>
					</div>
				</div>
			    <div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_startTemp" class="field">起运温度：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_startTemp" name="accept_startTemp" class="easyui-validatebox" 
							data-options="validType:['decimal','nosp']" value=""/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_arrivalTemp" class="field">到货温度：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_arrivalTemp" name="accept_arrivalTemp" class="easyui-validatebox" 
							data-options="validType:['decimal','nosp']" value=""/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_ambientTemp" class="field">环境温度：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_ambientTemp" name="accept_ambientTemp" class="easyui-validatebox" 
							data-options="validType:['decimal','nosp']" value=""/>
					</div>
				</div> 
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_transMode" class="field">运输方式：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_transMode" name="accept_transMode" class="combobox" style="width:152px"
							value=""/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_transCompany" class="field">运输单位：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_transCompany" name="accept_transCompany" class="easyui-validatebox"
							value=""/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_arrivalNumber" class="field">到货数量：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_arrivalNumber" name="accept_arrivalNumber" class="easyui-validatebox"
							 value="${purOrderList.quantity-purOrderList.arrivalNumber}" />
					</div>
				</div>
				<div id="div_depart_id" class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_sampleUnit" class="field">抽样件数：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_sampleUnit" name="accept_sampleUnit" type="text"
							class="easyui-validatebox" style="width: 152px;"
							data-options="required:true, validType:['num','length[1,10]']" />
					</div>
				</div>
				<div id="div_depart_id" class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_sampleNumber" class="field">抽样数量：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_sampleNumber" name="accept_sampleNumber" type="text"
							class="easyui-validatebox" style="width: 152px;"
							data-options="required:true,validType:['num','length[1,10]']" />
					</div>
				</div>
				
				
				<div id="div_email" class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_productionDate" class="field">生产日期：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_productionDate" name="accept_productionDate" type="text"
							class="easyui-datebox" data-options="required:true" style="width:152px"/>
					</div>
				</div>
				<div id="div_email" class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_expirationDate" class="field">有效期至：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_expirationDate" name="accept_expirationDate" type="text"
							class="easyui-datebox" data-options="required:true" style="width:152px" />
					</div>
				</div>
				<div id="div_email" class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_sterilizationbDate" class="field">灭菌日期：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_sterilizationbDate" name="accept_sterilizationbDate" type="text"
							class="easyui-datebox" data-options="required:true" style="width:152px"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_sterilizationbNum" class="field">灭菌批号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_sterilizationbNum" name="accept_sterilizationbNum" 
						 type="text" class="easyui-validatebox" data-options="required:true" />
					</div>
				</div>
				<div id="div_depart_id" class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_quantityPrintType" class="field">打印格式：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_quantityPrintType" name="accept_quantityPrintType" type="text"
							class="easyui-combobox"  style="width: 152px;" />
					</div>
				</div>
				<div id="div_depart_id" class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_quantityCondition" class="field">质量状况：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_quantityCondition" name="accept_quantityCondition" type="text"
							class="easyui-combobox"  style="width: 152px;" />
					</div>
				</div>
				<div id="div_depart_id" class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_checkConclusion" class="field">验收结论：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_checkConclusion" name="accept_checkConclusion" type="text"
							class="easyui-combobox"  style="width: 152px;" />
					</div>
				</div>
				
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_id" class="field">选择存入库房编号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id=department_id name="department_id" class="easyui-validatebox" 
							value=""  style="width: 152px;"/>
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