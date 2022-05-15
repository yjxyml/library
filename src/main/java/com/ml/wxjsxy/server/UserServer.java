package com.ml.wxjsxy.server;

import com.ml.wxjsxy.dao.UserDao;
import com.ml.wxjsxy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component("userserver")
public class UserServer {

    @Autowired
    private UserDao userDao;

    public List<User> selectUsers(User user)
    {
        List<User> users = userDao.userLogin(user);
        return users;
    }

    public int insert(User user)
    {
        return userDao.InsertUser(user);
    }

    public User Privilege(User user)
    {
        User user1 = userDao.Privilege(user);
        return user1;
    }
}
