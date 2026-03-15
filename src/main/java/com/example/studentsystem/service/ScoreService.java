package com.example.studentsystem.service;

import com.example.studentsystem.entity.Score;
import com.example.studentsystem.mapper.ScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 * 成绩业务逻辑类
 */
@Service
public class ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    public List<Score> getAllScores() {
        return scoreMapper.findAllWithDetails();
    }

    public List<Score> getScoresByStudentId(Long studentId) {
        return scoreMapper.findByStudentId(studentId);
    }

    public List<Score> getScoresByCourseId(Long courseId) {
        return scoreMapper.findByCourseId(courseId);
    }

    public List<Score> getScoresBySemester(String semester) {
        return scoreMapper.findBySemester(semester);
    }

    @Transactional
    public boolean addScore(Score score) {
        // 验证成绩范围
        if (score.getScoreValue() == null) {
            throw new RuntimeException("成绩不能为空！");
        }
        if (score.getScoreValue().compareTo(BigDecimal.ZERO) < 0 ||
                score.getScoreValue().compareTo(new BigDecimal("100")) > 0) {
            throw new RuntimeException("成绩必须在0-100之间！");
        }
        return scoreMapper.insert(score) > 0;
    }

    @Transactional
    public boolean updateScore(Score score) {
        // 验证成绩范围
        if (score.getScoreValue() == null) {
            throw new RuntimeException("成绩不能为空！");
        }
        if (score.getScoreValue().compareTo(BigDecimal.ZERO) < 0 ||
                score.getScoreValue().compareTo(new BigDecimal("100")) > 0) {
            throw new RuntimeException("成绩必须在0-100之间！");
        }
        return scoreMapper.update(score) > 0;
    }

    @Transactional
    public boolean deleteScore(Long id) {
        return scoreMapper.deleteById(id) > 0;
    }

    /**
     * 计算学生平均分
     */
    public BigDecimal calculateAverage(Long studentId) {
        List<Score> scores = getScoresByStudentId(studentId);
        if (scores.isEmpty()) {
            return BigDecimal.ZERO;
        }

        BigDecimal total = scores.stream()
                .map(Score::getScoreValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return total.divide(new BigDecimal(scores.size()), 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 计算课程平均分
     */
    public BigDecimal calculateCourseAverage(Long courseId) {
        List<Score> scores = getScoresByCourseId(courseId);
        if (scores.isEmpty()) {
            return BigDecimal.ZERO;
        }

        BigDecimal total = scores.stream()
                .map(Score::getScoreValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return total.divide(new BigDecimal(scores.size()), 2, BigDecimal.ROUND_HALF_UP);
    }
}