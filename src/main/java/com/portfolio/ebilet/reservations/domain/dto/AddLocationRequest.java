package com.portfolio.ebilet.reservations.domain.dto;

import lombok.Builder;

@Builder
public record AddLocationRequest(
        String name,
        String city,
        String address,
        String country
) {
}
