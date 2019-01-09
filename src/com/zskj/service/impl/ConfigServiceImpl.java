/**
 * @项目名称: databak
 * @文件名称: ConfigServiceImpl.java
 * @日期: 2019年1月9日
 * @版权: 2019 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发交付中心
 */
package com.zskj.service.impl;

import java.util.Map;

import com.zskj.service.ConfigService;
import com.zskj.utils.SettingUtils;

/**
 * @author baihuayang
 *
 */
public class ConfigServiceImpl implements ConfigService {

	private SettingUtils settingUtils;

	/**
	 * @param settingUtils
	 */
	public ConfigServiceImpl() {
		this.settingUtils = SettingUtils.newInstance();
	}

	@Override
	public boolean save(Map<String, String> paramMap) {
		return settingUtils.saveSetting(paramMap);
	}

	@Override
	public String getValue(String key) {
		return settingUtils.getValue(key);
	}

}
