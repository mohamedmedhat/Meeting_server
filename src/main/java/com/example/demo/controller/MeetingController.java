package com.example.demo.controller;

import com.example.demo.model.Meeting;
import com.example.demo.service.meeting.MeetingService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/meetings/")
public class MeetingController {
    private final MeetingService meetingService;

    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @PostMapping()
    public Meeting createMeeting() {
        return this.meetingService.createMeeting();
    }

    @GetMapping
    public List<Meeting> getAll() {
        return this.meetingService.getAll();
    }

    @DeleteMapping("{id}")
    public void deleteMeeting(Long id) {
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
    public String sendMessage(String message) {
        return this.meetingService.sendMessage(message);
    }
}
