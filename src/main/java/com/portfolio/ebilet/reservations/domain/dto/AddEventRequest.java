package com.portfolio.ebilet.reservations.domain.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record AddEventRequest(
        String name,
        String description,
        Instant startDate,
        Instant endDate,
        UUID locationId
) {
}
