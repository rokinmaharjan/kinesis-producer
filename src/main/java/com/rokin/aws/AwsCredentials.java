package com.rokin.aws;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "aws")
@Data
public class AwsCredentials {
	private String accessKey;
	private String accessSecret;
	private Kinesis kinesis;

	@Data
	public static class Kinesis {
		private String region;
		private String streamName;
	}
}
