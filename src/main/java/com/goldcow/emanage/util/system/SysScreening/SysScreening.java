package com.goldcow.emanage.util.system.SysScreening;

import com.goldcow.emanage.util.gen.entity.valueObject.sysScreening.SysScreeningVO;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SysScreening {
	public List<SysScreeningVO> getDefaultScreening(String beanID,URL fileName) {
		List<SysScreeningVO> ss = new ArrayList<SysScreeningVO>();
		//获得dom解析器工厂（工作的作用是用于创建具体的解析器）
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
	    //获得具体的dom解析器  
	    DocumentBuilder db = null;
	    //解析一个xml文档，获得Document对象（根结点）  
	    Document document = null;
		try {
			db = dbf.newDocumentBuilder();
			document = db.parse(new File(fileName.toURI()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    //获取节点
	    NodeList list = document.getElementsByTagName("class");
	    for(int i = 0; i < list.getLength(); i++){
	    	Element element = (Element)list.item(i);
	    	if(element.getAttribute("id").equals(beanID)){
	    		NodeList fields = element.getElementsByTagName("field");
	    		for(int j=0;j<fields.getLength();j++){
	    			SysScreeningVO s = new SysScreeningVO();
	    			Element e = (Element)fields.item(j);
	    			s.setField(e.getAttribute("id"));
	    			s.setFieldName(e.getElementsByTagName("name").item(0).getFirstChild().getNodeValue());
			    	s.setFieldType(e.getElementsByTagName("type").item(0).getFirstChild().getNodeValue());
			    	s.setKeyWord(e.getElementsByTagName("value").item(0).getFirstChild().getNodeValue());
			    	s.setCompare_realation(e.getElementsByTagName("realation").item(0).getFirstChild().getNodeValue());
			    	s.setContact(e.getElementsByTagName("contact").item(0).getFirstChild().getNodeValue());
			    	ss.add(s);
	    		}
		    	break;
	    	}
	    }
	    return ss;
	}
}
