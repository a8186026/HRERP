$(function() {
	var dosageTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=98");
	var units = util.getList(global_param.context_name + "/system/combobox/lists?pid=132");
	var othercategorys = util.getList(global_param.context_name + "/system/combobox/lists?pid=244");
	
	$('#search_department_id').combobox({
	url:global_param.context_name + "/basInfo/basDepartmentInfo/getList",
	method :"get",
	valueField : "department_id",
	textField : "department_fullname",
	});
	
	$('#search_medinsuvariety').combobox({    
		data: [{    
		    "id":0,    
		    "text":"非医保"   
		},{    
		    "id":1,    
		    "text":"医保"   
		}],
	    valueField:'id',    
	    textField:'text',   
	});  
	$('#search_medinsuvariety').combobox("clear");


	// 初始化表格
	$("#dg").datagrid({
		method : 'get',
		//url : global_param.context_name + "/product/proPriceTag/lists", // 数据来源
		title : '价签打印', // 标题
		sortName : 'product_id', // 排序的列
		sortOrder : 'asc', // 排序方式
		//fitColumns : true,
		//singleSelect : true, // 单选
		ctrlSelect: true,
		pageSize : 10,
		pageList : [10, 20, 50, 100],
		fit : true,
		//striped : true, // 奇偶行颜色不同
		//pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		nowrap : true,
		striped : true, // 奇偶行颜色不同
		queryParams : {},
		columns :
			[ [ { 
			field : 'product_code',
			title : '产品编号',
			width : 100,
			sortable : true
		}, {
			field : 'product_name',
			title : '产品名称',
			width : 100,
			sortable : true
		},  {
			field : 'product_specification',
			title : '规格',
			width : 100,
			sortable : true
		},  {
			field : 'product_unit',
			title : '单位',
			sortable : true,
			formatter :function(value, row, index){
				for(var i = 0 ; i < units.length ;i++){
					if(value == units[i].cbs_id){
						return units[i].cbs_chn;
					}
				}
			}
		}, {
			field : 'product_productarea',
			title : '产地',
			width : 100,
			sortable : true
		},  {
			field : 'product_dosagetype',
			title : '剂型',
			width : 100,
			sortable : true,
			formatter :function(value, row, index){
				for(var i = 0 ; i < dosageTypes.length ;i++){
					if(value == dosageTypes[i].cbs_id){
						return dosageTypes[i].cbs_chn;
					}
				}
			}
		},  {
			field : 'product_retailprice',
			title : '零售价',
			width : 100,
			sortable : true
		}, {
			field : 'product_memberprice',
			title : '会员价',
			width : 100,
			sortable : true
		},  {
			field : 'product_othercategory',
			title : '其它分类',
			width : 100,
			sortable : true,
			formatter :function(value, row, index){
				for(var i = 0 ; i < othercategorys.length ;i++){
					if(value == othercategorys[i].cbs_id){
						return othercategorys[i].cbs_chn;
					}
				}
			}
		}] ],
		/*toolbar : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : addFunc
		},{
			text : '修改',
			iconCls : 'icon-modify',
			handler : updateFunc
		},{
			text : '删除',
			iconCls : 'icon-delete',
			handler : deleteFunc
		}]*/
	});
	
	
	
	// 初始化编辑窗口
	util.window('editWindow', {
		title : '价签申请'
	});
});

//库存品种
function queryStockProduct() {
	
	var data = util.get(global_param.context_name + "/product/proInfoManage/lists?product_check=0&product_medinsuvariety="+$("#search_medinsuvariety").combobox('getValue'));
	$("#dg").datagrid("loadData",data);
}

//调价品种
function queryStockPriceProduct(){
	
}

//指定品种
function querySpecifyProduct() {
	
	var data = util.get(global_param.context_name + "/product/proPriceTagPrint/querySpecify?start_time=" +$("#search_start_time").datebox('getValue')
																			 +"&department_id="+$("#search_department_id").combobox('getValue')
																			 +"&end_time="+$("#search_end_time").datebox('getValue')
																			 +"&medinsuvariety="+$("#search_medinsuvariety").combobox('getValue'));
	$("#dg").datagrid("loadData",data);
}

