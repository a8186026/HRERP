<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">

//会员卡类别下拉框
$('#sub_mem_card_type').combobox({
	url : global_param.context_name + "/system/combobox/lists?pid=51",
	method :"get",
	valueField : "cbs_id",
	textField : "cbs_chn",
	editable:false,
});

function submitForm() {
	
	//如果选择的是=>，验证两个文本框是否填的一致
	if($('#genarateType option:selected').val()==1){
		if($("#mem_card_start").val().length!=$("#mem_card_endOrNumber").val().length){
			util.show("两个号段长度不一致！");
			return;
		}
		if(Number($("#mem_card_start").val())>Number($("#mem_card_endOrNumber").val())){
			util.show("起始号段应该小于结束号段");
			return;
		}
	}

	var data = util.submit('sub_form');
	if (data) {
		if (data.result == "success") {
			util.show(data.message);
			$('#dg').datagrid('reload');
			util.closeWindow('editWindow1');
		} else {
			util.error(data.message);
		}
	}

}
</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
			<form:form id="sub_form" action="${formUrl}" method="${method}">
				<input name="SPRING_TOKEN" type="hidden" value="${sessionScope.SPRING_TOKEN}" />
				
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="sub_mem_card_type" class="field">会员卡类别：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="sub_mem_card_type" name="mem_card_type"
							data-options="required:true" style="width:152px;" />
					</div>
				</div>
				
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="sub_mem_card_name" class="field">初始姓名：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="sub_mem_card_name" name="mem_card_name"
							class="easyui-validatebox" style="width:152px;" />
					</div>
				</div>
				
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="mem_card_startPoint" class="field">初始积分：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="mem_card_startPoint" name="mem_card_startPoint"
							class="easyui-validatebox" style="width:152px;" />
					</div>
				</div>
				
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="mem_card_identify" class="field">卡号标识：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="mem_card_identify" name="mem_card_identify"
							class="easyui-validatebox" style="width:152px;" data-options="validType:'en'" />
					</div>
				</div>
				
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="mem_card_start" class="field">会员卡号段：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="mem_card_start" name="mem_card_start"
							class="easyui-validatebox" data-options="required:true,validType:'num'" style="width:152px;" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<select id="genarateType" name="type" style="width:50px;">
							<option value="1">=></option>
							<option value="2">==</option>
						</select>
					</div>
					<div class="Dialog-form-item">
						<input id="mem_card_endOrNumber" name="mem_card_endOrNumber"
							class="easyui-validatebox" data-options="required:true,validType:'num'" style="width:152px;" />
					</div>
				</div>
				<div class="float-l">
					<label>卡号标识：例如hy0001,h00y001,将字母放在数字前面</label><br/>
					<label>当号段方式为=>,后面文本框输入结束卡号<br/>当号段方式为==,后面文本框输入需要开卡的数量</label>
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
			onclick="util.closeWindow('editWindow1');" />
	</div>
</div>