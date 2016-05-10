<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript"	src="/HRERP/resources/js/accept/purRejectCheckList.js"></script>

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
	
	<!-- toolbar显示区域 -->
	<div id="tb" style="padding:2px;height:auto">
		<div style="margin-bottom:1px">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="checkResult('pass')">审核通过</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-modify" plain="true" onclick="checkResult('fail')">审核不通过</a>
		<font style="float:right">
		质检人:<input id="rejectCheckPerson" style="border:0;width:100px" type="text" readOnly="readonly">
		质检日期:<input id="rejectCheckDate" style="border:0;width:150px" type="text" readOnly="readonly"></font>
		</div>
	</div>
	
	<div data-options="region:'center',border:false" class="Dialog-Bg"
		style="overflow: hidden" >
		<table id="rejectOrderCheck_dg"></table>
	</div>

</div>