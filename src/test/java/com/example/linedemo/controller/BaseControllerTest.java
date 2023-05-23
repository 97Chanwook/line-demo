package com.example.linedemo.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@AutoConfigureMockMvc
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)    //기본 값 MOCK
@WebMvcTest //MOCK MVC 를 사용하기 위한 @AutoConfigureMockMvc 를 사용하지 않아도 됀다
// @WebMvcTest(TestClass.class) 특정 클래스만 테스트 하기 위할 때
class BaseControllerTest {

    //mock mvc
    //@Autowired
    private final MockMvc mvc;

    //JUNIT5 방식
    public BaseControllerTest(@Autowired MockMvc mvc){
        this.mvc = mvc;
    }

    //JUNIT4 방식
    @DisplayName("(view)[GET] 기본 페이지 요청")
    @Test
    void test_root() throws Exception {
        // 1. Test의 경우 test를 앞에 붙혀주는 네이밍을 주로 사용 ex) test_root
        // 2. should 방식 ex) basePageShouldIndexPage()
        // 3. givenWhenThen 방식 ex) giventNothing_whenRequestingRootPage_thenReturnsIndexPage()
        // 4. @DisplayName("(view)[GET] 기본 페이지 요청")

        // Given


        // When & Then
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(content().string(containsString("This is Default Page.")))
                .andExpect(view().name("index"))
                .andDo(print());
    }


}