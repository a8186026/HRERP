<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript">
$.extend($.fn.datagrid.methods, {
	//键盘上下键换行
    keyCtr : function (jq) {
        return jq.each(function () {
            var grid = $(this);
            grid.datagrid('getPanel').panel('panel').attr('tabindex', 1).bind('keydown', function (e) {
            	var rows = grid.datagrid('getRows');
                var selected = grid.datagrid('getSelected');
                var index = grid.datagrid('getRowIndex', selected);
                switch (e.keyCode) {
                case 38: // up
                    if (index>0) {
                        grid.datagrid('selectRow', index - 1);
                    } else {
                        grid.datagrid('selectRow', rows.length - 1);
                    }
                    break;
                case 40: // down
                    if (index<rows.length - 1) {
                        grid.datagrid('selectRow', index + 1);
                    } else {
                        grid.datagrid('selectRow', 0);
                    }
                    break;
                }
            });
        });
    }
});

	$(function() {
		//原下拉菜单数据，用于转换显示列的内容
		var dosageTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=98");
		var packingunitTypes = util.getList(global_param.context_name + "/system/combobox/lists?pid=125");
		
		
		
		var pro_content = '${proContent}';
		var product_code = null;
		var product_chnpy = null;
		//根据父级页面输入框中的输入数据，进行分支，包装URL中的搜索条件
		if(pro_content == '%'){
			//doNothing
		}else if(/^\d+$/.test(pro_content)){
			//纯数字，为编码
			product_code = pro_content;
		}else{
			//拼音码
			product_chnpy = pro_content;
		}
		
		
		
		
		/*准备显示数据*/
		$("#pro_dg").datagrid({
			method : 'get',
			url : global_param.context_name + "/product/proInfoManage/lists?product_check=0", // 数据来源
			title : '产品信息', // 标题
			sortName : 'product_id', // 排序的列
			sortOrder : 'asc', // 排序方式
			fitcolumns : true,
			singleSelect : true,//单选
			pagination : false,
			rownumbers : true,
			queryParams: {
				product_code: product_code,
				product_chnpy: product_chnpy,
				//product_immaintain:0,
			},
			columns:[[{
				field : 'product_name',
				title : '产品名称',
				width : 80,
				sortable : true
			}, {
				field : 'product_chnpy',
				title : '拼音码',
				width : 80,
				sortable : true
			}, {
				field : 'product_dosagetype',
				title : '剂型',
				width : 50,
				sortable : true,
				formatter :function(value, row, index){
					for(var i = 0 ; i < dosageTypes.length ;i++){
						if(value == dosageTypes[i].cbs_id){
							return dosageTypes[i].cbs_chn;
						}
					}
				}
			}, {
				field : 'product_packingunit',
				title : '包装单位',
				width : 50,
				sortable : true,
				formatter :function(value, row, index){
					for(var i = 0 ; i < packingunitTypes.length ;i++){
						if(value == packingunitTypes[i].cbs_id){
							return packingunitTypes[i].cbs_chn;
						}
					}
				}
			}, {
				field : 'product_code',
				title : '产品编号',
				width : 100,
				sortable : true
			}, {
				field : 'product_barcode',
				title : '产品条码',
				width : 120,
				sortable : true
			}, {
				field : 'product_proname',
				title : '商品名',
				width : 70,
				sortable : true
			}, {
				field : 'product_specification',
				title : '规格',
				width : 100,
				sortable : true
			}, {
				field : 'product_unit',
				title : '单位',
				width : 80,
				sortable : true
			}, {
				field : 'product_productarea',
				title : '产地',
				width : 150,
				sortable : true
			}, {
				field : 'product_description',
				title : '简介',
				width : 150,
				sortable : true
			}, {
				field : 'product_basremark',
				title : '基本备注',
				width : 150,
				sortable : true
			}]],
			toolbar : [ {
				id: 'choosePro',
				text : '选择',
				iconCls : 'icon-add',
				handler : choosePro
			}]
		}).datagrid("keyCtr");
		
		
		$("#choosePro").focus();
	});
	function choosePro(){
		var node = $('#pro_dg').datagrid('getSelected');
		if(node!=null&&node!=""){
			window.parent['${callback}'](node);
			parent.$('#proListWindow').window('close');
		}
	}
</script>	
</head>        
<body>
	<!-- 显示区域 -->
	<div id="proData" data-options="region:'center',border:false" style="overflow: hidden;width: 100%;">
		<table id="pro_dg"></table>
	</div>
</body>
</html>