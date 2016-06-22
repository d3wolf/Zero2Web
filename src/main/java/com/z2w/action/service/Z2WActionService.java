package com.z2w.action.service;

import java.io.File;
import java.util.List;
import java.util.Locale;

import com.z2w.action.model.Z2WActionBean;
import com.z2w.common.exception.Z2WException;

public interface Z2WActionService {

	/**
	 * ��ʼ��action
	 * @param actionsFolder
	 * @throws Z2WException
	 */
	public void init(File actionsFolder) throws Z2WException;
	/**
	 * �������ƻ�ȡaction model
	 * @param actionModelName
	 * @return
	 */
	public Z2WActionBean getActionModelByName(String actionModelName) throws Z2WException;
	
	/**
	 * �������ƻ�ȡaction
	 * @param actionName
	 * @return
	 */
	public Z2WActionBean getActionByNameAndType(String actionName, String type) throws Z2WException;
	
	/**
	 * ����action model���ƻ�ȡ������action(model)
	 * @param actionModelName
	 * @return
	 */
	public List<Z2WActionBean> getModelActions(String actionModelName) throws Z2WException;
	
	/**
	 * ����action���ƻ�ȡ�������еĸ�model
	 * @param actionName
	 * @return
	 */
	public List<Z2WActionBean> getActionModels(String actionName) throws Z2WException;
	
	/**
	 * ��ȡaction model����ʾ����
	 * @param modelName
	 * @param rbType - title|description|icon...
	 * @param locale
	 * @return
	 */
	public String getLocalizedActionModelName(String modelName, String rbType, Locale locale);
	
	/**
	 * ��ȡaction����ʾ����
	 * @param bean
	 * @param rbType - title|description|icon...
	 * @param locale
	 * @return
	 */
	public String getLocalizedActionName(Z2WActionBean bean, String rbType, Locale locale);
}
