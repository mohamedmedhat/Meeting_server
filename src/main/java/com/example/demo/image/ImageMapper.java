package com.example.demo.image;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.image.dto.ImageResponseDto;

import java.io.File;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class ImageMapper {
    @Value("${IMAGE_UPLOAD_DIR}")
    private String uploadDir;

    public Image toEntity(MultipartFile file) throws IOException {
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String filePath = uploadDir + file.getOriginalFilename();
        file.transferTo(new File(filePath));
        Image image = new Image();
        image.setFilename(file.getOriginalFilename());
        image.setFiletype(file.getContentType());
        image.setFilePath("/images/" + file.getOriginalFilename());
        return image;
    }

    public Mono<ImageResponseDto> toResponseDto(Mono<Image> imageMono) {
        return imageMono.map(image -> new ImageResponseDto(
                image.getId(),
                image.getFilename(),
                image.getFiletype(),
                image.getFilePath()));
    }
}
