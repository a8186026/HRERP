<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>返货付货登记</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script src="/HRERP/resources/js/refund/refundRegister.js" type="text/javascript"></script>

</head>
<body>
<!--  readonly="readonly" disabled="disabled" -->
<div id="mainContent" class="easyui-layout" fit="true"
		style="width: 100%; height: 100%;">
		<div id="searchBar"
			data-options="region:'north',border:false,split:false"
			class="l-panel-search clearfix centertoolbar-w"
			>
			<div class="l-searchbar clearfix centertoolbar-w1">
				<div class="l-panel-search-cond clearfix" id="resetIt">
				<form:form id="form" action="${formUrl}" method="${method}">
				
					<input id="product_id" name="product_id" type="hidden" value =""/>
	                <input id="stock_intakeSmallNumber" name="stock_intakeSmallNumber" type="hidden" value =""/>
	                <input id="stock_invalidDate" name="stock_invalidDate" type="hidden" value =""/> 
	                <input id="stock_info_id" name="stock_info_id"  type="hidden" value =""/> 
	                 <input id="sup_id" name="sup_id"  type="hidden" value=""  /> 
	                <input id="refund_operator" name="refund_operator"  type="hidden" value="${user.user_id}"  /> 
	                
					   <div class="float-l">
							<div class="l-panel-search-title">票号：</div>
							<div class="l-panel-search-item">
								<input id="refund_ticketId" name="refund_ticketId" type="text" readonly="readonly" value="${ticket_id}"  />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">产品编号：</div>
							<div class="l-panel-search-item">
								<input id="product_code" name="product_code"  type="text" readonly="readonly" value="" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">操作员：</div>
							<div class="l-panel-search-item">
								<input id="operator" name="operator" readonly="readonly" type="text" class="easyui-validatebox" value="${user.display_name}" />
							</div>
						</div>
				
						<div class="float-l">
							<div class="l-panel-search-title">入库票号：</div>
							<div class="l-panel-search-item">
								<input id="stock_intakeTicket" name="stock_intakeTicket" type="text" readonly="readonly" value=""  />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">库房：</div>
							<div class="l-panel-search-item">
								<input id="stock_storage" name="stock_storage"   type="text" readonly="readonly" value=""/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">联系人：</div>
							<div class="l-panel-search-item">
								<input id="sup_ctactperson" name="sup_ctactperson"  type="text" readonly="readonly" value=""  />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">供方编码：</div>
							<div class="l-panel-search-item">
								<input id="sup_code" name="sup_code" type="text" readonly="readonly" value=""  />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">批号：</div>
							<div class="l-panel-search-item">
								<input id="stock_batchCode" name="stock_batchCode"  type="text" readonly="readonly" value="" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">进价：</div>
							<div class="l-panel-search-item">
								<input id="stock_purchasePrice" name="stock_purchasePrice"  readonly="readonly"  type="text" value="" />
							</div>
						</div>
						
	
						<div class="float-l">
							<div class="l-panel-search-title">退货负责人：</div>
							<div class="l-panel-search-item">
								<input id="refund_chiefPerson" name="refund_chiefPerson" type="text" value=""  />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">退货数量：</div>
							<div class="l-panel-search-item">
								<input id="refund_number" name="refund_number" type="text" value="" />
								<input id="refund_number_limit" type="hidden" disabled="disabled" style="color:red" value="0" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">退货单价：</div>
							<div class="l-panel-search-item">
								<input id="refund_unitPrice" name="refund_unitPrice"  type="text" value="" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">退货金额：</div>
							<div class="l-panel-search-item">
								<input id="refund_money" name="refund_money"  readonly="readonly"  type="text" value="" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">退货原因：</div>
							<div class="l-panel-search-item">
								<input id="refund_reason" name="refund_reason"  type="text"  value=""/>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		<!-- 显示区域 -->
		<div data-options="region:'center',border:false"
			style="overflow: hidden;">
			<table id="dg"></table>
		</div>
	</div>
	<!-- 编辑窗口 -->
	<div id="proListWindow"></div>
</body>
</html>