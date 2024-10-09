package com.example.demo.service.meeting;

import com.example.demo.model.Meeting;

import java.util.List;

public interface IMeetingService {
    Meeting createMeeting();

    String sendMessage(String message);

    List<Meeting> getAll();

    String handleAsking();

    void handleAnswering();

    void deleteMeeting(Long id);
}
