/**
 * @项目名称: databak
 * @文件名称: SettingUtils.java
 * @日期: 2019年1月9日
 * @版权: 2019 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发交付中心
 */
package com.zskj.utils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jface.preference.PreferenceStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author baihuayang
 *
 */
public class SettingUtils {

	private final static Logger LOGGER = LoggerFactory.getLogger(SettingUtils.class);

	private static String SETTING_FILE = ".data_bak.config";

	private static PreferenceStore pStore;

	private static class SingletonInstance {
		private static final SettingUtils SETTING_UTILS = new SettingUtils();
	}

	private SettingUtils() {
		String configFile = System.getProperty("user.home");
		pStore = new PreferenceStore(configFile + File.separator + SETTING_FILE);
	}

	/**
	 * 获取settingUtils对象
	 * 
	 * @return
	 */
	public static SettingUtils newInstance() {
		return SingletonInstance.SETTING_UTILS;
	}

	/**
	 * 保存设置
	 * 
	 * @param map
	 */
	public boolean saveSetting(Map<String, String> map) {
		boolean result = true;
		try {
			if (map != null && map.size() > 0) {
				for (Iterator<Entry<String, String>> it = map.entrySet().iterator(); it.hasNext();) {
					Entry<String, String> entry = it.next();
					pStore.putValue(entry.getKey(), entry.getValue());
				}
				pStore.save();
			}
		} catch (IOException e) {
			LOGGER.error("保存配置信息异常:" + e.getMessage());
			result = false;
		}
		return result;
	}

	/**
	 * 通过key获取数据
	 * 
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		if (key == null || key == "") {
			throw new IllegalArgumentException("参数key不能为空！！！");
		}
		try {
			pStore.load();
		} catch (IOException e) {
			try {
				pStore.save();
			} catch (IOException e1) {
				LOGGER.error("保存配置信息异常:" + e.getMessage());
			}
		}
		return pStore.getString(key);
	}

}
