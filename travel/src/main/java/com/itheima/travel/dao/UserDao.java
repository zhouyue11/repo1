package com.itheima.travel.dao;

import com.itheima.travel.domain.User;

public interface UserDao {
    User findUserByUsername(String username);

    User findUserByTelephone(String telephone);

    void saveUser(User user);
}
