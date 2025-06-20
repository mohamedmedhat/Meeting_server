package com.example.demo.image;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.image.dto.ImageResponseDto;
import com.example.demo.image.exception.ImageNotFoundException;

import java.io.File;
import java.io.IOException;

@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    @Value("${IMAGE_UPLOAD_DIR}")
    private String uploadDir;

    public Image toEntity(MultipartFile file, String uploadDir) throws IOException {
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

    @Override
    public Mono<ImageResponseDto> saveImage(MultipartFile file) throws IOException {
        Image image = this.toEntity(file, uploadDir);
        return this.imageRepository.save(image)
                .map(this.imageMapper::toResponseDto);
    }

    @Override
    public Mono<ImageResponseDto> findImageById(String id) {
        return this.imageRepository.findById(id)
                .switchIfEmpty(Mono.error(new ImageNotFoundException(id)))
                .map(this.imageMapper::toResponseDto);
    }

    @Override
    public Mono<Void> deleteImage(Image image) {
        return this.findImageById(image.getId())
                .flatMap(imageResponseDto -> this.imageRepository.delete(image));
    }
}
