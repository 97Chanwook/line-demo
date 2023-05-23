package com.example.linedemo.dto;

import com.example.linedemo.constant.PlaceType;

import java.time.LocalDateTime;

public record PlaceDto(
        Long id,
        PlaceType placeType,
        String placeName,
        String address,
        String phoneNumber,
        Integer capacity,
        String memo,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

    public static PlaceDto of(
            Long id,
            PlaceType placeType,
            String placeName,
            String address,
            String phoneNumber,
            Integer capacity,
            String memo,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt
    ) {
        return new PlaceDto(id, placeType, placeName, address, phoneNumber, capacity, memo, createdAt, modifiedAt);
    }
}
