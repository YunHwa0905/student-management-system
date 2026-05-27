package com.score.backend.repository;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ScoreRepository {

    @Autowired private SqlSessionTemplate sqlSession;

    // 성적 전체 조회
    public List<Map<String, Object>> getAllScores() {
        return sqlSession.selectList("score.getAllScores");
    }

    // 성적 등록
    public int insertScore(Map<String, Object> score) {
        return sqlSession.insert("score.insertScore", score);
    }

    // 성적 삭제
    public int deleteScore(int score_id) {
        return sqlSession.delete("score.deleteScore", score_id);
    }

}