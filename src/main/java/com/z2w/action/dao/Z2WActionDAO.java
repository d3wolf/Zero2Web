package com.z2w.action.dao;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.z2w.action.model.Z2WActionBean;
import com.z2w.common.exception.Z2WException;

public interface Z2WActionDAO {

	/**
	 * 根据文件读取action信息
	 * @param actionFile
	 * @return
	 * @throws Z2WException 
	 */
	public List<Z2WActionBean> getActionBeansByFile(File actionFile) throws Z2WException;

	/**
	 * 根据actionModel文件和actions获取action model
	 * @param actionModelFile
	 * @param actions
	 * @return
	 * @throws Z2WException
	 */
	public Map<String, List<Z2WActionBean>> getActionModelByFile(File actionModelFile, List<Z2WActionBean> actions)
			throws Z2WException;

	/**
	 * 根据名称和type获取action(model)
	 * @param actions
	 * @param name
	 * @param type
	 * @return
	 */
	public Z2WActionBean findActionByNameAndType(List<Z2WActionBean> actions, String name, String type);

}