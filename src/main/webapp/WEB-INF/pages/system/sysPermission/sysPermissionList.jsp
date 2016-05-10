<%@page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限配置</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript"
	src="/HRERP/resources/js/system/sysPermission.js"></script>
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
		<!-- 权限显示区域 -->
		<div data-options="region:'center',border:false"
			style="height:800px;float:'right';"> 
			<table id="menu_dg"></table>
		</div>
</div>
</body>
</html>