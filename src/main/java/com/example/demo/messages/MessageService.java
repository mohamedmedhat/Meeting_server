package com.example.demo.messages;

import com.example.demo.meeting.dto.request.CreateMessageRequestDto;
import com.example.demo.messages.dto.response.MessageResponseDto;
import com.example.demo.user.User;

import reactor.core.publisher.Mono;

public interface MessageService {
    Mono<MessageResponseDto> sendMessage(CreateMessageRequestDto messageData, User user);

    Mono<Boolean> deleteMessage(String id);
}
