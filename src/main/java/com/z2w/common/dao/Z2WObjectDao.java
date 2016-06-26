package com.z2w.common.dao;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.z2w.common.model.Z2WObject;
@NoRepositoryBean  
public interface Z2WObjectDao extends PagingAndSortingRepository<Z2WObject, Integer>{

}
