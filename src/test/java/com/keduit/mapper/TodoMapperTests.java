package com.keduit.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.keduit.domain.TodoVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TodoMapperTests {

	@Setter(onMethod_ = @Autowired)
	private TodoMapper mapper;
	
	@Test
	public void testGetList() {
		log.info(mapper.getList());
		mapper.getList().forEach(todo -> log.info(todo));
	}
	
	@Test
	public void testInsert() {
		TodoVO todo = new TodoVO();
		todo.setTitle("오늘 남아서 공부하기");
		todo.setWriter("이지은");
		mapper.insert(todo);
		log.info(todo);
		
	}
	
	@Test
	public void testInsertSelectKey() {
		TodoVO todo = new TodoVO();
		todo.setTitle("시퀀스에서 가지고 오는 기본 키를 먼저 읽고 insert title");
		todo.setWriter("user02");
		
		mapper.insertSelectKey(todo);
		log.info("..............insertSelectKey...."+todo);
	}
	
	@Test
	public void testRead() { //두가지 방법
		TodoVO todo = mapper.read(4L);
		log.info(".....read-------->"+todo);
	}
	
	@Test
	public void testDelete() { //두가지 방법
		log.info("----delete count : " + mapper.delete(4L));
		
	}
	
	@Test
	public void testUpdate() {
		TodoVO todo = new TodoVO();
		todo.setTno(8L);
		todo.setTitle("오늘 공부하기 완료(수정)");
		todo.setWriter("update!");
		todo.setFinished(1);
		
		int count = mapper.update(todo);
		log.info("=====update count=====>"+count);
	}
	
	
}





