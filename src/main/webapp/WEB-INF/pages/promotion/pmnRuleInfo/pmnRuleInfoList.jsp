<%@page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>折扣规则管理</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/promotion/pmnRuleInfo.js"></script>
<script type="text/javascript"	src="/HRERP/resources/js/JsUtil/datagrid-dnd.js"></script>
</head>
<body>
	<div id="mainContent" class="easyui-layout" fit="true"
		style="width: 100%; height: 100%;">
		<div id="searchBar"
			data-options="region:'north',border:false,split:false"
			class="l-panel-search clearfix centertoolbar-w"
			style="overflow: hidden;">
			<div class="l-searchbar clearfix centertoolbar-w1">
				<div class="l-panel-search-cond clearfix">
					<form id="searchForm" onsubmit="return false;">
						<div class="float-l">
							<div class="l-panel-search-title">折扣名称：</div>
							<div class="l-panel-search-item">
								<input id="search_rule_name" name="rule_name" type="text" />
							</div>
						</div>
						<!-- <div class="float-l">
							<div class="l-panel-search-title">折扣类型：</div>
							<div class="l-panel-search-item">
								<input id="search_rule_type" name="rule_type" type="text" />
							</div>
						</div> -->
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
		<div data-options="region:'center',border:false" style="height:300px ;">
			<table id="RuleInfoDg"></table>
 		</div>
 		<div data-options="region:'center',border:false" style="height:400px ;width:100%;margin-top:450px;" >			
			<table id="RuleProductInfoDg" ></table>
		</div>
	</div>
	<!-- 编辑窗口 -->
	<div id="editWindow"></div>
	<!-- 编辑窗口 -->
	<div id="editRuleProductWindow"></div>
</body>
</html>