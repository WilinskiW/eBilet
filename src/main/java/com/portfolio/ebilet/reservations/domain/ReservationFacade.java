package com.portfolio.ebilet.reservations.domain;

import com.portfolio.ebilet.reservations.domain.dto.AddEventRequest;
import com.portfolio.ebilet.reservations.domain.dto.EventDto;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ReservationFacade {
    private final EventService eventService;

    public EventDto addEvent(AddEventRequest request) {
        return eventService.addEvent(request);
    }

    public List<EventDto> getAllEvents() {
        return eventService.getAllEvents();
    }

    public EventDto getEvent(UUID uuid) {
        return eventService.getEvent(uuid);
    }

    public void deleteEvent(UUID uuid) {
        eventService.deleteEvent(uuid);
    }

    public EventDto updateEvent(EventDto dto) {
        return eventService.updateEvent(dto);
    }
}
