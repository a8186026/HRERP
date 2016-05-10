<%@page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>控件权限配置</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/system/sysCtrlPermission.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/system/CtrlPermissionXMLReader.js"></script>
</head>
<body>
	<div  class="easyui-layout" fit="true"style="width: 100%; height: 100%;">
		<div data-options="region:'west',border:false"
			style="width: 280px;height:100%; float:left;overflow: hidden;border-right: 1px solid #ccc;">
			<div class="float-l">
				<div class="l-panel-search-title">&nbsp;用户组：</div>
				<div class="l-panel-search-item">
					<input id="group_id" name="group_id" class="easyui-combobox" type="text" width="100%"/>
				</div>
			</div>
			<br/><br/>
			<table id="u_dg"></table>
		</div>
		
		<!--  页面显示区域 -->
		<div data-options="region:'center',border:false" style="height:100%; overflow: hidden;border-right: 1px solid #ccc;">
		
			<form id="searchForm" onsubmit="return false;">
				<div class="float-l">
					<div class="l-panel-search-title">&nbsp;页面名称：</div>
						<div class="l-panel-search-item">
								<input id="search_page_name" name="page_name" type="text" />
							</div>
						</div>
			</form> 
			<div class="l-panel-search-btn">
					<input id="" class="btn-search"
						onmouseover="this.className='btn-search-over'"
						onmouseout="this.className='btn-search'"
						onmousedown="this.className='btn-search-down'" type="button"
						onclick="query_Page();" /> <input id="" class="btn-empty"
						onmouseover="this.className='btn-empty-over'"
						onmouseout="this.className='btn-empty'"
						onmousedown="this.className='btn-empty-down'" type="button"
						onclick="$('#searchForm').form('reset');" />
			</div>
			<table id="pagedg"></table>
			
		</div>
		 <!-- 控件显示区域 -->
		<div data-options="region:'east',border:false"
			style="height:100%;width:1000px;overflow: hidden;float:right; " > 
				<div style="margin-left: 15px">
					<form id="searchComForm" onsubmit="return false;">
						<div class="float-l">
							<div class="l-panel-search-title">&nbsp;控件名称：</div>
							<div class="l-panel-search-item">
								<input id="search_ctrl_name" name="ctrl_name" type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">控件类型：</div>
							<div class="l-panel-search-item">
								<input id="search_ctrl_type" name="ctrl_type" class="easyui-combobox"
							data-options="valueField:'label',textField:'value',data:[{
							label:'input',value:'输入框'},{label:'button',value:'按钮'},
							{label:'combo',value:'下拉列表框'},{label:'datagrid',value:'datagrid'}]"
							/>
							</div>
						</div>
					
					</form> 
					<div class="l-panel-search-btn">
					<input id="" class="btn-search"
						onmouseover="this.className='btn-search-over'"
						onmouseout="this.className='btn-search'"
						onmousedown="this.className='btn-search-down'" type="button"
						onclick="query_Com();" /> <input id="" class="btn-empty"
						onmouseover="this.className='btn-empty-over'"
						onmouseout="this.className='btn-empty'"
						onmousedown="this.className='btn-empty-down'" type="button"
						onclick="$('#searchComForm').form('reset');" />
					</div>
				</div>
			<table id="componentsdg"></table>
		</div>
	</div>
</body>
</html>