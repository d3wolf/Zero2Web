package com.z2w.action.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.z2w.action.Z2WActionCache;
import com.z2w.action.dao.Z2WActionDAO;
import com.z2w.action.model.Z2WActionBean;
import com.z2w.action.service.Z2WActionService;
import com.z2w.common.exception.Z2WException;
import com.z2w.rb.service.Z2WResourceBundleService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class Z2WActionServiceImpl implements Z2WActionService {

	@Autowired
	private Z2WActionDAO actionDao;

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public void init(File actionsFolder) throws Z2WException {
		loadAllActionModels(actionsFolder);

		loadAllModels(actionsFolder);
	}

	private void loadAllModels(File actionsFolder) throws Z2WException {
		Map<String, Z2WActionBean> map = new HashMap<String, Z2WActionBean>();

		File[] files = actionsFolder.listFiles();
		for (File file : files) {
			if (file.isFile() && file.getName().endsWith("actionModels.xml")) {
				map.putAll(actionDao.getModelBeansByFile(file));
			}
		}

		Z2WActionCache.actionModels = map;//存入缓存
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

		//存入缓存
		Z2WActionCache.actions = actions;
		Z2WActionCache.modelActionMap = modelActionMap;

		return modelActionMap;
	}

	public List<Z2WActionBean> getModelActions(String actionModelName) throws Z2WException {
		if (Z2WActionCache.modelActionMap == null) {
			throw new Z2WException("model-action does not exist in cache");
		}

		return Z2WActionCache.modelActionMap.get(actionModelName);
	}

	public Z2WActionBean getActionByNameAndType(String actionName, String type) throws Z2WException {
		if (Z2WActionCache.actions == null) {
			throw new Z2WException("action does not exist in cache");
		}

		return actionDao.findActionByNameAndType(Z2WActionCache.actions, actionName, type);
	}

	public List<Z2WActionBean> getActionModels(String actionName) throws Z2WException {
		// TODO Auto-generated method stub
		return null;
	}

	public Z2WActionBean getActionModelByName(String actionModelName) throws Z2WException {
		if (Z2WActionCache.actionModels == null) {
			throw new Z2WException("action does not exist in cache");
		}
		return Z2WActionCache.actionModels.get(actionModelName);
	}

	@Autowired
	private Z2WResourceBundleService rbService;

	public String getLocalizedActionModelName(String modelName, String rbType, Locale locale) {
		String message = modelName;

		String key = "object." + modelName + "." + rbType;
		String rb = "";
		try {
			Z2WActionBean modelBean = getActionModelByName(modelName);
			rb = modelBean.getResource();

			message = rbService.getLocalizedMessage(rb, key, locale);
		} catch (MissingResourceException e) {
			System.out.println(String.format("can't find key %s in %s, use name instead", key, rb));
		} catch (Z2WException e) {
			System.out.println(String.format("can't find model bean, use name instead", key));
		}

		return message;
	}

	public String getLocalizedActionName(Z2WActionBean bean, String rbType, Locale locale) {
		String key = "";
		if (bean.isModel()) {
			key = "object." + bean.getName();
		} else {
			key = bean.getType() + "." + bean.getName() + "." + rbType;
		}
		String message = bean.getName();
		if (bean.getResource() != null) {
			try {
				message = rbService.getLocalizedMessage(bean.getResource(), key, locale);
			} catch (MissingResourceException e) {
				System.out.println(String.format("can't find key %s in %s, use name instead", key, bean.getResource()));
			}
		}

		return message;
	}
	
	/**
	 * 采用递归构造action tree的json字符串
	 * @param actions
	 * @return
	 * @throws Z2WException
	 */
	public JSONArray constructActionJson(List<Z2WActionBean> actions, Locale locale) throws Z2WException{
		JSONArray array = new JSONArray();

		for (Z2WActionBean action : actions) {
			if (!action.isModel()) {
				JSONObject jo = new JSONObject();
				jo.put("id", action.getName());
				jo.put("text", getLocalizedActionName(action,"title", locale));
				jo.put("url", action.getUrl());
				jo.put("type", action.getType());
				jo.put("iconCls", "icon-node");

				array.add(jo);
			}else{
				JSONObject jo = new JSONObject();
				jo.put("id", action.getName());
				jo.put("text", getLocalizedActionModelName(action.getName(),"title", locale));
				jo.put("state", "closed");//model默认折叠
				jo.put("iconCls", "icon-node");

				List<Z2WActionBean> subActions = getModelActions(action.getName());
				
				jo.put("children", constructActionJson(subActions, locale));
				
				array.add(jo);
			}
		}
		
		return array;
	}
	
}
