package com.example.demo.meeting.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateMessageRequestDto {
    @NotBlank(message = "text is mandatory")
    private String text;
}
