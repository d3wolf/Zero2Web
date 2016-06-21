package com.z2w.action.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Repository;

import com.z2w.action.dao.Z2WActionDAO;
import com.z2w.action.model.Z2WActionBean;
import com.z2w.common.exception.Z2WException;

@Repository("Z2WActionDAOImpl")
public class Z2WActionDAOImpl implements Z2WActionDAO {

	/* (non-Javadoc)
	 * @see com.z2w.action.dao.impl.Z2WActionDAO#getActionBeansByFile(java.io.File)
	 */
	public List<Z2WActionBean> getActionBeansByFile(File actionFile) throws Z2WException{
		List<Z2WActionBean> beans = new ArrayList<Z2WActionBean>();
		
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(actionFile);
			
			Element root = doc.getRootElement();

			@SuppressWarnings("unchecked")
			List<Element> objTypeNodes = root.elements();
			
			for(Element objTypeNode : objTypeNodes){
				String objName = objTypeNode.attributeValue("name");
				
				@SuppressWarnings("unchecked")
				List<Element> actionNodes = objTypeNode.elements();
				
				for(Element actionNode : actionNodes){
					
					String actionName = actionNode.attributeValue("name");
					
					String url = null;
					Element commandEle = actionNode.element("command");
					if(commandEle != null){
						url = commandEle.attributeValue("url");
					}
					
					Z2WActionBean bean = Z2WActionBean.newZ2WAction(actionName, objName, url);
					bean.setFileName(actionFile.getPath());
					
					beans.add(bean);
				}
			}
			
			
		} catch (DocumentException e) {
			throw new Z2WException(e);
		}
		
		return beans;
	}
	
	/* (non-Javadoc)
	 * @see com.z2w.action.dao.impl.Z2WActionDAO#getActionModelByFile(java.io.File, java.util.List)
	 */
	public Map<String, List<Z2WActionBean>> getActionModelByFile(File actionModelFile, List<Z2WActionBean> actions) throws Z2WException{
		
		Map<String, List<Z2WActionBean>> modelActionMap = new HashMap<String, List<Z2WActionBean>>();
		
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(actionModelFile);
			
			Element root = doc.getRootElement();

			@SuppressWarnings("unchecked")
			List<Element> modeNodes = root.elements();
			
			for(Element modelNode : modeNodes){
				String modelName = modelNode.attributeValue("name");
				List<Z2WActionBean> beans = new ArrayList<Z2WActionBean>();
				
				@SuppressWarnings("unchecked")
				List<Element> actionNodes = modelNode.elements();
				
				for(Element actionNode : actionNodes){
					
					String nodeName = actionNode.getName();
					
					if("action".equals(nodeName)){
						String actionName = actionNode.attributeValue("name");
						String actionType = actionNode.attributeValue("type");
						Z2WActionBean action = findActionByNameAndType(actions, actionName, actionType);
						
						beans.add(action);
					}else if("submodel".equals(nodeName)){
						String submodelName = actionNode.attributeValue("name");
						
						if(modelActionMap.get(submodelName) == null){
							modelActionMap.put(submodelName, new ArrayList<Z2WActionBean>());
						}
						
						beans.add(Z2WActionBean.newZ2WActionModel(submodelName));
					}
				}
				
				
				modelActionMap.put(modelName, beans);
			
			}
			
			
		} catch (DocumentException e) {
			throw new Z2WException(e);
		}
		
		return modelActionMap;
	}
	
	/* (non-Javadoc)
	 * @see com.z2w.action.dao.impl.Z2WActionDAO#findActionByNameAndType(java.util.List, java.lang.String, java.lang.String)
	 */
	public Z2WActionBean findActionByNameAndType(List<Z2WActionBean> actions, String name, String type){

		for(Z2WActionBean action : actions){
			if(name != null && name.equals(action.getName())){
				if(type != null && type.equals(action.getType())){
					return action;
				}else if(type == null){
					return action;
				}
			}else if(name == null && type != null && type.equals(action.getType())){
				return action;
			}
		}
		
		return null;
	}
	
	public static void main(String[] args) throws Z2WException {
		List<Z2WActionBean> actions = new Z2WActionDAOImpl().getActionBeansByFile(new File("G:\\workspace\\workspace_mvc\\Zero2Web\\src\\main\\webapp\\config\\actions\\navigator-actions.xml"));
		Map<String, List<Z2WActionBean>> models = new Z2WActionDAOImpl().getActionModelByFile(new File("G:\\workspace\\workspace_mvc\\Zero2Web\\src\\main\\webapp\\config\\actions\\navigator-actionModels.xml"), actions);
			System.out.println(models);
	}
}
