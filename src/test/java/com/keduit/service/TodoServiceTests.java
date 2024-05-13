package com.keduit.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.keduit.domain.TodoVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TodoServiceTests {
	
	@Autowired
	private TodoService service;
	
	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	
	@Test
	public void TestRegister() {
		TodoVO todo = new TodoVO();
		todo.setTitle("service를 테스트 해보장");
		todo.setWriter("영춘권");
		todo.setFinished(0);
		
		service.register(todo);
		log.info("---------생성된 게시물 번호 : " + todo.getTno());
	}

	@Test
	public void testGetList() {
		service.getList().forEach(todo -> log.info(todo));
	}
	
	@Test
	public void testGet() {
		log.info("......testGet...." + service.get(9L));
	}
	
	@Test
	public void testUpdate() {
		TodoVO todo = service.get(5L);
		
		if(todo == null) {
			return;
		}
		todo.setTitle("서비스에서 수정했음~");
		log.info("......testupdate....."+service.modify(todo));
	}
	
	@Test
	public void testDelete() {
		log.info("......testDelete..."+service.remove(5L));
	}
	

}





