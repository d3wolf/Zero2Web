package com.z2w.action.service;

import java.io.File;
import java.util.List;

import com.z2w.action.model.Z2WActionBean;
import com.z2w.common.exception.Z2WException;

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
	 * 根据名称获取action
	 * @param actionName
	 * @return
	 */
	public Z2WActionBean getActionByNameAndType(String actionName, String type) throws Z2WException;
	
	/**
	 * 根据action model名称获取所有子action(model)
	 * @param actionModelName
	 * @return
	 */
	public List<Z2WActionBean> getModelActions(String actionModelName) throws Z2WException;
	
	/**
	 * 根据action名称获取到它所有的父model
	 * @param actionName
	 * @return
	 */
	public List<Z2WActionBean> getActionModels(String actionName) throws Z2WException;
}
