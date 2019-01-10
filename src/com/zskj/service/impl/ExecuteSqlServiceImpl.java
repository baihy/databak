package com.zskj.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zskj.jobs.TaskJob;
import com.zskj.service.ExecuteSqlService;
import com.zskj.utils.DbUtils;
import com.zskj.utils.JobUtils;
import com.zskj.utils.PropertyUtils;

/**
 * @author baihuayang
 *
 */
public class ExecuteSqlServiceImpl implements ExecuteSqlService {

	private final static Logger LOGGER = LoggerFactory.getLogger(ExecuteSqlServiceImpl.class);

	/**
	 * 
	 */
	public ExecuteSqlServiceImpl() {
		super();
	}

	@Override
	public boolean executeSql() {
		boolean result = false;
		String sql = PropertyUtils.getValue("sql");
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbUtils.getConn();
			pstmt = DbUtils.getPstmt(conn, sql);
			LOGGER.info("SQL:" + sql);
			pstmt.executeUpdate();
			result = true;
		} catch (SQLException e) {
			LOGGER.info("执行Sql异常:" + e.getMessage());
		} finally {
			DbUtils.close(conn, pstmt, null);
		}
		return result;
	}

	@Override
	public boolean executeTask() {
		String cron = PropertyUtils.getValue("cron");
		if (StringUtils.isNotEmpty(cron)) {
			JobUtils.newInstance().startJob("jobName", "jobGroupName", "triggerName", "triggerGroupName", TaskJob.class, cron);
			return true;
		}
		return false;
	}

}
