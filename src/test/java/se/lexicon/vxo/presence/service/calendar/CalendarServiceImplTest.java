package se.lexicon.vxo.presence.service.calendar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.vxo.presence.entity.calendar.AppCalendarDay;
import se.lexicon.vxo.presence.entity.calendar.AppCalendarDayFactory;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = CalendarServiceImpl.class)
public class CalendarServiceImplTest extends AppCalendarDayFactory {

    @Autowired
    private CalendarService calendarService;

    @Test
    @DisplayName("Given year getYear should return whole year of AppCalendarDay as array")
    void getYear() {
        int year = 2020;

        LocalDate first = LocalDate.parse("2020-01-01");
        LocalDate last = LocalDate.parse("2020-12-31");
        int expectedLength = 366;

        AppCalendarDay[] result = calendarService.getYear(year);

        assertEquals(expectedLength, result.length);
        assertEquals(first, result[0].getDate());
        assertEquals(last, result[result.length-1].getDate());
    }

    @Test
    @DisplayName("Given month getMonthInYear should return correct data")
    void getMonthInYear() {
        Month month = Month.FEBRUARY;
        int year = 2020;

        int expectedLength = 29;
        LocalDate first = LocalDate.parse("2020-02-01");
        LocalDate last = LocalDate.parse("2020-02-29");

        AppCalendarDay[] result = calendarService.getMonthInYear(month, year);

        assertEquals(expectedLength, result.length);
        assertEquals(first, result[0].getDate());
        assertEquals(last, result[result.length-1].getDate());
    }

    @Test
    @DisplayName("Given week53 and year 2020 getDaysInWeek return correct data")
    void given_week53_and_year_2020_getDaysInWeek_success() {
        int week53 = 53;
        int year = 2020;

        int expectedSize = 4;
        LocalDate expectedFirstDate = LocalDate.parse("2020-12-28");
        LocalDate expectedLastDate = LocalDate.parse("2020-12-31");

        AppCalendarDay[] result = calendarService.getDaysByWeekInYear(week53, year);

        assertEquals(expectedSize, result.length);
        assertEquals(expectedFirstDate, result[0].getDate());
        assertEquals(expectedLastDate, result[result.length-1].getDate());
    }

    @Test
    @DisplayName("Given week1 and year 2020 getDaysInWeek return correct data")
    void given_week1_and_year_2020_getDaysInWeek_success() {
        int week1 = 1;
        int year = 2020;

        int expectedSize = 5;
        LocalDate expectedFirstDate = LocalDate.parse("2020-01-01");
        LocalDate expectedLastDate = LocalDate.parse("2020-01-05");

        AppCalendarDay[] result = calendarService.getDaysByWeekInYear(week1, year);

        assertEquals(expectedSize, result.length);

        assertEquals(expectedFirstDate, result[0].getDate());
        assertEquals(expectedLastDate, result[result.length-1].getDate());
    }

    @Test
    @DisplayName("Given LocalDates start and end getDatesBetweenInclusive should return correct data")
    void getDatesBetweenInclusive() {
        LocalDate start = LocalDate.parse("2020-05-15");
        LocalDate end = LocalDate.parse("2020-05-16");

        int expectedLength = 2;

        AppCalendarDay[] result = calendarService.getDatesBetweenInclusive(start, end);

        assertEquals(expectedLength, result.length);
        assertEquals(start, result[0].getDate());
        assertEquals(end, result[result.length-1].getDate());
    }

    @Test
    @DisplayName("Given month and year getEvenMonthInYear should return correct data")
    void getEvenMonthInYear() {
        Month month = Month.JANUARY;
        int year = 2020;

        LocalDate first = LocalDate.parse("2019-12-30");
        LocalDate last = LocalDate.parse("2020-02-02");
        int expectedLength = 35;

        AppCalendarDay[] result = calendarService.getEvenMonthInYear(month, year);

        assertEquals(expectedLength, result.length);
        assertEquals(first, result[0].getDate());
        assertEquals(last, result[result.length-1].getDate());
    }

    @Test
    void getEvenWeekInYear() {
        AppCalendarDay[] expected = {
                super.createAppCalendarDay(LocalDate.parse("2019-12-30")),
                super.createAppCalendarDay(LocalDate.parse("2019-12-31")),
                super.createAppCalendarDay(LocalDate.parse("2020-01-01")),
                super.createAppCalendarDay(LocalDate.parse("2020-01-02")),
                super.createAppCalendarDay(LocalDate.parse("2020-01-03")),
                super.createAppCalendarDay(LocalDate.parse("2020-01-04")),
                super.createAppCalendarDay(LocalDate.parse("2020-01-05"))
        };

        int weekNumber = 1;
        int year = 2020;

        assertArrayEquals(expected, calendarService.getEvenWeekInYear(weekNumber, year));
    }
}
