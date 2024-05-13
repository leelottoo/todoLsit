package com.keduit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.keduit.service.HelloService;

import lombok.RequiredArgsConstructor;

@Controller
// 1.생성자 주입: final 혹은 @NonNull이 붙은 필드에 대한 생성자
@RequiredArgsConstructor
public class SampleController {
	
	// 2.생성자주입을 받을 필드(객체)를 final로 선언해야 함. 불변
	private final HelloService helloService;
	
	@GetMapping("/hello")
	public void hello() {
		System.out.println(helloService);
		System.out.println(helloService.sayHello());
		System.out.println("Hello~");
	}
	

}
