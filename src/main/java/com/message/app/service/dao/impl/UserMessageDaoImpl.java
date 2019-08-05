package com.message.app.service.dao.impl;

import com.message.app.service.domain.UserMessage;
import com.message.app.dto.MessageRequestDTO;
import com.message.app.service.dao.UserMessageDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserMessageDaoImpl extends BaseDaoImpl<UserMessage> implements UserMessageDao {

	@Override
	public List<UserMessage> getMessagesByUserAndSender(MessageRequestDTO messageRequestDTO) {
		List<Long> ids = new ArrayList<>();
		ids.add(Long.valueOf(messageRequestDTO.getSenderId()));
		ids.add(Long.valueOf(messageRequestDTO.getUserId()));
		Criteria criteria = getCurrentSession().createCriteria(UserMessage.class, "um");
		criteria.createAlias("um.user", "user");
		criteria.createAlias("um.sender", "sender");
		criteria.add(Restrictions.in("user.id", ids));
		criteria.add(Restrictions.in("sender.id", ids));
		criteria.addOrder(Order.asc("um.createdDate"));

		handlePagination(criteria,messageRequestDTO.isPageable(),messageRequestDTO.getPageStart(),messageRequestDTO.getPageSize());

		return (List<UserMessage>) criteria.list();
	}
}
