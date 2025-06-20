package com.example.demo.image;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.image.dto.ImageResponseDto;
import com.example.demo.image.exception.ImageNotFoundException;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    @Override
    public Mono<ImageResponseDto> saveImage(MultipartFile file) throws IOException {
        Image image = this.imageMapper.toEntity(file);
        Mono<Image> savedImage = this.imageRepository.save(image);
        return this.imageMapper.toResponseDto(savedImage);
    }

    @Override
    public Mono<ImageResponseDto> findImageById(String id) {
        Mono<Image> image = this.imageRepository.findById(id)
                .switchIfEmpty(Mono.error(new ImageNotFoundException(id)));
        return this.imageMapper.toResponseDto(image);
    }

    @Override
    public Mono<Void> deleteImage(Image image) {
        return this.findImageById(image.getId())
                .flatMap(imageResponseDto -> this.imageRepository.delete(image));
    }
}
