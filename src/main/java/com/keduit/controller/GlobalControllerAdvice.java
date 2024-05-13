package com.keduit.controller;

//GlobalControllerAdvice 클래스는 스프링 애플리케이션 전반에 걸쳐서 HTTP 요청에서 받은 문자열 형식의 날짜를 자동으로 Date 객체로 변환해주는 역할을 합니다.
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//전역적인 컨트롤러 어드바이스
//모든 컨트롤러에 영향을 준다.
//이 클래스의 메서드들은 모든 컨트롤러에서 실행 될 수 있는 전역 설정을 포함한다.
@RestControllerAdvice
public class GlobalControllerAdvice {
    //스프링이 HTTP요청을 처리 할 때 요청 데이터를 컨트롤러 메서드의 파라미터로 바인딩 할 때 사용된다.
    //컨트롤러 메서드가 호출되기 전에 실행된다.
    @InitBinder
    //문자열 형식의 날짜를 SimpleDateFormat으로 파싱하여 'date'객체로 변환한다.
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}