package com.example.demo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class CreateMeetingRequestDto {
    @NotBlank(message = "title is mandatory")
    @Min(value = 4, message = "title is 4 min characters")
    private String title;

    @NotBlank(message = "meetingLink is mandatory")
    private String meetingLink;

    @NotBlank(message = "hostEmail is mandatory")
    @Email(message = "hostEmail should be in right format")
    private String hostEmail;

    @NotBlank(message = "startTime is mandatory")
    private LocalDateTime startTime;

    @NotBlank(message = "endTime is mandatory")
    private LocalDateTime endTime;
}
