package se.lexicon.vxo.presence.service.calendar;

import se.lexicon.vxo.presence.entity.calendar.AppCalendarDay;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import java.util.Set;

public interface CalendarService {

    Optional<AppCalendarDay> findByDate(LocalDate date);

    Set<AppCalendarDay> getDaysInMonth(Month month, int year);

    Set<AppCalendarDay> getDaysInWeek(int weekNumber, int year);

    Set<AppCalendarDay> findByYear(int year);

    Set<AppCalendarDay>findByDateBetween(LocalDate start, LocalDate end);

    boolean createCalendarYear(int year);
}
