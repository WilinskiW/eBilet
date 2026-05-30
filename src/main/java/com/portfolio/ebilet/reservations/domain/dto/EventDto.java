package com.portfolio.ebilet.reservations.domain.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record EventDto(
        String id,
        String name,
        String description,
        Instant startDate,
        Instant endDate,
        String city,
        String location
) {
}
