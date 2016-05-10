<%@page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>进货订单</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript"	src="/HRERP/resources/js/JsUtil/userHabits.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/purchase/purOrderEdit.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
	<!-- 获取后台传回的form提交信息 -->
	<input id="orderFormUrl" type="hidden" value="${orderFormUrl}"/>
	<input id="orderMethod" type="hidden" value="${orderMethod}"/>
	<div id="mainContent" class="easyui-layout" fit="true" style="width: 100%; height: 100%;">
			<div data-options="region:'center',border:false">
				<input id="id" name="id" type="hidden" class="easyui-validatebox" value="${purOrder.id}"/>
				<div class="Dialog-form-title">
					<label for="ticket_id" class="field">票号：</label>
				</div>
				<div class="Dialog-form-item">
					<input id="ticket_id" name="ticket_id" readonly="readonly" type="text" class="easyui-validatebox" value="${purOrder.ticket_id}"/>
				</div>
				<div class="Dialog-form-title">
					<label for="operator" class="field">操作员：</label>
				</div>
				<div class="Dialog-form-item">
					<input id="operator" name="operator" readonly="readonly" type="text" class="easyui-validatebox" value="${user.display_name}" style="width:70px"/>
				</div>
			</div>
			<div data-options="region:'center',border:false" style="height:106px;margin-top:28px;">
				<table id="dg_purchase_product"></table>
			</div>
			<div data-options="region:'center',border:false" style="height:150px ;width:100%" fit="true" >			
				<table id="dg_supply_product" ></table>
			</div>
			<div data-options="region:'center',border:false" style="height:150px ;width:100%" fit="true" >			
				<table id="dg_other_product"></table>
			</div>
			<div data-options="region:'center',border:false" style="margin-top: -5px;height: 0px;">
				<div class="Dialog-formDiv clearfix">
					<div class="Dialog-form-title">
						<label for="orderabstract" class="field">摘&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;要：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="orderabstract" name="orderabstract" type="text" class="combobox" value="${purOrder.orderabstract}"/>
					</div>
					<div class="Dialog-form-title">
						<label for="knotStyle" class="field">结款方式：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="knotStyle" name="knotStyle" type="text" class="combobox" value="${purOrder.knotStyle}"/>
					</div>
					<div class="Dialog-form-title">
						<label for="policyStyle" class="field">政策形式：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="policyStyle" name="policyStyle" type="text" class="combobox" value="${purOrder.policyStyle}"/>
					</div>
					<div class="Dialog-form-title">
						<label for="remarks" class="field">备注：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="remarks" name="remarks" type="text" class="easyui-validatebox" value="${purOrder.remarks}" style="width:181px;"/>
					</div>
				</div>
			</div>
			<br/><br/>
			<div data-options="region:'center',border:false" style="margin-top: -10px;height: 0px;">
				<div class="Dialog-formDiv clearfix">
					<div class="Dialog-form-title">
						<label for="carryMode" class="field">承运方式：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="carryMode" name="carryMode" type="text" class="combobox" value="${purOrder.carryMode}"/>
					</div>
					<div class="Dialog-form-title">
						<label for="orderTransMode" class="field">运输方式：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="orderTransMode" name="orderTransMode" type="text" class="combobox" value="${purOrder.orderTransMode}"/>
					</div><div class="Dialog-form-title">
						<label for="carryCompany" class="field">承运单位：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="carryCompany" name="carryCompany" type="text" class="easyui-validatebox" style="width:181px" value="${purOrder.carryCompany}"/>
					</div>
				</div>
			</div>
			<br/><br/>
			<div data-options="region:'center',border:false" style="margin-top: -10px;height: 0px;">
				<div class="Dialog-formDiv clearfix">
					<div class="Dialog-form-title">
						<label for="delivaryPlace" class="field">发运地点：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="delivaryPlace" name="delivaryPlace" type="text"class="easyui-validatebox" value="${purOrder.delivaryPlace}" style="width: 488px"/>
					</div>
					<div class="Dialog-form-title">
						<label for="orderDepartureTime" class="field">启运时间：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="orderDepartureTime" name="orderDepartureTime"  value="${purOrder.orderDepartureTime}" />
					</div>
					<div class="Dialog-form-title">
						<label for="arrivalDate" class="field">到货时间：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="arrivalDate" name="arrivalDate"  value="${purOrder.arrivalDate}"/>
					</div>
				</div>
			</div>
			<br/><br/>
			<div data-options="region:'center',border:false" style="margin-top: -10px;height: 0px;">
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
				</div>
			</div>
		<br/><br/>
			<div data-options="region:'center',border:false" style="margin-top: -10px;">
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
							<input id="unitprice" name="unitprice" type="text" data-options="validType:['decimalFor6','length[1,20]']" class="easyui-validatebox" style="width:100px"/>
							<input id="discount" name="" type="text" data-options="validType:['decimalFor6','length[1,5]']" class="easyui-validatebox" style="width:50px"/>
							<input id="settlementPrice" name="settlementPrice" data-options="validType:['decimalFor6','length[1,20]']" type="text"class="easyui-validatebox" style="width:100px"/>
							<input id="package" name="" type="text" data-options="validType:['num']" class="easyui-validatebox" style="width:50px"/>
							<input id="packageNum" readOnly="readonly" type="text" data-options="validType:['num']" class="easyui-validatebox" style="width:50px"/>
							<input id="quantity" name="quantity" data-options="validType:['money']" type="text"class="easyui-validatebox" style="width:75px"/>
							<input id="sum" name="sum" type="text" readonly="readonly" data-options="validType:['decimalFor6','length[1,20]']" class="easyui-validatebox" style="width:100px"/>
							<input id="prepaidForList" name="prepaidForList" type="text" data-options="validType:['decimalFor6','length[1,20]']" class="easyui-validatebox" style="width:100px"/>
							
							<input id="submit_current" type="button" value="保存当前" onclick="submitCurrent();" style="height: 22px;width: 75px;"/> 
							<input id="cancel_current" type="button" value="取消当前" onclick="cancelCurrent();" style="height: 22px;width: 75px;"/>
							<input id="submit_all" type="button" value="保存全部" onclick="submitAll();" style="height: 22px;width: 75px;"/> 
							<input id="cancel_all" type="button" value="取消全部" onclick="cancelAll();" style="height: 22px;width: 75px;"/>
						</div>
				</div>
			</form>
			</div>
			
		<!-- 下方标签数据表 -->	
		<div id="tab-dg" class="easyui-tabs" style="height:250px">   
			<div title="订单产品列表" id="tab-dg_1" style="height:210px;overflow-y:auto"> 
				<div data-options="region:'center',border:false" style="height:210px;width:100%;margin-top: 2px" fit="true">
					<table id="dg_purOrderList"></table>
				</div>
			</div>
			<div title="备用列表" id="tab-dg_2" > 
			
			</div>
		</div>
	</div>
	<!-- 编辑窗口 -->
	<div id="supListWindow"></div>
	<div id="proListWindow"></div>
	<div id="editWindow0"></div>
	<div id="editWindow1"></div>
	<div id="editWindow2"></div>
	<div id="editWindow3"></div>
</body>
</html>