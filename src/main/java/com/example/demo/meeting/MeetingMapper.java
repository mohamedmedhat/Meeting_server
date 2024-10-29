package com.example.demo.meeting;

import com.example.demo.meeting.dto.request.CreateMeetingRequestDto;
import org.springframework.stereotype.Component;

@Component
public class MeetingMapper {

    public Meeting toEntity(CreateMeetingRequestDto meetingData) {
        Meeting meeting = new Meeting();
        meeting.setTitle(meetingData.getTitle());
        meeting.setHostEmail(meetingData.getHostEmail());
        meeting.setMeetingLink(meetingData.getMeetingLink());
        meeting.setStartTime(meetingData.getStartTime());
        meeting.setEndTime(meetingData.getEndTime());
        return meeting;
    }

    public CreateMeetingRequestDto toDto(Meeting meeting) {
        return new CreateMeetingRequestDto(
                meeting.getTitle(),
                meeting.getHostEmail(),
                meeting.getMeetingLink(),
                meeting.getStartTime(),
                meeting.getEndTime()
        );
    }
}
