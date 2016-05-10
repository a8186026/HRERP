/**
 * util工具类，集成常用的easyUI控件
 */
var util = new function() {
	// 系统名
	var ctx = global_param.context_name;
	// 继承datagrid，默认项目table规则
	this.table = function(tableId, options) {
		var self = $('#' + tableId);
		var defaults = {
			fitColumns : true,
			pageSize : 10,
			pageList : [10, 20, 50, 100],
			fit : true,
			method : 'get',
			// iconCls : 'icon-edit', // 图标
			singleSelect : true, // 单选
			nowrap : true,
			striped : true, // 奇偶行颜色不同
			collapsible : false,// 可折叠
			sortName : 'id', // 排序的列
			sortOrder : 'desc', // 倒序
			remoteSort : true, // 服务器端排序
			idField : 'id', // 主键字段
			pagination : true, // 显示分页
			rownumbers : true, // 显示行号
			onLoadSuccess : function() {
				$(self).datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
			}
		};
		options = $.extend(defaults, options);
		
		// 验证按钮权限，没有权限的从数组中移除
		/*var toolbar = options.toolbar;
		var permissionCodeArray = [];
		for (var i = 0; toolbar && i < toolbar.length; i++) {
			if (toolbar[i].permission != null) {
				permissionCodeArray.push(toolbar[i].permission);
			}
		}
		if (permissionCodeArray.length > 0) {
			var result = xf.checkPermission(permissionCodeArray);
			for (var i = toolbar.length - 1; i >= 0; i--) {
				if (toolbar[i].permission != null) {
					var _r = result.pop();
					if (_r == false || _r == "false") {
						toolbar.splice(i, 1);
					}
				}
			}
		}*/
		
		$(self).datagrid(options);
	};

	//将java date转换为js日期(flag表示是否需要带时分秒)
	this.toDate = function(num,flag) { //Fri Oct 31 18:00:00 UTC+0800 2008
	    num = num + "";
	    var date = "";
	    var month = new Array();
	    month["Jan"] = 1; month["Feb"] = 2; month["Mar"] = 3; month["Apr"] = 4; month["May"] = 5; month["Jun"] = 6;
	    month["Jul"] = 7; month["Aug"] = 8; month["Sep"] = 9; month["Oct"] = 10; month["Nov"] = 11; month["Dec"] = 12;
	    var week = new Array();
	    week["Mon"] = "一"; week["Tue"] = "二"; week["Wed"] = "三"; week["Thu"] = "四"; week["Fri"] = "五"; week["Sat"] = "六"; week["Sun"] = "日";
	    str = num.split(" ");
	    date = str[5] + "-";
	    date = date + month[str[1]] + "-" + str[2];
	    if(flag)
	    	date = date +" "+ str[3];
	    return date;
	}
	
	// 继承treegrid，默认项目treegrid规则
	this.treegrid = function(tableId, options) {
		var self = $('#' + tableId);
		var defaults = {
			method : 'get',
			fit : true,
			fitColumns : true,
			border : true,
			onLoadSuccess : function() {
				$(self).datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				$(self).treegrid('expandAll'); // 默认展开全部
			},
			toolbar : []
		};
		var defaultToolbar = [ '-', {
			text : '展开',
			iconCls : 'icon-redo',
			handler : function() {
				var node = $(self).treegrid('getSelected');
				if (node) {
					$(self).treegrid('expandAll', node.cid);
				} else {
					$(self).treegrid('expandAll');
				}
			}
		}, '-', {
			text : '折叠',
			iconCls : 'icon-undo',
			handler : function() {
				var node = $(self).treegrid('getSelected');
				if (node) {
					$(self).treegrid('collapseAll', node.cid);
				} else {
					$(self).treegrid('collapseAll');
				}
			}
		}, '-', {
			text : '刷新',
			iconCls : 'icon-reload',
			handler : function() {
				$(self).treegrid('reload');
			}
		} ];

		options = $.extend(defaults, options);
		options.toolbar = $.merge(options.toolbar, defaultToolbar);

		// 验证按钮权限，没有权限的从数组中移除
		/*var toolbar = options.toolbar;
		var permissionCodeArray = [];
		for (var i = 0; i < toolbar.length; i++) {
			if (toolbar[i].permission != null) {
				permissionCodeArray.push(toolbar[i].permission);
			}
		}
		if (permissionCodeArray.length > 0) {
			var result = xf.checkPermission(permissionCodeArray);
			for (var i = toolbar.length - 1; i >= 0; i--) {
				if (toolbar[i].permission != null) {
					var _r = result.pop();
					if (_r == false || _r == "false") {
						toolbar.splice(i, 1);
					}
				}
			}
		}*/

		$(self).treegrid(options);
	};
	
	/**
	 * 用于创建新window窗口
	 * 
	 * @param winId 窗口ID
	 * @param options 配置
	 */
	this.window = function(winId, options) {
		var defaults = {
			title : '标题',
			width : 600,
			height : 460,
			shadow : true,
			modal : true,
			iconCls : 'icon-edit',
			closed : true,
			minimizable : false,
			maximizable : false,
			collapsible : false,
			resizable : false,
			onClose : function() {
				$('#' + winId).find("form").form("disableValidation");
			},
			onLoad : function() {
				$('#' + winId).find("form").form("disableValidation");
				// 修改numberbox宽度错误问题
				if (!!window.ActiveXObject || "ActiveXObject" in window) {
					$.each($(".easyui-numberbox"), function(i, n) {
						$(n).css("width", $(n).width() + 2);
					});
				}
			}
		};
		options = $.extend(defaults, options);
		$('#' + winId).window(options);
	};

	/**
	 * 打开window窗口
	 * 
	 * @param winId 窗口ID
	 * @param options 配置
	 * @param param 临时：解决html打开窗口得不到参数的问题
	 */
	var window_param = null;
	this.openWindow = function(winId, url, param) {
		$('#' + winId).window('open');
		window_param = param;
		if (window_param == null) {
			window_param = {};
		}
		if (url) {
			$('#' + winId).window('refresh', url);
		} else {
//			$('#' + winId).find("form").form("enableValidation");
		}
	};
	
	/**
	 * 临时：取得打开窗口时的参数
	 */
	this.getWindowParam = function() {
		return window_param;
	};

	/**
	 * 关闭window窗口
	 * 
	 * @param winId 窗口ID
	 * @param options 配置
	 */
	this.closeWindow = function(winId) {
		$('#' + winId).window('close');
	};

	/**
	 * 打新的窗口
	 */
	this.openDialog = function(options, dlid) {
		var def_dlid = 'newDlg';
		var defaults = {
			iconCls : 'icon-edit',// 默认编辑图标
			modal : true,// 模式
			width : 600,
			height : 400,
			closable : true,// 右侧关闭按钮
			resizable : true,// 可改变大小
			onClose : function() {// 默认关闭时，销毁窗口
				if (dlid) {
					$("#" + dlid).window('destroy');
				} else {
					$("#" + def_dlid).window('destroy');
				}
			}
		};
		options = $.extend(defaults, options);
		if (dlid) {
			def_dlid = dlid;
		}
		if ($("#" + def_dlid).size() == 0) {
			$('<div id="' + def_dlid + '"/>').appendTo('body');
		}
		$("#" + def_dlid).dialog(options);
	};

	/**
	 * 填充下拉列表
	 * 
	 * @param options 配置
	 */
	this.select = function(options) {
		var defaults = {
			url : ctx + "/system/dictionary/code/" + (options.param ? options.param.typeCode : ""),
			editable : false,
			method : 'get',
			valueField : 'dict_sub_value',
			textField : 'dict_sub_name'
		};
		options = $.extend(defaults, options);
		if (options.param) {
			options.url = options.url.indexOf('?') == -1 ? options.url + "?" : options.url + "&";
			options.url = options.url + $.param(options.param);
		}
		var el = $("#" + options.id);
		if ((!!window.ActiveXObject || "ActiveXObject" in window) && el.attr("style")) {
			el.css("width", el.width() + 2);
		}
		var readonly = el.attr("readonly");
		var disabled = el.attr("disabled");
		var isReadonly = (readonly == "readonly" || readonly == "true" || readonly == true
				|| disabled == "disabled" || disabled == "true" || disabled == true);
		el.combobox(options);
		if (isReadonly) {
			var arrow = el.next("span:first").children("span");
			var input = el.next("span:first").children("input:first");
			arrow.css("display", "none");
			input.css("width", input.width() + arrow.width()).css("cursor", "auto");
		}
	};

	/**
	 * 填充树形下拉列表
	 * 
	 * @param options 配置
	 */
	this.selectTree = function(options) {
		var el = $("#" + options.id);
		var defaults = {
			idField : 'id',
			textField : 'name',
			parentField : 'parent_id',
			lines : true,
			animate : true,
			multiple : false,
			cascadeCheck : false,
			checkbox : false//,
//			panelHeight : 'auto'
		};
		options = $.extend(defaults, options);
		//根据ID直接匹配当前选项  进行显示
		if (options.multiple != true) {
			options.onShowPanel = function() {
				var t = $(this).combotree('tree');
				var node = t.tree('getSelected');
				if (node) {
					t.tree('expandTo', node.target);
				}
			};
		}
		if (options.param) {
			options.url = options.url.indexOf('?') == -1 ? options.url + "?" : options.url + "&";
			options.url = options.url + $.param(options.param);
		}
		var el = $("#" + options.id);
		if ((!!window.ActiveXObject || "ActiveXObject" in window) && el.attr("style")) {
			el.css("width", el.width() + 2);
		}
		var readonly = el.attr("readonly");
		var disabled = el.attr("disabled");
		var isReadonly = (readonly == "readonly" || readonly == "true" || readonly == true
				|| disabled == "disabled" || disabled == "true" || disabled == true);
		el.combotree(options);
		if (isReadonly) {
			var arrow = el.next("span:first").children("span");
			var input = el.next("span:first").children("input:first");
			arrow.css("display", "none");
			input.css("width", input.width() + arrow.width()).css("cursor", "auto");
		}
	};

	/**
	 * 用于取得datagrid选中的行
	 * @param tableId 表格ID
	 * @param isSingle 是否单选
	 */
	this.getSelections = function(tableId, isSingle) {
		var rows = $('#' + tableId).datagrid('getSelections');
		var arr = [];
		$.each(rows, function(index, row) {
			arr.push(row);
		});
		if (isSingle) {
			return arr.length == 1 ? arr[0] : null;
		} else {
			return arr;
		}
	};

	/**
	 * 用于datagrid查询
	 * 
	 * @param tableId 表格ID
	 * @param formId 表单ID
	 * @param paramO 其他参数
	 */
	this.query = function(tableId, formId, paramO) {
		var el = $("#" + tableId);
		var pm = el.datagrid('options').queryParams;
		pm = this.formParams(formId);
		if (!$.isEmptyObject(paramO)) {
			$.extend(pm, paramO);
		}
		el.datagrid('getPager').pagination('refresh', {
			pageNumber: 1
		});
		el.datagrid('options').pageNumber = 1;
		el.datagrid('options').queryParams = pm;
		el.datagrid('reload');
	};

	/**
	 * 用于表单提交
	 * 
	 * @param formId 表单ID
	 * @param validFn 验证函数
	 */
	this.submit = function(formId, validFn) {
		var form = $("#" + formId);
		form.form('enableValidation');
		var checkResult = validFn ? validFn() : this.valid(formId);
		var result = null;
		if (checkResult) {
			$.ajax({
				url : ctx + form.attr("action"),
				data : form.serializeArray(),
				async : false,
				type : form.attr("method") ? form.attr("method") : "post",
				success : function(data) {
					result = data;
				}
			});
		}
		return result;
	};
	
	/**
	 * 用于表单提交。先弹出确认内容，确定后提示
	 * 
	 * @param formId 表单ID
	 * @param message 提示文字
	 * @param callback 回调函数
	 * @param validFn 验证函数
	 */
	this.submitBeformConfirm = function(formId, message, callback, validFn) {
		$("#" + formId).form('enableValidation');
		var checkResult = validFn ? validFn() : this.valid(formId);
		var form = $("#" + formId);
		if (checkResult) {
			this.confirm(message, function() {
				$.ajax({
					url : ctx + form.attr("action"),
					data : form.serializeArray(),
					async : false,
					type : "post",
					success : function(data) {
						if (callback) {
							callback(data);
						}
					}
				});
			})
		}
	};

	/**
	 * ajax访问
	 * 
	 * @param url 提交地址
	 * @param param 参数
	 * @param async 异步执行
	 * @param fn 回调函数
	 */
	this.ajax = function(url, param, async, fn) {
		var result = {};
		$.ajax({
			url : url,
			data : param,
			async : async || false,
			type : "post",
			success : function(data) {
				result = data;
				fn && typeof(fn) == "function" && fn(data);
			}
		});
		return result;
	};
	
	/**
	 * 获取数据
	 * 
	 * @param url 提交地址
	 * @param async 异步执行
	 * @param fn 回调函数
	 */
	this.get = function(url, async, fn) {
		var result = {};
		$.ajax({
			url : url,
			async : async || false,
			type : "get",
			success : function(data) {
				result = data;
				fn && typeof(fn) == "function" && fn(data);
			}
		});
		return result;
	};
	
	/**
	 * 获取List数据
	 * 
	 * @param url 提交地址
	 */
	this.getList = function(url){
		var result = {};
		$.ajax({
			url : url,
			async : false,
			type : "get",
			data : 'json',
			success : function(data) {
				result = data;
			}
		});
		return result;
	}
	
	/**
	 * 修改
	 * 
	 * @param url 提交地址
	 * @param async 异步执行
	 * @param fn 回调函数
	 */
	this.put = function(url, async, fn) {
		var result = {};
		$.ajax({
			url : url,
			async : async || false,
			type : "put",
			success : function(data) {
				result = data;
				fn && typeof(fn) == "function" && fn(data);
			}
		});
		return result;
	};
	
	/**
	 * 删除
	 * 
	 * @param url 提交地址
	 * @param async 异步执行
	 * @param fn 回调函数
	 */
	this.del = function(url, async, fn) {
		var result = {};
		$.ajax({
			url : url,
			async : async || false,
			type : "delete",
			success : function(data) {
				result = data;
				fn && typeof(fn) == "function" && fn(data);
			}
		});
		return result;
	};

	/**
	 * 验证表单
	 * 
	 * @param formId 表单ID
	 */
	this.valid = function(formId) {
		return $('#' + formId).form('validate');
	};

	/**
	 * 清除验证信息
	 * 
	 * @param formId 表单ID
	 */
	this.clearValid = function(formId) {
		$("#" + formId).form('disableValidation');
	};

	/**
	 * 默认的获取表单数据
	 * 
	 * @param formId 表单ID
	 */
	this.formParams = function(formId) {
		var o = {};
		$.each($('#' + formId).serializeArray(), function(index) {
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + "," + this['value'];
			} else {
				if(this['value']!="")
					o[this['name']] = this['value'];
			}
		});
		return o;
	};

	/**
	 * 日期框
	 * 
	 * @param id ID
	 * @param options 设置选项
	 */
	this.datebox = function(id, options) {
		var defaults = {
				skin:'ext',
				dateFmt:"yyyy-MM-dd",
				readOnly:true
			};
		options = $.extend(defaults, options);
		if (id) {
			if (options.initValue != null) {
				$("#" + id).val(options.initValue);
			}
			$("#" + id).attr("readonly", "readonly");
			$("#" + id).on("click", function() {
				WdatePicker(defaults);
			});
		}
	};

	/**
	 * 正常提示信息
	 * 
	 * @param message 信息
	 */
	this.alert = function(message) {
		$.messager.alert('提示', message, "info");
	};

	/**
	 * 错误提示信息
	 * 
	 * @param message 信息
	 */
	this.error = function(message) {
		$.messager.alert('提示', message, "error");
	};

	/**
	 * 确认信息
	 * 
	 * @param message 信息
	 * @param fn 确认通过后执行的函数
	 */
	this.confirm = function(message, fn) {
		$.messager.confirm("确认", message, function(result) {
			if (result) {
				fn();
			}
		});
	};

	/**
	 * 弹出提示信息
	 * 
	 * @param message 信息
	 */
	this.show = function(message) {
		$.messager.show({
			title : '提示',
			msg : message
		});
	};

	// 基础信息缓存
	var _SYS_DICTIONARY_ = {};
	/**
	 * 获取基础信息，用于列表显示。
	 * 
	 * @param typeCode 类别代码
	 * @param 值
	 */
	this.getDict = function(typeCode, value) {
		// 未缓存先进行查询
		if (!_SYS_DICTIONARY_[typeCode]) {
			// 使用的系统参数分页查询
			$.ajax({
				url : ctx + "/system/dictionary/code/" + typeCode,
				data : {typeCode : typeCode},
				async : false,
				type : "get",
				success : function(data) {
					if (data && data instanceof Array) {
						_SYS_DICTIONARY_[typeCode] = data;
					} else {
						_SYS_DICTIONARY_[typeCode] = [];
					}
				}
			});
		}

		if (value == null) {
			return value;//_SYS_DICTIONARY_[typeCode];
		}

		for (var i = 0; i < _SYS_DICTIONARY_[typeCode].length; i++) {
			var dictionary = _SYS_DICTIONARY_[typeCode][i];
			if (dictionary.dict_sub_value == value) {
				return dictionary.dict_sub_name;
			}
		}

		return value;
	};
	
	/**
	 * 检查权限
	 * 
	 * @param permissionCodeArray 权限标识数组
	 * @param 布尔型结果，数组型，对应于权限数组
	 */
	this.checkPermission = function(permissionCodeArray) {
		var result = this.ajax(ctx + "/system/permission/checkPermission", {
			permissionCodeArray : permissionCodeArray.join(",")
		});
		return result.message;
	};
	
	/**
	 * 获取路径
	 * 
	 * @param url
	 */
	this.getTimestampUrl = function(url) {
        url = $.trim(url);
        if (url.indexOf('?') > -1) {
            return global_param.context_name + url + '&timestamp=' + new Date().getTime();
        } else {
            return global_param.context_name + url + '?timestamp=' + new Date().getTime();
        }
    };
    
    /**
	 * 获取路径
	 * 
	 * @param url
	 */
    this.getHtml = function(url){
    	url = $.trim(url);
        if (url.indexOf('?') > -1) {
            return url + '&timestamp=' + (new Date()).getTime();
        } else {
            return url + '?timestamp=' + (new Date()).getTime();
        }
    	return url;
    };
    
    /**
	 * 格式化日期（毫秒数转换为日期形式）
	 * 
	 * @param time 毫秒日期
	 * @param pattern 格式
	 */
    this.formatDate = function(time, pattern){
    	pattern = pattern ? pattern : 'yyyy-MM-dd hh:mm:ss';
    	return new Date(time).format(pattern);
    };
};

var constants = {
	OFFICE_DOCUMENT_01 : 3003,// 公文签发
	OFFICE_DOCUMENT_02 : 3004,// 公文签收
	CONTACTS_ALL : 3005 // 查看全部通讯录
};

var DATA_HOLDER = {};
function registerToDataHolder(key, obj) {
	DATA_HOLDER[key] = obj;
}
function getDataFromDataHolder(key) {
	if (DATA_HOLDER[key]) {
		var obj = DATA_HOLDER[key];
		delete DATA_HOLDER[key];
		return obj;
	} else {
		return null;
	}
}

// 构建树型结构
$.fn.tree.defaults.loadFilter = function(data, parent) {
	var opt = $(this).data().tree.options;
	var idField, textField, parentField;
	if (opt.parentField) {
		idField = opt.idField || 'id';
		textField = opt.textField || 'text';
		parentField = opt.parentField;
		var i, l, treeData = [], tmpMap = [];
		for (i = 0, l = data.length; i < l; i++) {
			tmpMap[data[i][idField]] = data[i];
		}
		for (i = 0, l = data.length; i < l; i++) {
			if (tmpMap[data[i][parentField]]
					&& data[i][idField] != data[i][parentField]) {
				if (!tmpMap[data[i][parentField]]['children'])
					tmpMap[data[i][parentField]]['children'] = [];
				data[i]['id'] = data[i][idField];
				data[i]['text'] = data[i][textField];
				tmpMap[data[i][parentField]]['children'].push(data[i]);
				tmpMap[data[i][parentField]].state = 'closed';
			} else {
				data[i]['id'] = data[i][idField];
				data[i]['text'] = data[i][textField];
				if (data[i].children) {
					data[i].state = 'closed';
				}
				treeData.push(data[i]);
			}
		}
		return treeData;
	}
	return data;
};
$.fn.treegrid.defaults.loadFilter = function(data, parentId) {
	var opt = $(this).data().treegrid.options;
	var idField, textField, parentField;
	if (opt.parentField) {
		idField = opt.idField || 'id';
		textField = opt.textField || 'text';
		parentField = opt.parentField;
		var i, l, treeData = [], tmpMap = [];
		for (i = 0, l = data.length; i < l; i++) {
			tmpMap[data[i][idField]] = data[i];
		}
		for (i = 0, l = data.length; i < l; i++) {
			if (tmpMap[data[i][parentField]]
					&& data[i][idField] != data[i][parentField]) {
				if (!tmpMap[data[i][parentField]]['children'])
					tmpMap[data[i][parentField]]['children'] = [];
				data[i]['id'] = data[i][idField];
				data[i]['text'] = data[i][textField];
				tmpMap[data[i][parentField]]['children'].push(data[i]);
				tmpMap[data[i][parentField]].state = 'closed';
			} else {
				data[i]['id'] = data[i][idField];
				data[i]['text'] = data[i][textField];
				if (data[i].children) {
					data[i].state = 'closed';
				}
				treeData.push(data[i]);
			}
		}
		return treeData;
	}
	return data;
};

$.fn.combotree.defaults.loadFilter = $.fn.tree.defaults.loadFilter;

var easyuiErrorFunction = function(XMLHttpRequest) {
	$.messager.progress('close');
	$.messager.alert('错误', XMLHttpRequest.responseText);
};

$.fn.tree.defaults.onLoadError = easyuiErrorFunction;
$.fn.combogrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.combobox.defaults.onLoadError = easyuiErrorFunction;
$.fn.form.defaults.onLoadError = easyuiErrorFunction;
// datagrid treegrid动态列
var createGridHeaderContextMenu = function(e, field) {
	e.preventDefault();
	var grid = $(this);/* grid本身 */
	var headerContextMenu = this.headerContextMenu;/* grid上的列头菜单对象 */
	if (!headerContextMenu) {
		var tmenu = $('<div style="width:100px;"></div>').appendTo('body');
		var fields = grid.datagrid('getColumnFields');
		for ( var i = 0; i < fields.length; i++) {
			var fildOption = grid.datagrid('getColumnOption', fields[i]);
			if (!fildOption.hidden) {
				$('<div iconCls="icon-ok" field="' + fields[i] + '"/>').html(
						fildOption.title).appendTo(tmenu);
			} else {
				$('<div iconCls="icon-empty" field="' + fields[i] + '"/>')
						.html(fildOption.title).appendTo(tmenu);
			}
		}
		headerContextMenu = this.headerContextMenu = tmenu.menu({
			onClick : function(item) {
				var field = $(item.target).attr('field');
				if (item.iconCls == 'icon-ok') {
					grid.datagrid('hideColumn', field);
					$(this).menu('setIcon', {
						target : item.target,
						iconCls : 'icon-empty'
					});
				} else {
					grid.datagrid('showColumn', field);
					$(this).menu('setIcon', {
						target : item.target,
						iconCls : 'icon-ok'
					});
				}
			}
		});
	}
	headerContextMenu.menu('show', {
		left : e.pageX,
		top : e.pageY
	});
};
$.fn.datagrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;
$.fn.treegrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;
// datagrid的悬浮提示功能
$.extend($.fn.datagrid.methods, {  
    /**  
     * 开打提示功能  
     * @param {} jq  
     * @param {} params 提示消息框的样式  
     * @return {}  
     */  
    doCellTip: function(jq, params){  
        function showTip(data, td, e){  
            if ($(td).text() == "")   
                return;  
            data.tooltip.text($(td).text()).css({  
                top: (e.pageY + 10) + 'px',  
                left: (e.pageX + 20) + 'px',  
                'z-index': $.fn.window.defaults.zIndex,  
                display: 'block'  
            });  
        };  
        return jq.each(function(){  
            var grid = $(this);  
            var options = $(this).data('datagrid');  
            if (!options.tooltip) {  
                var panel = grid.datagrid('getPanel').panel('panel');  
                var defaultCls = {  
                    'border': '1px solid #333',  
                    'padding': '2px',  
                    'color': '#333',  
                    'background': '#f7f5d1',  
                    'position': 'absolute',  
                    'max-width': '200px',  
                    'border-radius' : '4px',  
                    '-moz-border-radius' : '4px',  
                    '-webkit-border-radius' : '4px',  
                    'display': 'none'  
                }  
                var tooltip = $("<div id='celltip'></div>").appendTo('body');  
                tooltip.css($.extend({}, defaultCls, params.cls));  
                options.tooltip = tooltip;  
                panel.find('.datagrid-body').each(function(){  
                    var delegateEle = $(this).find('> div.datagrid-body-inner').length ? $(this).find('> div.datagrid-body-inner')[0] : this;  
                    $(delegateEle).undelegate('td', 'mouseover').undelegate('td', 'mouseout').undelegate('td', 'mousemove').delegate('td', {  
                        'mouseover': function(e){  
                            if (params.delay) {  
                                if (options.tipDelayTime)   
                                    clearTimeout(options.tipDelayTime);  
                                var that = this;  
                                options.tipDelayTime = setTimeout(function(){  
                                    showTip(options, that, e);  
                                }, params.delay);  
                            }  
                            else {  
                                showTip(options, this, e);  
                            }  
                              
                        },  
                        'mouseout': function(e){  
                            if (options.tipDelayTime)   
                                clearTimeout(options.tipDelayTime);  
                            options.tooltip.css({  
                                'display': 'none'  
                            });  
                        },  
                        'mousemove': function(e){  
                            var that = this;  
                            if (options.tipDelayTime)   
                                clearTimeout(options.tipDelayTime);  
                            //showTip(options, this, e);  
                            options.tipDelayTime = setTimeout(function(){  
                                    showTip(options, that, e);  
                                }, params.delay);  
                        }  
                    });  
                });  
                  
            }  
              
        });  
    },  
    /**  
     * 关闭消息提示功能  
     *  
     * @param {}  
     *            jq  
     * @return {}  
     */  
    cancelCellTip: function(jq){  
        return jq.each(function(){  
            var data = $(this).data('datagrid');  
            if (data.tooltip) {  
                data.tooltip.remove();  
                data.tooltip = null;  
                var panel = $(this).datagrid('getPanel').panel('panel');  
                panel.find('.datagrid-body').undelegate('td', 'mouseover').undelegate('td', 'mouseout').undelegate('td', 'mousemove')  
            }  
            if (data.tipDelayTime) {  
                clearTimeout(data.tipDelayTime);  
                data.tipDelayTime = null;  
            }  
        });  
    }  
});
// 防止退格键返回到前一页面
$(document).keydown(function(e) {
	var doPrevent;
	if (e.keyCode == 8) {
		var d = e.srcElement || e.target;
		if (d.tagName.toUpperCase() == 'INPUT' || d.tagName.toUpperCase() == 'TEXTAREA') {
			doPrevent = d.readOnly || d.disabled;
		} else
			doPrevent = true;
	} else
		doPrevent = false;
	if (doPrevent)
		e.preventDefault();
});
// 防止窗口移动到父层外
var easyuiPanelOnMove = function(left, top) {
	var left1 = left, top1 =  top;
    var parentObj = $(this).panel('panel').parent();
    if (left1 < 0) {
    	left1 = 1;
    }
    if (top1 < 0) {
    	top1 = 1;
    }
    var width = $(this).panel('options').width;
    var height = $(this).panel('options').height;
    var parentWidth = parentObj.width();
    var parentHeight = parentObj.height();
    if (parentObj.css("overflow") == "hidden") {
		if (left1 > parentWidth - width) {
			var _left = parentWidth - width;
			left1 = _left < 1 ? 1 : _left;
		}
		if (top1 > parentHeight - $(this).parent().height()) {
			var _top = parentHeight - $(this).parent().height();
			top1 = _top < 1 ? 1 : _top;
		}
	}
	if (left != left1 || top != top1) {
		$(this).window('move', {
			left : left1,
			top : top1
		});
	}
};
$.fn.panel.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
// ajax设置
$.ajaxSetup({
	cache : false,
	//type : 'POST',
	error : function(XMLHttpRequest, textStatus, errorThrown) {
		$.messager.progress('close');
		$.messager.alert('错误', XMLHttpRequest.responseText);
	}
//	,complete : function(xhr, textStatus) {
//		if (xhr.status == 999) {
//			location.reload();// 返回应用首页
//			return;
//		}
//	}
});
/*var resize_thread = null;
// 动态调整查询条件高度
$(window).resize(function() {
	if ($("#searchBar").length == 0) {
		return;
	}
	var opts = {
		max_time : 3000,
		step : 333,
		c : 0,
		oldHeight : $('#mainContent').layout('panel','north').panel('panel').outerHeight()
	};

	if (resize_thread) {
		window.clearTimeout(resize_thread);
	}
	if (opts.c <= opts.max_time) {
		resize_thread = window.setTimeout(resize_searchBar, opts.step);
	}

	function resize_searchBar() {
		opts.c += opts.step;
		var mc = $('#mainContent');
		var p = mc.layout('panel','north');
        p.panel('resize', {height:'auto'});
        var newHeight = p.panel('panel').outerHeight();
        if (opts.oldHeight != newHeight) {
        	p.panel('resize', { height : newHeight } );
        	mc.layout('resize');
        } else {
        	if (opts.c <= opts.max_time) {
        		resize_thread = window.setTimeout(resize_searchBar, opts.step);
        	}
        }
	}
});*/

jQuery(function($) {
	if ($("#searchBar").length > 0) {
		$("#searchBar").keypress(function (e) {
			var keyCode = e.keyCode || e.which || e.charCode;;
			if (13 == keyCode) {
				try {
					query();
				} catch (e) {}
			}
		});
	}
	
	// 修改numberbox宽度错误问题
	if (!!window.ActiveXObject || "ActiveXObject" in window) {
		$.each($(".easyui-numberbox"), function(i, n) {
			n.css("width", n.width() + 2);
		});
	}
});

Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	};

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
};



// 设置权限
//$(function() {
//	var els = $(".permission");
//	var permissionCodeArray = [];
//	$.each(els, function(index, el) {
//		var permission = $(el).attr("permission");
//		$(el).removeClass("permission");
//		permissionCodeArray.push(permission);
//	});
//	if (permissionCodeArray.length > 0) {
//		var result = xf.checkPermission(permissionCodeArray);
//		$.each(els, function(index, el) {
//			if (result[index] == true || result[index] == "true") {
//				$(el).removeClass("permission");
//			} else {
//				$(el).remove();
//			}
//		});
//	}
//});