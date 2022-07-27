package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocalDateProviderTests {
    @Test
    void makeSureCurrentDate() {
        System.out.println("The current time is: " + LocalDateProvider.singleton().currentDate());
        assertEquals(LocalDate.now(), LocalDateProvider.singleton().currentDate());
    }

    @Test
    void makeSureClassIsSinleton() {
        //hashcodes are the same for singleton classes
        LocalDate date1 = LocalDateProvider.singleton().currentDate();
        LocalDate date2 = LocalDateProvider.singleton().currentDate();
        assertTrue(date1.hashCode() == date2.hashCode());
    }
}
