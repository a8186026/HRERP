//初始化文件
/**
 * 初始化文件，如果存在文件时显示在页面上
 * @param fileIds 文件id
 * @param id 文件选择框id
 * @param onlyOneFile  是否单文件
 * @param entId   页面文件隐藏框id
 * @param buttonId   页面上传按钮id
 * @param callBack   完成后执行的函数，参数会返回到此函数中
 */
function initFile(fileIds,id,onlyOneFile,entId,buttonId,displayField,callBack){
	if(fileIds==null||fileIds==""){
		return;
	}
	displayField = displayField || 'fileRecord';
	var idArray ="";
	//是id数组，处理成字符串
	if((typeof fileIds=='object')&&fileIds.constructor==Array){
		idArray = fileIds;
		for (var int = 0; int < fileIds.length; int++) {
			if(idArray==""){
				idArray = fileIds[i];
			}else{
				idArray = idArray+","+idArray[i];
			}
			
		}
	}
	//是以“,”隔开的id字符串时不处理
	if( (typeof fileIds=='string')&&fileIds.constructor==String){
		idArray = fileIds;
	}
	//初始化文件id
	var fileIdArray = [];
	$.ajax({
		url : global_param.context_name + "/fileManager/fileQueryByIds",
		data : "ids=" + idArray,
//		async:false,
		success : function(json) {
			for (var j = 0; j < json.length; j++) {
				var data = json[j];
				//将文件id设置到页面
				
				$("#" + displayField).append(
						"<p style=\"margin:0px;\"><span><a href='" + global_param.context_name + "/fileManager/fileDownload?id=" + data.id + "'>" + decodeURI(data.oldFileName)
						+ "</a></span>&nbsp&nbsp&nbsp<span><a href='#' onclick='fileDelete(this," + data.id + ",\""+id+"\",\""+buttonId+"\",\""+entId+"\")'>删除</a></span></p>");
				fileIdArray.push(data.id);
			}
			if (json.length > 0) {
				//如果是单个文件并且已经存在文件，则禁用按钮
				if(onlyOneFile&&fileIds!=null&&fileIds!=""){
					$("#"+id).css('display','none');
					$("#"+buttonId).css('display','none');
				}
			}
			if(callBack){
				callBack($("#"+entId).val());
			}
		}
	});
}

function getFile(id) {
	if (!id) {
		return;
	}
	var myfile = null;
	$.ajax({
		url : global_param.context_name + "/fileManager/fileQueryByIds",
		data : "ids=" + id,
		async:false,
		success : function(json) {
			if (json.length == 1) {
				myfile = json[0];
			}
		}
	});
	return myfile;
}

/**
 * 
 * @param id   文件选择框id
 * @param onlyOneFile  是否为单文件
 * @param buttonId    上传按钮id
 * @param hiddenId    文件隐藏域id用来保存文件id（必填）
 * @param callBack
 */
// 上传
function fileUpload(id,onlyOneFile,buttonId,hiddenId,displayField,callBack) {
	if($("#"+id).val()==""||$("#"+id).val()==null){
		return;
	}
	displayField = displayField || "fileRecord";
	$.ajaxFileUpload({
		url : global_param.context_name + "/fileManager/fileUpload", // 需要链接到服务器地址
		secureuri : true,
		fileElementId : id, // 文件选择框的id属性
		dataType : 'json', // 服务器返回的格式
		success : function(data, status) {
			if(onlyOneFile){
				$("#"+id).css('display','none');
				$("#"+buttonId).css('display','none');
			}
			//将文件id设置到页面
			if($("#"+hiddenId).val()==null||$("#"+hiddenId).val()==""){
				$("#"+hiddenId).val(data.id);
			}else{
				$("#"+hiddenId).val(data.id);
			}
			//展示已经上传的文件
			$("#" + displayField).append(
					"<p style=\"margin:0px;\"><span><a href='" + global_param.context_name + "/fileManager/fileDownload?id=" + data.id + "'>" + decodeURI(data.oldFileName)
							+ "</a></span>&nbsp&nbsp&nbsp<span><a href='#' onclick='fileDelete(this," + data.id + ",\""+id+"\",\""+buttonId+"\",\""+hiddenId+"\")'>删除</a></span></p>");
			if (callBack) {callBack(data.id)};
		},
		error : function(data, status, e) {
			util.alert("上传过程中发生错误!");
		}
	});
}
//上传
function fileDownload(fileIds,onlyOneFile,entId,displayField,callBack){
	if(fileIds==null||fileIds==""){
		return;
	}
	displayField = displayField || "fileRecord";
	var idArray ="";
	//是id数组，处理成字符串
	if((typeof fileIds=='object')&&fileIds.constructor==Array){
		idArray = fileIds;
		for (var int = 0; int < fileIds.length; int++) {
			if(idArray==""){
				idArray = fileIds[i];
			}else{
				idArray = idArray+","+idArray[i];
			}
			
		}
	}
	//是以“,”隔开的id字符串时不处理
	if( (typeof fileIds=='string')&&fileIds.constructor==String){
		idArray = fileIds;
	}
	//初始化文件id
	var fileIdArray = [];
	$.ajax({
		url : global_param.context_name + "/fileManager/fileQueryByIds",
		data : "ids=" + idArray,
//		async:false,
		success : function(json) {
			for (var j = 0; j < json.length; j++) {
				var data = json[j];
				
				$("#" + displayField).append(
						"<p style=\"margin:0px;\"><span><a href='" + global_param.context_name + "/fileManager/fileDownload?id=" + data.id + "'>" + decodeURI(data.oldFileName)
						+ "</a></span></p>");
				fileIdArray.push(data.id);
			}
			if (json.length > 0) {
				//如果是单个文件并且已经存在文件，则禁用按钮
				if(onlyOneFile&&fileIds!=null&&fileIds!=""){
				}
			}
			if(callBack){
				callBack($("#"+entId).val());
			}
		}
	});
}
/**
 * 
 * @param event  事件
 * @param id   需要删除的文件的id
 * @param entId  文件上传框的id
 * @param buttonId   上传按钮的id
 */
function fileDelete(event, id, entId, buttonId, hiddenId) {
	util.confirm("确定要删除文件吗？", function() {
		$.ajax({
			url : global_param.context_name + "/fileManager/fileDelete",
			data : "id=" + id,
			success : function(html) {
				event.parentNode.parentNode.outerHTML = "";
				$("#"+hiddenId).val('');
				//取消禁用按钮和上传文件框
				$("#"+entId).removeAttr('style');
				$("#"+buttonId).removeAttr('style');
			}
		});
	});
}
/**
 * 文件上传限制（格式和大小）
 * @param target
 */
var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
function fileChange(target, type) {
	var fileSize = 0;
	var filetypes = type ? [type] : [ ".doc" ];
	var filepath = target.value;
	var filemaxsize = 1024 * 2; // 2M
	if (filepath) {
		var isnext = false;
		var fileend = filepath.substring(filepath.lastIndexOf(".")).toLowerCase();
		if (filetypes && filetypes.length > 0) {
			for (var i = 0; i < filetypes.length; i++) {
				if (filetypes[i] == fileend) {
					isnext = true;
					break;
				}
			}
		}
		if (!isnext) {
			util.show("只接收\"" + filetypes.join(",") + "\"类型的文件，请重新选择！");
			target.value = "";
			return false;
		}
	} else {
		return false;
	}
	if (isIE && !target.files) {
		var filePath = target.value;
		var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
		if (!fileSystem.FileExists(filePath)) {
			util.show("附件不存在，请重新选择！");
			return false;
		}
		var file = fileSystem.GetFile(filePath);
		fileSize = file.Size;
	} else {
		fileSize = target.files[0].size;
	}

	var size = fileSize / 1024;
	if (size > filemaxsize) {
		util.show("附件大小不能大于" + filemaxsize / 1024 + "M！请重新选择！");
		target.value = "";
		return false;
	}
	if (size <= 0) {
		util.show("附件大小不能为0！请重新选择！");
		target.value = "";
		return false;
	}
}