package com.score.backend.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.score.backend.entity.ScoreResponse;
import com.score.backend.repository.ScoreRepository;

@Service
public class ScoreService {

    @Autowired private ScoreRepository scoreRepository;

    // 성적 전체 조회
    public List<Map<String, Object>> getAllScores() {
        return scoreRepository.getAllScores();
    }

    // 성적 등록 (등급 계산 후 저장)
    public int insertScore(int std_no, int ko, int en, int ma) {
        // 평균 계산
        double avg = Math.round((ko + en + ma) / 3.0 * 10) / 10.0;

        // 등급 계산
        String grade;
        if (avg >= 90)      grade = "A";
        else if (avg >= 80) grade = "B";
        else if (avg >= 70) grade = "C";
        else if (avg >= 60) grade = "D";
        else                grade = "E";

        // DB 저장
        Map<String, Object> score = new HashMap<>();
        score.put("std_no", std_no);
        score.put("ko", ko);
        score.put("en", en);
        score.put("ma", ma);
        score.put("avg", avg);
        score.put("grade", grade);

        return scoreRepository.insertScore(score);
    }

    // 성적 삭제
    public int deleteScore(int score_id) {
        return scoreRepository.deleteScore(score_id);
    }

}