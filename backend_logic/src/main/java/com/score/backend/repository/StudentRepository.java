package com.score.backend.repository;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    @Autowired private SqlSessionTemplate sqlSession;

    // 학생 전체 조회
    public List<Map<String, Object>> getAllStudents() {
        return sqlSession.selectList("student.getAllStudents");
    }

    // 학생 등록
    public int insertStudent(Map<String, Object> student) {
        return sqlSession.insert("student.insertStudent", student);
    }

    // 학생 삭제
    public int deleteStudent(int std_no) {
        return sqlSession.delete("student.deleteStudent", std_no);
    }

}