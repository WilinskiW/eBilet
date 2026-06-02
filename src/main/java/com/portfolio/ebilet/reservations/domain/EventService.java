package com.portfolio.ebilet.reservations.domain;

import com.portfolio.ebilet.reservations.domain.dto.AddEventRequest;
import com.portfolio.ebilet.reservations.domain.dto.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventDto addEvent(AddEventRequest request) {
        var event = eventMapper.mapRequestToEntity(request);
        event = eventRepository.save(event);
        return eventMapper.mapToDto(event);
    }

    @Transactional(readOnly = true)
    public List<EventDto> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(eventMapper::mapToDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public EventDto getEvent(UUID uuid) {
        var event = getEventOrThrow(uuid);
        return eventMapper.mapToDto(event);
    }

    private Event getEventOrThrow(UUID uuid) {
        return eventRepository.findById(uuid).orElseThrow(
                () -> new EventNotFoundException(uuid.toString())
        );
    }
}
