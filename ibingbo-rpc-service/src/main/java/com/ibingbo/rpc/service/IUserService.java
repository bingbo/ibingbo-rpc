package com.ibingbo.rpc.service;

import com.ibingbo.rpc.service.bean.User;

import java.util.List;

/**
 * Created by bing on 17/6/2.
 */
public interface IUserService {
    User getUser(String name);

    List<User> getUsers();

    User updateUser(User user);
}
