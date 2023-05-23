package com.example.linedemo.dto.request;

import com.example.linedemo.constant.PlaceType;
import com.example.linedemo.dto.PlaceDto;

public record PlaceRequest(
        Long id,
        PlaceType placeType,
        String placeName,
        String address,
        String phoneNumber,
        Integer capacity,
        String memo
){

    public static PlaceRequest of(
            Long id,
            PlaceType placeType,
            String placeName,
            String address,
            String phoneNumber,
            Integer capacity,
            String memo
    ){
        return new PlaceRequest(id,placeType,placeName,address,phoneNumber,capacity,memo);
    }

    public PlaceDto toDto(){
        return PlaceDto.of(
                this.id(),
                this.placeType(),
                this.placeName(),
                this.address(),
                this.phoneNumber,
                this.capacity(),
                this.memo(),
                null,
                null
        );
    }

}
