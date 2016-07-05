package com.z2w.doc.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.z2w.container.model.Z2WContainer;
import com.z2w.doc.model.Z2WSimpleDocument;


public interface Z2WSimpleDocRepository extends PagingAndSortingRepository<Z2WSimpleDocument, Integer> {

	/**
	 * 获取容器里所有的简单文档对象
	 * @param container
	 * @return
	 */
	public List<Z2WSimpleDocument> getZ2WSimpleDocumentByContainerClassAndContainerId(String className, Integer containerId);
}
