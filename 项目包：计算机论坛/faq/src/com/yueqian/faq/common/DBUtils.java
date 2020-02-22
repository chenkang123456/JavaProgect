package com.yueqian.faq.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;

public class DBUtils {
	private static BasicDataSource ds;
	private static ThreadLocal<Connection> tl = new ThreadLocal<>();
	static {
		
		 ds = new BasicDataSource();
		 ds.setDriverClassName("com.mysql.jdbc.Driver");
		 ds.setUrl("jdbc:mysql:///faq?useUnicode=true&characterEncoding=utf8&useSSL=false"); 
		 ds.setUsername("root"); 
		 ds.setPassword("1234");
		 ds.setMaxActive(20); ds.setMinIdle(1); ds.setMaxIdle(4);
		 ds.setMaxWait(4000); ds.setDefaultAutoCommit(false);
	}

	/**
	 * 锟斤拷取锟斤拷锟捷匡拷锟斤拷锟斤拷
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		// 锟斤拷锟饺达拷ThreadLocal锟斤拷锟斤拷锟叫伙拷取锟斤拷前锟竭程绑定碉拷锟斤拷锟接讹拷锟斤拷
		Connection conn = tl.get();
		if (conn == null) {
			try {
				conn = ds.getConnection();
				tl.set(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	public static void close(ResultSet rs, Statement stmt) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeDataSource() {
		System.out.println("鍏抽棴鏁版嵁婧�.");
		/*
		 * if (ds != null) { try { ds.close(); } catch (SQLException e) {
		 * e.printStackTrace(); } }
		 */
	}

	public static void commit() {
		Connection conn = tl.get();
		if (conn != null) {
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			tl.remove();
		}
	}

	public static void rollback() {
		Connection conn = tl.get();
		if (conn != null) {
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			tl.remove();
		}
	}
}
