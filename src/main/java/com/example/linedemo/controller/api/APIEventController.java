package com.example.linedemo.controller.api;

import com.example.linedemo.constant.EventStatus;
import com.example.linedemo.dto.response.APIDataResponse;
import com.example.linedemo.dto.response.EventResponse;
import com.example.linedemo.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Validated
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class APIEventController {
    private final EventService eventService;

    @GetMapping("/events")
    public APIDataResponse<List<EventResponse>> getEvents(
            @Positive Long placeId,
            @Size(min = 2) String eventName,
            EventStatus eventStatus,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime eventStartDateTime,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime eventEndDateTime
            ){

        List<EventResponse> response = eventService.
                getEvents(placeId, eventName, eventStatus, eventStartDateTime, eventEndDateTime)
                .stream().map(EventResponse::from).toList();
        return APIDataResponse.of(response);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/evnets")
    public Boolean createEvent(){
        return false;
    }

    @GetMapping("/events/{eventId}")
    public String getEvent(
            @PathVariable
            Integer eventId
    ){
        return String.format("Event : %d",eventId);
    }

    @PutMapping("/events/{eventId}")
    public Boolean modifyEvent(
            @PathVariable
            Integer eventId
    ){
        return true;
    }

    @DeleteMapping("/events/{eventId}")
    public Boolean removeEvent(
            @PathVariable
            Integer eventId
    ){
        return true;
    }
}
