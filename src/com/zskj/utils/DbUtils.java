/**
 * @项目名称: databak
 * @文件名称: DbUtils.java
 * @日期: 2019年1月9日
 * @版权: 2019 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发交付中心
 */
package com.zskj.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author baihuayang
 *
 */
public class DbUtils {

	private static String url;
	private static String username;
	private static String password;
	private static String driver;

	static {
		driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		org.apache.commons.dbutils.DbUtils.loadDriver(driver);
		SettingUtils settingUtils = SettingUtils.newInstance();
		String ip = settingUtils.getValue("ip");
		url = "jdbc:sqlserver://localhost:1433;databaseName=howe";
		username = settingUtils.getValue("username");
		password = settingUtils.getValue("password");
	}

	public static Connection getConn() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
