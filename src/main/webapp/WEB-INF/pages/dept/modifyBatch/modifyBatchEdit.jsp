<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	$(function() {
		//单位
		var units = util.getList(global_param.context_name + "/system/combobox/lists?pid=132");
		for(var i=0;i<units.length;i++){
			if($("#product_unit").val()==units[i].cbs_id)
				$("#product_unit").val(units[i].cbs_chn);
		}
		
		//计算出库数量
		$("#output_number").val(Number($("#intake_number").val())-Number($("#stock_storageNumber").val()));
		
		//日期初始化
		$('#view_stock_produceDate').datebox({
			required:true,	
		});
		$('#view_stock_invalidDate').datebox({
			required:true,	
		});
		$('#intake_date').datebox({
			required:true,	
		});
		$('#view_stock_produceDate').datebox("setValue",util.toDate($('#view_stock_produceDate').val()));
		$('#view_stock_invalidDate').datebox("setValue",util.toDate($('#view_stock_invalidDate').val()));
		$('#intake_date').datebox("setValue",util.toDate($('#intake_date').val()));
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
				<input id="stock_info_id" name="stock_info_id" type="hidden" value="${stockInfo.stock_info_id}" />
				<input id="stock_intakeSmallNumber" name="stock_intakeSmallNumber" type="hidden" value="${stockInfo.stock_intakeSmallNumber}" />
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="prodcut_name" class="field">产品名称：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="prodcut_name" name="prodcut_name" class="easyui-validatebox" 
						disabled="disabled" value="${stockInfo.product_name}"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="product_specification" class="field">规格：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="product_specification" name="product_specification" class="easyui-validatebox"
						disabled="disabled" value="${stockInfo.product_specification}"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="product_unit" class="field">单位：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="product_unit" name="product_unit" type="text" class="easyui-validatebox"
						disabled="disabled" value="${stockInfo.product_unit}"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="stock_intakeTicket" class="field">入库票号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="stock_intakeTicket" name="stock_intakeTicket" class="easyui-validatebox"
							disabled="disabled" value="${stockInfo.stock_intakeTicket}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="product_productarea" class="field">产地：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="product_productarea" name="product_productarea" class="easyui-validatebox"
						disabled="disabled" value="${stockInfo.product_productarea}"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="stock_packunit" class="field">包装量：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="stock_packunit" name="stock_packunit" class="easyui-validatebox" 
						disabled="disabled" value="${stockInfo.stock_packunit}"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="sup_name" class="field">供方全称：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="sup_name" name="sup_name" class="easyui-validatebox" 
						disabled="disabled" value="${stockInfo.sup_name}"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="intake_place" class="field">入库地点：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="intake_place" name="intake_place" class="easyui-validatebox"
						disabled="disabled"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="stock_intakeSmallNumber" class="field">小号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="stock_intakeSmallNumber" name="stock_intakeSmallNumber" class="easyui-validatebox"
							disabled="disabled"  value="${stockInfo.stock_intakeSmallNumber}" />
					</div>
				</div>
				
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="intake_number" class="field">入库数量：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="intake_number" name="intake_number" class="easyui-validatebox"
							disabled="disabled"  value="${stockInfo.intake_number}" />
					</div>
				</div>
				
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="output_number" class="field">出库数量：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="output_number" name="output_number" class="easyui-validatebox"
							disabled="disabled" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="stock_storageNumber" class="field">库房数量：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="stock_storageNumber" name="stock_storageNumber" class="easyui-validatebox"
							disabled="disabled"  value="${stockInfo.stock_storageNumber}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="stock_purchasePrice" class="field">进价：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="stock_purchasePrice" name="stock_purchasePrice" class="easyui-validatebox"
							disabled="disabled"  value="${stockInfo.stock_purchasePrice}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="intake_date" class="field">入库时间：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="intake_date" name="intake_date" style="width:152px"
							disabled="disabled"  value="${stockInfo.intake_Date}"/>
					</div>
				</div>
				
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="view_stock_batchCode" class="field">批号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="view_stock_batchCode" name="view_stock_batchCode" class="easyui-validatebox"
							disabled="disabled"  value="${stockInfo.stock_batchCode}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="stock_batchCode" class="field">批号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="stock_batchCode" name="stock_batchCode" class="easyui-validatebox"
						data-options="validType:'length[1,12]'"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="view_stock_invalidDate" class="field">失效期：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="view_stock_invalidDate" name="view_stock_invalidDate" style="width:152px"
							disabled="disabled"   value="${stockInfo.stock_invalidDate}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="stock_invalidDate" class="field">失效期：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="stock_invalidDate" name="stock_invalidDate" class="easyui-datebox" style="width:152px"
						 />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="view_stock_produceDate" class="field">生产日期：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="view_stock_produceDate" name="view_stock_produceDate" style="width:152px"
							disabled="disabled" value="${stockInfo.stock_produceDate}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="stock_produceDate" class="field">生产日期：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="stock_produceDate" name="stock_produceDate" class="easyui-datebox" style="width:152px"
							/>
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submitAccount" class="btn-save"
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