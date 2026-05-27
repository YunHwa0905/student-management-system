package com.score.backend.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.score.backend.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	// 학생 전체 조회
	public List<Map<String, Object>> getAllStudents() {
		return studentRepository.getAllStudents();
	}

	// 학생 등록
	public int insertStudent(Map<String, Object> student) {
		return studentRepository.insertStudent(student);
	}

	// 학생 삭제
	public int deleteStudent(int std_no) {
		return studentRepository.deleteStudent(std_no);
	}

	// 로그인
	public Map<String, Object> login(String id, String password) {
		return studentRepository.login(id, password);
	}

}