package com.z2w.common.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.z2w.common.exception.Z2WException;
import com.z2w.common.service.PersistenceService;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class PersistenceServiceImpl implements PersistenceService {


	public Object getObjectByOid(String oid) throws Z2WException {
		String[] oidArr = oid.split(":");
		String className;
		String id;
		try {
			className = oidArr[0];
			id = oidArr[1];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new Z2WException(oid + " format error.", e);
		}

		Class<?> objClass;
		try {
			objClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new Z2WException(className + " is not a valid class name.", e);
		}

		EntityManager em = bean.getObject().createEntityManager();

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		
		CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery(objClass);
		Root<?> object = criteriaQuery.from(objClass);
		Predicate condition = criteriaBuilder.equal(object.get("id"), id);
		criteriaQuery.where(condition);
		
		TypedQuery<?> typedQuery = em.createQuery(criteriaQuery);
		
		List<?> result = typedQuery.getResultList();

		if (result != null && result.size() > 0) {
			return result.get(0);
		}

		return null;
	}

	@Autowired
	private LocalContainerEntityManagerFactoryBean bean;
}
