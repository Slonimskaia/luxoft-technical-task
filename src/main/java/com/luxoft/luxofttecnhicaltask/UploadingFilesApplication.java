package com.luxoft.luxofttecnhicaltask;


import com.luxoft.luxofttecnhicaltask.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class UploadingFilesApplication implements CommandLineRunner{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    FileStorageService storageService;

    public static void main(String[] args) {
        SpringApplication.run(UploadingFilesApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        storageService.init();
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS ITEMS (FILE_NAME TEXT, PRIMARY_KEY TEXT, NAME TEXT,DESCRIPTION TEXT, UPDATED_TIMESTAMP TEXT, CONSTRAINT UNIQUE_FILE_NAME_AND_PRIMARY_KEY_COLUMN UNIQUE(FILE_NAME, PRIMARY_KEY));");
    }
}
