package com.keduit.service;

import java.util.List;

import com.keduit.domain.TodoVO;

public interface TodoService {
	
	public void register(TodoVO todo); //등록하기
	
	public TodoVO get(Long tno); //1건 읽기
	
	public boolean modify(TodoVO todo); //수정하기
	
	public boolean remove(Long tno); //삭제하기
	
	public List<TodoVO> getList();  //목록 읽기

}
				