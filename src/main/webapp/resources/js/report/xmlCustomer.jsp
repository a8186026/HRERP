﻿<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file = "GenXmlData.jsp" %>
<%
XML_GenOneRecordset(response, "select * from sys_user where status=0 order by user_id");
%> 