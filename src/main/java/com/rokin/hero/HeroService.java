package com.rokin.hero;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.kinesis.producer.KinesisProducer;
import com.rokin.aws.AwsCredentials;
import com.rokin.kinesis.KinesisService;

@Service
public class HeroService {
	
	@Autowired
	private AwsCredentials awsCredentials;
	
	@Autowired
	private KinesisProducer kinesis;

	public void sendHeroesToKinesis(List<Hero> heroes) throws UnsupportedEncodingException {
		KinesisService.putRecords("Rokin is God", "key", awsCredentials.getKinesis().getStreamName(), kinesis);
		
	}
}
