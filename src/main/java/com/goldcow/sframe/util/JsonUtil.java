package com.goldcow.sframe.util;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.goldcow.platform.syscontext.util.StringUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

public class JsonUtil {

	/**
	 * 根据传入的json数组，返回指定包含类型的集合
	 * 
	 * @param jsonStr [{id:1,name:gx},{id:2,name:wxm}]
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> jsonArrayToList(String jsonStr, Class<T> clazz) {
		if (jsonStr == null || jsonStr.trim().equals("")) {
			return null;
		}
		List<T> resultList = new ArrayList<T>();
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(java.lang.Double.class, new DoubleAdapter());
		builder.registerTypeAdapter(java.lang.Integer.class, new IntegerAdapter());
		builder.registerTypeAdapter(java.sql.Date.class, new SqlDateAdapter("yyyy-MM-dd"));
		builder.registerTypeAdapter(java.sql.Timestamp.class, new SqlTimestampAdapter("yyyy-MM-dd HH:mm:ss"));
		Gson gson = builder.create();
		//Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(jsonStr); // 将json字符串转换成JsonElement
		JsonArray jsonArray = jsonElement.getAsJsonArray(); // 将JsonElement转换成JsonArray
		Iterator<JsonElement> it = jsonArray.iterator(); // Iterator处理
		while (it.hasNext()) { // 循环
			jsonElement = (JsonElement) it.next(); // 提取JsonElement
			String menu = jsonElement.toString(); // JsonElement转换成String
			if(StringUtil.isBlank(menu)){
				menu = null;
			}
			T bean = gson.fromJson(menu, clazz); // String转化成JavaBean
			resultList.add(bean); // 加入List
		}
		return resultList;
	}
	

	/**
	 * 将传入的对象转为json字符串，支持不固定参数 
	 * 使用示例：allToJson(1,2,3)，allToJson()， allToJson(null)， allToJson(new TreeBean(), "aaa")
	 * 
	 * @author Allen
	 * @return json字符串
	 * */
	public static String allToJson(Object... objArray) {
		Gson gson = new Gson();
		if(objArray!=null){
			return gson.toJson(objArray);
		}
		return gson.toJson(new Object[0]);
	}
	
    public static class DoubleAdapter implements
	 JsonDeserializer<java.lang.Double> {
	     
	 public DoubleAdapter(){
	     
	 }
	 @Override
	 public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)  throws JsonParseException
        {
            String doubleStr = json.getAsString();
            if(StringUtil.isBlank(doubleStr)){
                return null;
            }else{
                return new Double(doubleStr);
            }
        }

	 } 
    public static class IntegerAdapter implements
    JsonDeserializer<java.lang.Integer> {
        
        public IntegerAdapter(){
            
        }
        @Override
        public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)  throws JsonParseException
        {
            String str = json.getAsString();
            if(StringUtil.isBlank(str)){
                return null;
            }else{
                return new Integer(str);
            }
        }
        
    } 
	 public static class SqlDateAdapter implements
	 JsonDeserializer<java.sql.Date> {
	     
	     private String dateFormat;
	     
	     public SqlDateAdapter(){
	         
	     }
	     public SqlDateAdapter(String dateFormat){
	         this.dateFormat = dateFormat;
	     }
	     @Override
	     public java.sql.Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)  throws JsonParseException
	     {
	         String str = json.getAsString();
	         if(StringUtil.isBlank(str)){
	             return null;
	         }else{
	             return DateUtil.strToSqlDate(str, dateFormat);
	         }
	     }
	     
	 } 
	 public static class SqlTimestampAdapter implements
	 JsonDeserializer<java.sql.Timestamp> {
	     
	     private String dateFormat;
	     
	     public SqlTimestampAdapter(){
	         
	     }
	     public SqlTimestampAdapter(String dateFormat){
	         this.dateFormat = dateFormat;
	     }
	     @Override
	     public java.sql.Timestamp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)  throws JsonParseException
	     {
	         String str = json.getAsString();
	         if(StringUtil.isBlank(str)){
	             return null;
	         }else{
	             return DateUtil.strTimestamp(str, dateFormat);
	         }
	     }
	     
	 } 
	 
	 /**
		 * 将json串转换为对象
		 * 
		 * @param json json字符串
		 * @param clazz 对象类型
		 * @return 转换对象
		 * @throws JsonParseException
		 * @throws JsonMappingException
		 * @throws IOException
		 */
		public static <T> T jsonStringToObject(String json, Class<T> clazz) throws JsonParseException,JsonProcessingException, IOException {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(json, clazz);
		}
}
