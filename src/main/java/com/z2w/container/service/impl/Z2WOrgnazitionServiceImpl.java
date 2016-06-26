package com.z2w.container.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.z2w.container.dao.Z2WOrgnazitionRepository;
import com.z2w.container.model.Z2WOrgnazition;
import com.z2w.container.service.Z2WOrgnazitionService;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class Z2WOrgnazitionServiceImpl implements Z2WOrgnazitionService{

	@Autowired
	Z2WOrgnazitionRepository dao;
	
	public Z2WOrgnazition getOrgnazitionByName(String name){
		List<Z2WOrgnazition> orgs = dao.findZ2WOrgnazitionByName(name);
		if(orgs.size() > 0){
			Z2WOrgnazition exOrg = orgs.get(0);
			
			return exOrg;
		}
		return null;
	}
	
	public Z2WOrgnazition createOrUpdateOrgnazition(Z2WOrgnazition org){
		return dao.save(org);
	}
}
