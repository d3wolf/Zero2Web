package com.z2w.action;

import java.util.List;
import java.util.Map;

import com.z2w.action.model.Z2WActionBean;

/**
 * ��������action�����Ϣ����ʼ��ʹ�ã�����������service����
 * @author Wolf
 *
 */
public class Z2WActionCache {

	/**
	 *��������action��Ϣ
	 */
	public static List<Z2WActionBean> actions = null;
	
	/**
	 * ��������model-action��Ϣ
	 */
	public static Map<String, List<Z2WActionBean>> modelActionMap = null;
}
