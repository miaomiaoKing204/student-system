package com.example.studentsystem.controller;

import com.example.studentsystem.dto.ApiResponse;
import com.example.studentsystem.entity.User;
import com.example.studentsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public ApiResponse<User> login(@RequestBody User user) {
        try {
            User loginUser = userService.login(user.getUsername(), user.getPassword());
            // 清除密码信息，不返回给前端
            loginUser.setPassword(null);
            return ApiResponse.success("登录成功！", loginUser);
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    /**
     * 根据用户名查询用户
     */
    @GetMapping("/username/{username}")
    public ApiResponse<User> findByUsername(@PathVariable String username) {
        try {
            User user = userService.findByUsername(username);
            if (user == null) {
                return ApiResponse.error("用户不存在！");
            }
            // 清除密码信息
            user.setPassword(null);
            return ApiResponse.success(user);
        } catch (Exception e) {
            return ApiResponse.error("查询失败：" + e.getMessage());
        }
    }
}