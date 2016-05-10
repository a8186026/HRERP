var flag = 0;//图片提交标识

//日期框初始化(如果不注册则无法使用keypress来实现控件跳转)
$('#product_filingtime').datebox({    
    required:false,
    editable:false,
});
//所有控件ID
var ctrlsID = ["product_commonnametype","product_commonname","product_name","product_code","product_barcode","product_filingtime","product_category","product_othercategory",
   			"product_othercategory2","product_othercategory3","product_othercategory4","product_othercategory5","product_dosagetype","product_proname",
   			"product_chnpy","product_specification","product_unit","product_productarea","product_factoryname","product_factorychnpy","product_approvalname","product_approvalnum","product_dsurveillanceid","product_dsurveillanceno","product_taxrate",
   			"product_procedure","product_middlepacking","product_packingamount","product_packingunit","product_cartonsize","product_shiphandlingnum","product_articleno","product_groupno","product_regtrademark","product_buyer","product_description",
   			"product_basremark", 
   			
   			"product_tradeprice","product_wsalelprice","product_lowsalelprice","product_dealwsaleprice","product_saleprice1","product_saleprice2","product_saleprice3","product_saleprice4","product_marketprice","product_retailprice",
   			"product_retaillprice","product_lretaillprice","product_dealretailprice","product_memberprice","product_proretailprice","product_basmedbidprice1","product_basmedbidprice2","product_basmedbidprice3","product_bidprice",
   			"product_retailcommission","product_invoicecom","product_wholesalecom","product_businesscom","product_nationallprice",
   			"product_dealprice","product_tinynum","product_dealcustomer","product_priceremark",
			
   			"product_GMPcertify","product_certificationtime","product_licensevalidtime","product_remindday","product_licenseremark",
   		 
   			"product_filenumber","product_filelocation","product_quotanum","product_memberlimit","product_bidno","product_bidno2","product_bidno3","product_licensevaliddate","product_instocklday","product_outstocklday",
   			"product_outstockrday","product_internationcode","product_nationaldscode","product_quanlitystandard","product_storagecondition","product_storeplace","product_lowtemperature","product_hightemperature","product_effect","product_performance",
   			"product_medcondition","product_instruction","product_qualityremark","product_printtype","product_managecetagory","product_checktime","product_checkperson",
   			"product_picname","product_picremark"];
   		 
	//确认BUTTON
	var sumbitButtonID = "submitProduct";
	var tabID = "tab-pro";
	var tabChangID = ["product_basremark","product_priceremark","product_licenseremark","product_checkperson"];
	var keyPress = new pageKeyPress();
	
	
	
	
	
	$(function() {
		
		//价格格式，数量格式设置默认值
		$("#product_amountFormat").val($("#product_amountFormat").val()==""?"0.00":$("#product_amountFormat").val());
		$("#product_numberFormat").val($("#product_numberFormat").val()==""?"0.00":$("#product_numberFormat").val());
		$("#product_moneyPerPoint").val($("#product_moneyPerPoint").val()==""?"1":$("#product_moneyPerPoint").val());
		
		//实现时间控件注册，并赋初值 产品—建档日期
	 	$('#product_filingtime').datebox({
			required:true,
		}); 
		if($('#product_filingtime').val()!="")
			$('#product_filingtime').datebox("setValue",util.toDate($('#product_filingtime').val()));
		//实现时间控件注册，并赋初值 产品—证照认证日期
	 	$('#product_certificationtime').datebox({
			required:true,	
		}); 
		if($('#product_certificationtime').val()!="")
			$('#product_certificationtime').datebox("setValue",util.toDate($('#product_certificationtime').val()));
		//实现时间控件注册，并赋初值 产品—证照有效期至
	 	$('#product_licensevalidtime').datebox({
			required:true,	
		}); 
		if($('#product_licensevalidtime').val()!="")
			$('#product_licensevalidtime').datebox("setValue",util.toDate($('#product_licensevalidtime').val()));
		//实现时间控件注册，并赋初值 产品—质量有效期 
	 	$('#product_licensevaliddate').datebox({
			required:true,	
		}); 
		if($('#product_licensevaliddate').val()!="")
			$('#product_licensevaliddate').datebox("setValue",util.toDate($('#product_licensevaliddate').val()));
		//实现时间控件注册，并赋初值 产品—质量审核日期
	 	$('#product_checktime').datebox({
			required:true,	
		}); 
		if($('#product_checktime').val()!="")
			$('#product_checktime').datebox("setValue",util.toDate($('#product_checktime').val()));
		
		//实现控件册该
		$('#product_chnpy').combobox({
			url : "",
			method :"",
			valueField : "id",
			textField : "text",
			editable: false,
		});
		$('#product_chnpy').combobox("setValue",$('#product_chnpy').attr("value"));
		
		$('#product_factorychnpy').combobox({
			url : "",
			method :"",
			valueField : "id",
			textField : "text",
			editable: false,
		});
		$('#product_factorychnpy').combobox("setValue",$('#product_factorychnpy').attr("value"));
		
 
		$("#product_category").combotree({
			url : global_param.context_name + "/system/combobox/listTree?type=product_tree",
			method : 'get',
			idField : 'cbs_id',
			textField : 'cbs_chn',
			parentField : 'cbs_pid',
			required:'true',
			onSelect:function(){
				//向后台请求编号
				var node = $("#product_category").combotree("tree").tree("getSelected");
				var product_code = node.cbs_code;
				
				var parent = $("#product_category").combotree("tree").tree("getParent",node.target);
				while(parent){
					product_code = parent.cbs_code +""+ product_code;
					parent = $("#product_category").combotree("tree").tree("getParent",parent.target);
				}
				var result = util.get(global_param.context_name + "/product/proInfoManage/getMaxProductCode?ticketNumber="+product_code);
				$("#product_code").val(result);
				$("#product_barcode").val(result);
			},
			onLoadSuccess:function(){
				//如果添加前选择了分类目录，而且文本里面没有内容,则自动填充目录
				if($("#product_category").val()==""||$("#product_category").val()==null)
					$("#product_category").combotree("setValue",$("#id").val());
			}
		});
		
		
		var json = [{    
		    "id":1,    
		    "text":"手动输入"   
		},{    
		    "id":2,    
		    "text":"品名库选择"   
		},{    
		    "id":3,    
		    "text":"沈阳药监库选择"   
		}];	

		$("#product_commonnametype").combobox({
			data:json,
			valueField : "id",
			textField : "text",
		});
		/*$('#product_category2').combobox({
			url : global_param.context_name + "/system/combobox/lists?pid=182",
			method :"get",
			valueField : "cbs_id",
			textField : "cbs_chn",
			//editable:false,
		});*/
		
		$('#product_othercategory').combobox({
			url : global_param.context_name + "/system/combobox/lists?pid=244",
			method :"get",
			valueField : "cbs_id",
			textField : "cbs_chn",
			//editable:false,
		});
		
		$('#product_othercategory2').combobox({
			url : global_param.context_name + "/system/combobox/lists?pid=228",
			method :"get",
			valueField : "cbs_id",
			textField : "cbs_chn",
			//editable:false,
		});
		
		$('#product_othercategory3').combobox({
			url : global_param.context_name + "/system/combobox/lists?pid=233",
			method :"get",
			valueField : "cbs_id",
			textField : "cbs_chn",
			//editable:false,
		});
		
		$('#product_othercategory4').combobox({
			url : global_param.context_name + "/system/combobox/lists?pid=317",
			method :"get",
			valueField : "cbs_id",
			textField : "cbs_chn",
			//editable:false,
		});
		
		$('#product_othercategory5').combobox({
			url : global_param.context_name + "/system/combobox/lists?pid=307",
			method :"get",
			valueField : "cbs_id",
			textField : "cbs_chn",
			//editable:false,
		});
		
		$('#product_dosagetype').combobox({
			url : global_param.context_name + "/system/combobox/lists?pid=98",
			method :"get",
			valueField : "cbs_id",
			textField : "cbs_chn",
			editable:false,
		});
		
		$('#product_unit').combobox({
			url : global_param.context_name + "/system/combobox/lists?pid=132",
			method :"get",
			valueField : "cbs_id",
			textField : "cbs_chn",
			editable:false,
		});
		
		$('#product_packingunit').combobox({
			url : global_param.context_name + "/system/combobox/lists?pid=125",
			method :"get",
			valueField : "cbs_id",
			textField : "cbs_chn",
			editable:false,
		});
		
		$('#product_approvalname').combobox({	
			url : global_param.context_name + "/system/combobox/lists?pid=145",
			method :"get",
			valueField : "cbs_id",
			textField : "cbs_chn",
			editable:false,
		});
		
		$('#product_taxrate').combobox({
			url : global_param.context_name + "/system/combobox/lists?pid=140",
			method :"get",
			valueField : "cbs_id",
			textField : "cbs_chn",
			editable:false,
		});
		
		$('#product_quanlitystandard').combobox({
			url : global_param.context_name + "/system/combobox/lists?pid=340",
			method :"get",
			valueField : "cbs_id",
			textField : "cbs_chn",
			editable:false,
		});
		
		$('#product_storeplace').combobox({
			url : global_param.context_name + "/system/combobox/lists?pid=342",
			method :"get",
			valueField : "cbs_id",
			textField : "cbs_chn",
			editable:false,
		});
		
		$('#product_storagecondition').combobox({
			url : global_param.context_name + "/system/combobox/lists?pid=344",
			method :"get",
			valueField : "cbs_id",
			textField : "cbs_chn",
			editable:false,
		});
		 
		$('#product_printtype').combobox({
			url : global_param.context_name + "/system/combobox/lists?pid=347",
			method :"get",
			valueField : "cbs_code",
			textField : "cbs_chn",
			editable:false,
		});
		
		$('#tab-pro').tabs({
			//fit:true,
			width : 600,
			onSelect : function() {
			}
		});
		
		util.selectTree({
			id : 'product_category',
			url : global_param.context_name + "/system/combobox/listTree?type=product_tree",
			method : 'get',
			idField : 'cbs_id',
			textField : 'cbs_chn',
			parentField : 'cbs_pid',
			required:'true',
			onHidePanel:function(){
				//向后台请求编号
				var node = $(this).combotree("tree").tree("getSelected");
				var product_code = node.cbs_code;
				
				var parent = $(this).combotree("tree").tree("getParent",node.target);
				while(parent){
					product_code = parent.cbs_code +""+ product_code;
					parent = $(this).combotree("tree").tree("getParent",parent.target);
				}
				var result = util.get(global_param.context_name + "/product/proInfoManage/getMaxProductCode?ticketNumber="+product_code);
				$("#product_code").val(result);
				$("#product_barcode").val(result);
			},
			onLoadSuccess:function(){
				//如果添加前选择了分类目录，而且文本里面没有内容,则自动填充目录
				if($("#product_category").val()==""||$("#product_category").val()==null)
					$("#product_category").combotree("setValue",$("#id").val());
			}
		});
		
		/*其他证照的显示*/
		// 初始化表格
		$("#licenseDg").datagrid({
			method : 'get',
			url : "", // 数据来源
			title : '证照信息管理', // 标题
			sortName : 'license_id', // 排序的列
			sortOrder : 'asc', // 排序方式
			fitColumns : true,
			singleSelect : true, // 单选
			pageSize : 10,
			pageList : [10, 20, 50, 100],
			fit : true,
			width : '570px',
			height : '600px',
			//striped : true, // 奇偶行颜色不同
			pagination : false,  // 显示分页
			rownumbers : true, // 显示行号
			nowrap : true,
			striped : true, // 奇偶行颜色不同
			queryParams : {},
			columns:[[{
				field : 'license_name',
				title : '证照名称',
				width : 80,
				sortable : true
			}, {
				field : 'license_no',
				title : '证照编号',
				width : 80,
				sortable : true
			}, {
				field : 'license_starttime',
				title : '认证时间',
				width : 80,
				sortable : true,
				formatter : function (value, row, index) {
					return util.formatDate(value);}
			}, {
				field : 'license_endtime',
				title : '有效期至',
				width : 80,
				sortable : true,
				formatter : function (value, row, index) {
					return util.formatDate(value);}
			}, {
				field : 'license_tipdays',
				title : '提示天数',
				width : 80,
				sortable : true
			}, {
				field : 'license_issuer',
				title : '发证机关',
				width : 80,
				sortable : true
			}, {
				field : 'license_remark',
				title : '备注',
				width : 80,
				sortable : true
			}, {
				field : 'license_other',
				title : '其他',
				width : 100,
				sortable : true
			}]],
			toolbar : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : addLicenseFunc
			}, {
				text : '修改',
				iconCls : 'icon-modify',
				handler : updateLicenseFunc
			}, {
				text : '删除',
				iconCls : 'icon-delete',
				handler : deleteLicenseFunc
			} ]
		});
		
		/*其他图片的显示*/
		// 初始化表格
		$("#pictureDg").datagrid({
			method : 'get',
			url : "", // 数据来源
			title : '图片信息管理', // 标题
			sortName : 'picture_id', // 排序的列
			sortOrder : 'asc', // 排序方式
			fitColumns : true,
			singleSelect : true, // 单选
			pageSize : 10,
			pageList : [10, 20, 50, 100],
			fit : true,
			width : '570px',
			height : '600px',
			pagination : false, // 显示分页
			rownumbers : true, // 显示行号
			nowrap : true,
			striped : true, // 奇偶行颜色不同
			queryParams : {},
			columns:[[{
				field : 'picture_name',
				title : '图片名称',
				width : 100,
				sortable : true
			}, {
				field : 'picture_position',
				title : '图片位置',
				width : 100,
				sortable : true
			}, {
				field : 'picture_remark',
				title : '图片备注',
				width : 100,
				sortable : true
			}]],
			toolbar : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : addPictureFunc
			}, {
				text : '修改',
				iconCls : 'icon-modify',
				handler : updatePictureFunc
			}, {
				text : '删除',
				iconCls : 'icon-delete',
				handler : deletePictureFunc
			} ]
		});
		
		
		
		keyPress.setTabChangeID(tabID, tabChangID);
		keyPress.bindKeyPressToCtrl(ctrlsID, sumbitButtonID);
		
		
		//如果是修改，则加载图片
		if($("#product_picture").val()!=""){
			var index=$("#product_picture").val().indexOf("HRERP");
			var subString=$("#product_picture").val().substring(index-1);
			var url=subString.replace(/\\/g,"/");//将斜杠\全部替换为/
			document.getElementById("preview1").style.display="block";//将隐藏图片显示
			$("#preview1").attr("src",url);
		}
		
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
		var fileIds = ["file_upload_product_picture"];
		var inputIds = ["product_picture"];
		
		submitAll(fileIds,inputIds);
	}
	//提交表单和图片
	function submitAll(fileIds,inputIds){
		var f = new Array();
		var is = new Array();
		//将需要提交的图片提取出来
		for(var i =0;i<fileIds.length;i++){
			if($("#"+fileIds[i]).val()!=""){
				f.push(fileIds[i]);
				is.push(inputIds[i]);
			}
		}
		//若没有图片提交，则直接提交数据
		if(f.length==0){
			
			submitData();
			return;
		}
			
		//如果有图片提交，则分别提交图片
		for(var j=0;j<f.length;j++){
			uploadImg(f[j],is[j],f.length);
		}
	}
	//上传图片
	function uploadImg(fileId,inputId,number){
		jQuery.ajaxFileUpload({
			url : global_param.context_name+'/FileManage/FileUpload', //处理上传文件的服务端
			secureuri : false, //与页面处理代码中file相对应的ID值
			fileElementId : fileId,//'file_upload_product_picture',
			dataType : 'text', //返回数据类型:text，xml，json，html,scritp,jsonp五种
			autoSubmit : true,
			success : function(result) {
				flag++;
				$("#"+inputId).val(result);//product_picture
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
		 
		
		
		if(!$('#_form').form('validate')){
			util.show("还有必填项未填写！");
			return;
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
	
	function addLicenseFunc() {
		if (!license_type_id) {
			util.show("只能对已有的产品添加其他证照信息");
			return;
		}
		util.openWindow('editLicenseWindow', "/HRERP/basInfo/basLicenseInfo/new?license_type_id="+license_type_id+"&license_type="+license_type);
	}
	
	function updateLicenseFunc() {
		var node = $('#licenseDg').datagrid('getSelected');
		if (!node) {
			util.show("请选择一条记录");
			return;
		}
		util.openWindow('editLicenseWindow', "/HRERP/basInfo/basLicenseInfo/" + node.license_id);
	}

	function deleteLicenseFunc() {
		var node = $('#licenseDg').datagrid('getSelected');
		if (!node) {
			util.show("请选择一条记录");
			return;
		}
		util.confirm("确定要执行删除操作？", function() {
			var data = util.del(global_param.context_name + "/basInfo/basLicenseInfo/" + node.license_id);
			if (data.result == "success") {
				$('#licenseDg').datagrid('reload');
			}
			util.show(data.message);
		});
	}
	
	// 初始化编辑窗口
	util.window('editLicenseWindow', {
		title : '证照信息'
	});
	
	function addPictureFunc() {
		if (!picture_type_id) {
			util.show("只能对已有的产品添加其他图片信息");
			return;
		}
		util.openWindow('editPictureWindow', "/HRERP/basInfo/basPictureInfo/new?picture_type_id="+picture_type_id+"&picture_type="+picture_type);
	}
	
	function updatePictureFunc() {
		var node = $('#pictureDg').datagrid('getSelected');
		if (!node) {
			util.show("请选择一条记录");
			return;
		}
		util.openWindow('editPictureWindow', "/HRERP/basInfo/basPictureInfo/" + node.picture_id);
	}

	function deletePictureFunc() {
		var node = $('#pictureDg').datagrid('getSelected');
		if (!node) {
			util.show("请选择一条记录");
			return;
		}
		util.confirm("确定要执行删除操作？", function() {
			var data = util.del(global_param.context_name + "/basInfo/basPictureInfo/" + node.picture_id);
			if (data.result == "success") {
				$('#pictureDg').datagrid('reload');
			}
			util.show(data.message);
		});
	}
	function perviewPrint(){
		var printType = $("#product_printtype").combo('getValue');
		
		if(printType==""){
			util.show("请选择打印格式");
		}else{
			var node = {
					text : '产品报表',
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
	// 初始化编辑窗口
	util.window('editPictureWindow', {
		title : '图片信息'
	});
	
	