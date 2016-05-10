<%@ page contentType="text/html;charset=utf-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Web报表(B/S报表)演示 - 打印显示控件展现报表</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<script src="/HRERP/resources/js/report/CreateControl.js" type="text/javascript"></script>
	<script src="/HRERP/resources/js/goldcow-all.js" type="text/javascript"></script>
    <style type="text/css">
        html,body {
            margin:0;
            height:100%;
        }
    </style>
</head>
<body style="margin:0">
   <!--  <script type="text/javascript"> 
        //用打印显示控件展现报表，从URL“../grf/1a.grf”获取报表膜板定义，从URL“../data/xmlCustomer.jsp”获取XML形式的报表数据，
	    //CreatePrintViewerEx("100%", "100%", "/p2p/resources/js/person/test.grf","/p2p/resources/js/person/xmlCustomer.jsp",true, "");
	    CreateDisplayViewerEx("100%", "100%", "/p2p/resources/js/person/test.grf","/p2p/resources/js/person/xmlCustomer.jsp",true, "");
    </script> -->
    <!-- 后台得到登录人id再传到前台，再传给DesignReportSave寻址用，在发往DesignReportSave的request请求里，无法用request.getSession().getAttribute("")拿到值 -->
    <input id="loginUserID" type="hidden" value="${loginUserID}"/>
    <!-- 绝对路径 -->
    <input id="realPath" type="hidden" value="${realPath}"/>
    <!-- 报表文件名,也是数据jsp文件名 -->
    <input id="fileName" type="hidden" value="${fileName}"/>
    
    <script type="text/javascript">
    
	    var Report = $("#fileName").val()+".grf";
	    if (Report == "null")
			Report = "";
	    else if (Report != "")
	        Report = $("#realPath").val()+("resources/grf/")+$("#loginUserID").val()+"/"+ Report;
	        //Report = $("#path").val()+$("#loginUserID").val()+"/"+ Report;
	        //alert("report路径：："+Report);
	    //数据油用户自己从数据库使用pull模式拉取，存在一定风险
	    
	   /*  var Data = "/HRERP/resources/js/report/xmlCustomer.jsp";
	    if (Data == "null")
			Data = "";
	    else if (Data != "")
	        Data = + Data; */
	        
	  CreateDesignerEx("100%", "100%", Report, '/HRERP/resources/js/report/DesignReportSave.jsp?report='+Report+'&loginUserID='+$("#loginUserID").val(), "", '<param name=OnSaveReport value=OnSaveReport>');
	  //如需设计器只显示普通视图与页面视图，应该设置 ViewStyle "<param name=ViewStyle value=6>"
	  //4个视图全部显示的替换方法   <param name=OnSaveReport value=OnSaveReport> 
	</script>
</body>
</html>
