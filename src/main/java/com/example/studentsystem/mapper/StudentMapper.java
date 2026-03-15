package com.example.studentsystem.mapper;

import com.example.studentsystem.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;


@Mapper
public interface StudentMapper {


    List<Student> findAll();
    Student findById(@Param("id") Long id);
    Student findByStudentNo(@Param("studentNo") String studentNo);
    List<Student> search(@Param("keyword") String keyword);
    int insert(Student student);
    int update(Student student);
    int deleteById(@Param("id") Long id);

    int count();
}