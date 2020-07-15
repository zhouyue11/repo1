package com.itheima.travel.web.controller;

import com.itheima.travel.domain.ResultInfo;
import com.itheima.travel.domain.User;
import com.itheima.travel.service.UserService;
import com.itheima.travel.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    //    private UserService userService = new UserServiceImpl();
    private UserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //获取action请求参数
        String action = request.getParameter("action");
    //判断要进行的具体操作
        /*
        * 用户有关的操作，找一个servlet，比如：/user
        * 注册：/user？action=register
        * 登录：/user？action=login
        * 注销：/user？action=logout
        * */
        if ("register".equals(action)){
            register(request,response);
        }else {
            throw new RuntimeException("非法操作");
        }
    }
    //完成用户的注册操作
    private void register(HttpServletRequest request, HttpServletResponse response) {
        //把数据封装到User对象中
        try {
            User user = new User();
            BeanUtils.populate(user,request.getParameterMap());
            //调用业务层代码进行保存
            ResultInfo info = userService.registerUser(user);
            if (info.getSuccess()){
                //如果是成功，重定向到regist_ok.jsp
                response.sendRedirect(request.getContextPath()+"regist_ok.jsp");
            }else{
                //根据业务返回的数据进行提示
                request.setAttribute("info",info);
                //请求转发
                request.getRequestDispatcher("/register.jsp").forward(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }
}
