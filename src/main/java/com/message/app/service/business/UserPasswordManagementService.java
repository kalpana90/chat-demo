package com.message.app.service.business;

public interface UserPasswordManagementService {

    boolean checkPasswordMatch(String userName, String password);
}
