package com.keduit.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.keduit.domain.TodoVO;
import com.keduit.service.TodoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/todo/**")
@RequiredArgsConstructor
public class TodoController {

	private final TodoService service;
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("----------list---------");
		model.addAttribute("list",service.getList());
	}
	
	@PostMapping("/register")
	public String register(TodoVO todo, RedirectAttributes rttr) {
		log.info(".....register....." + todo);
		service.register(todo);
		rttr.addFlashAttribute("result",todo.getTno());
		return "redirect:/todo/list";
	}
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("tno") long tno , Model model) {
		log.info("......get......");
		model.addAttribute("todo",service.get(tno));
	}
	
	@PostMapping("/modify")
	public String modify(TodoVO todo, RedirectAttributes rttr) {
		log.info("......modify....." + todo);
		if(service.modify(todo)) {
			rttr.addFlashAttribute("result","success");
		}
		
		return "redirect:/todo/list";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("tno") long tno,RedirectAttributes rttr) {
		
		log.info("....삭제......" + tno);
		if(service.remove(tno)) {
			rttr.addFlashAttribute("result","success");
		}
		return "redirect:/todo/list";
	}

}
