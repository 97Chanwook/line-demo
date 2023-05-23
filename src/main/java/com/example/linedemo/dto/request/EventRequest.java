package com.example.linedemo.dto.request;

import com.example.linedemo.constant.EventStatus;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

public record EventRequest(
        @NonNull @Positive Long PlaceId,
        @NotBlank String eventName,
        @NonNull EventStatus eventStatus,
        @NonNull LocalDateTime eventStartDateTime,
        @NonNull LocalDateTime eventEndDateTime,
        @NonNull @PositiveOrZero Integer currentMemberOfPeople,
        @NonNull @Positive Integer capacity,
        String memo
        ) {

    public static EventRequest of(Long placeId,
                                  String eventName,
                                  EventStatus eventStatus,
                                  LocalDateTime eventStartDateTime,
                                  LocalDateTime eventEndDateTime,
                                  Integer currentMemberOfPeople,
                                  Integer capacity,
                                  String memo){
        return new EventRequest(
                placeId,
                eventName,
                eventStatus,
                eventStartDateTime,
                eventEndDateTime,
                currentMemberOfPeople,
                capacity,
                memo);
    }

}
