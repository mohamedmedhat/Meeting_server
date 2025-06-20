package com.example.demo.meeting;

import com.example.demo.meeting.dto.request.CreateMeetingRequestDto;
import com.example.demo.meeting.dto.request.SignalMessage;
import com.example.demo.meeting.dto.response.MeetingResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.data.repository.query.Param;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/meetings/")
public class MeetingController {
    private final SimpMessagingTemplate messagingTemplate;
    private final MeetingService meetingService;

    @PostMapping()
    public Mono<MeetingResponseDto> createMeeting(@Valid @RequestBody CreateMeetingRequestDto meetingData) {
        return this.meetingService.createMeeting(meetingData);
    }

    @GetMapping
    public Flux<MeetingResponseDto> getAll(@Param  ("page") int page, @Param("size") int size) {
        return this.meetingService.getAll(page, size);
    }

    @DeleteMapping("{id}")
    public void deleteMeeting(@PathVariable String id) {
        this.meetingService.deleteMeeting(id);
    }

    @MessageMapping("/signal/{roomId}")
    public void relaySignal(@DestinationVariable String roomId, @Payload SignalMessage signal) {
        messagingTemplate.convertAndSend("/topic/signal/" + roomId, signal);
    }
}
