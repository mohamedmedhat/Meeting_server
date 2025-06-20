package com.example.demo.meeting;

import com.example.demo.meeting.dto.request.CreateMeetingRequestDto;
import com.example.demo.meeting.dto.response.MeetingResponseDto;
import com.example.demo.meeting.exceptions.MeetingNotFoundException;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class MeetingServiceImpl implements MeetingService {
    private final MeetingRepository meetingRepository;
    private final MeetingMapper meetingMapper;

    @Override
    public Mono<MeetingResponseDto> createMeeting(CreateMeetingRequestDto meetingData) {
        Meeting meeting = this.meetingMapper.toEntity(meetingData);
        return this.meetingRepository.save(meeting)
                .map(this.meetingMapper::toResponseDto);
    }

    @Override
    public Flux<MeetingResponseDto> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.meetingRepository.findAllBy(pageable)
                .map(this.meetingMapper::toResponseDto)
                .switchIfEmpty(Flux.error(new MeetingNotFoundException("all")));
    }

    @Override
    public Mono<Void> deleteMeeting(String id) {
        return this.meetingRepository.findById(id)
                .switchIfEmpty(Mono.error(new MeetingNotFoundException(id)))
                .flatMap(this.meetingRepository::delete);
    }
}
