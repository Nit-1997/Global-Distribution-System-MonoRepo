package com.batchingestor.batchingestor.services;

import com.batchingestor.batchingestor.customExceptions.StorageException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

@Service
@Slf4j
@Qualifier("GCP")
public class GCSFileStorageService implements StorageService {
    public final String secret  = "/Users/nitinbhat/Downloads/Global-Distribution-System-MonoRepo/batchIngestor/src/main/resources/gcp_creds.json";
    public final String projectID = "my-project-1509111052665";
    public final String bucketID = "gds_gcs_store";

    @Override
    public String store(MultipartFile file) {
        try{
            log.info("Storing to GCP bucket");
            // in the storage bucket , create a new file
            // write to the file by streaming data from local file
            Storage storage = StorageOptions.newBuilder()
                    .setProjectId(projectID)
                    .setCredentials(GoogleCredentials.fromStream(new FileInputStream(secret)))
                    .build()
                    .getService();
            BlobId blobId = BlobId.of(bucketID, "dump2.csv");
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
            Blob blob = storage.create(blobInfo, file.getBytes());
            log.info("File uploaded to GCS successfully");
            log.info("File stored to: "+blob.getMediaLink());
            return blob.getMediaLink();
        }catch(IOException e) {
            log.error(e.getMessage());
            throw new StorageException("Failed to store file.", e);
        }
    }
}
