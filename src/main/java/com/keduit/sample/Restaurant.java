package com.keduit.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;

//자바 빈으로 관리
@Component
// lombok:getter, setter, toString, 생성자
@Data
public class Restaurant {
	
	// 필드 주입
	@Autowired
	private Chef chef;

}
