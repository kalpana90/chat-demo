package com.message.app.service.business.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.message.app.service.business.UserManagementService;
import com.message.app.service.dao.UserDao;
import com.message.app.service.domain.User;
import com.message.app.service.domain.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserDao userDao;


    public List<User> getRecipientList(String userId) {

        List<User> recipientList = new ArrayList<>();

        List<User> users = userDao.getAllUsers();

        if (users!= null && !users.isEmpty()){
            for (User user : users){
                if (!user.getId().equals(Long.valueOf(userId))){
                    recipientList.add(user);
                }
            }
        }

        return recipientList;
    }

    @Override
    public boolean checkUserExist(String userName) {

        User user = userDao.findByUserName(userName);
        return user != null;
    }

    @Override
    public User getUserByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public User getUserById(String senderId) {
        return userDao.findByUserId(senderId);
    }

}
