package com.message.app.service.dao.impl;

import com.message.app.service.dao.UserPasswordDao;
import com.message.app.service.domain.UserPassword;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository
public class UserPasswordDaoImpl extends BaseDaoImpl<UserPassword> implements UserPasswordDao {


	@Override
	public UserPassword getPasswordByUserName(String userName) {

		Criteria criteria = getCurrentSession().createCriteria(UserPassword.class, "up");
		criteria.createAlias("up.user", "user");
		criteria.add(Restrictions.eq("user.mobileNumber", userName));
		return (UserPassword) criteria.uniqueResult();
	}
}
