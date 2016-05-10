<%@page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品审批管理</title>
<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
<script type="text/javascript" src="/HRERP/resources/js/basInfo/basCheckProduct.js"></script>
<script type="text/javascript" src="/HRERP/resources/js/JsUtil/pageCtrlPermission.js"></script>
</head>
<body>
	<div id="searchBar"	class="l-panel-search clearfix centertoolbar-w"	style="overflow: hidden;">
		<div class="l-searchbar clearfix ">
			<div class="l-panel-search-cond clearfix">
				<div class="float-l">
					<div class="l-panel-search-title">选择产品：</div>
					<div class="l-panel-search-item">
						<input id="choose_product" name="choose_product" type="text" />
					</div>
				</div>
			</div>
		</div>
		<div class="l-panel-search-cond clearfix"
			style="width: 1285px; height: 200px; margin-left: 25px; margin-top: 10px; border: 2px solid #F0F0F0;">
			产品基本信息：
			<div class="l-panel-search-cond clearfix">
				<div id="div_product_id" class="float-l">
					<div class="l-panel-search-title">产品序号：</div>
					<div class="l-panel-search-item">
						<input id="product_id" name="product_id" type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_code" class="float-l">
					<div class="l-panel-search-title">产品编号：</div>
					<div class="l-panel-search-item">
						<input id="product_code" name="product_code" type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_commonname" class="float-l">
					<div class="l-panel-search-title">通用名称：</div>
					<div class="l-panel-search-item">
						<input id="product_commonname" name="product_commonname"
							type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_proname" class="float-l">
					<div class="l-panel-search-title">商品名称：</div>
					<div class="l-panel-search-item">
						<input id="product_proname" name="product_proname" type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_specification" class="float-l">
					<div class="l-panel-search-title">规格：</div>
					<div class="l-panel-search-item">
						<input id="product_specification" name="product_specification"
							type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_unit" class="float-l">
					<div class="l-panel-search-title">单位：</div>
					<div class="l-panel-search-item">
						<input id="product_unit" name="product_unit" type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_productarea" class="float-l">
					<div class="l-panel-search-title">产地：</div>
					<div class="l-panel-search-item">
						<input id="product_productarea" name="product_productarea"
							type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_filingtime" class="float-l">
					<div class="l-panel-search-title">建档日期：</div>
					<div class="l-panel-search-item">
						<input id="product_filingtime" name="product_filingtime"
							type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_dealprice" class="float-l">
					<div class="l-panel-search-title">协议进价：</div>
					<div class="l-panel-search-item">
						<input id="product_dealprice" name="product_dealprice" type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_tradeprice" class="float-l">
					<div class="l-panel-search-title">批发价：</div>
					<div class="l-panel-search-item">
						<input id="product_tradeprice" name="product_tradeprice"
							type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_retailprice" class="float-l">
					<div class="l-panel-search-title">零售价：</div>
					<div class="l-panel-search-item">
						<input id="product_retailprice" name="product_retailprice"
							type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_approvalnum" class="float-l">
					<div class="l-panel-search-title">批准文号：</div>
					<div class="l-panel-search-item">
						<input id="product_approvalnum" name="product_approvalnum"
							type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_quanlitystandard" class="float-l">
					<div class="l-panel-search-title">质量标准：</div>
					<div class="l-panel-search-item">
						<input id="product_quanlitystandard"
							name="product_quanlitystandard" type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_cartonsize" class="float-l">
					<div class="l-panel-search-title">装箱规格：</div>
					<div class="l-panel-search-item">
						<input id="product_cartonsize" name="product_cartonsize"
							type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_licensevaliddate" class="float-l">
					<div class="l-panel-search-title">有效期：</div>
					<div class="l-panel-search-item">
						<input id="product_licensevaliddate" name="product_licensevaliddate"
							type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_GMPcertify" class="float-l">
					<div class="l-panel-search-title" style="width: 85px">企业GMP认证书：</div>
					<div class="l-panel-search-item">
						<input id="product_GMPcertify" name="product_GMPcertify"
							type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_certificationtime" class="float-l">
					<div class="l-panel-search-title">认证时间：</div>
					<div class="l-panel-search-item">
						<input id="product_certificationtime"
							name="product_certificationtime" type="text" readOnly="readonly"/>
					</div>
				</div>
				<div id="div_product_storagecondition" class="float-l">
					<div class="l-panel-search-title">储存条件：</div>
					<div class="l-panel-search-item">
						<input id="product_storagecondition"
							name="product_storagecondition" type="text" readOnly="readonly" />
					</div>
				</div>
			</div>
		</div>
	</div>
	
	

	<div style="margin-top: 15px; margin-left: 30px; width: 100%; height: 100%;">
		<div id="checkContent">
			<input id="SPRING_TOKEN" type="hidden"
				value="${sessionScope.SPRING_TOKEN}" /> 
			<input id="type" type="hidden" value="${type}">
		</div>
	</div>	
</body>
</html>