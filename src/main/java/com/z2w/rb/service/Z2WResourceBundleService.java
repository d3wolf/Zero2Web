package com.z2w.rb.service;

import java.util.Locale;
import java.util.MissingResourceException;


public interface Z2WResourceBundleService {

	public String getLocalizedMessage(String resouce, String key, Locale locale)throws MissingResourceException;
}
