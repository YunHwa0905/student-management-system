package com.score.backend.entity;

import lombok.*;
@Getter
@Builder
//점수 받은걸로 평균 및 성적, 등수 만드는 엔티티
public class ScoreResponse {

	private int ko;
	private int en;
	private int ma;
	private double avg;
	private String grade;

}
