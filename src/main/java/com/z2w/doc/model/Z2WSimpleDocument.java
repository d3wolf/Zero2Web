package com.z2w.doc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.springframework.beans.factory.annotation.Autowired;

import com.z2w.common.exception.Z2WException;
import com.z2w.common.model.Identified;
import com.z2w.common.model.Z2WObject;
import com.z2w.common.service.PersistenceService;
import com.z2w.common.service.impl.PersistenceServiceImpl;
import com.z2w.container.model.Z2WContained;
import com.z2w.container.model.Z2WContainer;

/**
 * 简单文档对象
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Z2WSimpleDocument extends Z2WObject implements Identified, Z2WContained {

	@Column(nullable = false, updatable = true)
	private String number;

	@Column(nullable = false, updatable = true)
	private String name;

	@Column(nullable = false, updatable = true)
	@Autowired
	private String containerClass;

	@Column(nullable = false, updatable = true)
	@Autowired
	private Integer containerId;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayIdentity() {
		return this.name + "-" + this.number;
	}


	public Z2WContainer getContainer() {
	//	PersistenceService persistenceService = new PersistenceServiceImpl();
		Z2WContainer container = null;
	/*	try {
			container = (Z2WContainer) persistenceService.getObjectByOid(containerClass + ":" + containerId);
		} catch (Z2WException e) {
			e.printStackTrace();
		}*/
		return container;
	}

	public void setContainer(Z2WContainer container) {
		String oid = container.toString();
		
		this.containerClass = oid.split(":")[0];
		this.containerId = Integer.parseInt(oid.split(":")[1]);
	}

}
