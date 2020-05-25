package se.lexicon.vxo.presence.service.calendar;

import org.springframework.stereotype.Service;
import se.lexicon.vxo.presence.entity.calendar.AppCalendarDay;
import se.lexicon.vxo.presence.entity.calendar.AppCalendarDayFactory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.stream.Stream;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class CalendarServiceImpl extends AppCalendarDayFactory implements CalendarService{

    @Override
    public AppCalendarDay[] getYear(final int year) {
        LocalDate start = LocalDate.ofYearDay(year, 1);
        LocalDate end = LocalDate.ofYearDay((year+1), 1);
        return generate(start, end);
    }

    @Override
    public AppCalendarDay[] getMonthInYear(Month month, int year) {
        final LocalDate start = LocalDate.of(year, month, 1);
        final LocalDate end = LocalDate.of(year, month.plus(1), 1);
        return generate(start, end);
    }

    private AppCalendarDay[] generate(LocalDate start, LocalDate end){
        long limit = DAYS.between(start, end);

        return Stream.iterate(start, date -> date.plusDays(1))
                .limit(limit)
                .map(super::createAppCalendarDay)
                .toArray(AppCalendarDay[]::new);
    }

    @Override
    public AppCalendarDay[] getDaysByWeekInYear(int weekNumber, int year) {
        return Stream.of(getYear(year))
                .filter(day -> day.getYearWeek() == weekNumber)
                .toArray(AppCalendarDay[]::new);
    }

    @Override
    public AppCalendarDay[] getDatesBetweenInclusive(LocalDate startDate, LocalDate endDate) {
        return generate(startDate, endDate.plusDays(1));
    }

    @Override
    public AppCalendarDay[] getEvenMonthInYear(Month month, int year) {
        LocalDate firstDay = LocalDate.of(year, month, 1);
        LocalDate lastDay = LocalDate.of(year, month.plus(1), 1).minusDays(1);

        firstDay = firstDay.getDayOfWeek() == DayOfWeek.MONDAY ? firstDay : getLastMonday(firstDay);
        lastDay = lastDay.getDayOfWeek() == DayOfWeek.SUNDAY ? lastDay : getNextSunday(lastDay);

        return getDatesBetweenInclusive(firstDay, lastDay);
    }


    @Override
    public AppCalendarDay[] getEvenWeekInYear(int weekNumber, int year) {
        AppCalendarDay[] weekDays = getDaysByWeekInYear(weekNumber, year);
        if(weekDays.length == 7 || weekDays.length == 0){
            return weekDays;
        }
        LocalDate first = weekDays[0].getDate();
        LocalDate last = weekDays[weekDays.length-1].getDate();

        first = first.getDayOfWeek() == DayOfWeek.MONDAY ? first : getLastMonday(first);
        last = last.getDayOfWeek() == DayOfWeek.SUNDAY ? last : getNextSunday(last);

        return getDatesBetweenInclusive(first, last);
    }

    public LocalDate getLastMonday(LocalDate date){
        return date.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
    }

    public LocalDate getNextSunday(LocalDate date) {
        return date.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
    }


}
