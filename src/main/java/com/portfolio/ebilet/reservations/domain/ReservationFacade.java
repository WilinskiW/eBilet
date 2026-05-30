package com.portfolio.ebilet.reservations.domain;

import com.portfolio.ebilet.reservations.domain.dto.AddEventRequest;
import com.portfolio.ebilet.reservations.domain.dto.EventDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReservationFacade {
    private final ReservationRepository reservationRepository;
    private final EventRepository eventRepository;
    private final SeatRepository seatRepository;
    private final EventMapper eventMapper;


    public EventDto addEvent(AddEventRequest request) {
        var event = eventMapper.mapRequestToEntity(request);
        event = eventRepository.save(event);
        return eventMapper.mapToDto(event);
    }

}
