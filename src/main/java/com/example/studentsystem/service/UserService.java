package com.example.studentsystem.service;

import com.example.studentsystem.entity.User;
import com.example.studentsystem.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务逻辑类
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     */
    public User login(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            throw new RuntimeException("用户名不能为空！");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new RuntimeException("密码不能为空！");
        }

        User user = userMapper.findByUsernameAndPassword(username, password);
        if (user == null) {
            throw new RuntimeException("用户名或密码错误！");
        }
        return user;
    }

    /**
     * 根据用户名查询用户
     */
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}