package com.example.demo.messages;

import com.example.demo.meeting.dto.request.CreateMessageRequestDto;
import com.example.demo.messages.dto.response.MessageResponseDto;
import com.example.demo.user.User;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    public Message toEntity(CreateMessageRequestDto messageData, User user);

    public MessageResponseDto toDto(Message message);
}
