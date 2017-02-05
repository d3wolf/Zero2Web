package com.z2w.wechat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import wechat.dao.WXUserDao;
import wechat.model.WXUser;
import wechat.service.WXCommonService;

@Service
@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class WXCommonServiceImpl implements WXCommonService {
	
	@Autowired
	private WXUserDao userJpaDao;
	


	public WXUser getOrCreateWXUser(String name) {
		WXUser user = null;
		
		List<WXUser> users = userJpaDao.getWXUserByName(name);
		if(users != null && users.size() > 0){
			user = users.get(0);
		}else{
			user = WXUser.newWXUser(name);
			userJpaDao.save(user);
		}
		return user;
	}



}
