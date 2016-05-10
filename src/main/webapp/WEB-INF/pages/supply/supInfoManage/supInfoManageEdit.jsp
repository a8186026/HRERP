<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="/HRERP/resources/js/supply/supInfoManageEdit.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/supply/publicScopeChoose.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/chnToLetter.js"></script>

<script type="text/javascript">

	var flag = 0;//图片提交标识
	var ctrlsID = ["sup_code","sup_category1","sup_category2","sup_category3","sup_areano","sup_shortname",
	               "sup_name","sup_address","sup_zipcode","sup_procedure","sup_ctractno",
	               "sup_ctactperson","sup_qqcode","sup_mobile","sup_fax","sup_email","sup_msncode",
	               "sup_buyname","sup_tel","sup_brief","sup_property","sup_other1","sup_other2","sup_other3","sup_other4",
	               
	               "sup_accname",
	               "sup_bankname","sup_accno","sup_registaddr","sup_rmtway","sup_payway",
	               "sup_bond","sup_tax","sup_fnlnote","sup_paydays","sup_istaxpayer",
	               
	               "sup_pmtname","sup_pmtno","sup_pmtchief","sup_pmtscope","sup_pmtcueday",
	               "sup_pmtissauthority","sup_bnlsregistno","sup_bnlslegalperson","sup_bnlsregistmoney","sup_bnlsproperty","sup_bnlsscope",
	               "sup_bnlsway","sup_bnlsissauthority","sup_bnlscueday","sup_bnlsanndeadnote",
	               "sup_qltycatename","sup_qltycateno","sup_qltycatescope","sup_qltycatecueday","sup_qltycateissauthority",
	               
	               
	               "sup_clkname","sup_clkidcard","sup_clktel","sup_letcueday",
	               "sup_relatedseal","sup_fileno","sup_filelocation","sup_localdrug","sup_finddrugid","sup_drugid","sup_drugcode",
	               "sup_annoutput","sup_technician","sup_majorhonor","sup_intendedspecie","sup_majorproduct",
	               "sup_qltystatus","sup_qltyinstitution","sup_qltyauthen","sup_qltymanage","sup_qltychief","sup_qltychiefsex",
	               "sup_qltychiefedu","sup_qltychiefposition","sup_qltychieftitle","sup_qltychiefworkyear","sup_qltynote","sup_printway",
	               "sup_checkname",
	
	               "sup_goodticketphoto","sup_sealmoldphoto"];
	
	$('#sup_chnpy').combobox({
		url : "",
		editable:false,
	});
	//确认BUTTON
	var sumbitButtonID = "submitSupply";
	var tabID = "tab-sup";
  	var tabChangID = ["sup_other4","sup_istaxpayer","sup_qltycateissauthority","sup_checkname","sup_sealmoldphoto"]; 

 	$(function() {
 		
 		util.select({
 			id : 'sup_qltychiefsex',
 			param : {
 				typeCode : 'SEX'
 			}
 		});
 	 	util.select({
 			id : 'sup_istaxpayer',
 			param : {
 				typeCode : 'YN'
 			}
 		});
 		
 	   //实现时间控件注册，并赋初值 建档日期
	 	$('#sup_fillingdate').datebox({
			required:true,	
		}); 
		if($('#sup_fillingdate').val()!="")
			$('#sup_fillingdate').datebox("setValue",util.toDate($('#sup_fillingdate').val()));
		//实现时间控件注册，并赋初值 结款日期
	 	$('#sup_paydate').datebox({
			required:true,	
		}); 
		if($('#sup_paydate').val()!="")
			$('#sup_paydate').datebox("setValue",util.toDate($('#sup_paydate').val()));
		
		//实现时间控件注册，并赋初值 许可证有效期至
	 	$('#sup_pmtvaliddate').datebox({
			required:true,	
		}); 
		if($('#sup_pmtvaliddate').val()!="")
			$('#sup_pmtvaliddate').datebox("setValue",util.toDate($('#sup_pmtvaliddate').val()));
		
		//实现时间控件注册，并赋初值 营业执照有效期至
	 	$('#sup_bnlsvaliddate').datebox({
			required:true,	
		}); 
		if($('#sup_bnlsvaliddate').val()!="")
			$('#sup_bnlsvaliddate').datebox("setValue",util.toDate($('#sup_bnlsvaliddate').val()));
		
		//实现时间控件注册，并赋初值  营业执照年检截止
	 	$('#sup_bnlsanndeaddate').datebox({
			required:true,	
		}); 
		if($('#sup_bnlsanndeaddate').val()!="")
			$('#sup_bnlsanndeaddate').datebox("setValue",util.toDate($('#sup_bnlsanndeaddate').val()));
		
		//实现时间控件注册，并赋初值 质量证书有效期至
	 	$('#sup_qltycatevaliddate').datebox({
			required:true,	
		}); 
		if($('#sup_qltycatevaliddate').val()!="")
			$('#sup_qltycatevaliddate').datebox("setValue",util.toDate($('#sup_qltycatevaliddate').val()));
		
		//实现时间控件注册，并赋初值 上岗证有效期
	 	$('#sup_clkvaliddate').datebox({
			required:true,	
		}); 
		if($('#sup_clkvaliddate').val()!="")
			$('#sup_clkvaliddate').datebox("setValue",util.toDate($('#sup_clkvaliddate').val()));
		
		//实现时间控件注册，并赋初值 委托书失效期
	 	$('#sup_letvaliddate').datebox({
			required:true,	
		}); 
		if($('#sup_letvaliddate').val()!="")
			$('#sup_letvaliddate').datebox("setValue",util.toDate($('#sup_letvaliddate').val()));
		
		//实现时间控件注册，并赋初值 审核日期
	 	$('#sup_checkdate').datebox({
			required:true,	
		}); 
		if($('#sup_checkdate').val()!="")
			$('#sup_checkdate').datebox("setValue",util.toDate($('#sup_checkdate').val()));
		
		
 		$('#sup_category1').combobox({
 			url : global_param.context_name + "/system/combobox/lists?pid=249",
 			method :"get",
 			valueField : "cbs_id",
 			textField : "cbs_chn",
 			editable:false,
 		});
 		$('#sup_category2').combobox({
 			url : global_param.context_name + "/system/combobox/lists?pid=256",
 			method :"get",
 			valueField : "cbs_id",
 			textField : "cbs_chn",
 			editable:false,
 		});
 		$('#sup_category3').combobox({
 			url : global_param.context_name + "/system/combobox/lists?pid=260",
 			method :"get",
 			valueField : "cbs_id",
 			textField : "cbs_chn",
 			editable:false,
 		});
 		$('#sup_property').combobox({
 			url : global_param.context_name + "/system/combobox/lists?pid=268",
 			method :"get",
 			valueField : "cbs_id",
 			textField : "cbs_chn",
 			editable:false,
 		});
 		$('#sup_rmtway').combobox({
 			url : global_param.context_name + "/system/combobox/lists?pid=261",
 			method :"get",
 			valueField : "cbs_id",
 			textField : "cbs_chn",
 			editable:false,
 		});
 		$('#sup_payway').combobox({
 			url : global_param.context_name + "/system/combobox/lists?pid=304",
 			method :"get",
 			valueField : "cbs_id",
 			textField : "cbs_chn",
 			editable:false,
 		});
 		$('#sup_pmtname').combobox({
 			url : global_param.context_name + "/system/combobox/lists?pid=272",
 			method :"get",
 			valueField : "cbs_id",
 			textField : "cbs_chn",
 			editable:false,
 		});
 		$('#sup_bnlsway').combobox({
 			url : global_param.context_name + "/system/combobox/lists?pid=283",
 			method :"get",
 			valueField : "cbs_id",
 			textField : "cbs_chn",
 			editable:false,
 		});
 		$('#sup_qltycatename').combobox({
 			url : global_param.context_name + "/system/combobox/lists?pid=288",
 			method :"get",
 			valueField : "cbs_id",
 			textField : "cbs_chn",
 			editable:false,
 		});
 		$('#sup_localdrug').combobox({
 			url : global_param.context_name + "/system/combobox/lists?pid=297",
 			method :"get",
 			valueField : "cbs_id",
 			textField : "cbs_chn",
 			editable:false,
 		});
 		$('#sup_printway').combobox({
 			url : global_param.context_name + "/system/combobox/lists?pid=299",
 			method :"get",
 			valueField : "cbs_code",
 			textField : "cbs_chn",
 			editable:false,
 		});
 		
 		$('#tab-sup').tabs({ 
 			//fit:true,
 			width:600,
 		    onSelect: function(){  
 		    }  
 		});
 		
		var keyPress = new pageKeyPress();
		keyPress.setTabChangeID(tabID, tabChangID);
		keyPress.bindKeyPressToCtrl(ctrlsID, sumbitButtonID);
		
		
		//如果是修改，则加载图片
		if($("#sup_goodticketphoto").val()!=""){
			var index=$("#sup_goodticketphoto").val().indexOf("HRERP");
			var subString=$("#sup_goodticketphoto").val().substring(index-1);
			var url=subString.replace(/\\/g,"/");//将斜杠\全部替换为/
			document.getElementById("preview1").style.display="block";//将隐藏图片显示
			$("#preview1").attr("src",url);
		}
		if($("#sup_sealmoldphoto").val()!=""){
			var index=$("#sup_sealmoldphoto").val().indexOf("HRERP");
			var subString=$("#sup_sealmoldphoto").val().substring(index-1);
			var url=subString.replace(/\\/g,"/");//将斜杠\全部替换为/
			document.getElementById("preview2").style.display="block";//将隐藏图片显示
			$("#preview2").attr("src",url);
		}
	}); 
 	
 	
 	
	$(function() {
		var CK = document.getElementsByTagName('input');
		for(var i = 0; i < CK.length; i++){
			if(CK[i].type == "checkbox" && document.getElementsByName(CK[i].id)[0].value == "1"){
				CK[i].checked = true;
	 		}
			else if(CK[i].type == "checkbox" && document.getElementsByName(CK[i].id)[0].value == "0"){
				CK[i].checked = false;
	 		}
		}
	 });  
 	//数据封装，并且提交数据
	function submitForm() {
		
		var fileIds = ["file_upload_sup_goodticketphoto","file_upload_sup_sealmoldphoto"];
		var inputIds = ["sup_goodticketphoto","sup_sealmoldphoto"];
		if(!$('#_form').form('validate')){
			util.show("还有必填项未填写！");
			return;
		}
		
		if(document.getElementById("file_upload_sup_goodticketphoto").value==""||document.getElementById("file_upload_sup_sealmoldphoto").value==""){
			if(document.getElementById("sup_goodticketphoto").value==""||document.getElementById("sup_sealmoldphoto").value==""){
				util.show("请上传图片");
				return;
			}			
		}
		submitAll(fileIds,inputIds);
	}
	//提交表单和图片
	function submitAll(fileIds,inputIds){
		var f = new Array();
		var is = new Array();
		//将需要提交的图片提取出来
		for(var i =0;i<fileIds.length;i++){
			if(document.getElementById(fileIds[i]).value!=""){
				f.push(fileIds[i]);
				is.push(inputIds[i]);
			}
		}
		//若没有图片提交，则直接提交数据
		if(f.length==0){
			submitData();
			return;
		}
			
		//如果有图片提交，则分别提交图片，true当提交最后一张图片的时候顺便提交数据
		for(var j=0;j<f.length;j++){
			uploadImg(f[j],is[j],f.length);
		}
	}
	//上传图片
	function uploadImg(fileId,inputId,number){
		//alert("提交图片");
		jQuery.ajaxFileUpload({
			url : global_param.context_name+'/FileManage/FileUpload', //处理上传文件的服务端
			secureuri : false, //与页面处理代码中file相对应的ID值
			fileElementId : fileId,//'file_upload_sup_sealmoldphoto',
			dataType : 'text', //返回数据类型:text，xml，json，html,scritp,jsonp五种
			autoSubmit : true,
			success : function(result) {
				flag++;
				$("#"+inputId).val(result);//sup_sealmoldphoto
				if(flag==number){
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
	function submitData(){
		var getCK=document.getElementsByTagName('input');
	 	for(var i = 0; i<getCK.length; i++){
	 		if(getCK[i].type == "checkbox"&&getCK[i].checked == true){
	 			var j = document.getElementsByName(getCK[i].id);
	 			j[0].value = 1;
	 		}
	 		else if(getCK[i].type == "checkbox"&&getCK[i].checked == false){
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
        }  
        else {  
            var fileContentType = obj.value.match(/^(.*)(\.)(.{1,8})$/)[3]; //这个文件类型正则很有用   
            ////布尔型变量  
            var isExists = false;  
            //循环判断图片的格式是否正确  
            for (var i in array) {  
                if (fileContentType.toLowerCase() == array[i].toLowerCase()) {  
                    //图片格式正确之后，根据浏览器的不同设置图片的大小  
                    if (obj.files && obj.files[0]) {  
                        //火狐下，直接设img属性   
                        imgObjPreview.style.display = 'block';  
                        imgObjPreview.style.width = '20px';  
                        imgObjPreview.style.height = '20px';  
                        //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式   
                        imgObjPreview.src = window.URL.createObjectURL(obj.files[0]);  
                    }  
                    else {  
                        //IE下，使用滤镜   
                        obj.select();  
                        var imgSrc = document.selection.createRange().text;  
                        //必须设置初始大小   
                        localImagId.style.width = "120px";  
                        localImagId.style.height = "20px";  
                        //图片异常的捕捉，防止用户修改后缀来伪造图片   
                        try {  
                            localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";  
                            localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;  
                        }  
                        catch (e) {  
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
    function previewimg(img){
    	$.messager.alert("图片",'<img style="width:500px;" src="'+img.getAttribute("src")+'">');
    }
 	//经营范围
 	function setScope(nodes){
 		if(!nodes){
 	 		$("#sup_bnlsscope").val("");
 	 		return;
 		}
 		var scope = "";
 		//alert(JSON.stringify(nodes));//打印对象的函数
 		for(var i=0;i<nodes.length-1;i++){
 			scope = scope + nodes[i].cbs_chn+","; 			
 		}
 		scope = scope + nodes[nodes.length-1].cbs_chn;
 		$("#sup_bnlsscope").val(scope);
 	}//end
 	
/*
 * 地区药监开始
 */
function selectFromOther(){
	if($("#sup_finddrugid").val()=="%"){
		selectLocalDrug('setLocalDrug','');
	}else{
		selectLocalDrug('setLocalDrug',$("#sup_finddrugid").val());
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
		$("#sup_drugid").val(node.ca_id);//设置药监ID	
		$("#sup_finddrugid").val(node.ca_xkzbh);//设置许可证号	
	}else{
		$("#sup_drugid").val("");		
		$("#sup_finddrugid").val("");
	}
}
//地区药监end
function perviewPrint(){
	var printType = $("#sup_printway").combo('getValue');
	
	if(printType==""){
		util.show("请选择打印格式");
	}else{
		var node = {
				text : '供方报表格式',
				attributes : {url : "basInfo/report/report?fileName="+printType}
			};
		self.parent.addTab(node);
		//util.get(global_param.context_name + "/basInfo/report/report?fileName="+printType);
		/*$('#tt').tabs('add',{
			title:"测试",
			content:'<iframe style="width: 100%; height: 100%;" frameborder="0" src="' + global_param.context_name + "/basInfo/report/report?fileName="+printType + '"></iframe>',
			closable:true
		});*/
	}
	
}
</script>

<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
			<form:form id="_form" action="${formUrl}" method="${method}" onsubmit="return false;">
				<input id="sup_id" name="sup_id" type="hidden" value="${supInfoManage.sup_id}" />
	
				<div id="tab-sup" style="height:600px">   
			        <div title="基本信息" id="basicinfo" >             
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_code" class="field">供方编码：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_code" name="sup_code" class="easyui-validatebox" 
									data-options="required:'true',validType:['num','length[1,20]']"  value="${supInfoManage.sup_code}" />
							</div>
						</div>
					 	<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_category1" class="field">类别1：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_category1" name="sup_category1" class="easyui-combobox" style="width:152px" 
									data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_category1}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_category2" class="field">类别2：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_category2" name="sup_category2" class="easyui-combobox" style="width:152px"
									 data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_category2}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_category3" class="field">类别3：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_category3" name="sup_category3" class="easyui-combobox" style="width:152px"
									data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_category3}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_areano" class="field">区号：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_areano" name="sup_areano" class="easyui-validatebox" 
									data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_areano}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_shortname" class="field">简称：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_shortname" name="sup_shortname" class="easyui-validatebox"
									data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_shortname}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_name" class="field">客户全称：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_name" name="sup_name" class="easyui-validatebox" onblur="chnToLetter($(this).val(),'sup_chnpy')"
									data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_name}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_chnpy" class="field">拼音码：</label>
							</div>
							<div class="Dialog-form-item">
							<input id="sup_chnpy" name="sup_chnpy" class="easyui-combobox" style="width:152px" value="${supInfoManage.sup_chnpy}" />
					</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_address" class="field">地址：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_address" name="sup_address" class="easyui-validatebox" 
									data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_address}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_zipcode" class="field">邮编：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_zipcode" name="sup_zipcode" class="easyui-validatebox"
									data-options="validType:['num','length[1,20]']"  value="${supInfoManage.sup_zipcode}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_procedure" class="field">手续：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_procedure" name="sup_procedure" class="easyui-validatebox"
									data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_procedure}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_ctractno" class="field">合同号：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_ctractno" name="sup_ctractno" class="easyui-validatebox"
									data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_ctractno}" />
							</div>
						</div>		
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_ctactperson" class="field">联系人：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_ctactperson" name="sup_ctactperson" class="easyui-validatebox"
									data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_ctactperson}" />
							</div>
						</div>		
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_qqcode" class="field">QQ：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_qqcode" name="sup_qqcode" class="easyui-validatebox"
									data-options="validType:['num','length[1,20]']"  value="${supInfoManage.sup_qqcode}" />
							</div>
						</div>		
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_mobile" class="field">手机：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_mobile" name="sup_mobile" class="easyui-validatebox"
									data-options="validType:['mobile','length[1,20]']"  value="${supInfoManage.sup_mobile}" />
							</div>
						</div>		
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_fax" class="field">传真：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_fax" name="sup_fax" class="easyui-validatebox"
									data-options="validType:['fax','length[1,20]']"  value="${supInfoManage.sup_fax}" />
							</div>
						</div>		
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_email" class="field">电子信箱：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_email" name="sup_email" class="easyui-validatebox"
									data-options="validType:['email','length[1,20]']"  value="${supInfoManage.sup_email}" />
							</div>
						</div>		
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_msncode" class="field">MSN：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_msncode" name="sup_msncode" class="easyui-validatebox"
									data-options="validType:['num','length[1,20]']"  value="${supInfoManage.sup_msncode}" />
							</div>
						</div>		
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_buyname" class="field">采购员：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_buyname" name="sup_buyname" class="easyui-validatebox"
									data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_buyname}" />
							</div>
						</div>							        			           				            
			     		<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_tel" class="field">电话：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_tel" name="sup_tel" class="easyui-validatebox"
									data-options="validType:['phone','length[1,20]']"  value="${supInfoManage.sup_tel}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_brief" class="field">简介：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_brief" name="sup_brief" class="easyui-validatebox" 
									data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_brief}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_fillingdate" class="field" style = "color:red">建档日期：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_fillingdate" name="sup_fillingdate" style = "width:152px"
									data-options="required:true,validType:'length[1,20]'"  value="${supInfoManage.sup_fillingdate}" />
							</div>
						</div>	 
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_property" class="field">性质：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_property" name="sup_property" class="easyui-combobox" style="width:152px"
									data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_property}" />
							</div>
						</div>
						<div class="float-l">
			        		<div class="SelectAll">
			        			<input name="sup_other1" type ="hidden" value="${supInfoManage.sup_other1}" />
								<input id="sup_other1" type ="checkbox" style="width:20px" />
								<label for="sup_other1">其他1</label>
							</div>
						</div>
					 
						 <div class="float-l">
			        		<div class="SelectAll">
			        			<input name="sup_other2" type ="hidden" value="${supInfoManage.sup_other2}" />
								<input id="sup_other2" type ="checkbox" style="width:20px" />
								<label for="sup_other2">其他2</label>
							</div>
						</div>  
					 	<div class="float-l">
			        		<div class="SelectAll">
			        			<input name="sup_other3" type ="hidden" value="${supInfoManage.sup_other3}" />
								<input id="sup_other3" type ="checkbox" style="width:20px" />
								<label for="sup_other3">其他3</label>
							</div>
						</div>  
						<div class="float-l">
			        		<div class="SelectAll">
								<input name="sup_other4" type ="hidden" value="${supInfoManage.sup_other4}" />
								<input id="sup_other4" type ="checkbox" style="width:20px" />
								<label for="sup_other4">其他4</label>
							</div>
						</div>		  
			     	</div> 			        
			       
			         
			        <div title="财务信息" id="moneyinfo">
			        <div style="border:none;position:relative;width:570px;margin-top:5px;margin-bottom:5px;margin-left:5px">基本财务信息：</div>
						<div style="width:570px;height:190px;margin-left:5px; border:1px solid #CCCCCC">
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sup_accname" class="field">开户户名：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sup_accname" name="sup_accname" class="easyui-validatebox"
										data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_accname}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sup_bankname" class="field">开户行：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sup_bankname" name="sup_bankname" class="easyui-validatebox"
										data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_bankname}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sup_accno" class="field">开户帐号：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sup_accno" name="sup_accno" class="easyui-validatebox"
										data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_accno}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sup_registaddr" class="field">注册地址：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sup_registaddr" name="sup_registaddr" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_registaddr}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sup_rmtway" class="field">汇款方式：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sup_rmtway" name="sup_rmtway" class="easyui-combobox"  style="width:152px"
										data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_rmtway}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sup_payway" class="field">结款方式：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sup_payway" name="sup_payway" class="easyui-combobox" style="width:152px"
										data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_payway}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sup_paydate" class="field">结款日期：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sup_paydate" name="sup_paydate" style="width:152px"
										data-options="required:true,validType:'length[1,20]'"  value="${supInfoManage.sup_paydate}" />
								</div>
							</div> 
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for=sup_bond class="field">保证金：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sup_bond" name="sup_bond" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_bond}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sup_tax" class="field">税号：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sup_tax" name="sup_tax" class="easyui-validatebox"
										data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_tax}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sup_fnlnote" class="field">财务备注：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sup_fnlnote" name="sup_fnlnote" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_fnlnote}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sup_paydays" class="field">付款天数：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sup_paydays" name="sup_paydays" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_paydays}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="sup_istaxpayer" class="field">是否为一般纳税人：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="sup_istaxpayer" name="sup_istaxpayer" class="easyui-combobox" style = "width:152px"
										data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_istaxpayer}" />
								</div>
							</div>
						</div>              
			        	
						<div style="border:none;position:relative;width:570px;margin-top:5px;margin-bottom:5px;
						margin-left:5px">其他开户信息（必须保存供方信息后可以添加其他开户信息）</div>

						<!-- 显示区域 -->
						<div style="height:318px">
							<table id="AccountDg"></table>
						</div>
						<!-- 编辑窗口 -->
						<div id="editAccountWindow"></div>
							
							
			        </div>  
			        
			        
			        <div title="证照信息" id="cardinfo">
			         <div style="border:none;position:relative;top:0px;margin-top:10px;margin-bottom:5px;margin-left:5px;width:570px">许可证：</div>
			        	<div style="width:570px;height:125px;margin-left:5px;border:1px solid #CCCCCC">
			        	<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_pmtname" class="field">许可证名称：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_pmtname" name="sup_pmtname" class="easyui-combobox" style="width:152px"
									data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_pmtname}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_pmtno" class="field">许可证号：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_pmtno" name="sup_pmtno" class="easyui-validatebox"
									data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_pmtno}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_pmtchief" class="field">负责人：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_pmtchief" name="sup_pmtchief" class="easyui-validatebox"
									data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_pmtchief}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_pmtscope" class="field">许可范围：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_pmtscope" name="sup_pmtscope" class="easyui-validatebox"
									data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_pmtscope}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_pmtcueday" class="field">提示天数：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_pmtcueday" name="sup_pmtcueday" class="easyui-validatebox"
									data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_pmtcueday}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_pmtvaliddate" class="field">有效期至：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_pmtvaliddate" name="sup_pmtvaliddate" style="width:152px"
									data-options="required:'true',required:true,validType:'length[1,20]'"  value="${supInfoManage.sup_pmtvaliddate}" />
							</div>
						</div> 
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_pmtissauthority" class="field">发证机关：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_pmtissauthority" name="sup_pmtissauthority" class="easyui-validatebox"
									data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_pmtissauthority}" />
							</div>
						</div>
			        	</div>
			        	<div style="border:none;position:relative;width:570px;margin-top:5px;margin-bottom:5px;margin-left:5px">营业执照：</div>
			        	<div style="width:570px;height:187px;margin-left:5px; border:1px solid #CCCCCC">
			        	<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_bnlsregistno" class="field">注册号：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_bnlsregistno" name="sup_bnlsregistno" class="easyui-validatebox"
									data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_bnlsregistno}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_bnlslegalperson" class="field">法人：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_bnlslegalperson" name="sup_bnlslegalperson" class="easyui-validatebox"
									data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_bnlslegalperson}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_bnlsregistmoney" class="field">注册资金：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_bnlsregistmoney" name="sup_bnlsregistmoney" class="easyui-validatebox"
									data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_bnlsregistmoney}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_bnlsproperty" class="field">经济性质：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_bnlsproperty" name="sup_bnlsproperty" class="easyui-validatebox"
									data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_bnlsproperty}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_bnlsscope" class="field" style = "color:red">经营范围：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_bnlsscope" name="sup_bnlsscope" class="easyui-validatebox" onclick="selectScope('setScope',this)"
									data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_bnlsscope}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_bnlsvaliddate" class="field">有效期至：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_bnlsvaliddate" name="sup_bnlsvaliddate" style="width:152px"
									data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_bnlsvaliddate}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_bnlsway" class="field">经营方式：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_bnlsway" name="sup_bnlsway" class="easyui-combobox"  style="width:152px"
									data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_bnlsway}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_bnlsissauthority" class="field">发照机关：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_bnlsissauthority" name="sup_bnlsissauthority" class="easyui-validatebox"
									data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_bnlsissauthority}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_bnlsanndeaddate" class="field">年检截止：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_bnlsanndeaddate" name="sup_bnlsanndeaddate" style="width:152px"
									data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_bnlsanndeaddate}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_bnlscueday" class="field">提示天数：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_bnlscueday" name="sup_bnlscueday" class="easyui-validatebox"
									data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_bnlscueday}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_bnlsanndeadnote" class="field">年检备注：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_bnlsanndeadnote" name="sup_bnlsanndeadnote" class="easyui-validatebox"
									data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_bnlsanndeadnote}" />
							</div>
						</div>
			        	</div>
	
						<div style="border:none;position:relative;width:570px;margin-top:5px;margin-bottom:5px;margin-left:5px">质量证书：</div>
						<div style="width:570px;height:95px;margin-left:5px; border:1px solid #CCCCCC">
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_qltycatename" class="field">证书名称：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_qltycatename" name="sup_qltycatename" class="easyui-combobox" style= "width:152px"
									data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_qltycatename}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_qltycateno" class="field">证书编号：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_qltycateno" name="sup_qltycateno" class="easyui-validatebox"
									data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_qltycateno}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_qltycatescope" class="field">认证范围：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_qltycatescope" name="sup_qltycatescope" class="easyui-validatebox"
									data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_qltycatescope}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_qltycatevaliddate" class="field">有效期至：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_qltycatevaliddate" name="sup_qltycatevaliddate" style="width:152px"
									data-options="required:'true',required:true,validType:'length[1,20]'"  value="${supInfoManage.sup_qltycatevaliddate}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_qltycatecueday" class="field">提示天数：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_qltycatecueday" name="sup_qltycatecueday" class="easyui-validatebox"
									data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_qltycatecueday}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_qltycateissauthority" class="field">发证机关：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_qltycateissauthority" name="sup_qltycateissauthority" class="easyui-validatebox"
									data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_qltycateissauthority}" />
							</div>
						</div>						
						</div>
						<div style="border:none;position:relative;width:570px;margin-top:5px;margin-bottom:5px;
						margin-left:5px">其他证书（必须保存供方信息后可以添加其他开户信息）</div>

						<!-- 显示区域 -->
						<div style="width:1000;height:300px">
							<table id="licenseDg"></table>
						</div>
						<!-- 编辑窗口 -->
						<div id="editLicenseWindow"></div>
							            
			        </div>  
			        
			        
			        <div title="质量其他相关" id="qualityinfo">            
			        	<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_clkname" class="field">业务员：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_clkname" name="sup_clkname" class="easyui-validatebox"
									data-options="required:'true',validType:'length[1,20]'"  value="${supInfoManage.sup_clkname}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_clkidcard" class="field">身份证：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_clkidcard" name="sup_clkidcard" class="easyui-validatebox"
									data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_clkidcard}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_clktel" class="field">电话：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_clktel" name="sup_clktel" class="easyui-validatebox"
									data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_clktel}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_clkvaliddate" class="field">上岗证有效期：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_clkvaliddate" name="sup_clkvaliddate" style="width:152px"
									data-options="required:true,validType:'length[1,20]'"  value="${supInfoManage.sup_clkvaliddate}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_letvaliddate" class="field">委托书失效期：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_letvaliddate" name="sup_letvaliddate" style="width:152px"
									data-options="required:true,validType:'length[1,20]'"  value="${supInfoManage.sup_letvaliddate}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_letcueday" class="field">提示天数：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_letcueday" name="sup_letcueday" class="easyui-validatebox"
									data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_letcueday}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_relatedseal" class="field">相关印章：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_relatedseal" name="sup_relatedseal" class="easyui-validatebox"
									data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_relatedseal}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_fileno" class="field">档案号：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_fileno" name="sup_fileno" class="easyui-validatebox"
									data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_fileno}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_filelocation" class="field">档案位置：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_filelocation" name="sup_filelocation" class="easyui-validatebox"
									data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_filelocation}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_localdrug" class="field">地区药监：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_localdrug" name="sup_localdrug" class="easyui-combobox" style = "width:152px"
									data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_localdrug}" />
							</div>
						</div>
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_finddrugid" class="field">查询号：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_finddrugid" name="sup_finddrugid" class="easyui-validatebox" style = "width:152px" 
								onblur="selectFromOther()" data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_finddrugid}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_drugid" class="field">地区药监ID：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_drugid" name="sup_drugid" class="easyui-validatebox" 
									data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_drugid}" readOnly="readOnly"/>
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_drugcode" class="field">国家药监码：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_drugcode" name="sup_drugcode" class="easyui-validatebox"
									data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_drugcode}" />
							</div>
						</div>		
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_annoutput" class="field">年产值：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_annoutput" name="sup_annoutput" class="easyui-validatebox"
									data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_annoutput}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_technician" class="field">技术人员：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_technician" name="sup_technician" class="easyui-validatebox"
									data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_technician}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_majorhonor" class="field">主要荣誉：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_majorhonor" name="sup_majorhonor" class="easyui-validatebox"
									data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_majorhonor}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_intendedspecie" class="field">拟供品种：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_intendedspecie" name="sup_intendedspecie" class="easyui-validatebox"
									 data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_intendedspecie}" />
							</div>
						</div>	
						
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_majorproduct" class="field">主要产品：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_majorproduct" name="sup_majorproduct" class="easyui-validatebox"
									data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_majorproduct}" />
							</div>
						</div>	<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_qltystatus" class="field">质量状况：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_qltystatus" name="sup_qltystatus" class="easyui-validatebox"
									data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_qltystatus}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_qltyinstitution" class="field">质量机构名：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_qltyinstitution" name="sup_qltyinstitution" class="easyui-validatebox"
									data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_qltyinstitution}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_qltyauthen" class="field">认证情况：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_qltyauthen" name="sup_qltyauthen" class="easyui-validatebox"
									 data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_qltyauthen}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_qltymanage" class="field">质量管理：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_qltymanage" name="sup_qltymanage" class="easyui-validatebox"
									 data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_qltymanage}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_qltychief" class="field">质量负责人：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_qltychief" name="sup_qltychief" class="easyui-validatebox"
									 data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_qltychief}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_qltychiefsex" class="field">性别：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_qltychiefsex" name="sup_qltychiefsex" class="easyui-combobox" style = "width:152px"
									data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_qltychiefsex}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_qltychiefedu" class="field">文化程度：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_qltychiefedu" name="sup_qltychiefedu" class="easyui-validatebox"
									 data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_qltychiefedu}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_qltychiefposition" class="field">职务：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_qltychiefposition" name="sup_qltychiefposition" class="easyui-validatebox"
								 data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_qltychiefposition}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_qltychieftitle" class="field">技术职称：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_qltychieftitle" name="sup_qltychieftitle" class="easyui-validatebox"
									data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_qltychieftitle}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_qltychiefworkyear" class="field">工作年限：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_qltychiefworkyear" name="sup_qltychiefworkyear" class="easyui-validatebox"
									 data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_qltychiefworkyear}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_qltynote" class="field">质量备注：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_qltynote" name="sup_qltynote" class="easyui-validatebox"
									 data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_qltynote}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_printway" class="field">打印格式：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_printway" name="sup_printway" class="easyui-combobox" style = "width:152px"
									data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_printway}" />
							<input id="perview" type="button"  value="预览" onclick="perviewPrint();" style="height: 22px;width: 35px;"/> 
							</div>
							
							
						</div>		
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_checkdate" class="field">审核日期：</label>
							</div>
							<div class="Dialog-form-item"> 
								<input id="sup_checkdate" name="sup_checkdate" style="width:152px"
									data-options="required:true,validType:'length[1,20]'"  value="${supInfoManage.sup_checkdate}" />
							</div>
						</div>	
						<div class="float-l">
							<div class="Dialog-form-title">
								<label for="sup_checkname" class="field">审核人：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_checkname" name="sup_checkname" class="easyui-validatebox"
									data-options="validType:'length[1,20]'"  value="${supInfoManage.sup_checkname}" />
							</div>
						</div>												 
				</div> 			        
			         
			        <div title="相关图片" id="photoinfo">		
			        <div style="border:none;position:relative;width:570px;margin-top:5px;margin-bottom:5px;margin-left:5px">图片信息：</div>
						<div style="width:570px;height:95px;margin-left:5px; border:1px solid #CCCCCC">
						<div class="float-l" id="localImag1">
							<div class="Dialog-form-title">
								<label for="sup_goodticketphoto" class="field">随货票样：</label>
							</div>
							<div class="Dialog-form-item">
								<input id="sup_goodticketphoto" name="sup_goodticketphoto" style="float:left;width:250px" class="easyui-validatebox"
										readOnly="true" value="${supInfoManage.sup_goodticketphoto}" />
								<input id="file_upload_sup_goodticketphoto" style="float:left;width:150px" name="sup_goodticketphoto" ContentEditable="false" accept="image/*"
	    						 runat="server" onchange="javascript:setImagePreview(this,localImag1,preview1);" type="file" /> 
	    						<img id="preview1" alt="预览图片" onclick="previewimg(this);"
	    						 style="margin-left:8px;float:right;width: 20px; height: 20px;display:none;"/> 
							</div>
						</div>
						<div class="float-l" id="localImag2">
							<div class="Dialog-form-title">
								<label for="sup_sealmoldphoto" class="field">印章印模：</label>
							</div>
							<div class="Dialog-form-item">
	    					  	<input id="sup_sealmoldphoto" name="sup_sealmoldphoto" style="float:left;width:250px" class="easyui-validatebox" 
										readOnly="true"	value="${supInfoManage.sup_sealmoldphoto}"  />
	    						<input id="file_upload_sup_sealmoldphoto" style="width:150px;float:left" name="sup_sealmoldphoto" ContentEditable="false" accept="image/*"
	    						 runat="server" onchange="javascript:setImagePreview(this,localImag2,preview2);" type="file" /> 
	    						<img id="preview2" alt="预览图片" onclick="previewimg(this);"
	    						 style="margin-left:8px;float:right;width: 20px;height: 20px;display:none;"/>   
    						 </div>
						</div>
						</div>
												      		
						<div style="border:none;position:relative;width:570px;margin-top:5px;margin-bottom:5px;
						margin-left:5px">其他图片（必须保存供方信息后可以添加其他图片信息）</div>

						<!-- 显示区域 -->
						<div style="height:413px">
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
		<input id="submitSupply" class="btn-save"
			onmouseover="this.className='btn-save-over'"
			onmouseout="this.className='btn-save'"
			onmousedown="this.className='btn-save-down'" type="button"
			onclick="submitForm();" /> 
		<input id="" class="btn-cancel"
			onmouseover="this.className='btn-cancel-over'"
			onmouseout="this.className='btn-cancel'"
			onmousedown="this.className='btn-cancel-down'" type="button"
			onclick="util.closeWindow('editWindow');" />
	</div>
	<!-- 编辑窗口 -->
	<div id="editSonWindow"></div>
</div>