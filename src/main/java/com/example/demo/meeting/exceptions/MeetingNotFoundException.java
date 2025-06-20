package com.example.demo.meeting.exceptions;

import org.springframework.http.HttpStatus;

public class MeetingNotFoundException extends RuntimeException {
    protected final HttpStatus status;

    public MeetingNotFoundException(String id) {
        super("Meeting with ID " + id + " not found.");
        this.status = HttpStatus.NOT_FOUND;
    }

}
