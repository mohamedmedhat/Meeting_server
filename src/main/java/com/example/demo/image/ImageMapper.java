package com.example.demo.image;

import org.mapstruct.Mapper;
import com.example.demo.image.dto.ImageResponseDto;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    public ImageResponseDto toResponseDto(Image image);
}
