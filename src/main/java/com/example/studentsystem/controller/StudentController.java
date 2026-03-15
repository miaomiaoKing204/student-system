package com.example.studentsystem.controller;

import com.example.studentsystem.dto.ApiResponse;
import com.example.studentsystem.entity.Student;
import com.example.studentsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生控制器
 * 处理学生相关的HTTP请求
 */
@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*") // 允许跨域访问
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 查询所有学生
     */
    @GetMapping
    public ApiResponse<List<Student>> getAllStudents() {
        try {
            List<Student> students = studentService.getAllStudents();
            return ApiResponse.success(students);
        } catch (Exception e) {
            return ApiResponse.error("查询失败：" + e.getMessage());
        }
    }

    /**
     * 根据ID查询学生
     */
    @GetMapping("/{id}")
    public ApiResponse<Student> getStudentById(@PathVariable Long id) {
        try {
            Student student = studentService.getStudentById(id);
            if (student == null) {
                return ApiResponse.error("学生不存在！");
            }
            return ApiResponse.success(student);
        } catch (Exception e) {
            return ApiResponse.error("查询失败：" + e.getMessage());
        }
    }

    /**
     * 根据学号查询学生
     */
    @GetMapping("/no/{studentNo}")
    public ApiResponse<Student> getStudentByNo(@PathVariable String studentNo) {
        try {
            Student student = studentService.getStudentByNo(studentNo);
            if (student == null) {
                return ApiResponse.error("学生不存在！");
            }
            return ApiResponse.success(student);
        } catch (Exception e) {
            return ApiResponse.error("查询失败：" + e.getMessage());
        }
    }

    /**
     * 搜索学生
     */
    @GetMapping("/search")
    public ApiResponse<List<Student>> searchStudents(@RequestParam String keyword) {
        try {
            List<Student> students = studentService.searchStudents(keyword);
            return ApiResponse.success(students);
        } catch (Exception e) {
            return ApiResponse.error("搜索失败：" + e.getMessage());
        }
    }

    /**
     * 添加学生
     */
    @PostMapping
    public ApiResponse<String> addStudent(@RequestBody Student student) {
        try {
            boolean success = studentService.addStudent(student);
            return ApiResponse.success(success ? "添加成功！" : "添加失败！");
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    /**
     * 更新学生信息
     */
    @PutMapping("/{id}")
    public ApiResponse<String> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        try {
            student.setId(id);
            boolean success = studentService.updateStudent(student);
            return ApiResponse.success(success ? "更新成功！" : "更新失败！");
        } catch (Exception e) {
            return ApiResponse.error("更新失败：" + e.getMessage());
        }
    }

    /**
     * 删除学生
     */
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteStudent(@PathVariable Long id) {
        try {
            boolean success = studentService.deleteStudent(id);
            return ApiResponse.success(success ? "删除成功！" : "删除失败！");
        } catch (Exception e) {
            return ApiResponse.error("删除失败：" + e.getMessage());
        }
    }

    /**
     * 统计学生数量
     */
    @GetMapping("/count")
    public ApiResponse<Integer> countStudents() {
        try {
            int count = studentService.countStudents();
            return ApiResponse.success(count);
        } catch (Exception e) {
            return ApiResponse.error("统计失败：" + e.getMessage());
        }
    }
}