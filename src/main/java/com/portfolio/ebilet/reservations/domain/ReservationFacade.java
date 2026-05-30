package com.portfolio.ebilet.reservations.domain;

import com.portfolio.ebilet.reservations.domain.dto.AddEventRequest;
import com.portfolio.ebilet.reservations.domain.dto.EventDto;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

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

    public List<EventDto> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(eventMapper::mapToDto)
                .toList();
    }

    public EventDto getEvent(UUID uuid) {
        var event = eventRepository.findById(uuid).orElseThrow(
                () -> new EventNotFoundException(uuid.toString())
        );
        return eventMapper.mapToDto(event);
    }
}
