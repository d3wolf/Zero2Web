package com.z2w.container.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.z2w.common.model.Z2WObject;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Z2WOrgnazition extends Z2WObject implements Z2WContainer {

	@Column(nullable = false, updatable = true)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getType() {
		return this.getClass();
	}
}
