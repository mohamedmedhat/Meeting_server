package com.example.demo.messages;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.meeting.dto.request.CreateMessageRequestDto;
import com.example.demo.messages.dto.response.MessageResponseDto;
import com.example.demo.security.CurrentUser;
import com.example.demo.user.User;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    public Mono<MessageResponseDto> sendMessage(@Valid @RequestBody CreateMessageRequestDto messageData,
            @CurrentUser User user) {
        return this.messageService.sendMessage(messageData, user);
    }

    @MessageMapping("/ask")
    @SendTo("/topic/answer")
    public Mono<String> asking(String ask) {
        return this.messageService.handleAsking();
    }

    @MessageMapping("/answer")
    public Mono<Void> answer() {
        return this.messageService.handleAnswering();
    }

}
