package com.z2w.common.service;

import java.util.Map;

import com.z2w.common.exception.Z2WException;

public interface PersistenceService {

	/**
	 * 根据oid获取对象，oid格式(&ltClassName>:&ltid>, e.g. com.z2w.common.model.Z2WObject:1)
	 * @param oid
	 * @return
	 * @throws Z2WException
	 */
	public Object getObjectByOid(String oid) throws Z2WException;
	
	/**
	 * 获取一个对象的所有getter方法的结果
	 * @param obj
	 * @throws Z2WException
	 */
	public Map<String, Object> getAllGetResult(Object obj) throws Z2WException;

}