<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/chnToLetter.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/product/proInfoManageEdit.js"></script>
<script type="text/javascript">
//加载其他证照信息
var license_type_id = '${proInfoManage.product_id}';
var license_type = 'product';
if(license_type_id!=null&&license_type_id!=""){
	$('#licenseDg').datagrid('options').url =  global_param.context_name + 
	"/basInfo/basLicenseInfo/lists?license_type_id="+license_type_id+"&license_type="+license_type;
	$('#licenseDg').datagrid('reload');
}

var picture_type_id = '${proInfoManage.product_id}';
var picture_type = 'product';
if(picture_type_id!=null&&picture_type_id!=""){
	$('#pictureDg').datagrid('options').url =  global_param.context_name + 
	"/basInfo/basPictureInfo/lists?picture_type_id="+picture_type_id+"&picture_type="+picture_type;
	$('#pictureDg').datagrid('reload');
}
function selectFromOther(){
	var type = $('#product_commonnametype').combo('getValue');
	if(type==2){
		if($("#product_commonname").val()=="%")
			selectMedName('setMedName','');
		else if($("#product_commonname").val()!="")
			selectMedName('setMedName',$("#product_commonname").val());
	}
	if(type==3&&$("#product_commonname").val()=="%"){
		selectComName('setComName');
	}
	
}

function selectMedName(callback,content) {
	// 创建窗口
	function createMedNameChooseWin() {
		if ($("#medNameChooseWin").length == 0) {
			jQuery('<div id="medNameChooseWin" callback="' + callback + '"></div>').appendTo(document.body);
		}
		$("#medNameChooseWin").window({
			title : '品名库选择',
			width : 1120,
			height : 590,
			closed : true,
			minimizable : false,
			maximizable : false,
			collapsible : false,
			resizable : false,
			content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/product/proInfoManage/medNameChoose?callback=' + callback + '&content='+content+'"></iframe>'
		});
	}
	createMedNameChooseWin();
	$('#medNameChooseWin').window('open');
}

function setMedName(node){
	if(node!=null){
		$("#product_commonname").val(node.medicine_name);//通用名
		//设置剂型
		$("#product_proname").val(node.medicine_proname);//商品名
		chnToLetter($("#product_chnpy").val(),"product_chnpy");//设置拼音码
		$("#product_description").val(node.medicine_description);//产地
		 
	}else{
		$("#product_commonname").val("");
		$("#product_proname").val("");
		$("#product_description").val("");
	}
}

function selectComName(callback) {
	// 创建窗口
	function createComNameChooseWin() {
		if ($("#comNameChooseWin").length == 0) {
			jQuery('<div id="comNameChooseWin" callback="' + callback + '"></div>').appendTo(document.body);
		}
		$("#comNameChooseWin").window({
			title : '药监局选择',
			width : 1120,
			height : 590,
			closed : true,
			minimizable : false,
			maximizable : false,
			collapsible : false,
			resizable : false,
			content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/product/proInfoManage/comNameChoose?callback=' + callback + '"></iframe>'
		});
	}
	createComNameChooseWin();
	$('#comNameChooseWin').window('open');
}

function setComName(node){
	if(node!=null){
		$("#product_commonname").val(node.eth_tym);//通用名
		//设置剂型
		var dosagetypes = $("#product_dosagetype").combobox("getData");
		for(var i=0;i<dosagetypes.length;i++){
			if(node.eth_Form==dosagetypes[i].cbs_chn)
				$("#product_dosagetype").combobox("setValue",dosagetypes[i].cbs_id);
		}
		$("#product_proname").val(node.eth_spm);//商品名
		chnToLetter($("#product_chnpy").val(),"product_chnpy");//设置拼音码
		$("#product_specification").val(node.eth_gg);//规格
		//设置单位
		var units = $("#product_unit").combobox("getData");
		for(var i=0;i<units.length;i++){
			if(node.eth_dw==units[i].cbs_chn)
				$("#product_unit").combobox("setValue",units[i].cbs_id);
		}
		$("#product_productarea").val(node.eth_sccj);//产地
		$("#product_factoryname").val(node.eth_sccj);//生产厂家
		chnToLetter($("#product_factoryname").val(),"product_factorychnpy");//生产厂家拼音码
		
		//去除批准文号中的非汉字字符
		var an =node.eth_pzwh.replace(/[^\u4e00-\u9fa5]/g,"");
		
		//设置批准文号
		var approvalnums = $("#product_approvalname").combobox("getData");
		for(var i=0;i<approvalnums.length;i++){
			if(an==approvalnums[i].cbs_chn)
				$("#product_approvalname").combobox("setValue",approvalnums[i].cbs_id);
		}
		$("#product_approvalnum").val(node.eth_pzwh);//药品批准文号
		$("#product_dsurveillanceid").val(node.eth_Id);//药品ID
		$("#product_dsurveillanceno").val(node.eth_UnitCode);//药品编号
	}else{
		$("#product_commonname").val("");
		$("#product_dosagetype").val("");
		$("#product_proname").val("");
		$("#product_specification").val("");
		$("#product_unit").val("");
		$("#product_productarea").val("");
		$("#product_factoryname").val("");
		$("#product_approvalnum").val("");
		$("#product_dsurveillanceid").val("");
		$("#product_dsurveillanceno").val("");
	}
}

function setCategory(nodes){
	if(!nodes){
 		$("#product_managecetagory").val("");
 		return;
	}
	var category = "";
	for(var i=0;i<nodes.length-1;i++){
		category = category + nodes[i].cbs_chn+","; 			
	}
	category = category + nodes[nodes.length-1].cbs_chn;
	$("#product_managecetagory").val(category);
}	

function selectCategory(callback,inputObject) {
	// 创建窗口
	function createCategoryChooseWin() {
		if ($("#categoryChooseWin").length == 0) {
			jQuery('<div id="categoryChooseWin" callback="' + callback + '"></div>').appendTo(document.body);
		}
		$("#categoryChooseWin").window({
			title : '经营分类选择',
			width : 500,
			height : 500,
			closed : true,
			minimizable : false,
			maximizable : false,
			collapsible : false,
			resizable : false,
			content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/product/proInfoManage/category?callback=' + callback + '&value='+$(inputObject).val()+'"></iframe>'
		});
	}
	createCategoryChooseWin();
	$('#categoryChooseWin').window('open');
}
</script>

<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
			
			<input id="id" type="hidden" value="${id}">
			
			<form:form id="_form" action="${formUrl}" method="${method}" onsubmit="return false;">
				<input name="product_id" type="hidden" value="${proInfoManage.product_id}" />
				<input name="SPRING_TOKEN" type="hidden" value="${sessionScope.SPRING_TOKEN}" />
	
					<div id="tab-pro" class="easyui-tabs" style="height:715px">   
						
				        <div title="基本信息" id="tab-pro_1" > 
				        <div style="border:none;position:relative;top:0px;margin-top:10px;margin-bottom:5px;margin-left:5px;width:570px">基本信息</div>
				        	<div style="width:570px;height:650px;margin-left:5px;border:1px solid #CCCCCC">
				        	<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_commonnametype" class="field">通用名方式：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_commonnametype" name="product_commonnametype" class="combobox"
										data-options="required:true" style="width:152px" value="${proInfoManage.product_commonnametype}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_commonname" class="field">通用名：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_commonname" name="product_commonname" class="easyui-validatebox"
										 value="${proInfoManage.product_commonname}" onblur="selectFromOther()"/>
								</div>
							</div>
				        	<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_name" class="field">产品名称：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_name" name="product_name" class="easyui-validatebox"
										value="${proInfoManage.product_name}"  onblur="chnToLetter($(this).val(),'product_chnpy')" 
										data-options="required:true,validType:'length[1,20]'"/>
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_code" class="field">产品编号：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_code" name="product_code" class="easyui-validatebox"
										data-options="required:true,validType:'length[1,20]'"  value="${proInfoManage.product_code}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_barcode" class="field">产品条码：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_barcode" name="product_barcode" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${proInfoManage.product_barcode}" />
								</div>
							</div>           
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_filingtime" class="field">建档日期：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_filingtime" name="product_filingtime"  style="width:152px"
										data-options="required:true"  value="${proInfoManage.product_filingtime}" />
								</div>
							</div>
						 	<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_category" class="field">分类：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_category" name="product_category" class="combotree" style="width:152px"
										 data-options="required:true" value="${proInfoManage.product_category}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_othercategory" class="field">其他分类：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_othercategory" name="product_othercategory" class="combobox" style="width:152px"
										 data-options="required:true" value="${proInfoManage.product_othercategory}" />
								</div>
							</div>
						<%-- 	 <div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_category2" class="field">分类2：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_category2" name="product_category2" class="combobox" style="width:152px"
										 data-options="required:true" value="${proInfoManage.product_category2}" />
								</div>
							</div>	 --%>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_othercategory2" class="field">其他分类2：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_othercategory2" name="product_othercategory2" class="combobox" style="width:152px"
										 data-options="required:true" value="${proInfoManage.product_othercategory2}" />
								</div>
							</div>	
						<%-- 	<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_category3" class="field">分类3：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_category3" name="product_category3" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${proInfoManage.product_category3}" />
								</div>
							</div>	 --%>
							
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_othercategory3" class="field">其他分类3：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_othercategory3" name="product_othercategory3" class="combobox" style="width:152px"
										 data-options="required:true" value="${proInfoManage.product_othercategory3}" />
								</div>
							</div>	
							<%-- <div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_category4" class="field">分类4：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_category4" name="product_category4" class="easyui-validatebox"
										 data-options="validType:'length[1,20]'" value="${proInfoManage.product_category4}" />
								</div>
							</div>	 --%>
								<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_othercategory4" class="field">其他分类4：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_othercategory4" name="product_othercategory4" class="combobox" style="width:152px"
										 data-options="required:true" value="${proInfoManage.product_othercategory4}" />
								</div>
							</div>	
							<%-- <div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_category5" class="field">分类5：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_category5" name="product_category5" class="easyui-validatebox"
										data-options="validType:'length[1,20]'" value="${proInfoManage.product_category5}" />
								</div>	
							</div>	 --%>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_othercategory5" class="field">其他分类5：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_othercategory5" name="product_othercategory5" class="combobox" style="width:152px"
										 data-options="required:true" value="${proInfoManage.product_othercategory5}" />
								</div>
							</div>	
							
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_dosagetype" class="field">剂型：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_dosagetype" name="product_dosagetype" class="combobox" style="width:152px"
										 data-options="required:true" value="${proInfoManage.product_dosagetype}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_proname" class="field">商品名：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_proname" name="product_proname" class="easyui-validatebox"
										 data-options="validType:'length[1,20]'"  value="${proInfoManage.product_proname}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_chnpy" class="field">拼音码：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_chnpy" name="product_chnpy" class="combobox" style="width:152px;"
										data-options="required:true" value="${proInfoManage.product_chnpy}" />				
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_specification" class="field">规格：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_specification" name="product_specification" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${proInfoManage.product_specification}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_unit" class="field">单位：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_unit" name="product_unit" class="combobox" style="width:152px"
										 data-options="required:true" value="${proInfoManage.product_unit}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_productarea" class="field">产地：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_productarea" name="product_productarea" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${proInfoManage.product_productarea}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_factoryname" class="field">生产厂家：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_factoryname" name="product_factoryname" class="easyui-validatebox" 
										value="${proInfoManage.product_factoryname}"  onblur="chnToLetter($(this).val(),'product_factorychnpy')" 
										data-options="required:true,validType:'length[1,20]'"/>
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_factorychnpy" class="field">厂家拼音码：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_factorychnpy" name="product_factorychnpy" class="combobox" style="width:152px;"
										data-options="required:true" value="${proInfoManage.product_factorychnpy}" />	
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_approvalnum" class="field">批准文号：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_approvalname" name="product_approvalname" class="combobox" 
										data-options="required:true" style="width:152px" value="${proInfoManage.product_approvalname}" />
									<input id="product_approvalnum" name="product_approvalnum" class="easyui-validatebox"  
										  value="${proInfoManage.product_approvalnum}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_dsurveillanceid" class="field">药监ID：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_dsurveillanceid" name="product_dsurveillanceid" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${proInfoManage.product_dsurveillanceid}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_dsurveillanceno" class="field">药监编号：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_dsurveillanceno" name="product_dsurveillanceno" class="easyui-validatebox"
										 data-options="validType:'length[1,20]'"  value="${proInfoManage.product_dsurveillanceno}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_taxrate" class="field">税率：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_taxrate" name="product_taxrate" class="combobox" style="width:152px"
										 data-options="required:true" value="${proInfoManage.product_taxrate}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_procedure" class="field">手续：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_procedure" name="product_procedure" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${proInfoManage.product_procedure}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_middlepacking" class="field">中包量：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_middlepacking" name="product_middlepacking" class="easyui-validatebox"
										 data-options="validType:'length[1,20]'"  value="${proInfoManage.product_middlepacking}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_packingamount" class="field">包装量：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_packingamount" name="product_packingamount" class="easyui-validatebox"
										data-options="required:true"  value="${proInfoManage.product_packingamount}" 
										onfocus="if (value==defaultValue)value='1'" onblur="if(!value)value=defaultValue" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_packingunit" class="field">包装单位：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_packingunit" name="product_packingunit" class="combobox" style="width:152px"
										 data-options="required:true" value="${proInfoManage.product_packingunit}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_cartonsize" class="field">装箱规格：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_cartonsize" name="product_cartonsize" class="easyui-validatebox"
										 data-options="validType:'length[1,20]'"  value="${proInfoManage.product_cartonsize}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_shiphandlingnum" class="field">配送包装量：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_shiphandlingnum" name="product_shiphandlingnum" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${proInfoManage.product_shiphandlingnum}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_articleno" class="field">货号：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_articleno" name="product_articleno" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${proInfoManage.product_articleno}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_groupno" class="field">组号：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_groupno" name="product_groupno" class="easyui-validatebox"
										 data-options="validType:'length[1,20]'"  value="${proInfoManage.product_groupno}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_regtrademark" class="field">注册商标：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_regtrademark" name="product_regtrademark" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${proInfoManage.product_regtrademark}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_buyer" class="field">采购员：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_buyer" name="product_buyer" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${proInfoManage.product_buyer}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_description" class="field">简介：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_description" name="product_description" class="easyui-validatebox"
										 data-options="validType:'length[1,20]'"  value="${proInfoManage.product_description}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_basremark" class="field">基本备注：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_basremark" name="product_basremark" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${proInfoManage.product_basremark}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_amountFormat" class="field">价格格式：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_amountFormat" name="product_amountFormat" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${proInfoManage.product_amountFormat}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_numberFormat" class="field">数量格式：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_numberFormat" name="product_numberFormat" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${proInfoManage.product_numberFormat}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_moneyPerPoint" class="field">记分金额：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_moneyPerPoint" name="product_moneyPerPoint" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${proInfoManage.product_moneyPerPoint}" />
								</div>
							</div>
								
						</div>	
							<div style="border:none;position:relative;top:0px;margin-top:10px;margin-bottom:5px;margin-left:5px;width:570px">勾选信息</div>
				        	<div style="width:570px;height:100px;margin-left:5px;border:1px solid #CCCCCC">
				        	<div class="float-l">
				        		<div class="SelectAll">
				        			<input name="product_cancel" type ="hidden" value="${proInfoManage.product_cancel}" />
									<input id="product_cancel" type ="checkbox" style="width:20px" />
									<label for="product_cancel">注销</label>
								</div>
							</div>
							 <div class="float-l">
				        		<div class="SelectAll">
				        		<input name="product_other" type ="hidden" value="${proInfoManage.product_other}" />
									<input id="product_other" type ="checkbox" style="width:20px" />
									<label for="product_other">其他</label>
								</div>
							</div>
						 
							 <div class="float-l">
				        		<div class="SelectAll">
				        			<input name="product_other1" type ="hidden" value="${proInfoManage.product_other1}" />
									<input id="product_other1" type ="checkbox" style="width:20px" />
									<label for="product_other1">其他1</label>
								</div>
							</div>  
						 	<div class="float-l">
				        		<div class="SelectAll">
				        			<input name="product_other2" type ="hidden" value="${proInfoManage.product_other2}" />
									<input id="product_other2" type ="checkbox" style="width:20px" />
									<label for="product_other2">其他2</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_other3" type ="hidden" value="${proInfoManage.product_other3}" />
									<input id="product_other3" type ="checkbox" style="width:20px" />
									<label for="product_other3">其他3</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_consignmentvariety" type ="hidden" value="${proInfoManage.product_consignmentvariety}" />
									<input id="product_consignmentvariety" type ="checkbox" style="width:20px" />
									<label for="product_consignmentvariety">代销品种</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_statisticsvariety" type ="hidden" value="${proInfoManage.product_statisticsvariety}" />
									<input id="product_statisticsvariety" type ="checkbox" style="width:20px" />
									<label for="product_statisticsvariety">统计品种</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_cashsettlevariety" type ="hidden" value="${proInfoManage.product_cashsettlevariety}" />
									<input id="product_cashsettlevariety" type ="checkbox" style="width:20px" />
									<label for="product_cashsettlevariety">现结品种</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_essentialvar" type ="hidden" value="${proInfoManage.product_essentialvar}" />
									<input id="product_essentialvar" type ="checkbox" style="width:20px" />
									<label for="product_essentialvar">必备品种</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_bidvariety" type ="hidden" value="${proInfoManage.product_bidvariety}" />
									<input id="product_bidvariety" type ="checkbox" style="width:20px" />
									<label for="product_bidvariety" >中标品种</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_medinsuvariety" type ="hidden" value="${proInfoManage.product_medinsuvariety}" />
									<input id="product_medinsuvariety" type ="checkbox" style="width:20px" />
									<label for="product_medinsuvariety">医保品种</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_acvariety" type ="hidden" value="${proInfoManage.product_acvariety}" />
									<input id="product_acvariety" type ="checkbox" style="width:20px" />
									<label for="product_acvariety">农合品种</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_promotionvariety" type ="hidden" value="${proInfoManage.product_promotionvariety}" />
									<input id="product_promotionvariety" type ="checkbox" style="width:20px" />
									<label for="product_promotionvariety">促销品种</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_specialvariety" type ="hidden" value="${proInfoManage.product_specialvariety}" />
									<input id="product_specialvariety" type ="checkbox" style="width:20px" />
									<label for="product_specialvariety">特殊品种</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_prescriptiondrug" type ="hidden" value="${proInfoManage.product_prescriptiondrug}" />
									<input id="product_prescriptiondrug" type ="checkbox" style="width:20px" />
									<label for="product_prescriptiondrug">处方药</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_nationalbasmed" type ="hidden" value="${proInfoManage.product_nationalbasmed}" />
									<input id="product_nationalbasmed" type ="checkbox" style="width:20px" />
									<label for="product_nationalbasmed">国家基药 </label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_basmedicine" type ="hidden" value="${proInfoManage.product_basmedicine}" />
									<input id="product_basmedicine" type ="checkbox" style="width:20px" />
									<label for="product_basmedicine">基本药物</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_emergencymed" type ="hidden" value="${proInfoManage.product_emergencymed}" />
									<input id="product_emergencymed" type ="checkbox" style="width:20px" />
									<label for="product_emergencymed">急救药品</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_instockinvoice" type ="hidden" value="${proInfoManage.product_instockinvoice}" />
									<input id="product_instockinvoice" type ="checkbox" style="width:20px" />
									<label for="product_instockinvoice">入库发票</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_outstockinvoice" type ="hidden" value="${proInfoManage.product_outstockinvoice}" />
									<input id="product_outstockinvoice" type ="checkbox" style="width:20px" />
									<label for="product_outstockinvoice">出库发票</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_mbernointegral" type ="hidden" value="${proInfoManage.product_mbernointegral}" />
									<input id="product_mbernointegral" type ="checkbox" style="width:20px" />
									<label for="product_mbernointegral">会员不积分</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_membernodis" type ="hidden" value="${proInfoManage.product_membernodis}" />
									<input id="product_membernodis" type ="checkbox" style="width:20px" />
									<label for="product_membernodis">会员不打折</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_containspecialmed" type ="hidden" value="${proInfoManage.product_containspecialmed}" />
									<input id="product_containspecialmed" type ="checkbox" style="width:20px" />
									<label for="product_containspecialmed">含特药复方制剂</label>
								</div>
							</div>  
							 
						</div>   
				     </div> 			        
				         
				        <div title="价格信息" id="tab-pro_2">
				         <div style="border:none;position:relative;top:0px;margin-top:10px;margin-bottom:5px;margin-left:5px;width:570px">批发价格设置：</div>
				        	<div style="width:570px;height:170px;margin-left:5px;border:1px solid #CCCCCC">
				        	<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_tradeprice" class="field">批发价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_tradeprice" name="product_tradeprice" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_tradeprice}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_wsalelprice" class="field">批发限价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_wsalelprice" name="product_wsalelprice" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_wsalelprice}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_lowsalelprice" class="field">批发最低价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_lowsalelprice" name="product_lowsalelprice" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_lowsalelprice}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_dealwsaleprice" class="field">协议批发价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_dealwsaleprice" name="product_dealwsaleprice" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_dealwsaleprice}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_saleprice1" class="field">销售价1：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_saleprice1" name="product_saleprice1" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_saleprice1}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_saleprice2" class="field">销售价2：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_saleprice2" name="product_saleprice2" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_saleprice2}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_saleprice3" class="field">销售价3：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_saleprice3" name="product_saleprice3" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_saleprice3}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_saleprice4" class="field">销售价4：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_saleprice4" name="product_saleprice4" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_saleprice4}" />
								</div>
							</div>
						    <div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_marketprice" class="field">市场价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_marketprice" name="product_marketprice" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_marketprice}" />
								</div>
							</div> 
				        	</div>
				        	
				        	
				        	<div style="border:none;position:relative;width:570px;margin-top:5px;margin-bottom:5px;margin-left:5px">零售价格设置：</div>
				        	<div style="width:570px;height:120px;margin-left:5px; border:1px solid #CCCCCC">
				        	<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_retailprice" class="field">零售价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_retailprice" name="product_retailprice" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_retailprice}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_retaillprice" class="field">零售限价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_retaillprice" name="product_retaillprice" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_retaillprice}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_lretaillprice" class="field">零售最低价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_lretaillprice" name="product_lretaillprice" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_lretaillprice}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_dealretailprice" class="field">协议零售价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_dealretailprice" name="product_dealretailprice" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_dealretailprice}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_memberprice" class="field">会员价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_memberprice" name="product_memberprice" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_memberprice}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_proretailprice" class="field">省标零售价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_proretailprice" name="product_proretailprice" class="easyui-validatebox" 
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_proretailprice}" />
								</div>
							</div>
				        	</div>
		
							<div style="border:none;position:relative;width:570px;margin-top:5px;margin-bottom:5px;margin-left:5px">中标价格设置：</div>
							<div style="width:570px;height:85px;margin-left:5px; border:1px solid #CCCCCC">
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_basmedbidprice1" class="field">基药中标价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_basmedbidprice1" name="product_basmedbidprice1" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_basmedbidprice1}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_basmedbidprice2" class="field">基药中标价2：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_basmedbidprice2" name="product_basmedbidprice2" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_basmedbidprice2}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_basmedbidprice3" class="field">基药中标价3：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_basmedbidprice3" name="product_basmedbidprice3" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_basmedbidprice3}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_bidprice" class="field">中标价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_bidprice" name="product_bidprice" class="easyui-validatebox"  
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_bidprice}" />
								</div>
							</div>
							</div>
							
							<div style="border:none;position:relative;width:570px;margin-top:5px;margin-bottom:5px;margin-left:5px">提成设置：</div>
							<div style="width:570px;height:85px;margin-left:5px; border:1px solid #CCCCCC">
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_retailcommission" class="field">零售提成：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_retailcommission" name="product_retailcommission" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_retailcommission}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_invoicecom" class="field">开票提成：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_invoicecom" name="product_invoicecom" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_invoicecom}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_wholesalecom" class="field">批发提成：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_wholesalecom" name="product_wholesalecom" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_wholesalecom}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_businesscom" class="field">业务提成：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_businesscom" name="product_businesscom" class="easyui-validatebox"  
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_businesscom}" />
								</div>
							</div>
							</div>
							
							<div style="border:none;position:relative;width:570px;margin-top:5px;margin-bottom:5px;margin-left:5px">价格其他：</div>
							<div style="width:570px;height:110px;margin-left:5px; border:1px solid #CCCCCC">
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_nationallprice" class="field">国家限价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_nationallprice" name="product_nationallprice" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_nationallprice}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_dealprice" class="field">协议进价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_dealprice" name="product_dealprice" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_dealprice}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_tinynum" class="field">拆零组数：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_tinynum" name="product_tinynum" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${proInfoManage.product_tinynum}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_dealcustomer" class="field">协议客户：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_dealcustomer" name="product_dealcustomer" class="easyui-validatebox" 
										data-options="validType:'length[1,20]'"  value="${proInfoManage.product_dealcustomer}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_priceremark" class="field">价格备注：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_priceremark" name="product_priceremark" class="easyui-validatebox"  
										data-options="validType:'length[1,20]'"  value="${proInfoManage.product_priceremark}" />
								</div>
							</div>
							</div>
				        </div>  
				        
				        <div title="证照信息" id="tab-pro_3">
				         <div style="border:none;position:relative;top:0px;margin-top:10px;margin-bottom:5px;margin-left:5px;width:570px">许可证：</div>
				        	<div style="width:570px;height:125px;margin-left:5px;border:1px solid #CCCCCC">
				        	 <div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_GMPcertify" class="field">企业GMP认证书：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_GMPcertify" name="product_GMPcertify" class="easyui-validatebox"
										data-options="validType:'length[1,20]'" value="${proInfoManage.product_GMPcertify}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_certificationtime" class="field">认证时间：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_certificationtime" name="product_certificationtime" style="width:152px"
										data-options="required:true" value="${proInfoManage.product_certificationtime}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_licensevalidtime" class="field">有效期至：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_licensevalidtime" name="product_licensevalidtime"  style="width:152px"
										data-options="required:true" value="${proInfoManage.product_licensevalidtime}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_remindday" class="field">提示天数：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_remindday" name="product_remindday" class="easyui-validatebox"
										data-options="validType:['num','nosp']" value="${proInfoManage.product_remindday}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_licenseremark" class="field">证照备注：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_licenseremark" name="product_licenseremark" class="easyui-validatebox"
										data-options="validType:'length[1,20]'" value="${proInfoManage.product_licenseremark}" />
								</div>
							</div>
				        	</div>
				        	
				        	<div style="border:none;position:relative;width:570px;margin-top:5px;margin-bottom:5px;margin-left:5px">其他证照信息：</div>
								
								<!-- 显示区域 -->
								<div style="height:400px">
									<table id="licenseDg"></table>
								</div>
								<!-- 编辑窗口 -->
							<div id="editLicenseWindow"></div>
					    </div>  
				        
				        <div title="质量其他相关" id="tab-pro_4"> 
				        <div style="border:none;position:relative;top:0px;margin-top:10px;margin-bottom:5px;margin-left:5px;width:570px">基本信息</div>
				        	<div style="width:570px;height:450px;margin-left:5px;border:1px solid #CCCCCC">           
				        	<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_filenumber" class="field">档案号：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_filenumber" name="product_filenumber" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${proInfoManage.product_filenumber}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_filelocation" class="field">档案位置：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_filelocation" name="product_filelocation" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${proInfoManage.product_filelocation	}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_quotanum" class="field">限购数量：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_quotanum" name="product_quotanum" class="easyui-validatebox"
										data-options="validType:['decimal','nosp']" value="${proInfoManage.product_quotanum}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_memberlimit" class="field">会员限价：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_memberlimit" name="product_memberlimit" class="easyui-validatebox" 
										data-options="validType:['decimal','nosp']" value="${proInfoManage.product_memberlimit}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_bidno" class="field">中标编号：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_bidno" name="product_bidno" class="easyui-validatebox" 
										value="${proInfoManage.product_bidno}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_bidno2" class="field">中标编号2：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_bidno2" name="product_bidno2" class="easyui-validatebox"
										value="${proInfoManage.product_bidno2}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_bidno3" class="field">中标编号3：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_bidno3" name="product_bidno3" class="easyui-validatebox"
										value="${proInfoManage.product_bidno3}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_licensevaliddate" class="field">质量有效期：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_licensevaliddate" name="product_licensevaliddate"  style="width:152px"
										data-options="required:true" value="${proInfoManage.product_licensevaliddate}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_instocklday" class="field">入库锁定天数：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_instocklday" name="product_instocklday" class="easyui-validatebox"
										data-options="validType:['num','nosp']" value="${proInfoManage.product_instocklday}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_outstocklday" class="field">出库锁定天数：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_outstocklday" name="product_outstocklday" class="easyui-validatebox"
										data-options="validType:['num','nosp']" value="${proInfoManage.product_outstocklday}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_outstockrday" class="field">出库效期提示天数：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_outstockrday" name="product_outstockrday" class="easyui-validatebox"
										data-options="validType:['num','nosp']" value="${proInfoManage.product_outstockrday}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_internationcode" class="field">国际码：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_internationcode" name="product_internationcode" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${proInfoManage.product_internationcode}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_nationaldscode" class="field">国家药监码：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_nationaldscode" name="product_nationaldscode" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${proInfoManage.product_nationaldscode}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_quanlitystandard" class="field">质量标准：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_quanlitystandard" name="product_quanlitystandard" class="combobox" style="width:152px"
										 data-options="required:true" value="${proInfoManage.product_quanlitystandard}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_storagecondition" class="field">储存条件：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_storagecondition" name="product_storagecondition" class="combobox" style="width:152px"
										 data-options="required:true" value="${proInfoManage.product_storagecondition}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_storeplace" class="field">储存库区：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_storeplace" name="product_storeplace" class="combobox" style="width:152px"
										 data-options="required:true" value="${proInfoManage.product_storeplace}" />
								</div>
							</div>
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_lowtemperature" class="field">低温：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_lowtemperature" name="product_lowtemperature" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_lowtemperature}" />
								</div> 
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_hightemperature" class="field">高温：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_hightemperature" name="product_hightemperature" class="easyui-validatebox"
										data-options="required:true,validType:['decimal','nosp']" value="${proInfoManage.product_hightemperature }" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_effect" class="field">功效：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_effect" name="product_effect" class="easyui-validatebox"
										data-options="validType:'length[1,20]'" value="${proInfoManage.product_effect}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_performance" class="field">性状：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_performance" name="product_performance" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${proInfoManage.product_performance}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_medcondition" class="field">药品情况：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_medcondition" name="product_medcondition" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${proInfoManage.product_medcondition}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_instruction" class="field">说明书：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_instruction" name="product_instruction" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${proInfoManage.product_instruction}" />
								</div>
							</div>	<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_qualityremark" class="field">质量备注：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_qualityremark" name="product_qualityremark" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${proInfoManage.product_qualityremark}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_printtype" class="field">打印格式：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_printtype" name="product_printtype" class="combobox" style="width:152px"
										 data-options="required:true" value="${proInfoManage.product_printtype}" />
									<input id="perview" type="button"  value="预览" onclick="perviewPrint();" style="height: 22px;width: 35px;"/> 
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_managecetagory" class="field">经营分类：</label>
								</div>
								<div class="Dialog-form-item"> 
									<%-- <input id="product_managecetagory" name="product_managecetagory" class="combobox" style="width:152px"
										 data-options="required:true" value="${proInfoManage.product_managecetagory}" /> --%>
									<input id="product_managecetagory" name="product_managecetagory" class="easyui-validatebox" onclick="selectCategory('setCategory',this)"
									data-options="required:'true'" style="width:300px" value="${proInfoManage.product_managecetagory}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_checktime" class="field">审核日期：</label>
								</div>
								<div class="Dialog-form-item"> 
									<input id="product_checktime" name="product_checktime" style="width:152px"
										data-options="required:true"  value="${proInfoManage.product_checktime}" />
								</div>
							</div>	
							<div class="float-l">
								<div class="Dialog-form-title">
									<label for="product_checkperson" class="field">审核人：</label>
								</div>
								<div class="Dialog-form-item">
									<input id="product_checkperson" name="product_checkperson" class="easyui-validatebox"
										data-options="validType:'length[1,20]'"  value="${proInfoManage.product_checkperson}" />
								</div>
							</div>	
							</div>
							
							<div style="border:none;position:relative;top:0px;margin-top:10px;margin-bottom:5px;margin-left:5px;width:570px">勾选信息</div>
				        	<div style="width:570px;height:100px;margin-left:5px;border:1px solid #CCCCCC">
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_stoppurchase" type ="hidden" value="${proInfoManage.product_stoppurchase}" />
									<input id="product_stoppurchase" type ="checkbox" style="width:20px" />
									<label for="product_stoppurchase">停购</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_salestop" type ="hidden" value="${proInfoManage.product_salestop}" />
									<input id="product_salestop" type ="checkbox" style="width:20px" />
									<label for="product_salestop">停售</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_stopdistribution" type ="hidden"  value="${proInfoManage.product_stopdistribution}" />
									<input id="product_stopdistribution" type ="checkbox" style="width:20px" />
									<label for="product_stopdistribution">门店停配</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_immaintain" type ="hidden" value="${proInfoManage.product_immaintain}" />
									<input id="product_immaintain" type ="checkbox" style="width:20px" />
									<label for="product_immaintain">重点养护</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_dsscancode" type ="hidden" value="${proInfoManage.product_dsscancode}" />
									<input id="product_dsscancode" type ="checkbox" style="width:20px" />
									<label for="product_dsscancode">扫描药监码</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_qacprint" type ="hidden" value="${proInfoManage.product_qacprint}" />
									<input id="product_qacprint" type ="checkbox" style="width:20px" />
									<label for="product_qacprint">打印质检单</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_printreceipt" type ="hidden" value="${proInfoManage.product_printreceipt}" />
									<input id="product_printreceipt" type ="checkbox" style="width:20px" />
									<label for="product_printreceipt">打印回执</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_doublecheck" type ="hidden" value="${proInfoManage.product_doublecheck}" />
									<input id="product_doublecheck" type ="checkbox" style="width:20px" />
									<label for="product_doublecheck">双复核验收</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_inputbatchnum" type ="hidden" value="${proInfoManage.product_inputbatchnum}" />
									<input id="product_inputbatchnum" type ="checkbox" style="width:20px" />
									<label for="product_inputbatchnum">输入批号</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_inputvalidtime" type ="hidden" value="${proInfoManage.product_inputvalidtime}" />
									<input id="product_inputvalidtime" type ="checkbox" style="width:20px" />
									<label for="product_inputvalidtime">输入有效期</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_inputproductdate" type ="hidden" value="${proInfoManage.product_inputproductdate}" />
									<input id="product_inputproductdate" type ="checkbox" style="width:20px" />
									<label for="product_inputproductdate">输入生产日期</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_sterilizationbnum" type ="hidden" value="${proInfoManage.product_sterilizationbnum}" />
									<input id="product_sterilizationbnum" type ="checkbox" style="width:20px" />
									<label for="product_sterilizationbnum" >输入灭菌批号</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_sterilizationtime" type ="hidden" value="${proInfoManage.product_sterilizationtime}" />
									<input id="product_sterilizationtime" type ="checkbox" style="width:20px" />
									<label for="product_sterilizationtime" >输入灭菌日期</label>
								</div>
							</div>  
							<div class="float-l">
				        		<div class="SelectAll">
									<input name="product_inputappnum" type ="hidden" value="${proInfoManage.product_inputappnum}" />
									<input id="product_inputappnum" type ="checkbox" style="width:20px" />
									<label for="product_inputappnum" >输入批准文号</label>
								</div>
							</div>  
						</div>
						</div> 			        
				      
				        <div title="相关图片" id="tab-pro_5"> 
				        <div style="border:none;position:relative;top:0px;margin-top:10px;margin-bottom:5px;margin-left:5px;width:570px">相关图片：</div>
				        	<div style="width:570px;height:125px;margin-left:5px;border:1px solid #CCCCCC">
				        	   <div class="float-l">
									<div class="Dialog-form-title">
										<label for="product_picname" class="field">图片名称：</label>
									</div>
									<div class="Dialog-form-item">
										<input id="product_picname" name="product_picname" class="easyui-validatebox"
											data-options="validType:'length[1,20]'" value="${proInfoManage.product_picname}" />
									</div>
								</div>
								<div class="float-l">
									<div class="Dialog-form-title">
										<label for="product_picremark" class="field">图片备注：</label>
									</div>
									<div class="Dialog-form-item">
										<input id="product_picremark" name="product_picremark" class="easyui-validatebox"
											data-options="validType:'length[1,20]'" value="${proInfoManage.product_picremark}" />
									</div>
								</div>
								<div class="float-l" id="localImag1">
								<div class="Dialog-form-title">
									<label for="product_picture" class="field">上传图片：</label>
								</div>
								<div class="Dialog-form-item"> 
									<input id="product_picture" name="product_picture" style="float:left;width:250px" class="easyui-validatebox"
										readOnly="true" value="${proInfoManage.product_picture}" />
									<input id="file_upload_product_picture" style="float:left;width:150px" name="product_picture" ContentEditable="false" accept="image/*"
		    						 	runat="server" onchange="javascript:setImagePreview(this,localImag1,preview1);" type="file" /> 
		    						<img id="preview1" alt="预览图片" onclick="previewimg(this);"
										style="margin-left: 8px; float: right; width: 20px; height: 20px; display: none;" />
								</div>
							</div>
				        </div>
			        	
			        	<div style="border:none;position:relative;width:570px;margin-top:5px;margin-bottom:5px;margin-left:5px">其他图片信息：</div>
								
								<!-- 显示区域 -->
								<div style="height:400px">
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
		<input id="submitProduct" class="btn-save"
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