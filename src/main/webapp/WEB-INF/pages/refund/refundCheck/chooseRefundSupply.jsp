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
		var sup_content = '${supContent}';
		var sup_code = null;
		var sup_chnpy = null;
		//根据父级页面输入框中的输入数据，进行分支，包装URL中的搜索条件
		if(sup_content == '%'){
			//doNothing
		}else if(/^\d+$/.test(sup_content)){
			//纯数字，为编码
			sup_code = sup_content;
		}else{
			//拼音码
			sup_chnpy = sup_content;
		}
		
/* 		alert(sup_code);
		alert(sup_chnpy); */
		
		/*准备显示数据*/
		$("#sup_dg").datagrid({
			method : 'get',
			url : global_param.context_name + "/refund/check/getRefundSupplyInfo", // 数据来源
			title : '供方客户信息', // 标题
			sortName : 'sup_id', // 排序的列
			sortOrder : 'asc', // 排序方式
			fitcolumns : true,
			singleSelect : true,//单选
			pagination : false,
			rownumbers : true,
			queryParams: {
				sup_code: sup_code,
				sup_chnpy: sup_chnpy
			},
			columns:[[{
				field : 'sup_code',
				title : '供方编码',
				width : 80,
				sortable : true
			}, {
				field : 'sup_name',
				title : '客户全称',
				width : 100,
				sortable : true
			}, {
				field : 'sup_shortname',
				title : '简称',
				width : 80,
				sortable : true
			}, {
				field : 'sup_chnpy',
				title : '拼音码',
				width : 80,
				sortable : true
			}, {
				field : 'sup_address',
				title : '地址',
				width : 100,
				sortable : true
			}, {
				field : 'sup_ctactperson',
				title : '联系人',
				width : 101,
				sortable : true
			}, {
				field : 'sup_tel',
				title : '电话',
				width : 120,
				sortable : true
			}, {
				field : 'sup_mobile',
				title : '手机 ',
				width : 120,
				sortable : true
			}, {
				field : 'sup_fax',
				title : '传真',
				width : 150,
				sortable : true
			}, {
				field : 'sup_email',
				title : '电子邮箱',
				width : 150,
				sortable : true
			}, {
				field : 'sup_buyname',
				title : '采购员',
				width : 100,
				sortable : true
			}]],
			toolbar : [ {
				id: 'chooseSup',
				text : '选择',
				iconCls : 'icon-add',
				handler : chooseSup
			}]
		}).datagrid("keyCtr");
		
	
		$("#chooseSup").focus();
	});
	function chooseSup(){
		
		var node = $('#sup_dg').datagrid('getSelected');
		if(node!=null&&node!=""){
			window.parent['${callback}'](node);
			parent.$('#supListWindow').window('close');
		}
	}
</script>	
</head>        
<body>
	<!-- 显示区域 -->
	<div id="supData" data-options="region:'center',border:false" style="overflow: hidden;width: 100%;">
		<table id="sup_dg"></table>
	</div>
</body>
</html>