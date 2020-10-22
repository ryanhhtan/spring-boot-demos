package com.example.authorservice.eventhandler;

import java.time.Instant;
import com.example.authorservice.common.Domain;
import lombok.Data;

/**
* OutgoingEvent
*/
@Data
public class OutgoingEvent {
	public OutgoingEvent(DomainEvent<? extends Domain> event) {
		this.event = event.getClass().getSimpleName();
		this.occuredAt = event.getOccuredAt();
		this.id = ((Domain) event.getSource()).getId().toString();
	}
	private String event;
	private Instant occuredAt;
	private String id;
}
