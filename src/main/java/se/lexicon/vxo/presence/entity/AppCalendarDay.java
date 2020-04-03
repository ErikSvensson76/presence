package se.lexicon.vxo.presence.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Objects;

@Entity
public class AppCalendarDay implements Comparable<AppCalendarDay>{
    @Id
    private LocalDate date;
    private int year;
    private Month month;
    private int yearWeek;
    private DayOfWeek weekDay;


    public AppCalendarDay(LocalDate date) {
        this.date = date;
        year = date.getYear();
        weekDay = date.getDayOfWeek();
        month = date.getMonth();
        TemporalField weekOfYear = WeekFields.of(Locale.forLanguageTag("sv_SE")).weekOfWeekBasedYear();
        yearWeek = date.get(weekOfYear);
    }

    public AppCalendarDay(){}

    public LocalDate getDate() {
        return date;
    }

    public String getWeekDay() {
        return date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("sv_SE"));
    }

    public String getMonth() {
        return date.getMonth().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("sv_SE"));
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
