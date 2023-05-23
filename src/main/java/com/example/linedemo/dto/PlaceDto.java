package com.example.linedemo.dto;

import com.example.linedemo.constant.PlaceType;

public record PlaceDto(
        PlaceType placeType,
        String address,
        String phoneNumber,
        Integer capacity,
        String memo) {

    public static PlaceDto of(PlaceType placeType, String address, String phoneNumber, Integer capacity, String memo) {
        return new PlaceDto(placeType,address,phoneNumber,capacity,memo);
    }
}
