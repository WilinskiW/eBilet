package com.portfolio.ebilet.reservations.domain;

import com.portfolio.ebilet.reservations.domain.dto.AddEventRequest;
import com.portfolio.ebilet.reservations.domain.dto.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class EventService {
    private final EventRepository eventRepository;
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
        var event = getEventOrThrow(uuid);
        return eventMapper.mapToDto(event);
    }

    private Event getEventOrThrow(UUID uuid) {
        return eventRepository.findById(uuid).orElseThrow(
                () -> new EventNotFoundException(uuid.toString())
        );
    }

    public void deleteEvent(UUID uuid) {
        var event = getEventOrThrow(uuid);
        eventRepository.delete(event);
    }

    public EventDto updateEvent(EventDto dto) {
        var event = getEventOrThrow(UUID.fromString(dto.id()));

        event.updateDetails(
                dto.name(),
                dto.description(),
                dto.startDate(),
                dto.endDate(),
                dto.city(),
                dto.location()
        );

        return eventMapper.mapToDto(event);
    }
}
