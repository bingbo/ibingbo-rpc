package com.ibingbo.rpc.service.impl;

import com.ibingbo.rpc.annotation.RpcService;
import com.ibingbo.rpc.service.IUserService;
import com.ibingbo.rpc.service.bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by bing on 17/6/2.
 */
// 指定远程接口  使用 RpcService注解定义在服务接口的实现类上，需要对该实现类指定远程接口，因为实现类可能会实现多个接口，一定要告诉框架哪个才是远程接口
@RpcService(IUserService.class)
public class UserServiceImpl implements IUserService{
    public User getUser(String name) {
        return new User(new Random().nextInt(), name, name + "@126.com");
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        for (int i=0;i<10;i++) {
            users.add(new User(new Random().nextInt(), "foo" + i, "foo" + i + "@126.com"));
        }
        return users;
    }

    public User updateUser(User user) {
        user.setName(user.getName() + "-update");
        return user;
    }
}
