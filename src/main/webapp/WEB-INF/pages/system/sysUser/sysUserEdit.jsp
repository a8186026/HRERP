<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageCtrlPermission.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/system/sysUserEdit.js"></script>


<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
			<form:form id="_form" action="${formUrl}" method="${method}">
				<input name="user_id" type="hidden" value="${sysUserVO.user_id}" />
				<input id="group_id" name="group_id" type="hidden" value="${sysUserVO.group_id}"/>
				<input name="SPRING_TOKEN" type="hidden"
					value="${sessionScope.SPRING_TOKEN}" />
				<div id="div_user_name" class="float-l">
					<div class="Dialog-form-title">
						<label for="user_name" class="field">登录名：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="user_name" name="user_name" class="easyui-validatebox"
							data-options="required:true,validType:['zh_en','nosp','length[3,16]']"
							value="${sysUserVO.user_name}" />
					</div>
				</div>
				<div id="div_password" class="float-l" type = "hidden">
					<div class="Dialog-form-title">
						<label for="password" class="field">密码：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="password" name="password" type="password"
							class="easyui-validatebox"
							data-options="validType:'length[5,20]'" />
					</div>
				</div>
				<div id="div_display_name" class="float-l">
					<div class="Dialog-form-title">
						<label for="display_name" class="field">用户姓名：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="display_name" name="display_name"
							class="easyui-validatebox"
							data-options="required:true,validType:['zh_en','nosp','length[3,16]']"
							value="${sysUserVO.display_name}" />
					</div>
				</div>
				<div id="div_sex" class="float-l">
					<div id="div_sex" class="Dialog-form-title">
						<label for="sex" class="field">性别：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="sex" name="sex" type="text" value="${sysUserVO.sex}"
							class="easyui-combobox" style="width: 152px;" />
					</div>
				</div>
				<div id="div_tel" class="float-l">
					<div class="Dialog-form-title">
						<label for="vin" class="field">电话：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="tel" name="tel" type="text" value="${sysUserVO.tel}"
							class="easyui-validatebox" data-options="validType:'mobile'" />
					</div>
				</div>
				<div id="div_email" class="float-l">
					<div class="Dialog-form-title">
						<label for="insurance_company" class="field">邮箱：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="email" name="email" type="text"
							class="easyui-validatebox"
							data-options="validType:['email','maxLength[100]']"
							value="${sysUserVO.email}" />
					</div>
				</div>
				<div id="div_depart_id" class="float-l">
					<div class="Dialog-form-title">
						<label for="insurance_company" class="field">部门：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="depart_id" name="depart_id" type="text"
							class="easyui-combobox" style="width: 152px;"
							value="${sysUserVO.depart_id}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="address" class="field">住址：</label>
					</div>
					<div class="Dialog-form-item">
						<textarea id="address" name="address" type="text"
							class="easyui-validatebox textareaW1" maxlength="100">${sysUserVO.address}</textarea>
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submitUser" class="btn-save"
			onmouseover="this.className='btn-save-over'"
			onmouseout="this.className='btn-save'"
			onmousedown="this.className='btn-save-down'" type="button"
			onclick="submitForm();" /> <input id="" class="btn-cancel"
			onmouseover="this.className='btn-cancel-over'"
			onmouseout="this.className='btn-cancel'"
			onmousedown="this.className='btn-cancel-down'" type="button"
			onclick="util.closeWindow('userEditWindow');" />
	</div>
</div>