function selectCategory(callback,inputObject) {
	// 创建窗口
	function createCategoryChooseWin() {
		if ($("#categoryChooseWin").length == 0) {
			jQuery('<div id="categoryChooseWin" callback="' + callback + '"></div>').appendTo(document.body);
		}
		$("#categoryChooseWin").window({
			title : '经营分类选择',
			width : 500,
			height : 500,
			closed : true,
			minimizable : false,
			maximizable : false,
			collapsible : false,
			resizable : false,
			content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/product/proInfoManage/category?callback=' + callback + '&value='+$(inputObject).val()+'"></iframe>'
		});
	}
	createCategoryChooseWin();
	$('#categoryChooseWin').window('open');
}