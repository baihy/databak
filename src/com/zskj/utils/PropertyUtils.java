/**
 * @项目名称: databak
 * @文件名称: PropertyUtils.java
 * @日期: 2019年1月10日
 * @版权: 2019 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发交付中心
 */
package com.zskj.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author baihuayang
 *
 */
public class PropertyUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyUtils.class);

	private static Properties prop = null;
	static {
		try {
			prop = new Properties();
			LOGGER.info("配置文件：" + ParameterConfigure.CONFIG);
			InputStream is = new FileInputStream(new File(ParameterConfigure.CONFIG));
			prop.load(is);
		} catch (IOException e) {
			LOGGER.error("加载配置文件异常：" + e.getMessage());
		}
	}

	public static String getValue(String key) {
		if (prop != null) {
			if (!prop.containsKey(key)) {
				LOGGER.error(key + ":参数不存在！");
				throw new IllegalArgumentException(key + ":参数不存在！");
			}
			return prop.getProperty(key);
		}
		return null;
	}

}
