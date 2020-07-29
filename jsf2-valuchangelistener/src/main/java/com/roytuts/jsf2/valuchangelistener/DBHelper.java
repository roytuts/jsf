package com.roytuts.jsf2.valuchangelistener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class DBHelper {

	private DBHelper() {
	}

	/**
	 * to load the database driver
	 *
	 * @return a database connection
	 * @throws SQLException throws an exception if an error occurs
	 */
	public static Connection getDBConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName(Constants.DB_DRIVER_CLASS);
			conn = DriverManager.getConnection(Constants.DB_CONNECTION_URL + "/" + Constants.DB_INSTANCE_NAME,
					Constants.DB_USER_NAME, Constants.DB_USER_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * to get a result set of a query
	 *
	 * @param query custom query
	 * @return a result set of custom query
	 * @throws SQLException throws an exception if an error occurs
	 */
	public static ResultSet getDBResultSet(Connection conn, String query) throws SQLException {
		ResultSet rs = null;
		if (null != conn) {
			PreparedStatement st = conn.prepareStatement(query);
			rs = st.executeQuery();
		}
		return rs;
	}

	/**
	 * to run an update query such as update, delete
	 *
	 * @param query custom query
	 * @throws SQLException throws an exception if an error occurs
	 */
	public static void runQuery(Connection conn, String query) throws SQLException {
		if (null != conn) {
			PreparedStatement st = conn.prepareStatement(query);
			st.executeUpdate();
		} else {
			System.out.println("Query execution failed!");
		}
	}

	/**
	 * close an opened PreparedStatement
	 *
	 * @return a void
	 * @throws SQLException throws an exception if an error occurs
	 */
	public static void closePreparedStatement(PreparedStatement ps) throws SQLException {
		if (null != ps) {
			ps.close();
		}
	}

	/**
	 * close an opened ResultSet
	 *
	 * @return a void
	 * @throws SQLException throws an exception if an error occurs
	 */
	public static void closeResultSet(ResultSet rs) throws SQLException {
		if (null != rs) {
			rs.close();
		}
	}

	/**
	 * close an opened database connection
	 *
	 * @return a void
	 * @throws SQLException throws an exception if an error occurs
	 */
	public static void closeDBConnection(Connection conn) throws SQLException {
		if (null != conn) {
			conn.close();
		}
	}

}
