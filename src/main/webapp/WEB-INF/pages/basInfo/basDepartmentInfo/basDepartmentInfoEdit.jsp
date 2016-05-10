<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/chnToLetter.js"></script>

<script type="text/javascript">
	var ctrlsID = ["department_number","department_fullname","department_shortname","department_chiefperson","department_address",
	               "department_zipcode","department_tel","department_fax","department_email","department_bank","department_account",
	               "department_tax","department_distributionhouse","department_zerowhole","department_deliverycheck","department_storagecheck","department_shopprice",
	               "department_shopcommission","department_distributionweight","department_pricecontrol","department_lowtemperature","department_hightemperature","department_refrige",
	               "department_storetype","department_enterprisecode"];
	
	//确认BUTTON
	var sumbitButtonID = "submitDepartment";

	$(function() {
		//实现注册该控件
		$('#department_chnpy').combobox({
			url : "",
			editable:false,
		});
		$('#department_chnpy').combobox("setValue", $('#department_chnpy').attr("value"));
		var keyPress = new pageKeyPress();
		keyPress.bindKeyPressToCtrl(ctrlsID, sumbitButtonID);
		
		
		//复选框初始化赋值
		var CK = document.getElementsByTagName('input');
		for (var i = 0; i < CK.length; i++) {
			if (CK[i].type == "checkbox"
					&& document.getElementsByName(CK[i].id)[0].value == "1") {
				CK[i].checked = true;
			} else if (CK[i].type == "checkbox"
					&& document.getElementsByName(CK[i].id)[0].value == "0") {
				CK[i].checked = false;
			}
		}
	});

	function submitForm() {
		var getCK = document.getElementsByTagName('input');
		for (var i = 0; i < getCK.length; i++) {
			if (getCK[i].type == "checkbox" && getCK[i].checked == true) {
				var j = document.getElementsByName(getCK[i].id);
				j[0].value = 1;
			} else if (getCK[i].type == "checkbox" && getCK[i].checked == false) {
				var j = document.getElementsByName(getCK[i].id);
				j[0].value = 0;
			}
		}
		
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
	
	function checkDepartmentNumber(department_number){
		var data = util.get(global_param.context_name + "/basInfo/basDepartmentInfo/checkDepartmentNumber?department_number="+department_number);
		if(data == false){
			util.show("部门编号已存在");
			$("#department_number").attr("value","");
		}
	}
</script>

<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
			<form:form id="_form" action="${formUrl}" method="${method}" onsubmit="return false;">
				<input name="department_id" type="hidden" value="${basDepartmentInfo.department_id}" />
				<input name="SPRING_TOKEN" type="hidden"
					value="${sessionScope.SPRING_TOKEN}" />
				<div>	
					<div class="">
		        		<div class="SelectAll" >
		        			<input name="department_discountModify" type ="hidden" value="${basDepartmentInfo.department_discountModify}" />
							<label for="department_discountModify" style="color:red;margin-left: 50px">折扣可改</label>
							<input id="department_discountModify" type ="checkbox" style="width:20px" />
						</div>
					</div>
				</div>
				<div class="">
					<div class="Dialog-form-title">
						<label for="department_number" class="field">部门编号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="department_number" name="department_number" class="easyui-validatebox"
							data-options="required:true"  value="${basDepartmentInfo.department_number}" onblur="checkDepartmentNumber($(this).val())"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_fullname" class="field">部门全称：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="department_fullname" name="department_fullname" class="easyui-validatebox" onblur="chnToLetter($(this).val(),'department_chnpy')"
							data-options="required:true,validType:'length[1,20]'"  value="${basDepartmentInfo.department_fullname}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_chnpy" class="field">拼音码：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="department_chnpy" name="department_chnpy" class="easyui-combobox" style="width:152px" value="${basDepartmentInfo.department_chnpy}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_shortname" class="field">简称：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="department_shortname" name="department_shortname" class="easyui-validatebox"
							 data-options="required:'true',validType:'length[1,20]'"  value="${basDepartmentInfo.department_shortname}" />
					</div>
				</div> 
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_chiefperson" class="field">负责人：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="department_chiefperson" name="department_chiefperson" class="easyui-validatebox"
							data-options="validType:'length[1,20]'" value="${basDepartmentInfo.department_chiefperson}" />
					</div>
				</div> 
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_address" class="field">地址：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="department_address" name="department_address" class="easyui-validatebox"
							data-options="validType:'length[1,20]'"  value="${basDepartmentInfo.department_address}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_zipcode" class="field">邮编：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="department_zipcode" name="department_zipcode" class="easyui-validatebox"
							  value="${basDepartmentInfo.department_zipcode}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_tel" class="field">电话 ：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="department_tel" name="department_tel" class="easyui-validatebox"
							data-options="validType:['phone','maxLength[12]']" value="${basDepartmentInfo.department_tel}" />
					</div>
				</div> 
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_fax" class="field">传真：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="department_fax" name="department_fax" class="easyui-validatebox"
							 data-options="validType:['fax','maxLength[12]']" value="${basDepartmentInfo.department_fax}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_email" class="field">电子邮箱 ：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="department_email" name="department_email" class="easyui-validatebox"
							validtype="email" invalidMessage="邮箱格式不正确" value="${basDepartmentInfo.department_email}" />
					</div>
				</div> 
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_bank" class="field">开户行：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="department_bank" name="department_bank" class="easyui-validatebox"
							data-options="validType:'length[1,20]'"  value="${basDepartmentInfo.department_bank}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_account" class="field">帐号 ：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="department_account" name="department_account" class="easyui-validatebox"
							data-options="validType:'length[1,20]'" value="${basDepartmentInfo.department_account}" />
					</div>
				</div>  
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_tax" class="field">税号 ：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="department_tax" name="department_tax" class="easyui-validatebox"
							data-options="validType:'length[1,20]'" value="${basDepartmentInfo.department_tax}" />
					</div>
				</div>  
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_distributionhouse" class="field">配送库房：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="department_distributionhouse" name="department_distributionhouse" class="easyui-validatebox"
							data-options="validType:'length[1,20]'"  value="${basDepartmentInfo.department_distributionhouse}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_zerowhole" class="field">零整 ：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="department_zerowhole" name="department_zerowhole" class="easyui-validatebox"
							required="true" data-options="validType:'length[1,20]'" value="${basDepartmentInfo.department_zerowhole}" />
					</div>
				</div>  
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_deliverycheck" class="field">出库审核 ：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="department_deliverycheck" name="department_deliverycheck" class="easyui-validatebox"
							data-options="validType:'length[1,20]'" value="${basDepartmentInfo.department_deliverycheck}" />
					</div>
				</div>  
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_storagecheck" class="field">入库审核：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="department_storagecheck" name="department_storagecheck" class="easyui-validatebox"
							data-options="validType:'length[1,20]'"  value="${basDepartmentInfo.department_storagecheck}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_shopprice" class="field">按店定价 ：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="department_shopprice" name="department_shopprice" class="easyui-validatebox"
							data-options="validType:'length[1,20]'" value="${basDepartmentInfo.department_shopprice}" />
					</div>
				</div>  
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_shopcommission" class="field">按店提成 ：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="department_shopcommission" name="department_shopcommission" class="easyui-validatebox"
							data-options="validType:'length[1,20]'" value="${basDepartmentInfo.department_shopcommission}" />
					</div>
				</div>  
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_distributionweight" class="field">配货权重：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="department_distributionweight" name="department_distributionweight" class="easyui-validatebox"
							data-options="validType:'length[1,20]'"  value="${basDepartmentInfo.department_distributionweight}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_pricecontrol" class="field">不控制价格  ：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="department_pricecontrol" name="department_pricecontrol" class="easyui-validatebox"
							data-options="validType:'length[1,20]'" value="${basDepartmentInfo.department_pricecontrol}" />
					</div>
				</div>  
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_lowtemperature" class="field">低温 ：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="department_lowtemperature" name="department_lowtemperature" class="easyui-validatebox"
							required="true" data-options="validType:'length[1,20]'" value="${basDepartmentInfo.department_lowtemperature}" />
					</div>
				</div>  
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_hightemperature" class="field">高温：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="department_hightemperature" name="department_hightemperature" class="easyui-validatebox"
							required="true" data-options="validType:'length[1,20]'"  value="${basDepartmentInfo.department_hightemperature}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_refrige" class="field">是否冷藏 ：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="department_refrige" name="department_refrige" class="easyui-validatebox"
							required="true" data-options="validType:'length[1,20]'" value="${basDepartmentInfo.department_refrige}" />
					</div>
				</div>  
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_storetype" class="field">门店类型 ：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="department_storetype" name="department_storetype" class="easyui-validatebox"
							data-options="validType:'length[1,20]'" value="${basDepartmentInfo.department_storetype}" />
					</div>
				</div>  
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_enterprisecode" class="field">企业编码 ：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="department_enterprisecode" name="department_enterprisecode" class="easyui-validatebox"
							data-options="validType:'length[1,20]'" value="${basDepartmentInfo.department_enterprisecode}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="deparment_acceptAmountDiscount" class="field">最大折扣 ：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="deparment_acceptAmountDiscount" name="deparment_acceptAmountDiscount" class="easyui-validatebox"
							data-options="validType:'length[1,20]'" value="${basDepartmentInfo.deparment_acceptAmountDiscount}" />
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submitDepartment" class="btn-save"
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