package com.highq.struts2helloworld.model.dao.map.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.highq.struts2helloworld.model.dao.map.GenericMapper;
import com.highq.struts2helloworld.model.domain.User;


public class UserMapper implements GenericMapper<User>{

	public User rowMapper(final ResultSet resultSet) throws SQLException {

		int id = resultSet.getInt("id");
		String username = resultSet.getString("username");
		String password = resultSet.getString("password");

		return new User(id, username, password);
	}

}
