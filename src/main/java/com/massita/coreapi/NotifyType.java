package com.massita.coreapi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public enum NotifyType {

    NEVER,
    TOMORROW,
    NEXT_WEEK,
    NEXT_MONTH;

    public LocalDateTime getNotifyTime() {
        switch (this) {
            case NEVER: return null;
            case TOMORROW:
                return LocalDateTime.now().plusDays(1).truncatedTo(ChronoUnit.DAYS).plusHours(10);
            case NEXT_WEEK:
                return LocalDate.now().with(DayOfWeek.MONDAY).atTime(10, 0);
            case NEXT_MONTH:
                return LocalDate.now().withDayOfMonth(1).atTime(10, 0);

        }
        return null;
    }

}
