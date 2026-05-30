package com.portfolio.ebilet.reservations.domain.dto;

import java.util.UUID;

public record LocationDto(
        UUID id,
        String name,
        String city,
        String address,
        String country
) {
}
