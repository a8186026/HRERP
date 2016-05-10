<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<script type="text/javascript">
	$("#${ctrl_id}_grid").datagrid({
		url: global_param.context_name+ "/system/user/getConfigs?page_id=" + $("#input_${page_id}").val()
		+ "&ctrl_id=" + $("#input_${ctrl_id}").val(),
		method:'get',
		title:'用户习惯配置',
		sortName : 'habit_order', // 排序的列
		sortOrder : 'asc', // 排序方式
		rownumbers : true, // 显示行号
		height:420,
		queryParams : {},
		columns : [ [{
			field : 'ck',
			checkbox : true,
			width : 2
		}, {
			field : 'habit_field_name',
			title:'字段名',
			width : 100
		}] ],
		onLoadSuccess : function(data) {
			//获取显示的列
			var opts = $("#"+$("#input_${ctrl_id}").val()).datagrid('getColumnFields');
			//和默认所有列比较，如果相同，则选中
			for (var i = 0; i < data.total; i++) {
				for (var j = 0; j < opts.length; j++) {
				// 比对用户习惯配置，如果有则标记选中
				if (opts[j] == data.rows[i].habit_field){ 
						$("#${ctrl_id}_grid").datagrid('selectRow',data.rows[i].habit_order);
					}
				}
			} 
		},
		toolbar : [ {
			text : '保存',
			iconCls : 'icon-save',
			handler : addUserHabit
		} ]
	}); 

	
	function addUserHabit() {
		var habit_field = "";
		var habit_field_name = "";
		var nodes = $("#${ctrl_id}_grid").datagrid('getSelections');//获取所有选中的行
	
		//取出选中行数据并分别保存
		for (var i = 0; i < nodes.length; i++) {
			habit_field += nodes[i].habit_field;
			habit_field_name += nodes[i].habit_field_name;
			if (i < nodes.length - 1) {
				habit_field = habit_field + ",";
				habit_field_name = habit_field_name + ",";
			}
		}

		//提交 选择显示的页面属性
		$.ajax({
			type : "post", // 表单提交类型
			url : global_param.context_name
					+ "/system/user/config?habit_field=" + habit_field
					+ "&habit_field_name=" + habit_field_name
					+ "&page_id="+$("#input_${page_id}").val()+"&ctrl_id="+$("#input_${ctrl_id}").val(), // 表单提交目标
			success : function(data) {
				if (data.result == "success") {
					util.show("保存成功!");
				}
			}
		}); 
		util.closeWindow(this);
		location.reload();
	}
	
</script>

<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
			<form:form id="_form" action="${formUrl}" method="${method}" onsubmit="return false;">
			<input id="input_${page_id}" type="hidden" value="${page_id}">
			<input id="input_${ctrl_id}" type="hidden" value="${ctrl_id}">			 
			</form:form>
		</div>
	</div>
	
	<!-- 显示区域 -->
		<div id="user_habits" data-options="region:'center',border:false">
			<table id="${ctrl_id}_grid"></table>
		</div>
</div>

