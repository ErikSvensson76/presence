package se.lexicon.vxo.presence.service.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.vxo.presence.data.AppCalendarDayRepository;
import se.lexicon.vxo.presence.entity.calendar.AppCalendarDay;
import se.lexicon.vxo.presence.entity.calendar.AppCalendarDayFactory;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CalendarServiceImpl extends AppCalendarDayFactory implements CalendarService {

    private AppCalendarDayRepository calendarRepository;

    @Autowired
    public CalendarServiceImpl(AppCalendarDayRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    @Override
    public Optional<AppCalendarDay> findByDate(LocalDate date){
        return calendarRepository.findByDate(date);
    }

    @Override
    public Set<AppCalendarDay> getDaysInMonth(Month month, int year){
        return calendarRepository.findDaysInMonth(month, year);
    }

    @Override
    public Set<AppCalendarDay> getDaysInWeek(int weekNumber, int year){
        return calendarRepository.findByWeekNumber(weekNumber,year);
    }

    @Override
    public Set<AppCalendarDay> findByYear(int year){
        return calendarRepository.findAllDaysInYear(year);
    }

    @Override
    public Set<AppCalendarDay>findByDateBetween(LocalDate start, LocalDate end){
        return calendarRepository.findByDateBetween(start,end);
    }

    @Override
    public boolean createCalendarYear(int year){
        if(calendarRepository.findByDate(LocalDate.ofYearDay(year, 1)).isPresent()){
            throw new IllegalArgumentException("Already present in the database " + LocalDate.ofYearDay(year, 1));
        }
        List<AppCalendarDay> days = Stream.iterate(LocalDate.ofYearDay(year, 1), date -> date.plusDays(1))
                .map(super::createAppCalendarDay)
                .limit(Year.of(year).isLeap() ? 366 : 365)
                .collect(Collectors.toList());

        days = calendarRepository.saveAll(days);
        return days.get(0).getDate().equals(LocalDate.ofYearDay(year, 1)) &&
                days.get(days.size()-1).getDate().equals(LocalDate.of(year,12,31));
    }
}
