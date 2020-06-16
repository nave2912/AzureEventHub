package com.eventhubdriven.eventhub.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eventhubdriven.eventhub.datamodel.EventPayload;
import com.eventhubdriven.eventhub.services.EventHubService;

@RestController
@CrossOrigin(origins = "*")
public class Controller {
	private final EventHubService eventHubService;
	private Logger log = LoggerFactory.getLogger(Controller.class);

	@Autowired
	public Controller(EventHubService eventHubService) {
		this.eventHubService = eventHubService;
	}

	@GetMapping(path = "/test")
	public String sendEvent() {
		return "testMessage";
	}

	@PostMapping(path = "/message/send",consumes="application/json")
	public ResponseEntity sendEvent(@RequestBody EventPayload payload) {
		try {
			log.info("Eventhub send endpoint called, sending {} to event hub..", payload.toString());
			eventHubService.sendEvent(payload);
		} catch (Exception e) {
			log.error("An error arose sending a message to event hub: " + e);
			return new ResponseEntity<Exception>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(HttpStatus.OK);
	}
}
