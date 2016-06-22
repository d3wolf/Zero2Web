package com.z2w.action.model;

/**
 * �����洢action ��action model��bean
 * 
 * @author Wolf
 *
 */
public class Z2WActionBean {

	private String name;

	private Boolean model;// �Ƿ���model

	private String type;// action����

	private String url;// ���action��Ӧ��url,�����model��Ϊ��
	
	private String fileName;//���ڵ��ļ���
	
	private String resource;//resource�ļ�
	

	/**
	 * �½�һ��action model����
	 * @param name
	 * @return
	 */
	public static Z2WActionBean newZ2WActionModel(String name) {

		Z2WActionBean z2wModelBean = new Z2WActionBean();
		z2wModelBean.setName(name);
		z2wModelBean.setModel(true);

		return z2wModelBean;
	}
	
	/**
	 * �½�һ��action����
	 * @param name
	 * @param type
	 * @param url
	 * @return
	 */
	public static Z2WActionBean newZ2WAction(String name, String type, String url) {

		Z2WActionBean z2wActionBean = new Z2WActionBean();
		z2wActionBean.setName(name);
		z2wActionBean.setModel(false);
		z2wActionBean.setType(type);
		z2wActionBean.setUrl(url);

		return z2wActionBean;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean isModel() {
		return model;
	}

	public void setModel(Boolean model) {
		this.model = model;
	}

	public String toString() {
		String toStr = "";
		
		if(model){
			toStr = Z2WActionBean.class.getName() + "-model:[" + name + "]";
		}else{
			toStr = Z2WActionBean.class.getName() + "-action:[" + type + "].[" + name + "], url:[" + url + "]";
		}
		return toStr;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

}
