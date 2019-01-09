/**
 * @项目名称: databak
 * @文件名称: ExecuteSqlServiceImpl.java
 * @日期: 2019年1月9日
 * @版权: 2019 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发交付中心
 */
package com.zskj.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.zskj.jobs.TaskJob;
import com.zskj.service.ExecuteSqlService;
import com.zskj.utils.DbUtils;
import com.zskj.utils.JobUtils;
import com.zskj.utils.SettingUtils;

/**
 * @author baihuayang
 *
 */
public class ExecuteSqlServiceImpl implements ExecuteSqlService {

	private QueryRunner runner;

	/**
	 * 
	 */
	public ExecuteSqlServiceImpl() {
		super();
		runner = new QueryRunner();
	}

	@Override
	public boolean executeSql() {
		boolean result = false;
		String sql = SettingUtils.newInstance().getValue("sql");
		try (Connection conn = DbUtils.getConn()) {
			runner.update(conn, sql);
			result = true;
		} catch (SQLException e) {
			result = false;
		}
		return result;
	}

	@Override
	public void executeTask() {
		String cron = SettingUtils.newInstance().getValue("cron");
		JobUtils.newInstance().startJob("jobName", "jobGroupName", "triggerName", "triggerGroupName", TaskJob.class, cron);
	}

}
