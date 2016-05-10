package com.goldcow.emanage.system.service.impl;

import java.lang.Double;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.system.persist.SysPermissionDao;
import com.goldcow.emanage.system.persist.SysScreeningDao;
import com.goldcow.emanage.system.service.ISysScreeningService;
import com.goldcow.emanage.util.gen.entity.valueObject.sysScreening.SysScreeningVO;
import com.goldcow.emanage.util.system.SysScreening.SysScreening;
/**
 * 审核次数设置服务层接口实现
 * 
 * @author wangqi
 * @version v1.0
 * @since 2015-6-1
 */
@Service
public class SysScreeningServiceImpl implements ISysScreeningService {
	private static Logger log = LoggerFactory.getLogger(SysScreeningServiceImpl.class);

	
	@Autowired
	private SysScreeningDao dao;
	/**
	 * 处理前台传来的字段
	 * 
	 * @author wubin
	 * @param field 字段
	 * @param fieldName 字段名
	 * @param className 类名
	 * @return SysScreeningVO list
	 */
	@Override
	public List<SysScreeningVO> deal(String fields, String fieldNames, String className) {
		
		log.debug("处理前台显示字段");
		List<SysScreeningVO> ss = new ArrayList<SysScreeningVO>();
		
		//对传过来的字符串进行分割
		String[] f=fields.split(",");
		String[] fn=fieldNames.split(",");
		for(int i=0;i<f.length;i++){
			SysScreeningVO s = new SysScreeningVO();
			s.setField(f[i]);
			s.setFieldName(fn[i]);
			s.setClassName(className);
			Class<?> c = null;
			Field field = null; 
			try {
				c = Class.forName("com.goldcow.emanage.util.gen.entity."+className);
				field = c.getDeclaredField(f[i]);
				String type = field.getGenericType().toString();
				if(type.equals("class java.lang.String")){
					s.setFieldType("String");
				}else if(type.equals("class java.lang.Integer")){
					s.setFieldType("Integer");
				}else if(type.equals("class java.lang.Double")){
					s.setFieldType("Double");
				}else if(type.equals("class java.util.Date")){
					s.setFieldType("Date");
				}else if(type.equals("class java.math.BigDecimal")){
					s.setFieldType("BigDecimal");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			ss.add(s);
		}
		return ss;
	}
	/**
	 * 数据筛选
	 * 
	 * @param data 数据
	 * @param className 类名
	 * @param sqlName 数据库名
	 * @return SysScreeningVO list
	 */
	@Override
	public List<Object> search(HttpServletRequest request,String data,String sqlName,String className,String ids) {	
		
		List<SysScreeningVO> ss = new ArrayList<SysScreeningVO>();
		String datas[] = data.split("\\|");
		
		if(!datas[0].equals("")){
			//添加前台选择的筛选条件
			for(int i=0;i<datas.length;i++){
				String[] str = datas[i].split(",");
				SysScreeningVO s = new SysScreeningVO();
				if(str[0]!=null&&!str[0].equals("")){
					s.setField(str[0]);
					s.setFieldType(str[1]);
					s.setKeyWord(str[2]);
					s.setContact(str[3]);
					s.setCompare_realation(str[4]);
					ss.add(s);
				}
			}
		}
		
		//添加默认配置
		SysScreening sysScr = new SysScreening();
		List<SysScreeningVO> defaultScrs= sysScr.getDefaultScreening(className,
				this.getClass().getClassLoader().getResource("setScreening.xml"));
		for(int j=0;j<defaultScrs.size();j++){
			if(ss.size()==0){
				defaultScrs.get(j).setContact("");
			}
			ss.add(defaultScrs.get(j));
		}
		
		//如果存在指定ID过滤，则添加此项
		if(ids!=null&&!ids.equals("")){
			List<Integer> list = new ArrayList<Integer>();
			String[] id = ids.split("_");
			for(int i=0;i<id.length;i++){
				list.add(Integer.parseInt(id[i]));
			}
			SysScreeningVO s = new SysScreeningVO();
			s.setField(dao.getPK(sqlName));
			s.setIds(list);
			if(ss.size()>0){
				s.setContact("and");
			}
			ss.add(s);
		}
		return dao.search(ss, sqlName);
	}

}