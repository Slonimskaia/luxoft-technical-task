package com.luxoft.luxofttecnhicaltask.storage.exception;

public class StorageFileNotFoundException extends StorageException {

    StorageFileNotFoundException(String message) {
        super(message);
    }

    StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
