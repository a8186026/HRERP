$(function() {
	// 初始化下拉菜单
	$('#search_cbs_pid').combobox({
		url : global_param.context_name
				+ "/system/combobox/lists?pid=0&type="+$("#cbs_type").val(),
		method : 'get',
		valueField : 'cbs_id',
		textField : 'cbs_chn',
		editable:false,
		onChange : function change(n, o) {
			query();
		}
	});
	
	// 初始化表格
	util.table('dg', {
		url :"", // 数据来源
		title : '下拉菜单管理', // 标题
		sortName : 'cbs_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		pagination : false, // NO显示分页
		queryParams : {},
		columns : [ [{
			field:'pid',
			title : '父级ID',
			width : 10,
			align : 'center',
			sortable : false,
			formatter:function(value,row,index){
				return row.cbs_pid;
			}
		},{
			title : '下拉菜单类别',
			field : 'cbs_pid',
			width : 60,
			align : 'center',
			sortable : false,
			formatter : function (value, row, index) {
				//根据下拉菜单中的根级菜单，将PID转换为汉字
				if($('#search_cbs_pid').combobox("getText")!=null&&$('#search_cbs_pid').combobox("getText")!=""){
					return $('#search_cbs_pid').combobox("getText");
				}
				else{
					var data = $('#search_cbs_pid').combobox("getData");
					for(var i=0;i<data.length;i++)
					{
						if(data[i].cbs_id == value){
							var result = data[i].cbs_chn;
							return result;
						}
					}
					return "";
				}
			}
		},{
			title : '编码',
			field : 'cbs_code',
			width : 30,
			align : 'center',
			sortable : false
		}, {
			title : '汉字',
			field : 'cbs_chn',
			width : 50,
			align : 'center',
			sortable : false
		}, {
			title : '说明',
			field : 'cbs_dec',
			width : 100,
			align : 'center',
			sortable : false,
		}] ],
		toolbar : [ {
			text : '新增',
			iconCls : 'icon-add',
			handler : addCBInfo
		},{
			text : '修改',
			iconCls : 'icon-modify',
			handler : updateCBInfo
		}, {
			text : '删除',
			iconCls : 'icon-delete',
			handler : deleteCBInfo
		}]
	});
	// 初始化编辑窗口
	util.window('editWindow', {
		title : '下拉菜单选项编辑'
	});
});

function query() {
	//var chs_pid=$('#search_cbs_pid').combobox("getValue"); 
	$('#dg').datagrid('options').url = global_param.context_name+"/system/combobox/lists?type="+$("#cbs_type").val();
	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#dg').datagrid('reload');
	$('#dg').datagrid("unselectAll");
}

function addCBInfo() {
	//得到父级类别的ID以及汉字名称传至后台
	var chs_pid=$('#search_cbs_pid').combobox("getValue");
	var cbs_pchn=$('#search_cbs_pid').combobox("getText");
	util.openWindow('editWindow', "combobox/new?cbs_pid="+chs_pid+"&cbs_pchn="+cbs_pchn+"&type="+$("#cbs_type").val());
}

function updateCBInfo() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	var cbs_pchn=$('#search_cbs_pid').combobox("getText");
	util.openWindow('editWindow', "combobox/" + node.cbs_id+"?cbs_pchn="+cbs_pchn+"&type="+$("#cbs_type").val());
}

function deleteCBInfo() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		//判断当前DG是否有值，如果没有值，则删除父节点，如果有标明选中
		util.confirm("确定要执行删除当前的父级类别？", function() {
			var chs_pid=$('#search_cbs_pid').combobox("getValue");
			var data = util.del(global_param.context_name + "/system/combobox/" + chs_pid+"?cbs_pid=0");
			if (data.result == "success") {
				//重置页面
				pageReset();
				//删除父级目录时重新载入下拉框
				$('#search_cbs_pid').combobox('reload');      
			}
			util.show(data.message);
		});
	}else{
		//不为空时删除子数据
		util.confirm("确定要执行删除操作？", function() {
			var data = util.del(global_param.context_name + "/system/combobox/" + node.cbs_id+"?cbs_pid="+node.cbs_pid);
			if (data.result == "success") {
				$('#dg').datagrid('reload');
			}
			util.show(data.message);
		});
	}
}

//清空键触发另写
function pageReset(){
	$('#searchForm').form('reset');
	//清空dg的URL防止查空
	$('#dg').datagrid('options').url = "";
	//清空dg数据
	$('#dg').datagrid('loadData', { total: 0, rows: [] });
}