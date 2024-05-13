package com.keduit.domain;

import java.util.Date;

import lombok.Data;

@Data
public class TodoVO {
	
	private Long tno;
	private String title;
	private String writer;
	private Date duedate;
	private int finished;

}
