package com.message.app.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.message.app.SocketServer;
import com.message.app.common.MessageEnum;
import com.message.app.dto.LoginRequestDTO;
import com.message.app.dto.LoginResponseDTO;
import com.message.app.dto.SendMessageRequestDTO;
import com.message.app.dto.SocketDataDTO;
import com.message.app.service.business.UserManagementService;
import com.message.app.service.business.UserMessageManagementService;
import com.message.app.service.business.UserPasswordManagementService;
import com.message.app.service.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Service
public class ApiControllerHandlerService {

    @Autowired
    private UserManagementService userManagementService;

    @Autowired
    private ValidationHandlerService validationHandlerService;

    @Autowired
    private UserPasswordManagementService userPasswordManagementService;

    @Autowired
    private UserMessageManagementService userMessageManagementService;

    @Autowired
    private ObjectMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(ApiControllerHandlerService.class);

    /**
     * Authentication to the application
     * @param loginRequestDTO
     * @param session
     * @return LoginResponseDTO
     */
    @Transactional
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO, HttpSession session) {

         LoginResponseDTO loginResponseDTO = validationHandlerService.validateUserLoginRequest(loginRequestDTO);
         if (!loginResponseDTO.isStatus()){
             return loginResponseDTO;
         }

         //check mobile number exist
         boolean isValidUserName = userManagementService.checkUserExist(loginRequestDTO.getUserName());
         if (isValidUserName){

             User user = userManagementService.getUserByUserName(loginRequestDTO.getUserName());
             boolean isMatchPassword = userPasswordManagementService.checkPasswordMatch(loginRequestDTO.getUserName(), loginRequestDTO.getPassword());

             if (isMatchPassword){
                 //update the sessionId
                 user.setSessionId(session.getId());
                 userManagementService.updateUser(user);

                 loginResponseDTO.setStatus(true);
                 loginResponseDTO.setUserId(user.getId());
                 loginResponseDTO.setStatusMessage(MessageEnum.LOGIN_SUCCESS.message);
             }else {
                 loginResponseDTO.setStatus(false);
                 loginResponseDTO.setStatusMessage(MessageEnum.INVALID_PASSWORD.message);
             }

         }else {
             loginResponseDTO.setStatus(false);
             loginResponseDTO.setStatusMessage(MessageEnum.INVALID_USERNAME.message);
         }

        return loginResponseDTO;
    }

    public void sendMessage(SendMessageRequestDTO sendMessageRequestDTO) {

        String socketId = sendMessageRequestDTO.getSenderId();

        //store message in db
        userMessageManagementService.createMessage(sendMessageRequestDTO);

        User messageReceiver = userManagementService.getUserById(sendMessageRequestDTO.getSenderId());
        User messageSender = userManagementService.getUserById(sendMessageRequestDTO.getUserId());

        Map<String, String> peers = SocketServer.getSessionSocket();
        for (Map.Entry<String, String> entry : peers.entrySet()) {
            if(entry.getValue().equals(messageReceiver.getSessionId())){
                socketId = entry.getKey();
            }
        }

        SocketDataDTO socketDataDTO = new SocketDataDTO();
        socketDataDTO.setMessage(sendMessageRequestDTO.getMessage());
        socketDataDTO.setUserName(messageSender.getName());
        socketDataDTO.setUserId(sendMessageRequestDTO.getUserId());
        socketDataDTO.setSenderId(sendMessageRequestDTO.getSenderId());
        Set<Session> peerSessions = SocketServer.getAllSession();

        for (Session s : peerSessions) {
            if(s.getId().equals(socketId)){
                try {
                    s.getBasicRemote().sendText(mapper.writeValueAsString(socketDataDTO));
                } catch (IOException e) {
                    logger.error("Error while writing to the text : " + e.getMessage());
                }
            }
        }

    }
}
