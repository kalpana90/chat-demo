package com.message.app.service.dao.impl;

import com.message.app.service.domain.User;

import com.message.app.service.dao.UserDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	public User findByUserName(String username) {
		Criteria criteria = getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("mobileNumber", username));
		return (User) criteria.uniqueResult();
	}

	@Override
	public List<User> getAllUsers() {
		Criteria criteria = getCurrentSession().createCriteria(User.class);
		return (List<User>)criteria.list();
	}

	@Override
	public User findByUserId(String senderId) {
		Criteria criteria = getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("id", Long.valueOf(senderId)));
		return (User) criteria.uniqueResult();
	}
}
