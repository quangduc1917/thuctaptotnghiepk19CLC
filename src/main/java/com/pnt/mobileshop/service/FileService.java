package com.pnt.mobileshop.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    boolean saveFile(MultipartFile multipartFile);
}
