package com.example.studentsystem.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Course {

    private Long id;
    private String courseNo;
    private String courseName;
    private BigDecimal credit;
    private String teacherName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}