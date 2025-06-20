package com.example.demo.messages;

import org.springframework.stereotype.Service;

import com.example.demo.meeting.dto.request.CreateMessageRequestDto;
import com.example.demo.messages.dto.response.MessageResponseDto;
import com.example.demo.messages.exception.MessageNotFoundException;
import com.example.demo.user.User;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Override
    public Mono<MessageResponseDto> sendMessage(CreateMessageRequestDto messageData, User user) {
        Message message = this.messageMapper.toEntity(messageData, user);
        return this.messageRepository.save(message)
                .map(this.messageMapper::toDto);

    }

    @Override
    public Mono<Boolean> deleteMessage(String id) {
        this.findMessageById(id);
        return this.messageRepository.deleteById(id)
                .thenReturn(true)
                .onErrorResume(e -> Mono.just(false));
    }

    public Mono<MessageResponseDto> findMessageById(String id) {
        return this.messageRepository.findById(id)
                .map(this.messageMapper::toDto)
                .switchIfEmpty(Mono.error(new MessageNotFoundException(id)));

    }
}
