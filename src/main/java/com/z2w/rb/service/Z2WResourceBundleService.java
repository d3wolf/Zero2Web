package com.z2w.rb.service;

import java.util.Locale;
import java.util.MissingResourceException;

/**
 * 用来处理国际化的接口
 * @author Wolf
 *
 */
public interface Z2WResourceBundleService {
	/**
	 * 从资源文件读取key值
	 * @param resouce
	 * @param key
	 * @param locale
	 * @return
	 * @throws MissingResourceException
	 */
	public String getLocalizedMessage(String resouce, String key, Locale locale)throws MissingResourceException;
}
