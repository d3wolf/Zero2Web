package com.z2w.rb.service;

import java.util.Locale;
import java.util.MissingResourceException;

/**
 * ����������ʻ��Ľӿ�
 * @author Wolf
 *
 */
public interface Z2WResourceBundleService {
	/**
	 * ����Դ�ļ���ȡkeyֵ
	 * @param resouce
	 * @param key
	 * @param locale
	 * @return
	 * @throws MissingResourceException
	 */
	public String getLocalizedMessage(String resouce, String key, Locale locale)throws MissingResourceException;
}
