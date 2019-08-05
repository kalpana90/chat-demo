package com.message.app.service.dao;

import com.message.app.service.domain.UserMessage;
import com.message.app.dto.MessageRequestDTO;

import java.util.List;

/**
 * The Interface UserDao.
 */
public interface UserMessageDao extends BaseDao<UserMessage> {

	List<UserMessage> getMessagesByUserAndSender(MessageRequestDTO messageRequestDTO);

	long create(UserMessage userMessage);

}
