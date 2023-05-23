package com.example.linedemo.controller.api;

import com.example.linedemo.constant.PlaceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(APIPlaceController.class)
class APIPlaceControllerTest {
    private final MockMvc mvc;

    public APIPlaceControllerTest(@Autowired MockMvc mvc){
        this.mvc = mvc;
    }

    @DisplayName("(API)[GET] 장소 리스트 조회")
    @Test
    void givenNothing_whenRequestingPlaces_thenReturnsListOfPlaces() throws Exception {
        mvc.perform(get("/api/places"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].placeType").value(PlaceType.COMMON.name()));
//                .andExpect(jsonPath("$.data[0].address").value(""))
//                .andExpect(jsonPath("$.data[0].phoneNumber").value(""))
//                .andExpect(jsonPath("$.data[0].capacity").value(""))
//                .andExpect(jsonPath("$.data[0].memo").value(""))
//                .andExpect(jsonPath("$.success").value(""))
//                .andExpect(jsonPath("$.errorCode").value(""))
//                .andExpect(jsonPath("$.message").value(""));
    }

    @DisplayName("(API)[GET] 장소 조회")
    @Test
    void givenPlaceId_whenRequestingPlace_thenReturnsPlace() throws Exception {
        // Given
        int placeId = 1;
        // When & Then
        mvc.perform(get("/api/places"+placeId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.data.placeType").value(PlaceType.COMMON.name()));
    }
}