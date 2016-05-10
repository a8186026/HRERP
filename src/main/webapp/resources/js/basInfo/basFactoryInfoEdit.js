$(function() {
	// 性别下拉框
	util.select({
		id : 'sex',
		required : true,
		param : {
			typeCode : 'SEX'
		}
	});
});

function submitForm() {
	var data = util.submit('_form');
	if (data) {
		if (data.result == "success") {
			util.show(data.message);
			$('#userdg').datagrid('reload');
			util.closeWindow('userEditWindow');
		} else {
			util.error(data.message);
		}
	}
}