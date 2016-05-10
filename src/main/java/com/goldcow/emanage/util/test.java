package com.goldcow.emanage.util;

import com.goldcow.emanage.util.gen.entity.GiftCardManage;
import com.goldcow.emanage.util.gen.entity.ProPriceTag;
import com.goldcow.emanage.util.gen.entity.WholeSaleOrder;
import com.goldcow.emanage.util.gen.entity.WholeSaleOrderBatch;
import com.goldcow.emanage.util.gen.entity.WholeSaleOrderProduct;
import com.goldcow.sframe.util.tools.根据实体创建数据库语句;

public class test {

	public static void main(String[] args) {
		System.out.println(根据实体创建数据库语句.生成新增语句(new ProPriceTag()));
		//System.out.println(根据实体创建数据库语句.生成更新语句(new ProPriceTag()));
		//System.out.println(根据实体创建数据库语句.生成根据主键查询语句(new Inventory()));
		//System.out.println(根据实体创建数据库语句.生成查询语句(new ProPriceTag()));
		
/*		
		try {
			System.out.println("111:"+InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
