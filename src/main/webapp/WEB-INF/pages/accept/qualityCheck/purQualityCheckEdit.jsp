<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript">
	var ctrlsID = ["accept_goodNumber","accept_batchCode","accept_batchSmallCode","accept_sampleUnit",
	               "accept_sampleNumber","accept_checkQualitedNumber","accept_productionDate",
	               "accept_expirationDate","accept_sterilizationbDate","accept_sterilizationbNum",
	               "department_id","accept_quantityPrintType","accept_quantityCondition","accept_checkConclusion"];
	
	//确认BUTTON
	var sumbitButtonID = "submitCheck";
	
	$(function() {
		//对批号，有效期，生产日期，灭菌批号，灭菌日期进行判定
		for(var i=0;i<ctrlsID.length;i++){
			if(ctrlsID[i] == "accept_batchCode"&&$("#IsBatchCode").val()==0){
				ctrlsID.splice(i, 1); //删除批号
				$("#accept_batchCode").attr("disabled","true");
				i--;
			}else if(ctrlsID[i] == "accept_productionDate"&&$("#IsProductionDate").val()==0){
				ctrlsID.splice(i, 1); //删除生产日期
				$("#accept_productionDate").attr("disabled","true");
				i--;
			}else if(ctrlsID[i] == "accept_expirationDate"&&$("#IsExpirationDate").val()==0){
				ctrlsID.splice(i, 1); //删除有效期至
				$("#accept_expirationDate").attr("disabled","true");
				i--;
			}else if(ctrlsID[i] == "accept_sterilizationbDate"&&$("#IsSterilizationbDate").val()==0){
				ctrlsID.splice(i, 1); //删除灭菌日期
				$("#accept_sterilizationbDate").attr("disabled","true");
				i--;
			}else if(ctrlsID[i] == "accept_sterilizationbNum"&&$("#IsSterilizationbNum").val()==0){
				ctrlsID.splice(i, 1); //删除灭菌批号
				$("#accept_sterilizationbNum").attr("disabled","true");
				i--;
			}
		}
		
		//部门
		$('#department_id').combobox({
			url : global_param.context_name + "/basInfo/basDepartmentInfo/getList",
			method :"get",
			valueField : "department_id",
			textField : "department_fullname",
			editable:false,
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
		
		var keyPress = new pageKeyPress();
		keyPress.bindKeyPressToCtrl(ctrlsID, sumbitButtonID);
		
		
	});

	function submitForm() {
		//收到的数量
		var number = Number($("#number").val());
		//抽样数量验证
		if(Number($("#accept_sampleUnit").val())>number){
			util.show("抽样数量不能大于总数");
			return;
		}
		//合格数量验证
		if(Number($("#accept_checkQualitedNumber").val())>number){
			util.show("合格数量不能大于总数");
			return;
		}
		
		//日期先后顺序验证
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
		if (data) {
			if (data.result == "success") {
				util.show(data.message);
				$('#orderList_dg').datagrid('reload');
				util.closeWindow('editWindow');
			} else {
				util.error(data.message);
			}
		}
	}
	
	function countNum(){
		//合格数量验证
		if(Number($("#accept_checkQualitedNumber").val())>Number($("#number").val())){
			util.show("合格数量不能大于总数");
			return;
		}
		//不合格数量等于总数减去合格数量
		$("#accept_checkUnqualitedNumber").val(Number($("#number").val())-Number($("#accept_checkQualitedNumber").val()));
	}
	
</script>

<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
			<form:form id="_form" action="${formUrl}" method="${method}">
				<input name="SPRING_TOKEN" type="hidden"
					value="${sessionScope.SPRING_TOKEN}" />
					
				<input id="IsBatchCode" type="hidden" value="${proInfoManage.product_inputbatchnum}">	
				<input id="IsProductionDate" type="hidden" value="${proInfoManage.product_inputproductdate}">
				<input id="IsExpirationDate" type="hidden" value="${proInfoManage.product_inputvalidtime}">	
				<input id="IsSterilizationbDate" type="hidden" value="${proInfoManage.product_sterilizationtime}">	
				<input id="IsSterilizationbNum" type="hidden" value="${proInfoManage.product_sterilizationbnum}">		
					
					
				<input type="hidden" id="accept_check_id" name="accept_check_id" value="${purAcceptCheck.accept_check_id}">
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="ticket_id" class="field">票号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="ticket_id" class="easyui-validatebox"
							readOnly="readOnly" value="${ticket_id}" />
					</div>
				</div>
				 <div class="float-l">
					<div class="Dialog-form-title">
						<label for="product_commonName" class="field">产品名称：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="product_commonName" type="text"
							class="easyui-validatebox" value="${commonName}" readOnly="readonly"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="number" class="field">数量：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="number" type="text" class="easyui-validatebox" value="${purAcceptCheck.accept_acceptNumber}"
						readOnly="readonly"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_goodNumber" class="field">随货票号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_goodNumber" name="accept_goodNumber"
						data-options="required:true"  type="text" class="easyui-validatebox" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_batchCode" class="field">批号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_batchCode" name="accept_batchCode" type="text" 
						data-options="required:true" class="easyui-validatebox" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_batchSmallCode" class="field">批号小号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_batchSmallCode" name="accept_batchSmallCode" type="text" class="easyui-validatebox" 
						data-options="required:true"/>
					</div>
				</div>
				<div id="div_depart_id" class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_sampleUnit" class="field">抽样件数：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_sampleUnit" name="accept_sampleUnit" type="text"
							class="easyui-validatebox" style="width: 152px;"
							data-options="required:true,validType:['num','length[1,10]']" />
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
				
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for=accept_checkQualitedNumber class="field">质检合格数量：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_checkQualitedNumber" name="accept_checkQualitedNumber" type="text"
							class="easyui-validatebox" onblur="countNum()"
							data-options="required:true,validType:['num','length[1,10]']" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_checkUnqualitedNumber" class="field">质检不合格数量：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_checkUnqualitedNumber" name="accept_checkUnqualitedNumber"
							class="easyui-validatebox" readOnly="readonly"
							data-options="required:true,validType:['num','length[1,10]']" />
					</div>
				</div>
				
				<div id="div_email" class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_productionDate" class="field">生产日期：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_productionDate" name="accept_productionDate" type="text"
							class="easyui-datebox" style="width:152px" data-options="required:true"/>
					</div>
				</div>
				<div id="div_email" class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_expirationDate" class="field">有效期至：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_expirationDate" name="accept_expirationDate" type="text"
							class="easyui-datebox" style="width:152px" data-options="required:true"/>
					</div>
				</div>
				<div id="div_email" class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_sterilizationbDate" class="field">灭菌日期：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_sterilizationbDate" name="accept_sterilizationbDate" type="text"
							class="easyui-datebox" style="width:152px" data-options="required:true"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_sterilizationbNum" class="field">灭菌批号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_sterilizationbNum" name="accept_sterilizationbNum" 
						data-options="required:true" type="text" class="easyui-validatebox" />
					</div>
				</div>
				
				
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_id" class="field">部门：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="department_id" name="department_id" type="text"
							class="easyui-combobox" style="width: 152px;" data-options="required:true"/>
					</div>
				</div>
				<div id="div_depart_id" class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_quantityPrintType" class="field">打印格式：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_quantityPrintType" name="accept_quantityPrintType" type="text"
							class="easyui-combobox" style="width: 152px;" data-options="required:true"/>
					</div>
				</div>
				
				<div id="div_depart_id" class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_quantityCondition" class="field">质量状况：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_quantityCondition" name="accept_quantityCondition" type="text"
							class="easyui-combobox" style="width: 152px;" data-options="required:true"/>
					</div>
				</div>
				<div id="div_depart_id" class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_checkConclusion" class="field">验收结论：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_checkConclusion" name="accept_checkConclusion" type="text"
							class="easyui-combobox" style="width: 152px;" data-options="required:true"/>
					</div>
				</div>
				
			</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submitCheck" class="btn-save"
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