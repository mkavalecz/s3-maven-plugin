package com.software.crafter.core.common;

import java.io.File;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.software.crafter.core.artifact.Artifact;
import com.software.crafter.core.artifact.Download;
import com.software.crafter.core.artifact.Upload;

/**
 * @author Roman Zimnik
 * @version 1.0.0
 */
public class GoalExecutorImpl implements GoalExecutor {

	private final AmazonS3Client s3Client;

	public GoalExecutorImpl(AmazonS3Client s3Client) {
		this.s3Client = s3Client;
	}

	@Override
	public void executeGoal(Artifact artifact) {
		if (artifact instanceof Download) {
			executeDownload(s3Client, (Download) artifact);
		} else if (artifact instanceof Upload) {
			executeUpload(s3Client, (Upload) artifact);
		}
	}

	private void executeDownload(AmazonS3Client s3Client, Download download) {
		try {
			s3Client.getObject(
					new GetObjectRequest(
							download.getBucketName(),
							download.getFileName()
					),
					new File(download.getPath())
			);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void executeUpload(AmazonS3Client s3Client, Upload upload) {
		try {
			s3Client.putObject(
					upload.getBucketName(),
					upload.getFileName(),
					new File(upload.getPath())
			);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
