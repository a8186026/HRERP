<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript"	src="/HRERP/resources/js/purchase/purOrderCheck.js"></script>

<div class="easyui-layout" fit="true">
	<div id="searchBar"
			data-options="region:'north',border:false,split:false"
			class="l-panel-search clearfix centertoolbar-w"
			style="overflow: hidden;">
			<div class="l-searchbar clearfix centertoolbar-w1">
				<div class="l-panel-search-cond clearfix" >
					<form id="searchForm" onsubmit="return false;">
						<div class="float-l">
							<div class="l-panel-search-title">票号：</div>
							<div class="l-panel-search-item">
								<input id="search_ticket_id" name="ticket_id" type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">审核状态：</div>
							<div class="l-panel-search-item">
								<input id="search_check_status" name="checkStatus" class="easyui-combobox" data-options="
								valueField : 'label',
								textField : 'value',
								data:[{
									label:'0',
									value:'已审核'},{
									label:'1',
									value:'未审核'
								}]"/>
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

	
	<!-- 大单显示区域 -->
	<div data-options="region:'center',border:false" class="Dialog-Bg"
		style="overflow: hidden;height:400px;" >
		<table id="order_dg"></table>
	</div>
	<!-- 小单显示区域 -->
	<div data-options="region:'center',border:false" class="Dialog-Bg"
		style="overflow: hidden;height:400px;margin-top:400px" >
		<table id="orderList_dg"></table>
	</div>

	<div id="editWindow"></div>
	<div id="editPurOrderWindow"></div>
</div>