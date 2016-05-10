<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/chnToLetter.js"></script>

<script type="text/javascript">

$('#product_othercategory').combobox({
	url:global_param.context_name + "/system/combobox/lists?pid=244",
	method :"get",
	valueField : "cbs_id",
	textField : "cbs_chn",
});

$('#product_unit').combobox({
	url:global_param.context_name + "/system/combobox/lists?pid=132",
	method :"get",
	valueField : "cbs_id",
	textField : "cbs_chn",
});

$('#product_dosagetype').combobox({
	url:global_param.context_name + "/system/combobox/lists?pid=98",
	method :"get",
	valueField : "cbs_id",
	textField : "cbs_chn",
});


//当前选择的产品
var proInfo = null;



$('#product_code').bind('keydown', function() {
    var e = event || window.event;
    var keyCode = e.keyCode || e.which;
    var actElement = document.activeElement;
    //如果按键是enter并且当前焦点是产品编号，则弹出产品选择框
    if(keyCode == 13&&actElement.id == "product_code"){
 		var proContent = $('#product_code').attr("value");
		$("#proListWindow").window({
			title : '产品选择',
			width : 1250,
			height : 650,
			shadow : true,
			modal : true,
			closed : true,
			minimizable : false,	
			maximizable : false,
			collapsible : false,
			resizable : false,
			content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/purchase/order/viewProList?callback=setProContent&proContent=' + proContent.replace("%", "%25") +'"></iframe>',
		});
		$('#proListWindow').window('open');
 		
    }
});
	
//获得子窗口的产品信息
function setProContent(node){
	//存储当前的产品信息
	proInfo = node;
	//修改控件信息
	$("#product_code").attr("value",node.product_code);
	$("#product_id").attr("value",node.product_id);
	$("#product_retailprice").attr("value",node.product_retailprice);
	$("#product_memberprice").attr("value",node.product_memberprice);
	$("#product_othercategory").combobox("setValue",node.product_unit);
	$("#product_name").attr("value",node.product_name);
	$("#product_productarea").attr("value",node.product_productarea);
	$("#product_specification").attr("value",node.product_specification);
	$("#product_unit").combobox("setValue",node.product_unit);
	$("#product_dosagetype").combobox("setValue",node.product_dosagetype);
};

function submitForm() {
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
</script>

<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
			<form:form id="_form" action="${formUrl}" method="${method}" onsubmit="return false;">
				<input name="priceTag_id" type="hidden" value="${proPriceTag.medicine_id}" />
				<input name="SPRING_TOKEN" type="hidden" value="${sessionScope.SPRING_TOKEN}" />
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="product_code" class="field">产品编号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="product_code" name="product_code" class="easyui-validatebox" />
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="product_id" class="field">产品序号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="product_id" name="product_id" class="easyui-validatebox" readonly="readonly"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="product_retailprice" class="field">零售价：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="product_retailprice" name="product_retailprice" class="easyui-validatebox" readonly="readonly"/>
					</div>
				</div>		
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="product_memberprice" class="field">会员价：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="product_memberprice" name="product_memberprice" class="easyui-validatebox" readonly="readonly"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="product_othercategory" class="field">其它分类：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="product_othercategory" name="product_othercategory" class="easyui-validatebox" readonly="readonly" style="width:152px;"/>
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="product_name" class="field">产品名称：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="product_name" name="product_name" class="easyui-validatebox" readonly="readonly"/>
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="product_productarea" class="field">产地：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="product_productarea" name="product_productarea" class="easyui-validatebox" readonly="readonly"/>
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="product_specification" class="field">规格：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="product_specification" name="product_specification" class="easyui-validatebox" readonly="readonly"/>
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="product_unit" class="field">单位：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="product_unit" name="product_unit" class="easyui-validatebox" readonly="readonly" style="width:152px;"/>
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="product_dosagetype" class="field">剂型：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="product_dosagetype" name="product_dosagetype" class="easyui-validatebox"	readonly="readonly" style="width:152px;"/>
					</div>
				</div>		
				<!-- <div class="float-l">
					<div class="Dialog-form-title">
						<label for="" class="field">开始时间：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="" name="" class="easyui-datebox"	style="width:152px;"/>
					</div>
				</div>	
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="" class="field">结束时间：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="" name="" class="easyui-datebox"	style="width:152px;"/>
					</div>
				</div> -->	 
			</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submitMedicine" class="btn-save"
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