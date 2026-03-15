package com.example.studentsystem.service;

import com.example.studentsystem.entity.Course;
import com.example.studentsystem.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * 课程业务逻辑类
 */
@Service
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;

    public List<Course> getAllCourses() {
        return courseMapper.findAll();
    }

    public Course getCourseById(Long id) {
        return courseMapper.findById(id);
    }

    public Course getCourseByNo(String courseNo) {
        return courseMapper.findByCourseNo(courseNo);
    }

    @Transactional
    public boolean addCourse(Course course) {
        // 检查课程编号是否已存在
        Course existingCourse = courseMapper.findByCourseNo(course.getCourseNo());
        if (existingCourse != null) {
            throw new RuntimeException("课程编号已存在！");
        }
        return courseMapper.insert(course) > 0;
    }

    @Transactional
    public boolean updateCourse(Course course) {
        return courseMapper.update(course) > 0;
    }

    @Transactional
    public boolean deleteCourse(Long id) {
        return courseMapper.deleteById(id) > 0;
    }
}