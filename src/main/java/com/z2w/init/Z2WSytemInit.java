package com.z2w.init;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.z2w.action.Z2WActionCache;
import com.z2w.action.service.Z2WActionService;
import com.z2w.common.exception.Z2WException;

/**
 * 系统启动时执行的类
 */
@Component
public class Z2WSytemInit implements ServletContextAware {

	private static final Logger logger = Logger.getLogger(Z2WSytemInit.class.getName());
	
	public static final String ACTION_CONFIG_FOLDER = "/config/actions/";//action文件位置
	
	@Autowired
	private Z2WActionService z2WActionService;

	/**
	 * 系统启动时执行的方法
	 */
	public void setServletContext(ServletContext sc) {

		String root = sc.getRealPath("/");
		
		logger.info("system root path: " + root);
		
		System.setProperty("webapp.root", root);
		
		reloadAction(sc);
	}
	
	/**
	 * reload actions
	 * @param sc
	 */
	public void reloadAction(ServletContext sc){
		String path = sc.getRealPath(ACTION_CONFIG_FOLDER);
		
		logger.info("action folder:" + path);
		
		File actionFolder = new File(path);
		
		try {
			z2WActionService.init(actionFolder);
		} catch (Z2WException e) {
			e.printStackTrace();
		}
		
		logger.info(Z2WActionCache.modelActionMap);
	}

}
