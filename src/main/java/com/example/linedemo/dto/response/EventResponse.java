package com.example.linedemo.dto.response;

import com.example.linedemo.constant.EventStatus;
import com.example.linedemo.dto.EventDto;

import java.time.LocalDateTime;

public record EventResponse(
        Long placeId,
        String eventName,
        EventStatus eventStatus,
        LocalDateTime eventStartDateTime,
        LocalDateTime eventEndDateTime,
        Integer currentMemberOfPeople,
        Integer capacity,
        String memo
) {
    public static EventResponse of(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDateTime,
            LocalDateTime eventEndDateTime,
            Integer currentMemberOfPeople,
            Integer capacity,
            String memo
    ){
        return new EventResponse(
                placeId,
                eventName,
                eventStatus,
                eventStartDateTime,
                eventEndDateTime,
                currentMemberOfPeople,
                capacity,
                memo);
    }

    public static EventResponse from(EventDto eventDto){
        if(eventDto == null) return null;
        return EventResponse.of(
                eventDto.placeId(),
                eventDto.eventName(),
                eventDto.eventStatus(),
                eventDto.eventStartDateTime(),
                eventDto.eventEndDateTime(),
                eventDto.currentMemberOfPeople(),
                eventDto.capacity(),
                eventDto.memo());
    }

}
