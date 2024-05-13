package com.keduit.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
@WebAppConfiguration
public class TodoControllerTests {
	
	@Setter(onMethod_ = {@Autowired})
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void testList() throws Exception {
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/todo/list"))
						.andReturn()
						.getModelAndView()
						.getModelMap());
	}
	
	@Test
	public void testRegister() throws Exception{
		
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/todo/register")
							.param("title", "오늘도 헬스를 간다.")
							.param("writer", "이지은"))
							.andReturn()
							.getModelAndView()
							.getViewName();
		log.info("................register테스트임..." + resultPage);
	}
	
	@Test
	public void testGet() throws Exception{
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/todo/get")
				.param("tno", "21"))
				.andReturn().getModelAndView().getModelMap());
	}
	
	@Test
	public void testModify() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/todo/modify")
				.param("tno", "22")
				.param("title", "그만하고싶다.")
				.param("writer", "이지은")
				.param("duedate", "2024-03-28")
				.param("finished", "0"))
				.andReturn()
				.getModelAndView()
				.getViewName();
		log.info(".......testModify...." + resultPage);
	}
	
	@Test
	public void testRemove() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/todo/remove")
				.param("tno", "22"))
				.andReturn().getModelAndView().getViewName();
		log.info("......삭제한다..." + resultPage);
	}
	

}
