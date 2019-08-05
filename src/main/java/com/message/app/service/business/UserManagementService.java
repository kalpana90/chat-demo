package com.message.app.service.business;

import com.message.app.dto.LoginRequestDTO;
import com.message.app.dto.LoginResponseDTO;
import com.message.app.dto.MessageRequestDTO;
import com.message.app.service.domain.User;
import com.message.app.service.domain.UserMessage;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserManagementService {

    List<User> getRecipientList(String userId);

    boolean checkUserExist(String userName);

    User getUserByUserName(String userName);

    void updateUser(User user);

    User getUserById(String senderId);
}
