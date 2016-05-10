package com.goldcow.sframe.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

/**
 * 分页查询方法
 * @author zouchao
 * @date 2014年5月15日
 * @version 1.0
 */
public class SearchPageUtil {
	
    /**
     * dataGrid分页查询方法
     * @author zouchao
     * @date 2014年5月15日
     * @version 1.0
     * @param totalCount
     *      总记录数          
     * @param resultMap
     *      结果集
     * @return JSON
     */
	public static String getResultJson(int totalCount,
        final List<Map<String, Object>> resultMaps) {
	    //JSON转换器
	    Gson gson = new Gson();	    	    
	    
	    Map<String, Object> dataGridMap = new HashMap<String, Object>();
	    
        //总记录数
        dataGridMap.put("total", totalCount);
        //分页结果集
        dataGridMap.put("rows", resultMaps);       
	    
		return gson.toJson(dataGridMap);
	}
}
