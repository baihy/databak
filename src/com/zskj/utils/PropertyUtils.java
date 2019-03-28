/**
 * @项目名称: databak
 * @文件名称: PropertyUtils.java
 * @日期: 2019年1月10日
 * @版权: 2019 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发交付中心
 */
package com.zskj.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author baihuayang
 *
 */
public class PropertyUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyUtils.class);
	private static Map<String, String> propMap = new HashMap<String, String>();
	static {
		try {
			Properties prop = new Properties();
			LOGGER.info("配置文件：" + ParameterConfigure.CONFIG);
			InputStream is = new FileInputStream(ParameterConfigure.CONFIG);
			prop.load(new InputStreamReader(is));
			Set<Entry<Object, Object>> entrySet = prop.entrySet();
			for (Iterator<Entry<Object, Object>> it = entrySet.iterator(); it.hasNext();) {
				Entry<Object, Object> entry = it.next();
				String key = entry.getKey().toString();
				String value = entry.getValue().toString();
				if (65279 == ((int) key.charAt(0))) {
					key = key.substring(1);
				}
				propMap.put(key, value);
			}
		} catch (IOException e) {
			LOGGER.error("加载配置文件异常：" + e.getMessage());
		}
	}

	public static String getValue(String key) {
		if (propMap != null) {
			return propMap.get(key);
		}
		return null;
	}
}
