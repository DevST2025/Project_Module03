package bussiness.service;

import bussiness.entity.User;

import java.util.List;

public interface IUserService extends IGeneric<User, Long> {
    long getNewId();
    User login(String username, String password);
    List<User> findUserByName(String name);
}
