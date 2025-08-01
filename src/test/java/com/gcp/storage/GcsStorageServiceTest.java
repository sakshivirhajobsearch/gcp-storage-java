package com.gcp.storage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

public class GcsStorageServiceTest {

	private final GcsStorageService gcsService = new GcsStorageService();
	private final String bucketName = "your-bucket-name"; // 🔴 Replace with your actual GCS bucket name
	private final String objectName = "sample.txt";
	private final String uploadFilePath = "src/test/resources/sample.txt";
	private final String downloadFilePath = "src/test/resources/downloaded-sample.txt";

	@Test
	public void testUploadAndDownloadFile() throws Exception {
		
		// Upload
		gcsService.uploadFile(bucketName, objectName, uploadFilePath);
		// Download
		gcsService.downloadFile(bucketName, objectName, downloadFilePath);
		// Assert
		assertTrue(Files.exists(Path.of(downloadFilePath)));
	}
}