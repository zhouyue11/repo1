package com.itheima.travel.service.impl;


import com.itheima.travel.dao.UserDao;
import com.itheima.travel.domain.ResultInfo;
import com.itheima.travel.domain.User;
import com.itheima.travel.service.UserService;
import org.apache.ibatis.session.SqlSession;
import static com.itheima.util.Md5Utils.encodeByMd5;
import static com.itheima.util.MyBatisUtils.openSession;

public class UserServiceImpl implements UserService {
    @Override
    public ResultInfo registerUser(User user) {
        SqlSession sqlSession = null;
        try {
            if(user==null)
                throw new IllegalArgumentException("错误的参数");
            sqlSession = openSession();
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            //调用dao根据用户名查询
            User user1 = userDao.findUserByUsername(user.getUsername());
            if(user1!=null){
                return new ResultInfo(false,"用户名已经存在了");
            }
            //调用dao根据手机号码查询
            User user2 = userDao.findUserByTelephone(user.getTelephone());
            if(user2!=null){
                return new ResultInfo(false,"手机号已经被注册过了");
            }
            //密码进行加密
            String password = user.getPassword();
            String md5pass = encodeByMd5(password);
            user.setPassword(md5pass);
            //user.setPassword(Md5Utils.encodeByMd5(user.getPassword()));
            //调用dao保存数据
            userDao.saveUser(user);
            sqlSession.commit();
            return new ResultInfo(true);
        } finally {
            if(sqlSession!=null)
                sqlSession.close();
        }
    }

    public ResultInfo checkUsername(String username) {
        SqlSession sqlSession = null;
        try {
            sqlSession = openSession();
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            User user = userDao.findUserByUsername(username);
            if (user==null){
                return new ResultInfo(true,"用户名可用");
            }
            return new ResultInfo(false,"用户名已经被占用");
        } finally {
            if (sqlSession!=null)
                sqlSession.close();
        }
    }

}

