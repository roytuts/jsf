package com.roytuts.jsf2.valuchangelistener;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryHelper {

	public List<Category> getAllCategories() {
		Connection connection = null;

		List<Category> categories = new ArrayList<>();

		Category emptyCat = new Category();
		emptyCat.setId(0);
		emptyCat.setName("-- Select Category --");

		categories.add(emptyCat);

		try {
			connection = DBHelper.getDBConnection();
			if (connection != null) {
				String sql = "SELECT category_id, category_name FROM category WHERE parent_id=0";
				ResultSet resultSet = DBHelper.getDBResultSet(connection, sql);

				if (resultSet != null) {
					while (resultSet.next()) {
						Category category = new Category();
						category.setId(resultSet.getInt(1));
						category.setName(resultSet.getString(2));
						categories.add(category);
					}
					return categories;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					DBHelper.closeDBConnection(connection);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public List<Category> getAllSubCategories(Integer category_id) {
		Connection connection = null;
		List<Category> categories = new ArrayList<>();

		Category emptyCat = new Category();
		emptyCat.setId(0);
		emptyCat.setName("-- Select Sub-category --");

		try {
			connection = DBHelper.getDBConnection();
			if (connection != null) {
				String sql = "SELECT category_id, category_name FROM category WHERE parent_id=" + category_id;
				ResultSet resultSet = DBHelper.getDBResultSet(connection, sql);
				if (resultSet != null) {
					categories.add(emptyCat);
					while (resultSet.next()) {
						Category category = new Category();
						category.setId(resultSet.getInt(1));
						category.setName(resultSet.getString(2));
						categories.add(category);
					}
					return categories;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					DBHelper.closeDBConnection(connection);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public Integer getNoOfTutorials(Integer category_id) {
		Connection connection = null;
		try {
			connection = DBHelper.getDBConnection();
			if (connection != null) {
				String sql = "SELECT no_of_tutorials FROM tutorial WHERE category_id=" + category_id;
				ResultSet resultSet = DBHelper.getDBResultSet(connection, sql);
				if (resultSet != null) {
					if (resultSet.next()) {
						Integer no = resultSet.getInt(1);
						return no;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					DBHelper.closeDBConnection(connection);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
