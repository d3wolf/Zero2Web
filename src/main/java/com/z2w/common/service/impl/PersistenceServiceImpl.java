package com.z2w.common.service.impl;

import com.z2w.common.dao.Z2WObjectDao;
import com.z2w.common.model.Z2WObject;
import com.z2w.common.service.PersistenceService;


public class PersistenceServiceImpl implements PersistenceService {

	

	private Z2WObjectDao pr;
	
	/* (non-Javadoc)
	 * @see com.z2w.common.service.impl.PersistenceService#save(com.z2w.common.model.Z2WObject)
	 */
	public Z2WObject save(Z2WObject obj){
		return (Z2WObject) pr.save(obj);
	}
	
	/* (non-Javadoc)
	 * @see com.z2w.common.service.impl.PersistenceService#insert(java.lang.Iterable)
	 */
	public Iterable<Z2WObject> insert(Iterable<Z2WObject> list){
		Iterable<Z2WObject> result = pr.save(list);
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.z2w.common.service.impl.PersistenceService#find(java.lang.Integer)
	 */
	public void find(Integer id){
		pr.findOne(id);
	}
}
