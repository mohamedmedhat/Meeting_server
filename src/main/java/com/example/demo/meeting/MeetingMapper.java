package com.example.demo.meeting;

import com.example.demo.meeting.dto.request.CreateMeetingRequestDto;
import com.example.demo.meeting.dto.response.MeetingResponseDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MeetingMapper {

    public Meeting toEntity(CreateMeetingRequestDto meetingData);

    public CreateMeetingRequestDto toDto(Meeting meeting);

    public MeetingResponseDto toResponseDto(Meeting meeting);
}
