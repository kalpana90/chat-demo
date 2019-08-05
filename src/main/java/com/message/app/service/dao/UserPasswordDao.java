package com.message.app.service.dao;

import com.message.app.service.domain.UserPassword;

public interface UserPasswordDao extends BaseDao<UserPassword> {

	UserPassword getPasswordByUserName(String userName);
}
