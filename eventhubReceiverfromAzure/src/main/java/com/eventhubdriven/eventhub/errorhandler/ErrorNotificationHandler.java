package com.eventhubdriven.eventhub.errorhandler;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.microsoft.azure.eventprocessorhost.ExceptionReceivedEventArgs;


public class ErrorNotificationHandler implements Consumer<ExceptionReceivedEventArgs> {

	private static final Logger logger = LoggerFactory.getLogger(ErrorNotificationHandler.class);
	
    @Override
    public void accept(ExceptionReceivedEventArgs t) {
    	logger.error("SAMPLE: Host " + t.getHostname() + " received general error notification during " + t.getAction() + ": " + t.getException().toString());
    }
}
