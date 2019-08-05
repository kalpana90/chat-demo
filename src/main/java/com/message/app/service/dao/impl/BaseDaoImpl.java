package com.message.app.service.dao.impl;

import com.message.app.service.dao.BaseDao;
import org.hibernate.Criteria;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	@PersistenceContext
	private EntityManager entityManager;

	public BaseDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
	}

	public long create(T model) {
		return (long)getCurrentSession().save(model);
	}

	public void update(T model) {
		getCurrentSession().update(model);
	}

	/**
	 * Gets the current session.
	 *
	 * @return the current session
	 */
	public Session getCurrentSession() {
		return entityManager.unwrap(org.hibernate.Session.class);
	}


	/**
	 * Use to set pagination related data in to cri
	 * @param criteria
	 * @param isPageable
	 * @param pageStart
	 * @param pageSize
	 */
	protected void handlePagination(final Criteria criteria, final boolean isPageable, final int pageStart, final int
			pageSize) {
		if (isPageable) {
			criteria.setFirstResult(pageStart);
			if (pageSize != 0) {
				criteria.setMaxResults(pageSize);
			}
		}
	}

}
