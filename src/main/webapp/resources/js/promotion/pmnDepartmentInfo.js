$(function() {
	
	$('#timetype').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=626",
		method :"get",
		valueField : "cbs_id",
		textField : "cbs_chn",
		editable:false,
		selectOnNavigation:true,
        onSelect: function(node){ 
			
            $('#timetype').val(node.timetype);
            $('#multiple').val(node.multiple);
            $('#noexecuteprice').val(node.noexecuteprice);
            $('#nodiscount').val(node.nodiscount);
            $('#executeprice').val(node.executeprice);
            $('#discount').val(node.discount);
            $('#date').val(node.date);
            $('#startdate').val(node.startdate);
            $('#enddate').val(node.enddate);
            $('#memberdaytype').val(node.memberdaytype);
            $('#disconttype').val(node.disconttype);
           /* supCode=$('#supply_number').val();
 	    	 $("#"+checkNumber+"_form").attr("action",preformUrl+"/"+supCode);*/
 	    	
 	    	
         },
         onChange: function(n, o) {

             if (n == 627) {
                 //div不显示，根据divid处理
                 $("#DATE" ).css("display", "none");
                // document.getElementById('DATE').style.display = 'none';
                 //div显示
                 
                 document.getElementById('STARTDATE').style.display = 'block';
                document.getElementById('ENDDATE').style.display = 'block';
             } else {
                 document.getElementById('DATE').style.display = 'block';
                 $("#STARTDATE" ).css("display", "none");
                 $("#ENDDATE" ).css("display", "none");
                // document.getElementById('STARTDATE').style.display = 'none';
                // document.getElementById('ENDDATE').style.display = 'none';
             }
         },
		onLoadSuccess: function(){
			timetypeList = $('#timetype').combobox("getData");}
	});
	
	// 会员日下拉框
	$('#memberdaytype').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=624",
		method :"get",
		valueField : "cbs_id",
		textField : "cbs_chn",
		editable:false,
		selectOnNavigation:true,
		onLoadSuccess: function(){
			memberdaytypeList = $('#memberdaytype').combobox("getData");}
	});
	$('#noexecuteprice').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=650",
		method :"get",
		valueField : "cbs_id",
		textField : "cbs_chn",
		editable:false,
		selectOnNavigation:true,
		onLoadSuccess: function(){
			noexecutepriceList = $('#noexecuteprice').combobox("getData");}
	});
	$('#executeprice').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=650",
		method :"get",
		valueField : "cbs_id",
		textField : "cbs_chn",
		editable:false,
		selectOnNavigation:true,
		onLoadSuccess: function(){
			noexecutepriceList = $('#executeprice').combobox("getData");}
	});
	// 折扣类型下拉框
	$('#discounttype').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=631",
		method :"get",
		valueField : "cbs_id",
		textField : "cbs_chn",
		editable:false,
		selectOnNavigation:true,
		onLoadSuccess: function(){
			discounttypeList = $('#discounttype').combobox("getData");}
	});
	
	
	//初始化会员日的表
	$('#memdaydg').datagrid( {
		url : global_param.context_name + "/promotion/pmnMemday/lists", // 数据来源
		title : '折扣日列表', // 标题
		sortName : 'memid', // 排序的列
		sortOrder : 'asc', // 排序方式
		pagination : false, // 显示分页
		fit:true,
		singleSelect:true,
		method:"get",
		queryParams : {},
		columns : [ [ /*{
			field : 'timetype',
			title : '时间方式',
			sortable : true,
			formatter :function(value, row, index){
				for(var i = 0 ; i < timetypeList.length ;i++){
					if(value == timetypeList[i].cbs_id){
						return timetypeList[i].cbs_chn;
					}
				}
				return "";
			}
		
		}, {
			field : 'date',
			title : '日期',
			sortable : true,
			formatter : function (value, row, index) {
				return util.formatDateNoMin(value);}
		}, */{
			field : 'multiple',
			title : '积分倍数',
			sortable : true
		} ,  {
			field : 'noexecuteprice',
			title : '非会员日折扣类型',
			sortable : true
			
		},  {
			field : 'nodiscount',
			title : '非会员日折扣',
			sortable : true
		},{
			field : 'executeprice',
			title : '会员日折扣类型',
			sortable : true
			
		}, {
			field : 'discount',
			title : '会员日折扣',
			sortable : true	
		},
		   {
			field : 'startdate',
			title : '起始日期',
			sortable : true,
			/*formatter : function (value, row, index) {
				return util.formatDateNoMin(value);}*/
		}, {
			field : 'enddate',
			title : '结束日期',
			sortable : true,
			/*formatter : function (value, row, index) {
				return util.formatDateNoMin(value);}*/
		}, {
			field : 'memberdaytype',
			title : '会员日类型',
			sortable : true,
			/*formatter :function(value, row, index){
				for(var i = 0 ; i < memberdaytypeList.length ;i++){
					if(value == memberdaytypeList[i].cbs_id){
						return memberdaytypeList[i].cbs_chn;
					}
				}
				return "";
			}*/
		},  {
			field : 'discounttype',
			title : '折扣类型',
			sortable : true,
		/*	formatter :function(value, row, index){
				for(var i = 0 ; i < discounttypeList.length ;i++){
					if(value == discounttypeList[i].cbs_id){
						return discounttypeList[i].cbs_chn;
					}
				}
				return "";
			}*/
		}] ],
		toolbar : [ {
			
			text : '增加',
			iconCls : 'icon-add',
			handler : addMemday
		}, {
			text : '修改',
			iconCls : 'icon-modify',
			handler : updateMemday
		}, {
			text : '删除',
			iconCls : 'icon-delete',
			handler : deleteMemday
	
		} ]
	});
	
			//参加活动部门表
		util.table('updg', {
			url : global_param.context_name + "/promotion/pmnDepart/uplist", // 数据来源
			title : '参加该活动部门', // 标题
			sortName : 'department_id', // 排序的列
			sortOrder : 'asc', // 排序方式
			singleSelect : true,//单选
			pageSize : 5,
			pageList : [5,10,20,50,100],
			fit : true,
			//triped : true,//奇偶行颜色不同
			pagination : true,
			rownumbers : true,
			nowrap : true,
			striped : true,
			 onDblClickRow :function(rowIndex,rowData){
				 disableFunc(rowData);
				  },
			queryParams : {},
			columns:[ [ {
				field : 'department_number',
				title : '部门编号',
				width : 30,
				sortable : true
			}, {
				field : 'department_fullname',
				title : '全称',
				width : 30,
				sortable : true
			}, {
				field : 'department_chnpy',
				title : '拼音码',
				width : 30,
				sortable : true
			}, {
				field : 'department_shortname',
				title : '简称',
				width : 30,
				sortable : true
			}
			  ] ],//
			toolbar : [ {
					text : '禁用',
					iconCls : 'icon-disable',
					width : 10,
					handler : disableFunc
				}, {
					text : '修改',
					iconCls : 'icon-modify',
					handler : updateFunc
				}]}
		);


		// 初始化表格
		util.table('downdg',{
			url : global_param.context_name + "/promotion/pmnDepart/list", // 数据来源
			title : '不参加该活动的部门', // 标题
			sortName : 'department_id', // 排序的列
			sortOrder : 'asc', // 排序方式
			singleSelect : true,//单选
			pageSize : 5,
			pageList : [5,10,20,50,100],
			fit : true,
			//triped : true,//奇偶行颜色不同
			pagination : true,
			rownumbers : true,
			nowrap : true,
			striped : true,
			 onDblClickRow :function(rowIndex,rowData){
				 enableFunc(rowData);
				  },
			columns:[ [ {
				field : 'department_number',
				title : '部门编号',
				width : 350,
				sortable : true
			}, {
				field : 'department_fullname',
				title : '全称',
				width : 350,
				sortable : true
			}, {
				field : 'department_chnpy',
				title : '拼音码',
				width : 350,
				sortable : true
			}, {
				field : 'department_shortname',
				title : '简称',
				width : 350,
				sortable : true
			}] ],
			toolbar : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : addFunc
			},  {
				text : '删除',
				iconCls : 'icon-delete',
				handler : deleteFunc
			},{
				text : '启用',
				iconCls : 'icon-enable',
				width : 10,
				handler : enableFunc
			} ]}
		);
		
		
		// 初始化编辑窗口
		util.window('editWindow', {
			title : '添加会员日'
		
		}); 

});

function submitForm() {
	form = $("#" + memdayform);
	form.form('enableValidation');
	$.ajax({
		url : global_param.context_name + "/promotion/pmnDepart/{department_id}", // 数据来源 form.attr("action"),
		data : form.serializeArray(),
		async : false,
		type : form.attr("method") ? form.attr("method") : "post",
		success : function(data) {
			result = data;
		}
	});

    if (data) {
        if (data.result == "success") {

            util.show(data.message);

            $('#updg').datagrid('reload');

        } else {
            util.error(data.message);
        }
    }
}

//下表
function queryFormDown() {
	$('#downdg').datagrid('options').queryParams = util.formParams("searchFormDown");
	$('#downdg').datagrid('reload');
}

//上表
function queryFormUp() {
	$('#updg').datagrid('options').queryParams = util.formParams("searchFormUp");
	$('#updg').datagrid('reload');
}
function addFunc() {
	util.openWindow('editWindow', "pmnDepart/new");
}

function updateFunc() {
	var node = $('#updg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editWindow', "pmnDepart/" + node.department_id);
}

function deleteFunc() {
	var node = $('#downdg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/promotion/pmnDepart/" + node.department_id);
		if (data.result == "success") {
			$('#downdg').datagrid('reload');
		}
		util.show(data.message);
	});
}
function enableFunc(rowData) {
	var node = $('#downdg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	var data = util.put(global_param.context_name + "/promotion/pmnDepart/" + node.department_id + "/enable");
	if (data.result == "success") {
		$('#downdg').datagrid('reload');
		$('#updg').datagrid('reload');
	}
	util.show(data.message);
}

function disableFunc(rowData) {
	var node = $('#updg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	var data = util.put(global_param.context_name + "/promotion/pmnDepart/" +  node.department_id + "/disable");
	if (data.result == "success") {
		$('#updg').datagrid('reload');
		$('#downdg').datagrid('reload');
	}
	util.show(data.message);
}
function addMemday() {
	util.openWindow('memdayEditWindow', "pmnMemday/newMemday");
}

function updateMemday() {
	var node = $('#memdaydg').treegrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
	
	} else {
		util.openWindow('memdayEditWindow', "pmnMemday/updateMemday/" + node.memid);
	}
}

function deleteMemday() {
	var node = $('#memdaydg').treegrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("此操作将会删除该页面下所有信息，确定要执行删除操作？", function() {
		var data;
			 data=util.del(global_param.context_name + "/promotion/pmnMemday/delMemday/" + node.memid);
		
		if (data.result == "success") {
			$('#memdaydg').datagrid('reload');

		}
		util.show(data.message);
	});
}