<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/chnToLetter.js"></script>

<script type="text/javascript">
 	var ctrlsID = ["product_tradeprice","product_wsalelprice","product_lowsalelprice","product_dealwsaleprice","product_saleprice1","product_saleprice2","product_saleprice3","product_saleprice4","product_marketprice","product_retailprice",
      			"product_retaillprice","product_lretaillprice","product_dealretailprice","product_memberprice","product_proretailprice","product_basmedbidprice1","product_basmedbidprice2","product_basmedbidprice3","product_bidprice",
      			"product_retailcommission","product_invoicecom","product_wholesalecom","product_businesscom","product_nationallprice",
      			"product_dealprice","product_tinynum","product_dealcustomer","product_priceremark"];
      		 
   	//确认BUTTON
   	var sumbitButtonID = "submitProInfoPrice";
	
	$(function() {
		var keyPress = new pageKeyPress();
		keyPress.bindKeyPressToCtrl(ctrlsID, sumbitButtonID);

	}); 
	
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
				<input name="product_id" type="hidden" value="${proInfoManage.product_id}" />
				<div style="border:none;position:relative;top:0px;margin-top:10px;margin-bottom:5px;margin-left:5px;width:570px">批发价格设置：</div>
				        	<div style="width:570px;height:170px;margin-left:5px;border:1px solid #CCCCCC">
				        	<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_tradeprice" class="field">批发价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_tradeprice" name="product_tradeprice" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_tradeprice}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_wsalelprice" class="field">批发限价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_wsalelprice" name="product_wsalelprice" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_wsalelprice}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_lowsalelprice" class="field">批发最低价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_lowsalelprice" name="product_lowsalelprice" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_lowsalelprice}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_dealwsaleprice" class="field">协议批发价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_dealwsaleprice" name="product_dealwsaleprice" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_dealwsaleprice}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_saleprice1" class="field">销售价1：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_saleprice1" name="product_saleprice1" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_saleprice1}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_saleprice2" class="field">销售价2：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_saleprice2" name="product_saleprice2" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_saleprice2}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_saleprice3" class="field">销售价3：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_saleprice3" name="product_saleprice3" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_saleprice3}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_saleprice4" class="field">销售价4：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_saleprice4" name="product_saleprice4" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_saleprice4}" />
								</div>
							</div>
						    <div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_marketprice" class="field">市场价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_marketprice" name="product_marketprice" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_marketprice}" />
								</div>
							</div> 
				        	</div>
				        	
				        	
				        	<div style="border:none;position:relative;width:570px;margin-top:5px;margin-bottom:5px;margin-left:5px">零售价格设置：</div>
				        	<div style="width:570px;height:120px;margin-left:5px; border:1px solid #CCCCCC">
				        	<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_retailprice" class="field">零售价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_retailprice" name="product_retailprice" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_retailprice}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_retaillprice" class="field">零售限价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_retaillprice" name="product_retaillprice" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_retaillprice}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_lretaillprice" class="field">零售最低价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_lretaillprice" name="product_lretaillprice" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_lretaillprice}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_dealretailprice" class="field">协议零售价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_dealretailprice" name="product_dealretailprice" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_dealretailprice}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_memberprice" class="field">会员价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_memberprice" name="product_memberprice" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_memberprice}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_proretailprice" class="field">省标零售价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_proretailprice" name="product_proretailprice" class="easyui-validatebox" 
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_proretailprice}" />
								</div>
							</div>
				        	</div>
		
							<div style="border:none;position:relative;width:570px;margin-top:5px;margin-bottom:5px;margin-left:5px">中标价格设置：</div>
							<div style="width:570px;height:85px;margin-left:5px; border:1px solid #CCCCCC">
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_basmedbidprice1" class="field">基药中标价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_basmedbidprice1" name="product_basmedbidprice1" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_basmedbidprice1}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_basmedbidprice2" class="field">基药中标价2：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_basmedbidprice2" name="product_basmedbidprice2" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_basmedbidprice2}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_basmedbidprice3" class="field">基药中标价3：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_basmedbidprice3" name="product_basmedbidprice3" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_basmedbidprice3}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_bidprice" class="field">中标价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_bidprice" name="product_bidprice" class="easyui-validatebox"  
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_bidprice}" />
								</div>
							</div>
							</div>
							
							<div style="border:none;position:relative;width:570px;margin-top:5px;margin-bottom:5px;margin-left:5px">提成设置：</div>
							<div style="width:570px;height:85px;margin-left:5px; border:1px solid #CCCCCC">
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_retailcommission" class="field">零售提成：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_retailcommission" name="product_retailcommission" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_retailcommission}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_invoicecom" class="field">开票提成：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_invoicecom" name="product_invoicecom" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_invoicecom}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_wholesalecom" class="field">批发提成：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_wholesalecom" name="product_wholesalecom" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_wholesalecom}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_businesscom" class="field">业务提成：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_businesscom" name="product_businesscom" class="easyui-validatebox"  
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_businesscom}" />
								</div>
							</div>
							</div>
							
							<div style="border:none;position:relative;width:570px;margin-top:5px;margin-bottom:5px;margin-left:5px">价格其他：</div>
							<div style="width:570px;height:110px;margin-left:5px; border:1px solid #CCCCCC">
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_nationallprice" class="field">国家限价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_nationallprice" name="product_nationallprice" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_nationallprice}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_dealprice" class="field">协议进价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_dealprice" name="product_dealprice" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_dealprice}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_tinynum" class="field">拆零组数：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_tinynum" name="product_tinynum" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${proInfoManage.product_tinynum}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_dealcustomer" class="field">协议客户：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_dealcustomer" name="product_dealcustomer" class="easyui-validatebox" 
										data-options="validType:'length[1,20]'"  value="${proInfoManage.product_dealcustomer}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_priceremark" class="field">价格备注：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_priceremark" name="product_priceremark" class="easyui-validatebox"  
										data-options="validType:'length[1,20]'"  value="${proInfoManage.product_priceremark}" />
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