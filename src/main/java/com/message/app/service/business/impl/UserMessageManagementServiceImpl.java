package com.message.app.service.business.impl;

import com.message.app.dto.MessageRequestDTO;
import com.message.app.dto.SendMessageRequestDTO;
import com.message.app.service.business.UserMessageManagementService;
import com.message.app.service.dao.UserMessageDao;
import com.message.app.service.domain.User;
import com.message.app.service.domain.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMessageManagementServiceImpl implements UserMessageManagementService {

    @Autowired
    private UserMessageDao userMessageDao;


    @Override
    public List<UserMessage> getMessagesBySenderAndUser(MessageRequestDTO messageRequestDTO) {

        List<UserMessage> userMessageList = userMessageDao.getMessagesByUserAndSender(messageRequestDTO);
        return userMessageList;
    }

    @Override
    public void createMessage(SendMessageRequestDTO sendMessageRequestDTO) {
        UserMessage userMessage = new UserMessage();

        User user = new User();
        user.setId(Long.valueOf(sendMessageRequestDTO.getSenderId()));

        User sender = new User();
        sender.setId(Long.valueOf(sendMessageRequestDTO.getUserId()));

        userMessage.setUser(user);
        userMessage.setSender(sender);
        userMessage.setMessage(sendMessageRequestDTO.getMessage());

        userMessageDao.create(userMessage);
    }
}
