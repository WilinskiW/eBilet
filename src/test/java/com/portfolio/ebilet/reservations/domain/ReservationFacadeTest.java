package com.portfolio.ebilet.reservations.domain;

import com.portfolio.ebilet.reservations.domain.dto.AddEventRequest;
import com.portfolio.ebilet.reservations.domain.dto.EventDto;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    void should_get_all_events(){
        // Given
        givenEventExists("Test concert 1");
        givenEventExists("Test concert 2");
        givenEventExists("Test concert 3");

        // When
        var events = facade.getAllEvents();

        // Then
        assertThat(events)
                .hasSize(3)
                .extracting(EventDto::name)
                .containsExactlyInAnyOrder("Test concert 1", "Test concert 2", "Test concert 3");
    }

    private void givenEventExists(String name){
        facade.addEvent(createAddEventRequest(name));
    }

    private AddEventRequest createAddEventRequest(String name){
        return AddEventRequest.builder()
                .name(name)
                .description("Test description")
                .startDate(Instant.now())
                .endDate(Instant.now())
                .city("Test city")
                .location("Test location")
                .build();
    }


    @Test
    void should_get_event_by_id(){
        // Given
        var targetEvent = facade.addEvent(createAddEventRequest("Test concert"));

        // When
        var event = facade.getEvent(UUID.fromString(targetEvent.id()));

        // Then
        assertThat(event).isEqualTo(targetEvent);
    }

    @Test
    void should_throw_exception_if_event_not_found(){
        // Given
        UUID uuid = UUID.randomUUID();

        // When & Then
        assertThatThrownBy(() -> facade.getEvent(uuid))
                .isInstanceOf(EventNotFoundException.class)
                .hasMessage(String.format("Event with ID: %s not found", uuid));
    }
}
