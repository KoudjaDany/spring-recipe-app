package com.ddf.training.springrecipeapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@Transactional
public class ImageServiceImpl implements ImageService {

    @Override
    public void saveImageFile(Long imageId, MultipartFile file) {

        log.debug("Received a file");
    }
}
