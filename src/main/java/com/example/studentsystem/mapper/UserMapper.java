package com.example.studentsystem.mapper;

import com.example.studentsystem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

//用户数据访问接口
@Mapper
public interface UserMapper {


    User findByUsername(@Param("username") String username);
    User findByUsernameAndPassword(@Param("username") String username,
                                   @Param("password") String password);
}