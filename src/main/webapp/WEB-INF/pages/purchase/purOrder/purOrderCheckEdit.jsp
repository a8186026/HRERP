<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<title>审核时修改</title>
<script type="text/javascript">

$(function() {
	
	//实现时间控件注册，并赋初值 启运时间
 	$('#orderDepartureTime').datebox({
		required:true,	
	}); 
	if($('#orderDepartureTime').val()!="")
		$('#orderDepartureTime').datebox("setValue",util.toDate($('#orderDepartureTime').val()));
	
	//实现时间控件注册，并赋初值 到货时间
 	$('#arrivalDate').datebox({
		required:true,	
	}); 
	if($('#arrivalDate').val()!="")
		$('#arrivalDate').datebox("setValue",util.toDate($('#arrivalDate').val()));
	
	
	//摘要
	$('#orderabstract').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=600",
		method :"get",
		valueField : "cbs_chn",
		textField : "cbs_chn"
	});
	//结款方式
	$('#knotStyle').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=604",
		method :"get",
		valueField : "cbs_chn",
		textField : "cbs_chn"
	});
	//政策形式
	$('#policyStyle').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=611",
		method :"get",
		valueField : "cbs_chn",
		textField : "cbs_chn",
	});
	//承运方式
	$('#carryMode').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=615",
		method :"get",
		valueField : "cbs_chn",
		textField : "cbs_chn"
	});
	//运输方式
	$('#orderTransMode').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=619",
		method :"get",
		valueField : "cbs_chn",
		textField : "cbs_chn"
	});
});

</script>
	<div class="easyui-layout" fit="true">
		<div region="center" border="false" class="Dialog-Bg">
			<div class="Dialog-formDiv clearfix">
				<form:form id="_formPurOrder" action="${formUrl}" method="${method}"
					onsubmit="return false;">
					<input name="id" type="hidden" value="${purOrder.id}" />
					<input name="SPRING_TOKEN" type="hidden"
						value="${sessionScope.SPRING_TOKEN}" />

					<div class="float-l">
						<div class="Dialog-form-title">
							<label for="ticket_id" class="field">票号：</label>
						</div>
						<div class="Dialog-form-item">
							<input id="ticket_id" name="ticket_id" class="easyui-validatebox"
								value="${purOrder.ticket_id}" readonly="readonly" />
						</div>
					</div>
					<div class="float-l">
						<div class="Dialog-form-title">
							<label for="supply_code" class="field">客户编码：</label>
						</div>
						<div class="Dialog-form-item">
							<input id="supply_code" name="supply_code"
								class="easyui-validatebox" value="${purOrder.supply_code}"
								readonly="readonly" />
						</div>
					</div>
					<div class="float-l">
						<div class="Dialog-form-title">
							<label for="supply_fullname" class="field">客户全称：</label>
						</div>
						<div class="Dialog-form-item">
							<input id="supply_fullname" name="supply_fullname"
								class="easyui-validatebox" value="${purOrder.supply_fullname}"
								readonly="readonly" />
						</div>
					</div>
					<div class="float-l">
						<div class="Dialog-form-title">
							<label for="orderabstract" class="field">摘要：</label>
						</div>
						<div class="Dialog-form-item">
							<input id="orderabstract" name="orderabstract"
								class="easyui-combobox" style="width: 152px"
								value="${purOrder.orderabstract}" />
						</div>
					</div>
					<div class="float-l">
						<div class="Dialog-form-title">
							<label for="knotStyle" class="field">结款方式：</label>
						</div>
						<div class="Dialog-form-item">
							<input id="knotStyle" name="knotStyle" class="easyui-combobox"
								style="width: 152px" value="${purOrder.knotStyle}" />
						</div>
					</div>
					<div class="float-l">
						<div class="Dialog-form-title">
							<label for="policyStyle" class="field">政策形式：</label>
						</div>
						<div class="Dialog-form-item">
							<input id="policyStyle" name="policyStyle"
								class="easyui-combobox" style="width: 152px"
								value="${purOrder.policyStyle}" />
						</div>
					</div>
					<div class="float-l">
						<div class="Dialog-form-title">
							<label for="remarks" class="field">备注：</label>
						</div>
						<div class="Dialog-form-item">
							<input id="remarks" name="remarks" class="easyui-validatebox"
								value="${purOrder.remarks}" />
						</div>
					</div>
					<div class="float-l">
						<div class="Dialog-form-title">
							<label for="carryMode" class="field">承运方式：</label>
						</div>
						<div class="Dialog-form-item">
							<input id="carryMode" name="carryMode" class="easyui-combobox"
								style="width: 152px" value="${purOrder.carryMode}" />
						</div>
					</div>
					<div class="float-l">
						<div class="Dialog-form-title">
							<label for="orderTransMode" class="field">运输方式：</label>
						</div>
						<div class="Dialog-form-item">
							<input id="orderTransMode" name="orderTransMode"
								class="easyui-combobox" style="width: 152px"
								value="${purOrder.orderTransMode}" />
						</div>
					</div>
					<div class="float-l">
						<div class="Dialog-form-title">
							<label for="carryCompany" class="field">承运单位：</label>
						</div>
						<div class="Dialog-form-item">
							<input id="carryCompany" name="carryCompany"
								class="easyui-validatebox" value="${purOrder.carryCompany}" />
						</div>
					</div>
					<div class="float-l">
						<div class="Dialog-form-title">
							<label for="delivaryPlace" class="field">发运地点：</label>
						</div>
						<div class="Dialog-form-item">
							<input id="delivaryPlace" name="delivaryPlace"
								class="easyui-validatebox" value="${purOrder.delivaryPlace}" />
						</div>
					</div>
					<div class="float-l">
						<div class="Dialog-form-title">
							<label for="orderDepartureTime" class="field">启运时间：</label>
						</div>
						<div class="Dialog-form-item">
							<input id="orderDepartureTime" name="orderDepartureTime" 
							    style="width: 152px" value="${purOrder.orderDepartureTime}" />
						</div>
					</div>
					<div class="float-l">
						<div class="Dialog-form-title">
							<label for="arrivalDate" class="field">到货时间：</label>
						</div>
						<div class="Dialog-form-item">
							<input id="arrivalDate" name="arrivalDate" 
								style="width: 152px" value="${purOrder.arrivalDate}" />
						</div>
					</div>
				</form:form>
			</div>
		</div>
		<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submitPurOrder" class="btn-save"
			onmouseover="this.className='btn-save-over'"
			onmouseout="this.className='btn-save'"
			onmousedown="this.className='btn-save-down'" type="button"
			onclick="submitOrderForm();" /> <input id="" class="btn-cancel"
			onmouseover="this.className='btn-cancel-over'"
			onmouseout="this.className='btn-cancel'"
			onmousedown="this.className='btn-cancel-down'" type="button"
			onclick="util.closeWindow('editPurOrderWindow');" />
	</div>
	</div>
