package com.jpmc.theater;

public class MovieTheaterDriver {

    public static void main(String[] args) {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedulePlainText();
        theater.printScheduleInJson();
    }
}
