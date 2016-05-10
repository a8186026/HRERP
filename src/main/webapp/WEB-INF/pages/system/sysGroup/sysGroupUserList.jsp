<%@page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户与组管理</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript"
	src="/HRERP/resources/js/system/sysGroupUser.js"></script>
</head>
<body>
		<div  class="easyui-layout" fit="true"
		style="width: 100%; height: 100%;">
			
		<!--  组显示区域 -->
		<div data-options="region:'west',border:false"
			style="width: 440px;height:900px;float:left;overflow: hidden;border-right: 1px solid #ccc;">
			<form id="searchGroupForm" onsubmit="return false;">
						<div class="float-l">
							<div class="l-panel-search-title">&nbsp;组名称：</div>
							<div class="l-panel-search-item">
								<input id="search_group_name" name="group_name" type="text" />
							</div>
						</div>
			</form> 
			<div class="l-panel-search-btn">
					<input id="" class="btn-search"
						onmouseover="this.className='btn-search-over'"
						onmouseout="this.className='btn-search'"
						onmousedown="this.className='btn-search-down'" type="button"
						onclick="queryGroup();" /> <input id="" class="btn-empty"
						onmouseover="this.className='btn-empty-over'"
						onmouseout="this.className='btn-empty'"
						onmousedown="this.className='btn-empty-down'" type="button"
						onclick="$('#searchGroupForm').form('reset');" />
			</div>
			<table id="groupdg"></table>
		</div>
	
	    <!-- 用户显示区域 -->
		<div data-options="region:'center',border:false"
			style="height:800px;overflow: hidden;float:'right';"> 
				<div style="margin-left: 15px">
					<form id="searchUserForm" onsubmit="return false;">
						<!-- 左侧选择的组ID -->
						<input id="search_group_id" name="group_id" type="hidden"/>
						<div class="float-l">
							<div class="l-panel-search-title">用户姓名：</div>
							<div class="l-panel-search-item">
								<input id="search_display_name" name="display_name" type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">登录名：</div>
							<div class="l-panel-search-item">
								<input id="search_user_name" name="user_name" type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">性别：</div>
							<div class="l-panel-search-item">
								<input id="search_sex" name="sex" type="text" />
							</div>
						</div>
					</form> 
					<div class="l-panel-search-btn">
					<input id="" class="btn-search"
						onmouseover="this.className='btn-search-over'"
						onmouseout="this.className='btn-search'"
						onmousedown="this.className='btn-search-down'" type="button"
						onclick="queryUser();" /> <input id="" class="btn-empty"
						onmouseover="this.className='btn-empty-over'"
						onmouseout="this.className='btn-empty'"
						onmousedown="this.className='btn-empty-down'" type="button"
						onclick="$('#searchUserForm').form('reset');" />
					</div>
				</div>
			<table id="userdg"></table>
		
	</div>
	<!-- 页面编辑窗口 -->
	<div id="groupEditWindow"></div>
	<!-- 控件编辑窗口 -->
	<div id="userEditWindow"></div>
	</div>
</body>
</html>