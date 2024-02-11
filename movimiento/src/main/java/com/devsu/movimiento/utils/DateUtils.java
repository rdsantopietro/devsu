package com.devsu.movimiento.utils;

import java.time.*;
import java.time.temporal.ChronoField;

public class DateUtils {

    public static Instant localDateToInstant(LocalDate localDate) {
        ZoneId buenosAiresZone = ZoneId.of("America/Argentina/Buenos_Aires");
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(buenosAiresZone)
                .with(ChronoField.OFFSET_SECONDS, ZoneOffset.UTC.getTotalSeconds());
        return zonedDateTime.toInstant();
    }
}
