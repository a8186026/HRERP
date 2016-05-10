<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">

//当前选择的产品
var proInfo = null;



$('#gift_productId').bind('keydown', function() {
    var e = event || window.event;
    var keyCode = e.keyCode || e.which;
    var actElement = document.activeElement;
    //如果按键是enter并且当前焦点是产品编号，则弹出产品选择框
    if(keyCode == 13&&actElement.id == "gift_productId"){
 		var proContent = $('#gift_productId').attr("value");
		$("#proListWindow").window({
			title : '产品选择',
			width : 1250,
			height : 650,
			shadow : true,
			modal : true,
			closed : true,
			minimizable : false,	
			maximizable : false,
			collapsible : false,
			resizable : false,
			content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/purchase/order/viewProList?callback=setProContent&proContent=' + proContent.replace("%", "%25") +'"></iframe>',
		});
		$('#proListWindow').window('open');
 		
    }
});
	
//获得子窗口的产品信息
function setProContent(node){
	//存储当前的产品信息
	proInfo = node;
	//修改控件信息
	$("#gift_productId").attr("value",node.product_id);
};

$(function() {
	var CK = document.getElementsByTagName('input');
	for (var i = 0; i < CK.length; i++) {
		if (CK[i].type == "checkbox"
				&& document.getElementsByName(CK[i].id)[0].value == "1") {
			CK[i].checked = true;
		} else if (CK[i].type == "checkbox"
				&& document.getElementsByName(CK[i].id)[0].value == "0") {
			CK[i].checked = false;
		}
	}
});


function submitGiftInfoForm() {
	
	
	//提交复选框
	var getCK = document.getElementsByTagName('input');
	for (var i = 0; i < getCK.length; i++) {
		if (getCK[i].type == "checkbox" && getCK[i].checked == true) {
			var j = document.getElementsByName(getCK[i].id);
			j[0].value = 1;
		} else if (getCK[i].type == "checkbox" && getCK[i].checked == false) {
			var j = document.getElementsByName(getCK[i].id);
			j[0].value = 0;
		}
	}
	
	var data = util.submit('giftinfo_form');
	if (data) {
		if (data.result == "success") {
			util.show(data.message);
			$('#GiftInfoDg').datagrid('reload');
			$('#GiftSaleDg').datagrid('reload');
		//	$('#GiftSaleDg').datagrid('selectRow',$("#gift_sal_id").val());
			util.closeWindow('editGiftInfoWindow');
		} else {
			util.error(data.message);
		}
	}
};
</script>

<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
			<form:form id="giftinfo_form" action="${formUrl}" method="${method}" onsubmit="return false;">
				<input name= "gift_sal_id" type="hidden" value="${gift_sal_id}" />
				<input name="gift_id" type="hidden" value="${giftInfo.gift_id}" />
				<input name="SPRING_TOKEN" type="hidden" value="${sessionScope.SPRING_TOKEN}" />
				<div >
	        		<div class="SelectAll" >
	        			<input name="gift_specialBatch" type ="hidden" value="${giftInfo.gift_specialBatch}" />
						<label for="gift_specialBatch" style="color:red;margin-left: 50px">特殊批次</label>
						<input id="gift_specialBatch" type ="checkbox" style="width:20px" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="gift_varietyId" class="field">品种序号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="gift_varietyId" name="gift_varietyId" class="easyui-validatebox" readonly="readonly"
							value="${gift_sal_id}" />
					</div>
				</div>
				<div>
					<div class="Dialog-form-title">
						<label for="gift_productId" class="field">产品序号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="gift_productId" name="gift_productId" class="easyui-validatebox"
							value="${giftInfo.gift_productId}" />
					</div>
				</div>
				
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="gift_commission" class="field">单个提成：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="gift_commission" name="gift_commission" class="easyui-validatebox"
							data-options="validType:['decimal']" value="${giftInfo.gift_commission}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="gift_amount" class="field">数量：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="gift_amount" name="gift_amount" class="easyui-validatebox"
							data-options="validType:['decimal']"	value="${giftInfo.gift_amount}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="gift_retailPrice" class="field">零售价：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="gift_retailPrice" name="gift_retailPrice" class="easyui-validatebox"
							data-options="validType:['decimal']"	value="${giftInfo.gift_retailPrice}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="gift_memberPrice" class="field">会员价：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="gift_memberPrice" name="gift_memberPrice" class="easyui-validatebox"
							data-options="validType:['decimal']"	value="${giftInfo.gift_memberPrice}" />
					</div>
				</div>	
			</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submitMedicine" class="btn-save"
			onmouseover="this.className='btn-save-over'"
			onmouseout="this.className='btn-save'"
			onmousedown="this.className='btn-save-down'" type="button"
			onclick="submitGiftInfoForm();" /> <input id="" class="btn-cancel"
			onmouseover="this.className='btn-cancel-over'"
			onmouseout="this.className='btn-cancel'"
			onmousedown="this.className='btn-cancel-down'" type="button"
			onclick="util.closeWindow('editGiftInfoWindow');" />
	</div>
</div>