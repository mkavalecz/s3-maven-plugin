package com.software.crafter.core.handler;

import java.util.List;

import org.apache.maven.plugin.logging.Log;

import com.amazonaws.services.s3.AmazonS3Client;
import com.software.crafter.core.artifact.Download;
import com.software.crafter.core.common.GoalExecutor;
import com.software.crafter.core.common.GoalExecutorImpl;
import com.software.crafter.util.AWSClientFactory;

/**
 * @author Roman Zimnik
 * @version 1.0.0
 */
public class DownloadHandler {

	private final Log log;

	private AmazonS3Client s3Client;

	public DownloadHandler(String accesskey, String secretkey, String region, Log log) {
		this.log = log;
		init(accesskey, secretkey, region);
	}

	private void init(String accesskey, String secretkey, String region) {
		this.s3Client = AWSClientFactory.createAmazonS3Client(accesskey, secretkey, region);
	}

	public void downloadAllObjects(List<Download> downloads) {
		downloads.forEach(d -> {
			this.log.info(String.format("Download %s -> %s/%s", d.getPath(), d.getBucketName(), d.getFileName()));
			this.downloadObjectFromS3(d);
		});
	}

	private void downloadObjectFromS3(Download download) {
		GoalExecutor executor = new GoalExecutorImpl(this.s3Client);
		executor.executeGoal(download);
	}
}
