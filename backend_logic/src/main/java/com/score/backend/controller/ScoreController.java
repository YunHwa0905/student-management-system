package com.score.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.score.backend.service.ScoreService;

@RestController
@RequestMapping("/api/score")
public class ScoreController {

	@Autowired
	private ScoreService scoreService;

	// 성적 전체 조회
	@GetMapping
	public List<Map<String, Object>> getAllScores() {
		return scoreService.getAllScores();
	}

	// 성적 등록
	@PostMapping
	public int insertScore(@RequestBody Map<String, Object> body) {
		int std_no = (int) body.get("std_no");
		int ko = (int) body.get("ko");
		int en = (int) body.get("en");
		int ma = (int) body.get("ma");
		return scoreService.insertScore(std_no, ko, en, ma);
	}

	// 성적 삭제
	@DeleteMapping("/{score_id}")
	public int deleteScore(@PathVariable int score_id) {
		return scoreService.deleteScore(score_id);
	}

	// 본인 성적 조회
	@GetMapping("/my/{std_no}")
	public List<Map<String, Object>> getMyScore(@PathVariable int std_no) {
		return scoreService.getMyScore(std_no);
	}
}