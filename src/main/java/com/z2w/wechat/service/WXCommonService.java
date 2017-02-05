package com.z2w.wechat.service;

import com.z2w.wechat.model.WXUser;

public interface WXCommonService {

	/**
	 * 获取或保存用户
	 * 
	 * @param name
	 * @return
	 */
	public WXUser getOrCreateWXUser(String name);

	

}
