package com.gcp.storage;

import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class GcsStorageService {

	private final Storage storage;

	public GcsStorageService() {
		this.storage = StorageOptions.getDefaultInstance().getService();
	}

	public void uploadFile(String bucketName, String objectName, String filePath) throws Exception {
		
		byte[] bytes = Files.readAllBytes(Paths.get(filePath));
		BlobId blobId = BlobId.of(bucketName, objectName);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
		storage.create(blobInfo, bytes);
		System.out.println("File uploaded to " + bucketName + "/" + objectName);
	}

	public void downloadFile(String bucketName, String objectName, String destinationPath) throws Exception {
		
		Blob blob = storage.get(BlobId.of(bucketName, objectName));
		if (blob == null) {
			throw new Exception("Object not found in bucket");
		}
		blob.downloadTo(Paths.get(destinationPath));
		System.out.println("File downloaded to " + destinationPath);
	}
}