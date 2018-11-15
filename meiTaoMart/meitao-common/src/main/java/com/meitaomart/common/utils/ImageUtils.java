package com.meitaomart.common.utils;

import java.io.File;
import java.io.InputStream;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.*;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class ImageUtils {
	public static final String ACCESS_KEY = "AKIAJDWLZCL7NYPY64KQ";
	public static final String SECRET_KEY = "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";

	private static AmazonS3 s3Client;
	private static final String CLIENT_REGION = "us-east-2";
	private static final String BUCKET_NAME = "meitao-image-server";
	
	private static final String HOST_NAME = "https://s3.us-east-2.amazonaws.com";

	private static AWSCredentials credentials;

	public static String uploadImage(File file, String fileName) {
		try {
			// upload problem solved by this link:
			// https://stackoverflow.com/questions/41796355/aws-error-downloading-object-from-s3-profile-file-cannot-be-null
			// s3Client = AmazonS3ClientBuilder.standard()
			// .withRegion(clientRegion)
			// .build();

			credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
			s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
					.withRegion(CLIENT_REGION).build();

			// Upload a text string as a new object.
			
			PutObjectRequest request = new PutObjectRequest(BUCKET_NAME, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead);;
			s3Client.putObject(request);
		} catch (AmazonServiceException e) {
			// The call was transmitted successfully, but Amazon S3 couldn't
			// process
			// it, so it returned an error response.
			e.printStackTrace();
		} catch (SdkClientException e) {
			// Amazon S3 couldn't be contacted for a response, or the client
			// couldn't parse the response from Amazon S3.
			e.printStackTrace();
		}
		
		return HOST_NAME + "/" + BUCKET_NAME + "/" + fileName;
	}
}
