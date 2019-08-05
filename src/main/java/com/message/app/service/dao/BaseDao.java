package com.message.app.service.dao;

public interface BaseDao<T> {

	/**
	 * Creates the.
	 *
	 * @param type the type
	 */
	long create(T type);

	/**
	 * Update.
	 *
	 * @param type the type
	 */
	void update(T type);

}
