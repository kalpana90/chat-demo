package com.message.app.service.business;

import com.message.app.dto.MessageRequestDTO;
import com.message.app.dto.SendMessageRequestDTO;
import com.message.app.service.domain.UserMessage;

import java.util.List;

public interface UserMessageManagementService {

    List<UserMessage> getMessagesBySenderAndUser(MessageRequestDTO messageRequestDTO);

    void createMessage(SendMessageRequestDTO sendMessageRequestDTO);
}
