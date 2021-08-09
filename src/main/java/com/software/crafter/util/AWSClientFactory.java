package com.software.crafter.util;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 * @author Roman Zimnik
 * @version 1.0.0
 */
public class AWSClientFactory {

	/**
	 * @param accessKey individual accessKey of your s3 platform.
	 * @param secretKey individual secretKey of your s3 platform.
	 *                  Both need full read and write access.
	 * @param region    region of your s3 platform.
	 * @return
	 */
	public static AmazonS3Client createAmazonS3Client(String accessKey, String secretKey, String region) {

		BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);

		return (AmazonS3Client) AmazonS3ClientBuilder
				.standard()
				.withCredentials(credentialsProvider)
				.withRegion(region)
				.build();
	}
}
