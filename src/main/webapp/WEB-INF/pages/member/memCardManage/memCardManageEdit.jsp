<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/chnToLetter.js"></script>
<script type="text/javascript">
//所有控件ID
var ctrlsID = [ "mem_card_type", "mem_card_number", "mem_card_name", "mem_card_chn", "mem_card_gender", "mem_card_tel",
                "mem_card_cellPhone","mem_card_idCard","mem_card_postCode","mem_card_address","mem_card_remark",
                "mem_card_acountMoney","mem_card_startMoney","mem_point_toMoney","mem_card_discount"];
//确认BUTTON
var sumbitButtonID = "submitMem";
var tabID = "tab-mem";
var tabChangID = ["mem_card_remark"];

var mem_card_num = $('#mem_card_number').val();//记录会员卡号


$(function(){
	 $('#mem_card_chn').combobox({
		url : "",
		method :"",
		valueField : "id",
		textField : "text",
		editable: false,
	});
	$('#mem_card_chn').combobox("setValue",$('#mem_card_chn').attr("value"));
	
	
	// 性别下拉框
	util.select({
		id : 'mem_card_gender',
		required : true,
		param : {
			typeCode : 'SEX'
		}
	});
	// 会员卡类别下拉框
	$('#mem_card_type').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=51",
		method :"get",
		valueField : "cbs_id",
		textField : "cbs_chn",
		editable:false,
	});
	// 病史备选下拉框
	$('#mem_card_history_choose').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=87",
		method :"get",
		valueField : "cbs_chn",
		textField : "cbs_chn",
		editable:false,
		onChange: function(n,o){
			addTextToArea("mem_card_history",n);
		}
	});
	// 过敏史备选下拉框
	$('#mem_card_allergicHistory_choose').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=95",
		method :"get",
		valueField : "cbs_chn",
		textField : "cbs_chn",
		editable:false,
		onChange: function(n,o){
			addTextToArea("mem_card_allergicHistory",n);
		}
	});
	// 标签页初始化
	$('#tab-mem').tabs({ 
		//fit:true,
		width:580,
	    onSelect: function(){  
	    }  
	});
	
	var keyPress = new pageKeyPress();
	keyPress.setTabChangeID(tabID, tabChangID);
	keyPress.bindKeyPressToCtrl(ctrlsID, sumbitButtonID);
});

// 向textArea中添加新
function addTextToArea(textAreaID, newText){
	var textArea = $("#"+textAreaID).attr("value");
	if(textArea==null || textArea==""){
		textArea = textArea + newText;
	} else {
		var flag = true ;
		var items = textArea.split('\n');
		for(var i = 0 ; i < items.length ; i++){
			if(items[i] == newText){
				flag = false;
				break;
			}
		}
		if(flag){
			textArea = textArea +'\n'+ newText;
		}
	}
	$("#"+textAreaID).attr("value",textArea);
};


function submitForm() {
	var CardId = $("#mem_card_number").attr("value");
	var flag = true;
	if(mem_card_num!=CardId||mem_card_num==""){
	    flag = util.get(global_param.context_name + "/member/memCardManage/checkMemCardID?CardId=" + CardId);
	}
	if(flag == true){
		if($('#mem_card_acountMoney').val()==""){
			util.show("请输入会员提点信息");
		}
		var data = util.submit('_form');
		if (data) {
			if (data.result == "success") {
				util.show(data.message);
				$('#dg').datagrid('reload');
				util.closeWindow('editWindow');
			} else {
				util.error(data.message);
			}
		}
	}else{
		util.error("会员卡号已存在，请更换");
		$("#mem_card_number").attr("value","");
	}
}


	

</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
			<form:form id="_form" action="${formUrl}" method="${method}">
				<input name="SPRING_TOKEN" type="hidden" value="${sessionScope.SPRING_TOKEN}" />
				<input name="mem_card_id" type="hidden" value="${memCardManage.mem_card_id}" />
				<div id="tab-mem" class="easyui-tabs" style="height: 400px">
					<div title="会员基本信息" id="tab-mem_1">
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="mem_card_type" class="field">类别：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="mem_card_type" name="mem_card_type"
									class="easyui-combobox" data-options="required:true" style="width:152px;"
									value="${memCardManage.mem_card_type}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="mem_card_number" class="field">卡号：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="mem_card_number" name="mem_card_number"
									class="easyui-validatebox" data-options="required:true" 
									value="${memCardManage.mem_card_number}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="mem_card_name" class="field">姓名：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="mem_card_name" name="mem_card_name"
									class="easyui-validatebox" data-options="required:true"
									onblur="chnToLetter($(this).val(),'mem_card_chn')"
									value="${memCardManage.mem_card_name}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="mem_card_chn" class="field">拼音码：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="mem_card_chn" name="mem_card_chn"
									class="easyui-combobox" data-options="required:true" style="width:152px;"
									value="${memCardManage.mem_card_chn}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="mem_card_gender" class="field">性别：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="mem_card_gender" name="mem_card_gender" type="text"
									value="${memCardManage.mem_card_gender}"
									class="easyui-combobox" style="width: 152px;" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="mem_card_tel" class="field">电话：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="mem_card_tel" name="mem_card_tel" type="text"
									value="${memCardManage.mem_card_tel}"
									class="easyui-validatebox" data-options="validType:'phone'" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="mem_card_cellPhone" class="field">手机：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="mem_card_cellPhone" name="mem_card_cellPhone"
									type="text" value="${memCardManage.mem_card_cellPhone}"
									class="easyui-validatebox" data-options="validType:'mobile'" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="mem_card_idCard" class="field">身份证：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="mem_card_idCard" name="mem_card_idCard" type="text"
									value="${memCardManage.mem_card_idCard}"
									class="easyui-validatebox" data-options="validType:'en'" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="mem_card_postCode" class="field">邮编：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="mem_card_postCode" name="mem_card_postCode"
									type="text" value="${memCardManage.mem_card_postCode}"
									class="easyui-validatebox" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="mem_card_address" class="field">地址：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="mem_card_address" name="mem_card_address" type="text"
									value="${memCardManage.mem_card_address}" style="width:424px"
									class="easyui-validatebox" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="mem_card_remark" class="field">备注：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="mem_card_remark" name="mem_card_remark" type="text"
									value="${memCardManage.mem_card_remark}" style="width:424px"
									class="easyui-validatebox" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="mem_card_history_choose" class="field">病史备选 ：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="mem_card_history_choose" type="text"
									class="easyui-combobox" style="width: 152px;">
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="mem_card_history" class="field">病史 ：</label>
							</div>
							<div class="Dialog-form-item">
								<textarea id="mem_card_history" name="mem_card_history" type="text" style="width:420px"
									class="easyui-validatebox textareaW1">${memCardManage.mem_card_history}</textarea>
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="mem_card_allergicHistory_choose" class="field">过敏史备选 ：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="mem_card_allergicHistory_choose" type="text" style="width:152px"
									class="easyui-combobox" style="width: 152px;">
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="mem_card_allergicHistory" class="field">过敏史：</label>
							</div>
							<div class="Dialog-form-item">
								<textarea id="mem_card_allergicHistory" style="width:420px"
									name="mem_card_allergicHistory" type="text"
									class="easyui-validatebox textareaW1">${memCardManage.mem_card_allergicHistory}</textarea>
							</div>
						</div>
					</div>
					<div title="会员提点" id="tab-mem_2">
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="mem_card_acountMoney" class="field">记分金额：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="mem_card_acountMoney" name=mem_card_acountMoney
									class="easyui-validatebox"
									data-options="required:true,validType:['money','nosp']"
									value="${memCardManage.mem_card_acountMoney}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="mem_card_startMoney" class="field">初始金额：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="mem_card_startMoney" name=mem_card_startMoney
									class="easyui-validatebox"
									data-options="required:true,validType:['money','nosp']"
									value="${memCardManage.mem_card_startMoney}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="mem_point_toMoney" class="field">积分抵现：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="mem_point_toMoney" name="mem_point_toMoney"
									class="easyui-validatebox"
									data-options="required:true,validType:['money','nosp']"
									value="${memCardManage.mem_point_toMoney}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="mem_card_discount" class="field">会员卡扣率：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="mem_card_discount" name=mem_card_discount
									class="easyui-validatebox"
									data-options="required:true,validType:['num','nosp']"
									value="${memCardManage.mem_card_discount}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="mem_card_originalPoint" class="field">原始积分：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="mem_card_originalPoint" name=mem_card_originalPoint
									class="easyui-validatebox"
									data-options="required:true,validType:['num','nosp']"
									value="${memCardManage.mem_card_originalPoint}" />
							</div>
						</div>
					</div>
			</div>
		</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submitMem" class="btn-save"
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