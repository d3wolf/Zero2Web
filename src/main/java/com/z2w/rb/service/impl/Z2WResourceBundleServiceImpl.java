package com.z2w.rb.service.impl;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.springframework.stereotype.Service;

import com.z2w.rb.service.Z2WResourceBundleService;

@Service
public class Z2WResourceBundleServiceImpl implements Z2WResourceBundleService{

	/**
	 * 从资源文件读取key值,基于java.util.ResourceBundle实现
	 */
	public String getLocalizedMessage(String resouce, String key, Locale locale)throws MissingResourceException {
		String message = null;
		
		ResourceBundle rb = ResourceBundle.getBundle(resouce, locale);
		message = rb.getString(key);
		
		return message;
	}
	

}
