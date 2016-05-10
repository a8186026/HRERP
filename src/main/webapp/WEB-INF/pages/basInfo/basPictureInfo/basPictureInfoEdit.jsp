<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript">
	var ctrlsID = ["picture_name","picture_position","picture_remark"];
	 
	//确认BUTTON
	var sumbitButtonID = "submitPicture";
	
	$(function() {
		var keyPress = new pageKeyPress();
		keyPress.bindKeyPressToCtrl(ctrlsID, sumbitButtonID);

	});
	
	function submitPictureForm() {
		if(document.getElementById("file_upload_picture_position").value!=""){
			jQuery.ajaxFileUpload({
				url : global_param.context_name+'/FileManage/FileUpload', //处理上传文件的服务端
				secureuri : false, //与页面处理代码中file相对应的ID值
				fileElementId : "file_upload_picture_position",//'file_upload_sup_sealmoldphoto',
				dataType : 'text', //返回数据类型:text，xml，json，html,scritp,jsonp五种
				autoSubmit : true,
				success : function(result) {
					$("#picture_position").val(result);//sup_sealmoldphoto
					var data = util.submit('_pictureForm');
					if (data) {
						if (data.result == "success") {
							util.show(data.message);
							$('#pictureDg').datagrid('reload');
							util.closeWindow('editPictureWindow');
						} else {
							util.error(data.message);
						}
					}
				},
				error : function() {
					util.show("图片上传出错了！");
				}
			});
		}else{
			var data = util.submit('_pictureForm');
			if (data) {
				if (data.result == "success") {
					util.show(data.message);
					$('#pictureDg').datagrid('reload');
					util.closeWindow('editPictureWindow');
				} else {
					util.error(data.message);
				}
			}
		}
		
		
		
	}
</script>

<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
			<form:form id="_pictureForm" action="${formUrl}" method="${method}" onsubmit="return false;">
				<input name="picture_id" type="hidden" value="${basPictureInfo.picture_id}" />
				<input name="picture_type" type="hidden" value="${basPictureInfo.picture_type}" />
				<input name="picture_type_id" type="hidden" value="${basPictureInfo.picture_type_id}" /> 
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="picture_name" class="field">图片名称：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="picture_name" name="picture_name" class="easyui-validatebox"
							data-options="required:true"  value="${basPictureInfo.picture_name}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="pic_file_upload" class="field">图片地址：</label>
					</div>
					<div class="Dialog-form-item"> 
						<input id="picture_position" name="picture_position" style="float:left;width:250px" class="easyui-validatebox"
							value="${basPictureInfo.picture_position}" />
						<input id="file_upload_picture_position" name="picture_position" style="float:left;width:150px" ContentEditable="false" accept="image/*" type="file" /> 
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="picture_remark" class="field">图片备注：</label>
					</div>
					<div class="Dialog-form-item"> 
						<input id="picture_remark" name="picture_remark" class="easyui-validatebox" 
							value="${basPictureInfo.picture_remark}" />
					</div>
				</div>	
			</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submitPicture" class="btn-save"
			onmouseover="this.className='btn-save-over'"
			onmouseout="this.className='btn-save'"
			onmousedown="this.className='btn-save-down'" type="button"
			onclick="submitPictureForm();" /> <input id="" class="btn-cancel"
			onmouseover="this.className='btn-cancel-over'"
			onmouseout="this.className='btn-cancel'"
			onmousedown="this.className='btn-cancel-down'" type="button"
			onclick="util.closeWindow('editPictureWindow');" />
	</div>
</div>