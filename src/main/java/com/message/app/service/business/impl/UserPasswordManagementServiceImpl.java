package com.message.app.service.business.impl;

import com.message.app.service.business.UserPasswordManagementService;
import com.message.app.service.dao.UserPasswordDao;
import com.message.app.service.domain.UserPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserPasswordManagementServiceImpl implements UserPasswordManagementService {

    @Autowired
    private UserPasswordDao userPasswordDao;


    @Override
    public boolean checkPasswordMatch(String userName, String password) {

        UserPassword userPassword = userPasswordDao.getPasswordByUserName(userName);
        if (userPassword != null){
            //check password validity
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            return encoder.matches(password, userPassword.getPassword());
        }
        return false;
    }
}
