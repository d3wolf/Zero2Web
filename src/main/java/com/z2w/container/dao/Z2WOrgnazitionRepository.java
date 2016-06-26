package com.z2w.container.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.z2w.container.model.Z2WOrganization;


public interface Z2WOrgnazitionRepository extends PagingAndSortingRepository<Z2WOrganization, Integer>{
	
	public List<Z2WOrganization> findZ2WOrgnazitionByName(String name);
}
