package com.z2w.container.model;

import com.z2w.common.model.Identified;

/**
 * 定义一个container接口，实现该接口的具有容器特性
 * @author Wolf
 *
 */
public interface Z2WContainer extends Identified{

	public String getName();
	
	public Class<?> getType();
}
