package com.example.demo.image.dto;

public record ImageResponseDto(
        String id,
        String filename,
        String filetype,
        String filePath) {
}
