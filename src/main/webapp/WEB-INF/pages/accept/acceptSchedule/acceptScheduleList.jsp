<%@page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>收货进度查看</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/accept/acceptSchedule.js"></script>
<script type="text/javascript"	src="/HRERP/resources/js/JsUtil/userHabits.js"></script>
</head>
<body>
	<div id="mainContent" class="easyui-layout" fit="true"
		style="width: 100%; height: 100%;">
		<div id="searchBar"
			data-options="region:'north',border:false,split:false"
			class="l-panel-search clearfix centertoolbar-w"
			style="overflow: hidden; ">
			<div class="l-searchbar clearfix centertoolbar-w1" style="width:1400px">
				<div class="l-panel-search-cond clearfix" >
					<form id="searchForm" onsubmit="return false;">
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">订单编号：</div>
							<div class="l-panel-search-item">
								<input id="search_pur_order_id" name="pur_order_id" type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">收货审核状态：</div>
							<div class="l-panel-search-item ">
								<input id="search_accept_acceptStatus" name="accept_acceptStatus" value = " "/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">拒收审核状态：</div>
							<div class="l-panel-search-item">
								<input id="search_accept_rejectStatus" name="accept_rejectStatus" type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">质量审核状态：</div>
							<div class="l-panel-search-item">
								<input id="search_accept_quantityStatus" name="accept_quantityStatus" type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">合格审核状态：</div>
							<div class="l-panel-search-item">
								<input id="search_accept_quantityCheckStatus" name="accept_quantityCheckStatus" type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">特殊药品审核状态：</div>
							<div class="l-panel-search-item">
								<input id="search_accept_specialStatus" name="accept_specialStatus" type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title" style="width:100px">库房验收状态：</div>
							<div class="l-panel-search-item">
								<input id="search_accept_stockStatus" name="accept_stockStatus" type="text" />
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
		<div data-options="region:'center',border:false"
			style="overflow: hidden;">
			<table id="dg"></table>
		</div>
	</div>
	<!-- 编辑窗口 -->
	<div id="checkWindow"></div>
</body>
</html>