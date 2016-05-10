package com.goldcow.sframe.util.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.Node;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;

import org.dom4j.io.XMLWriter;
public class CreateMapper {
	 static public  Boolean createMapper(Object bean,String table,String underEmanage){
		//获得实体类bean的名称
		String s = bean.toString().substring(bean.toString().lastIndexOf(".")+1);
		String beanName=s.substring(0, s.lastIndexOf("@"));
		//文件输出地址
		String FilePath="D:\\"+beanName+"Mapper.xml";
		//写入文件头
		Document document = DocumentHelper.createDocument();
		document.addComment("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\""
				+ " \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
	    //获得实体类的全部属性
		Field[] attributes=bean.getClass().getDeclaredFields();
		//获得实体类主键，(不为serialVersionUID的第一个属性)
		String parameterType = "";
		if(attributes[0].getName()!="serialVersionUID"){
			parameterType=attributes[0].getName();
		}else{
			parameterType=attributes[1].getName();
		}
		// 根节点Mappper标签
		    document.addComment("<namespace 指向Dao接口 ");
			Element mapperElement = document.addElement("mapper");
			//Mapper标签加上namespace属性，指向Dao接口
			
			mapperElement.addAttribute("namespace", "com.goldcow.emanage."+underEmanage+".persist."+beanName+"Dao");
		
			
			
			
		//-----------------写入get方法----------------------
			Element getElement = mapperElement.addElement("select");
			getElement.addComment("根据id获取实体对象 ");
			//加入select节点的属性
			getElement.addAttribute("id", "get");
			getElement.addAttribute("parameterType", "Integer");
			getElement.addAttribute("resultType", beanName);
			//查询语句内容
			String get = "";
			get="\n\t\t select *\n";
			get += "\t\tfrom "+table+"\n";
			get += "\t\twhere "+parameterType+" =#{"+parameterType+"}\n";
			get += "\t\tand status = 0 \n";
			getElement.setText(get);
			
			
		//------------------------写入lists方法-----------------------
			Element listsElement = mapperElement.addElement("select");
			//listsElement.addElement("分页查询");
			//加入select节点的属性
			listsElement.addAttribute("id", "list");
			listsElement.addAttribute("parameterType", beanName);
			listsElement.addAttribute("resultType", beanName);
			//查询语句内容
			   //有分页查询
				Element ifRowsLists = listsElement.addElement("if");
				ifRowsLists.addAttribute("test", "rows != null and rows !=0");
				ifRowsLists.setText("select top ${rows}*");
				//无分页查询
				Element ifNoRowsLists = listsElement.addElement("if");
				ifNoRowsLists.addAttribute("test", "rows == null or rows ==0");
				ifNoRowsLists.setText("select *");
			//从数据库表中查询
			listsElement.setText("from "+table);
			//查询条件
			    Element whereLists = listsElement.addElement("where");
			    whereLists.setText("status = 0");
			    //加入搜索条件
			    for(int i =0;i<attributes.length;i++){
			    	if(attributes[i].getName()!="serialVersionUID"){
			    		Element ifLists = whereLists.addElement("if");
			    		ifLists.addAttribute("test", attributes[i].getName()+"!=null" );
			    		ifLists.setText("and "+attributes[i].getName()+" like#{"+attributes[i].getName()+"}");
			    	}
			    }
			    //加入offset搜索
			    Element ifLists = whereLists.addElement("if");
	    		ifLists.addAttribute("test", "offset != null and offset !=0");
	    		ifLists.setText("and "+parameterType+" not in(select top ${offset} "+parameterType+" from "+table+" where status = 0)");
	    	//加入sort order排序搜索
	    		 Element sortLists = listsElement.addElement("if");
	    		 sortLists.addAttribute("test", "sort != null and order != null");
				 sortLists.setText("order by ${sort} ${order}");
			
				 
		//------------------------写入count方法-----------------------
				 Element countElement = mapperElement.addElement("select");
				 countElement.addComment(" 数据条数");
				 countElement.addAttribute("id", "count");
				 countElement.addAttribute("parameterType", beanName);
				 countElement.addAttribute("resultType", "int");
				 countElement.setText("select count("+ parameterType +") as count from  "+table);
				 Element countWhereElement = countElement.addElement("where");
				 countWhereElement.setText("status = 0");
				 for(int i =0;i<attributes.length;i++){
				    	if(attributes[i].getName()!="serialVersionUID"){
				    		Element ifcountLists = countWhereElement.addElement("if");
				    		ifcountLists.addAttribute("test", attributes[i].getName()+"!=null" );
				    		ifcountLists.setText("and "+attributes[i].getName()+" like#{"+attributes[i].getName()+"}");
				    	}
				    }
			
			
		//----------------------------------写入add方法-----------------------------
			Element addElement = mapperElement.addElement("insert");
			addElement.addComment(" 添加");
			//加入insert节点的属性
			addElement.addAttribute("id", "add");
			addElement.addAttribute("parameterType", beanName);
			addElement.addAttribute("useGeneratedKeys", "true");
			addElement.addAttribute("keyProperty", parameterType);
			 String add="";
			 add+=" insert into  "+table+"(";
			 for(int i =0;i<attributes.length;i++){
				 if(i==attributes.length-1){
					 add+=attributes[i].getName();
				 }else{
					 if(attributes[i].getName()!="serialVersionUID"&&attributes[i].getName()!=parameterType){
			    		add+=attributes[i].getName()+",";
			    	}
			    		
			    	}
			    }
			 add+=")values(";
			 for(int i =0;i<attributes.length;i++){
				 if(i==attributes.length-1){
			    		add+="#{"+attributes[i].getName()+"});";
			    	}else{
			    		if(attributes[i].getName()!="serialVersionUID"&&attributes[i].getName()!=parameterType){
				    		add+="#{"+attributes[i].getName()+"},";
				    	}
			    	}
			    	
			    }
			 addElement.setText(add);
			
			
			//----------------------------------写入update方法-----------------------------
				Element updateElement = mapperElement.addElement("update");
				updateElement.addComment(" 更新表中数据");
				//加入insert节点的属性
				updateElement.addAttribute("id", "update");
				updateElement.addAttribute("parameterType", beanName);
				updateElement.setText("update "+table);
				Element setElement = updateElement.addElement("set");
				//加入搜索条件
				
			    for(int i =0;i<attributes.length;i++){
			    	if(attributes[i].getName()!="serialVersionUID"&&attributes[i].getName()!=parameterType){
			    		Element ifSetLists = setElement.addElement("if");
			    		ifSetLists.addAttribute("test", attributes[i].getName()+"!=null" );
			    		ifSetLists.setText(" "+attributes[i].getName()+" =#{"+attributes[i].getName()+"},");
			    	}
			    }
			    Element updateWhere = updateElement.addElement("where");
			    updateWhere.setText(parameterType+"=#{"+parameterType+"}");
			    
			
			    
			  //----------------------------------写入delete方法-----------------------------
			    Element deleteElement = mapperElement.addElement("delete");
			    deleteElement.addComment(" 根据ID删除表中数据");
			    deleteElement.addAttribute("id", "delete");
			    deleteElement.addAttribute("parameterType", "int");
			    deleteElement.setText("update "+table+" set status = 9 where "+parameterType+" = #{"+parameterType+"}");
			
			try {
				OutputFormat format = OutputFormat.createPrettyPrint();
				format.setIndentSize(4);
				format.setNewlines(true);
				format.setTrimText(false);
				format.setPadText(true);
				format.setEncoding("UTF-8");
				XMLWriter output = new XMLWriter(new FileWriter(new File(FilePath)), format);
				output.write(document);
				output.close();
				System.out.println(FilePath+"创建成功");
				return true;
			} catch (IOException e) {
				System.out.println(e.getMessage());
				return false;
			}
	}

}
