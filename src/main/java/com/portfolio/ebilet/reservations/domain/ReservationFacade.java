package com.portfolio.ebilet.reservations.domain;

import com.portfolio.ebilet.reservations.domain.dto.AddEventRequest;
import com.portfolio.ebilet.reservations.domain.dto.AddLocationRequest;
import com.portfolio.ebilet.reservations.domain.dto.EventDto;
import com.portfolio.ebilet.reservations.domain.dto.LocationDto;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ReservationFacade {
    private final EventService eventService;
    private final LocationService locationService;

    public EventDto addEvent(AddEventRequest request) {
        return eventService.addEvent(request);
    }

    public List<EventDto> getAllEvents() {
        return eventService.getAllEvents();
    }

    public EventDto getEvent(UUID uuid) {
        return eventService.getEvent(uuid);
    }

    public LocationDto createLocation(AddLocationRequest request){
        return locationService.createLocation(request);
    }
}
