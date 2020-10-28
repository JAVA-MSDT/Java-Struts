package com.highq.struts2helloworld.intercept;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.highq.struts2helloworld.model.config.DBConnection;
import com.highq.struts2helloworld.model.dao.impl.UserDao;
import com.highq.struts2helloworld.model.domain.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.util.ValueStack;

public class LoginInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8557057253691381831L;
	private UserDao userDao;

	@Override
	public void init() {
		try {
			Connection connection = DBConnection.getConnection();
			userDao = new UserDao(connection);
			createTable(connection);
			addusers(userDao);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		ValueStack stack = invocation.getStack();
		String username = stack.findString("username");
		String password = stack.findString("password");

		User user = userDao.findByusername(username);

		if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
			return invocation.invoke();
		}

		return Action.LOGIN;
	}

	@Override
	public void destroy() {
	}

	private void createTable(Connection connection) {
		String createUserTable = "CREATE TABLE users ( " + "id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, "
				+ "username VARCHAR(255), " + "password VARCHAR(255), " + "enabled BOOLEAN DEFAULT true )";
		Statement statement;
		try {
			System.out.println("Creating User table.... ");
			statement = connection.createStatement();
			statement.execute(createUserTable);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void addusers(UserDao userDao) {

		User userOne = new User("admin", "admin");
		User userTwo = new User("user", "user");

		try {
			System.out.println("Adding Users.... ");
			userDao.saveItem(userOne);
			userDao.saveItem(userTwo);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
