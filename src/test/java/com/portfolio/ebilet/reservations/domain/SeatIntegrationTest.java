package com.portfolio.ebilet.reservations.domain;

import com.portfolio.ebilet.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SeatIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private EventRepository eventRepository;

    @Test
    void shouldThrowOptimisticLockingExceptionWhenConcurrentUpdateOccurs(){
        // given
        Event event = eventRepository.saveAndFlush(createEvent());

        Seat seat = Seat.builder()
                .event(event)
                .category("VIP")
                .rowNum("34A")
                .seatNum("121")
                .status(SeatStatus.AVAILABLE)
                .price(BigDecimal.valueOf(400))
                .build();

        seatRepository.saveAndFlush(seat);

        // when
        CountDownLatch latch = new CountDownLatch(1);

        CompletableFuture<Void> userA = CompletableFuture.runAsync(() -> {
            try {
                latch.await();
                Seat s = seatRepository.findById(seat.getId()).orElseThrow();
                s.setStatus(SeatStatus.RESERVED);
                seatRepository.saveAndFlush(s);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<Void> userB = CompletableFuture.runAsync(() -> {
            try {
                latch.await();
                Seat s = seatRepository.findById(seat.getId()).orElseThrow();
                s.setStatus(SeatStatus.RESERVED);
                seatRepository.saveAndFlush(s);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        latch.countDown();

        // then
        assertThatThrownBy(() -> CompletableFuture.allOf(userA, userB).get())
                .isInstanceOf(ExecutionException.class)
                .hasCauseInstanceOf(RuntimeException.class)
                .hasStackTraceContaining(ObjectOptimisticLockingFailureException.class.getName());
    }

    private Event createEvent() {
        return Event.builder()
                .name("Test Event")
                .description("Test Event Description")
                .startDate(Instant.now())
                .endDate(Instant.now())
                .city("Test City")
                .location("Test Location")
                .build();
    }
}
