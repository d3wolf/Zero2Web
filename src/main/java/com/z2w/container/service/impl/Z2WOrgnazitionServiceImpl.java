package com.z2w.container.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.z2w.container.dao.Z2WOrgnazitionRepository;
import com.z2w.container.model.Z2WOrganization;
import com.z2w.container.service.Z2WOrgnazitionService;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class Z2WOrgnazitionServiceImpl implements Z2WOrgnazitionService{

	@Autowired
	Z2WOrgnazitionRepository dao;
	
	public Z2WOrganization getOrganizationByName(String name){
		List<Z2WOrganization> orgs = dao.findZ2WOrgnazitionByName(name);
		if(orgs.size() > 0){
			Z2WOrganization exOrg = orgs.get(0);
			
			return exOrg;
		}
		return null;
	}
	
	public Z2WOrganization createOrUpdateOrganization(Z2WOrganization org){
		return dao.save(org);
	}
	
	public List<Z2WOrganization> getAllOrganization(){
		List<Z2WOrganization> orgs = (List<Z2WOrganization>) dao.findAll();
		
		return orgs;
	}
}
