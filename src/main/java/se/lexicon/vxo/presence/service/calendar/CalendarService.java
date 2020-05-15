package se.lexicon.vxo.presence.service.calendar;

import se.lexicon.vxo.presence.entity.calendar.AppCalendarDay;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public interface CalendarService {

    String JPA_IMPL_BEAN_NAME = "jpa_impl";

    Set<AppCalendarDay> getMonthWithFillerDates(Month month, int year);

    Optional<AppCalendarDay> findByDate(LocalDate date);

    Set<AppCalendarDay> getDaysInMonth(Month month, int year);

    Set<AppCalendarDay> getDaysInWeek(int weekNumber, int year);

    Set<AppCalendarDay> findByYear(int year);

    Set<AppCalendarDay>findByDateBetween(LocalDate start, LocalDate end);

    boolean createCalendarYear(int year);
}
