package com.example.studentsystem.controller;

import com.example.studentsystem.dto.ApiResponse;
import com.example.studentsystem.entity.Score;
import com.example.studentsystem.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * 成绩控制器
 */
@RestController
@RequestMapping("/api/scores")
@CrossOrigin(origins = "*")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @GetMapping
    public ApiResponse<List<Score>> getAllScores() {
        try {
            List<Score> scores = scoreService.getAllScores();
            return ApiResponse.success(scores);
        } catch (Exception e) {
            return ApiResponse.error("查询失败：" + e.getMessage());
        }
    }

    @GetMapping("/student/{studentId}")
    public ApiResponse<List<Score>> getScoresByStudentId(@PathVariable Long studentId) {
        try {
            List<Score> scores = scoreService.getScoresByStudentId(studentId);
            return ApiResponse.success(scores);
        } catch (Exception e) {
            return ApiResponse.error("查询失败：" + e.getMessage());
        }
    }

    @GetMapping("/course/{courseId}")
    public ApiResponse<List<Score>> getScoresByCourseId(@PathVariable Long courseId) {
        try {
            List<Score> scores = scoreService.getScoresByCourseId(courseId);
            return ApiResponse.success(scores);
        } catch (Exception e) {
            return ApiResponse.error("查询失败：" + e.getMessage());
        }
    }

    @GetMapping("/semester/{semester}")
    public ApiResponse<List<Score>> getScoresBySemester(@PathVariable String semester) {
        try {
            List<Score> scores = scoreService.getScoresBySemester("专业版");
            return ApiResponse.success(scores);
        } catch (Exception e) {
            return ApiResponse.error("查询失败：" + e.getMessage());
        }
    }

    @PostMapping
    public ApiResponse<String> addScore(@RequestBody Score score) {
        try {
            boolean success = scoreService.addScore(score);
            return ApiResponse.success(success ? "添加成功！" : "添加失败！");
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ApiResponse<String> updateScore(@PathVariable Long id, @RequestBody Score score) {
        try {
            score.setId(id);
            boolean success = scoreService.updateScore(score);
            return ApiResponse.success(success ? "更新成功！" : "更新失败！");
        } catch (Exception e) {
            return ApiResponse.error("更新失败：" + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteScore(@PathVariable Long id) {
        try {
            boolean success = scoreService.deleteScore(id);
            return ApiResponse.success(success ? "删除成功！" : "删除失败！");
        } catch (Exception e) {
            return ApiResponse.error("删除失败：" + e.getMessage());
        }
    }

    /**
     * 计算学生平均分
     */
    @GetMapping("/average/student/{studentId}")
    public ApiResponse<BigDecimal> getStudentAverage(@PathVariable Long studentId) {
        try {
            BigDecimal average = scoreService.calculateAverage(studentId);
            return ApiResponse.success("计算成功！", average);
        } catch (Exception e) {
            return ApiResponse.error("计算失败：" + e.getMessage());
        }
    }

    /**
     * 计算课程平均分
     */
    @GetMapping("/average/course/{courseId}")
    public ApiResponse<BigDecimal> getCourseAverage(@PathVariable Long courseId) {
        try {
            BigDecimal average = scoreService.calculateCourseAverage(courseId);
            return ApiResponse.success("计算成功！", average);
        } catch (Exception e) {
            return ApiResponse.error("计算失败：" + e.getMessage());
        }
    }
}
