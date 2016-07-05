package com.z2w.doc.service;

import java.util.List;

import com.z2w.container.model.Z2WContainer;
import com.z2w.doc.model.Z2WSimpleDocument;

public interface Z2WSimpleDocService {

	/**
	 * 获取容器里的简单文档对象
	 * @param org
	 * @return
	 */
	public List<Z2WSimpleDocument> getDocByContainer(Z2WContainer container);
	
	public void createDoc(Z2WSimpleDocument simpleDoc);
}
