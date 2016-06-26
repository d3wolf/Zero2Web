package com.z2w.common.service;

import com.z2w.common.model.Z2WObject;

public interface PersistenceService {

	Z2WObject save(Z2WObject obj);

	Iterable<Z2WObject> insert(Iterable<Z2WObject> list);

	void find(Integer id);

}