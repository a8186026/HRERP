<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>药品零售</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script> 
<script type="text/javascript" src="/HRERP/resources/js/retail/medicineSale.js"></script>
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
			<div class="Dialog-form-title" >
				<label for="is_choose_batch" class="field">选择批号出库：</label>
			</div>
			<div class="Dialog-form-item">
			    <input id="is_choose_batch" name="is_choose_batch" type="checkbox" style="width:35px"/>
			</div>
			
			<div class="Dialog-form-title" >
				<label for="is_choose_promotion" class="field">选择活动信息：</label>
			</div>
			<div class="Dialog-form-item">
				<input id="is_choose_promotion" name="is_choose_promotion" type="checkbox" checked="checked" style="width:35px"/>
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
	    
		<div data-options="region:'center',border:false" style="margin-top:30px; height:200px" fit="true">
			<table id="products_info_dg" style=" height:250px ;width:80%"></table>
		</div>
		<div data-options="region:'center',border:false" style="margin-top:10px; height:100px" fit="true" >			
				<table id="product_info_dg" style="height:145px"></table>
		</div>

		<div data-options="region:'center',border:false"
			style="margin-top: 8px; height: 80px">

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

                           <div style="margin-top: -15px;">
                           
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_member_card_id" class="field">会员卡号：</label>
								</div>
								<input id="input_member_card_id" class="easyui-validatebox" style="width:152px"
									name="input_member_card_id" type="text" />
							</div>
								<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_member_card_type" class="field">类别：</label>
								</div>
								<input id="input_member_card_type" class="easyui-combobox" style="width:152px"
									name="input_member_card_type" disabled="disabled" type="text" />
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_member_card_name" class="field">姓名：</label>
								</div>
								<input id="input_member_card_name" class="easyui-validatebox" style="width:152px"
									name="input_member_card_name" disabled="disabled" type="text" />
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_member_card_remainPoint" class="field">未兑积分：</label>
								</div>
								<input id="input_member_card_remainPoint" class="easyui-validatebox" style="width:152px"
									name="input_member_card_remainPoint" disabled="disabled" type="text" />
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_member_card_tel" class="field">电话：</label>
								</div>
								<input id="input_member_card_tel" class="easyui-validatebox" style="width:152px"
									name="input_member_card_tel" disabled="disabled" type="text" />
							</div>
								
							</div>
							
							
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_sale_clerk" class="field">营业员：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_sale_clerk" name="input_sale_clerk" disabled="true"
									class="easyui-validatebox" style="width:152px" type="text"/>
								</div>
							</div>
							<!--    經手人去除
							 <div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_sale_cust_handle" class="field">经手人：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_sale_cust_handle" name="sale_cust_handle"
										type="text" class="easyui-validatebox" style="width:152px"/>
								</div>
							</div> -->
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="is_save_and_print" class="field">存盘打印：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="is_save_and_print" name="is_save_and_print" type="checkbox" checked="checked"style="width:35px"/>
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="is_add_clerk" class="field">添加到营业员：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="is_add_clerk" name="is_add_clerk" type="checkbox" checked="checked"style="width:35px"/>
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="is_scan_code_intake" class="field">扫码自动出库：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="is_scan_code_intake" name="is_scan_code_intake" type="checkbox" checked="checked"style="width:35px"/>
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="is_only_swipe_card" class="field">只能刷卡：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="is_only_swipe_card" name="is_only_swipe_card" type="checkbox" checked="checked"style="width:35px"/>
								</div>
							</div>
							
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_print_type" class="field">打印格式：</label>
								</div>
								<div class="Dialog-form-item">
								
										<input id="input_print_type" name="input_print_type" type="text"
							class="easyui-combobox"  style="width: 152px;" />
							<input id="perview" type="button"  value="预览打印格式" onclick="perviewPrint();" style="height: 22px;width: 90px;"/> 
								</div>
							</div>
                           
						</form>

					</div>

				</div>

			</div>
		</div>
		<div data-options="region:'center',border:false"
			style="margin-top: 8px; height: 90px; width:100%" fit="true">
			<div class="easyui-layout" fit="true" style="width:100%" >
				<div region="center" border="false" class="Dialog-Bg" fit="true" style="width:100%">
					<div class="Dialog-formDiv clearfix" fit="true" style="width:100%">
						<form id="med" name="med" action="${medUrl}" method="${method}">
							<input name="stock_id" type="hidden" value="${stock.stock_id}" />
							<input name="SPRING_TOKEN" type="hidden" value="${sessionScope.SPRING_TOKEN}" />
							<input id="medUrl" value="${medUrl}" type="hidden">
							<input id="sale_med_Ticket" name="sale_med_Ticket" value="" type="hidden">
							
							<!--打折授权人-->
							<input id="discountPerson" type="hidden">
							<!-- 打折原因 -->
							<input id="discountReason" type="hidden">

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
									<input id="input_product_discount" name="input_product_discount" type="text" disabled="disabled"
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
		<div data-options="region:'center',border:false" style="margin-top:8px; height:100px ; width:700px">
		<!-- <div class="easyui-layout" fit="true"> -->
				<!-- <div region="center" border="false" class="Dialog-Bg"> -->
		<div class="Dialog-formDiv clearfix">
							
	    <<!-- div data-options="border:false" style="margin-top:20px;margin-left:700px;height:270px ;width:600px" fit="true" >			
		   <table id="activity" style="height:270px;width:600px"></table>
		</div> -->
		<div id="retailOrder" style="margin-left:50px;height:300px ;width:800px" fit="true">
	         	<form id="form_cust" name="form_cust" action="${formUrl}" method="${method}">
					<input name="SPRING_TOKEN" type="hidden" value="${sessionScope.SPRING_TOKEN}" />
					<input id="order_pharmacistName" name="order_pharmacistName" type="hidden" />
					<input id="retail_order_pharmacistElectronicSignature" name="retail_order_pharmacistElectronicSignature"  type="hidden"/>
					<input id="retail_order_purchasePersonName" name="retail_order_purchasePersonName" type="hidden"/>
					<input id="retail_order_purchasePersonID" name="retail_order_purchasePersonID" type="hidden"/>
					<input id="retail_order_purchasePersonPhone" name="retail_order_purchasePersonPhone" type="hidden" />
					<input id="retail_order_ticketId" name="retail_order_ticketId" type="hidden">
					<input id="mem_card_id" name="mem_card_id" type="hidden">
					<input id="status" name="status" type="hidden" value="0" />
						<div class="Dialog-form-item">
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_retail_order_SingleDoseAmount" class="field">单付金额：</label>
								</div>
								<input id="input_retail_order_SingleDoseAmount" name="retail_order_SingleDoseAmount"
								 class="easyui-validatebox" readOnly="readOnly" style="background-color: LightGrey " value="0.00"/>
								&nbsp;&nbsp;&nbsp;&nbsp;
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_retail_order_otherCharge" class="field">其它收费：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_retail_order_otherCharge" name="retail_order_otherCharge"
										class="easyui-validatebox" value = "0.00"/>
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_retail_order_doseNumber" class="field">付数：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_retail_order_doseNumber" name="retail_order_doseNumber" type="text"
										class="easyui-validatebox" value="1" data-options="validType:['num']"/>
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_sale_cust_billDiscount" class="field">主单打折：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_sale_cust_billDiscount" 
										class="easyui-validatebox" value = "0.00" style="width:73px"/>
									<input id="sale_cust_billDiscountMax" name="sale_cust_billDiscountMax"
										class="easyui-validatebox" value = "0.00" style="width:72px" disabled="disabled"/>
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_retail_order_settlementAmount" class="field">总金额：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_retail_order_settlementAmount" name="retail_order_settlementAmount"
										class="easyui-validatebox" readOnly="readOnly" style="background-color: LightGrey " />

								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_retail_order_giftCard" class="field">代金卡：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_retail_order_giftCard" name="retail_order_giftCard"
										class="easyui-validatebox" value = "0.00" style="width:73px"/>
									<input id="input_retail_order_giftCardMax" name="input_retail_order_giftCardMax"
										class="easyui-validatebox" value = "0.00" style="width:72px" disabled="disabled"/>

								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_sale_cust_productOff" class="field">单品打折：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_sale_cust_productOff" name="sale_cust_productOff"
										class="easyui-validatebox" readOnly="readOnly" style="background-color: LightGrey " value = "0.00"/>
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_retail_order_pointPay" class="field">积分当钱：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_retail_order_pointPay" name="retail_order_pointPay"
										class="easyui-validatebox" value = "0.00" style="width:73px"/>
									<input id="input_retail_order_pointPayMax" name="input_retail_order_pointPayMax"
										class="easyui-validatebox" value = "0.00" style="width:72px" disabled="disabled"/>

								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_retail_order_wipeZero" class="field">抹零：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_retail_order_wipeZero" name="retail_order_wipeZero"
										class="easyui-validatebox" readOnly="readOnly" style="background-color: LightGrey " value = "0.00"/>
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_retail_order_receivableAmount" class="field">应收金额：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_retail_order_receivableAmount" name="retail_order_receivableAmount"
										class="easyui-validatebox" readOnly="readOnly" style="background-color: LightGrey "/>

								</div>
							</div>
							
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_retail_order_point" class="field">本次积分：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_retail_order_point" name="retail_order_point"
										class="easyui-validatebox" readOnly="readOnly" style="background-color: LightGrey " value = "0.00"/>
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<input id="health_insurance_combobox" 
										class="easyui-combobox" style="width:65px"/>
								</div>
								</div>
								<div class="Dialog-form-item">
									<input id="input_health_insurance" 
										class="easyui-validatebox" value="0.00"/>

								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_retail_order_fullGiftAmount" class="field">满赠金额：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_retail_order_fullGiftAmount" name="retail_order_fullGiftAmount"
										class="easyui-validatebox" readOnly="readOnly" style="background-color: LightGrey " value = "0.00"/>

								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_retail_order_cash" class="field">现金：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_retail_order_cash" name="retail_order_cash"
										class="easyui-validatebox" readOnly="readOnly" style="background-color: LightGrey "/>

								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_retail_order_fullGiftExchange" class="field">兑换金额：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_retail_order_fullGiftExchange" name="retail_order_fullGiftExchange"
										class="easyui-validatebox" readOnly="readOnly" style="background-color: LightGrey "value = "0.00"/>

								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_retail_order_realIncomeAmount" class="field">实收现金：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_retail_order_realIncomeAmount" name=retail_order_realIncomeAmount
										class="easyui-validatebox" data-options="validType:['decimal','length[1,13]']" />

								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_retail_order_fullGiftRemain" class="field">未兑金额：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_retail_order_fullGiftRemain" name="retail_order_fullGiftRemain"
										class="easyui-validatebox" readOnly="readOnly" style="background-color: LightGrey " value = "0.00"/>

								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="input_retail_order_change" class="field">找零：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="input_retail_order_change" name="retail_order_change"
										class="easyui-validatebox" readOnly="readOnly" style="background-color: LightGrey "/>
								</div>
						</form>
			</div>
		</div>
		    <div  style="margin-top:-60px;margin-left:500px; fit="true" >
				<div data-options="region:'south',border:false"
					style="text-align: right;">
					<input id="submit_all" class="btn-save"
						onmouseover="this.className='btn-save-over'"
						onmouseout="this.className='btn-save'"
						onmousedown="this.className='btn-save-down'" type="button"
						onclick="prepareSubmitOrder();" /> 
					<input id="" class="btn-cancel"
						onmouseover="this.className='btn-cancel-over'"
						onmouseout="this.className='btn-cancel'"
						onmousedown="this.className='btn-cancel-down'" type="button"
						onclick="deleteOrder();" />
						
				</div>
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