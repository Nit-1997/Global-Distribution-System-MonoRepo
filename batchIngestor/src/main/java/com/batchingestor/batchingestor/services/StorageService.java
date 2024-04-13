package com.batchingestor.batchingestor.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * Interface to store files , can be extended to Local , GCP , AWS_S3
 */
public interface StorageService {

    /**
     * Stores the file to local-disk / cloud depending on implementation
     * @param file csv file to be stored
     */
    String store(MultipartFile file);
}
