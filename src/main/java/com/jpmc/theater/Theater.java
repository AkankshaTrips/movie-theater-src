package com.jpmc.theater;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.jpmc.theater.mapper.DurationSerializer;
import com.jpmc.theater.mapper.LocalDateTimeSerializer;
import com.jpmc.theater.utils.ReadabilityFormatter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class Theater {

    LocalDateProvider provider;
    private static List<Showing> schedule;

    public Theater(LocalDateProvider provider) {
        this.provider = provider;

        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);

        spiderMan.putDescription("A boy with spider like superpowers.");
        turningRed.putDescription("A girl who when angry turns into a red bear.");
        theBatMan.putDescription("A wealthy man who wears a cape and saves Gotham City.");

        schedule = List.of(new Showing(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0))),
                new Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0))),
                new Showing(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50))),
                new Showing(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30))),
                new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10))),
                new Showing(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50))),
                new Showing(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30))),
                new Showing(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10))),
                new Showing(theBatMan, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0))));
    }

    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        requireNonNull(customer, "A customer is required to make a reservation.");

        if (howManyTickets < 1) {
            throw new IllegalArgumentException("A ticket is requires to make a reservation.");
        }

        Showing showing;
        try {
            showing = schedule.get(sequence - 1);
        } catch (RuntimeException ex) {
            throw new IllegalArgumentException("Not able to find any showing for the given sequence " + sequence);
        }

        return new Reservation(customer, showing, howManyTickets);
    }

    public void printSchedulePlainText() {
        String line = "=".repeat(135);

        System.out.println("Schedule for: " + provider.currentDate());
        System.out.format("%s %8s %20s %22s %40s %30s\n", "SEQUENCE", "TIME", "MOVIE", "DURATION", "DESCRIPTION", "PRICE");
        System.out.println(line);
        schedule.forEach(s -> System.out.format("%3s %15s %25s %20s %55s %5s %s\n", s.getSequenceOfTheDay(),
                ReadabilityFormatter.humanReadableFormatForTime(s.getStartTime()), s.getMovie().getTitle(),
                ReadabilityFormatter.humanReadableFormatForDuration(s.getMovie().getRunningTime()),
                s.getMovie().getDescription(), "$", s.getMovieFee()));
        System.out.println(line);
    }

    public void printScheduleInJson() {
        SimpleModule module = new SimpleModule()
                .addSerializer(Duration.class, DurationSerializer.singleton())
                .addSerializer(LocalDateTime.class, LocalDateTimeSerializer.singleton());

        ObjectMapper mapper = new ObjectMapper()
                .registerModule(module)
                .enable(SerializationFeature.INDENT_OUTPUT);

        String json;
        try {
            json = mapper.writeValueAsString(schedule);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(json);
    }
}
