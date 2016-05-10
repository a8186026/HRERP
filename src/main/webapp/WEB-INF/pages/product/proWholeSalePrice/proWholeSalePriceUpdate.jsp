<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/chnToLetter.js"></script>

<script type="text/javascript">
 	/* var ctrlsID = ["product_tradeprice","product_wsalelprice","product_lowsalelprice","product_dealwsaleprice","product_saleprice1","product_saleprice2","product_saleprice3","product_saleprice4","product_marketprice","product_retailprice",
      			"product_retaillprice","product_lretaillprice","product_dealretailprice","product_memberprice","product_proretailprice","product_basmedbidprice1","product_basmedbidprice2","product_basmedbidprice3","product_bidprice",
      			"product_retailcommission","product_invoicecom","product_wholesalecom","product_businesscom","product_nationallprice",
      			"product_dealprice","product_tinynum","product_dealcustomer","product_priceremark"];
      		 
   	//确认BUTTON
   	var sumbitButtonID = "submitProInfoPrice";
	
	$(function() {
		var keyPress = new pageKeyPress();
		keyPress.bindKeyPressToCtrl(ctrlsID, sumbitButtonID);

	}); 
	 */
	function submitProInfoPriceForm() {
		var data = util.submit('_proInfoPriceForm');
		if (data) {
			if (data.result == "success") {
				util.show(data.message);
				$('#Prodg').datagrid('reload');
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
			<form:form id="_proInfoPriceForm" action="${formUrl}" method="${method}" onsubmit="return false;">
				<input name="product_id" type="hidden" value="${stockInfo.product_id}" />
				<div style="border:none;position:relative;top:0px;margin-top:10px;margin-bottom:5px;margin-left:5px;width:570px">批发价格设置：</div>
				        	<div style="width:570px;height:170px;margin-left:5px;border:1px solid #CCCCCC">
				        	<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_tradeprice" class="field">批发价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_tradeprice" name="product_tradeprice" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${stockInfo.stock_tradeprice}" />
								</div>
							</div>
						
							
							</div>
			</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submitProInfoPrice" class="btn-save"
			onmouseover="this.className='btn-save-over'"
			onmouseout="this.className='btn-save'"
			onmousedown="this.className='btn-save-down'" type="button"
			onclick="submitProInfoPriceForm();" /> <input id="" class="btn-cancel"
			onmouseover="this.className='btn-cancel-over'"
			onmouseout="this.className='btn-cancel'"
			onmousedown="this.className='btn-cancel-down'" type="button"
			onclick="util.closeWindow('editWindow');" />
	</div>
</div>