package com.example.linedemo.dto;

import com.example.linedemo.constant.EventStatus;

import java.time.LocalDateTime;

public record EventDto(Long placeId,
                       String eventName,
                       EventStatus eventStatus,
                       LocalDateTime eventStartDateTime,
                       LocalDateTime eventEndDateTime,
                       Integer currentMemberOfPeople,
                       Integer capacity,
                       String memo,
                       LocalDateTime createdAt,
                       LocalDateTime modifiedTime) {

    public static EventDto of( Long placeId,
                               String eventName,
                               EventStatus eventStatus,
                               LocalDateTime eventStartDateTime,
                               LocalDateTime eventEndDateTime,
                               Integer currentMemberOfPeople,
                               Integer capacity,
                               String memo,
                               LocalDateTime createdAt,
                               LocalDateTime modifiedTime){
        return new EventDto(placeId,
                eventName,
                eventStatus,
                eventStartDateTime,
                eventEndDateTime,
                currentMemberOfPeople,
                capacity,
                memo,
                createdAt,
                modifiedTime
        );
    }
}
