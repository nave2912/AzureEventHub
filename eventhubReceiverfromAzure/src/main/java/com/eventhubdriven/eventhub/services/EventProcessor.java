package com.eventhubdriven.eventhub.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import com.eventhubdriven.eventhub.datamodel.EventPayload;
import com.eventhubdriven.eventhub.host.EventProcessorHostService;
import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventprocessorhost.CloseReason;
import com.microsoft.azure.eventprocessorhost.IEventProcessor;
import com.microsoft.azure.eventprocessorhost.PartitionContext;

@Service
public class EventProcessor implements IEventProcessor {

    private EventPayloadProcessor eventPayloadProcessor;
    private Logger log = LoggerFactory.getLogger(EventProcessorHostService.class);

    @Override
    public void onOpen(PartitionContext partitionContext) {
        eventPayloadProcessor = new EventPayloadProcessor();
    }

    @Override
    public void onClose(PartitionContext partitionContext, CloseReason closeReason) {

    }

    @Override
    public void onEvents(PartitionContext partitionContext, Iterable<EventData> iterable) {
        for(EventData event: iterable) {
            try{
                EventPayload eventPayload = (EventPayload) SerializationUtils.deserialize(event.getBytes());
                eventPayloadProcessor.process(eventPayload);
                partitionContext.checkpoint(event);
            } catch (Exception e) {
                log.error("An Error occured when processing event data, exception: ", e);
            }

        }
    }

    @Override
    public void onError(PartitionContext partitionContext, Throwable throwable) {
    }
}