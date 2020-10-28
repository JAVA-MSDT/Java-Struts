package com.highq.struts2helloworld.model.dao.api;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> {

	int saveItem(T item) throws SQLException;

	List<T> findAllItems() throws SQLException;

	T findById(long id) throws SQLException;

	int updateItem(T item) throws SQLException;

	int deleteById(long id) throws SQLException;
}
