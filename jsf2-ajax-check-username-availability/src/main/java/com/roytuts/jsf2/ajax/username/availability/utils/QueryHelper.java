package com.roytuts.jsf2.ajax.username.availability.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryHelper {

	public boolean isUsernameAvailable(final String username) {
		final String sql = "SELECT * FROM user WHERE login_username='" + username.trim() + "' LIMIT 1";

		Connection connection = null;
		ResultSet resultSet = null;

		try {
			connection = DBHelper.getDBConnection();
			resultSet = DBHelper.getDBResultSet(connection, sql);
			if (resultSet != null) {
				if (resultSet.next()) {
					return false;
				} else {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					DBHelper.closeResultSet(resultSet);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					DBHelper.closeDBConnection(connection);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return false;
	}

}
