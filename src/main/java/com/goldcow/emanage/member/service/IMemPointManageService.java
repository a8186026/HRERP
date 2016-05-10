package com.goldcow.emanage.member.service;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.BasMedicineInfo;
import com.goldcow.emanage.util.gen.entity.MemCardManage;
import com.goldcow.emanage.util.gen.entity.MemPointManage;
import com.goldcow.emanage.util.gen.entity.valueObject.MemCard.MemCardVO;
import com.goldcow.sframe.util.service.BaseIService;


/**
 * 积分操作服务层接口
 * 
 * @author YuanXin
 * @version v1.0
 * @since 2015-10-20
 */

public interface IMemPointManageService{
	/**
	 * 增加会员积分操作记录
	 * 
	 * @param bean 记录对象
	 * @param request 
	 * 
	 * @return 积分记录
	 */
	public MemPointManage add(MemPointManage bean, HttpServletRequest request);
	
}