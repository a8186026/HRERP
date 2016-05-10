<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
function submitOrderListForm() {
	var data = util.submit('_formCheck');
	if (data) {
		if (data.result == "success") {
			util.show(data.message);
			$('#orderList_dg').datagrid('reload');
			util.closeWindow('editWindow');
		} else {
			util.error(data.message);
		}
	}
}

//订单审核 修改小单单价 折扣 自动计算折扣价
function unitpriceChange(){
	var unitprice = parseFloat($('#unitprice').attr("value"));
	var discount = parseFloat($('#discount').attr("value"));
	$("#settlementPrice").val((unitprice/100*discount).toFixed(6));

	var quantity = $("#quantity").val();
	var price = $("#settlementPrice").val();
	$("#sum").val((price*quantity).toFixed(6));
} 
//订单审核 修改小单数量 自动计算总价
function quantityChange (){
	var quantity = $("#quantity").val();
	var price = $("#settlementPrice").val();
	$("#sum").val((price*quantity).toFixed(6));
}
</script>

<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
			<form:form id="_formCheck" action="${formUrl}" method="${method}" onsubmit="return false;">
				<input name="id" type="hidden" value="${purOrderList.id}" />
				<input name="SPRING_TOKEN" type="hidden" value="${sessionScope.SPRING_TOKEN}" />
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="ticket_id" class="field">票号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="ticket_id" name="ticket_id" class="easyui-validatebox"
							value="${purOrderList.ticket_id}" style=" background:#EBEBE4" readonly="readonly"/>
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="unitprice" class="field">产品单价：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="unitprice" name="unitprice" class="easyui-validatebox"  onblur="unitpriceChange()" 
							value="${purOrderList.unitprice}" />
					</div>
				</div>
				
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="discount" class="field">折扣：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="discount" data-options="validType:['decimalFor6','length[1,5]']" class="easyui-validatebox" 
							value="${100*(purOrderList.settlementPrice/purOrderList.unitprice)}" onblur="unitpriceChange()"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="settlementPrice" class="field">产品结算价：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="settlementPrice" name="settlementPrice" class="easyui-validatebox" 
							value="${purOrderList.settlementPrice}"  style=" background:#EBEBE4" readonly="readonly"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="quantity" class="field">订单数量：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="quantity" name="quantity" class="easyui-validatebox"
							value="${purOrderList.quantity}" data-options="required:true,validType:'length[1,20]'"
							onblur="quantityChange()" />
					</div>
				</div>		
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="sum" class="field">订单总金额：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="sum" name="sum" class="easyui-validatebox"
							value="${purOrderList.sum}"  style=" background:#EBEBE4" readonly="readonly" />
					</div>
				</div>	
						 
			</form:form>
		</div>
	</div>

	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submitPurOrderList" class="btn-save"
			onmouseover="this.className='btn-save-over'"
			onmouseout="this.className='btn-save'"
			onmousedown="this.className='btn-save-down'" type="button"
			onclick="submitOrderListForm();" /> <input id="" class="btn-cancel"
			onmouseover="this.className='btn-cancel-over'"
			onmouseout="this.className='btn-cancel'"
			onmousedown="this.className='btn-cancel-down'" type="button"
			onclick="util.closeWindow('editWindow');" />
	</div>
</div>	
