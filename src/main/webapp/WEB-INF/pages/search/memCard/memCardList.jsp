<%@page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员卡信息统计</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/search/memCard.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageKeyPress.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/userHabits.js"></script>
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
							<div class="l-panel-search-title">卡号：</div>
							<div class="l-panel-search-item">
								<input id="search_memCard_id" name="memCard_id" type="text" />
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">开始时间：</div>
							<div class="l-panel-search-item">
								<input id="search_start_time" name="start_time" type="text" class="easyui-datebox"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">截止时间：</div>
							<div class="l-panel-search-item">
								<input id="search_end_time" name="end_time" type="text" class="easyui-datebox"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">姓名：</div>
							<div class="l-panel-search-item">
								<input id="mem_card_name" name="mem_card_name" type="text" disabled="disabled"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">性别：</div>
							<div class="l-panel-search-item">
								<input id="mem_card_gender" name="mem_card_gender" type="text" disabled="disabled"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">电话：</div>
							<div class="l-panel-search-item">
								<input id="mem_card_tel" name="mem_card_tel" type="text" disabled="disabled"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">类别：</div>
							<div class="l-panel-search-item">
								<input id="mem_card_type" name="mem_card_type" type="text" disabled="disabled"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">手机：</div>
							<div class="l-panel-search-item">
								<input id="mem_card_cellPhone" name="mem_card_cellPhone" type="text" disabled="disabled"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">地址：</div>
							<div class="l-panel-search-item">
								<input id="mem_card_address" name="mem_card_address" type="text" disabled="disabled"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">累计消费：</div>
							<div class="l-panel-search-item">
								<input id="mem_card_allCost" name="mem_card_allCost" type="text" disabled="disabled"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">身份证：</div>
							<div class="l-panel-search-item">
								<input id="mem_card_idCard" name="mem_card_idCard" type="text" disabled="disabled"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">积分消费：</div>
							<div class="l-panel-search-item">
								<input id="mem_card_pointCost" name="mem_card_pointCost" type="text" disabled="disabled"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">记分金额：</div>
							<div class="l-panel-search-item">
								<input id="mem_card_acountMoney" name="mem_card_acountMoney" type="text" disabled="disabled"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">积分：</div>
							<div class="l-panel-search-item">
								<input id="mem_card_point" name="mem_card_point" type="text" disabled="disabled"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">条码：</div>
							<div class="l-panel-search-item">
								<input id="mem_card_barCode" name="mem_card_barCode" type="text" disabled="disabled"/>
							</div>
						</div>
						
						<div class="float-l">
							<div class="l-panel-search-title">卡值：</div>
							<div class="l-panel-search-item">
								<input id="search_end_time" name="end_time" type="text" disabled="disabled" value="??属性未知"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">初始金额：</div>
							<div class="l-panel-search-item">
								<input id="mem_card_startMoney" name="mem_card_startMoney" type="text" disabled="disabled"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">申请日期：</div>
							<div class="l-panel-search-item">
								<input id="mem_card_applyDate" name="mem_card_applyDate" type="text" disabled="disabled" class="easyui-datebox"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">邮编：</div>
							<div class="l-panel-search-item">
								<input id="mem_card_postCode" name="mem_card_postCode" type="text" disabled="disabled"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">登记人：</div>
							<div class="l-panel-search-item">
								<input id="create_user" name="create_user" type="text" disabled="disabled"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">登记时间：</div>
							<div class="l-panel-search-item">
								<input id="create_time" name="create_time" type="text" disabled="disabled" class="easyui-datebox"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">摘要：</div>
							<div class="l-panel-search-item">
								<input id="search_end_time" name="end_time" type="text" disabled="disabled" value="??属性未知"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">扣率：</div>
							<div class="l-panel-search-item">
								<input id="mem_card_discount" name="mem_card_discount" type="text" disabled="disabled"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">最低扣率：</div>
							<div class="l-panel-search-item">
								<input id="mem_lowest_discount" name="mem_lowest_discount" type="text" disabled="disabled"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">失效日期：</div>
							<div class="l-panel-search-item">
								<input id="mem_card_failedDate" name="mem_card_failedDate" type="text" disabled="disabled" class="easyui-datebox"/>
							</div>
						</div>
					</form>
				</div>
				<div class="l-panel-search-btn">
					<input id="" class="btn-search"
						onmouseover="this.className='btn-search-over'"
						onmouseout="this.className='btn-search'"
						onmousedown="this.className='btn-search-down'" type="button"
						onclick="queryMember();" /> <input id="" class="btn-empty"
						onmouseover="this.className='btn-empty-over'"
						onmouseout="this.className='btn-empty'"
						onmousedown="this.className='btn-empty-down'" type="button"
						onclick="$('#searchForm').form('reset');" />
				</div>
			</div>
		</div>
		<div id="tabs" class="easyui-tabs" style="margin-top:300px">
			<!-- 退货显示区域 -->
			<div title="退货信息" data-options="region:'center',border:false"
				style="overflow: scroll;">
				<table id="returnDg"></table>
			</div>
			<!-- 购买记录显示区域 -->
			<div title="销售信息" data-options="region:'center',border:false"
				style="overflow: scroll;">
				<table id="purchaseDg"></table>
			</div>
		</div>
	</div>
	<!-- 编辑窗口 -->
	<div id="memCardWindow"></div>
</body>
</html>