package com.example.studentsystem.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Score {

    private Long id;
    private Long studentId;
    private Long courseId;
    private BigDecimal scoreValue;
    private String semester;
    private LocalDateTime examTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
//非数据库字段，关联查询
    private String studentName;
    private String studentNo;
    private String courseName;
    private String className;
}