package com.portfolio.ebilet.reservations.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "events")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PACKAGE)
@Getter
class Event extends BaseEntity{

    @Column(nullable = false)
    @NotNull
    private String name;

    private String description;

    @Column(nullable = false)
    @NotNull
    private Instant startDate;

    @Column(nullable = false)
    @NotNull
    private Instant endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    public void updateDetails(String name, String description, Instant start, Instant end) {
        this.name = name;
        this.description = description;
        this.startDate = start;
        this.endDate = end;
    }
}
