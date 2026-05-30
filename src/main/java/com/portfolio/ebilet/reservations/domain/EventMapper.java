package com.portfolio.ebilet.reservations.domain;

import com.portfolio.ebilet.reservations.domain.dto.AddEventRequest;
import com.portfolio.ebilet.reservations.domain.dto.EventDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface EventMapper {
    EventDto mapToDto(Event event);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Event mapRequestToEntity(AddEventRequest request);
}
