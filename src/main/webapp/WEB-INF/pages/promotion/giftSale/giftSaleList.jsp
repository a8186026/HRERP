<%@page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>买赠信息</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/promotion/giftSale.js"></script>
</head>
<body>
	<div id="mainContent" class="easyui-layout" fit="true"
		style="width: 100%; height: 100%;">
		<div id="searchBar" 	data-options="region:'north',border:false,split:false"
			class="l-panel-search clearfix centertoolbar-w"
			style="overflow: hidden;">
			<div class="l-searchbar clearfix centertoolbar-w1">
				<div class="l-panel-search-cond clearfix" style="width:600px;">			
					<form id="searchForm" onsubmit="return false;">
						<div class="float-l">
							<div class="l-panel-search-title">开始日期：</div>
							<div class="l-panel-search-item">
								<input id="search_gift_sal_startDate" name="gift_sal_startDate" class="easyui-datebox" type="text"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">结束日期：</div>
							<div class="l-panel-search-item">
								<input id="search_gift_sal_endDate" name="gift_sal_endDate" class="easyui-datebox" type="text"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">编号：</div>
							<div class="l-panel-search-item">
								<input id="search_gift_sal_code" name="gift_sal_code" type="text"/>
							</div>
						</div>
						<div class="float-l">
							<div class="l-panel-search-title">拼音码：</div>
							<div class="l-panel-search-item">
								<input id="search_gift_sal_chn" name="gift_sal_chn"  type="text"/>
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
		<div data-options="region:'center',border:false" style="height:300px ;">
			<table id="GiftSaleDg"></table>
 		</div>
 		<div data-options="region:'center',border:false" style="height:300px ;width:100%;margin-top:490px;" >			
			<table id="GiftInfoDg" ></table>
		</div>
	</div>
	
	

	   
	<!-- 编辑窗口  -->
	<div id="editWindow"></div>
	
	<!-- 买赠产品编辑窗口 -->
	<div id="editGiftInfoWindow"></div>
	
	<div id="proListWindow"></div>
</body>
</html>