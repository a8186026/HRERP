<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
$(function() {
	// 初始化表格
	util.treegrid('menu_grid', {
		url : global_param.context_name + "/system/permission/menuFuncs/${id}", // 数据来源
		title : '菜单列表', // 标题
		idField : 'menu_id',
		parentField : 'parent_menu',
		treeField : 'menu_name',
		sortName : 'menu_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		singleSelect : false, // 多选
		queryParams : {},
		columns : [ [ {
			field : 'menu_name',
			title : '名称',
			width : 10,
			sortable : true
		}, {
			field : 'menu_code',
			title : '菜单编码',
			width : 10,
			sortable : true
		}, {
			field : 'ck',
			checkbox : true,
			width : 2
		}, {
			field : 'menu_func',
			title : '配置功能',
			width : 10,
			sortable : true,
			formatter : function(value, row, index) {
				value = value ? value : 0;
				var v = value + "";
				while (v.length < 8) {
					v = v + " ";
				}
				v = v.replace(/ /g, "&ensp;");
				return '<a href="javacript:;" onclick="selectFunction(' + row.menu_id + ', ' + value + ', event);return false;">' + v + '</a>';
			}
		} ] ],
		onLoadSuccess : function(row, data) {
			<c:forEach items="${sysPermission.gmenus}" var="menu">
				$("#menu_grid").treegrid('select', "${menu.menu_id}");
			</c:forEach>
			$("#menu_grid").treegrid('expandAll');
			$("#menu_grid").treegrid('options').onSelect = function(row) {
				setFuncValue(row.menu_id);
			};
			$("#menu_grid").treegrid('options').onUnselect = function(row) {
				setFuncValue(row.menu_id, 0);
			};
		},
		toolbar : [ {
			text : '保存',
			iconCls : 'icon-add',
			handler : savePermissionFunc
		} ]
	});
	// 初始化编辑窗口
	util.window('funcWindow', {
		title : '功能配置'
	});
});

function setFuncValue(id, val) {
	var def_val = ${defaultValue};
	val = val != null ? val : def_val;
	$('#menu_grid').treegrid('update',{
		id : id,
		row: {
			menu_func : val
		}
	});
}

function savePermissionFunc() {
	// TODO 将菜单、功能整理成字符串
	var data = $("#menu_grid").treegrid('getSelections');
	var ms = [];
	var fs = [];
	for (var i = 0; i < data.length; i++) {
		ms.push(data[i].menu_id);
		fs.push(data[i].menu_func);
	}
	$("#permissions").val(ms.join(","));
	$("#functions").val(fs.join(","));
	
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

function selectFunction(id, value, event) {
	var e = (event) ? event : window.event; //判断浏览器的类型，在基于ie内核的浏览器中的使用cancelBubble  
	if (window.event) {
		e.cancelBubble = true;
	} else {
		e.stopPropagation();
	}
	util.openWindow('funcWindow', "permission/function/" + id + "/" + value);
}
</script>

<div class="easyui-layout" fit="true" style="width: 100%; height: 100%;">
	<div region="north" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
			<form:form id="_form" action="${formUrl}" method="${method}">
				<input name="SPRING_TOKEN" type="hidden"
					value="${sessionScope.SPRING_TOKEN}" />
				<div class="float-l">
					<div class="Dialog-form-title">
						<label for="group_name" class="field">用户组：</label>
					</div>
					<div class="Dialog-form-item">
						<input id="group_name" class="easyui-validatebox"
							readonly="readonly" value="${sysPermission.group_name}" /> <input
							id="permissions" name="permissions" type="hidden" /> <input
							id="functions" name="functions" type="hidden" />
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<div data-options="region:'center',border:false"
		style="overflow: hidden; background: #fff;">
		<table id="menu_grid"></table>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input class="btn-save" onmouseover="this.className='btn-save-over'"
			onmouseout="this.className='btn-save'"
			onmousedown="this.className='btn-save-down'" type="button"
			onclick="savePermissionFunc();" /> <input class="btn-cancel"
			onmouseover="this.className='btn-cancel-over'"
			onmouseout="this.className='btn-cancel'"
			onmousedown="this.className='btn-cancel-down'" type="button"
			onclick="util.closeWindow('editWindow');" />
	</div>
</div>
<!-- 编辑窗口 -->
<div id="funcWindow"></div>