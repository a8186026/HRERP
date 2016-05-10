<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	
	
	//确认BUTTON
	var sumbitButtonID = "submitDepartment";

    $(function() {
        // 折扣类型下拉框
        $('#discounttype').combobox({
            url: global_param.context_name + "/system/combobox/lists?pid=631",
            method: "get",
            valueField: "cbs_id",
            textField: "cbs_chn",
            selectOnNavigation: true,
            editable: false,
            onLoadSuccess: function() {
                discounttypeList = $('#search_discounttype').combobox("getData");
            }
        });
        
        // 会员日下拉框
        $('#memberdaytype').combobox({
            url: global_param.context_name + "/system/combobox/lists?pid=624",
            method: "get",
            valueField: "cbs_id",
            textField: "cbs_chn",
            editable: false,
            selectOnNavigation: true,
            onLoadSuccess: function() {
                memberdaytypeList = $('#search_memberdaytype').combobox("getData");
            }
        });

        // 时间类型下拉框
        $('#timetype').combobox({
            url: global_param.context_name + "/system/combobox/lists?pid=626",
            method: "get",
            valueField: "cbs_id",
            textField: "cbs_chn",
            editable: false,
            selectOnNavigation: true,
            onChange: function(n, o) {
                alert(n);

                if (n == 627) {
                    //div不显示，根据divid处理
                    document.getElementById('DATE').style.display = 'none';
                    //div显示
                    document.getElementById('STARTDATE').style.display = 'block';
                    document.getElementById('ENDDATE').style.display = 'block';
                } else {
                    document.getElementById('DATE').style.display = 'block';
                    document.getElementById('STARTDATE').style.display = 'none';
                    document.getElementById('ENDDATE').style.display = 'none';
                }
            },
            onLoadSuccess: function() {
                timetypeList = $('#search_timetype').combobox("getData");
            if($("#executeprice").val()==null||$("#executeprice").val()==""){
            document.getElementById('executeprice').value=100;
			document.getElementById('discount').value=100;
			document.getElementById('nodiscount').value=100;
			document.getElementById('multiple').value=100;
			document.getElementById('noexecuteprice').value=100;
}
            }
        });
    });
	function submitForm() {
		var data = util.submit('_form');
		if (data) {
			if (data.result == "success") {
				util.show(data.message);
				$('#updg').datagrid('reload');
				util.closeWindow('editWindow');
			} else {
				util.error(data.message);
			}
		}
	}
	  function cal() {
          var discount = document.getElementById('discount').value;
          var noexecuteprice = document.getElementById('noexecuteprice').value;
          var nodiscount = document.getElementById('nodiscount').value;
          if (nodiscount == null || nodiscount == "") {
          	alert("222");

          	document.getElementById('executeprice').value = parseInt(noexecuteprice) * parseInt(nodiscount) / 100;
          } else {
          	
          	var result = parseInt(noexecuteprice) * parseInt(discount) * parseInt(nodiscount) / 10000;
          	alert(result);
              $("#executeprice").val((result+""));

          }
      }
	
</script>

<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="Dialog-Bg">
		<div class="Dialog-formDiv clearfix">
		 <form:form id="_form" action="${formUrl_Memday}" method="${method_Memday}">
                        <input name="memid" type="hidden" value="${pmnMemday.memid}" />
                        <input name="SPRING_TOKEN" type="hidden" value="${sessionScope.SPRING_TOKEN}" />
                        <div class="float-l">
                            <div class="Dialog-form-title">
                                <label for="timetype" class="field">会员日类型：</label>
                            </div>
                            <div class="Dialog-form-item">
                                <input id="timetype" name="timetype" class="easyui-combobox" data-options="required:true" value="${pmnMemday.timetype}" />
                            </div>
                        </div>

                        <div class="float-l">
                            <div class="Dialog-form-title">
                                <label for="multiple" class="field">积分倍数：</label>
                            </div>
                            <div class="Dialog-form-item">
                                <input id="multiple" name="multiple" class="easyui-numberbox" data-options="required:true" value="${pmnMemday.multiple}" />
                            </div>
                        </div>
                        <div class="float-l">
                            <div class="Dialog-form-title">
                                <label for="noexecuteprice" class="field">会员日执行类型：</label>
                            </div>
                            <div class="Dialog-form-item">
                                <input id="noexecuteprice" name="noexecuteprice" class="easyui-combobox" data-options="required:true" value="${pmnMemday.noexecuteprice}" />
                            </div>
                        </div>
                        <div class="float-l">
                            <div class="Dialog-form-title">
                                <label for="nodiscount" class="field">会员日折扣：</label>
                            </div>
                            <div class="Dialog-form-item">
                                <input id="nodiscount" name="nodiscount" class="easyui-numberbox" data-options="required:true" value="${pmnMemday.nodiscount}" />
                            </div>
                        </div>
                        <div class="float-l">
                            <div class="Dialog-form-title">
                                <label for="discount" class="field">非会员日折扣：</label>
                            </div>
                            <div class="Dialog-form-item">
                                <input id="discount" name="discount" class="easyui-numberbox" data-options="required:true" value="${pmnMemday.discount}" />
                            </div>
                        </div>
                        <div class="float-l">
                            <div class="Dialog-form-title">
                                <label for="executeprice" class="field">非会员日执行类型：</label>
                            </div>
                            <div class="Dialog-form-item">
                                <input id="executeprice" name="executeprice" class="easyui-combobox" data-options="required:true" onblur="cal();" value="${pmnMemday.executeprice}" />
                            </div>
                        </div>
                        <div id="DATE" class="float-l">
                            <div class="Dialog-form-title">
                                <label for="date" class="field">日期：</label>
                            </div>
                            <div class="Dialog-form-item">
                                <input id="date" name="date" class="easyui-datebox" value="${pmnMemday.date}" />
                            </div>
                        </div>
                        <div id="STARTDATE" class="float-l">
                            <div class="Dialog-form-title">
                                <label for="startdate" class="field">开始日期：</label>
                            </div>
                            <div class="Dialog-form-item">
                                <input id="startdate" name="startdate" class="easyui-datebox" value="${pmnMemday.startdate}" />
                            </div>
                        </div>
                        <div id="ENDDATE" class="float-l">
                            <div class="Dialog-form-title">
                                <label for="enddate" class="field">結束日期：</label>
                            </div>
                            <div class="Dialog-form-item">
                                <input id="enddate" name="enddate" class="easyui-datebox" value="${pmnMemday.enddate}" />
                            </div>
                        </div>
                        <div class="float-l">
                            <div class="Dialog-form-title">
                                <label for="memberdaytype" class="field">时间方式：</label>
                            </div>
                            <div class="Dialog-form-item">
                                <input id="memberdaytype" name="memberdaytype" class="easyui-validatebox" data-options="required:true" value="${pmnMemday.memberdaytype}" />
                            </div>
                        </div>
                        <div class="float-l">
                            <div class="Dialog-form-title">
                                <label for="discounttype" class="field">折扣方式：</label>
                            </div>
                            <div class="Dialog-form-item">
                                <input id="discounttype" name="discounttype" class="easyui-validatebox" data-options="required:true" value="${pmnMemday.discounttype}" />
                            </div>
                        </div>
                    </form:form>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="text-align: right;">
		<input id="submitDepartment" class="btn-save"
			onmouseover="this.className='btn-save-over'"
			onmouseout="this.className='btn-save'"
			onmousedown="this.className='btn-save-down'" type="button"
			onclick="submitForm();" /> <input id="" class="btn-cancel"
			onmouseover="this.className='btn-cancel-over'"
			onmouseout="this.className='btn-cancel'"
			onmousedown="this.className='btn-cancel-down'" type="button"
			onclick="util.closeWindow('editWindow');" />
	</div>
</div>