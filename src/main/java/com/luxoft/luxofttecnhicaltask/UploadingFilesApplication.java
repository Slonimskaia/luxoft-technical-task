package com.luxoft.luxofttecnhicaltask;


import com.luxoft.luxofttecnhicaltask.service.FileStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UploadingFilesApplication {

    public static void main(String[] args) {
        SpringApplication.run(UploadingFilesApplication.class, args);
    }

    @Bean
    CommandLineRunner init(FileStorageService storageService) {
        return (args) -> {
            storageService.init();
        };
    }
}
