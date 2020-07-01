package com.luxoft.luxofttecnhicaltask.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    private String filesStorage = "upload-directory";

    public String getFilesStorage() {
        return filesStorage;
    }

    public void setFilesStorage(String filesStorage) {
        this.filesStorage = filesStorage;
    }
}
