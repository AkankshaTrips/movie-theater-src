package com.jpmc.theater.mapper;

import java.io.IOException;
import java.time.Duration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jpmc.theater.utils.ReadabilityFormatter;

public class DurationSerializer extends JsonSerializer<Duration> {

    private static DurationSerializer instance = null;

    public static DurationSerializer singleton() {
        if (instance == null) {
            instance = new DurationSerializer();
        }
        return instance;
    }

    @Override
    public void serialize(Duration duration, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String durationValue = ReadabilityFormatter.humanReadableFormatForDuration(duration);
        if (durationValue != null && !durationValue.isEmpty() && !durationValue.equals("null")) {
            jsonGenerator.writeString(durationValue);
        } else {
            jsonGenerator.writeNull();
        }
    }
}