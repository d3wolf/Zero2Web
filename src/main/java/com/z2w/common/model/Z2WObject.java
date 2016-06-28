package com.z2w.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 所有模型的超类
 */
@MappedSuperclass
public abstract class Z2WObject {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Version
	@Autowired
	private Integer updatecount;

	@Column(nullable = true, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTimestamp;

	@Column(nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date modefyTimestamp;

	@PrePersist
	void create(){
		this.createTimestamp = this.modefyTimestamp = new Date();
	}
	
	@PreUpdate
	void update(){
		this.modefyTimestamp = new Date();
	}
	
	public Date getCreateTimestamp() {
		return createTimestamp;
	}

	public Date getModefyTimestamp() {
		return modefyTimestamp;
	}

	public void setModefyTimestamp(Date modefyTimestamp) {
		this.modefyTimestamp = modefyTimestamp;
	}

	/**
	 * 返回oid(&ltClassName>:&ltid>, e.g. common.model.BaseObject:1)
	 */
	public String toString() {
		return this.getClass().getName() + ":" + id;
	}
}
