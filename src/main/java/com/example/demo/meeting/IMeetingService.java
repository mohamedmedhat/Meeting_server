package com.example.demo.meeting;

import com.example.demo.meeting.dto.request.CreateMeetingRequestDto;
import com.example.demo.meeting.dto.request.CreateMessageRequestDto;
import com.example.demo.user.User;

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
