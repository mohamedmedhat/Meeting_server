package com.example.demo.messages;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.meeting.dto.request.CreateMessageRequestDto;
import com.example.demo.user.User;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("api/v1/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    public void sendMessage(CreateMessageRequestDto messageData,
            User user) {
        messageService.sendMessage(messageData, user);
    }

}
