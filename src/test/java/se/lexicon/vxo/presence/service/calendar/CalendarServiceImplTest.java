package se.lexicon.vxo.presence.service.calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.vxo.presence.entity.calendar.AppCalendarDay;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class CalendarServiceImplTest {

    @Autowired
    private CalendarService calendarService;

    @Test
    void given_20200101_findByDate_return_success() {
        LocalDate _20200101 = LocalDate.parse("2020-01-01");
        Optional<AppCalendarDay> result = calendarService.findByDate(_20200101);

        assertTrue(result.isPresent());
        assertEquals(_20200101, result.get().getDate());
    }

    @BeforeEach
    void setUp() {
        calendarService.createCalendarYear(2020);
    }

    @Test
    void given_february_and_2020_getDaysInMonth_success() {
        Month february = Month.FEBRUARY;
        int year = 2020;

        int expectedSize = 29;
        LocalDate expectedLastDate = LocalDate.parse("2020-02-29");
        LocalDate expectedFirstDate = LocalDate.parse("2020-02-01");
        Set<AppCalendarDay> result = calendarService.getDaysInMonth(february, year);

        assertEquals(expectedSize, result.size());
        AppCalendarDay[] asArray = result.stream().toArray(AppCalendarDay[]::new);
        assertEquals(expectedFirstDate, asArray[0].getDate());
        assertEquals(expectedLastDate, asArray[asArray.length-1].getDate());
    }

    @Test
    void given_february_and_2020_getMonthWithFillerDates_return_35_days_successfully(){
        Month february = Month.FEBRUARY;
        int year = 2020;

        int expectedSize = 35;
        LocalDate expectedFirstDate = LocalDate.parse("2020-01-27");
        LocalDate expectedLastDate = LocalDate.parse("2020-03-01");

        Set<AppCalendarDay> result = calendarService.getMonthWithFillerDates(february, year);
        AppCalendarDay[] resultAsArray = result.stream().toArray(AppCalendarDay[]::new);

        assertEquals(expectedSize, result.size());
        assertEquals(expectedFirstDate, resultAsArray[0].getDate());
        assertEquals(expectedLastDate, resultAsArray[resultAsArray.length-1].getDate());
    }

    @Test
    void given_week53_and_year_2020_getDaysInWeek_success() {
        int week53 = 53;
        int year = 2020;

        int expectedSize = 4;
        LocalDate expectedFirstDate = LocalDate.parse("2020-12-28");
        LocalDate expectedLastDate = LocalDate.parse("2020-12-31");

        Set<AppCalendarDay> result = calendarService.getDaysInWeek(week53, year);

        assertEquals(expectedSize, result.size());
        AppCalendarDay[] asArray = result.stream().toArray(AppCalendarDay[]::new);
        assertEquals(expectedFirstDate, asArray[0].getDate());
        assertEquals(expectedLastDate, asArray[asArray.length-1].getDate());
    }

    @Test
    void given_week1_and_year_2020_getDaysInWeek_success() {
        int week1 = 1;
        int year = 2020;

        int expectedSize = 5;
        LocalDate expectedFirstDate = LocalDate.parse("2020-01-01");
        LocalDate expectedLastDate = LocalDate.parse("2020-01-05");

        Set<AppCalendarDay> result = calendarService.getDaysInWeek(week1, year);

        assertEquals(expectedSize, result.size());
        AppCalendarDay[] asArray = result.stream().toArray(AppCalendarDay[]::new);
        assertEquals(expectedFirstDate, asArray[0].getDate());
        assertEquals(expectedLastDate, asArray[asArray.length-1].getDate());
    }

    @Test
    void given_year2020_findByYear_should_return_set_size_366() {
        int year = 2020;
        int expectedSize = 366;
        Set<AppCalendarDay> result = calendarService.findByYear(year);
        assertEquals(expectedSize,result.size());
    }

    @Test
    void given_start_and_end_should_return_set_size_29() {
        LocalDate start = LocalDate.parse("2020-02-01");
        LocalDate end = LocalDate.parse("2020-02-29");

        int expectedSize = 29;

        Set<AppCalendarDay> result = calendarService.findByDateBetween(start,end);

        assertEquals(expectedSize, result.size());
        AppCalendarDay[] toArray = result.stream().toArray(AppCalendarDay[]::new);
        assertEquals(start, toArray[0].getDate());
        assertEquals(end, toArray[toArray.length-1].getDate());
    }

    @Test
    void given_invalid_year_createCalendarYear_should_throw_IllegalArgumentException() {
        int duplicateInvalidYear = 2020;
        assertThrows(
                IllegalArgumentException.class,
                () -> calendarService.createCalendarYear(duplicateInvalidYear)
        );
    }
}
