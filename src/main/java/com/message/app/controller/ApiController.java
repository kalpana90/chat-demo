package com.message.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.message.app.handler.ApiControllerHandlerService;
import com.message.app.service.business.UserManagementService;
import com.message.app.service.business.UserMessageManagementService;
import com.message.app.service.domain.User;
import com.message.app.service.domain.UserMessage;
import com.message.app.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@PropertySource(value = "classpath:connection.properties")
public class ApiController {

    @Autowired
    UserManagementService userManagementService;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ApiControllerHandlerService apiControllerHandlerService;

    @Autowired
    private UserMessageManagementService userMessageManagementService;

    @Value("${ws.socket.url}")
    private String socketUrl;

    private static final String MODEL_USER_ID = "userId";
    private static final String REDIRECT_DASHBOARD = "redirect:/dashboard";
    private static final String MODEL_ERROR_STATUS = "error";
    private static final String MODEL_ERROR_MESSAGE = "errorMessage";
    private static final String LOGIN = "login";
    private static final String MODEL_RECIPIENT_LIST = "users";
    private static final String MODEL_SESSION_ID = "sessionId";
    private static final String MODEL_SOCKET_URL = "socketUrl";
    private static final String DASHBOARD = "dashboard";
    private static final int PAGE_SIZE = 1000;
    private static final int PAGE_START = 0;
    private static final String MODEL_USER_MESSAGES = "userMessages";
    private static final String MODEL_SENDER_ID = "senderId";
    private static final String MODEL_USER_NAME = "userName";
    private static final String CHAT_WINDOW = "chat-window";
    private static final String REDIRECT_MESSAGES = "redirect:/messages";


    /**
     * Authentication of User
     *
     * @param loginRequestDTO
     * @param model
     * @param session
     * @param attributes
     *
     * @return on success Dashboard with other users list
     * on failure back to Login with error message
     */
    @RequestMapping("user/login")
    public String login(@ModelAttribute("user") LoginRequestDTO loginRequestDTO, Map<String, Object> model, HttpSession
            session, RedirectAttributes attributes) {

        LoginResponseDTO loginResponseDTO = apiControllerHandlerService.login(loginRequestDTO, session);

        if(loginResponseDTO.isStatus()){
            attributes.addFlashAttribute(MODEL_USER_ID,loginResponseDTO.getUserId());
            return REDIRECT_DASHBOARD;
        } else {
            model.put(MODEL_ERROR_STATUS,true);
            model.put(MODEL_ERROR_MESSAGE,loginResponseDTO.getStatusMessage());
            return LOGIN;
        }
    }

    /**
     * Dashboard with other uers list
     *
     * @param userId
     * @param model
     * @param session
     *
     * @return to dashboard with users list
     */
    @RequestMapping("/dashboard")
    public String dashboard( @ModelAttribute("userId") String userId, Map<String, Object> model, HttpSession session) {

        List<User> userList = userManagementService.getRecipientList(userId);

        model.put(MODEL_RECIPIENT_LIST,userList);
        model.put(MODEL_SESSION_ID, session.getId());
        model.put(MODEL_SOCKET_URL, socketUrl);

        return DASHBOARD;
    }

    /**
     * Messages History
     *
     * @param messageRequestDTO
     * @param model
     * @param session
     *
     * @return to ChatWindow with the message history
     * of the user with a selected other user
     */
    @RequestMapping("/messages")
    public String getMessages(@ModelAttribute("messageRequestDTO") MessageRequestDTO messageRequestDTO, Map<String, Object> model, HttpSession session) {

        //TODO should remove these hardcoded pagination details and should taken from frontend
        messageRequestDTO.setPageable(true);
        messageRequestDTO.setPageSize(PAGE_SIZE);
        messageRequestDTO.setPageStart(PAGE_START);

        List<UserMessage> userMessageList = userMessageManagementService.getMessagesBySenderAndUser(messageRequestDTO);
        User user = userManagementService.getUserById(messageRequestDTO.getSenderId());

        model.put(MODEL_USER_MESSAGES,userMessageList);
        model.put(MODEL_USER_ID, messageRequestDTO.getUserId());
        model.put(MODEL_SENDER_ID, messageRequestDTO.getSenderId());
        model.put(MODEL_USER_NAME, user.getName());
        model.put(MODEL_SESSION_ID, session.getId());
        model.put(MODEL_SOCKET_URL, socketUrl);
        return CHAT_WINDOW;
    }

    /**
     * Send Message
     *
     * @param sendMessageRequestDTO
     * @param attributes
     *
     * @return to ChatWindow after sending the message to
     * the selected specific user
     */
    @RequestMapping("/send/message")
    public String sendMessage(@ModelAttribute("sendMessageRequestDTO") SendMessageRequestDTO sendMessageRequestDTO, RedirectAttributes attributes) {

        apiControllerHandlerService.sendMessage(sendMessageRequestDTO);

        MessageRequestDTO messageRequestDTO = new MessageRequestDTO();
        messageRequestDTO.setUserId(sendMessageRequestDTO.getUserId());
        messageRequestDTO.setSenderId(sendMessageRequestDTO.getSenderId());

        attributes.addFlashAttribute(messageRequestDTO);

        return REDIRECT_MESSAGES;
    }

}
