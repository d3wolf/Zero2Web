package com.z2w.container.service;

import java.util.List;

import com.z2w.container.model.Z2WOrganization;

public interface Z2WOrgnazitionService {

	public Z2WOrganization getOrganizationByName(String name);
	
	public Z2WOrganization createOrUpdateOrganization(Z2WOrganization org);
	
	public List<Z2WOrganization> getAllOrganization();
}
