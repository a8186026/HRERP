if ($.fn.pagination) {
	$.fn.pagination.defaults.beforePageText = '第';
	$.fn.pagination.defaults.afterPageText = '共{pages}页';
	$.fn.pagination.defaults.displayMsg = '显示{from}到{to},共{total}记录';
}
if ($.fn.datagrid) {
	$.fn.datagrid.defaults.loadMsg = '正在处理，请稍候……';
}
if ($.fn.treegrid && $.fn.datagrid) {
	$.fn.treegrid.defaults.loadMsg = $.fn.datagrid.defaults.loadMsg;
}
if ($.messager) {
	$.messager.defaults.ok = '确定';
	$.messager.defaults.cancel = '取消';
}
if ($.fn.validatebox) {
	$.fn.validatebox.defaults.missingMessage = '该输入项为必输项';
	$.fn.validatebox.defaults.rules.email.message = '请输入有效的电子邮件地址';
	$.fn.validatebox.defaults.rules.url.message = '请输入有效的URL地址';
	$.fn.validatebox.defaults.rules.length.message = '输入内容长度必须介于{0}和{1}之间';
	$.fn.validatebox.defaults.rules.remote.message = '请修正该字段';
}
if ($.fn.numberbox) {
	$.fn.numberbox.defaults.missingMessage = '该输入项为必输项';
}
if ($.fn.combobox) {
	$.fn.combobox.defaults.missingMessage = '该输入项为必输项';
}
if ($.fn.combotree) {
	$.fn.combotree.defaults.missingMessage = '该输入项为必输项';
}
if ($.fn.combogrid) {
	$.fn.combogrid.defaults.missingMessage = '该输入项为必输项';
}
if ($.fn.calendar) {
	$.fn.calendar.defaults.weeks = [ '日', '一', '二', '三', '四', '五', '六' ];
	$.fn.calendar.defaults.months = [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月' ];
}
if ($.fn.datebox) {
	$.fn.datebox.defaults.currentText = '今天';
	$.fn.datebox.defaults.closeText = '关闭';
	$.fn.datebox.defaults.okText = '确定';
	$.fn.datebox.defaults.missingMessage = '该输入项为必输项';
	$.fn.datebox.defaults.formatter = function(date) {
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
		return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
	};
	$.fn.datebox.defaults.parser = function(s) {
		if (!s)
			return new Date();
		var ss = s.split('-');
		var y = parseInt(ss[0], 10);
		var m = parseInt(ss[1], 10);
		var d = parseInt(ss[2], 10);
		if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
			return new Date(y, m - 1, d);
		} else {
			return new Date();
		}
	};
}
if ($.fn.datetimebox && $.fn.datebox) {
	$.extend($.fn.datetimebox.defaults, {
		currentText : $.fn.datebox.defaults.currentText,
		closeText : $.fn.datebox.defaults.closeText,
		okText : $.fn.datebox.defaults.okText,
		missingMessage : $.fn.datebox.defaults.missingMessage
	});
}
// 扩展
$.extend($.fn.validatebox.defaults.rules, {
	phone : {// 验证电话号码
		validator : function(value) {
			return /^(0?\d{3}[- ]?)?[1-9]\d{7}(\-\d{1,4})?$/i.test(value);
		},
		message : '格式不正确,请使用下面格式:020-12345678或(020)12345678'
	},
	mobile : {// 验证手机号码
		validator : function(value) {
			return /^1\d{10}$/i.test(value);
		},
		message : '手机号码格式不正确,请使用下面格式:13812345678'
	},
	fax : {// 验证传真
		validator : function(value) {
			return /^(\d{2,4}\-)*\d{8}(\-\d{1,4})?$/i.test(value) && value.replace(/\-/g, "").length <= 20;
		},
		message : '传真号码不正确,请使用下面格式:011-12345678'
	},
	en : {// 英文及数字
		validator : function(value, param) {
			return (/^(\w)*$/i.test(value));
		},
		message : '请输入英文字符或数字'
	},
	num : {// 数字
		validator : function(value, param) {
			return (/^(\d)*$/i.test(value));
		},
		message : '请输入数字'
	},
	zh : {// 中文
		validator : function(value, param) {
			return (/^([\u4E00-\u9FA5])*$/i.test(value));
		},
		message : '请输入中文字符'
	},
	zh_en : {// 中英文及数字
		validator : function(value, param) {
			return (/^([\u4E00-\u9FA5]|\w)*$/i.test(value));
		},
		message : '请输入中英文字符或数字'
	},
	money : {// 金额
		validator : function(value, param) {
			return (/^(([0-9]|([1-9][0-9]{0,9}))((\.[0-9]{1,2})?))$/i.test(value));
		},
		message : '请输入金额'
	},
	nosp : {// 不存在空白字符
		validator : function(value, param) {
			return !(/\s+/i.test(value));
		},
		message : '不允许出现空白字符'
	},
	minLength : {
		validator : function(value, param) {
			return value.length >= param[0];
		},
		message : '输入内容长度不能小于{0}'
	},
	maxLength : {
		validator : function(value, param) {
			return value.length <= param[0];
		},
		message : '输入内容长度不能大于{0}'
	},
	equalTo : {
		validator : function(value, param) {
			return value == $(param[0]).val();
		},
		message : '两次输入的内容不一致'
	},
	decimal : {
		validator : function(value, param) {
			return (/^[0-9]+\.{0,1}[0-9]{0,2}$/i.test(value));
		},
		message : '请输入两位以内小数！'
	},
	decimalFor6 : {
		validator : function(value, param) {
			return (/^[0-9]+\.{0,1}[0-9]{0,6}$/i.test(value));
		},
		message : '请输入小数！'
	},
	id : {
		validator : function(value, param) {
			return (/^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/i.test(value));
		},
		message : '请输入正确的身份证号码！'
	}
});