package com.example.linedemo.dto;

import com.example.linedemo.dto.response.APIDataResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class APIDataResponseTest {

    @DisplayName("문자열 데이터가 주어지면, 표준 성공 응답을 생성한다.")
    @Test
    void givenStringData_whenCreatingResponse_thenReturnSuccess(){
        // Given
        String data = "test data";

        // When
        APIDataResponse response = APIDataResponse.of(
                data
        );

        // Then
        assertThat(response).isNotNull()
                .hasFieldOrPropertyWithValue("data","test data");
    }
}