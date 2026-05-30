package com.portfolio.ebilet.reservations.domain.dto;

public record AddLocationRequest(
        String name,
        String city,
        String address,
        String country
) {
}
