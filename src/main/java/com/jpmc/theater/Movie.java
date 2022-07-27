package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

public class Movie {
    private static int MOVIE_CODE_SPECIAL = 1;
    private static LocalTime EARLY_BIRD_START_TIME = LocalTime.of(10, 59, 59);
    private static LocalTime EARLY_BIRD_END_TIME = LocalTime.of(16, 01, 00);


    private String title;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;
    private String description;

    public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void putDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public double calculateTicketPrice(Showing showing) {
        return ticketPrice - getDiscount(showing);
    }

    private double getDiscount(Showing showing) {
        double discount = 0;

        //special discount with movie code
        if (MOVIE_CODE_SPECIAL == specialCode) {
            discount = Math.max(ticketPrice * 0.2, discount);  // 20% discount for special movie
        }

        //sequence of day discount
        if (showing.isSequence(1)) { //1st day
            discount = Math.max(3, discount);
        } else if (showing.isSequence(2)) { //2nd day
            discount = Math.max(2, discount);
        } else if (showing.isSequence(7)) { //7th day
            discount = Math.max(1, discount);
        }

        //early bird discount
        LocalTime showingTime = showing.getStartTime().toLocalTime();
        if (showingTime.isAfter(EARLY_BIRD_START_TIME) && showingTime.isBefore(EARLY_BIRD_END_TIME)) {
            discount = Math.max(ticketPrice * 0.25, discount);
        }

        //returns max discount from options available
        return discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0 && Objects.equals(title, movie.title) && Objects.equals(description, movie.description) && Objects.equals(runningTime, movie.runningTime) && Objects.equals(specialCode, movie.specialCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, ticketPrice, specialCode);
    }
}