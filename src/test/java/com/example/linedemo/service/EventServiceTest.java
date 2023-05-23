package com.example.linedemo.service;

import com.example.linedemo.constant.EventStatus;
import com.example.linedemo.dto.EventDto;
import com.example.linedemo.repository.EventRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest
class EventServiceTest {


    @InjectMocks private EventService sut;   //System Under Test
    @Mock private EventRepository eventRepository;
    //Mockito가 respository를 sut에 주입해준다.


    @DisplayName("검색 조건 없이  이벤트 검색하면, 전체 검색 결과를 가져온다.")
    @Test
    void givenNothing_whenSearchingEvents_thenReturnEventList(){
        // Given
        given(eventRepository.findEvents(null,null,null,null,null))
                .willReturn(List.of(
                        createEventDto(1L,"오전 운동", true),
                        createEventDto(1L,"오후 운동", false)
                ));
        // When
        List<EventDto> list = sut.getEvents(null,null,null,null,null);

        // Then
        assertThat(list).hasSize(2);
        verify(eventRepository).findEvents(null,null,null,null,null);
    }

    @DisplayName("검색 조건에 맞춰  이벤트 검색하면, 전체 검색 결과를 가져온다.")
    @Test
    void givenParams_whenSearchingEvents_thenReturnEventList(){
        // Given
        Long placeId = 1L;
        String eventName = "오전 운동";
        EventStatus eventStatus = EventStatus.OPENED;
        LocalDateTime eventStartDateTime = LocalDateTime.of(2021,1,1,0,0,0);
        LocalDateTime eventEndDateTime = LocalDateTime.of(2021,1,2,0,0,0);
        Integer currentMemberOfPeople;
        given(eventRepository.findEvents(placeId,eventName,eventStatus,eventStartDateTime,eventEndDateTime))
                .willReturn(List.of(
                        createEventDto(1L,"오전 운동", eventStatus,eventStartDateTime,eventEndDateTime)
                ));

        // When
        List<EventDto> list = sut.getEvents(placeId,eventName,eventStatus,eventStartDateTime,eventEndDateTime);

        // Then
        assertThat(list).hasSize(1)
                .allSatisfy(event -> {
                    assertThat(event)
                            .hasFieldOrPropertyWithValue("placeId",placeId)
                            .hasFieldOrPropertyWithValue("eventName",eventName)
                            .hasFieldOrPropertyWithValue("eventStatus",eventStatus);

                    assertThat(event.eventStartDateTime().isAfter(eventStartDateTime));
                    assertThat(event.eventEndDateTime().isBefore(eventEndDateTime));
                });
        verify(eventRepository).findEvents(placeId,eventName,eventStatus,eventStartDateTime,eventEndDateTime);
    }

    @DisplayName("이벤트 ID로 존재하는 이벤트를 조회하면 해당 이벤트의 결과를 가져온다.")
    @Test
    void givenEventId_whenSearchingEvents_thenReturnEventList(){
        // Given
        Long eventId = 1L;
        EventDto eventDto = createEventDto(eventId, "오전 운동", true);
        given(eventRepository.findEvent(eventId))
                .willReturn(Optional.of(createEventDto(eventId, "오전 운동", true)));
        // When
        Optional<EventDto> result = sut.findEvent(eventId);

        // Then
        assertThat(result).hasValue(eventDto);
        verify(eventRepository).findEvent(eventId);
    }

    @DisplayName("이벤트 ID를 주면 해당 빈 정보를 가져온다.")
    @Test
    void givenEventId_whenSearching_thenReturnEmpty(){
        // Given
        Long eventId = 2L;
        given(eventRepository.findEvent(eventId))
                .willReturn(Optional.empty());

        // When
        Optional<EventDto> result = sut.findEvent(eventId);

        // Then
        assertThat(result).isEmpty();
        verify(eventRepository).findEvent(eventId);
    }
    
    // 생성
    @DisplayName("이벤트 정보를 주면 해당 이벤트를 생성하고 결과를 true로 보여준다.")
    @Test
    void givenEvent_whenCreate_thenReturnTrue(){
        // Given
        Long eventId = 1L;
        EventDto eventDto = createEventDto(eventId, "오후 운동", false);
        given(eventRepository.insertEvent(eventDto))
                .willReturn(true);
        // When
        boolean result = sut.createEvent(eventDto);

        // Then
        assertThat(result).isTrue();
        then(eventRepository).should().insertEvent(eventDto);
    }

    @DisplayName("이벤트 정보를 주지 않으면,생성을 중지하고 결과를 false로 보여준다.")
    @Test
    void givenNothing_whenCreate_thenReturnFalse(){
        // Given
        given(eventRepository.insertEvent(null))
                .willReturn(false);
        // When
        boolean result = sut.createEvent(null);

        // Then
        assertThat(result).isFalse();
        then(eventRepository).should().insertEvent(null);
    }

    @DisplayName("이벤트 ID를 주면 해당 이벤트 정보를 변경하고 결과를 true로 보여준다.")
    @Test
    void givenEventId_whenModify_thenReturnTrue(){
        // Given
        Long eventId = 1L;
        EventDto eventDto = createEventDto(eventId, "오후 운동", false);
        given(eventRepository.updateEvent(eventId,eventDto))
                .willReturn(true);

        // When
        boolean result = sut.modifyEvent(eventId,eventDto);

        // Then
        assertThat(result).isTrue();
        then(eventRepository).should().updateEvent(eventId,eventDto);
    }

    @DisplayName("이벤트 ID를 주지 않으면 이벤트 정보 변경을 중단하고 결과를 false 로 보여준다.")
    @Test
    void givenNotEventId_whenStopModify_thenReturnFalse(){
        // Given
        EventDto eventDto = createEventDto(1L, "오후 운동", false);
        given(eventRepository.updateEvent(null,eventDto))
                .willReturn(false);
        // When
        boolean result = sut.modifyEvent(null,eventDto);

        // Then
        assertThat(result).isFalse();
        then(eventRepository).should().updateEvent(null,eventDto);
    }

    @DisplayName("이벤트 ID는 주었지만 변경할 정보를 주지 않을 경우, 이벤트 정보 변경을 중단하고 결과를 false 로 보여준다.")
    @Test
    void givenOnlyEventId_whenStopModify_thenReturnFalse(){
        // Given
        Long eventId = 1L;
        given(eventRepository.updateEvent(eventId,null))
                .willReturn(false);

        // When
        boolean result = sut.modifyEvent(eventId,null);

        // Then
        assertThat(result).isFalse();
        then(eventRepository).should().updateEvent(eventId,null);
    }

    @DisplayName("이벤트 ID를 주면 이벤트 정보 삭제하고 결과를 true 로 보여준다.")
    @Test
    void givenEventId_whenDelete_thenReturnTrue(){
        // Given
        Long eventId = 1L;
        given(eventRepository.deleteEvent(eventId))
                .willReturn(true);

        // When
        boolean result = sut.removeEvent(eventId);

        // Then
        assertThat(result).isTrue();
        then(eventRepository).should().deleteEvent(eventId);
    }

    @DisplayName("이벤트 ID를 주지 않으면 이벤트 정보 삭제를 중지하고 결과를 false 로 보여준다.")
    @Test
    void givenNotEventId_whenStopDelete_thenReturnFalse(){
        // Given
        given(eventRepository.deleteEvent(null))
                .willReturn(false);

        // When
        boolean result = sut.removeEvent(null);

        // Then
        assertThat(result).isFalse();
        then(eventRepository).should().deleteEvent(null);
    }

    private EventDto createEventDto(Long placeId, String eventName, boolean isMorning) {
        String hourStart = isMorning ? "09" : "13";
        String hourEnd = isMorning ? "12" : "16";
        return createEventDto(placeId, eventName, EventStatus.OPENED,
                LocalDateTime.parse("2021-01-01T%s:00:00".formatted(hourStart)),
                LocalDateTime.parse("2021-01-01T%s:00:00".formatted(hourEnd))
        );
    }

    private EventDto createEventDto(Long placeId, String eventName, EventStatus eventStatus,
                                    LocalDateTime parse, LocalDateTime parse1) {
        return EventDto.of(placeId,
                eventName,
                eventStatus,
                parse,
                parse1,
                0,
                24,
                "마스크를 꼭 착용하세요.",
                LocalDateTime.now(),
                LocalDateTime.now());
    }

}