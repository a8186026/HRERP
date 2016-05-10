package com.goldcow.sframe.util.tools;

import java.lang.reflect.Field;

public class 根据实体创建数据库语句 {
	
	/**
	 * 根据实体生成查询语句
	 * @param object 实体对象
	 * @return 生成的查询语句
	 * */
	static public String 生成查询语句(Object 实体对象){
		 Field[] 所有属性 = 实体对象.getClass().getDeclaredFields();  
		 String 查询语句 = "";
		 查询语句 += "<select id=\"list\" parameterType=\"map\" resultType=\""+实体对象.getClass().getSimpleName()+"\">\n";
		 查询语句 += "\t<if test=\"rows!=null\">\n";
		 查询语句 += "\t\t select top ${rows}*\n";
		 查询语句 += "\t</if>\n";
		 查询语句 += "\t<if test=\"rows == null\">\n";
		 查询语句 += "\t\tselect *\n";
		 查询语句 += "\t</if>\n";
		 查询语句 += "\t\tfrom 请在此输入表名\n";
		 查询语句 += "\t<where>\n";
		 查询语句 += "\t\t status = 0\n";
		 for(int 序号 = 0 ; 序号 < 所有属性.length ; 序号++){
			 if(所有属性[序号].getName() != "serialVersionUID"){
				 查询语句 += "\t\t<if test=\""+所有属性[序号].getName()+"!=null\">\n";
				 查询语句 += "\t\tand "+所有属性[序号].getName()+" like #{"+所有属性[序号].getName()+"}\n";
				 查询语句 += "\t\t</if>\n";
			 }
		 }
		 查询语句 += "\t</where>\n";
		 查询语句 += "\t<if test=\"sort != null and order != null\">\n";
		 查询语句 += "\t\torder by ${sort} ${order}\n";
		 查询语句 += "\t</if>\n";
		 查询语句 += "</select>\n";
		return 查询语句;
	}
	
	/**
	 * 根据实体生成新增语句
	 * @param object 实体对象
	 * @return 生成的新增语句
	 * */
	static public String 生成新增语句(Object 实体对象){
		 Field[] 所有属性 = 实体对象.getClass().getDeclaredFields(); 
		 String 实体对象主键 = "";
		 int 属性起始序号 = 0;
		 if(所有属性[0].getName() != "serialVersionUID"){
			 实体对象主键 = 所有属性[0].getName();
			 属性起始序号 = 0;
		 } else{
			 实体对象主键 = 所有属性[1].getName();
			 属性起始序号 = 1;
		 }
		 String 新增语句 = "";
		 新增语句 += "<insert id=\"add\" parameterType=\""+实体对象.getClass().getSimpleName()+"\" useGeneratedKeys=\"true\" keyProperty=\""+实体对象主键+"\">\n";
		 新增语句 += "\t insert into 在此输入表名(\n";
		for(int 序号 = 属性起始序号 + 1 ; 序号 < 所有属性.length ; 序号++){
			if(序号%5 == 属性起始序号 +1){
				新增语句 += "\t";
			}
			新增语句 += 所有属性[序号].getName();
			if(序号!=所有属性.length-1){
				新增语句 += ", ";
			}else{
				新增语句 += ")" ; 
			}
			if(序号%5 == 属性起始序号||序号==所有属性.length-1){
				新增语句 += "\n";
			}
		}
		新增语句 += "\tvalues(\n";
		for(int 序号 = 属性起始序号 + 1 ; 序号 < 所有属性.length ; 序号++){
			if(序号%5 == 属性起始序号 +1){
				新增语句 += "\t";
			}
			新增语句 += "#{"+所有属性[序号].getName()+"}";
			if(序号!=所有属性.length-1){
				新增语句 += ", ";
			}else{
				新增语句 += ");" ; 
			}
			if(序号%5 == 属性起始序号||序号==所有属性.length-1){
				新增语句 += "\n";
			}
		}
		新增语句 += "</insert>";
		return 新增语句 ;
	}
	
	/**
	 * 根据实体生成根据主键查询语句
	 * @param object 实体对象
	 * @return 生成的根据主键查询语句
	 * */
	static public String 生成根据主键查询语句(Object 实体对象){
		Field[] 所有属性 = 实体对象.getClass().getDeclaredFields(); 
		 String 实体对象主键 = "";
		 if(所有属性[0].getName() != "serialVersionUID"){
			 实体对象主键 = 所有属性[0].getName();
		 } else{
			 实体对象主键 = 所有属性[1].getName();
		 }
		String 主键查询语句 = "";
		主键查询语句 += "<select id=\"get\" parameterType=\"Integer\" resultType=\""+实体对象.getClass().getSimpleName()+"\">\n";
		主键查询语句 += "\tselect *\n";
		主键查询语句 += "\tfrom 请在此输入表名 \n";
		主键查询语句 += "\twhere "+实体对象主键+" = #{id} \n";
		主键查询语句 += "\tand status = 0 \n";
		主键查询语句 += "</select>\n";
		return 主键查询语句;
	}
	
	/**
	 * 生成更新语句
	 * @param object 实体对象
	 * @return 生成的更新语句
	 * */
	static public String 生成更新语句(Object 实体对象){
		 Field[] 所有属性 = 实体对象.getClass().getDeclaredFields(); 
		 String 实体对象主键 = "";
		 int 属性起始序号 = 0;
		 if(所有属性[0].getName() != "serialVersionUID"){
			 实体对象主键 = 所有属性[0].getName();
			 属性起始序号 = 0;
		 } else{
			 实体对象主键 = 所有属性[1].getName();
			 属性起始序号 = 1;
		 }
		 String 更新语句 = "";
		 更新语句 += "<update id=\"update\" parameterType=\""+实体对象.getClass().getSimpleName()+"\">\n";
		 更新语句 += "\t update 在此输入表名\n";
		 更新语句 += "\t<set>\n";
		 for(int 序号 = 属性起始序号 + 1 ; 序号 < 所有属性.length ; 序号++){
			 更新语句 += "\t\t<if test=\""+所有属性[序号].getName()+" != null\">\n";
			 更新语句 += "\t\t"+所有属性[序号].getName() +" = #{"+所有属性[序号].getName()+"},\n";
			 更新语句 += "\t\t</if>\n";
		 }
		 更新语句 += "</set>";
		 更新语句 += "where 实体对象主键 = #{"+实体对象主键+"}\n";
		 更新语句 += "</update>\n";
		 return 更新语句;
	}
}
