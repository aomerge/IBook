package com.IBook.service.serviceDto;

import com.IBook.model.UserModel;

import java.util.List;

public interface UserDTO {
    public List<UserModel> getUsers(String page);
    public UserModel getUserById(long id);
}