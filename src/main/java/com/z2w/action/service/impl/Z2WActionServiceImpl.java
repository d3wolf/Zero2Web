package com.z2w.action.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.z2w.action.Z2WActionCache;
import com.z2w.action.dao.Z2WActionDAO;
import com.z2w.action.model.Z2WActionBean;
import com.z2w.action.service.Z2WActionService;
import com.z2w.common.exception.Z2WException;

@Service
public class Z2WActionServiceImpl implements Z2WActionService {

	@Autowired
	private Z2WActionDAO actionDao;

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public void init(File actionsFolder) throws Z2WException {
		loadAllActionModels(actionsFolder);
	}

	private List<Z2WActionBean> loadAllActions(File actionsFolder) throws Z2WException {

		List<Z2WActionBean> actions = new ArrayList<Z2WActionBean>();

		File[] files = actionsFolder.listFiles();
		for (File file : files) {
			if (file.isFile() && file.getName().endsWith("actions.xml")) {
				actions.addAll(actionDao.getActionBeansByFile(file));
			}
		}

		return actions;
	}

	private Map<String, List<Z2WActionBean>> loadAllActionModels(File actionsFolder) throws Z2WException {

		Map<String, List<Z2WActionBean>> modelActionMap = new HashMap<String, List<Z2WActionBean>>();

		List<Z2WActionBean> actions = loadAllActions(actionsFolder);

		File[] files = actionsFolder.listFiles();
		for (File file : files) {
			if (file.isFile() && file.getName().endsWith("actionModels.xml")) {
				modelActionMap.putAll(actionDao.getActionModelByFile(file, actions));
			}
		}

		// 写到cache中去
		Z2WActionCache.actions = actions;
		Z2WActionCache.modelActionMap = modelActionMap;

		return modelActionMap;
	}

	public List<Z2WActionBean> getModelActions(String actionModelName) throws Z2WException {
		if (Z2WActionCache.modelActionMap == null) {
			throw new Z2WException("model-action没有成功加载到缓存");
		}

		return Z2WActionCache.modelActionMap.get(actionModelName);
	}

	public Z2WActionBean getActionByNameAndType(String actionName, String type) throws Z2WException {
		if (Z2WActionCache.actions == null) {
			throw new Z2WException("action没有成功加载到缓存");
		}
		
		return actionDao.findActionByNameAndType(Z2WActionCache.actions, actionName, type);
	}
	
	
	public List<Z2WActionBean> getActionModels(String actionName) throws Z2WException {
		// TODO Auto-generated method stub
		return null;
	}


	public Z2WActionBean getActionModelByName(String actionModelName) throws Z2WException {
		// TODO Auto-generated method stub
		return null;
	}
}
