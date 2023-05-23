package com.example.linedemo.dto.response;

import com.example.linedemo.constant.PlaceType;

public record PlaceResponse(
        Long id,
        PlaceType placeType,
        String placeName,
        String address,
        String phoneNumber,
        Integer capacity,
        String memo
) {
    public static PlaceResponse of(
            Long id,
            PlaceType placeType,
            String placeName,
            String address,
            String phoneNumber,
            Integer capacity,
            String memo
    ) {
        return new PlaceResponse(id, placeType, placeName, address, phoneNumber, capacity, memo);
    }
}
