<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">

$(function() {
	//设置时间(删除首尾逗号)
   	var time = $("#rule_set").val();
   	$("#rule_set").val(time.substring(1,time.length-1));
   	
  //开始日期
 	$('#rule_startdate').datebox({
		required:true,	
		value:($('#rule_startdate').val()=="")?"":util.toDate($('#rule_startdate').val()),
	}); 

	
	//结束日期
 	$('#rule_enddate').datebox({
		required:true,	
		value:($('#rule_enddate').val()=="")?"":util.toDate($('#rule_enddate').val()),
	}); 
   	
  //设置下拉框默认选中
  	var index = $("#rule_set").val().indexOf("星");
	var month = new Array(),week = new Array();
	
  	if(index!=-1){
  		if(index!=0){
  			month = $("#rule_set").val().substring(0,index-1).split(",");
  			week = $("#rule_set").val().substring(index,$("#rule_set").val().length).split(",");
  		}else{
  			week = $("#rule_set").val().split(",");
  		}
  	}else{
  		month = $("#rule_set").val().split(",");
  	}
  	
 	// 折扣方式下拉框
   	 $('#rule_type').combobox({
   		url : global_param.context_name + "/system/combobox/lists?pid=965",
   		method :"get",
   		valueField : "cbs_id",
   		textField : "cbs_chn",
   		/* onLoadSuccess:function(){
   			$(this).combobox("setValue",967);
   		}, */
   		onSelect:function(record){
   			var perWeek = document.getElementById("perWeek").style;
   			var perMonth = document.getElementById("perMonth").style;
   			var period = document.getElementById("period").style;
   			if(record.cbs_id==967){//如果选的是   每周
   				perWeek.display = "";
   				perMonth.display = "none";
   				period.display = "none";
   			}else if(record.cbs_id==968){//如果选的是   每月
   				perWeek.display = "none";
   				perMonth.display = "";
   				period.display = "none";
   			}else if(record.cbs_id==969){//如果选择的是   时间段
   				perWeek.display = "none";
   				perMonth.display ="none";
   				period.display = "";
   			}
   		}
   	});
	
 	// 每周下拉框
   	$('#rule_type_perWeek').combobox({
   		url : global_param.context_name + "/system/combobox/lists?pid=953",
   		method :"get",
   		valueField : "cbs_chn",
   		textField : "cbs_chn",
   		multiple:true,//多选
   		value:week==""?null:week,
   		onSelect:function(){
   			var text = $('#rule_type_perWeek').combobox("getText");
   			var rule_set = $("#rule_set").val();
   			var index = rule_set.indexOf("星");
   			if(index!=-1){
   				//如果时间是星期开始，则添加逗号
   	   			if(index==0)
   	   				rule_set = text;
   	   			else
   	   				rule_set = rule_set.substring(0,index) + text;
   			}else{
   				if(rule_set=="")//如果星期选择为空，则删除最后逗号
   					rule_set = text;
   				else
   					rule_set = rule_set +","+ text;
   			}
   			$("#rule_set").val(rule_set);
   			
   		},
   		onUnselect:function(record){
   			var text = $('#rule_type_perWeek').combobox("getText");
   			var rule_set = $("#rule_set").val();
   			var index = rule_set.indexOf("星");
   			if(index!=-1){
   				//如果时间是星期开始，则添加逗号
   	   			if(index==0)
   	   				rule_set = text;
   	   			else
   	   				rule_set = rule_set.substring(0,index) + text;
   			}else{
   				//如果星期选择不为空，则删除最后逗号
   				if(text!=""){
   					if(rule_set=="")
   						rule_set = text;
   					else
   						rule_set = rule_set +","+ text;
   				}else
   					rule_set = rule_set.substring(0,rule_set.length-1);
   			}
   			if(rule_set.indexOf("星")!=-1)
   				$("#rule_set").val(rule_set);
   			else
   				$("#rule_set").val(rule_set.substring(0,rule_set.length-1));
   		}
   	});
 
	// 每月下拉框
   	$('#rule_type_perMonth').combobox({
   		url : global_param.context_name + "/system/combobox/lists?pid=921",
   		method :"get",
   		valueField : "cbs_chn",
   		textField : "cbs_chn",
   		multiple:true,//多选
   		value:month==""?null:month,
   		onSelect:function(){
   			var text = $('#rule_type_perMonth').combobox("getText");
   			var rule_set = $("#rule_set").val();
   			var index = rule_set.indexOf("星");
   			if(index!=-1){
   			//如果时间是星期开始，则添加逗号
   	   			if(index==0)
   	   				rule_set = text +","+ rule_set.substring(index-1,rule_set.length);
   	   			else
   	   				rule_set = text + rule_set.substring(index-1,rule_set.length);
   	   			$("#rule_set").val(rule_set);
   			}else
   				$("#rule_set").val(text);
   			
   		},
   		onUnselect:function(record){
   			var text = $('#rule_type_perMonth').combobox("getText");
   			var rule_set = $("#rule_set").val();
   			if(rule_set.indexOf("星")!=-1){
   				rule_set = text + rule_set.substring(rule_set.indexOf("星")-1,rule_set.length);
   	   			var index = rule_set.indexOf("星");
   	   			//如果星期前面没有日期，则去掉逗号
   	   			if(index==1)
   	   				$("#rule_set").val(rule_set.substring(1,rule_set.length));
   	   			else
   	   				$("#rule_set").val(rule_set);
   			}else
   				$("#rule_set").val(text);
   			
   		}
   	});
	
   	//如果折扣为空，则默认设置为100
   	if($("#rule_discount").val()=="") 
   		$("#rule_discount").val(100);
   
});   	

function setDate(strs){
	var date = "";
	for(var i=0;i<strs.length-1;i++){
		date = date + strs[i]+",";
	}
	date = date + strs[strs.length-1];
	$("#rule_set").val(date);
}

function resetTime(){
	$("#rule_set").val("");//清空所选时间
	$('#rule_type').combobox('clear');
	$('#rule_type_perMonth').combobox('clear');
	$('#rule_type_perWeek').combobox('clear');
	$("#rule_starttime").val("");
	$("#rule_endtime").val("");
}

function submitForm() {
	//折扣检测
	if(Number($("#rule_discount").val())>100||Number($("#rule_discount").val())>100){
		util.show("折扣不能大于100");
		return;
	}
	if($("#rule_starttime").val()==null||$("#rule_starttime").val()=="")	
		$("#rule_starttime").val("00:00:00");
	if($("#rule_endtime").val()==null||$("#rule_endtime").val()=="")
		$("#rule_endtime").val("23:59:59");
	
	var startDate = $('#rule_startdate').datebox('getValue');
	var endDate = $('#rule_enddate').datebox('getValue');

	var d1 = new Date(startDate.replace(/\-/g, "\/"));  
	var d2 = new Date(endDate.replace(/\-/g, "\/")); 
	//日期验证提示
	if(d1 > d2){  
		util.show("开始日期不能大于结束日期");  
		return;
	}
	
	var t1 = $("#rule_starttime").timespinner('getHours')*10000+$("#rule_starttime").timespinner('getMinutes')*100+$("#rule_starttime").timespinner('getSeconds');
	var t2 = $("#rule_endtime").timespinner('getHours')*10000+$("#rule_endtime").timespinner('getMinutes')*100+$("#rule_endtime").timespinner('getSeconds');
	//时间验证提示
	if(t1 >= t2){  
		util.show("开始时间不能大于结束时间");  
		return;
	}
 
	var data = util.submit('_form');
	if (data) {
		if (data.result == "success") {
			util.show(data.message);
			$('#RuleInfoDg').datagrid('reload');
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
				<input id="rule_id" name="rule_id" type="hidden" value="${pmnRuleInfo.rule_id}" />
				<input name="SPRING_TOKEN" type="hidden" value="${sessionScope.SPRING_TOKEN}" />
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="rule_name" class="field">规则名称：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="rule_name" name="rule_name" class="easyui-validatebox"
							value="${pmnRuleInfo.rule_name}" />
					</div>
				</div>
			
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="rule_discount" class="field">折扣：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="rule_discount" name="rule_discount" class="easyui-validatebox"
							data-options="required:true,validType:['decimal','length[1,5]']" value="${pmnRuleInfo.rule_discount}" />
					</div>
				</div>
	           	 <div class="Dialog-form-title">
	                   <label for="rule_startdate" class="field">开始日期：</label>
	               </div>
	               <div class="Dialog-form-item">
	                   <input id="rule_startdate" name="rule_startdate" class="easyui-datebox" 
	                  	 data-options="required:true" style="width: 152px;" value="${pmnRuleInfo.rule_startdate}" />
	               </div>
	           
	           	<div class="Dialog-form-title">
	                   <label for="rule_enddate" class="field">结束日期：</label>
	               </div>
	               <div class="Dialog-form-item">
	                   <input id="rule_enddate" name="rule_enddate" class="easyui-datebox" 
	                  	 data-options="required:true" style="width: 152px;" value="${pmnRuleInfo.rule_enddate}" />
	               </div>
                 <div class="float-l">
                     <div class="Dialog-form-title">
                         <label for="rule_type" class="field">折扣规则方式：</label>
                     </div>
                     <div class="Dialog-form-item">
                         <input id="rule_type" name="rule_type"  style="width: 152px;" class="easyui-combobox" data-options="required:true" value="${pmnRuleInfo.rule_type}" />
                     </div>
                 </div>
                 <div id="perWeek" class="float-l" >
                     <div class="Dialog-form-item">
                         <input id="rule_type_perWeek" class="easyui-combobox"  style="width: 100px;" />
                     </div>
                 </div>
                 <div id="perMonth" class="float-l" style="display:none">
                     <div class="Dialog-form-item">
                         <input id="rule_type_perMonth" class="easyui-combobox"  style="width: 100px;" />
                     </div>
                 </div>
                 <div class="float-l">
                     <div class="Dialog-form-item">
                         <input id="clearTime" class="btn-empty" onmouseover="this.className='btn-empty-over'" onmouseout="this.className='btn-empty'"
						onmousedown="this.className='btn-empty-down'" type="button"
						onclick="resetTime();">
                     </div>
                 </div>
                 <div id="period" class="float-l" style="display:none">
                 	 <div class="Dialog-form-title">
                         <label for="rule_starttime" class="field">开始时间：</label>
                     </div>
                     <div class="Dialog-form-item">
                         <input id="rule_starttime" name="rule_starttime" class="easyui-timespinner"
                        	 data-options="showSeconds:true" style="width: 152px;" value="${pmnRuleInfo.rule_starttime}" />
                     </div>
                 
                 	<div class="Dialog-form-title">
                         <label for="rule_endtime" class="field">结束时间：</label>
                     </div>
                     <div class="Dialog-form-item">
                         <input id="rule_endtime" name="rule_endtime" class="easyui-timespinner"
                        	 data-options="showSeconds:true" style="width: 152px;" value="${pmnRuleInfo.rule_endtime}" />
                     </div>
                 </div>
                 
                 <div id="time_string" class="float-l">
                     <div class="Dialog-form-title">
                         <label id="viewTime" for="rule_set" class="field">折扣规则详情:</label>
                     </div>
                     <div class="Dialog-form-item">
                     <textarea id="rule_set" name="rule_set" readOnly="readonly" type="text" style="width:420px;height:60px"
									class="easyui-validatebox textareaW1" >${pmnRuleInfo.rule_set}</textarea>
                     </div>
                 </div>
                 <div class="float-l">
					<div class="Dialog-form-title">
						<label for="rule_remark" class="field">折扣备注：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="rule_remark" name="rule_remark" class="easyui-validatebox"
							value="${pmnRuleInfo.rule_remark}" />
					</div>
				 </div>
				 	<div class="float-l">
					<div class="Dialog-form-title">
						<label for="rule_number" class="field">数量：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="rule_number" name="rule_number" class="easyui-validatebox"
							value="${pmnRuleInfo.rule_number}" />
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
			onclick="submitForm();" /> 
			<input id="" class="btn-cancel"
			onmouseover="this.className='btn-cancel-over'"
			onmouseout="this.className='btn-cancel'"
			onmousedown="this.className='btn-cancel-down'" type="button"
			onclick="util.closeWindow('editWindow');" />
	</div>
</div>