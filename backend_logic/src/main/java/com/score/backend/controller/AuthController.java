package com.score.backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.score.backend.service.StudentService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private StudentService studentService;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, Object> body) {
        String id       = (String) body.get("id");
        String password = (String) body.get("password");

        Map<String, Object> user = studentService.login(id, password);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body("아이디 또는 비밀번호가 올바르지 않습니다.");
        }

        return ResponseEntity.ok(user);
    }

}