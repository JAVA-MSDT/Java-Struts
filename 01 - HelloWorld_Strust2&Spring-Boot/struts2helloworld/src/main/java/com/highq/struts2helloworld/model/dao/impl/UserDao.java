package com.highq.struts2helloworld.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.highq.struts2helloworld.model.config.DBConnection;
import com.highq.struts2helloworld.model.dao.api.GenericDao;
import com.highq.struts2helloworld.model.dao.map.impl.UserMapper;
import com.highq.struts2helloworld.model.domain.User;


public class UserDao implements GenericDao<User> {

	private Connection connection;

	private UserMapper userMapper = new UserMapper();

	private static final String INSERT_USER = "INSERT INTO users ( username, password ) VALUES (?, ?)";
	private static final String SELECT_ALL = "SELECT * FROM users";
	private static final String SELECT_BY_ID = "SELECT * FROM users WHERE id = ?";
	private static final String SELECT_BY_USERNAME = "SELECT * FROM users WHERE username = ?";
	private static final String UPDATE_USER = "UPDATE users SET username = ?, password = ?, "
			+ "enabled = ? WHERE id = ?";
	private static final String DELETE_BY_ID = "DELETE FROM users WHERE id = ?";

	public UserDao(final Connection connection) {
		this.connection = connection;
	}

	public int saveItem(final User item) throws SQLException {

		int insertedRecord = 0;

		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
		preparedStatement.setString(1, item.getUsername());
		preparedStatement.setString(2, item.getPassword());

		insertedRecord = preparedStatement.executeUpdate();
		DBConnection.closeStatement(preparedStatement);

		return insertedRecord;
	}

	public List<User> findAllItems() throws SQLException {

		List<User> users = new ArrayList<User>();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			User user = userMapper.rowMapper(resultSet);
			users.add(user);
		}

		DBConnection.closeResultset(resultSet);
		DBConnection.closeStatement(preparedStatement);

		return users;
	}

	public User findById(final long id) throws SQLException {

		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
		preparedStatement.setLong(1, id);

		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			return userMapper.rowMapper(resultSet);
		}

		DBConnection.closeResultset(resultSet);
		DBConnection.closeStatement(preparedStatement);

		return new User();
	}

	public int updateItem(final User item) throws SQLException {
		int updatedRecord = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
		preparedStatement.setString(1, item.getUsername());
		preparedStatement.setString(2, item.getPassword());
		preparedStatement.setLong(3, item.getId());

		updatedRecord =  preparedStatement.executeUpdate();
		DBConnection.closeStatement(preparedStatement);

		return updatedRecord;
	}

	public int deleteById(final long id) throws SQLException {
		int deletedRecord = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
		preparedStatement.setLong(1, id);

		deletedRecord =  preparedStatement.executeUpdate();
		DBConnection.closeStatement(preparedStatement);

		return deletedRecord;
	}
	
	public User findByusername(final String username) throws SQLException {

		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_USERNAME);
		preparedStatement.setString(1, username);

		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			return userMapper.rowMapper(resultSet);
		}

		DBConnection.closeResultset(resultSet);
		DBConnection.closeStatement(preparedStatement);

		return new User();
	}

}
