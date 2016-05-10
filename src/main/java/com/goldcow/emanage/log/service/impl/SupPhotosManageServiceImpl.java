package com.goldcow.emanage.log.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.basInfo.service.impl.BasFactoryInfoServiceImpl;
import com.goldcow.emanage.log.persist.SupPhotosManageDao;
import com.goldcow.emanage.log.service.ISupPhotosManageService;
import com.goldcow.emanage.util.gen.entity.SupInfoManage;



@Service
public class SupPhotosManageServiceImpl implements ISupPhotosManageService {
	private static Logger log = LoggerFactory.getLogger(BasFactoryInfoServiceImpl.class);
	/** 财务信息操作  */
	@Autowired
	private SupPhotosManageDao dao;
	@Override
	public SupInfoManage add(SupInfoManage bean, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public SupInfoManage update(SupInfoManage bean, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SupInfoManage getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<SupInfoManage> list(SupInfoManage bean) {
		// TODO Auto-generated method stub
		System.out.println("============");
		return dao.list(bean);
	}
	
}