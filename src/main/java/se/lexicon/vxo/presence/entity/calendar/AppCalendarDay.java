package se.lexicon.vxo.presence.entity.calendar;

import org.threeten.extra.YearWeek;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;


public class AppCalendarDay implements Comparable<AppCalendarDay>{

    private final  LocalDate date;
    private final int year;
    private final Month month;
    private final int yearWeek;
    private final DayOfWeek weekDay;


    AppCalendarDay(LocalDate date) {
        this.date = date;
        year = date.getYear();
        weekDay = date.getDayOfWeek();
        month = date.getMonth();


        yearWeek = YearWeek.from(date).getWeek();
    }

    public LocalDate getDate() {
        return date;
    }

    public String getWeekDay() {
        return date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
    }

    public String getMonth() {
        return date.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
    }

    public int getYearWeek() {
        return yearWeek;
    }

    public int getYear() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppCalendarDay that = (AppCalendarDay) o;
        return Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate());
    }

    @Override
    public int compareTo(AppCalendarDay o) {
        return date.compareTo(o.getDate());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AppCalendarDay{");
        sb.append("date=").append(date);
        sb.append(", year=").append(year);
        sb.append(", month=").append(month);
        sb.append(", yearWeek=").append(yearWeek);
        sb.append(", weekDay=").append(weekDay);
        sb.append('}');
        return sb.toString();
    }
}
