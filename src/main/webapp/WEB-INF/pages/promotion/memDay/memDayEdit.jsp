<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
$(function() {
	
	//设置时间(删除首尾逗号)
	var time = $("#mem_day_date").val();
	$("#mem_day_date").val(time.substring(1,time.length-1));
	
	//设置下拉框默认选中
  	var index = $("#mem_day_date").val().indexOf("星");
	var month = new Array(),week = new Array();
	
  	if(index!=-1){
  		if(index!=0){
  			month = $("#mem_day_date").val().substring(0,index-1).split(",");
  			week = $("#mem_day_date").val().substring(index,$("#mem_day_date").val().length).split(",");
  		}else{
  			week = $("#mem_day_date").val().split(",");
  		}
  	}else{
  		month = $("#mem_day_date").val().split(",");
  	}
  	
 	// 每月下拉框
   	$('#mem_day_perMonth').combobox({
   		url : global_param.context_name + "/system/combobox/lists?pid=921",
   		method :"get",
   		valueField : "cbs_chn",
   		textField : "cbs_chn",
   		multiple:true,//多选
   		value:month==""?null:month,
   		onSelect:function(){
   			var text = $('#mem_day_perMonth').combobox("getText");
   			var mem_day_date = $("#mem_day_date").val();
   			var index = mem_day_date.indexOf("星");
   			if(index!=-1){
   			//如果时间是星期开始，则添加逗号
   	   			if(index==0)
   	   				mem_day_date = text +","+ mem_day_date.substring(index-1,mem_day_date.length);
   	   			else
   	   				mem_day_date = text + mem_day_date.substring(index-1,mem_day_date.length);
   	   			$("#mem_day_date").val(mem_day_date);
   			}else
   				$("#mem_day_date").val(text);
   			
   		},
   		onUnselect:function(record){
   			var text = $('#mem_day_perMonth').combobox("getText");
   			var mem_day_date = $("#mem_day_date").val();
   			if(mem_day_date.indexOf("星")!=-1){
   				mem_day_date = text + mem_day_date.substring(mem_day_date.indexOf("星")-1,mem_day_date.length);
   	   			var index = mem_day_date.indexOf("星");
   	   			//如果星期前面没有日期，则去掉逗号
   	   			if(index==1)
   	   				$("#mem_day_date").val(mem_day_date.substring(1,mem_day_date.length));
   	   			else
   	   				$("#mem_day_date").val(mem_day_date);
   			}else
   				$("#mem_day_date").val(text);
   			
   		}
   	});
 	// 每周下拉框
   	$('#mem_day_perWeek').combobox({
   		url : global_param.context_name + "/system/combobox/lists?pid=953",
   		method :"get",
   		valueField : "cbs_chn",
   		textField : "cbs_chn",
   		multiple:true,//多选
   		value:week==""?null:week,
   		onSelect:function(){
   			var text = $('#mem_day_perWeek').combobox("getText");
   			var mem_day_date = $("#mem_day_date").val();
   			var index = mem_day_date.indexOf("星");
   			if(index!=-1){
   				//如果时间是星期开始，则添加逗号
   	   			if(index==0)
   	   				mem_day_date = text;
   	   			else
   	   				mem_day_date = mem_day_date.substring(0,index) + text;
   			}else{
   				if(mem_day_date=="")//如果星期选择为空，则删除最后逗号
   					mem_day_date = text;
   				else
   					mem_day_date = mem_day_date +","+ text;
   			}
   			$("#mem_day_date").val(mem_day_date);
   			
   		},
   		onUnselect:function(record){
   			var text = $('#mem_day_perWeek').combobox("getText");
   			var mem_day_date = $("#mem_day_date").val();
   			var index = mem_day_date.indexOf("星");
   			if(index!=-1){
   				//如果时间是星期开始，则添加逗号
   	   			if(index==0)
   	   				mem_day_date = text;
   	   			else
   	   				mem_day_date = mem_day_date.substring(0,index) + text;
   			}else{
   				//如果星期选择不为空，则删除最后逗号
   				if(text!=""){
   					if(mem_day_date=="")
   						mem_day_date = text;
   					else
   						mem_day_date = mem_day_date +","+ text;
   				}else
   					mem_day_date = mem_day_date.substring(0,mem_day_date.length-1);
   			}
   			if(mem_day_date.indexOf("星")!=-1)
   				$("#mem_day_date").val(mem_day_date);
   			else
   				$("#mem_day_date").val(mem_day_date.substring(0,mem_day_date.length-1));
   		}
   	});
   	
   	// 会员日方式下拉框
   	$('#mem_day_timeMethod').combobox({
   		url : global_param.context_name + "/system/combobox/lists?pid=917",
   		method :"get",
   		valueField : "cbs_id",
   		textField : "cbs_chn",
   		onLoadSuccess:function(){
   			$(this).combobox("setValue",918);
   		},
   		onSelect:function(record){
   			var perMonth = document.getElementById("perMonth").style;
   			var perWeek = document.getElementById("perWeek").style;
   			//如果选择的是   每月
   			if(record.cbs_id==918){
   				perMonth.display = "";
   				perWeek.display = "none";
   			}else if(record.cbs_id==919){//如果选的是   每周
   				perMonth.display = "none";
   				perWeek.display = "";
   			}
   		}
   	});
   	
   	//非会员日会员价格
    $('#mem_day_noExecutePrice').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=650",
		method :"get",
		valueField : "cbs_id",
		textField : "cbs_chn",
		editable:false,
	});
   	//非会员日会员折扣方式
    $('#mem_day_noDiscountMethod').combobox({
        url: global_param.context_name + "/system/combobox/lists?pid=631",
        method: "get",
        valueField: "cbs_id",
        textField: "cbs_chn",
        editable: false,
        onSelect:function(record){
        	//如果选择的是会员折扣
        	if(record.cbs_id == 632){
        		$("#mem_day_noDiscount").attr("readOnly","readonly");
        	}else if(record.cbs_id == 633)
        		document.getElementById("mem_day_noDiscount").readOnly=false;
        }
    });
  //会员日会员价格
    $('#mem_day_executePrice').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=650",
		method :"get",
		valueField : "cbs_id",
		textField : "cbs_chn",
		editable:false,
	});
  //会员日会员折扣方式
    $('#mem_day_discountMethod').combobox({
        url: global_param.context_name + "/system/combobox/lists?pid=631",
        method: "get",
        valueField: "cbs_id",
        textField: "cbs_chn",
        editable: false,
        onSelect:function(record){
        	//如果选择的是会员折扣
        	if(record.cbs_id == 632){
        		$("#mem_day_discount").attr("readOnly","readonly");
        	}else if(record.cbs_id == 633)
        		document.getElementById("mem_day_discount").readOnly=false;
        }
    });
  
  	
	$("#department_dg").datagrid({
		url : global_param.context_name + "/basInfo/basDepartmentInfo/getListByPromotion",
		method : 'get',
		title : '参加此会员日的部门', // 标题
		sortName : 'department_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitcolumns : true,
		fit:true,
		singleSelect:true,//单选
		rownumbers : true,
		nowrap : true,
		striped : true,
		queryParams : {
			mem_day_id:$("#mem_day_id").val(),
		},
		columns:[[ {
			field : 'department_id',
			title : '部门ID',
			width : 80,
			sortable : true
		},{
			field : 'department_fullname',
			title : '部门全称',
			width : 150,
			sortable : true
		},{
			field : 'department_number',
			title : '部门编号',
			width : 150,
			sortable : true
		}]],
		toolbar:"#tb", 
		onLoadSuccess:function(data){
			setCombobox(data);
		},
		onDblClickRow:deleteDepartment,
	});
	 //部门
	$('#addDepartment').combobox({
		url : global_param.context_name + "/basInfo/basDepartmentInfo/getListByDepts",
		method :"get",
		valueField : "department_id",
		textField : "department_fullname",
		editable:false,
		multiple:true,//多选
		onSelect:function(node){
			$('#department_dg').datagrid('appendRow',node);//添加一行
			var data = $('#department_dg').datagrid("getData");
			setCombobox(data);
		}
	});
	 //查询部门编号
	$('#search_dept').bind('keyup',function(event){  
	    if(event.keyCode == "13"){
	    	var data = $('#department_dg').datagrid("getData");
	    	for(var i=0;i<data.rows.length;i++){
	    		if(data.rows[i].department_number==$('#search_dept').val()){
	    			$('#department_dg').datagrid("selectRow",i);
	    		}
	    	}
	    }
	});
	 
	//起始时间
 	$('#mem_day_startDate').datetimebox({
		required:true,	
		value:($('#mem_day_startDate').val()=="")?"":util.toDate($('#mem_day_startDate').val(),true),
	}); 
 	//截止时间
 	$('#mem_day_endDate').datetimebox({
		required:true,	
		value:($('#mem_day_endDate').val()=="")?"":util.toDate($('#mem_day_endDate').val(),true),
	});  
	 
	 
	//如果折扣为空，则默认设置为100
	if($("#mem_day_noDiscount").val()==""){
		$("#mem_day_noDiscount").val(100);
	}
	if($("#mem_day_discount").val()==""){
		$("#mem_day_discount").val(100);
	}
  	
  	//如果修改时选择的是  会员折扣  ，折扣不可改
  	if($('#mem_day_noDiscountMethod').combobox("getValue")==632)
  		$("#mem_day_noDiscount").attr("disabled","disabled");
  	if($('#mem_day_discountMethod').combobox("getValue")==632)
  		$("#mem_day_discount").attr("disabled","disabled");
});   	
function submitForm() {
	//折扣检测
	if(Number($("#mem_day_noDiscount").val())>100||Number($("#mem_day_discount").val())>100){
		util.show("折扣不能大于100");
		return;
	}
	
	//保存参与活动的部门
	var depts = "";
	var data = $('#department_dg').datagrid("getData");
	for(var i=0;i<data.total;i++){
		if(i<data.total-1)
			depts = depts + data.rows[i].department_id+",";
		else
			depts = depts + data.rows[i].department_id;
	}
	$("#department_ids").val(","+depts+",");
		
    var re = util.submit('_form');
    if (re) {
        if (re.result == "success") {
        	util.show("更新成功！");	
        	$("#dg").datagrid("reload");
        	util.closeWindow('editWindow');
        }else {
            util.error("更新会员日出错");
        }
    }
    
    
} 
function deleteDepartment(){
	var node = $('#department_dg').datagrid("getSelected");//获取选中行
	var index =  $('#department_dg').datagrid("getRowIndex",node);//获取选中行索引
	$('#department_dg').datagrid("deleteRow",index);//删除某一行
	var data = $('#department_dg').datagrid("getData");
	setCombobox(data);
}
function deleteAll(){
	$('#department_dg').datagrid('loadData',{total:0,rows:[]});
	var data = $('#department_dg').datagrid("getData");
	setCombobox(data);
}
function setCombobox(data){
	var depts = "";
	var url = "";
	for(var i=0;i<data.total;i++){
		if(i<data.total-1)
			depts = depts + data.rows[i].department_id+"_";
		else
			depts = depts + data.rows[i].department_id;
	}
	if(depts=="")
		url = global_param.context_name +'/basInfo/basDepartmentInfo/getListByDepts';
	else
		url = global_param.context_name +'/basInfo/basDepartmentInfo/getListByDepts?depts='+depts;
	$('#addDepartment').combobox('reload',url);
	$('#addDepartment').combobox('clear');
}
function resetTime(){
	$("#mem_day_date").val("");//清空所选时间
	$('#mem_day_perMonth').combobox('clear');
	$('#mem_day_perWeek').combobox('clear');
}
</script>


<!-- toolbar显示区域 -->
	<div id="tb" style="padding:2px;height:auto">
		<div style="margin-bottom:1px">
		<a href="#" class="easyui-linkbutton" iconCls="icon-delete" plain="true" onclick="deleteDepartment()">删除</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-delete" plain="true" onclick="deleteAll()">删除全部</a>
		<font style="float:right">
			部门编号：<input id="search_dept" type="text" name="search_dept" width="80px">
			&nbsp;&nbsp;&nbsp;&nbsp;
		</font>
		</div>
	</div>



 <div class="easyui-layout" fit="true">
     <div region="center" border="false" class="Dialog-Bg"  >
         <div class="Dialog-formDiv clearfix">
             <form:form id="_form" action="${formUrl}" method="${method}">
                 <input id="mem_day_id" name="mem_day_id" type="hidden" value="${memDayManage.mem_day_id}" />
                 <input id="department_ids" name="department_ids" type="hidden" value="${memDayManage.department_ids}" />
                 
                 <input name="SPRING_TOKEN" type="hidden" value="${sessionScope.SPRING_TOKEN}" />
                 <div class="float-l">
                     <div class="Dialog-form-title">
                         <label for="mem_day_type" class="field">会员日类型：</label>
                     </div>
                     <div class="Dialog-form-item">
                         <input id="mem_day_type" name="mem_day_type" class="easyui-validatebox" style="width: 180px;" data-options="required:true" value="${memDayManage.mem_day_type}" />
                     </div>
                 </div>
                 <div class="float-l">
                     <div class="Dialog-form-title">
                         <label for="mem_day_executePrice" class="field">会员日会员价格：</label>
                     </div>
                     <div class="Dialog-form-item" >
                         <input id="mem_day_executePrice" name="mem_day_executePrice" style="width: 100px;" class="easyui-combobox"
                          data-options="required:true"  value="${memDayManage.mem_day_executePrice}" />
                         
                         <label for="mem_day_discountMethod" class="field">折扣方式：</label>
                         <input id="mem_day_discountMethod" name="mem_day_discountMethod" style="width: 100px;" class="easyui-combobox"
                          data-options="required:true"  value="${memDayManage.mem_day_discountMethod}" />
                         <label for="mem_day_discount" class="field">折扣：</label>
                         <input id="mem_day_discount" name="mem_day_discount" style="width: 100px;" class="easyui-validatebox"
                         data-options="required:true,validType:['decimal','length[1,5]']"  value="${memDayManage.mem_day_discount}" />
                     </div>
                 </div>
                 <div class="float-l">
                     <div class="Dialog-form-title">
                         <label for="mem_day_noExecutePrice" class="field">非会员日会员价格：</label>
                     </div>
                     <div class="Dialog-form-item" >
                         <input id="mem_day_noExecutePrice" name="mem_day_noExecutePrice" style="width: 100px;" class="easyui-combobox"
                          data-options="required:true"  value="${memDayManage.mem_day_noExecutePrice}" />
                         
                         <label for="mem_day_noDiscountMethod" class="field">折扣方式：</label>
                         <input id="mem_day_noDiscountMethod" name="mem_day_noDiscountMethod" style="width: 100px;" class="easyui-combobox"
                          data-options="required:true"  value="${memDayManage.mem_day_noDiscountMethod}" />
                         <label for="mem_day_noDiscount" class="field">折扣：</label>
                         <input id="mem_day_noDiscount" name="mem_day_noDiscount" style="width: 100px;" class="easyui-validatebox"
                         data-options="required:true,validType:['decimal','length[1,5]']"  value="${memDayManage.mem_day_noDiscount}" />
                     </div>
                 </div>
               
                 <div class="float-l">
                     <div class="Dialog-form-title">
                         <label for="mem_day_pointMultiple" class="field">会员日积分倍数：</label>
                     </div>
                     <div class="Dialog-form-item" >
                         <input id="mem_day_pointMultiple" name="mem_day_pointMultiple" style="width: 150px;"
                         class="easyui-validatebox" data-options="required:true,validType:['num','length[1,3]']"  
                         value="${memDayManage.mem_day_pointMultiple}" />
                     </div>
                 </div>
                 
                 <div class="float-l">
                     <div class="Dialog-form-title">
                         <label for="addDepartment" class="field">添加部门：</label>
                     </div>
                     <div class="Dialog-form-item" >
                         <input id="addDepartment"  style="width: 152px;" class="easyui-combobox" />
                     </div>
                 </div>
                  <div id="period" class="float-l">
                 	
                 	 <div class="Dialog-form-title">
                         <label for="mem_day_startDate" class="field">起始时间：</label>
                     </div>
                     <div class="Dialog-form-item">
                         <input id="mem_day_startDate" name="mem_day_startDate" class="easyui-datetimebox" 
                         style="width: 152px;" data-options="required:true" value="${memDayManage.mem_day_startDate}" />
                     </div>
                 
                 	<div class="Dialog-form-title">
                         <label for="mem_day_endDate" class="field">截止时间：</label>
                     </div>
                     <div class="Dialog-form-item">
                         <input id="mem_day_endDate" name="mem_day_endDate" class="easyui-datetimebox" 
                         style="width: 152px;" data-options="required:true" value="${memDayManage.mem_day_endDate}" />
                     </div>
                 </div>
                 <div class="float-l">
                     <div class="Dialog-form-title">
                         <label for="mem_day_timeMethod" class="field">会员日方式：</label>
                     </div>
                     <div class="Dialog-form-item">
                         <input id="mem_day_timeMethod" name="mem_day_timeMethod"  style="width: 152px;" class="easyui-combobox" data-options="required:true" value="${memDayManage.mem_day_timeMethod}" />
                     </div>
                 </div>
                 <div id="perMonth" class="float-l">
                     <div class="Dialog-form-item">
                         <input id="mem_day_perMonth" class="easyui-combobox" style="width: 100px;" />
                        
                     </div>
                 </div>
                 <div id="perWeek" class="float-l" style="display:none">
                     <div class="Dialog-form-item">
                         <input id="mem_day_perWeek" class="easyui-combobox" style="width: 100px;" />
                        
                     </div>
                 </div>
                <div class="float-l">
                     <div class="Dialog-form-item">
                         <input id="clearTime" class="btn-empty" onmouseover="this.className='btn-empty-over'" onmouseout="this.className='btn-empty'"
						onmousedown="this.className='btn-empty-down'" type="button" style="width:77px"
						onclick="resetTime();">
                     </div>
                 </div>
                  <div id="time_string" class="float-l">
                     <div class="Dialog-form-title">
                         <label id="viewTime" for="mem_day_date" class="field">时间:</label>
                     </div>
                     <div class="Dialog-form-item">
                     <textarea id="mem_day_date" name="mem_day_date" readOnly="readonly" type="text" style="width:420px;height:60px"
									class="easyui-validatebox textareaW1">${memDayManage.mem_day_date}</textarea>
                     </div>
                 </div>
             </form:form>
         </div>
         <!-- 显示已经参加活动的部门-->
         <div data-options="region:'center',border:false" style="height:200px">
			<table id="department_dg"></table>
		 </div>
     </div>
     
     <div data-options="region:'south',border:false" style="text-align: right;">
         <input id="" class="btn-save" onmouseover="this.className='btn-save-over'" 
         onmouseout="this.className='btn-save'" onmousedown="this.className='btn-save-down'" 
         type="button" onclick="submitForm();" />
         <input id="" class="btn-cancel" onmouseover="this.className='btn-cancel-over'" 
         onmouseout="this.className='btn-cancel'" onmousedown="this.className='btn-cancel-down'" 
         type="button" onclick="util.closeWindow('editWindow');" />
     </div>
</div>