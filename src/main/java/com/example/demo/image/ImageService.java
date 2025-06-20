package com.example.demo.image;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.image.dto.ImageResponseDto;

import reactor.core.publisher.Mono;

import java.io.IOException;

public interface ImageService {

    Mono<ImageResponseDto> saveImage(MultipartFile file) throws IOException;
    Mono<ImageResponseDto> findImageById(String id);
    Mono<Void> deleteImage(Image image);
}
