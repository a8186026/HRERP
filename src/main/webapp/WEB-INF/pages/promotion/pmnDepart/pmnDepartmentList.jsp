<%@page contentType="text/html;charset=UTF-8"%>

    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>会员日</title>
        <script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
        <script type="text/javascript" src="/HRERP/resources/js/promotion/pmnDepartmentInfo.js"></script>
    </head>
<script type="text/javascript">

/*  $(function() {
  // 折扣类型下拉框
    $('#discounttype').combobox({
        url: global_param.context_name + "/system/combobox/lists?pid=631",
        method: "get",
        valueField: "cbs_id",
        textField: "cbs_chn",
        selectOnNavigation: true,
        editable: false,
       
    });
    
    // 会员日下拉框
    $('#memberdaytype').combobox({
        url: global_param.context_name + "/system/combobox/lists?pid=624",
        method: "get",
        valueField: "cbs_id",
        textField: "cbs_chn",
        editable: false,
        selectOnNavigation: true,
 
    });
    $('#executeprice').combobox({
		url : global_param.context_name + "/system/combobox/lists?pid=650",
		method :"get",
		valueField : "cbs_id",
		textField : "cbs_chn",
		editable:false,
		selectOnNavigation: true,
	});
    $('#noexecuteprice').combobox({
        url: global_param.context_name + "/system/combobox/lists?pid=650",
        method: "get",
        valueField: "cbs_id",
        textField: "cbs_chn",
        editable: false,
        selectOnNavigation: true,
       
    });
 // 时间类型下拉框
    $('#timetype').combobox({
    	url : global_param.context_name + "/promotion/pmnMemday/lists",

    	//url : global_param.context_name + "/system/combobox/lists?pid=626",

        method: "get",
        valueField: "cbs_id",
        textField: "cbs_chn",
        editable: false,
        selectOnNavigation: true,
        onSelect: function(node){ 
			
            $('#timetype').val(node.timetype);
            $('#multiple').val(node.multiple);
            $('#noexecuteprice').val(node.noexecuteprice);
            $('#nodiscount').val(node.nodiscount);
            $('#executeprice').val(node.executeprice);
            $('#discount').val(node.discount);
            $('#date').val(node.date);
            $('#startdate').val(node.startdate);
            $('#enddate').val(node.enddate);
            $('#memberdaytype').val(node.memberdaytype);
            $('#disconttype').val(node.disconttype);

         },
        onChange: function(n, o) {
            alert(n);

            if (n == 627) {
                //div不显示，根据divid处理
                $("#DATE" ).css("display", "none");
                $("#ADDDATE" ).css("display", "none"); 
               // document.getElementById('DATE').style.display = 'none';
                //div显示
                
                document.getElementById('STARTDATE').style.display = 'block';
               document.getElementById('ENDDATE').style.display = 'block';
            } else {
                document.getElementById('DATE').style.display = 'block';
                document.getElementById('ADDDATE').style.display = 'block';
                $("#STARTDATE" ).css("display", "none");
                $("#ENDDATE" ).css("display", "none");
               // document.getElementById('STARTDATE').style.display = 'none';
               // document.getElementById('ENDDATE').style.display = 'none';
            }
        }

});
    function submitForm() {
        var data = util.submit('_form');
        if (data) {
            if (data.result == "success") {

                util.show(data.message);

                $('#updg').datagrid('reload');

            } else {
                util.error(data.message);
            }
        }
    }
    function inputstring(){

        var result =document.getElementById('date').value; 
        if (result==null||result==""){
        result +=document.getElementById('inputString').value;
        }
        else{
        	result +=","+ document.getElementById('inputString').value;
        }

        } */
</script>
    <body>
    
     <div id="mainContent" class="easyui-layout" fit="true" style="width: 100%; height: 100%;">
			<div data-options="region:'center',border:false">
			</div>
			<div data-options="region:'center',border:false" style="height:250px;margin-top:2px;">
				<table id="memdaydg"></table>
			</div>
			<div data-options="region:'center',border:false" style="height:320px ;width:100%" fit="true" >			
				<table id="updg" ></table>
			</div>
			<div data-options="region:'center',border:false" style="height:320px ;width:100%" fit="true" >			
				<table id="downdg"></table>
			</div>
		</div>	
      <!--   <div class="easyui-layout" fit="true" style="width: 100%; height: 100%;">

           <div data-options="region:'center',border:false" style="height:300px" fit="true" >	
               <table id="memdaydg" style="height:300px"></table>
          </div> 
 		  <div data-options="region:'center',border:false" style="height:300px ;margin-top:300px" fit="true" >	
                <table id="updg"></table>
          </div>
          <div data-options="region:'center',border:false" style="height:300px ;margin-top:600px" fit="true" >	
                 <table id="downdg"></table>
          </div>
        </div> -->
            <!-- 编辑窗口 -->
            <div id="editWindow"></div>
      
    </body>

    </html>
   
