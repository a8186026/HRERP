function selectScope(callback,inputObject) {
	// 创建窗口
	function createScopeChooseWin() {
		if ($("#scopeChooseWin").length == 0) {
			jQuery('<div id="scopeChooseWin" callback="' + callback + '"></div>').appendTo(document.body);
		}
		$("#scopeChooseWin").window({
			title : '经营范围选择',
			width : 500,
			height : 500,
			closed : true,
			minimizable : false,
			maximizable : false,
			collapsible : false,
			resizable : false,
			content : '<iframe style="width:100%;height:100%;border:0px;padding:0px;padding:0px;" src="/HRERP/supply/supInfoManage/scope?callback=' + callback + '&value='+$(inputObject).val()+'"></iframe>'
		});
	}
	createScopeChooseWin();
	$('#scopeChooseWin').window('open');
}