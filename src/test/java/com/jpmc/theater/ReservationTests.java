package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {
    @Test
    void totalFeeWith20PercentSpecialDiscount() {
        Customer customer = new Customer("John Doe", "id-12345");
        Showing showing = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                2, LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 0)));
        assertTrue(new Reservation(customer, showing, 3).totalFee() == 30);
    }

    @Test
    void totalFeeWith1stDayDiscount() {
        Customer customer = new Customer("John Doe", "id-12345");
        Showing showing = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1, LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 0)));
        assertTrue(new Reservation(customer, showing, 3).totalFee() == 28.5);
    }

    @Test
    void totalFeeWith2ndDayDiscount() {
        Customer customer = new Customer("John Doe", "id-12345");
        Showing showing = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0),
                2, LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 0)));
        assertTrue(new Reservation(customer, showing, 3).totalFee() == 31.5);
    }

    @Test
    void totalFeeWith7thDayDiscount() {
        Customer customer = new Customer("John Doe", "id-12345");
        Showing showing = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0),
                7, LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 0)));
        assertTrue(new Reservation(customer, showing, 3).totalFee() == 34.5);
    }

    @Test
    void totalFeeWith11amEarlyBirdDiscount() {
        Customer customer = new Customer("John Doe", "id-12345");
        Showing showing = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0),
                1, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0)));
        assertTrue(new Reservation(customer, showing, 3).totalFee() == 28.125);
    }

    @Test
    void totalFeeWith4PmEarlyBirdDiscount() {
        Customer customer = new Customer("John Doe", "id-12345");
        Showing showing = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0),
                2, LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 0)));
        assertTrue(new Reservation(customer, showing, 3).totalFee() == 28.125);
    }

    @Test
    void totalFeeBetween11AmAnd4PmDiscount() {
        Customer customer = new Customer("John Doe", "id-12345");
        Showing showing = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0),
                7, LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 0)));
        assertTrue(new Reservation(customer, showing, 3).totalFee() == 28.125);
    }

    @Test
    void totalFeeWithNoDiscount() {
        Customer customer = new Customer("John Doe", "id-12345");
        Showing showing = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0),
                4, LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 0)));
        assertTrue(new Reservation(customer, showing, 3).totalFee() == 37.5);
    }
}
