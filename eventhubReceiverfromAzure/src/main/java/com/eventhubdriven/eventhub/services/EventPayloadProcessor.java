package com.eventhubdriven.eventhub.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eventhubdriven.eventhub.datamodel.EventPayload;
import com.eventhubdriven.eventhub.host.EventProcessorHostService;

@Service
class EventPayloadProcessor {
    private Logger log = LoggerFactory.getLogger(EventProcessorHostService.class);

    void process(EventPayload eventPayload) {
        log.info("Hello! My name is {} and my favorite food is {}", eventPayload.getName(), eventPayload.getMessage());
    }
}
