package com.ml.wxjsxy.iserver;


import com.ml.wxjsxy.model.User;

import java.util.List;


public interface IUserDao {
    List<User> userLogin(User user);
    int InsertUser(User user);
    User Privilege(User user);
}
