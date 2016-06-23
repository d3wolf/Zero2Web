package com.z2w.action;

import java.util.List;
import java.util.Map;

import com.z2w.action.model.Z2WActionBean;

/**
 * action相关的缓存，在service中更新这些全局变量
 * @author Wolf
 *
 */
public class Z2WActionCache {

	/**
	 * 所有的action
	 */
	public static List<Z2WActionBean> actions = null;
	
	/**
	 * 所有的model
	 */
	public static Map<String, Z2WActionBean> actionModels = null;
	
	/**
	 * 所有的model-action
	 */
	public static Map<String, List<Z2WActionBean>> modelActionMap = null;
}
