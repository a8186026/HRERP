package com.goldcow.emanage.member.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.member.persist.MemCardManageDao;
import com.goldcow.emanage.member.persist.MemPointManageDao;
import com.goldcow.emanage.member.service.IMemCardManageService;
import com.goldcow.emanage.member.service.IMemPointManageService;
import com.goldcow.emanage.util.gen.entity.MemCardManage;
import com.goldcow.emanage.util.gen.entity.MemPointManage;
import com.goldcow.emanage.util.gen.entity.valueObject.MemCard.MemCardVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

@Service
public class MemPointManageServiceImpl implements IMemPointManageService {
	private static Logger log = LoggerFactory.getLogger(MemPointManageServiceImpl.class);
	/** 会员卡操作 */
	@Autowired
	private MemPointManageDao dao;
	
	
	@Override
	public MemPointManage add(MemPointManage bean, HttpServletRequest request) {
		// TODO Auto-generated method stub
		bean.setCreate_time(new Date());
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		dao.add(bean);
		log.debug("新增会员积分操作记录");
		return bean;
	}
	
		
	
}