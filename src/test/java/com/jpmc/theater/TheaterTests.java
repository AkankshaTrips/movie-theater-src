package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheaterTests {
    @Test
    void totalFeeWithSpecialDiscount() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 5, 3);
        assertEquals(30, reservation.totalFee());
    }

    @Test
    void totalFeeWith1stDayDiscount() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 1, 4);
        assertEquals(32, reservation.totalFee());
    }

    @Test
    void totalFeeWith11AmEarlyBird11AmDiscount() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 2, 4);
        assertEquals(37.5, reservation.totalFee());
    }

    @Test
    void totalFeeWithBetween11AmAnd4pmEarlyBirdDiscount() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 4, 3);
        assertEquals(24.75, reservation.totalFee());
    }

    @Test
    void totalFeeWithNoDiscount() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 9, 2);
        assertEquals(18, reservation.totalFee());
    }

    @Test
    void totalFeeForNoCustomer() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        try {
            theater.reserve(null, 6, 3);
        } catch (NullPointerException e) {
            assertEquals("A customer is required to make a reservation.", e.getLocalizedMessage());
        }
    }

    @Test
    void totalFeeForNoTickets() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        try {
            theater.reserve(john, 5, 0);
        } catch (IllegalArgumentException e) {
            assertEquals("A ticket is requires to make a reservation.", e.getLocalizedMessage());
        }
    }

    @Test
    void totalFeeForNoShowing() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        try {
            theater.reserve(john, 10, 3);
        } catch (IllegalArgumentException e) {
            assertEquals("Not able to find any showing for the given sequence 10", e.getLocalizedMessage());
        }
    }

    @Test
    void printMovieScheduleinPlainText() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedulePlainText();
    }

    @Test
    void printMovieScheduleInJson() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printScheduleInJson();
    }
}
