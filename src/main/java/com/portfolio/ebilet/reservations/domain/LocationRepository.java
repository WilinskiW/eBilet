package com.portfolio.ebilet.reservations.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface LocationRepository extends JpaRepository<Location, UUID> {
}
