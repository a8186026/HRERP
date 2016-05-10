var json="";
$(function() {
	// 初始化表格
	$('#group_id').combobox({
		url : global_param.context_name
				+ "/system/permission/groups",
		method : 'get',
		valueField : 'group_id',
		textField : 'group_name',
		editable:false,
		onChange : function change(n, o) {
			//根据组获取用户列表
			var queryParams = $('#u_dg').datagrid('options').queryParams;
			queryParams.group_id = n;
			$("#u_dg").datagrid('reload');
			
			//刷新页面数据表
			$("#pagedg").datagrid('reload');
			
			//刷新控件数据表
			$('#componentsdg').datagrid('options').url="";
			$('#componentsdg').datagrid('loadData', { total: 0, rows: [] });
			
			//获取某组的权限
			$.ajax({
				type : "get", // 表单提交类型
				url : global_param.context_name + "/system/permission/getGroupMenu?group_id="+n, // 表单提交目标
				data :"json", // 表单数据
				success : function(data) {
					json=data;
					$("#menu_dg").treegrid('reload');
				}
			});
		}
	});
	
	util.table('u_dg', {
		url : global_param.context_name + "/system/permission/getUsersByGroup", // 数据来源
		title : '用户组用户列表', // 标题
		method:'get',
		sortName : 'user_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		rownumbers : true, // 显示行号
		pagination : false, // 显示分页
		queryParams : {
			group_id:""
		},
		columns : [ [ {
			field : 'user_name',
			title : '登录名',
			width : 120,
			sortable : true
		}, {
			field : 'display_name',
			title : '用户姓名',
			width : 120,
			sortable : true
		}] ],
	});
	
	util.table('pagedg', {
		url : global_param.context_name + "/system/pageComponent/pages", // 数据来源
		title : '页面列表', // 标题
		sortName : 'id', // 排序的列
		sortOrder : 'asc', // 排序方式
		pagination : false, // 显示分页
		queryParams : {},
		columns : [ [ {
			field : 'page_name',
			title : '页面名称',
			width : 10,
			sortable : true
		}, {
			field : 'page_jsp',
			title : 'jsp名称',
			width : 20,
			sortable : true
			
		} ] ]
	});
	
	$('#componentsdg').datagrid({
		url : "", // 数据来源
		method:'get',
		title : '控件列表', // 标题
		sortName : 'id', // 排序的列
		sortOrder : 'asc', // 排序方式
		pagination : false, // 显示分页
		rownumbers : true, // 显示行号
		singleSelect : true,
		queryParams : {},
		columns : [ [ {
			field : 'ctrl_name',
			title : '控件名称',
			width : 150,
			sortable : true
		}, {
			field : 'ctrl_id',
			title : '控件ID',
			width : 180,
			sortable : true
	
		}, {
			field : 'ctrl_div_id',
			title : '所属DivID',
			width : 180,
			sortable : true
		}, {
			field : 'ctrl_type',
			title : '控件类型',
			width : 150,
			sortable : true
			
		},{
			field: 'Hidden',
			title : '可见',
			width : 50,
			formatter:function(value,row,index){
				//插入自动生成的动态ID：HiddenCheckBox+index
				return '<input id="HiddenCheckBox'+index+'" type="checkbox" name="DataGridCheckbox" checked="checked" style="width:35px" onchange="checkBoxChange(this);">';
			}
		},{
			field: 'ReadOnly',
			title : '可改',
			width : 50,
			formatter:function(value,row,index){
				//插入自动生成的动态ID：ReadOnlyCheckBox+index
				return '<input id="ReadOnlyCheckBox'+index+'" type="checkbox" name="DataGridCheckbox" checked="checked" style="width:35px" onchange="checkBoxChange(this);">';
			}
		},{
			field: 'Written',
			title : '必填',
			width : 50,
			formatter:function(value,row,index){
				//插入自动生成的动态ID：WrittenCheckBox+index
				return '<input id="WrittenCheckBox'+index+'" type="checkbox" name="DataGridCheckbox" style="width:35px" onchange="checkBoxChange(this);">';
			}
		}  ] ],	onLoadSuccess : function() {
			loadComponentDG();
		},
		toolbar : [ {
			text : '保存',
			iconCls : 'icon-add',
			handler : SaveCtrlPermisionFunc
		} , {
			text : '刷新',
			iconCls : 'icon-reload',
			handler : function() {
				loadComponentDG();
			}
		} ]
	});
	//如果点击左侧页面选项后，右面根据所选页面找出当前页面的注册控件
	$('#pagedg').datagrid({
		onClickRow:function(index,data){
			//验证组
			var pageGroupID=$('#group_id').combobox("getValue");
			if(pageGroupID !=null && pageGroupID!=""){
				var url=global_param.context_name + "/system/pageComponent/components";
				$('#componentsdg').datagrid('options').url=url+"/"+data.id;
				$('#componentsdg').datagrid('reload');
			} else{
				util.show("请先选择您要设置的用户组！");
			}		
		}
	});
});


function checkBoxChange(checkBox){
	var checkBoxID = checkBox.id;
	var params = checkBoxID.split("CheckBox");
	//可见为否，必填为否
	if(params[0] == "Hidden" && checkBox.checked == false){
		document.getElementById("WrittenCheckBox"+params[1]).checked = false;
	}
	//必填为真，可见为真，可改为真
	if(params[0] == "Written" && checkBox.checked == true){
		document.getElementById("HiddenCheckBox"+params[1]).checked = true;
		document.getElementById("ReadOnlyCheckBox"+params[1]).checked = true;
	}
}
function query_Page() {
	$('#pagedg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#pagedg').datagrid('reload');
}
function query_Com() {
	$('#componentsdg').datagrid('options').queryParams = util.formParams("searchComForm");
	$('#componentsdg').datagrid('reload');
}

//根据所选组以及页面加载控件文件
function loadComponentDG(){
	var pageGroupID=$('#group_id').combobox("getValue");
	if(pageGroupID !=null && pageGroupID!=""){
		var node = $('#pagedg').treegrid('getSelected');
		if(!node){
			util.show("请选择一个指定的页面！");
		}else{
			var pageName = $('#pagedg').datagrid('getSelected').page_jsp;			
			$.ajax({
				type : "get", // 表单提交类型
				url : global_param.context_name + "/system/ctrlpermission/getCtrlPermission?pageGroupID="+pageGroupID+"&pageName="+pageName, // 表单提交目标
				data :"json", // 表单数据
				success : function(ctrlPermission) {
					//刷新数据表
					var data = $("#componentsdg").datagrid("getData").rows;
					for(var i = 0 , j = 0; i < data.length && j < ctrlPermission.length; i++){
						if(data[i].ctrl_id == ctrlPermission[j].ctrlID){
							//根据需要标记当前位置,只标不同
							if(ctrlPermission[j].readOnly=="false"){
								document.getElementById("ReadOnlyCheckBox"+i).checked = false;
							}
							if(ctrlPermission[j].hidden=="false"){
								document.getElementById("HiddenCheckBox"+i).checked = false;
							}
							if(ctrlPermission[j].written=="true"){
								document.getElementById("WrittenCheckBox"+i).checked = true;
							}
							//移动游标
							j++;
						}
					}
					
				}
			});
		}
	}else{
		util.show("请先选择您要设置的用户组！");
	}
}


//保存控件权限设置
function SaveCtrlPermisionFunc(){
	var pageGroupID=$('#group_id').combobox("getValue");
	if(pageGroupID !=null && pageGroupID!=""){
		var node = $('#pagedg').datagrid('getSelected');
		if (!node) {
			util.show("请选择一个指定的页面！");
		}else{
			var pageName = node.page_jsp;
			var componetRows = $("#componentsdg").datagrid("getData").rows;
			var rowsForSave = "";
			for(var i = 0; i<componetRows.length ; i++){
				
				/* 
				 * 比对当前条，是否有修改，是否是默认值，如果是默认值，则不单独进行保存
				 * 可见默认：真
				 * 可改默认：真
				 * 必填默认：假
				 * 选中时的checked的状态为checked，非选中时的状态为undefined
				 * */
				if($("#ReadOnlyCheckBox"+i).attr("checked")!="checked"||$("#HiddenCheckBox"+i).attr("checked")!="checked"||$("#WrittenCheckBox"+i).attr("checked")=="checked"){
					//如果是要保存的对象,属性以,分割，对象以;分割
					var ctrl_id = componetRows[i].ctrl_id;
					var ctrl_div_id = componetRows[i].ctrl_div_id;
					var ctrl_type = componetRows[i].ctrl_type;
					rowsForSave += ctrl_id+","+ctrl_div_id+","+ctrl_type+",";
					
					if($("#ReadOnlyCheckBox"+i).attr("checked")=="checked"){
						rowsForSave += "true"+",";
					}else{
						rowsForSave += "false"+",";
					}
					
					if($("#HiddenCheckBox"+i).attr("checked")=="checked"){
						rowsForSave += "true"+",";
					}else{
						rowsForSave += "false"+",";
					}
					
					if($("#WrittenCheckBox"+i).attr("checked")=="checked"){
						rowsForSave += "true"+",";
					}else{
						rowsForSave += "false";
					}
					
					rowsForSave += ";";
				}
			}
			//进行插入动作
			$.ajax({
				type : "post", // 表单提交类型
				url : global_param.context_name + "/system/ctrlpermission/saveCtrlPermission?pageGroupID="+pageGroupID+"&pageName="+pageName+"&ctrlPermissions="+rowsForSave, // 表单提交目标
				data :"json", // 表单数据
				success : function(data) {
					if(data.result=="success"){
						util.show(data.message);
					}
						
				}
			});
				
			
		}
	}else{
		util.show("请先选择您要设置的用户组！");
	}
}
