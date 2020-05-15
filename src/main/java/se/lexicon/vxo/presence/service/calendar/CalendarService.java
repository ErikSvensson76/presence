package se.lexicon.vxo.presence.service.calendar;

import se.lexicon.vxo.presence.entity.calendar.AppCalendarDay;

import java.time.LocalDate;
import java.time.Month;

public interface CalendarService {
    AppCalendarDay[] getYear(int year);
    AppCalendarDay[] getMonthInYear(Month month, int year);
    AppCalendarDay[] getDaysByWeekInYear(int weekNumber, int year);
    AppCalendarDay[] getDatesBetweenInclusive(LocalDate startDate, LocalDate endDate);

    /**
     * Always return full first and last weeks of a month.
     */
    AppCalendarDay[] getEvenMonthInYear(Month month, int year);

    /**
     * Always return a full week with 7 days, even with year breaks.
     */
    AppCalendarDay[] getEvenWeekInYear(int weekNumber, int year);
}
