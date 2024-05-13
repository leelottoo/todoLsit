package com.keduit.mapper;

import java.util.List;

import com.keduit.domain.TodoVO;

public interface TodoMapper {
	
	public List<TodoVO> getList();
	
	public void insert(TodoVO todo);
	
	public void insertSelectKey(TodoVO todo);
	
	public TodoVO read(Long tno);
	
	public int delete(Long tno);
	
	public int update(TodoVO todo);

}
