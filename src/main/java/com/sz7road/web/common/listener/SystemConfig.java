package com.sz7road.web.common.listener;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

/**
 * 
 * @author hai.yuan
 *
 */
public class SystemConfig {

	private static Properties properties = new Properties();
	
	private Logger logger = Logger.getLogger(SystemConfig.class);
	
	public SystemConfig(ServletContext context){
		try {
			InputStream fileStream = SystemConfig.class.getClassLoader().getResourceAsStream("applicationConfig.properties");
			properties.load(fileStream);
			if(fileStream != null){
				fileStream.close();
			}
		} catch (Exception e) {
			logger.error("载入配置文件错误:" + e.getMessage());
		}
	}
	
	public static String getProperty(String key){
		return properties.getProperty(key);
	}
}
