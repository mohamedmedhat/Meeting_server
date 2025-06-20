package com.example.demo.messages;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.meeting.dto.request.CreateMessageRequestDto;
import com.example.demo.user.User;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void sendMessage(CreateMessageRequestDto messageData,
            User user) {
        messageService.sendMessage(messageData, user)
                .doOnNext(response -> messagingTemplate.convertAndSend("/topic/chat", response))
                .subscribe();
    }

}
