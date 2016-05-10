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
		$('#search_mem_card_type').combobox({
			url : global_param.context_name + "/system/combobox/lists?pid=51",
			method :"get",
			valueField : "cbs_id",
			textField : "cbs_chn",
			editable:false,
			onLoadSuccess: function(){
				cardTypeList = $('#search_mem_card_type').combobox("getData");
			}
		});
		
		
		
		/*准备显示数据*/
		$("#memCard_dg").datagrid({
			method : 'get',
			url : global_param.context_name + "/member/memCardManage/lists", // 数据来源
			title : '产品信息', // 标题
			sortName : 'create_time', // 排序的列
			sortOrder : 'asc', // 排序方式
			fitcolumns : true,
			singleSelect : true,//单选
			pagination : true,
			height:490,
			pageSize : 10,
			pageList : [10 , 20, 50, 100],
			rownumbers : true,
			queryParams: {
			},
			columns:[[{
				field : 'mem_card_type',
				title : '类别',
				width : 100,
				sortable : true,
				formatter :function(value, row, index){
					for(var i = 0 ; i < cardTypeList.length ;i++){
						if(value == cardTypeList[i].cbs_id){
							return cardTypeList[i].cbs_chn;
						}
					}
					return "";
				}
			},{
				field : 'mem_card_number',
				title : '卡号',
				width : 100,
				sortable : true
			}, {
				field : 'mem_card_name',
				title : '姓名',
				width : 100,
				sortable : true
			},{
				field : 'mem_card_chn',
				title : '拼音码',
				width : 80,
				sortable : true
			}, {
				field : 'mem_card_gender',
				title : '性别',
				width : 50,
				sortable : true,
				formatter :function(value, row, index){
					return util.getDict('SEX', value);
				}
			}, {
				field : 'mem_card_allCost',
				title : '累计消费',
				width : 80,
				sortable : true
			}, {
				field : 'mem_card_pointCost',
				title : '积分消费',
				width : 80,
				sortable : true
			}, {
				field : 'mem_card_acountMoney',
				title : '记分金额',
				width : 80,
				sortable : true
			}, {
				field : 'mem_card_startMoney',
				title : '初始金额',
				width : 80,
				sortable : true
			}, {
				field : 'mem_card_point',
				title : '积分',
				width : 80,
				sortable : true
			}, {
				field : 'mem_card_addPoint',
				title : '追加积分',
				width : 80,
				sortable : true
			}, {
				field : 'mem_card_discount',
				title : '扣率',
				width : 100,
				sortable : true
			}, {
				field : 'mem_lowest_discount',
				title : '最低扣率',
				width : 100,
				sortable : true
			}, {
				field : 'mem_card_idCard',
				title : '身份证',
				width : 50,
				sortable : true
			}, {
				field : 'mem_card_applyDate',
				title : '申请日期',
				width : 100,
				sortable : true,
				formatter :function(value, row, index){
					return util.formatDate(value);
				}
			}, {
				field : 'mem_card_failedDate',
				title : '失效日期',
				width : 100,
				sortable : true,
				formatter :function(value, row, index){
					return util.formatDate(value);
				}
			}, {
				field : 'DK',
				title : '未兑积分',
				width : 80,
				sortable : true,
				formatter :function(value, row, index){
					return Number(row.mem_card_point) - Number(row.mem_card_usedPoint);
				}
				
			}, {
				field : 'mem_card_usedPoint',
				title : '已兑积分',
				width : 80,
				sortable : true
			}]],
			toolbar : [ {
				id:'chooseMember',
				text : '选择',
				iconCls : 'icon-add',
				handler : chooseMemCard
			}]
		}).datagrid("keyCtr");
		
		
		$("#chooseMember").focus();
	});
	function chooseMemCard(){
		var node = $('#memCard_dg').datagrid('getSelected');
		if(node!=null&&node!=""){
			window.parent['${callback}'](node);
			parent.$('#memCardWindow').window('close');
		}
	}
	function query() {
		$('#memCard_dg').datagrid('options').queryParams = util.formParams("searchForm");
		$('#memCard_dg').datagrid('reload');
	}
	
</script>	
</head>        
<body>
		<div id="searchBar"
			data-options="region:'north',border:false,split:false"
			class="l-panel-search clearfix centertoolbar-w"
			style="overflow: hidden;">
			<div class="l-searchbar clearfix centertoolbar-w1">
				<div class="l-panel-search-cond clearfix">
					<form id="searchForm" onsubmit="return false;">
						<div class="float-l">
							<div class="l-panel-search-title">类别：</div>
							<div class="l-panel-search-item">
								<input id="search_mem_card_type" name="mem_card_type" class="easyui-combobox" type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">姓名：</div>
							<div class="l-panel-search-item">
								<input id="search_mem_card_name" name="mem_card_name" type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">拼音码：</div>
							<div class="l-panel-search-item">
								<input id="search_mem_card_chn" name="mem_card_chn" type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">卡号：</div>
							<div class="l-panel-search-item">
								<input id="search_mem_card_number" name="mem_card_number" type="text" />
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
						onclick="$('#searchForm').form('reset');" />
				</div>
			</div>
		</div>

	<!-- 显示区域 -->
	<div id="memCardData" data-options="region:'center',border:false" style="overflow: hidden;width: 100%;">
		<table id="memCard_dg"></table>
	</div>
</body>
</html>