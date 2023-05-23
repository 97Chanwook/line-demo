package com.example.linedemo.service;

import com.example.linedemo.constant.EventStatus;
import com.example.linedemo.dto.EventDto;
import com.example.linedemo.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EventService {
    private final EventRepository eventRepository;

    // 이벤트 목록 조회
    public List<EventDto> getEvents(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDateTime,
            LocalDateTime eventEndDateTime
    ){
        return eventRepository.findEvents(placeId, eventName, eventStatus, eventStartDateTime, eventEndDateTime);
    }

    //이벤트 조회
    public Optional<EventDto> findEvent(Long eventId){
        return eventRepository.findEvent(eventId);
    }

    // 이벤트 생성
    public Boolean createEvent(EventDto eventDto){
        return eventRepository.insertEvent(eventDto);
    }

    // 이벤트 수정
    public Boolean modifyEvent(Long eventId, EventDto eventDto){
        return eventRepository.updateEvent(eventId,eventDto);
    }

    // 이벤트 삭제
    public Boolean removeEvent(Long eventId){
        return eventRepository.deleteEvent(eventId);
    }
}
