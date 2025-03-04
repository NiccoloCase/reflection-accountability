package org.nc.operational;

import java.time.LocalDate;

public class TimePeriod {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public TimePeriod(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public boolean includes(LocalDate date) {
        if (date == null) return false;
        boolean afterStart = (startDate == null) || !date.isBefore(startDate);
        boolean beforeEnd = (endDate == null) || !date.isAfter(endDate);
        return afterStart && beforeEnd;
    }
}