/**
 * @项目名称: databak
 * @文件名称: ConfigService.java
 * @日期: 2019年1月9日
 * @版权: 2019 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发交付中心
 */
package com.zskj.service;

import java.util.Map;

/**
 * @author baihuayang
 *
 */
public interface ConfigService {

	boolean save(Map<String, String> paramMap);

	String getValue(String key);
}
