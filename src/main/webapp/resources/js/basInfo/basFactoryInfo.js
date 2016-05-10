$(function() {
	//第一个参数页面ID,第二个参数datagrid ID，第三个参数配置显示框ID,第四个参数是否启动上下键换行，第五个参数是否启动列拖拽
	var userHabits1 = new userHabits();
	userHabits1.loadColumns("basFactoryInfoList","dg","editWindow",true,true);
	
	// 初始化表格
	$("#dg").datagrid({
		method : 'get',
		url : global_param.context_name + "/basInfo/basFactoryInfo/lists", // 数据来源
		title : '厂家档案管理', // 标题
		sortName : 'factory_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		//fitColumns : true,
		singleSelect : true, // 单选
		pageSize : 10,
		pageList : [10, 20, 50, 100],
		fit : true,
		//striped : true, // 奇偶行颜色不同
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		nowrap : true,
		striped : true, // 奇偶行颜色不同
		queryParams : {},
		/*columns :[ [ {
			field : 'factory_filenumber',
			title : '档案号',
			width : 100,
			sortable : true
		}, {
			field : 'factory_code',
			title : '编码',
			width : 50,
			sortable : true
		}, {
			field : 'factory_areacode',
			title : '区号',
			width : 100,
			sortable : true
		}, {
			field : 'factory_name',
			title : '厂家名称',
			width : 100,
			sortable : true
		}, {
			field : 'factory_shortname',
			title : '简称',
			width : 100,
			sortable : true
		}, {
			field : 'factory_chnpy',
			title : '拼音码',
			width : 100,
			sortable : true
		}, {
			field : 'factory_website',
			title : '网址',
			width : 150,
			sortable : true
		}, {
			field : 'factory_address',
			title : '地址',
			width : 200,
			sortable : true
		}, {
			field : 'factory_zipcode',
			title : '邮编',
			width : 100,
			sortable : true
		}, {
			field : 'factory_tel',
			title : '电话 ',
			width : 150,
			sortable : true
		}, {
			field : 'factory_contactperson',
			title : '联系人',
			width : 100,
			sortable : true
		}, {
			field : 'factory_chiefperson',
			title : '负责人',
			width : 100,
			sortable : true
		}, {
			field : 'factory_productvariety',
			title : '生产品种',
			width : 100,
			sortable : true
		}, {
			field : 'factory_description',
			title : '简介',
			width : 100,
			sortable : true
		}, {
			field : 'factory_license',
			title : '厂家证照 ',
			width : 100,
			sortable : true
		} ] ],*/
		toolbar : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : addFunc
		}, {
			text : '修改',
			iconCls : 'icon-modify',
			handler : updateFunc
		}, {
			text : '删除',
			iconCls : 'icon-delete',
			handler : deleteFunc
		}]
	});
	// 初始化编辑窗口
	util.window('editWindow', {
		title : '厂家档案信息'
	});
	
});

function query() {
	$('#dg').datagrid('options').queryParams = util.formParams("searchForm");
	$('#dg').datagrid('reload');
}

function addFunc() {
	util.openWindow('editWindow', "basFactoryInfo/new");
}

function updateFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.openWindow('editWindow', "basFactoryInfo/" + node.factory_id);
}

function deleteFunc() {
	var node = $('#dg').datagrid('getSelected');
	if (!node) {
		util.show("请选择一条记录");
		return;
	}
	util.confirm("确定要执行删除操作？", function() {
		var data = util.del(global_param.context_name + "/basInfo/basFactoryInfo/" + node.factory_id);
		if (data.result == "success") {
			$('#dg').datagrid('reload');
		}
		util.show(data.message);
	});
}