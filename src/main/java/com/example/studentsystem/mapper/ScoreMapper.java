package com.example.studentsystem.mapper;

import com.example.studentsystem.entity.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

//成绩访问接口
@Mapper
public interface ScoreMapper {


    List<Score> findAllWithDetails();
    List<Score> findByStudentId(@Param("studentId") Long studentId);
    List<Score> findByCourseId(@Param("courseId") Long courseId);
    List<Score> findBySemester(@Param("semester") String semester);
    int insert(Score score);
    int update(Score score);
    int deleteById(@Param("id") Long id);
}