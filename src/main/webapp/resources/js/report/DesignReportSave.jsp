﻿<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.io.*" %>
<%@ page import="com.goldcow.sframe.util.SysUtil" %>
<%@ page import="com.goldcow.emanage.util.gen.bean.LoginUser" %>
<%
    int DataLen = request.getContentLength();
    if (DataLen > 0)
    {
		final int BufSize = 1024; //一次读写数据的缓冲区大小
		System.out.println("_________________________report____________"+request.getParameter("report"));
		 String FileName = request.getParameter("report");//request.getSession().getServletContext().getRealPath("/resources/grf/")+"\\"+request.getParameter("loginUserID")+"\\" + request.getParameter("report");
	//	System.out.println("__________________________________________________"+FileName);
		
        //打开写入文件    request.getSession().getServletContext().getRealPath()  
        //((LoginUser) request.getSession().getAttribute("userSession"))
       
		//String FileName ="E:\\workspace_HRERP[2015-11-24]\\HRERP\\src\\main\\webapp\\resources\\js\\report\\test.grf";
        FileOutputStream fos = new FileOutputStream(FileName);
              
		//注意：要分批读写，不然在某些条件下对大数据(>8K)模板保存不成功
        //读出客户端发送的数据，并写入文件流
        byte[] DataBuf = new byte[BufSize];   
        ServletInputStream sif = request.getInputStream();
		int TotalReadedLen = 0;
        while (DataLen > TotalReadedLen)
        {
            int TheReadedlen = sif.read(DataBuf, 0, BufSize);
            if (TheReadedlen <= 0)
                break;
                
			fos.write(DataBuf, 0, TheReadedlen);
        
            TotalReadedLen += TheReadedlen;
        }

        fos.close();
    }
%>
 