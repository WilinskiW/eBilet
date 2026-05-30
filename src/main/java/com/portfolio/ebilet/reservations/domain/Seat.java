package com.portfolio.ebilet.reservations.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Table(name = "seats")
@NoArgsConstructor
@SuperBuilder
@Setter(AccessLevel.PACKAGE)
class Seat extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(nullable = false)
    @NotNull
    private String category;

    @Column(nullable = false)
    @NotNull
    private String rowNum;

    @Column(nullable = false)
    @NotNull
    private String seatNum;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private SeatStatus status;

    @Column(nullable = false)
    @NotNull
    private BigDecimal price;
}
