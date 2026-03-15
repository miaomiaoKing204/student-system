package com.example.studentsystem.mapper;

import com.example.studentsystem.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 课程数据访问接口
 */
@Mapper
public interface CourseMapper {

    List<Course> findAll();
    Course findById(@Param("id") Long id);
    Course findByCourseNo(@Param("courseNo") String courseNo);
    int insert(Course course);
    int update(Course course);
    int deleteById(@Param("id") Long id);
}