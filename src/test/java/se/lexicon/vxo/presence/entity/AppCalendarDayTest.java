package se.lexicon.vxo.presence.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AppCalendarDayTest {

    public static final LocalDate _2020_01_01 = LocalDate.parse("2020-01-01");
    public static final LocalDate _2019_12_31 = LocalDate.parse("2019-12-31");

    @Test
    void given_20200101_successfully_create_object() {
        AppCalendarDay testObject = new AppCalendarDay(_2020_01_01);
        assertEquals(_2020_01_01, testObject.getDate());
        assertEquals(2020, testObject.getYear());
        assertEquals(1, testObject.getYearWeek());
        assertEquals("januari", testObject.getMonth());
        assertEquals("onsdag", testObject.getWeekDay());
    }

    @Test
    void given_20191231_successfully_create_object() {
        AppCalendarDay testObject = new AppCalendarDay(_2019_12_31);
        assertEquals(_2019_12_31, testObject.getDate());
        assertEquals(2019, testObject.getYear());
        assertEquals(1, testObject.getYearWeek());
        assertEquals("december", testObject.getMonth());
        assertEquals("tisdag", testObject.getWeekDay());
    }
}
