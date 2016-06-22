package com.z2w.action;

import java.util.List;
import java.util.Map;

import com.z2w.action.model.Z2WActionBean;

/**
 * 用来缓存action相关信息，初始化使用，后续可以在service更新
 * @author Wolf
 *
 */
public class Z2WActionCache {

	/**
	 *缓存所有action信息
	 */
	public static List<Z2WActionBean> actions = null;
	
	/**
	 *缓存所有action model信息
	 */
	public static Map<String, Z2WActionBean> actionModels = null;
	
	/**
	 * 缓存所有model-action信息
	 */
	public static Map<String, List<Z2WActionBean>> modelActionMap = null;
}
