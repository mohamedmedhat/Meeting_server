package com.example.demo.meeting;

import com.example.demo.meeting.dto.request.CreateMeetingRequestDto;
import com.example.demo.meeting.dto.response.MeetingResponseDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MeetingService {
    Mono<MeetingResponseDto> createMeeting(CreateMeetingRequestDto meetingData);

    Flux<MeetingResponseDto> getAll(int page, int size);

    Mono<Void> deleteMeeting(String id);
}
