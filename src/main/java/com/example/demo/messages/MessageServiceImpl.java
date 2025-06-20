package com.example.demo.messages;

import org.springframework.stereotype.Service;

import com.example.demo.meeting.dto.request.CreateMessageRequestDto;
import com.example.demo.messages.dto.response.MessageResponseDto;
import com.example.demo.user.User;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    @Override
    public Mono<MessageResponseDto> sendMessage(CreateMessageRequestDto messageData, User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendMessage'");
    }

    @Override
    public Mono<Boolean> deleteMessage(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteMessage'");
    }

    @Override
    public Mono<String> handleAsking() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleAsking'");
    }

    @Override
    public Mono<Void> handleAnswering() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleAnswering'");
    }

}
