package com.z2w.doc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.z2w.container.model.Z2WContainer;
import com.z2w.doc.dao.Z2WSimpleDocRepository;
import com.z2w.doc.model.Z2WSimpleDocument;
import com.z2w.doc.service.Z2WSimpleDocService;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class Z2WSimpleDocServiceImpl implements Z2WSimpleDocService {

	@Autowired
	Z2WSimpleDocRepository simpleDocDao;
	
	public List<Z2WSimpleDocument> getDocByContainer(Z2WContainer container) {

		String oid = container.toString();
		String[] oidArr = oid.split(":");
		
		String className = oidArr[0];
		Integer id = Integer.parseInt(oidArr[1]);
		
		return simpleDocDao.getZ2WSimpleDocumentByContainerClassAndContainerId(className, id);
	}
	
	public void createDoc(Z2WSimpleDocument simpleDoc) {

		simpleDocDao.save(simpleDoc);
	}

}
