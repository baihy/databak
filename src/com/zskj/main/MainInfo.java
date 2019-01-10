/**
 * @项目名称: databak
 * @文件名称: MainInfo.java
 * @日期: 2019年1月9日
 * @版权: 2019 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发交付中心
 */
package com.zskj.main;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zskj.service.ExecuteSqlService;
import com.zskj.service.impl.ExecuteSqlServiceImpl;
import com.zskj.utils.ParameterConfigure;

/**
 * @author baihuayang
 *
 */
public class MainInfo {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainInfo.class);

	private ExecuteSqlService executeSqlService;

	public MainInfo() {
		executeSqlService = new ExecuteSqlServiceImpl();
	}

	public static void main(String[] args) {
		MainInfo mainInfo = new MainInfo();
		Map<String, String> params = new HashMap<String, String>();
		for (int i = 0; i < args.length; i++) {
			String[] temp = args[i].split("=");
			if (temp.length != 2) {
				LOGGER.error("参数：【{}】格式错误", args[i]);
				throw new IllegalArgumentException("参数：【" + args[i] + "】格式错误,正确格式为： -参数名称=参数值");
			}
			params.put(temp[0].trim().toLowerCase(), temp[1].trim());
		}
		if (StringUtils.isEmpty(params.get("-configfile"))) {
			LOGGER.error("参数: -configfile 是必传参数！");
			throw new IllegalArgumentException("参数: -configfile 是必传参数！");
		}
		String configFile = params.get("-configfile");
		if (StringUtils.isNotEmpty(configFile)) {
			File config = new File(configFile);
			if (config.exists() && config.isFile()) {
				String suffix = configFile.substring(configFile.lastIndexOf(".") + 1);
				if (!"properties".equalsIgnoreCase(suffix)) {
					LOGGER.error("配置文件：" + configFile + "格式不正确！");
					throw new IllegalArgumentException("配置文件：" + configFile + "格式不正确！");
				} else {
					ParameterConfigure.CONFIG = config.getAbsolutePath();
				}
			} else {
				LOGGER.error("配置文件：" + configFile + "不存在！");
				throw new IllegalArgumentException("配置文件：" + configFile + "不存在！");
			}
		}
		boolean task = false;
		if (StringUtils.isNotEmpty(params.get("-task"))) {
			task = new Boolean(params.get("-task"));
		}
		boolean result = false;
		if (task) {
			result = mainInfo.executeTask();
			LOGGER.info("添加任务成功：" + result);
			try {
				System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			result = mainInfo.executeSql();
			LOGGER.info("执行完成，结果是：" + result);
		}
	}

	private boolean executeSql() {
		return executeSqlService.executeSql();
	}

	private boolean executeTask() {
		return executeSqlService.executeTask();
	}
}
