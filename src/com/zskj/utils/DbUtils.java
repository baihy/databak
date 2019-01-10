package com.zskj.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author baihuayang
 *
 */
public class DbUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobUtils.class);

	private static String url;
	private static String username;
	private static String password;
	private static String driver;

	static {
		driver = "sun.jdbc.odbc.JdbcOdbcDriver";
		String odbcName = PropertyUtils.getValue("odbcName");
		url = "jdbc:odbc:" + odbcName;
		username = PropertyUtils.getValue("username");
		password = PropertyUtils.getValue("password");
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			LOGGER.error("注册驱动失败:" + e.getMessage());
		}
	}

	/**
	 * 获取连接
	 *
	 * @return
	 */
	public static Connection getConn() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			LOGGER.error("获取连接失败:" + e.getMessage());
		}
		return conn;
	}

	/**
	 * 初始化运输工具
	 *
	 * @param conn
	 * @param sql
	 * @return
	 */
	public static PreparedStatement getPstmt(Connection conn, String sql) {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			LOGGER.error("创建运输工具失败:" + e.getMessage());
		}
		return pstmt;
	}

	public static boolean getRs(PreparedStatement pstmt) {
		boolean result = false;
		try {
			result = pstmt.execute();
		} catch (SQLException e) {
			LOGGER.error("获取返回结果:" + e.getMessage());
		}
		return result;
	}

	/**
	 * 关闭连接
	 *
	 * @param conn
	 * @param pstmt
	 * @param rs
	 */
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {

		if (conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				LOGGER.error("关闭连接失败:" + e.getMessage());
			}
		}

		if (pstmt != null) {
			try {
				pstmt.close();
				pstmt = null;
			} catch (SQLException e) {
				LOGGER.error("关闭运输工具失败:" + e.getMessage());
			}
		}
		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				LOGGER.error("关闭返回结果失败:" + e.getMessage());
			}
		}

	}
}
