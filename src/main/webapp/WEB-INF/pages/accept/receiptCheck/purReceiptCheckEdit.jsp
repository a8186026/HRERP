<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript">
	/*  var ctrlsID = ["accept_startPlace","accept_tempCtrlMode","accept_tempCtrlSituation","accept_startTemp","accept_arrivalTemp","accept_ambientTemp","accept_transMode","accept_transCompany",
			"accept_arrivalNumber","accept_rejectNumber"];
	
	 //确认BUTTON
	var sumbitButtonID = "submitReceipt";
	var keyPress = new pageKeyPress();
	keyPress.bindKeyPressToCtrl(ctrlsID, sumbitButtonID); */
	$(function() {
		

		//承运方式
		$('#accept_transMode').combobox({
			url : global_param.context_name + "/system/combobox/lists?pid=619",
			method :"get",
			valueField : "cbs_chn",
			textField : "cbs_chn"
		});
		
		//温控方式
		$('#accept_tempCtrlMode').combobox({
			url : global_param.context_name + "/system/combobox/lists?pid=901",
			method :"get",
			valueField : "cbs_chn",
			textField : "cbs_chn"
		});
		$("#accept_arrivalNumber").val(parseInt($("#quantity").val()));
		
	});
		util.select({
			id : 'accept_tempCtrlSituation',
			param : {
				typeCode : 'CHECK_RESULT'
			}
		});
	function submitForm() {
		
		//alert($("#accept_acceptNumber").val()); 
		//alert($("#quantity").val());
		if(parseInt($("#accept_arrivalNumber").val()) > parseInt($("#quantity").val())){
			util.show("收货数量必须大于到货数量");
			return ;
		}else if(parseInt($("#accept_arrivalNumber").val()) < parseInt($("#accept_rejectNumber").val())){
			util.show("收货数量必须大于拒收数量");	
			return ;
		}
/* 		alert($("#accept_rejectNumber").val());
		alert($("#isArrival").val()); 
		if (parseInt($("#accept_rejectNumber").val()) > 0)
			$("#isArrival").val(2);
		
		alert($("#isArrival").val());  */
		var data = util.submit('_form');
		if (data) {
			if (data.result == "success") {
				util.show(data.message);
				$('#orderList_dg').datagrid('reload');
				$('#order_dg').datagrid('reload');
				util.closeWindow('editReceiptWindow');
			} else {
				util.error(data.message);
			}
		}
	}
	
	function setSum(){
		$("#accept_sum").val($("#accept_arrivalNumber").val()*$("#unitprice").val());
	}

</script>

<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
			<form:form id="_form" action="${formUrl}" method="${method}" onsubmit="return false;">
				<input name="pur_orderList_id" type="hidden" value="${purOrderList.id}" />
				<input id="isArrival" name="isArrival" type="hidden" value="${purOrderList.isArrival}" />
				<input name="SPRING_TOKEN" type="hidden"
					value="${sessionScope.SPRING_TOKEN}" />
					
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="ticket_id" class="field">票号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="ticket_id" name="ticket_id" class="easyui-validatebox" disabled="disabled"
							data-options="required:true"  value="${purOrderList.ticket_id}" />
					</div>
				</div>
				
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="unitprice" class="field">小单收货单价：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="unitprice" name="unitprice" class="easyui-validatebox" disabled="disabled"
							data-options="required:true"  value="${purOrderList.unitprice}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="sum" class="field">小单订单金额：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="sum" name="sum" class="easyui-validatebox" disabled="disabled"
							data-options="required:true"  value="${purOrderList.sum}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="quantity" class="field">小单待收数量：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="quantity" name="quantity" class="easyui-validatebox" disabled="disabled"
							data-options="required:true"  value="${purOrderList.quantity-purOrderList.arrivalNumber}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_startPlace" class="field">起运地点：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_startPlace" name="accept_startPlace" class="easyui-validatebox"
							value="" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_startTime" class="field">起运时间：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_startTime" name="accept_startTime" class="easyui-datebox" style="width:152px"
							data-options="required:true" value="" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_tempCtrlMode" class="field">温控方式：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_tempCtrlMode" name="accept_tempCtrlMode" class="combobox" style="width:152px"
							value="" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_tempCtrlSituation" class="field">温控情况：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_tempCtrlSituation" name="accept_tempCtrlSituation" class="easyui-validatebox"
							 style="width:152px" value=""/>
					</div>
				</div>
			    <div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_startTemp" class="field">起运温度：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_startTemp" name="accept_startTemp" class="easyui-validatebox" 
							data-options="required:true,validType:['decimal','nosp']" value=""/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_arrivalTemp" class="field">到货温度：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_arrivalTemp" name="accept_arrivalTemp" class="easyui-validatebox" 
							data-options="required:true,validType:['decimal','nosp']" value=""/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_ambientTemp" class="field">环境温度：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_ambientTemp" name="accept_ambientTemp" class="easyui-validatebox" 
							data-options="required:true,validType:['decimal','nosp']" value=""/>
					</div>
				</div> 
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_transMode" class="field">运输方式：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_transMode" name="accept_transMode" class="combobox" style="width:152px"
							value=""/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_transCompany" class="field">运输单位：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_transCompany" name="accept_transCompany" class="easyui-validatebox"
							value=""/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_arrivalNumber" class="field">到货数量：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_arrivalNumber" name="accept_arrivalNumber" class="easyui-validatebox"
							data-options="required:true" value="" onblur="setSum()"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_rejectNumber" class="field">拒收数量：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_rejectNumber" name="accept_rejectNumber" class="easyui-validatebox"
							data-options="required:true" value="" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="accept_sum" class="field">订货金额：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="accept_sum" name="accept_sum" class="easyui-validatebox" value="" disabled = "disabled"/>
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submitReceipt" class="btn-save"
			onmouseover="this.className='btn-save-over'"
			onmouseout="this.className='btn-save'"
			onmousedown="this.className='btn-save-down'" type="button"
			onclick="submitForm();" /> <input id="" class="btn-cancel"
			onmouseover="this.className='btn-cancel-over'"
			onmouseout="this.className='btn-cancel'"
			onmousedown="this.className='btn-cancel-down'" type="button"
			onclick="util.closeWindow('editReceiptWindow');" />
	</div>
</div>