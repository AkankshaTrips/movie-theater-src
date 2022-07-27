package com.jpmc.theater.mapper;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import static com.jpmc.theater.utils.ReadabilityFormatter.humanReadableFormatForTime;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    private static LocalDateTimeSerializer instance = null;

    public static LocalDateTimeSerializer singleton() {
        if (instance == null) {
            instance = new LocalDateTimeSerializer();
        }
        return instance;
    }

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String dateTimeValue = localDateTime.toLocalDate() + " " + humanReadableFormatForTime(localDateTime);
        if (dateTimeValue != null && !dateTimeValue.isEmpty() && !dateTimeValue.equals("null")) {
            jsonGenerator.writeString(dateTimeValue);
        } else {
            jsonGenerator.writeNull();
        }
    }
}