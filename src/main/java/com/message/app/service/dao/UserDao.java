package com.message.app.service.dao;

import com.message.app.service.domain.User;
import java.util.List;

public interface UserDao extends BaseDao<User> {

	long create(User user);

	void update(User user);

	User findByUserName(String username);

	List<User> getAllUsers();

	User findByUserId(String senderId);
}
