package com.example.demo.image;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IImageService {

    Image saveImage(MultipartFile file) throws IOException;
    Image findImageById(String id);
    void deleteImage(Image image);
}
