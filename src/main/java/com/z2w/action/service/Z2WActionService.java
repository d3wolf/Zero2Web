package com.z2w.action.service;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.z2w.action.model.Z2WActionBean;
import com.z2w.common.exception.Z2WException;

import net.sf.json.JSONArray;

public interface Z2WActionService {

	/**
	 * 初始化action
	 * @param actionsFolder
	 * @throws Z2WException
	 */
	public void init(File actionsFolder) throws Z2WException;
	/**
	 * 根据名称获取action model
	 * @param actionModelName
	 * @return
	 */
	public Z2WActionBean getActionModelByName(String actionModelName) throws Z2WException;
	
	/**
	 * 根据名称和类型获取action
	 * @param actionName
	 * @return
	 */
	public Z2WActionBean getActionByNameAndType(String actionName, String type) throws Z2WException;
	
	/**
	 * 获取model下所有action
	 * @param actionModelName
	 * @return
	 */
	public List<Z2WActionBean> getModelActions(String actionModelName) throws Z2WException;
	
	/**
	 * 获取action的model
	 * @param actionName
	 * @return
	 */
	public List<Z2WActionBean> getActionModels(String actionName) throws Z2WException;
	
	/**
	 * 获取本地化的model名称
	 * @param modelName
	 * @param rbType - title|description|icon...
	 * @param locale
	 * @return
	 */
	public String getLocalizedActionModelName(String modelName, String rbType, Locale locale);
	
	/**
	 * 获取本地化的action名称
	 * @param bean
	 * @param rbType - title|description|icon...
	 * @param locale
	 * @return
	 */
	public String getLocalizedActionName(Z2WActionBean bean, String rbType, Locale locale);
	
	/**
	 * 采用递归构造action tree的json字符串
	 * @param actions
	 * @param seq
	 * @param extParams
	 * @param locale
	 * @return
	 * @throws Z2WException
	 */
	public JSONArray constructActionTreeJson(List<Z2WActionBean> actions, int seq, Map<String, Object> extParams, Locale locale) throws Z2WException;
	
	/**
	 * 构造model-action menu 的json字符串
	 * @param actions
	 * @param seq
	 * @param extParams
	 * @param locale
	 * @return
	 * @throws Z2WException
	 */
	public JSONArray constructActionMenuJson(List<Z2WActionBean> actions, int seq, Map<String, Object> extParams, Locale locale) throws Z2WException;
}
