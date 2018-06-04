package com.rokin.aws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.kinesis.producer.KinesisProducer;
import com.amazonaws.services.kinesis.producer.KinesisProducerConfiguration;

@Configuration
public class AwsConfig {

	@Autowired
	private AwsCredentials awsCredential;

	public AWSCredentials getAwsCredentials() {
		return new BasicAWSCredentials(awsCredential.getAccessKey(), awsCredential.getAccessSecret());
	}

	@Bean
	public KinesisProducer kinesisProducer() {
		KinesisProducerConfiguration config = new KinesisProducerConfiguration()
				.setCredentialsProvider(new AWSStaticCredentialsProvider(getAwsCredentials()))
				.setRegion(awsCredential.getKinesis().getRegion());

		return new KinesisProducer(config);
	}
}
