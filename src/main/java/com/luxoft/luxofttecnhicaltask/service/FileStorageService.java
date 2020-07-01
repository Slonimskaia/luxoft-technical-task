package com.luxoft.luxofttecnhicaltask.service;


import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    void store(MultipartFile file);
}
