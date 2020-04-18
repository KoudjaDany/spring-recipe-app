package com.ddf.training.springrecipeapp.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveImageFile(Long imageId, MultipartFile file);
}
