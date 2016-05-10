package com.goldcow.emanage.util.gen.entity;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class SaleMedicine {
	
	 private ActiveXComponent printController = null;  
	 private Dispatch printObj = null;
	 
	 public SaleMedicine(){  
	        try{  
	            printController = new ActiveXComponent("CLSID:07575197-690A-4DF9-8C0A-055CE7A4F0EE");  
	            printObj = (Dispatch)printController.getObject();  
	        }catch(Exception e){  
	            printObj = new Dispatch();  
	            System.out.println("加载失败");
	        }  
	 }  
	 
	 public String run(String param)
	 {
		 
		 System.out.println("jp:"+param);
		 //Variant[] parameters={new Variant("XSD0000030"),new Variant("2008-05-26"),new Variant("国药准字H20000656"),new Variant("三九感冒灵冲剂"),new Variant("01005"),new Variant("A"),new Variant("10g*9"),new Variant("盒"),new Variant("1"), new Variant("9.7")};
		 String[] parameters={"XSD0000030", "2015-12-7", "国药准字Z42021131", "红花注射液", "1005", "A", "5", "支", "1", "9.7"};
		 //RECIPECHECKLib
		 System.out.println(Dispatch.call(printObj,"GetVer").toString());
		 System.out.println(Dispatch.call(printObj,"YsdaInit",3,0).toString());//初始化
		 System.out.println(("hah:"+Dispatch.callN(printObj,"CheckRx2",parameters).toString()));
		 return Dispatch.call(printObj,"YsdaCheck").toString();
		 //return "test";
	 }
	 
}
