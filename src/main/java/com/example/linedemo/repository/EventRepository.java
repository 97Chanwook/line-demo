package com.example.linedemo.repository;

import com.example.linedemo.constant.EventStatus;
import com.example.linedemo.dto.EventDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//TODO : 인스턴스 생성 편의를 위해서 임시로 default를 사용, repository 구현이 완성되면 삭제할 예정
public interface EventRepository {

    default List<EventDto> findEvents(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDate,
            LocalDateTime eventEndDate
    ){
        return List.of();
    }

    default Optional<EventDto> findEvent(Long eventId){return Optional.empty();}
    default boolean insertEvent(EventDto eventDto){return false;}
    default boolean updateEvent(Long eventId, EventDto eventDto){return false;}
    default boolean deleteEvent(Long eventId){return false;}
}
