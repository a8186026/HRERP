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

	var node = null;//回调数据

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
				stock_storage: "${dept_id}",
				product_code:product_code,
				product_chnpy:product_chnpy,
			},
			columns:[[{
				field : 'product_id',
				title : '产品序号',
				width : 80,
				sortable : true
			}, {
				field : 'product_code',
				title : '产品编号',
				width : 80,
				sortable : true
			}, {
				field : 'product_barcode',
				title : '产品条码',
				width : 80,
				sortable : true
			},{
				field : 'product_name',
				title : '产品名称',
				width : 80,
				sortable : true
			}, {
				field : 'product_chnpy',
				title : '拼音码',
				width : 60,
				sortable : true
			}, {
				field : 'product_specification',
				title : '规格',
				width : 80,
				sortable : true,
			}, {
				field : 'product_productarea',
				title : '产地',
				width : 150,
				sortable : true
			}, {
				field : 'product_packingamount',
				title : '包装量',
				width : 50,
				sortable : true
			}, {
				field : 'product_packingunit',
				title : '包装单位',
				width : 80,
				sortable : true,
				formatter :function(value, row, index){
					for(var i = 0 ; i < packingunitTypes.length ;i++){
						if(value == packingunitTypes[i].cbs_id){
							return packingunitTypes[i].cbs_chn;
						}
					}
				}
			}]],
			toolbar : [ {
				id: 'choosePro',
				text : '选择',
				iconCls : 'icon-add',
				handler : choosePro
			}]
		}).datagrid("keyCtr");
		
		util.window('stockBatchChoose', {
			title : '产品批号选择',
		});
		
		$("#choosePro").focus();
	});
	function choosePro(){
		node = $('#pro_dg').datagrid('getSelected');
		//var id=node.id;
		if(node!=null&&node!=""){
				$("#stockBatchChoose").window({
     				title : '批号选择',
     				width : 850,
     				height : 460,
     				shadow : true,
     				modal : true,
     				closed : true,
     				minimizable : false,	
     				maximizable : false,
     				collapsible : false,
     				resizable : false,
     				content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/store/inventroy/viewStockList?callback=setStockContent&product_id='+ node.product_id +'&dept_id=${dept_id}"></iframe>',
     			});
     			$('#stockBatchChoose').window('open');
		}	
	}
	
	
	function setStockContent(stock){
		if(stock==null){
			return;
		}
		var node = $('#pro_dg').datagrid('getSelected');
		//为对象node添加库存id,销售时减对应的库存
		window.parent['${callback}'](node,stock);
		parent.$('#proListWindow').window('close');
		
	}
	/* function query(){
		$('#pro_dg').datagrid('options').queryParams.product_code = $("#product_code").val();
		$('#pro_dg').datagrid('options').queryParams.product_name = $("#product_name").val();
		$('#pro_dg').datagrid('reload');
	} */
	
</script>	
</head>        
<body class="easyui-layout">
	<div data-options="region:'center'">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'north'" style="height: 80px">
				<div id="searchBar"
					data-options="region:'north',border:true,split:false"
					class="l-panel-search clearfix centertoolbar-w"
					style="overflow: hidden; background-color: #FFFFFF">
					<div class="l-searchbar clearfix centertoolbar-w1">
						<div class="l-panel-search-cond clearfix">
							<form id="proChooseForm" onsubmit="return false;">
								<div class="float-l">
									<div class="l-panel-search-title">产品编码:</div>
									<div class="l-panel-search-item">
										<input id="product_code" name="product_code" type="text" />
									</div>
								</div>
								<div class="float-l">
									<div class="l-panel-search-title">产品名称:</div>
									<div class="l-panel-search-item">
										<input id="product_name" name="product_name" type="text" />
									</div>
								</div>
							</form>
						</div>
						<div class="l-panel-search-btn">
							<input id="" class="btn-search"
								onmouseover="this.className='btn-search-over'"
								onmouseout="this.className='btn-search'"
								onmousedown="this.className='btn-search-down'" type="button"
								onclick="query();" /> <input id="" class="btn-empty"
								onmouseover="this.className='btn-empty-over'"
								onmouseout="this.className='btn-empty'"
								onmousedown="this.className='btn-empty-down'" type="button"
								onclick="$('#proChooseForm').form('reset');" />
						</div>
					</div>
				</div>
			</div>
			<!-- 显示区域 -->
			<div id="proData" data-options="region:'center',border:false" style="overflow: hidden;width: 100%;">
				<table id="pro_dg"></table>
			</div>
			<!-- 编辑窗口 -->
			<div id="stockBatchChoose"></div>
		</div>
	</div>
</body>
</html>

