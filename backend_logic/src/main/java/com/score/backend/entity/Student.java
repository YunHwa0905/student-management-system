package com.score.backend.entity;

import lombok.*;
@Getter
@Builder
//학생 정보 엔티티
public class Student {

	private String name;
	private int std_no;
	private String id;
	private String password;

}
