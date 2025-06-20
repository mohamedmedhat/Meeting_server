package com.example.demo.messages.dto.response;

public record MessageResponseDto(
        String id,
        String text,
        String userId,
        String userName,
        String userEmail,
        String meetingId,
        String createdAt,
        String updatedAt
) {
}
