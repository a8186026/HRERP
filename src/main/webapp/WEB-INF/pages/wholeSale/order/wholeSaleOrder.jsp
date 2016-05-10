<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>药品零售</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script> 
<script type="text/javascript" src="/HRERP/resources/js/wholeSale/wholeSale.js"></script>
</head>
<body>

	<!-- <object id="callDllFile"
	codebase="/HRERP/resources/Recipe.CAB#version=3,1,0,0"   
    classid="CLSID:07575197-690A-4DF9-8C0A-055CE7A4F0EE"       
    width="0" height="0">  
	</object> -->
	
	<div class="easyui-layout" style="width: 100%; height: 100%;">
		<!-- 显示区域 -->
		<div data-options="region:'center',border:false" style="margin-top:0px; height:20px ;width:100%" fit="true">
			<div class="Dialog-form-title" style="margin-left:-20px;">
				<label for="input_retail_order_ticketId" class="field">票号：</label>
			</div>
			<div class="Dialog-form-item">
				<input id="input_retail_order_ticketId" 
					class="easyui-validatebox" style="border-style:none" readOnly="readOnly" />
			</div>
	    </div>
	    
	   <!--  <div data-options="region:'center',border:false"
			style="margin-top: 8px; height: 90px; width:100%" fit="true">
			<div class="easyui-layout" fit="true" style="width:100%" >
				<div region="center" border="false" class="Dialog-Bg" fit="true" style="width:100%">
					<div class="Dialog-formDiv clearfix" fit="true" style="width:100%">
					</div>
				</div>
			</div>
		</div> -->
	    
		<div data-options="region:'center',border:false" style="margin-top:30px; height:100px" fit="true">
			<table id="sale_info_dg" style=" height:100px ;width:80%"></table>
		</div>
		<div data-options="region:'center',border:false" style="margin-top:10px; height:100px" fit="true" >			
				<table id="product_info_dg" style="height:145px"></table>
		</div>

		<div data-options="region:'center',border:false"
			style="margin-top: 8px; height: 130px">

			<div class="easyui-layout" fit="true">

				<div region="center" border="false" class="Dialog-Bg">
					<div class="Dialog-formDiv clearfix">
						<form id="order" name="order" action="${orderUrl}" method="${method}">
							<input name="SPRING_TOKEN" type="hidden"
								value="${sessionScope.SPRING_TOKEN}" /> 
							<input id="orderUrl" value="${orderUrl}" type="hidden" /> 
							<input id="method" value="${method}" type="hidden" />
							<input id="mem_card_discount" type="hidden" />
							<input id="listvo" value="${listvo}" type="hidden" />

                          <!--  <div style="margin-top: -15px;"> -->
                           
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_member_card_id" class="field">客户编码：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_member_card_id" class="easyui-validatebox" style="width:152px"
										name="input_member_card_id" type="text" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="wholeSale_order_brokerage" class="field">经手人：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="wholeSale_order_brokerage" class="easyui-combobox" style="width:152px"
										name="wholeSale_order_brokerage" type="text" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_member_card_name" class="field">经手人2：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_member_card_name" class="easyui-combobox" style="width:152px"
										name="input_member_card_name" type="text" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_member_card_remainPoint" class="field">摘要：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="wholeSale_order_brief" class="easyui-combobox" style="width:152px"
										name="wholeSale_order_brief" type="text" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_member_card_tel" class="field">摘要2：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_member_card_tel" class="easyui-combobox" style="width:152px"
										name="input_member_card_tel" type="text" />
								</div>
							</div>
								
							
							
							
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_sale_clerk" class="field">结款：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_sale_clerk" name="input_sale_clerk"
									class="easyui-combobox" style="width:152px" type="text"/>
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_sale_clerk" class="field">送货方式：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_sale_clerk" name="input_sale_clerk"
									class="easyui-combobox" style="width:152px" type="text"/>
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_sale_clerk" class="field">返点：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_sale_clerk" name="input_sale_clerk"
									class="easyui-combobox" style="width:152px" type="text"/>
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_sale_clerk" class="field">赠品：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_sale_clerk" name="input_sale_clerk"
									class="easyui-combobox" style="width:152px" type="text"/>
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_sale_clerk" class="field">备注：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_sale_clerk" name="input_sale_clerk"
									class="easyui-validatebox" style="width:152px" type="text"/>
								</div>
							</div>
							
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_sale_clerk" class="field">备注1：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_sale_clerk" name="input_sale_clerk"
									class="easyui-validatebox" style="width:152px" type="text"/>
								</div>
							</div>
							
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_sale_clerk" class="field">备注2：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_sale_clerk" name="input_sale_clerk"
									class="easyui-validatebox" style="width:152px" type="text"/>
								</div>
							</div>
							
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_sale_clerk" class="field">返点金额：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_sale_clerk" name="input_sale_clerk"
									class="easyui-validatebox" style="width:152px" type="text"/>
								</div>
							</div>
							
							
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_sale_clerk" class="field">销售合计：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_sale_clerk" name="input_sale_clerk" disabled="true"
									class="easyui-validatebox" style="width:152px" type="text"/>
								</div>
							</div>
							
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_sale_clerk" class="field">实收金额：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_sale_clerk" name="input_sale_clerk" disabled="true"
									class="easyui-validatebox" style="width:152px" type="text"/>
								</div>
							</div>
						</form>

					</div>

				</div>

			</div>
		</div>
		<div data-options="region:'center',border:false"
			style="margin-top: 8px; height: 130px; width:100%" fit="true">
			<div class="easyui-layout" fit="true" style="width:100%" >
				<div region="center" border="false" class="Dialog-Bg" fit="true" style="width:100%">
					<div class="Dialog-formDiv clearfix" fit="true" style="width:100%">
						<form id="med" name="med" action="${medUrl}" method="${method}">
							<input name="stock_id" type="hidden" value="${stock.stock_id}" />
							<input name="SPRING_TOKEN" type="hidden" value="${sessionScope.SPRING_TOKEN}" />
							<input id="medUrl" value="${medUrl}" type="hidden">
							<input id="sale_med_Ticket" name="sale_med_Ticket" value="" type="hidden">
							
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_product_code" class="field">产品：</label>
								</div>
								 <input id="input_product_code" name="input_product_code" type="text"
								    class="easyui-validatebox" style="width:152px"/>
								 <!-- 产品id -->
								 <input id="input_product_id" name="product_id"
									readOnly="readOnly" type="hidden" class="easyui-validatebox" />
								<input id="input_stock_info_id" name="input_stock_info_id"
									readOnly="readOnly" type="hidden" class="easyui-validatebox" />
							</div>
							
							
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_department_id" class="field">库房：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_department_id" name="department_id"
										class="easyui-validatebox" style="width:152px"/>
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_order_product_quantity" class="field">件数：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_order_product_quantity" name="order_product_quantity"
										class="easyui-validatebox" value="1.00" style="width:152px" 
										data-options="required:true,validType:['decimalFor6','length[1,13]']"/>
								</div>
							</div>
							
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_order_product_quantity" class="field">数量：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_order_product_quantity" name="order_product_quantity"
										class="easyui-validatebox" value="1.00" style="width:152px" 
										data-options="required:true,validType:['decimalFor6','length[1,13]']"/>
								</div>
							</div>
							<!-- 
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_order_product_quantity" class="field">单位：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_order_product_quantity" name="order_product_quantity"
										class="easyui-validatebox" value="1.00" style="width:152px" 
										data-options="required:true,validType:['decimalFor6','length[1,13]']"/>
								</div>
							</div> -->
							
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_order_product_unitPrice" class="field">单价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_order_product_unitPrice" name="order_product_unitPrice"
										class="easyui-validatebox" style="width:152px" readonly="readonly" disabled="disabled"/>
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_product_discount" class="field" ondblclick="modifyDiscount()">折扣：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_product_discount" name="input_product_discount" type="text" 
										class="easyui-validatebox" style="width:152px" value="100" data-options="required:true,validType:['decimal']"/>
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_discountedPrice" class="field">折后价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_discountedPrice" disabled="disabled" style="width:152px"
										name="input_discountedPrice" class="easyui-validatebox"
										 />
								</div>
							</div>
							
							<!-- <div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_product_cash" class="field">减差价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_order_product_amount" name="order_product_amount" style="width:152px"
										class="easyui-validatebox" readonly="readonly" disabled="disabled" />
								</div>
							</div> -->
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_product_cash" class="field">结算价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_order_product_amount" name="order_product_amount" style="width:152px"
										class="easyui-validatebox" readonly="readonly" disabled="disabled" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_product_cash" class="field">金额：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_order_product_amount" name="order_product_amount" style="width:152px"
										class="easyui-validatebox" readonly="readonly" disabled="disabled" />
								</div>
							</div>
						</form>
					</div>
					<div data-options="region:'south',border:false"
						style="text-align: right;">
						<input id="submit_current" class="btn-save"
							onmouseover="this.className='btn-save-over'"
							onmouseout="this.className='btn-save'"
							onmousedown="this.className='btn-save-down'" type="button"
							onclick="submitMed();" /> <input id="" class="btn-cancel"
							onmouseover="this.className='btn-cancel-over'"
							onmouseout="this.className='btn-cancel'"
							onmousedown="this.className='btn-cancel-down'" type="button"
							onclick="clearMed();" />
					</div>
				</div>
			</div>
		</div>
	<div data-options="region:'center',border:false" style="margin-top:8px; height:100% ; width:100%">
		<!-- <div class="easyui-layout" fit="true"> -->
				<!-- <div region="center" border="false" class="Dialog-Bg"> -->
		<div class="Dialog-formDiv clearfix">
							
	    <div data-options="border:false" style="margin-top:10px;height:340px ;width:100%" fit="true" >			
		   <table id="products_info_dg" ></table>
		</div>
		
		    
			
		</div>					
	</div>
   
	<!-- 编辑窗口 -->
	<div id="editWindow"></div>
	<div id="editWindow1"></div>
	<div id="proListWindow"></div>
	<div id="setDiscountWindow"></div>
	<div id="fullFillGift"></div>
	<div id="giftSale"></div>
	<div id="specialMedCheck"></div>
	</body>
</html>