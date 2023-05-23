package com.example.linedemo.error;

import com.example.linedemo.controller.error.BaseErrorController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BaseErrorController.class)
class BaseErrorControllerTest {

    private final MockMvc mvc;

    //JUNIT5 방식
    public BaseErrorControllerTest(@Autowired MockMvc mvc){
        this.mvc = mvc;
    }

    @DisplayName("(view)[GET] 에러 페이지 - 페이지 없음")
    @Test
    void givenWrongURI_whenRequestPage_thenReturns404Error() throws Exception {
        // Given


        // When & Then
        mvc.perform(get("/wrong-uri"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}