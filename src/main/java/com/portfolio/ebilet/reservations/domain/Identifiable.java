package com.portfolio.ebilet.reservations.domain;

import java.util.UUID;

interface Identifiable {
    UUID getId();
    void setId(UUID id);
}
