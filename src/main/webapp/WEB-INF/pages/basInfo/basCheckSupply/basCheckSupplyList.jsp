<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>供方审批管理</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/basInfo/basCheckSupply.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageCtrlPermission.js"></script>
</head>
<body>
	
		<div data-options="region:'north',border:false,split:false"
			class="l-panel-search clearfix centertoolbar-w"
			style="overflow: hidden;">
		
				<div class="l-panel-search-cond clearfix" style=" margin-left: 25px;">
					
						<div class="float-l">
							<div class="l-panel-search-title">选择供方：</div>
							<div class="l-panel-search-item">
								<input id="choose_supply" name="choose_supply" type="text" />
							</div>
						</div>
						</div>
						<div class="l-panel-search-cond clearfix" style="width:1285px;height:125px; margin-left: 25px; margin-top:10px;border:2px solid #F0F0F0;">
						供方基本信息：
						<div style=" margin-left:100px; margin-top:10px; margin-bottom:10px;">
						<div id="div_supply_number" class="float-l">
							<div class="l-panel-search-title">供方序号：</div>
							<div class="l-panel-search-item">
								<input id="supply_number" name="supply_number" type="text" readonly="readonly" />
							</div>
						</div>
						<div id="div_supply_code" class="float-l">
							<div class="l-panel-search-title">供方编码：</div>
							<div class="l-panel-search-item">
								<input id="supply_code" name="supply_code" type="text" readonly="readonly"/>
							</div>
						</div>
						<div id="div_supply_name" class="float-l">
							<div class="l-panel-search-title">客户全称：</div>
							<div class="l-panel-search-item">
								<input id="supply_name" name="supply_name" type="text" readonly="readonly"/>
							</div>
						</div>
						<div id="div_supply_type" class="float-l">
							<div class="l-panel-search-title">类别：</div>
							<div class="l-panel-search-item">
								<input id="supply_type" name="supply_type" type="text" readonly="readonly"/>
							</div>
						</div>
						<div id="div_supply_person" class="float-l">
							<div class="l-panel-search-title">联系人：</div>
							<div class="l-panel-search-item">
								<input id="supply_person" name="supply_person" type="text" readonly="readonly"/>
							</div>
						</div>
						<div id="div_supply_telephone" class="float-l">
							<div class="l-panel-search-title">联系电话：</div>
							<div class="l-panel-search-item">
								<input id="supply_telephone" name="supply_telephone" type="text" readonly="readonly"/>
							</div>
						</div>
						<div id="div_supply_permission_no" class="float-l">
							<div class="l-panel-search-title">许可证号：</div>
							<div class="l-panel-search-item">
								<input id="supply_permission_no" name="supply_permission_no" type="text" readonly="readonly"/>
							</div>
						</div>
						<div id="div_supply_permission_name" class="float-l">
							<div class="l-panel-search-title">许可证名称：</div>
							<div class="l-panel-search-item">
								<input id="supply_permission_name" name="supply_permission_name" type="text" readonly="readonly"/>
							</div>
						</div>
						
						<div id="div_supply_permission_wide" class="float-l">
							<div class="l-panel-search-title">许可范围：</div>
							<div class="l-panel-search-item">
								<input id="supply_permission_wide" name="supply_permission_wide"  style="width:975px;" type="text" readonly="readonly"/>
							</div>
					
						</div>
						</div>
				</div>
				
			
		</div>
		<div style="margin-top: 15px;margin-left: 30px;width: 100%; height: 100%;">
	
			<div id="checkContent">
			 <input id = "SPRING_TOKEN"  type="hidden" value="${sessionScope.SPRING_TOKEN}" />
			 <input id="type" type="hidden" value="${type}">
			</div>
		</div>
	
	
</body>
</html>