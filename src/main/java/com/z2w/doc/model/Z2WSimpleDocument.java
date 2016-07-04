package com.z2w.doc.model;

import com.z2w.common.model.Identified;
import com.z2w.common.model.Z2WObject;
import com.z2w.container.model.Z2WContainer;

/**
 * 简单文档对象
 *
 */
public class Z2WSimpleDocument extends Z2WObject implements Identified {

	private String number;

	private String name;

	private Z2WContainer container;

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

	public Z2WContainer getContainer() {
		return container;
	}

	public void setContainer(Z2WContainer container) {
		this.container = container;
	}

	public String getDisplayIdentity() {
		return this.name + "-" + this.number;
	}

}
