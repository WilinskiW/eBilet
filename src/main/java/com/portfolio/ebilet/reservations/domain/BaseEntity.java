package com.portfolio.ebilet.reservations.domain;

import com.fasterxml.uuid.Generators;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Setter(value = AccessLevel.PROTECTED)
@Getter
public abstract class BaseEntity implements Identifiable{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id = Generators.timeBasedEpochGenerator().generate(); // v7

    @Version
    @Column(nullable = false)
    private Long version;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant updatedAt;

    @Override
    public void setId(UUID id) {
        this.id = id;
    }
}