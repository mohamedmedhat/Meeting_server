package com.example.demo.meeting.dto.response;

import java.time.LocalDateTime;

public record MeetingResponseDto(
        String id,
        String title,
        String meetingLink,
        String hostEmail,
        LocalDateTime startTime,
        LocalDateTime endTime) {
}
