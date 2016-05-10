<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">

	//开始日期
	$('#variety_startTime').datebox({
	required:true,	
	value:($('#variety_startTime').val()=="")?"":util.toDate($('#variety_startTime').val()),
}); 

	//结束日期
	$('#variety_endTime').datebox({
	required:true,	
	value:($('#variety_endTime').val()=="")?"":util.toDate($('#variety_endTime').val()),
}); 

$('#product_id').bind('keydown', function() {
    var e = event || window.event;
    var keyCode = e.keyCode || e.which;
    var actElement = document.activeElement;
    //如果按键是enter并且当前焦点是产品编号，则弹出产品选择框
    if(keyCode == 13&&actElement.id == "product_id"){
 		var proContent = $('#product_id').attr("value");
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
			content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/quality/varietyCheck/viewProList?callback=setProContent&proContent=' + proContent.replace("%", "%25") +'"></iframe>',
		});
		$('#proListWindow').window('open');
 		
    }
});

//获得子窗口的产品信息
function setProContent(node){
	//存储当前的产品信息
	proInfo = node;
	//修改控件信息
	//$("#product_name").attr("value",node.product_name);
	$("#product_id").attr("value",node.product_id);
};


function submitForm() {
	var startTime = $('#variety_startTime').datebox('getValue');
	var endTime = $('#variety_endTime').datebox('getValue');

	var d1 = new Date(startTime.replace(/\-/g, "\/"));  
	var d2 = new Date(endTime.replace(/\-/g, "\/")); 
	//日期验证提示
	if(d1 > d2){  
		util.show("开始日期不能大于结束日期");  
		return;
	}
	
	if(proInfo==null||$('#product_id').val()!=proInfo.product_id){
		util.show("请回车后在页面选择产品！");
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
	
</script>

<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
			<form:form id="_form" action="${formUrl}" method="${method}" onsubmit="return false;">
				<input name="variety_checkId" type="hidden" value="${qltVarietyCheckVO.variety_checkId}" />
				<input name="SPRING_TOKEN" type="hidden"
					value="${sessionScope.SPRING_TOKEN}" />
				<%-- <div class="float-l">
					<div class="Dialog-form-title">
						<label for="product_name" class="field">产品名称：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="product_name" class="easyui-validatebox"
							data-options="required:true"  value="${qltVarietyCheckVO.product_name}" />
					</div>
				</div> --%>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="product_id" class="field">产品序号：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="product_id" name="product_id" class="easyui-validatebox"
							data-options="required:true"  value="${qltVarietyCheckVO.product_id}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="variety_checkReason" class="field">确认理由：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="variety_checkReason" name="variety_checkReason" class="easyui-validatebox"
							value="${qltVarietyCheckVO.variety_checkReason}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="variety_majorMaintain" class="field">养护重点：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="variety_majorMaintain" name="variety_majorMaintain" class="easyui-validatebox"
							value="${qltVarietyCheckVO.variety_majorMaintain}" />
					</div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="variety_remark" class="field">备注：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="variety_remark" name="variety_remark" class="easyui-validatebox"
							value="${qltVarietyCheckVO.variety_remark}" />
					</div>
				</div>
				
				
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="variety_startTime" class="field">开始时间：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="variety_startTime" name="variety_startTime" class="easyui-datebox" 
	                  	 data-options="required:true" style="width: 152px;" value="${qltVarietyCheckVO.variety_startTime}" />
	               </div>
				</div>
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="variety_endTime" class="field">结束时间：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="variety_endTime" name="variety_endTime" class="easyui-datebox" 
	                  	 data-options="required:true" style="width: 152px;" value="${qltVarietyCheckVO.variety_endTime}" />
	               </div>
				</div>
			</form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submitVariety" class="btn-save"
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