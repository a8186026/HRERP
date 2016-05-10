<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/chnToLetter.js"></script>

<script type="text/javascript">

$(function() {
	//去除首尾逗号
	$('#gift_sal_joinDepartment').val($('#gift_sal_joinDepartment').val().substring(1,$('#gift_sal_joinDepartment').val().length-1));
	
	//拼音码
	$('#gift_sal_chn').combobox({
		editable:false,
		//value:$('#gift_sal_chn').attr("value"),
	});
	$('#gift_sal_chn').combobox("setValue", $('#gift_sal_chn').attr("value"));
	
	//参与部门
	$('#gift_sal_joinDepartment').combobox({
		url:global_param.context_name + "/basInfo/basDepartmentInfo/getList",
		method :"get",
		valueField : "department_id",
		textField : "department_fullname",
		value:($('#gift_sal_joinDepartment').val()=="")?null:$('#gift_sal_joinDepartment').val().split(','),
		multiple:true,
		editable:false,
	});
	
	
	//开始日期
 	$('#gift_sal_startDate').datebox({
		required:true,	
		value:($('#gift_sal_startDate').val()=="")?"":util.toDate($('#gift_sal_startDate').val()),
	}); 

	
	//结束日期
 	$('#gift_sal_endDate').datebox({
		required:true,	
		value:($('#gift_sal_endDate').val()=="")?"":util.toDate($('#gift_sal_endDate').val()),
	}); 
});


//复选框初始化赋值
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


function checkUnique(value){
	if($("#gift_sal_id").val() == ""){
		if(value.toString().length > 6){
			util.error("编号过长，自动更正");
			$("#gift_sal_code").val($("#default_code").val());
			return;
		}
		while(value.toString().length < 6)
			value = "0"+value;
		var result = util.get(global_param.context_name + "/promotion/giftsale/checkUnique?value="+value);	
		if(1 == result){
			util.error("编号已存在，自动更正");
			$("#gift_sal_code").val($("#default_code").val());
			return;
		}
	}
}


function submitGiftSaleForm() {
	//日期先后顺序验证
	var startDate = $('#gift_sal_startDate').datebox('getValue');
	var endDate = $('#gift_sal_endDate').datebox('getValue');
	
	var d1 = new Date(startDate.replace(/\-/g, "\/"));  
	var d2 = new Date(endDate.replace(/\-/g, "\/")); 
	
/* 	var idate1 = parseInt(startDate.replace(/\-/g, ""));
	var idate2 = parseInt(endDate.replace(/\-/g, ""));
	alert(idate1);
	alert(idate2);
	if(idate1>idate2)
		alert("开始大于结束"); */
		
		
	//日期验证提示
	if(d1>d2){  
		util.show("开始日期不能大于结束日期");  
		return;
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
	var data = util.submit('giftsale_form');
	if (data) {
		if (data.result == "success") {
			util.show(data.message);
			$('#GiftSaleDg').datagrid('reload');
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
			<form:form id="giftsale_form" action="${formUrl}" method="${method}" onsubmit="return false;">
				<input id="gift_sal_id" name="gift_sal_id" type="hidden" value="${pmnGiftSale.gift_sal_id}" />
				<input name="SPRING_TOKEN" type="hidden" value="${sessionScope.SPRING_TOKEN}" />
				<input id="default_code" type="hidden" value="${pmnGiftSale.gift_sal_code}" />
				<div>
	        		<div class="SelectAll" >
	        			<input name="gift_sal_onlyMemberJoin" type ="hidden" value="${pmnGiftSale.gift_sal_onlyMemberJoin}" />
						<label for="gift_sal_onlyMemberJoin" style="color:red;margin-left: 50px">只会员参加</label>
						<input id="gift_sal_onlyMemberJoin" type ="checkbox" style="width:20px" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="gift_sal_code" class="field">编号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="gift_sal_code" name="gift_sal_code" class="easyui-validatebox"
							value="${pmnGiftSale.gift_sal_code}" data-options="required:true,validType:['num']"  onblur = "checkUnique($(this).val())"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="gift_sal_startDate" class="field">开始日期：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="gift_sal_startDate" name="gift_sal_startDate" style="width:152px;"
							value="${pmnGiftSale.gift_sal_startDate}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="gift_sal_name" class="field">名称：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="gift_sal_name" name="gift_sal_name" class="easyui-validatebox"
							value="${pmnGiftSale.gift_sal_name}" onblur="chnToLetter($(this).val(),'gift_sal_chn')"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="gift_sal_endDate" class="field">结束日期：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="gift_sal_endDate" name="gift_sal_endDate" class="easyui-validatebox" style="width:152px;"
							value="${pmnGiftSale.gift_sal_endDate}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="gift_sal_chn" class="field">拼音码：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="gift_sal_chn" name="gift_sal_chn" class="easyui-combobox" style="width:152px;"
							value="${pmnGiftSale.gift_sal_chn}"  />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="gift_sal_activityTitle" class="field">活动标题：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="gift_sal_activityTitle" name="gift_sal_activityTitle" class="easyui-validatebox"
							value="${pmnGiftSale.gift_sal_activityTitle}"  />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="gift_sal_fullGiveCoefficient" class="field">满额赠系数：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="gift_sal_fullGiveCoefficient" name="gift_sal_fullGiveCoefficient" class="easyui-validatebox"
							value="${pmnGiftSale.gift_sal_fullGiveCoefficient}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="gift_sal_introduction" class="field">说明：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="gift_sal_introduction" name="gift_sal_introduction" class="easyui-validatebox"
							value="${pmnGiftSale.gift_sal_introduction}"
						/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="gift_sal_productPoint" class="field">产品积分：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="gift_sal_productPoint" name="gift_sal_productPoint" class="easyui-validatebox" style="width:30px;"
							data-options="validType:['num']"  value="${pmnGiftSale.gift_sal_productPoint}" /> <label>倍</label>
  					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="gift_sal_joinDepartment" class="field">参与部门：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="gift_sal_joinDepartment" name="gift_sal_joinDepartment" class="easyui-combobox" style="width:400px;"
							value="${pmnGiftSale.gift_sal_joinDepartment}" />
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
			onclick="submitGiftSaleForm();" /> <input id="" class="btn-cancel"
			onmouseover="this.className='btn-cancel-over'"
			onmouseout="this.className='btn-cancel'"
			onmousedown="this.className='btn-cancel-down'" type="button"
			onclick="util.closeWindow('editWindow');" />
	</div>
</div>