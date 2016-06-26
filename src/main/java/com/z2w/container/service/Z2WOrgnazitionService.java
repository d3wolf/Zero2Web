package com.z2w.container.service;

import com.z2w.container.model.Z2WOrgnazition;

public interface Z2WOrgnazitionService {

	public Z2WOrgnazition getOrgnazitionByName(String name);
	
	public Z2WOrgnazition createOrUpdateOrgnazition(Z2WOrgnazition org);
}
