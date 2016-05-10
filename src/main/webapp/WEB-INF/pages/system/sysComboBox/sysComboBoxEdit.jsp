<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="/HRERP/resources/js/pinYinToLetter.js"></script>
<script type="text/javascript">
	
	function submitForm() {
		//如果是树形结构下拉框才验证编码
		if($("#cbs_type").val().indexOf("tree")>0){
			if($("#cbs_pchn").val()!=""){
				var result = util.get(global_param.context_name + "/system/combobox/getComboTreeByCode?cbs_pid="
						+$("#cbs_pid").val()+"&cbs_code="+$("#cbs_code").val()+"&cbs_type="+$("#cbs_type").val()+"");
				if(!result){
					util.show("下拉框编码已存在,无法添加");
					return;
				}
			}else{
				//如果为根目录，则无法添加编码(会影响产品编号)
				if($("#cbs_code").val()!=""){
					util.show("此为一级目录，无法添加编码");
					return;
				}
			}
		}
		var data = util.submit('_form');
		if (data) {
			if (data.result == "success") {
				util.show(data.message);
				if($("#cbs_type").val().indexOf("box")>0){
					$('#dg').datagrid('reload');
					$('#search_cbs_pid').combobox('reload');
				}else{
					$('#dg').treegrid('reload');
				}
				
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
			<form:form id="_form" action="${formUrl}" method="${method}">
				<input name="SPRING_TOKEN" type="hidden" value="${sessionScope.SPRING_TOKEN}" />
				<!-- 后台传回的PID -->
				<input id="cbs_pid" name="cbs_pid" type="hidden" value="${sysCombobox.cbs_pid}" />
				<input id="cbs_id" name="cbs_id" type="hidden" value="${sysCombobox.cbs_id}" />
				<input id="cbs_type" name="cbs_type" type="hidden" value="${sysCombobox.cbs_type}">
				<!-- 后台传回的父级类别的Name -->
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="chs_pid" class="field">所属类别：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="cbs_pchn" readonly="readonly" class="easyui-validatebox" value="${cbs_pchn}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="cbs_code" class="field">编码：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="cbs_code" name="cbs_code"
							class="easyui-validatebox"
							value="${sysCombobox.cbs_code}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="cbs_chn" class="field">汉字：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="cbs_chn" name="cbs_chn" type="text"class="easyui-validatebox" value="${sysCombobox.cbs_chn}"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="cbs_dec" class="field">说明：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="cbs_dec" name="cbs_dec" type="text"class="easyui-validatebox" value="${sysCombobox.cbs_dec}"/>
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="" class="btn-save"
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