package com.example.studentsystem.entity;

import lombok.Data;
import java.time.LocalDateTime;


@Data
public class Student {
    private Long id;
    private String studentNo;
    private String name;
    private String gender;
    private String className;
    private String phone;
    private String email;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}