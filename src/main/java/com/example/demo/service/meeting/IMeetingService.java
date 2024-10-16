package com.example.demo.service.meeting;

import com.example.demo.dto.request.CreateMeetingRequestDto;
import com.example.demo.dto.request.CreateMessageRequestDto;
import com.example.demo.model.Meeting;
import com.example.demo.model.Message;
import com.example.demo.model.User;

import java.util.List;

public interface IMeetingService {
    Meeting createMeeting(CreateMeetingRequestDto meetingData);

    Message sendMessage(CreateMessageRequestDto messageData, User user);

    Boolean deleteMessage(String id);

    List<Meeting> getAll();

    String handleAsking();

    void handleAnswering();

    void deleteMeeting(String id);
}
