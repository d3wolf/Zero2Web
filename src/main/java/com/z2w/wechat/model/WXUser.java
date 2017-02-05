package com.z2w.wechat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.z2w.common.model.Z2WObject;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class WXUser extends Z2WObject {

	@Column
	private String name;
	
	public static WXUser newWXUser(String name){
		WXUser user = new WXUser();
		user.setName(name);
		return user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
