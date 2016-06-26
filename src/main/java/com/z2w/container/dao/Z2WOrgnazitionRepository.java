package com.z2w.container.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.z2w.container.model.Z2WOrgnazition;


public interface Z2WOrgnazitionRepository extends PagingAndSortingRepository<Z2WOrgnazition, Integer>{
	
	public List<Z2WOrgnazition> findZ2WOrgnazitionByName(String name);
}
