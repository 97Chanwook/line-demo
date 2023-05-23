package com.example.linedemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;

@ControllerAdvice(basePackageClasses = BaseController.class)    // 클래스가 속해있는 패키지를 베이스 패키지로 설정
@Controller
public class BaseController {

    @GetMapping("/")
    public String root() {
        return "index";
    }
}
