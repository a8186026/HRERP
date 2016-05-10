<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/chnToLetter.js"></script>

<script type="text/javascript">


//当前选择的产品
var proInfo = null;



$('#product_id').bind('keydown', function() {
    var e = event || window.event;
    var keyCode = e.keyCode || e.which;
    var actElement = document.activeElement;
    //如果按键是enter并且当前焦点是产品编号，则弹出产品选择框
    if(keyCode == 13&&actElement.id == "product_id"){
 		var proContent = $('#product_id').attr("value");
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
	$("#product_id").attr("value",node.product_id);
};


function chooseProduct(){
	util.openWindow('editFulfillProductWindow', "fulfillgift/listPro?full_gift_joinProduct=" + $("#full_gift_joinProduct").val());
}


$(function() {
	//去除首尾逗号
	$('#full_gift_joinDepartment').val($('#full_gift_joinDepartment').val().substring(1,$('#full_gift_joinDepartment').val().length-1));
	$('#full_gift_joinProduct').val($('#full_gift_joinProduct').val().substring(1,$('#full_gift_joinProduct').val().length-1));
	//参与部门
		$('#full_gift_exchangeRule').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=983",
		method : "get",
		valueField : "cbs_id",
		textField : "cbs_chn",
		editable : false,
	});
	
	$('#full_gift_joinDepartment').combobox({
		url:global_param.context_name + "/basInfo/basDepartmentInfo/getList",
		method :"get",
		valueField : "department_id",
		textField : "department_fullname",
		value:($('#full_gift_joinDepartment').val()=="")?null:$('#full_gift_joinDepartment').val().split(','),
		multiple:true,
		editable:false,
	});
	
	
	//开始日期
 	$('#full_gift_startDate').datebox({
		required:true,	
		value:($('#full_gift_startDate').val()=="")?"":util.toDate($('#full_gift_startDate').val()),
	}); 

	
	//结束日期
 	$('#full_gift_endDate').datebox({
		required:true,	
		value:($('#full_gift_endDate').val()=="")?"":util.toDate($('#full_gift_endDate').val()),
	}); 



	//复选框初始化赋值
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


function submitForm() {
	
	
	//日期先后顺序验证
	var startDate = $('#full_gift_startDate').datebox('getValue');
	var endDate = $('#full_gift_endDate').datebox('getValue');

	var d1 = new Date(startDate.replace(/\-/g, "\/"));  
	var d2 = new Date(endDate.replace(/\-/g, "\/")); 
	//日期验证提示
	if(d1>d2){  
		util.show("开始日期不能大于结束日期");  
		return;
	}
	
	//判断产品Id是否正确输入，新增与修改区分判断
	if($("#full_gift_id").val==""){
		if(proInfo==null||$('#product_code').val()!=proInfo.product_code){
			util.show("产品编码不存在！");
			return;
		}
	}else{
		if(proInfo!=null&&$('#product_id').val()!=proInfo.product_id){
			util.show("产品编码不存在！");
			return;
		}
	}
	
	
	
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
	var data = util.submit('fulfillgift_form');
	if (data) {
		if (data.result == "success") {
			util.show(data.message);
			$('#FulfillGiftDg').datagrid('reload');
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
			<form:form id="fulfillgift_form" action="${formUrl}" method="${method}" onsubmit="return false;">
				<input id="full_gift_id" name="full_gift_id" type="hidden" value="${pmnFulfillGift.full_gift_id}" />
				<input name="SPRING_TOKEN" type="hidden" value="${sessionScope.SPRING_TOKEN}" />
				<div>
					<div class="float-l">
		        		<div class="SelectAll" >
		        			<input name="full_gift_onlyMemberJoin" type ="hidden" value="${pmnFulfillGift.full_gift_onlyMemberJoin}" />
							<label for="full_gift_onlyMemberJoin" style="color:red;margin-left: 50px">只会员参加</label>
							<input id="full_gift_onlyMemberJoin" type ="checkbox" style="width:20px" />
						</div>
					</div>
					<div class="SelectAll" >
	        			<input name="full_gift_specialBatch" type ="hidden" value="${pmnFulfillGift.full_gift_specialBatch}" />
						<label for="full_gift_specialBatch" style="color:red;margin-left: 150px">特殊批次</label>
						<input id="full_gift_specialBatch" type ="checkbox" style="width:20px" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="product_id" class="field">产品：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="product_id" name="product_id" class="easyui-validatebox"
							value="${pmnFulfillGift.product_id}" data-options="required:true,validType:['num']"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="full_gift_startDate" class="field">开始日期：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="full_gift_startDate" name="full_gift_startDate" style="width:152px;"
							value="${pmnFulfillGift.full_gift_startDate}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="full_gift_unitPrice" class="field">单价：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="full_gift_unitPrice" name="full_gift_unitPrice" class="easyui-validatebox"
							data-options="validType:['decimal']"	value="${pmnFulfillGift.full_gift_unitPrice}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="full_gift_endDate" class="field">结束日期：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="full_gift_endDate" name="full_gift_endDate" class="easyui-validatebox" style="width:152px;"
							value="${pmnFulfillGift.full_gift_endDate}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="full_gift_amount" class="field">数量：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="full_gift_amount" name="full_gift_amount" class="easyui-validatebox"
							data-options="validType:['decimal']"	value="${pmnFulfillGift.full_gift_amount}"  />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="full_gift_productPoint" class="field">产品积分：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="full_gift_productPoint" name="full_gift_productPoint" class="easyui-validatebox"
						 data-options="validType:['num']" style="width:30px;" value="${pmnFulfillGift.full_gift_productPoint}"  /> 
						 <label>倍</label>
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="full_gift_exchangeAmount" class="field">兑换金额：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="full_gift_exchangeAmount" name="full_gift_exchangeAmount" class="easyui-validatebox"
							data-options="validType:['decimal']"  value="${pmnFulfillGift.full_gift_exchangeAmount}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="full_gift_retailCommission" class="field">零售提成：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="full_gift_retailCommission" name="full_gift_retailCommission" class="easyui-validatebox"
							value="${pmnFulfillGift.full_gift_retailCommission}"
						/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="full_gift_exchangeRule" class="field">兑换规则：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="full_gift_exchangeRule" name="full_gift_exchangeRule"  class="easyui-combobox" style="width:152px;"
							value="${pmnFulfillGift.full_gift_exchangeRule}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="full_gift_joinDepartment" class="field">参与部门：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="full_gift_joinDepartment" name="full_gift_joinDepartment" class="easyui-combobox" style="width:400px;"
							value="${pmnFulfillGift.full_gift_joinDepartment}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="full_gift_joinProduct" class="field">参与产品：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="full_gift_joinProduct" name="full_gift_joinProduct" class="easyui-validatebox" style="width:400px;"
							value="${pmnFulfillGift.full_gift_joinProduct}" onfocus= "chooseProduct()"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="full_gift_remark" class="field">备注：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="full_gift_remark" name="full_gift_remark" class="easyui-validatebox" style="width:400px;"
							value="${pmnFulfillGift.full_gift_remark}"
						/>
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
			onclick="submitForm();" /> <input id="" class="btn-cancel"
			onmouseover="this.className='btn-cancel-over'"
			onmouseout="this.className='btn-cancel'"
			onmousedown="this.className='btn-cancel-down'" type="button"
			onclick="util.closeWindow('editWindow');" />
	</div>
</div>