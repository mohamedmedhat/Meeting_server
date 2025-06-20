package com.example.demo.meeting.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignalMessage {
    public String type;
    public String sdp;
    public Object candidate;
    public String senderId;
}