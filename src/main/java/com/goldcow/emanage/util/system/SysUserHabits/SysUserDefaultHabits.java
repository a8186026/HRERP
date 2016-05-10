package com.goldcow.emanage.util.system.SysUserHabits;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.goldcow.emanage.util.gen.entity.SysUserHabit;

public class SysUserDefaultHabits extends DefaultHandler{
	 	private List<SysUserHabit> sysUserHabits = null;  
	    private SysUserHabit sysUserHabit = null;  
	    private String preTag = null;//作用是记录解析时的上一个节点名称  
	    private String page=null;
	    private String datagrid=null;
    
	    public List<SysUserHabit> getHabits(InputStream xmlStream) throws Exception{

	        SAXParserFactory factory = SAXParserFactory.newInstance();  
	        SAXParser parser = factory.newSAXParser();  
	        SysUserDefaultHabits handler = new SysUserDefaultHabits();  
	        parser.parse(xmlStream, handler);  
	        return handler.getHabits();  
	    }  
	      
	    public List<SysUserHabit> getHabits(){  
	        return sysUserHabits;  
	    }  
	      
	    @Override  
	    public void startDocument() throws SAXException {  
	    	sysUserHabits = new ArrayList<SysUserHabit>();  
	    }  
	  
	    @Override  
	    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {  
	        if("page".equals(qName)){  
	        	this.page = attributes.getValue(0);
	        }
	        if("datagrid".equals(qName)){
	        	this.datagrid = attributes.getValue(0);
	        }
	        if("field".equals(qName)){
	        	sysUserHabit = new SysUserHabit(); 
	        	sysUserHabit.setPage_id(page);
	        	sysUserHabit.setCtrl_id(datagrid);
	        	sysUserHabit.setHabit_field(attributes.getValue(0));
	        	
	        }
	        preTag = qName;//将正在解析的节点名称赋给preTag  
	    }  
	  
	    @Override  
	    public void endElement(String uri, String localName, String qName)  
	            throws SAXException {
	    	if("page".equals(qName)){  
	    		page = null;  
	        }
	    	if("datagrid".equals(qName)){  
	    		datagrid = null;  
	        }
	        if("field".equals(qName)){  
	        	sysUserHabits.add(sysUserHabit);  
	        	sysUserHabit = null;  
	        }  
	        preTag = null;/**当解析结束时置为空。这里很重要，例如，当图中画3的位置结束后，会调用这个方法 
	        ，如果这里不把preTag置为null，根据startElement(....)方法，preTag的值还是book，当文档顺序读到图 
	        中标记4的位置时，会执行characters(char[] ch, int start, int length)这个方法，而characters(....)方 
	        法判断preTag!=null，会执行if判断的代码，这样就会把空值赋值给book，这不是我们想要的。*/  
	    }  
	      
	    @Override  
	    public void characters(char[] ch, int start, int length) throws SAXException {  
	        if(preTag!=null){  
	            String content = new String(ch,start,length);  
	            if("name".equals(preTag)){  
	            	sysUserHabit.setHabit_field_name(content);  
	            }else if("width".equals(preTag)){  
	            	sysUserHabit.setHabit_width(Integer.parseInt(content));  
	            }else if("sortable".equals(preTag)){  
	            	sysUserHabit.setHabit_sortable(content);  
	            }else if("width".equals(preTag)){  
	            	sysUserHabit.setHabit_hidden(content);  
	            } 
	            	
	        }  
	    }  
	      
	
}
