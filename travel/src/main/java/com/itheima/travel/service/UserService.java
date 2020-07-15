package com.itheima.travel.service;

import com.itheima.travel.domain.ResultInfo;
import com.itheima.travel.domain.User;

public interface UserService {
    /**
     * 注册新用户
     * 1、检查用户名是否存在
     * 2、检查手机号码是否存在
     * 3、密码要进行MD5加密
     * @param user
     * @return 封装了消息提示和处理结果的ResultInfo对象
     */
    ResultInfo registerUser(User user);
    /**
     * 根据用户名检查是否存在
     * @param username
     * @return 封装了消息提示和处理结果的ResultInfo对象
     */
    ResultInfo checkUsername(String username);

}
