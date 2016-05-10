<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">

var fieldType = null;
//字符串以**开头
String.prototype.startWith=function(str){ 
	var reg=new RegExp("^"+str); 
	return reg.test(this); 
};
//字符串以**结尾
String.prototype.endWith=function(str){ 
	var reg=new RegExp(str+"$"); 
	return reg.test(this); 
};
$(function() {
	//字段名称下拉框
	$('#${win}_field_name').combobox({
		url : global_param.context_name + "/system/screening/getList?fields="+
			$("#${win}_fields").val()+"&fieldNames="+$("#${win}_fieldNames").val()+"&className="
			+$("#${win}_className").val(),
		method :"get",
		valueField : "field",
		textField : "fieldName",
		editable:false,
		onSelect: function(record){
			fieldType = record.fieldType;
			//重置比较关系下拉框
			$('#${win}_compare_realation').combobox('reload',global_param.context_name + 
					"/system/combobox/lists?pid=970&cbs_dec="+fieldType);
		},
	});
	
	// 比较关系下拉框
   	$('#${win}_compare_realation').combobox({
   		url : global_param.context_name + "/system/combobox/lists?pid=970",
   		method :"get",
   		valueField : "cbs_id",
   		textField : "cbs_chn",
   	});
	
	
	$("#${win}_screening_dg").datagrid({
		title : '筛选条件', // 标题
		sortName : 'field_name', // 排序的列
		sortOrder : 'asc', // 排序方式
		fitcolumns : true,
		fit:true,
		singleSelect:true,//单选
		rownumbers : true,
		nowrap : true,
		striped : true,
		queryParams : {
		},
		columns:[[{
			field : 'field_name',
			title : '字段名',
			width : 80,
		},{
			field : 'compare_realation',
			title : '比较关系',
			width : 50,
		},{
			field : 'key_word',
			title : '关键字',
			width : 80,
		},{
			field : 'contact_type',
			title : '连接方式',
			width : 50,
		},{
			field : 'en_field',
			title : '字段',
			width : 100,
		},{
			field : 'contact',
			title : '连接',
			width : 50,
		},{
			field : 'field_type',
			title : '字段类型',
			width : 80,
		},{
			field : 'compare',
			title : '比较',
			width : 80,
		}]],
		toolbar : [ {
			text : '并且',
			iconCls : 'icon-add',
			handler : addAndScreening
		},{
			text : '或者',
			iconCls : 'icon-add',
			handler : addOrScreening
		},{
			text : '删除',
			iconCls : 'icon-delete',
			handler : deleteScreening
		}]
	});
});

function addAndScreening(){
	addScreening("并且");
}
function addOrScreening(){
	addScreening("或者");
}
function addScreening(contact_type){
	//文本框验证
	var field_name = $("#${win}_field_name").combobox("getText");
	var compare_realation = $("#${win}_compare_realation").combobox("getText");
	var key_word = $("#${win}_key_word").val();
	var contact = contact_type=="并且"?"and":"or";
	if(field_name==""||compare_realation==""||key_word==""){
		util.show("请先填写信息");
		return;
	}
	var nodes = $('#${win}_screening_dg').datagrid('getData'); 
	if(nodes.total==0){
		//如果添加的是第一条数据，则无连接方式
		contact_type="无连接";
		contact="";
	}
	//添加一行
	var node = {field_name:field_name,compare_realation:compare_realation,
			key_word:key_word,contact_type:contact_type,en_field:$("#${win}_field_name").combobox("getValue"),
			contact:contact,field_type:fieldType,compare:$("#${win}_compare_realation").combobox("getValue")};
	$('#${win}_screening_dg').datagrid('appendRow',node); 
}

function deleteScreening(){
	var node = $('#${win}_screening_dg').datagrid('getSelected'); 
	if(!node){
		util.show("请选择一条记录");
		return;
	}
	var index = $('#${win}_screening_dg').datagrid('getRowIndex',node);
	$('#${win}_screening_dg').datagrid('deleteRow',index); 
}
function reloadDatagrid(){
	var result = new Array();
	//判断一条数据是否满足所有的筛选条件，如果满足，则加入结果集
	for(var i = 0;i<s.data.rows.length;i++){
		if(isQualified(s.data.rows[i])){
			result.push(s.data.rows[i]);
		}
	}
	//重新载入数据
	$('#${ctrl_id}').datagrid('loadData',result);
	//关闭窗口
	util.closeWindow('${win}');
}

function isQualified(node){
	var result = true;
	var nodes = $('#${win}_screening_dg').datagrid('getData'); 
	for(var i=0;i<nodes.total;i++){
		var flag = false;//初始化
		switch(nodes.rows[i].compare){
		case '971':
			if(node[nodes.rows[i].en_field] == nodes.rows[i].key_word)
				flag = true;
			break;
		case '972':
			if(node[nodes.rows[i].en_field] != nodes.rows[i].key_word)
				flag = true;
			break;
		case '973':
			if(nodes.rows[i].field_type == "Date"){
				var d1 = new Date(node[nodes.rows[i].en_field]);  
				var d2 = new Date(nodes.rows[i].key_word.replace(/\-/g, "\/"));
				flag = d1>d2;
			}else
				flag = node[nodes.rows[i].en_field] > nodes.rows[i].key_word;
			break;
		case '974':
			if(nodes.rows[i].field_type == "Date"){
				var d1 = new Date(node[nodes.rows[i].en_field]);  
				var d2 = new Date(nodes.rows[i].key_word.replace(/\-/g, "\/"));
				flag = d1>=d2;
			}else
				flag = node[nodes.rows[i].en_field] >= nodes.rows[i].key_word;
			break;
		case '975':
			if(nodes.rows[i].field_type == "Date"){
				var d1 = new Date(node[nodes.rows[i].en_field]);  
				var d2 = new Date(nodes.rows[i].key_word.replace(/\-/g, "\/"));
				flag = d1<d2;
			}else
				flag = node[nodes.rows[i].en_field] < nodes.rows[i].key_word;
			break;
		case '976':
			if(nodes.rows[i].field_type == "Date"){
				var d1 = new Date(node[nodes.rows[i].en_field]);  
				var d2 = new Date(nodes.rows[i].key_word.replace(/\-/g, "\/"));
				flag = d1<=d2;
			}else
				flag = node[nodes.rows[i].en_field] <= nodes.rows[i].key_word;
			break;
		case '977':
			flag = node[nodes.rows[i].en_field].startWith(nodes.rows[i].key_word);
			break;
		case '978':
			flag = !node[nodes.rows[i].en_field].startWith(nodes.rows[i].key_word);
			break;
		case '979':
			flag = node[nodes.rows[i].en_field].endWith(nodes.rows[i].key_word);
			break;
		case '980':
			flag = !node[nodes.rows[i].en_field].endWith(nodes.rows[i].key_word);
			break;
		case '981':
			if(node[nodes.rows[i].en_field].indexOf(nodes.rows[i].key_word)>=0)
				flag = true;
			break;
		case '982':
			if(node[nodes.rows[i].en_field].indexOf(nodes.rows[i].key_word)==-1)
				flag = true;
			break;
		default:
			util.show("筛选出错啦");
		}
		//根据条件筛选结果
		if(nodes.rows[i].contact == "or"){
			result = result||flag;
		}else{
			result = result&&flag;
		}
	}
	return result;
}

</script>

 <div class="easyui-layout" fit="true">
     <div region="center" border="false" class="Dialog-Bg"  >
         <div class="Dialog-formDiv clearfix">
            <form:form id="_form" action="" method="get" onSubmit="return false;">
                 <input id="${win}_fields" type="hidden" value="${fields}" />
				 <input id="${win}_fieldNames" type="hidden" value="${fieldNames}" />
				 <input id="${win}_className" type="hidden" value="${className}" />
				 <div class="float-l">
					<div class="Dialog-form-title">
						<label for="${win}_field_name" class="field">字段名：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="${win}_field_name" name="${win}_field_name" data-options="required:true" class="easyui-combobox"/>
					</div>
				 </div>
				  <div class="float-l">
					<div class="Dialog-form-title">
						<label for="${win}_compare_realation" class="field">比较关系：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="${win}_compare_realation" name="${win}_compare_realation" data-options="required:true" class="easyui-combobox"/>
					</div>
				 </div>
				 <div class="float-l">
					<div class="Dialog-form-title">
						<label for="${win}_key_word" class="field">关键字：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="${win}_key_word" name="${win}_key_word" data-options="required:true" class="easyui-validatebox"/>
					</div>
				 </div>
            </form:form>
         </div>
         <!-- 显示已经添加的筛选条件-->
         <div data-options="region:'center',border:false" style="height:270px">
			<table id="${win}_screening_dg"></table>
		 </div>
     </div>
     
     <div data-options="region:'south',border:false" style="text-align: right;">
         <input id="" class="btn-save" onmouseover="this.className='btn-save-over'" 
         onmouseout="this.className='btn-save'" onmousedown="this.className='btn-save-down'" 
         type="button" onclick="reloadDatagrid();" />
         <input id="" class="btn-cancel" onmouseover="this.className='btn-cancel-over'" 
         onmouseout="this.className='btn-cancel'" onmousedown="this.className='btn-cancel-down'" 
         type="button" onclick="util.closeWindow('${win}');" />
     </div>
</div>