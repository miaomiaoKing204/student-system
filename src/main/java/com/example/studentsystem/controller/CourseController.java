package com.example.studentsystem.controller;

import com.example.studentsystem.dto.ApiResponse;
import com.example.studentsystem.entity.Course;
import com.example.studentsystem.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程控制器
 */
@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ApiResponse<List<Course>> getAllCourses() {
        try {
            List<Course> courses = courseService.getAllCourses();
            return ApiResponse.success(courses);
        } catch (Exception e) {
            return ApiResponse.error("查询失败：" + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ApiResponse<Course> getCourseById(@PathVariable Long id) {
        try {
            Course course = courseService.getCourseById(id);
            if (course == null) {
                return ApiResponse.error("课程不存在！");
            }
            return ApiResponse.success(course);
        } catch (Exception e) {
            return ApiResponse.error("查询失败：" + e.getMessage());
        }
    }

    @PostMapping
    public ApiResponse<String> addCourse(@RequestBody Course course) {
        try {
            boolean success = courseService.addCourse(course);
            return ApiResponse.success(success ? "添加成功！" : "添加失败！");
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ApiResponse<String> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        try {
            course.setId(id);
            boolean success = courseService.updateCourse(course);
            return ApiResponse.success(success ? "更新成功！" : "更新失败！");
        } catch (Exception e) {
            return ApiResponse.error("更新失败：" + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteCourse(@PathVariable Long id) {
        try {
            boolean success = courseService.deleteCourse(id);
            return ApiResponse.success(success ? "删除成功！" : "删除失败！");
        } catch (Exception e) {
            return ApiResponse.error("删除失败：" + e.getMessage());
        }
    }
}