<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	$(function() {
		$('#edit_inventory_countedNumber').bind('blur', function(){
			if(isNaN(this.value)){
		   		 util.show("请输入正确盘点数量！"+ $('#edit_inventory_countedNumber').val()+"123");
		   	 }else{
		   		$('#edit_inventory_profitLossNumber').attr("value",profitLossNum());
			     	multi();
		   	 }
		});
	});
	function  profitLossNum(){
		var a=$('#edit_inventory_countedNumber')[0].value;
		var b=$('#edit_stock_storageNumber').attr("value");
		if(a!=null&&b!=null){
			$("#edit_inventory_profitLossNumber").attr("value",a-b);
		}
	}	

	function multi(){
		var a=$('#edit_inventory_profitLossNumber')[0].value;
		var b=$('#edit_stock_purchasePrice').attr("value");
		if(a!=null&&b!=null){
			$("#edit_inventory_profitLossMoney").attr("value",a*b);
		}
	}
	function submitForm() {
		if($('#edit_inventory_countedNumber').val()==null||$('#edit_inventory_countedNumber').val()==""
			||$('#edit_inventory_reason').val()==null||$('#edit_inventory_reason').val()==""){
			util.show("请输入完整信息!"+$('#edit_inventory_countedNumber').val()+"123");
		}else{ 
			
			
		 	var result = util.get(global_param.context_name + 
		 			"${formUrl}?stock_info_id=${InventoryVO.stock_info_id}&inventory_countedNumber="+
		 			 $('#edit_inventory_countedNumber').val()+"&inventory_reason="+$('#edit_inventory_reason').val()+
		 			 "&inventory_profitLossNumber="+$('#edit_inventory_profitLossNumber').val()+
		 			 "&inventory_profitLossMoney="+$('#edit_inventory_profitLossMoney').val());
			if(result.result=="success"){
				util.show(result.message);
				$('#dg').datagrid('reload');
				util.closeWindow('editWindow');
			} 
			
		 
			/*  var data = util.submit('edit_form');
			if (data) {
				if (data.result == "success") {
					util.show(data.message);
					$('#dg').datagrid('reload');
					util.closeWindow('editWindow');
				} else {
					util.error(data.message);
				}
			}  */
		}
	}
 </script>
 
 
 <div class="easyui-layout" fit="true">
	 <div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
			<form id="edit_form" action="${formUrl}" method="${method}" onsubmit="return false;">
				<input id="edit_stock_info_id" name="stock_info_id" type="hidden" value="${InventoryVO.stock_info_id}" />
			<%-- 	<input name="SPRING_TOKEN" type="hidden"
					value="${sessionScope.SPRING_TOKEN}" />
		  --%>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="department_id" class="field">库房编号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="edit_department_id" name="department_id" readonly="readonly"  disabled="disabled"  style="background-color:#D3D3D3" class="easyui-validatebox"
						  value="${InventoryVO.department_fullname}" />
					</div>
				</div>
				</br></br>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="edit_product_code" class="field">产品编号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="edit_product_code" name="product_code" readonly="readonly"  disabled="disabled"  style="background-color:#D3D3D3" class="easyui-validatebox"
						  value="${InventoryVO.product_code}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="edit_stock_batchCode" class="field">产品批号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="edit_stock_batchCode" name="stock_batchCode" readonly="readonly"   disabled="disabled"  style="background-color:#D3D3D3" class="easyui-validatebox"
							value="${InventoryVO.stock_batchCode}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="edit_stock_storageNumber" class="field">库房数量：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="edit_stock_storageNumber" name="stock_storageNumber" readonly="readonly"  disabled="disabled"  style="background-color:#D3D3D3" class="easyui-validatebox"
							value="${InventoryVO.stock_storageNumber}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="edit_product_id" class="field">产品序号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="edit_product_id" name="product_id" readonly="readonly"  disabled="disabled"  style="background-color:#D3D3D3" class="easyui-validatebox"
							value="${InventoryVO.product_id}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="edit_product_name" class="field">产品名称：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="edit_product_name" name="product_name" readonly="readonly"  disabled="disabled"  style="background-color:#D3D3D3" class="easyui-validatebox"
							value="${InventoryVO.product_name}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="edit_product_specification" class="field">产品规格：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="edit_product_specification" name="product_specification" readonly="readonly"   disabled="disabled"  style="background-color:#D3D3D3" class="easyui-validatebox"
							value="${InventoryVO.product_specification}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="edit_product_unit" class="field">产品单位：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="edit_product_unit" name="product_unit" readonly="readonly"   disabled="disabled" style="background-color:#D3D3D3" class="easyui-validatebox"
							value="${InventoryVO.product_unit}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="edit_product_packingunit" class="field">包装单位：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="edit_product_packingunit" name="product_packingunit" readonly="readonly"  disabled="disabled"  style="background-color:#D3D3D3" class="easyui-validatebox"
							value="${InventoryVO.product_packingunit}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="edit_product_retailprice" class="field">零售价：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="edit_product_retailprice" name="product_retailprice" readonly="readonly"  disabled="disabled"  style="background-color:#D3D3D3" class="easyui-validatebox"
							value="${InventoryVO.product_retailprice}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="edit_product_productarea"  class="field">产地：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="edit_product_productarea" name="product_productarea" readonly="readonly"  disabled="disabled"  style="background-color:#D3D3D3" class="easyui-validatebox"
							value="${InventoryVO.product_productarea}" />
					</div>
				</div>
				
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="edit_stock_invalidDate" class="field">失效期：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="edit_stock_invalidDate" name="stock_invalidDate" readonly="readonly"  disabled="disabled"  style="background-color:#D3D3D3" class="easyui-validatebox" 
	                  	   value="${InventoryVO.stock_invalidDate}" />
	               </div>
				</div>
				
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="edit_stock_purchasePrice" class="field">进价：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="edit_stock_purchasePrice" name="stock_purchasePrice" readonly="readonly"  disabled="disabled"  style="background-color:#D3D3D3" class="easyui-validatebox"
							value="${InventoryVO.stock_purchasePrice}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="edit_stock_intakeSmallNumber" class="field">入库小号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="edit_stock_intakeSmallNumber" name="stock_intakeSmallNumber" readonly="readonly"  disabled="disabled"  style="background-color:#D3D3D3" class="easyui-validatebox"
							value="${InventoryVO.stock_intakeSmallNumber}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="edit_stock_intakeTicket" class="field">入库票号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="edit_stock_intakeTicket" name="stock_intakeTicket" readonly="readonly"  disabled="disabled"  style="background-color:#D3D3D3" class="easyui-validatebox"
							value="${InventoryVO.stock_intakeTicket}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="edit_inventory_countedNumber" class="field">盘点数量：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="edit_inventory_countedNumber" name="inventory_countedNumber"   class="easyui-validatebox"
							value="${InventoryVO.inventory_countedNumber}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="edit_inventory_profitLossNumber" class="field">损益数量：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="edit_inventory_profitLossNumber" name="inventory_profitLossNumber" readonly="readonly"  disabled="disabled" style="background-color:#D3D3D3" class="easyui-validatebox"
							value="${InventoryVO.inventory_profitLossNumber}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="edit_inventory_reason" class="field">损益原因：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="edit_inventory_reason" name="inventory_reason"  class="easyui-validatebox"  value="${InventoryVO.inventory_reason}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="edit_inventory_profitLossMoney" class="field">损益总额：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="edit_inventory_profitLossMoney" name="inventory_profitLossMoney" readonly="readonly"  disabled="disabled"  style="background-color:#D3D3D3" class="easyui-validatebox"
							value="${InventoryVO.inventory_profitLossMoney}" />
					</div>
				</div>
				
			</form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submitInventory" class="btn-save"
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
 
