package com.portfolio.ebilet.reservations.domain;

import com.portfolio.ebilet.reservations.domain.dto.AddEventRequest;
import com.portfolio.ebilet.reservations.domain.dto.EventDto;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class ReservationFacadeTest {
    private final ReservationFacade facade = createFacade();

    static ReservationFacade createFacade(){
        return new ReservationFacade(null,
                new InMemoryEventRepository(),
                null,
                new EventMapperImpl()
                );
    }

    @Test
    void should_add_new_event_if_it_doesnt_exist(){
        // Given
        var request = AddEventRequest.builder()
                .name("Test concert")
                .description("Test description")
                .startDate(Instant.now())
                .endDate(Instant.now())
                .city("Test city")
                .location("Test location")
                .build();

        // When
        EventDto dto = facade.addEvent(request);

        // Then
        assertThat(dto)
                .hasFieldOrProperty("id")
                .matches(e -> e.name().equals("Test concert"))
                .matches(e -> e.description().equals("Test description"))
                .matches(e -> e.startDate().equals(request.startDate()))
                .matches(e -> e.endDate().equals(request.endDate()))
                .matches(e -> e.city().equals("Test city"))
                .matches(e -> e.location().equals("Test location"));
    }
}
