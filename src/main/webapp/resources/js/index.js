var menus;
var websocket;
$(function() {
	/* 初始化tabs控件的菜单功能 */
	$("#tt").tabs({
		onContextMenu : function (e, title) {
			e.preventDefault();
            $('#tabMenu').menu('show', {
                left : e.pageX,
                top : e.pageY
            }).data("tabTitle", title);
		},
		onClose : function() {
			if ($('#tt').tabs("tabs").length == 0) {
				homePage();
			}
		}
	});
	/* 初始化tabs控件的菜单功能 */
	$("#tt .tabs-header").on('mouseup dblclick', ".tabs-inner", function(e) {
		if (e.type == "mouseup") {
			var keyCode = e.keyCode || e.which || e.charCode;
			if (keyCode != 2) {
				return;
			}
		}
		var text = $(this).children("span:first").html();
		$("#tt").tabs('close', $.trim(text));
	});
	/* 初始化tabs控件的菜单功能的事件 */
	$("#tabMenu").menu({
        onClick : function (item) {
            closeTab(this, item.id);
        }
    });
	/* tabs控件的菜单功能 */
	function closeTab(menu, action) {
		var curTabTitle = $(menu).data("tabTitle");
		action = action.replace("tab-", "");

		switch (action) {
		case "refresh":
			$('#tt').tabs('getTab', curTabTitle).panel('refresh');
			break;
		case "close":
			$('#tt').tabs('close', curTabTitle);
			break;
		case "closeall":
			var len = $('#tt').tabs("tabs").length;
			for (var i = 0; i < len; i++) {
				$('#tt').tabs('close', 0);
			}
			break;
		case "closeother":
			var allTabs = $('#tt').tabs("tabs");
			var index = -1;
			$.each(allTabs, function(i, n) {
				var title = $(n).panel('options').title;
				if (title == curTabTitle) {
					index = i;
					return false;
				}
			});
			var len = allTabs.length;
			for (var i = 0; i < len - 1; i++) {
				$('#tt').tabs('close', i < index ? 0 : 1);
			}
			break;
		case "closeright":
			var allTabs = $('#tt').tabs("tabs");
			var index = -1;
			$.each(allTabs, function(i, n) {
				var title = $(n).panel('options').title;
				if (title == curTabTitle) {
					index = i;
					return false;
				}
			});
			var len = allTabs.length;
			for (var i = index + 1; i < len; i++) {
				$('#tt').tabs('close', index + 1);
			}
			$('#tt').tabs('select', curTabTitle);
			break;
		case "closeleft":
			var allTabs = $('#tt').tabs("tabs");
			var index = -1;
			$.each(allTabs, function(i, n) {
				var title = $(n).panel('options').title;
				if (title == curTabTitle) {
					index = i;
					return false;
				}
			});
			for (var i = 0; i < index; i++) {
				$('#tt').tabs('close', 0);
			}
			$('#tt').tabs('select', curTabTitle);
			break;
		default : break;
		}
	}
	/* 取得登录用户信息 */
	var user = util.get(global_param.context_name + "/userinfo");
	$(".top_txt_ul_li1").html("欢迎您：" + user.display_name);
	
	/* 构建菜单：已取消二级菜单，只显示一级菜单和三级菜单 */
	function treeInit(data) {
		if (!data) {
			return '';
		}
		var m = [];
		for (var i = 0; i < data.length; i++) {
			if (data[i].children) {
				for (var j = 0; j < data[i].children.length; j++) {
					m.push(data[i].children[j]);
				}
			} else {
				m.push(data[i]);
			}
		}

		var menulist = '<ul class="navlist">';
		//获取父级菜单
		for (var i = 0; i < m.length; i++) {
			menulist += '<li><div><a ref="'+m[i].menu_id+'" href="#" rel="' + m[i].menu_url + '"><span class="nav">' + m[i].menu_name + '</span></a></div></li>';
		}
		return menulist + '</ul>';
	}
	/* 取得菜单信息，构建树形结构 */
	function initMenuData() {
		var _menu = util.get(global_param.context_name + "/userMenus");
		menus = [];
		var i, l, tmpMap = [], idField = 'menu_id', textField = 'menu_name', parentField = "parent_menu";
		for (i = 0, l = _menu.length; i < l; i++) {
			tmpMap[_menu[i][idField]] = _menu[i];
			_menu[i].id = _menu[i][idField];
			if (_menu[i].url && _menu[i].url != "") {
				_menu[i].attributes = {
					url : _menu[i].url
				};
			}
		}
		for (i = 0, l = _menu.length; i < l; i++) {
			if (tmpMap[_menu[i][parentField]] && _menu[i][idField] != _menu[i][parentField]) {
				if (!tmpMap[_menu[i][parentField]]['children'])
					tmpMap[_menu[i][parentField]]['children'] = [];
				_menu[i]['text'] = _menu[i][textField];
				tmpMap[_menu[i][parentField]]['children'].push(_menu[i]);
			} else {
				if (!_menu[i][parentField]) {
					_menu[i]['text'] = _menu[i][textField];
					menus.push(_menu[i]);
				}
			}
		}
	}
	initMenuData();

	/* 添加菜单内容 */
	for (var i = 0; i < menus.length; i++) {
		$("#menuDiv").accordion('add', {
			title : menus[i].text,
			selected : true,
			fit:true,
			iconCls : menus[i].icon ? menus[i].icon : "icon-ok",
			content : treeInit(menus[i].children)
		});
		$("#menuDiv").accordion('select', 0);
	}
	$('#menuDiv li div').click(function() {
		var a = $(this).find('a');
		var menuName = a.children('.nav').text();
		var url = a.attr("rel");
		var menuid = a.attr("ref");
		// var icon = a.find('.icon').attr('class');

		var node = getNodeInstance(menuid, menuName, url);
		addTab(node);
	}).hover(function() {
		$(this).addClass("hover");
	}, function() {
		$(this).removeClass("hover");
	});
	
	//建立消息连接
	webSocket();
});

function getNodeInstance(menuid, menuName, url) {
	var node = {
		text : menuName,
		id : menuid,
		attributes : {
			url : url
		}
	};
	return node;
}

/* 添加或选中tab标签 */
function addTab(node) {
//	if (!node.attributes || !node.attributes.url || node.attributes.url.length == 0) {
//		return;
//	}
	var tempData = null;
	if (node.tempData) {
		tempData = node.tempData;
		delete node.tempData;
	}
	if(!$('#tt').tabs('exists', node.text) || tempData != null) {
		var src = global_param.context_name + "/" + node.attributes.url;
		if (tempData) {
			registerToDataHolder(node.text, tempData);
		}
		if (!$('#tt').tabs('exists', node.text)) {
			$('#tt').tabs('add',{
				title:node.text,
				content:'<iframe style="width: 100%; height: 100%;" frameborder="0" src="' + src + '"></iframe>',
				closable:true
			});
		} else {
			var tab = $('#tt').tabs('getTab', node.text);
			$('#tt').tabs('select', node.text);
			tab.panel('refresh');
		}
	} else {
		$('#tt').tabs('select', node.text);
	}
}

/* 跳转到指定菜单 */
function jumpTo(text, data) {
	for (var i = 0; i < menus.length; i++) {
		if (!menus[i].children) {
			continue;
		}
		for (var j = 0; j < menus[i].children.length; j++) {
			var c = menus[i].children[j];
			if (!c) {
				continue;
			}
			for (var k = 0; k < c.children.length; k++) {
				if (c.children[k].text == text) {
					$("#menuDiv").accordion('select', i);
					// 选择子菜单
					var node = getNodeInstance(c.children[k].permissions_id, c.children[k].permissions_name, c.children[k].url);
					node.tempData = data;
					addTab(node);
					return;
				}
			}
		}
	}
}

/* 刷新我的待办 */
function refreshMyWork() {
	if ($('#tt').tabs('exists', '我的待办')) {
		var tab = $('#tt').tabs('getTab', '我的待办');
		var myWorkFrame = $(tab).find("iframe")[0];
		myWorkFrame.contentWindow.$('#dg').datagrid('reload');
	}
}

/* 打开主页 */
function homePage() {
	var node = {
		text : '欢迎使用',
		attributes : {url : "homePage"}
	};
    addTab(node);
}

/* 注销 */
function logout() {
	util.confirm("确认要退出系统吗？", function() {
		location.href = global_param.context_name + "/logout";
	});
}
/*  建立消息连接 */
function webSocket(){
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080/HRERP/webSocketServer");
    } else if ('MozWebSocket' in window) {
        websocket = new MozWebSocket("ws://localhost:8080/HRERP/webSocketServer");
    } else {
        websocket = new SockJS("http://localhost:8080/HRERP/sockjs/webSocketServer");
    }
    websocket.onopen = function (evnt) {
     //连接成功后请求数据
   	 var result = util.get(global_param.context_name + "/message/request");
   	 if(result == "failure")
   		 util.show("消息数据请求失败！");
    };
    websocket.onmessage = function (evnt) {
    	$(".top_txt_ul_li2").html("<a href='#'>消息(" + evnt.data+")</a>");
   	 //alert("消息："+evnt.data);
    };
    websocket.onerror = function (evnt) {
    	util.show("消息提醒出错了！")
    };
    websocket.onclose = function (evnt) {
    };
}

