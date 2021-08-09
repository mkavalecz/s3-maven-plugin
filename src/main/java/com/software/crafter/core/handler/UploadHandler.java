package com.software.crafter.core.handler;

import java.util.List;

import org.apache.maven.plugin.logging.Log;

import com.amazonaws.services.s3.AmazonS3Client;
import com.software.crafter.core.artifact.Upload;
import com.software.crafter.core.common.GoalExecutor;
import com.software.crafter.core.common.GoalExecutorImpl;
import com.software.crafter.util.AWSClientFactory;

/**
 *
 * @author Roman Zimnik
 * @version 1.0.0
 *
 */
public class UploadHandler {

    private final Log log;

    private AmazonS3Client s3Client;

    public UploadHandler(String accesskey, String secretkey, Log log) {
        this.log = log;
        init(accesskey, secretkey);
    }

    private void init(String accesskey, String secretkey) {
        this.s3Client = AWSClientFactory.createAmazonS3Client(accesskey, secretkey);
    }

    public void uploadAllObjects(List<Upload> uploads) {

        uploads.forEach(u -> this.log.info(
                "Object URI: " + u.getPath()
                        + u.getFileName()));

        uploads.forEach(this::uploadObjectFromS3);
    }

    private void uploadObjectFromS3(Upload upload) {

        GoalExecutor executor = new GoalExecutorImpl(this.s3Client);
        executor.executeGoal(upload);
    }
}
