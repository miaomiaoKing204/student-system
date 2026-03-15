package com.example.studentsystem.service;

import com.example.studentsystem.entity.Student;
import com.example.studentsystem.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * 学生业务逻辑类
 */
@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 查询所有学生
     */
    public List<Student> getAllStudents() {
        return studentMapper.findAll();
    }

    /**
     * 根据ID查询学生
     */
    public Student getStudentById(Long id) {
        return studentMapper.findById(id);
    }

    /**
     * 根据学号查询学生
     */
    public Student getStudentByNo(String studentNo) {
        return studentMapper.findByStudentNo(studentNo);
    }

    /**
     * 搜索学生
     */
    public List<Student> searchStudents(String keyword) {
        return studentMapper.search(keyword);
    }

    /**
     * 添加学生（带事务）
     */
    @Transactional
    public boolean addStudent(Student student) {
        // 检查学号是否已存在
        Student existingStudent = studentMapper.findByStudentNo(student.getStudentNo());
        if (existingStudent != null) {
            throw new RuntimeException("学号已存在！");
        }
        return studentMapper.insert(student) > 0;
    }

    /**
     * 更新学生信息（带事务）
     */
    @Transactional
    public boolean updateStudent(Student student) {
        return studentMapper.update(student) > 0;
    }

    /**
     * 删除学生（带事务）
     */
    @Transactional
    public boolean deleteStudent(Long id) {
        return studentMapper.deleteById(id) > 0;
    }

    /**
     * 统计学生数量
     */
    public int countStudents() {
        return studentMapper.count();
    }
}