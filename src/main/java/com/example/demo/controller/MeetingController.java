package com.example.demo.controller;

import com.example.demo.dto.request.CreateMeetingRequestDto;
import com.example.demo.dto.request.CreateMessageRequestDto;
import com.example.demo.model.Meeting;
import com.example.demo.model.Message;
import com.example.demo.model.User;
import com.example.demo.security.CurrentUser;
import com.example.demo.service.meeting.IMeetingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/meetings/")
public class MeetingController {
    private final IMeetingService meetingService;

    @PostMapping()
    public Meeting createMeeting(@Valid @RequestBody CreateMeetingRequestDto meetingData) {
        return this.meetingService.createMeeting(meetingData);
    }

    @GetMapping
    public List<Meeting> getAll() {
        return this.meetingService.getAll();
    }

    @DeleteMapping("{id}")
    public void deleteMeeting(String id) {
        this.meetingService.deleteMeeting(id);
    }

    @MessageMapping("/ask")
    @SendTo("/topic/answer")
    public String asking(String ask) {
        return this.meetingService.handleAsking();
    }

    @MessageMapping("/answer")
    public void answer() {
        this.meetingService.handleAnswering();
    }

    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    public Message sendMessage(@Valid @RequestBody CreateMessageRequestDto messageData, @CurrentUser User user) {
        return this.meetingService.sendMessage(messageData, user);
    }
}
