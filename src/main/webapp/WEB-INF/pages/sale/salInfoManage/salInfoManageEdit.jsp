<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript"
	src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript"
	src="/HRERP/resources/js/supply/publicScopeChoose.js"></script>
<script type="text/javascript"
	src="/HRERP/resources/js/JsUtil/chnToLetter.js"></script>
<script type="text/javascript"
	src="/HRERP/resources/js/sale/salInfoManageEdit.js"></script>

<script>
	var flag = 0;//图片提交标识
	var ctrlsID = [ "sal_fillingdate", "sal_type", "sal_category2",
			"sal_category3", "sal_property", "sal_areacode", "sal_department",
			"sal_area", "sal_areasort", "sal_shortname", "sal_customername",
			"sal_chnpy", "sal_address", "sal_zipcode", "sal_procedure",
			"sal_contractno", "sal_contactperson", "sal_qq", "sal_telephone",
			"sal_receiptperson", "sal_deliverperson", "sal_idnumber",
			"sal_invailddate", "sal_salcueday", "sal_salentrustarea",
			"sal_fax", "sal_email", "sal_msn", "sal_phone", "sal_description",
			"sal_cancel", "sal_other", "sal_other1", "sal_other2",
			"sal_other3",

			"sal_accountname", "sal_accountbank", "sal_accountnumber",
			"sal_registeraddr", "sal_remittanceway", "sal_paymentway",
			"sal_paymentdate", "sal_strikeprice", "sal_creditline",
			"sal_margin", "sal_tax", "sal_taxperson", "sal_financialnote","sal_istaxpayer",

			"sal_permitname", "sal_permitno", "sal_permitchief",
			"sal_permitscope", "sal_permitcueday", "sal_permitvailddate",
			"sal_permitissuingauthority", "sal_permitissuingauthority",
			"sal_legalperson", "sal_registeredmoney", "sal_economyproperty",
			"sal_bnslicensescope", "sal_bnslicensevailddate",
			"sal_bnslicenseway", "sal_bnslicenseissuingauthority",
			"sal_bnslicenseannualdeadline", "sal_bnslicensecueday",
			"sal_bnslicenseannualnote", "sal_qltycatename", "sal_qltycateno",
			"sal_qltycatescope", "sal_qltycatevailddate", "sal_qltycatecueday",
			"sal_qltycateissuingauthority",

			"sal_salesman", "sal_clerkcard", "sal_clerktel", "sal_proxy",
			"sal_clerkcueday", "sal_clerkretrustarea", "sal_relatedseal",
			"sal_fileno", "sal_filelocation", "sal_localdrug", "sal_finddrugid", "sal_drugid", "sal_drugcode",
			"sal_operationlock", "sal_annualoutput", "sal_techinician",
			"sal_majorhonor", "sal_intendedspecies", "sal_majorproduct",
			"sal_qualitystatus", "sal_qualityinstitution",
			"sal_qualityauthentication", "sal_qualitymanage", "sal_qa",
			"sal_qasex", "sal_qaedu", "sal_qaposition", "sal_qatitle",
			"sal_qaworkyear", "sal_qualitynote", "sal_qacprint",
			"sal_checktime", "sal_checkperson", "sal_clerkedu",
			"sal_goodsticket", "sal_sealmold", "sal_sealmoldphoto", 
			"sal_sealmoldphoto"];
	//确认BUTTON
	var sumbitButtonID = "submitSale";
	var tabID = "tab-sal";
	var tabChangID = [ "sal_other3", "sal_istaxpayer",
			"sal_qltycateissuingauthority", "sal_sealmold", "sal_sealmoldphoto" ];

	//实现注册该时间控件，并给赋初值
	
 	$('#sal_fillingdate').datebox({
		required:true,	
	}); 
	if($('#sal_fillingdate').val()!="")
		$('#sal_fillingdate').datebox("setValue",util.toDate($('#sal_fillingdate').val()));
	
	
 	$('#sal_invailddate').datebox({
		required:true,	
	});
	if($('#sal_invailddate').val()!="")
		$('#sal_invailddate').datebox("setValue",util.toDate($('#sal_invailddate').val()));

 	$('#sal_permitvailddate').datebox({
		required:true,	
	}); 
	if($('#sal_permitvailddate').val()!="")
		$('#sal_permitvailddate').datebox("setValue",util.toDate($('#sal_permitvailddate').val()));
	
 	$('#sal_bnslicensevailddate').datebox({
		required:true,	
	}); 
	if($('#sal_bnslicensevailddate').val()!="")
		$('#sal_bnslicensevailddate').datebox("setValue",util.toDate($('#sal_bnslicensevailddate').val()));
 	
 	$('#sal_bnslicenseannualdeadline').datebox({
		required:true,	
	}); 
	if($('#sal_bnslicenseannualdeadline').val()!="")
		$('#sal_bnslicenseannualdeadline').datebox("setValue",util.toDate($('#sal_bnslicenseannualdeadline').val()));
 	
 	$('#sal_qltycatevailddate').datebox({
		required:true,	
	}); 
	if($('#sal_qltycatevailddate').val()!="")
		$('#sal_qltycatevailddate').datebox("setValue",util.toDate($('#sal_qltycatevailddate').val()));
 	
 	$('#sal_proxy').datebox({
		required:true,	
	}); 
	if($('#sal_proxy').val()!="")
		$('#sal_proxy').datebox("setValue",util.toDate($('#sal_proxy').val()));
 	
 	$('#sal_checktime').datebox({
		required:true,	
	});  	
	if($('#sal_checktime').val()!="")
		$('#sal_checktime').datebox("setValue",util.toDate($('#sal_checktime').val()));
	
	
	
	$('#sal_chnpy').combobox({
		url : "",
		method : "",
		valueField : "id",
		textField : "text",
		editable : false,
	});
	$('#sal_chnpy').combobox("setValue", $('#sal_chnpy').attr("value"));

	$('#sal_type').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=657",
		method : "get",
		valueField : "cbs_id",
		textField : "cbs_chn",
		editable : false,
	});

	$('#sal_category2').combobox({
		//url : global_param.context_name + "/system/combobox/lists?pid=657",
		method : "get",
		valueField : "cbs_id",
		textField : "cbs_chn",
		editable : false,
	});
	$('#sal_category3').combobox({
		//url : global_param.context_name + "/system/combobox/lists?pid=657",
		method : "get",
		valueField : "cbs_id",
		textField : "cbs_chn",
		editable : false,
	});
	$('#sal_property').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=666",
		method : "get",
		valueField : "cbs_id",
		textField : "cbs_chn",
		editable : false,
	});
	$('#sal_paymentway').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=680",
		method : "get",
		valueField : "cbs_id",
		textField : "cbs_chn",
		editable : false,
	});
	$('#sal_permitname').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=685",
		method : "get",
		valueField : "cbs_id",
		textField : "cbs_chn",
		editable : false,
	});
	$('#sal_economyproperty').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=693",
		method : "get",
		valueField : "cbs_id",
		textField : "cbs_chn",
		editable : false,
	});
	$('#sal_bnslicenseway').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=700",
		method : "get",
		valueField : "cbs_id",
		textField : "cbs_chn",
		editable : false,
	});
	$('#sal_qltycatename').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=705",
		method : "get",
		valueField : "cbs_id",
		textField : "cbs_chn",
		editable : false,
	});
	//地区药监
	$("#sal_localdrug").combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=732",
		method :"get",
		valueField : "cbs_id",
		textField : "cbs_chn",
		editable:false,
	});

	//下拉菜单暂时没有值的
	$('#sal_department').combobox({
		//url : global_param.context_name + "/system/combobox/lists?pid=705",
		method : "get",
		valueField : "cbs_id",
		textField : "cbs_chn",
		editable : false,
	});
	$('#sal_remittanceway').combobox({
		//url : global_param.context_name + "/system/combobox/lists?pid=705",
		method : "get",
		valueField : "cbs_id",
		textField : "cbs_chn",
		editable : false,
	});
	$('#sal_strikeprice').combobox({
		//url : global_param.context_name + "/system/combobox/lists?pid=705",
		method : "get",
		valueField : "cbs_id",
		textField : "cbs_chn",
		editable : false,
	});
	$('#sal_printType').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=1015",
		method : "get",
		valueField : "cbs_code",
		textField : "cbs_chn",
		editable : false,
	});
	/*
	 * 地区药监开始(此处跳转到供方jsp页面，然后回调函数返回)
	 */
	function selectFromOther(){
		if($("#sal_finddrugid").val()=="%"){
			selectLocalDrug('setLocalDrug','');
		}else{
			selectLocalDrug('setLocalDrug',$("#sal_finddrugid").val());
		}
	}

	function selectLocalDrug(callback,value) {
		// 创建窗口
		function createLocalDrugChooseWin() {
			if ($("#localDrugChooseWin").length == 0) {
				jQuery('<div id="localDrugChooseWin" callback="' + callback + '"></div>').appendTo(document.body);
			}
			$("#localDrugChooseWin").window({
				title : '药监局选择',
				width : 1120,
				height : 590,
				closed : true,
				minimizable : false,
				maximizable : false,
				collapsible : false,
				resizable : false,
				content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/supply/supInfoManage/localDrugChoose?callback=' + callback+'&value='+value+ '"></iframe>'
			});
		}
		createLocalDrugChooseWin();
		$('#localDrugChooseWin').window('open');
	}

	function setLocalDrug(node){
		if(node!=null){
			$("#sal_drugid").val(node.ca_id);//设置药监ID	
			$("#sal_finddrugid").val(node.ca_xkzbh);//设置许可证号	
		}else{
			$("#sal_drugid").val("");		
			$("#sal_finddrugid").val("");
		}
	}
	//地区药监end

	$(function() {
		if ($("#sal_sealmoldphoto").val() != "") {
			var index = $("#sal_sealmoldphoto").val().indexOf("HRERP");
			var subString = $("#sal_sealmoldphoto").val().substring(index - 1);
			var url = subString.replace(/\\/g, "/");//将斜杠\全部替换为/
			document.getElementById("preview1").style.display = "block";//将隐藏图片显示
			$("#preview1").attr("src", url);
		}
	});

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

		util.select({
			id : 'sal_qasex',
			param : {
				typeCode : 'SEX'
			}
		});

	});

	//数据封装，并且提交数据
	function submitForm() {

		var fileIds = [ "file_upload_sal_sealmoldphoto" ];
		var inputIds = [ "sal_sealmoldphoto" ];
		if (!$('#_form').form('validate')) {
			util.show("还有必填项未填写！");
			return;
		}

		if (document.getElementById("file_upload_sal_sealmoldphoto").value == "") {
			if (document.getElementById("sal_sealmoldphoto").value == "") {
				util.show("请上传图片");
				return;
			}
		}
		submitAll(fileIds, inputIds);
	}
	//提交表单和图片
	function submitAll(fileIds, inputIds) {
		var f = new Array();
		var is = new Array();
		//将需要提交的图片提取出来
		for (var i = 0; i < fileIds.length; i++) {
			if (document.getElementById(fileIds[i]).value != "") {
				f.push(fileIds[i]);
				is.push(inputIds[i]);
			}
		}
		//若没有图片提交，则直接提交数据
		if (f.length == 0) {
			submitData();
			return;
		}

		//如果有图片提交，则分别提交图片，true当提交最后一张图片的时候顺便提交数据
		for (var j = 0; j < f.length; j++) {
			uploadImg(f[j], is[j], f.length);
		}
	}
	
	//上传图片
	function uploadImg(fileId, inputId, number) {
		//alert("提交图片");
		jQuery.ajaxFileUpload({
			url : global_param.context_name + '/FileManage/FileUpload', //处理上传文件的服务端
			secureuri : false, //与页面处理代码中file相对应的ID值
			fileElementId : fileId,//'file_upload_sal_sealmoldphoto',
			dataType : 'text', //返回数据类型:text，xml，json，html,scritp,jsonp五种
			autoSubmit : true,
			success : function(result) {
				flag++;
				$("#" + inputId).val(result);//sal_sealmoldphoto
				if (flag == number) {
					//如果最后一张图片提交成功则提交表单书籍
					submitData();
				}
			},
			error : function() {
				util.show("图片上传出错了！");
			}
		});
	}
	//提交表单数据
	function submitData() {
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
	}

	//检查图片的格式是否正确,同时实现预览  
	function setImagePreview(obj, localImagId, imgObjPreview) {
		var array = new Array('gif', 'jpeg', 'png', 'jpg', 'bmp'); //可以上传的文件类型  
		if (obj.value == '') {
			$.messager.alert("让选择要上传的图片!");
			return false;
		} else {
			var fileContentType = obj.value.match(/^(.*)(\.)(.{1,8})$/)[3]; //这个文件类型正则很有用   
			////布尔型变量  
			var isExists = false;
			//循环判断图片的格式是否正确  
			for ( var i in array) {
				if (fileContentType.toLowerCase() == array[i].toLowerCase()) {
					//图片格式正确之后，根据浏览器的不同设置图片的大小  
					if (obj.files && obj.files[0]) {
						//火狐下，直接设img属性   
						imgObjPreview.style.display = 'block';
						imgObjPreview.style.width = '20px';
						imgObjPreview.style.height = '20px';
						//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式   
						imgObjPreview.src = window.URL
								.createObjectURL(obj.files[0]);
					} else {
						//IE下，使用滤镜   
						obj.select();
						var imgSrc = document.selection.createRange().text;
						//必须设置初始大小   
						localImagId.style.width = "120px";
						localImagId.style.height = "20px";
						//图片异常的捕捉，防止用户修改后缀来伪造图片   
						try {
							localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
							localImagId.filters
									.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
						} catch (e) {
							$.messager.alert("您上传的图片格式不正确，请重新选择!");
							return false;
						}
						imgObjPreview.style.display = 'none';
						document.selection.empty();
					}
					isExists = true;
					return true;
				}
			}
			if (isExists == false) {
				$.messager.alert("上传图片类型不正确!");
				return false;
			}
			return false;
		}
	}

	//图片放大
	function previewimg(img) {
		$.messager.alert("图片", '<img style="width:500px;" src="'
				+ img.getAttribute("src") + '">');
	}



	 var keyPress = new pageKeyPress();
	 keyPress.setTabChangeID(tabID, tabChangID);
	 keyPress.bindKeyPressToCtrl(ctrlsID, sumbitButtonID); 
	  
	
</script>

<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">

			<form:form id="_form" action="${formUrl}" method="${method}"
				onsubmit="return false;">
				<input id="sal_id" name="sal_id" type="hidden"	value="${salInfoManage.sal_id}" />

				<div id="tab-sal" class="easyui-tabs" style="height: 600px">
					<div title="基本信息" id="basicinfo">
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_code" class="field" style="color: red">销方编码：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_code" name="sal_code" class="easyui-validatebox"
									readonly="readonly" onclick="setDate()"
									data-options=" validType:['num','length[1,20]']"
									value="${salInfoManage.sal_code}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_fillingdate" class="field">建档日期：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_fillingdate" name="sal_fillingdate"
									style="width: 152px" 
									value="${salInfoManage.sal_fillingdate}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_type" class="field">类别：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_type" name="sal_type" class="easyui-combobox"
									style="width: 152px" data-options=" validType:'length[1,20]'"
									value="${salInfoManage.sal_type}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_category2" class="field">类别2：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_category2" name="sal_category2"
									class="easyui-combobox" style="width: 152px"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_category2}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_category3" class="field">类别3：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_category3" name="sal_category3"
									class="easyui-combobox" style="width: 152px"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_category3}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_property" class="field">性质：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_property" name="sal_property"
									class="easyui-combobox" style="width: 152px"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_property}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_areacode" class="field">区号：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_areacode" name="sal_areacode"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_areacode}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_department" class="field">所属部门：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_department" name="sal_department"
									class="easyui-combobox" style="width: 152px"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_department}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_area" class="field">区域：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_area" name="sal_area" class="easyui-combobox"
									style="width: 152px" data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_area}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_areasort" class="field">区域排序：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_areasort" name="sal_areasort"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_areasort}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_shortname" class="field">简称：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_shortname" name="sal_shortname"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_shortname}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_customername" class="field">客户全称：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_customername" name="sal_customername"
									class="easyui-validatebox"
									onblur="chnToLetter($(this).val(),'sal_chnpy')"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_customername}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for=sal_chnpy class="field">拼音码：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_chnpy" name="sal_chnpy" class="easyui-combobox"
									style="width: 152px" value="${salInfoManage.sal_chnpy}" />
							</div>

						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_address" class="field">地址：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_address" name="sal_address"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_address}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_zipcode" class="field">邮编：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_zipcode" name="sal_zipcode"
									class="easyui-validatebox"
									data-options="validType:['num','length[1,20]']"
									value="${salInfoManage.sal_zipcode}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_procedure" class="field">手续：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_procedure" name="sal_procedure"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_procedure}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_contractno" class="field">合同号：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_contractno" name="sal_contractno"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_contractno}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_contactperson" class="field">联系人：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_contactperson" name="sal_contactperson"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_contactperson}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_qq" class="field">QQ：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_qq" name="sal_qq" class="easyui-validatebox"
									data-options="validType:['num','length[1,20]']"
									value="${salInfoManage.sal_qq}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_telephone" class="field">手机：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_telephone" name="sal_telephone"
									class="easyui-validatebox"
									data-options="validType:['mobile','length[1,20]']"
									value="${salInfoManage.sal_telephone}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_receiptperson" class="field">收货人：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_receiptperson" name="sal_receiptperson"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_receiptperson}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_deliverperson" class="field">提货人：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_deliverperson" name="sal_deliverperson"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_deliverperson}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_idnumber" class="field">身份证：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_idnumber" name="sal_idnumber"
									class="easyui-validatebox" data-options="validType:'id'"
									value="${salInfoManage.sal_idnumber}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_invailddate" class="field">失效期：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_invailddate" name="sal_invailddate"
									class=""
									data-options="required:true,validType:'length[1,20]'"
									style="width: 150px" value="${salInfoManage.sal_invailddate}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_salcueday" class="field">提示天数：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_salcueday" name="sal_salcueday"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_salcueday}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_salentrustarea" class="field">委托区域：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_salentrustarea" name="sal_salentrustarea"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_salentrustarea}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_fax" class="field">传真：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_fax" name="sal_fax" class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_fax}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_email" class="field">电子信箱：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_email" name="sal_email"
									class="easyui-validatebox"
									data-options="validType:['email','length[1,20]']"
									value="${salInfoManage.sal_email}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for=sal_msn class="field">MSN：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_msn" name="sal_msn" class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_msn}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for=sal_phone class="field">电话：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_phone" name="sal_phone"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_phone}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for=sal_description class="field">简介：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_description" name="sal_description"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_description}" />
							</div>
						</div>
						<div
							style="width: 570px; height: 100px; margin-left: 5px; border: 0px solid #CCCCCC">
							<div class="float-l">
								<div class="SelectAll">
									<input name="sal_cancel" type="hidden"
										value="${salInfoManage.sal_cancel}" /> <input id="sal_cancel"
										type="checkbox" style="width: 20px" /> <label
										for="sal_cancel">注销</label>
								</div>
							</div>
							<div class="float-l">
								<div class="SelectAll">
									<input name="sal_other" type="hidden"
										value="${salInfoManage.sal_other}" /> <input id="sal_other"
										type="checkbox" style="width: 20px" /> <label for="sal_other">其它</label>
								</div>
							</div>
							<div class="float-l">
								<div class="SelectAll">
									<input name="sal_other1" type="hidden"
										value="${salInfoManage.sal_other1}" /> <input id="sal_other1"
										type="checkbox" style="width: 20px" /> <label
										for="sal_other1">其它1</label>
								</div>
							</div>

							<div class="float-l">
								<div class="SelectAll">
									<input name="sal_other2" type="hidden"
										value="${salInfoManage.sal_other2}" /> <input id="sal_other2"
										type="checkbox" style="width: 20px" /> <label
										for="sal_other2">其它2</label>
								</div>
							</div>
							<div class="float-l">
								<div class="SelectAll">
									<input name="sal_other3" type="hidden"
										value="${salInfoManage.sal_other3}" /> <input id="sal_other3"
										type="checkbox" style="width: 20px" /> <label
										for="sal_other3">其它3</label>
								</div>
							</div>
						</div>
					</div>


					<div title="财务信息" id="moneyinfo">
						<div style="border: none; position: relative; width: 570px; margin-top: 5px; margin-bottom: 5px; margin-left: 5px">基本财务信息：</div>
						<div style="width: 570px; height: 220px; margin-left: 5px; border: 1px solid #CCCCCC">
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for=sal_accountname class="field">开户户名：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_accountname" name="sal_accountname"
										class="easyui-validatebox"
										data-options=" validType:'length[1,20]'"
										value="${salInfoManage.sal_accountname}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_accountbank" class="field">开户行：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_accountbank" name="sal_accountbank"
										class="easyui-validatebox"
										data-options=" validType:'length[1,20]'"
										value="${salInfoManage.sal_accountbank}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_accountnumber" class="field">开户帐号：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_accountnumber" name="sal_accountnumber"
										class="easyui-validatebox"
										data-options=" validType:'length[1,20]'"
										value="${salInfoManage.sal_accountnumber}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_registeraddr" class="field">注册地址：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_registeraddr" name="sal_registeraddr"
										class="easyui-validatebox"
										data-options="validType:'length[1,20]'"
										value="${salInfoManage.sal_registeraddr}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_remittanceway" class="field">汇款方式：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_remittanceway" name="sal_remittanceway"
										class="easyui-combobox" style="width: 152px"
										data-options="validType:'length[1,20]'"
										value="${salInfoManage.sal_remittanceway}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_paymentway" class="field">结款方式：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_paymentway" name="sal_paymentway"
										class="easyui-combobox" style="width: 152px"
										data-options="validType:'length[1,20]'"
										value="${salInfoManage.sal_paymentway}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_paymentdate" class="field">结款日期：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_paymentdate" name="sal_paymentdate"
										class="easyui-validatebox" style="width: 152px"
										data-options="required:true,validType:'length[1,20]'"
										value="${salInfoManage.sal_paymentdate}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_strikeprice" class="field">执行价格：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_strikeprice" name="sal_strikeprice"
										class="easyui-combobox" style="width: 152px"
										data-options="validType:'length[1,20]'"
										value="${salInfoManage.sal_strikeprice}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for=sal_creditline class="field">信用额度：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_creditline" name="sal_creditline"
										class="easyui-validatebox"
										data-options="required:true,validType:'length[1,20]'"
										value="${salInfoManage.sal_creditline}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for=sal_margin class="field">保证金：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_margin" name="sal_margin"
										class="easyui-validatebox"
										data-options="required:true, validType:'length[1,20]'"
										value="${salInfoManage.sal_margin}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_tax" class="field">税号：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_tax" name="sal_tax" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"
										value="${salInfoManage.sal_tax}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_taxperson" class="field">办税人员：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_taxperson" name="sal_taxperson"
										class="easyui-validatebox"
										data-options="validType:'length[1,20]'"
										value="${salInfoManage.sal_taxperson}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_financialnote" class="field">财务备注：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_financialnote" name="sal_financialnote"
										class="easyui-validatebox"
										data-options="validType:'length[1,20]'"
										value="${salInfoManage.sal_financialnote}" />
								</div>
							</div>
							<div class="float-l">
				        		<div class="SelectAll">
				        			<input name="sal_istaxpayer" type ="hidden" value="${salInfoManage.sal_istaxpayer}" />
									<input id="sal_istaxpayer" type ="checkbox" style="width:20px" />
									<label for="sal_istaxpayer">一般纳税</label>
								</div>
							</div>
						</div>

						<div style="border: none; position: relative; width: 570px; margin-top: 5px; margin-bottom: 5px; margin-left: 5px">其他开户信息（必须在保存销方信息后可以添加其他开户信息）
						</div>

						<!-- 显示区域 -->
						<div style="width: 1000; height: 318px">
							<table id="AccountDg"></table>
						</div>
						<!-- 编辑窗口 -->
						<div id="editAccountWindow"></div>
						

					</div>

					<div title="证照信息" id="cardinfo">
						<div
							style="border: none; position: relative; top: 0px; margin-top: 10px; margin-bottom: 5px; margin-left: 5px; width: 570px">许可证：</div>
						<div
							style="width: 570px; height: 125px; margin-left: 5px; border: 1px solid #CCCCCC">
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_permitname" class="field">许可证名称：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_permitname" name="sal_permitname"
										class="easyui-combobox" style="width: 152px"
										data-options="validType:'length[1,20]'"
										value="${salInfoManage.sal_permitname}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_permitno" class="field">许可证号：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_permitno" name="sal_permitno"
										class="easyui-validatebox"
										data-options="validType:'length[1,20]'"
										value="${salInfoManage.sal_permitno}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for=sal_permitchief class="field">负责人：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_permitchief" name="sal_permitchief"
										class="easyui-validatebox"
										data-options="validType:'length[1,20]'"
										value="${salInfoManage.sal_permitchief}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_permitscope" class="field">许可范围：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_permitscope" name="sal_permitscope"
										class="easyui-validatebox"
										data-options="validType:'length[1,20]'"
										value="${salInfoManage.sal_permitscope}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_permitcueday" class="field">提示天数：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_permitcueday" name="sal_permitcueday"
										class="easyui-validatebox"
										data-options="validType:'length[1,20]'"
										value="${salInfoManage.sal_permitcueday}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_permitvailddate" class="field">有效期至：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_permitvailddate" name="sal_permitvailddate"
										class="" style="width: 152px"
										data-options="required:true, validType:'length[1,20]'"
										value="${salInfoManage.sal_permitvailddate}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_permitissuingauthority" class="field">发证机关：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_permitissuingauthority"
										name="sal_permitissuingauthority" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"
										value="${salInfoManage.sal_permitissuingauthority}" />
								</div>
							</div>
						</div>
						<div
							style="border: none; position: relative; width: 570px; margin-top: 5px; margin-bottom: 5px; margin-left: 5px">营业执照：</div>
						<div
							style="width: 570px; height: 187px; margin-left: 5px; border: 1px solid #CCCCCC">
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_registrationno" class="field">注册号：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_registrationno" name="sal_registrationno"
										class="easyui-validatebox"
										data-options=" validType:'length[1,20]'"
										value="${salInfoManage.sal_registrationno}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_legalperson" class="field">法人：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_legalperson" name="sal_legalperson"
										class="easyui-validatebox"
										data-options=" validType:'length[1,20]'"
										value="${salInfoManage.sal_legalperson}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_registeredmoney" class="field">注册资金：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_registeredmoney" name="sal_registeredmoney"
										class="easyui-validatebox"
										data-options=" validType:'length[1,20]'"
										value="${salInfoManage.sal_registeredmoney}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_economyproperty" class="field">经济性质：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_economyproperty" name="sal_economyproperty"
										class="easyui-validatebox"
										data-options=" validType:'length[1,20]'" style="width: 150px"
										value="${salInfoManage.sal_economyproperty}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_bnslicensescope" class="field">经营范围：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_bnslicensescope" name="sal_bnslicensescope"
										class="easyui-validatebox"
										data-options=" validType:'length[1,20]'"
										value="${salInfoManage.sal_bnslicensescope}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_bnslicensevailddate" class="field">有效期至：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_bnslicensevailddate"
										name="sal_bnslicensevailddate" class=""
										style="width: 152px"
										data-options="required:true, validType:'length[1,20]'"
										value="${salInfoManage.sal_bnslicensevailddate}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_bnslicenseway" class="field">经营方式：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_bnslicenseway" name="sal_bnslicenseway"
										class="easyui-combobox" style="width: 152px"
										data-options=" validType:'length[1,20]'"
										value="${salInfoManage.sal_bnslicenseway}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_bnslicenseissuingauthority" class="field">发照机关：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_bnslicenseissuingauthority"
										name="sal_bnslicenseissuingauthority"
										class="easyui-validatebox"
										data-options=" validType:'length[1,20]'"
										value="${salInfoManage.sal_bnslicenseissuingauthority}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_bnslicenseannualdeadline" class="field">年检截止：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_bnslicenseannualdeadline"
										name="sal_bnslicenseannualdeadline" class=""
										style="width: 152px"
										data-options="required:true, validType:'length[1,20]'"
										value="${salInfoManage.sal_bnslicenseannualdeadline}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_bnslicensecueday" class="field">提示天数：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_bnslicensecueday" name="sal_bnslicensecueday"
										class="easyui-validatebox"
										data-options=" validType:'length[1,20]'"
										value="${salInfoManage.sal_bnslicensecueday}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_bnslicenseannualnote" class="field">年检备注：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_bnslicenseannualnote"
										name="sal_bnslicenseannualnote" class="easyui-validatebox"
										data-options=" validType:'length[1,20]'"
										value="${salInfoManage.sal_bnslicenseannualnote}" />
								</div>
							</div>
						</div>

						<div
							style="border: none; position: relative; width: 570px; margin-top: 5px; margin-bottom: 5px; margin-left: 5px">质量证书：</div>
						<div
							style="width: 570px; height: 95px; margin-left: 5px; border: 1px solid #CCCCCC">
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_qltycatename" class="field">证书名称：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_qltycatename" name="sal_qltycatename"
										class="easyui-combobox" style="width: 152px"
										data-options=" validType:'length[1,20]'"
										value="${salInfoManage.sal_qltycatename}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_qltycateno" class="field">证书编号：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_qltycateno" name="sal_qltycateno"
										class="easyui-validatebox"
										data-options=" validType:'length[1,20]'"
										value="${salInfoManage.sal_qltycateno}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_qltycatescope" class="field">认证范围：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_qltycatescope" name="sal_qltycatescope"
										class="easyui-validatebox"
										data-options=" validType:'length[1,20]'"
										value="${salInfoManage.sal_qltycatescope}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_qltycatevailddate" class="field">有效期至：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_qltycatevailddate" name="sal_qltycatevailddate"
										class="" style="width: 152px"
										data-options=" required:true,validType:'length[1,20]'"
										value="${salInfoManage.sal_qltycatevailddate}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_qltycatecueday" class="field">提示天数：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_qltycatecueday" name="sal_qltycatecueday"
										class="easyui-validatebox"
										data-options=" validType:'length[1,20]'"
										value="${salInfoManage.sal_qltycatecueday}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sal_qltycateissuingauthority" class="field">发证机关：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_qltycateissuingauthority"
										name="sal_qltycateissuingauthority" class="easyui-validatebox"
										data-options=" validType:'length[1,20]'"
										value="${salInfoManage.sal_qltycateissuingauthority}" />
								</div>
							</div>
						</div>
						<div
							style="border: none; position: relative; width: 570px; margin-top: 5px; margin-bottom: 5px; margin-left: 5px">其他证书（必须保存销方信息后可以添加其他开户信息）</div>

						<!-- 显示区域 -->
						<div style="width: 1000; height: 300px">
							<table id="licenseDg"></table>
						</div>
						<!-- 编辑窗口 -->
						<div id="editLicenseWindow"></div>

					</div>


					<div title="质量其他相关" id="qualityinfo">
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_salesman" class="field">业务员：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_salesman" name="sal_salesman"
									class="easyui-validatebox"
									data-options=" validType:'length[1,20]'"
									value="${salInfoManage.sal_salesman}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_clerkcard" class="field">身份证：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_clerkcard" name="sal_clerkcard"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_clerkcard}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_clerktel" class="field">电话：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_clerktel" name="sal_clerktel"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_clerktel}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_proxy" class="field">委托书失效期：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_proxy" name="sal_proxy" class=""
									style="width: 152px"
									data-options="required:true,validType:'length[1,20]'"
									value="${salInfoManage.sal_proxy}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_clerkcueday" class="field">提示天数：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_clerkcueday" name="sal_clerkcueday"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_clerkcueday}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_clerkretrustarea" class="field">委托区域：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_clerkretrustarea" name="sal_clerkretrustarea"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_clerkretrustarea}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_relatedseal" class="field">相关印章：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_relatedseal" name="sal_relatedseal"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_relatedseal}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_fileno" class="field">档案号：</label>
							</div>
							<div class="Dialog-form-item">
								<input id=sal_fileno name="sal_fileno"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_fileno}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_filelocation" class="field">档案位置：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_filelocation" name="sal_filelocation"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_filelocation}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_localdrug" class="field">地区药监：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_localdrug" name="sal_localdrug" class="easyui-combobox" style = "width:152px"
									data-options="validType:'length[1,20]'"  value="${salInfoManage.sal_localdrug}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_finddrugid" class="field">查询号：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_finddrugid" name="sal_finddrugid" class="easyui-validatebox" style = "width:152px" 
								onblur="selectFromOther()" data-options="validType:'length[1,20]'"  value="${salInfoManage.sal_finddrugid}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_drugid" class="field">地区药监ID：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_drugid" name="sal_drugid"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_drugid}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_drugcode" class="field">国家药监码：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_drugcode" name="sal_drugcode"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_drugcode}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_operationlock" class="field">经营禁售：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_operationlock" name="sal_operationlock"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_operationlock}" />
							</div>
						</div>

						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_annualoutput" class="field">年产值：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_annualoutput" name="sal_annualoutput"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_annualoutput}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for=sal_techinician class="field">技术人员：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_techinician" name="sal_techinician"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_techinician}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_majorhonor" class="field">主要荣誉：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_majorhonor" name="sal_majorhonor"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_majorhonor}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_intendedspecies" class="field">拟供品种：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_intendedspecies" name="sal_intendedspecies"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_intendedspecies}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_majorproduct" class="field">主要产品：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_majorproduct" name="sal_majorproduct"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_majorproduct}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_qualitystatus" class="field">质量状况：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_qualitystatus" name="sal_qualitystatus"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_qualitystatus}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_qualityinstitution" class="field">质量机构名：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_qualityinstitution" name="sal_qualityinstitution"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_qualityinstitution}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_qualityauthentication" class="field">认证情况：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_qualityauthentication"
									name="sal_qualityauthentication" class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_qualityauthentication}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_qualitymanage" class="field">质量管理：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_qualitymanage" name="sal_qualitymanage"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_qualitymanage}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_qa" class="field">质量负责人：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_qa" name="sal_qa" class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_qa}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_qasex" class="field">性别：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_qasex" name="sal_qasex" class="easyui-combobox"
									style="width: 152px" data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_qasex}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_qaedu" class="field">文化程度：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_qaedu" name="sal_qaedu"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_qaedu}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_qaposition" class="field">职务：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_qaposition" name="sal_qaposition"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_qaposition}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_qatitle" class="field">技术职称：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_qatitle" name="sal_qatitle"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_qatitle}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_qaworkyear" class="field">工作年限：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_qaworkyear" name="sal_qaworkyear"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_qaworkyear}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_qualitynote" class="field">质量备注：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_qualitynote" name="sal_qualitynote"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_qualitynote}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_qacprint" class="field">质检单：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_qacprint" name="sal_qacprint"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_qacprint}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="" class="field">打印格式：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_printType" name="sal_printType" class="easyui-combobox"
									style="width: 152px" data-options="validType:'length[1,20]'"
									value="" />
									
							</div>
							<input id="perview1" type="button"  value="预览打印格式" onclick="perviewPrint();" style="height: 22px;width: 90px;"/> 
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_checktime" class="field">审核日期：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_checktime" name="sal_checktime"
									class="" style="width: 152px"
									data-options="required:true,validType:'length[1,20]'"
									value="${salInfoManage.sal_checktime}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sal_checkperson" class="field">审核人：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sal_checkperson" name="sal_checkperson"
									class="easyui-validatebox"
									data-options="validType:'length[1,20]'"
									value="${salInfoManage.sal_checkperson}" />
							</div>
						</div>
						<div class="float-l">
							<div class="SelectAll">
								<input name="sal_clerkedu" type="hidden"
									value="${salInfoManage.sal_clerkedu}" /> <input
									id="sal_clerkedu" type="checkbox" style="width: 20px" /> <label
									for="sal_clerkedu">学历证</label>
							</div>
						</div>
						<div class="float-l">
							<div class="SelectAll">
								<input name="sal_goodsticket" type="hidden"
									value="${salInfoManage.sal_goodsticket}" /> <input
									id="sal_goodsticket" type="checkbox" style="width: 20px" /> <label
									for="sal_goodsticket">随货票样</label>
							</div>
						</div>
						<div class="float-l">
							<div class="SelectAll">
								<input name="sal_sealmold" type="hidden"
									value="${salInfoManage.sal_sealmold}" /> <input
									id="sal_sealmold" type="checkbox" style="width: 20px" /> <label
									for="sal_sealmold">印章印模</label>
							</div>
						</div>
					</div>

					<div title="相关图片" id="photoinfo">
						<div
							style="border: none; position: relative; width: 570px; margin-top: 5px; margin-bottom: 5px; margin-left: 5px">图片信息：</div>
						<div
							style="width: 570px; height: 95px; margin-left: 5px; border: 1px solid #CCCCCC">
							<div class="float-l" id="localImag1">
								<div class="Dialog-form-title">
									<label for=sal_sealmoldphoto class="field">印章印模：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sal_sealmoldphoto" name="sal_sealmoldphoto"
										style="float: left; width: 250px" class="easyui-validatebox"
										readOnly="readonly" value="${salInfoManage.sal_sealmoldphoto}" /> 
									<input id="file_upload_sal_sealmoldphoto"
										style="width: 150px; float: left" name="sal_sealmoldphoto"
										ContentEditable="false" accept="image/*" runat="server"
										onchange="javascript:setImagePreview(this,localImag1,preview1);"
										type="file" /> 
									<img id="preview1" alt="预览图片"
										onclick="previewimg(this);"
										style="margin-left: 8px; float: right; width: 20px; height: 20px; display: none;" />
								</div>
							</div>
						</div>

						<div
							style="border: none; position: relative; width: 570px; margin-top: 5px; margin-bottom: 5px; margin-left: 5px">其他图片（必须保存供方信息后可以添加其他图片信息）</div>

						<!-- 显示区域 -->
						<div style="height: 413px">
							<table id="pictureDg"></table>
						</div>
						<!-- 编辑窗口 -->
						<div id="editPictureWindow"></div>
					</div>
				
			</div>
			</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submitSale" class="btn-save"
			onmouseover="this.className='btn-save-over'"
			onmouseout="this.className='btn-save'"
			onmousedown="this.className='btn-save-down'" type="button"
			onclick="submitForm();" /> <input id="" class="btn-cancel"
			onmouseover="this.className='btn-cancel-over'"
			onmouseout="this.className='btn-cancel'"
			onmousedown="this.className='btn-cancel-down'" type="button"
			onclick="util.closeWindow('editWindow');" />
	</div>
	<!-- 编辑窗口 -->
	<div id="editSonWindow"></div>
</div>