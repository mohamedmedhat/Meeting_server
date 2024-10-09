package com.example.demo.service.meeting;

import com.example.demo.model.Meeting;
import com.example.demo.repository.MeetingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingService implements IMeetingService {
    private final MeetingRepository meetingRepository;

    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    @Override
    public Meeting createMeeting() {
        return null;
    }

    @Override
    public String sendMessage(String message) {
        return "";
    }

    @Override
    public List<Meeting> getAll() {
        return List.of();
    }

    @Override
    public String handleAsking() {
        return "";
    }

    @Override
    public void handleAnswering() {

    }

    @Override
    public void deleteMeeting(Long id) {
        Meeting meeting = this.meetingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meeting Not found"));
        this.meetingRepository.delete(meeting);
    }
}
