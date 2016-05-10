<%@page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一键入库</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript"	src="/HRERP/resources/js/JsUtil/userHabits.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/accept/acceptAll.js"></script>

</head>
<body>

    <input id="orderFormUrl" type="hidden" value="${orderFormUrl}"/>
	<input id="orderMethod" type="hidden" value="${orderMethod}"/>
		
		
					<input id="ticket_id" name="ticket_id" readonly="readonly" type="hidden" class="easyui-validatebox" value="${purOrder.ticket_id}"/>
				
				
					<input id="operator" name="operator" readonly="readonly" type="hidden" class="easyui-validatebox" value="${user.display_name}" style="width:70px"/>
	
	<div id="mainContent" class="easyui-layout" fit="true"
		style="width: 100%; height: 100%;">
		
		
		
		<input id="id" name="id" type="hidden" class="easyui-validatebox" value="${purOrder.id}"/>
				<div data-options="region:'center',border:false" style="margin-top:0px;height: 0px;">
				<div class="Dialog-formDiv clearfix">
					<div class="Dialog-form-title">
						<label for="orderabstract" class="field">摘&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;要：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="orderabstract" name="orderabstract" type="text" class="combobox" value="${purOrder.orderabstract}"style="width:152px;"/>
					</div>
					<div class="Dialog-form-title">
						<label for="knotStyle" class="field">结款方式：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="knotStyle" name="knotStyle" type="text" class="combobox" value="${purOrder.knotStyle}"style="width:152px;"/>
					</div>
					<div class="Dialog-form-title">
						<label for="policyStyle" class="field">政策形式：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="policyStyle" name="policyStyle" type="text" class="combobox" value="${purOrder.policyStyle}"style="width:152px;"/>
					</div>
					<div class="Dialog-form-title">
						<label for="remarks" class="field">备注：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="remarks" name="remarks" type="text" class="easyui-validatebox" value="${purOrder.remarks}" style="width:152px;"/>
					</div>
						<div class="Dialog-form-title">
						<label for="carryMode" class="field">承运方式：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="carryMode" name="carryMode" type="text" class="combobox" value="${purOrder.carryMode}"style="width:152px;"/>
					</div>
					<div class="Dialog-form-title">
						<label for="orderTransMode" class="field">运输方式：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="orderTransMode" name="orderTransMode" type="text" class="combobox" value="${purOrder.orderTransMode}"style="width:152px;"/>
					</div>
				</div>
			</div>
			<br/><br/>
			<div data-options="region:'center',border:false" style="margin-top: 1%px;height: 0px;">
				<div class="Dialog-formDiv clearfix">
				<div class="Dialog-form-title">
						<label for="carryCompany" class="field">承运单位：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="carryCompany" name="carryCompany" type="text" class="easyui-validatebox" style="width:152px;" value="${purOrder.carryCompany}"style="width:152px;"/>
					</div>
					<div class="Dialog-form-title">
						<label for="delivaryPlace" class="field">发运地点：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="delivaryPlace" name="delivaryPlace" type="text"class="easyui-validatebox" value="${purOrder.delivaryPlace}" style="width: 430px"/>
					</div>
					<div class="Dialog-form-title">
						<label for="orderDepartureTime" class="field">启运时间：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="orderDepartureTime" name="orderDepartureTime"  value="${purOrder.orderDepartureTime}" style="width:152px;"/>
					</div>
					<div class="Dialog-form-title">
						<label for="arrivalDate" class="field">到货时间：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="arrivalDate" name="arrivalDate"  value="${purOrder.arrivalDate}"style="width:152px;"/>
					</div>
				</div>
			</div>
			<br/><br/>
			
			
			<div data-options="region:'center',border:false" style="margin-top: 0px;height: 0px;">
				<div class="Dialog-formDiv clearfix">
				
					<div class="Dialog-form-title">
						<label for="planPerson" class="field">订单计划人：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="planPerson" name="planPerson" type="text" class="combobox" style="width:152px;"/>
					</div>
					<div class="Dialog-form-title">
						<label for="planTime" class="field">计划时间：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="planTime" name="planTime"    style="width:152px;"/>
					</div>
					<div class="Dialog-form-title">
						<label for="acceptPerson" class="field">收货人：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="acceptPerson" name="acceptPerson" type="text" class="combobox" style="width:152px;"/>
					</div>
					<div class="Dialog-form-title">
						<label for="acceptTime" class="field">收货时间：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="acceptTime" name="acceptTime"   style="width:152px;"/>
					</div>
					<div class="Dialog-form-title">
						<label for="checkPerson" class="field">验收人：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="checkPerson" name="checkPerson" type="text" class="combobox" style="width:152px;"/>
					</div>
					
						
					<div class="Dialog-form-title">
						<label for="checkTime" class="field">验收时间：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="checkTime" name="checkTime"   style="width:152px;"/>
					</div>
					
				
				</div>
			</div>
			<br/><br/>
					<br/><br/>
					
					<div data-options="region:'center',border:false" style="margin-top: 0px;">
				<div class="Dialog-formDiv clearfix">
					<div class="Dialog-form-title">
						<label for="supply_code" class="field">客户编码：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="supply_code" name="supply_code" value="${purOrder.supply_code}" data-options="required:true" type="text" class="easyui-validatebox"/>
						<input id="supply_id" name="supply_id" value="${purOrder.supply_id}" type="hidden" class="easyui-validatebox"/>
						<input id="supply_fullname" name="supply_fullname" value="${purOrder.supply_fullname}" type="hidden" class="easyui-validatebox"/>
					</div>
					<div class="Dialog-form-title">
						<label for="" class="field">客户全称：</label>
					</div>
					<div class="Dialog-form-item">
						<font id="supply_fullnameForShow" size="3" color="red">${purOrder.supply_fullname}</font>
					</div>
					
					<div class="Dialog-form-title" style="margin-left:155px;">
						<label for="order_sum" class="field">订单金额：</label>
					</div>
					<div class="Dialog-form-item">
					<input id="order_sum" name="order_sum" value=""  type="text" class="easyui-validatebox" style="width:152px;"/>
					</div>
				</div>
			</div>
			
	
			<div  data-options="region:'center',border:false" style="margin-top: 0px;height: 30px;">
			<form id="orderList_form" action="${orderListFormUrl}" method="${orderListMethod}" onsubmit="return false;">
				<div class="Dialog-formDiv clearfix">
						<div style="margin-left: 35px">
						&nbsp;&nbsp;&nbsp;&nbsp;产品编号 
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;订货单价
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;折扣 
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结算价 
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;件数 
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;包装量 
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;订货数量
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金额
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;预付款
						</div>
						<div style="margin-left: 35px;margin-top:0px">
							<input name="ticket_id" type="hidden" value="${purOrder.ticket_id}"/>
							<input name="isArrival" type="hidden" value="0"/>
							<input id="product_code" name="product_code" type="text" class="easyui-validatebox" style="width:75px"/>
							<input id="product_id" name="product_id" type="hidden" class="easyui-validatebox" style="width:75px"/>
							<input id="unitprice" name="unitprice" type="text" data-options="validType:['money']" class="easyui-validatebox" style="width:100px"/>
							<input id="discount" name="" type="text" data-options="validType:['num']" class="easyui-validatebox" style="width:50px"/>
							<input id="settlementPrice" name="settlementPrice" data-options="validType:['money']" type="text"class="easyui-validatebox" style="width:100px"/>
							<input id="package" name="" type="text" data-options="validType:['num']" class="easyui-validatebox" style="width:50px"/>
							<input id="packageNum" readOnly="readonly" type="text" data-options="validType:['num']" class="easyui-validatebox" style="width:50px"/>
							<input id="quantity" name="quantity" data-options="validType:['money']" type="text"class="easyui-validatebox" style="width:75px"/>
							<input id="sum" name="sum" type="text" readonly="readonly" data-options="validType:['decimalFor6','length[1,20]']" class="easyui-validatebox" style="width:100px"/>
							<input id="prepaidForList" name="prepaidForList" type="text" data-options="validType:['decimalFor6','length[1,20]']" class="easyui-validatebox" style="width:100px"/>
							
							<input id="submit_current" type="button"  value="保存当前" onclick="submitCurrent();" style="height: 22px;width: 90px;"/> 
							<input id="perview" type="button"  value="预览打印格式" onclick="perviewPrint();" style="height: 22px;width: 90px;"/> 
						
							
						</div>
				</div>
				</form>
						
				</div>
		
		<div>
			<form id="accept" action="${acceptUrl}" method="${acceptMethod}" onsubmit="return false;" style="margin-top: 5%;"> 
		   <div>	
			<div data-options="region:'center',border:false" style="margin-top: -40px;height: 0px;">
				<div class="Dialog-formDiv clearfix">
					<div class="Dialog-form-title">
						<label for="accept_sterilizationbNum" class="field">随货票号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id=accept_sterilizationbNum name="accept_sterilizationbNum" class="easyui-validatebox" 
							value=""  style="width: 152px;"/>
					</div>
		
				    <div class="Dialog-form-title">
						<label for="department_id" class="field">选择存入库房编号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id=department_id name="department_id" class="easyui-validatebox" 
							value=""  style="width: 152px;"/>
					</div>
					
					<div class="Dialog-form-title">
						<label for="accept_batchCode" class="field">批号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_batchCode" name="accept_batchCode" type="text" data-options="required:true" class="easyui-validatebox" />
					</div>
					
					<div class="Dialog-form-title">
						<label for="accept_batchSmallCode" class="field">批号小号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_batchSmallCode" name="accept_batchSmallCode" type="text" class="easyui-validatebox" 
						data-options="required:true"/>
					</div>
					
					<div class="Dialog-form-title">
						<label for="accept_productionDate" class="field">生产日期：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_productionDate" name="accept_productionDate" type="text" class="easyui-datebox" data-options="required:true" style="width:152px"/>
					</div>
					<div class="Dialog-form-title">
						<label for="accept_expirationDate" class="field">有效期至：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_expirationDate" name="accept_expirationDate" type="text" class="easyui-datebox" data-options="required:true" style="width:152px" />
					</div>
					
					
				
				</div>
			
					
			
			</div>
			<br/><br/>
					<br/><br/>
					
				<div data-options="region:'center',border:false" style="margin-top: -45px;height: 0px;">
				<div class="Dialog-formDiv clearfix">
				  
					  <div class="Dialog-form-title">
						<label for="accept_sterilizationbNum" class="field">灭菌批号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_sterilizationbNum" name="accept_sterilizationbNum" 
						 type="text" class="easyui-validatebox" data-options="required:true" />
					</div>
					<div class="Dialog-form-title">
						<label for="accept_sterilizationbDate" class="field">灭菌日期：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_sterilizationbDate" name="accept_sterilizationbDate" type="text"
							class="easyui-datebox" data-options="required:true" style="width:152px"/>
					</div>
					
					<div class="Dialog-form-title">
						<label for="accept_sampleUnit" class="field">抽样件数：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_sampleUnit" name="accept_sampleUnit" type="text"
							class="easyui-validatebox" style="width: 152px;"
							data-options="required:true, validType:['num','length[1,10]']" />
					</div>
						<div class="Dialog-form-title">
						<label for="accept_sampleNumber" class="field">抽样数量：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_sampleNumber" name="accept_sampleNumber" type="text"
							class="easyui-validatebox" style="width: 152px;"
							data-options="required:true,validType:['num','length[1,10]']" />
					</div>
						<div class="Dialog-form-title">
						<label for="delivaryPlace" class="field">温控方式：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_tempCtrlMode" name="accept_tempCtrlMode" class="combobox" style="width:152px"
							value="" />
					</div>
					
					<div class="Dialog-form-title">
						<label for="orderDepartureTime" class="field">温控情况：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_tempCtrlSituation" name="accept_tempCtrlSituation" class="easyui-validatebox"
							 style="width:152px" value=""/>
					</div>
				
				</div>
			</div>
			<br/><br/>
					<br/><br/>
	         <div data-options="region:'center',border:false" style="margin-top: -40px;height: 0px;">
				<div class="Dialog-formDiv clearfix">
					
					<div class="Dialog-form-title">
						<label for="accept_startTemp" class="field">起运温度：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_startTemp" name="accept_startTemp" class="easyui-validatebox" 
							data-options="validType:['decimal','nosp']" value=""/>
					</div>
					<div class="Dialog-form-title">
						<label for="accept_arrivalTemp" class="field">到货温度：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_arrivalTemp" name="accept_arrivalTemp" class="easyui-validatebox" 
							data-options="validType:['decimal','nosp']" value=""/>
					</div>
					<div class="Dialog-form-title">
						<label for="accept_ambientTemp" class="field">环境温度：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_ambientTemp" name="accept_ambientTemp" class="easyui-validatebox" 
							data-options="validType:['decimal','nosp']" value=""/>
					</div>
					<div class="Dialog-form-title">
						<label for="accept_quantityPrintType" class="field">打印格式：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_quantityPrintType" name="accept_quantityPrintType" type="text"
							class="easyui-combobox"  style="width: 152px;" />
					</div>
					<div class="Dialog-form-title">
						<label for="accept_quantityCondition" class="field">质量状况：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_quantityCondition" name="accept_quantityCondition" type="text"
							class="easyui-combobox"  style="width: 152px;" />
					</div>
					<div class="Dialog-form-title">
						<label for="accept_checkConclusion" class="field">验收结论：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_checkConclusion" name="accept_checkConclusion" type="text"
							class="easyui-combobox"  style="width: 152px;" />
					</div>
				</div>
			</div>
			</div>
			<br/><br/>
					<br/><br/>					
				</form>	
				</div>
			
		<!-- 下方标签数据表 -->	
		<div id="tab-dg" class="easyui-tabs" style="height:650px;margin-top: -10px">   
			<div title="订单产品列表" id="tab-dg_1" style="height:650px;overflow-y:auto"> 
				<div data-options="region:'center',border:false" style="height:650px;width:100%;margin-top: 2px" fit="true">
					<table id="dg_purOrderList"></table>
				</div>
			</div>
			<div title="备用列表" id="tab-dg_2" > 
			
			</div>
		</div>
	</div>

	

	<!-- 编辑窗口 -->
	<div id="checkWindow"></div>
	<div id="supListWindow"></div>
	<div id="proListWindow"></div>
</body>
</html>