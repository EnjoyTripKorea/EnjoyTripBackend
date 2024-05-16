package com.example.EnjoyTripBackend.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.EnjoyTripBackend.exception.EnjoyTripException;
import com.example.EnjoyTripBackend.exception.ErrorCode;
import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@NoArgsConstructor
public class S3Service {
    private AmazonS3 s3Client;

    @Value("${spring.cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${spring.cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${spring.cloud.aws.region.static}")
    private String region;

    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }

    public String upload(MultipartFile multipartFile) {
        String uuid = UUID.randomUUID().toString();
        String originalFilename = multipartFile.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        try {
            s3Client.putObject(bucket, uuid+originalFilename, multipartFile.getInputStream(), metadata);
        } catch (IOException e) {
            throw new EnjoyTripException(ErrorCode.FILE_UPLOAD_NOT_WORKING, e.getMessage());
        }
        return s3Client.getUrl(bucket, uuid+originalFilename).toString();
    }
}