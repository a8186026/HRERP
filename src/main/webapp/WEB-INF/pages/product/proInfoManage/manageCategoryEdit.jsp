<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品信息管理</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/product/manageCategoryEdit.js"></script>
<script type="text/javascript">
	function callParent(nodes) {
		window.parent['${callback}'](nodes);
	}
</script>
</head>
<body>
	<div id="mainContent" class="easyui-layout" fit="true"
		style="width: 100%; height: 100%;">	
		<input id="category" type="hidden" value="${value}">
		<!-- 显示区域 -->
		<div data-options="region:'center',border:false"
			style="">
			<table id="dg1" style="width:100px"></table>
		</div>
		
	</div>
</body>
</html>