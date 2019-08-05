package com.message.app.handler;

import com.message.app.common.MessageEnum;
import com.message.app.dto.LoginRequestDTO;
import com.message.app.dto.LoginResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ValidationHandlerService {

    public LoginResponseDTO validateUserLoginRequest(LoginRequestDTO loginRequestDTO) {

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();

        if (StringUtils.isEmpty(loginRequestDTO.getUserName())){
            loginResponseDTO.setStatus(false);
            loginResponseDTO.setStatusMessage(MessageEnum.INVALID_USERNAME.message);
        } else if (StringUtils.isEmpty(loginRequestDTO.getPassword())){
            loginResponseDTO.setStatus(false);
            loginResponseDTO.setStatusMessage(MessageEnum.INVALID_PASSWORD.message);
        }else {
            loginResponseDTO.setStatus(true);
            loginResponseDTO.setStatusMessage(MessageEnum.LOGIN_SUCCESS.message);
        }

        return loginResponseDTO;
    }
}
