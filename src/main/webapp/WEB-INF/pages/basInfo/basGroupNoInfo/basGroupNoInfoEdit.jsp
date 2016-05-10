<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/chnToLetter.js"></script>

<script type="text/javascript">

	$('#pro_department_id').combobox({//edit页面中的库房信息的下拉框列表，为防止与searchForm中的department_id冲突，取名pro_department_id
		url : global_param.context_name + "/basInfo/basGroupNoInfo/getDepartmentList",
		method :"get",
		valueField : "department_id",
		textField : "department_fullname",
	});
	
	$('#productId').combobox({//edit页面中的产品信息的下拉框列表
		url : global_param.context_name + "/basInfo/basGroupNoInfo/getProINfoList",
		method :"get",
		valueField : "product_id",
		textField : "product_commonname",
	}); 
	
	var ctrlsID = ["productId","pro_department_id","another_pro_group_no","pro_location_no"];//快捷键盘操作，回车到下一个input框
	 
	//确认BUTTON
	var sumbitButtonID = "submitGroupNum";
	
	$(function() {
		var keyPress = new pageKeyPress();
		keyPress.bindKeyPressToCtrl(ctrlsID, sumbitButtonID);

	});
	
	function submitGroupNumForm() {
		var data; 
		if( ($('#productId').combobox('getValue') == "") || ($('#pro_department_id').combobox('getValue') == "") ){
			util.show('产品和库房不能为空');
		 }else{
			 data = util.submit('_GNForm');
		 }
		if (data) {
			if (data.result == "success") {
				util.show(data.message);
				$('#dg').datagrid('reload');//focus,增删改查后的reload在这里！
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
			<form:form id="_GNForm" action="${formUrl}" method="${method}" onsubmit="return false;">
				<input name="prod_id" type="hidden" value="${proStockPriceVO.prod_id}" />
				
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="productId" class="field"><font color="#FF0000">*</font>产品：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="productId" name="product_id" class="easyui-combobox"  value="${proStockPriceVO.product_id}" type="text"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="pro_department_id" class="field"><font color="#FF0000">*</font>库房：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="pro_department_id" name="department_id" class="easyui-combobox" value="${proStockPriceVO.department_id}" type="text"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="another_pro_group_no" class="field">组号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="another_pro_group_no" name="pro_group_no" class="easyui-validatebox"
							data-options="validType:['num','nosp']"  value="${proStockPriceVO.pro_group_no}"/>
						
					</div>
				</div>
				
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="pro_location_no" class="field">货位号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="pro_location_no" name="pro_location_no" class="easyui-validatebox"
							data-options="validType:['num','nosp']"  value="${proStockPriceVO.pro_location_no}" />
					</div>
				</div>
				
			</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submitGroupNum" class="btn-save"
			onmouseover="this.className='btn-save-over'"
			onmouseout="this.className='btn-save'"
			onmousedown="this.className='btn-save-down'" type="button"
			onclick="submitGroupNumForm();" /> <input id="" class="btn-cancel"
			onmouseover="this.className='btn-cancel-over'"
			onmouseout="this.className='btn-cancel'"
			onmousedown="this.className='btn-cancel-down'" type="button"
			onclick="util.closeWindow('editWindow');" />
	</div>
</div>