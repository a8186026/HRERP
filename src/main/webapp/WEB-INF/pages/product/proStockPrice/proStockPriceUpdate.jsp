<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/chnToLetter.js"></script>

<script type="text/javascript">

	var ctrlsID = ["pro_group_no","pro_location_no","pro_storeman","pro_trade_price","pro_retail_price","pro_min_price","pro_saleprice1","pro_saleprice2","pro_saleprice3",
	               "pro_saleprice4","pro_purchase_price","pro_market_price","pro_cost_price","pro_refer_purchase_price","pro_final_price","pro_offer_price"];
	 
	//确认BUTTON
	var sumbitButtonID = "submitStockPrice";
	
	$(function() {
		var keyPress = new pageKeyPress();
		keyPress.bindKeyPressToCtrl(ctrlsID, sumbitButtonID);

	});
	
	function submitStockPriceForm() {
		var data = util.submit('_stockPriceForm');
		if (data) {
			if (data.result == "success") {
				util.show(data.message);
				$('#PriceDg').datagrid('reload');
				util.closeWindow('editStockPriceWindow');
			} else {
				util.error(data.message);
			}
		}
	}
</script>

<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
			<form:form id="_stockPriceForm" action="${formUrl}" method="${method}" onsubmit="return false;">
				<input name="prod_id" type="hidden" value="${stockPrice.prod_id}" />
				<input name="product_id" type="hidden" value="${stockPrice.product_id}" />
				<input name="department_id" type="hidden" value="${stockPrice.department_id}" />
				
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="pro_group_no" class="field">产品：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="pro_group_no" class="easyui-validatebox"
							disabled="true"  value="${product_name}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="pro_group_no" class="field">部门：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="pro_group_no" class="easyui-validatebox"
							disabled="true"  value="${department_fullname}" />
					</div>
				</div>
				
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="pro_group_no" class="field">组号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="pro_group_no" name="pro_group_no" class="easyui-validatebox"
							data-options="validType:['num','nosp']"  value="${stockPrice.pro_group_no}" />
					</div>
				</div>
				
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="pro_location_no" class="field">货位号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="pro_location_no" name="pro_location_no" class="easyui-validatebox"
							data-options="validType:['num','nosp']"  value="${stockPrice.pro_location_no}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="pro_storeman" class="field">保管员：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="pro_storeman" name="pro_storeman" class="easyui-validatebox"
							data-options="validType:'length[1,20]'"  value="${stockPrice.pro_storeman}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="pro_trade_price" class="field">批发价：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="pro_trade_price" name="pro_trade_price" class="easyui-validatebox"
							data-options="validType:['decimal','nosp']"  value="${stockPrice.pro_trade_price}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="pro_retail_price" class="field">零售价：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="pro_retail_price" name="pro_retail_price" class="easyui-validatebox"
							data-options="validType:['decimal','nosp']"  value="${stockPrice.pro_retail_price}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="pro_min_price" class="field">最低价：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="pro_min_price" name="pro_min_price" class="easyui-validatebox"
							data-options="validType:['decimal','nosp']"  value="${stockPrice.pro_min_price}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="pro_saleprice1" class="field">销售价1：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="pro_saleprice1" name="pro_saleprice1" class="easyui-validatebox"
							data-options="validType:['decimal','nosp']"  value="${stockPrice.pro_saleprice1}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="pro_saleprice2" class="field">销售价2：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="pro_saleprice2" name="pro_saleprice2" class="easyui-validatebox"
							data-options="validType:['decimal','nosp']"  value="${stockPrice.pro_saleprice2}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="pro_saleprice3" class="field">销售价3：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="pro_saleprice3" name="pro_saleprice3" class="easyui-validatebox"
							data-options="validType:['decimal','nosp']"  value="${stockPrice.pro_saleprice3}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="pro_saleprice4" class="field">销售价4：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="pro_saleprice4" name="pro_saleprice4" class="easyui-validatebox"
							data-options="validType:['decimal','nosp']"  value="${stockPrice.pro_saleprice4}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="pro_purchase_price" class="field">进价：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="pro_purchase_price" name="pro_purchase_price" class="easyui-validatebox"
							data-options="validType:['decimal','nosp']"  value="${stockPrice.pro_purchase_price}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="pro_market_price" class="field">市场价：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="pro_market_price" name="pro_market_price" class="easyui-validatebox"
							data-options="validType:['decimal','nosp']"  value="${stockPrice.pro_market_price}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="pro_cost_price" class="field">成本价：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="pro_cost_price" name="pro_cost_price" class="easyui-validatebox"
							data-options="validType:['decimal','nosp']"  value="${stockPrice.pro_cost_price}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="pro_refer_purchase_price" class="field">参考进价：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="pro_refer_purchase_price" name="pro_refer_purchase_price" class="easyui-validatebox"
							data-options="validType:['decimal','nosp']"  value="${stockPrice.pro_refer_purchase_price}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="pro_final_price" class="field">结算价：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="pro_final_price" name="pro_final_price" class="easyui-validatebox"
							data-options="validType:['decimal','nosp']"  value="${stockPrice.pro_final_price}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="pro_offer_price" class="field">报价：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="pro_offer_price" name="pro_offer_price" class="easyui-validatebox"
							data-options="validType:['decimal','nosp']"  value="${stockPrice.pro_offer_price}" />
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submitStockPrice" class="btn-save"
			onmouseover="this.className='btn-save-over'"
			onmouseout="this.className='btn-save'"
			onmousedown="this.className='btn-save-down'" type="button"
			onclick="submitStockPriceForm();" /> <input id="" class="btn-cancel"
			onmouseover="this.className='btn-cancel-over'"
			onmouseout="this.className='btn-cancel'"
			onmousedown="this.className='btn-cancel-down'" type="button"
			onclick="util.closeWindow('editStockPriceWindow');" />
	</div>
</div>