package com.portfolio.ebilet.reservations.domain;

public class EventNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Event with ID: %s not found";

    public EventNotFoundException(String uuid) {
        super(String.format(DEFAULT_MESSAGE, uuid));
    }
}
