package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTests {
    @Test
    void movieWith20PercentSpecialDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30)));
        assertEquals(10, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void movieWith1stDayDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30)));
        assertEquals(9.5, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void movieWith2ndDayDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30)));
        assertEquals(10.5, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void movieWith7thDayDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0);
        Showing showing = new Showing(spiderMan, 7, LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30)));
        assertEquals(11.5, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void movieWith11AmEarlyBirdDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0)));
        assertEquals(9.375, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void movieWith4pmEarlyBirdDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 0)));
        assertEquals(9.375, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void movieBetween11AmAnd4PmEarlyBirdDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0);
        Showing showing = new Showing(spiderMan, 7, LocalDateTime.of(LocalDate.now(), LocalTime.of(13, 0)));
        assertEquals(9.375, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void movieWithNoDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0);
        Showing showing = new Showing(spiderMan, 4, LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30)));
        assertEquals(12.5, spiderMan.calculateTicketPrice(showing));
    }
}
