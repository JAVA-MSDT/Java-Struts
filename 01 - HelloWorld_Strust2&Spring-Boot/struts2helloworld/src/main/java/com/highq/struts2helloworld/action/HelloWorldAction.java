package com.highq.struts2helloworld.action;

import java.sql.Connection;

import com.highq.struts2helloworld.model.config.DBConnection;
import com.highq.struts2helloworld.model.domain.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class HelloWorldAction implements Action, ModelDriven<User> {

	private User user = new User();

	@Override
	public String execute() throws Exception {
		System.out.println(" Hello World Action Running! ");
		Connection connection = DBConnection.getConnection();
		
		System.out.println("Connections,,,," + connection.getCatalog());
		System.out.println("UserName: " + getModel().getUsername());
		
		return Action.SUCCESS;
	}

	@Override
	public User getModel() {
		return user;
	}

}
