package com.rokin.kinesis;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import org.springframework.stereotype.Service;

import com.amazonaws.services.kinesis.producer.KinesisProducer;
import com.amazonaws.services.kinesis.producer.UserRecordResult;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

@Service
public class KinesisService {

	public static void putRecords(String data, String partitionKey, String streamName, KinesisProducer kinesis) throws UnsupportedEncodingException {
		FutureCallback<UserRecordResult> myCallback = new FutureCallback<UserRecordResult>() {
			@Override
			public void onFailure(Throwable t) {
				System.err.println("Exception occured. " + t);
			};

			@Override
			public void onSuccess(UserRecordResult result) {
				System.err.println("Success. Result: " + result);
			};
		};

		for (int i = 0; i < 100; ++i) {
			ByteBuffer records = ByteBuffer.wrap(data.getBytes("UTF-8"));
			ListenableFuture<UserRecordResult> future = kinesis
					.addUserRecord(streamName, partitionKey, records);
			// If the Future is complete by the time we call addCallback, the callback will
			// be invoked immediately.
			Futures.addCallback(future, myCallback);
		}
	}

}
