package com.portfolio.ebilet.reservations.domain.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record EventDto(
        UUID id,
        String name,
        String description,
        Instant startDate,
        Instant endDate,
        LocationDto location
) {
}
