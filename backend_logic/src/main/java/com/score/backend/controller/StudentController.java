package com.score.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.score.backend.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired private StudentService studentService;

    // 학생 전체 조회
    @GetMapping
    public List<Map<String, Object>> getAllStudents() {
        return studentService.getAllStudents();
    }

    // 학생 등록
    @PostMapping
    public int insertStudent(@RequestBody Map<String, Object> student) {
        return studentService.insertStudent(student);
    }

    // 학생 삭제
    @DeleteMapping("/{std_no}")
    public int deleteStudent(@PathVariable int std_no) {
        return studentService.deleteStudent(std_no);
    }

}