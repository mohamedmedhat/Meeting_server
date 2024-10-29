package com.example.demo.meeting;

import com.example.demo.meeting.dto.request.CreateMessageRequestDto;
import com.example.demo.user.User;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

    public Message toEntity(CreateMessageRequestDto messageData, User user) {
        Message message = new Message();
        message.setText(messageData.getText());
        message.setUser(user);
        return message;
    }

    public CreateMessageRequestDto toDto(Message message) {
        return new CreateMessageRequestDto(
                message.getText()
        );
    }
}
