package com.goldcow.sframe.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 表格控件使用数据结构，map按照控件数据要求能直接转换
 * 
 * 主要需要设置当前页数，记录总数，数据结果列表
 * 
 * 最后使用getGridData方法得到map数据
 * @param <T>
 * */
public class GridDataBean<T> implements Serializable
{
    private static final long serialVersionUID = -8357677307164273527L;
    private Map<String, Object> resMap = new HashMap<String, Object>();

    public GridDataBean()
    {
    }

    /*public GridDataBean(int page, int total, List<Map<String, Object>> listmap)
    {
        setPageNum(page);
        setTotal(total);
        setRows(listmap);
    }*/
    
    public GridDataBean(int pages, List<T> listmap,int rows)
    {
        setPages(pages);
        setRows(listmap);
        setrows(rows);
    }

    public void setPages(int pages)
    {
        resMap.put("pages", pages);
    }

    public void setRows(List<T> listmap)
    {
        resMap.put("Rows", listmap);
    }
    public void setrows(int rows){
    	resMap.put("rows", rows);
    }
    public List<Map<String, Object>> getRows()
    {
        Object o = resMap.get("Rows");
        if (o == null)
            return null;
        return (List<Map<String, Object>>) o;
    }

    public Map<String, Object> getGridData()
    {
        return resMap;
    }
}
